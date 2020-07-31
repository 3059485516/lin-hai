package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.nim.yl.NetIMCache;
import com.netease.nim.yl.upload.AcceleratorConfig;
import com.netease.nim.yl.upload.NOSUpload;
import com.netease.nim.yl.upload.NOSUploadHandler;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.UploadBadHabitsContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/31 3:07 PM
 * desc   : EMPTY
 * ================================================
 */
public class UploadBadHabitsPresenter extends UploadBadHabitsContract.Presenter {
    private NOSUpload.UploadExecutor mUploadExecutor;

    public UploadBadHabitsPresenter(UploadBadHabitsContract.View view, Intent intent) {
        super(view, intent);
    }

    @Override
    public void addContent() {
        uploadInit();
    }

    private Observable<AcceleratorConfig> loadDefaultAcceleratorConfig = Observable.create(new ObservableOnSubscribe<AcceleratorConfig>() {
        @Override
        public void subscribe(ObservableEmitter<AcceleratorConfig> emitter) throws Exception {
            AcceleratorConfig acceleratorConfig;
            try {
                acceleratorConfig = new AcceleratorConfig();
                acceleratorConfig.setChunkSize(1024 * 64);
                acceleratorConfig.setChunkRetryCount(2);
                acceleratorConfig.setConnectionTimeout(10 * 1000);
                acceleratorConfig.setSoTimeout(30 * 1000);
                acceleratorConfig.setLbsConnectionTimeout(10 * 1000);
                acceleratorConfig.setLbsSoTimeout(10 * 1000);
                acceleratorConfig.setRefreshInterval(2 * 1000);
                acceleratorConfig.setMonitorInterval(120 * 1000);
                acceleratorConfig.setMonitorThread(true);
                acceleratorConfig.setQueryRetryCount(2);
                emitter.onNext(acceleratorConfig);
            } catch (InvalidChunkSizeException |
                    InvalidParameterException e) {
                emitter.onError(e);
            }
        }
    });

    private Observable<NOSUpload> initNosUpload = Observable.create(new ObservableOnSubscribe<NOSUpload>() {
        @Override
        public void subscribe(ObservableEmitter<NOSUpload> e) throws Exception {
            final NOSUpload nOSUpload = NOSUpload.getInstace(mContext);
            NOSUpload.Config config = new NOSUpload.Config();
            config.appKey = AppUtils.readAppKey(App.get().getApplication());
            config.accid = NetIMCache.getAccount();
            config.token = NetIMCache.getToken();
            nOSUpload.setConfig(config);
            e.onNext(nOSUpload);
        }
    });

    private void uploadInit() {
        final Observable<NOSUpload> nosUploadObservable = loadDefaultAcceleratorConfig.concatMap(new Function<AcceleratorConfig, ObservableSource<NOSUpload>>() {
            @Override
            public ObservableSource<NOSUpload> apply(AcceleratorConfig acceleratorConfig) throws Exception {
                if (acceleratorConfig != null) {
                    return initNosUpload;
                }
                return null;
            }
        });
        addRx2Destroy(new RxSubscriber<NOSUpload>(nosUploadObservable) {
            @Override
            protected void _onNext(NOSUpload nosUpload) {
                mView.showCircleProgressDialog();
                final String filePath = getFilePath();
                File file = new File(filePath);
                final String name = file.getName();
                nosUpload.fileUploadInit(name, name, -1, Config.NETEASE_VIDEO_TEMPLATE,
                        null, "", -1, null, new NOSUploadHandler.UploadInitCallback() {
                            @Override
                            public void onSuccess(String nosToken, String bucket, String object) {
                                doUpload(nosUpload, file, bucket, object, nosToken);
                            }

                            @Override
                            public void onFail(int code, String msg) {
//                                KLog.d(TAG, "code = " + code + ", msg = " + msg);
                                sendUploadError(msg);
                            }
                        });
            }
        });
    }

    /**
     * 开始发布了(回调是主线程)
     */
    private void doUpload(NOSUpload nosUpload, final File file, final String bucket, final String object, String token) {
        /**
         *  查看一个这个文件是否已经发布过，如果发布过就取出它的uploadcontext, 传给NosUpload.putFileByHttp
         *  进行断点续传,当uploadContext是null时, 就从头开始往发布
         */
        String uploadContext = nosUpload.getUploadContext(file);
        try {
            mUploadExecutor = nosUpload.putFileByHttp(file, uploadContext, bucket, object, token, new NOSUploadHandler.UploadCallback() {
                @Override
                public void onUploadContextCreate(String oldUploadContext, String newUploadContext) {
                    //将新的uploadcontext保存起来
                    nosUpload.setUploadContext(file, newUploadContext);
//                    KLog.d(TAG, "doUpload onUploadContextCreate: " + Thread.currentThread().getName());
                }

                @Override
                public void onProcess(long current, long total) {
//                    KLog.d(TAG, "doUpload onProcess: " + Thread.currentThread().getName());
//                    KLog.d(TAG, "current = " + current + ", total = " + total);
                    int progress = (int) ((100.0F * current / total));
                    if (mView != null) {
                        mView.setUploadProgress(progress);
                    }
                }

                @Override
                public void onSuccess(CallRet ret) {
//                    KLog.d(TAG, "doUpload onSuccess: " + Thread.currentThread().getName());
                    String json = ret.getCallbackRetMsg();
                    try {
                        String vid = null;
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("ret");
                        if (jsonObject1 != null) {
                            vid = jsonObject1.optString("vid");
                        }
                        mUploadExecutor = null;
//                        KLog.d(TAG, "播放地址 = http://nos.netease.com/" + bucket + "/" + object);
                        nosUpload.setUploadContext(file, "");
                        if (!TextUtils.isEmpty(vid)) {
                            uploadSuccess(vid);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(CallRet ret) {
                    sendUploadError("发布失败");
//                    KLog.d(TAG, "发布失败");
                    mUploadExecutor = null;
                }

                @Override
                public void onCanceled(CallRet ret) {
                    try {
//                        KLog.d(TAG, "发布取消");
                        mUploadExecutor = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mUploadExecutor.join();
        } catch (Exception e) {
//            KLog.d(TAG, "发布发生异常");
        }
    }

    private void uploadSuccess(String vid) {
        String title = mView.getVideoTitle();
        String content = mView.getContent();
        String nodeId  = getSelectedNode();
        final Map<String, Object> param = createParam(nodeId, title, vid, content);
        addRx2Destroy(new RxSubscriber(Api.shootBadHabit(param)) {
            @Override
            protected void _onNext(Object o) {
                toast("发布成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                sendUploadError("发布失败");
            }
        });
    }


    private void sendUploadError(String errorMsg) {
        Observable.just(errorMsg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addRx2Destroy(d);
                    }

                    @Override
                    public void onNext(String s) {
                        mView.dissCircleProgressDialog();
                        toast(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void submitCancel() {
        if (mUploadExecutor != null) {
            mUploadExecutor.cancel();
            mUploadExecutor = null;
        }
    }

    private Map<String,Object> createParam(String nodeId,String title,String video_path,String contents){
        Map<String,Object> map = new HashMap<>();
        map.put("pre_node_id",nodeId);
        map.put("title",title);
        map.put("video_path",video_path);
        map.put("contents",contents);
        return map;
    }
}
