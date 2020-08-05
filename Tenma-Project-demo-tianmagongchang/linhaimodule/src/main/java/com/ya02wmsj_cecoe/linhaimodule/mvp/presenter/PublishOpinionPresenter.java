package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.luck.picture.lib.entity.LocalMedia;
import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.nim.yl.NetIMCache;
import com.netease.nim.yl.upload.AcceleratorConfig;
import com.netease.nim.yl.upload.NOSUpload;
import com.netease.nim.yl.upload.NOSUploadHandler;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishOpinionContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.AppUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.DragPointView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class PublishOpinionPresenter extends PublishOpinionContract.Presenter {
    private List<LocalMedia> mImages = new ArrayList<>();
    private NOSUpload.UploadExecutor mUploadExecutor;

    public PublishOpinionPresenter(PublishOpinionContract.View view,Intent intent) {
        super(view,intent);
    }

    @Override
    public List<LocalMedia> getImageList() {
        return mImages;
    }

    @Override
    public void commit(Map<String, Object> map) {
        mView.showDialog("正在提交请稍候..");
        addRx2Destroy(new RxSubscriber<Object>(Api.addContent(map, mImages)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
                if (HttpCode.CODE_20012.getCode().equals(code)) {
                    mView.gotoActivity(new Intent(mContext, BindRegionActivity.class));
                }
            }
        });
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
                throw new IllegalArgumentException("acceleratorConfig init failed!");
            }
        });
        addRx2Destroy(new RxSubscriber<NOSUpload>(nosUploadObservable) {
            @Override
            protected void _onNext(NOSUpload nosUpload) {
                mView.showCircleProgressDialog();
                final String filePath = getFileVideoPath();
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
//                            uploadSuccess(vid);
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

  /*  private void uploadSuccess(String vid) {
        String title = mView.getVideoTitle();
        String content = mView.getContent();
        String nodeId  = getSelectedNode();
        final String regionCode = mView.getRegionCode();
        final Map<String, Object> param = createParam(nodeId, title, vid, content,regionCode);
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
    }*/


    private void sendUploadError(String errorMsg) {
        Single.just(errorMsg).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addRx2Destroy(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        mView.dissCircleProgressDialog();
                        toast(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

   /* @Override
    public void submitCancel() {
        if (mUploadExecutor != null) {
            mUploadExecutor.cancel();
            mUploadExecutor = null;
        }
    }*/

    private Map<String,Object> createParam(String nodeId,String title,String video_path,String contents,String regionCode){
        Map<String,Object> map = new HashMap<>();
        map.put("pre_node_id",nodeId);
        map.put("title",title);
        map.put("video_path",video_path);
        map.put("contents",contents);
        map.put("region_code",regionCode);
        return map;
    }
}
