package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.nim.yl.NetIMCache;
import com.netease.nim.yl.upload.AcceleratorConfig;
import com.netease.nim.yl.upload.NOSUpload;
import com.netease.nim.yl.upload.NOSUploadHandler;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ShowCvilizePublishContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * 我要秀文明 发布图片或视频页面
 */
public class ShowCvilizePublishPresenter extends ShowCvilizePublishContract.Presenter {
    private static final String TAG = "ShowCvilizePublishPresenter";
    private NOSUpload mNOSUpload;
    private NOSUpload.UploadExecutor mUploadExecutor;
    private AcceleratorConfig mAcceleratorConfig;
    private String mFileName;
    private Map<String, Object> mParams;
    private static final int MSG_UPLOAD_ERROR = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPLOAD_ERROR) {
                mView.dissCircleProgressDialog();
                toast((String) msg.obj);
            }
        }
    };

    public ShowCvilizePublishPresenter(ShowCvilizePublishContract.View view) {
        super(view);
    }


    @Override
    public void editContent(Map<String, Object> map, String videoPath) {
        mParams = map;
        if (getImageList().size() == 0) {
            toast("请选择上传图片");
            return;
        }
        if (!TextUtils.isEmpty(videoPath)) {
            File file = new File(videoPath);
            if (!file.exists()) {
                toast("视频文件不存在");
                return;
            }
            if (!Config.getInstance().isLoginIm()) {
                toast("正在登陆IM，请稍后重试");
                Config.getInstance().loginNim(Config.getInstance().getUser().getUuid(), Config.getInstance().getUser().getToken());
                return;
            }
            if (loadDefaultAcceleratorConfig()) {
                initNosUpload();
                uploadInit(file);
                mView.showCircleProgressDialog();
            } else {
                toast("初始化发布参数失败");
            }
        } else {
            uploadSuccess(mParams);
        }
    }

    /**
     * 初始化发布参数
     */
    private boolean loadDefaultAcceleratorConfig() {
        try {
            if (mAcceleratorConfig == null) {
                mAcceleratorConfig = new AcceleratorConfig();
                mAcceleratorConfig.setChunkSize(1024 * 64);
                mAcceleratorConfig.setChunkRetryCount(2);
                mAcceleratorConfig.setConnectionTimeout(10 * 1000);
                mAcceleratorConfig.setSoTimeout(30 * 1000);
                mAcceleratorConfig.setLbsConnectionTimeout(10 * 1000);
                mAcceleratorConfig.setLbsSoTimeout(10 * 1000);
                mAcceleratorConfig.setRefreshInterval(2 * 1000);
                mAcceleratorConfig.setMonitorInterval(120 * 1000);
                mAcceleratorConfig.setMonitorThread(true);
                mAcceleratorConfig.setQueryRetryCount(2);
            }
            return true;
        } catch (InvalidChunkSizeException | InvalidParameterException e) {
            KLog.d(TAG, "初始化发布参数失败");
            return false;
        }
    }

    /**
     * 初始化点播配置
     */
    private void initNosUpload() {
        if (mNOSUpload == null) {
            mNOSUpload = NOSUpload.getInstace(mContext);
            NOSUpload.Config config = new NOSUpload.Config();
            config.appKey = "1651e58661c3fe50e5b01abaa270a9f8";
            config.accid = NetIMCache.getAccount();
            config.token = NetIMCache.getToken();
            mNOSUpload.setConfig(config);
        }
    }

    /**
     * 用户验证(回调是子线程)
     */
    private void uploadInit(final File file) {
        mFileName = file.getName();
        mNOSUpload.fileUploadInit(mFileName, mFileName, -1, Config.NETEASE_VIDEO_TEMPLATE, null, "", -1, null, new NOSUploadHandler.UploadInitCallback() {
            @Override
            public void onSuccess(String nosToken, String bucket, String object) {
                doUpload(file, bucket, object, nosToken);
            }

            @Override
            public void onFail(int code, String msg) {
                KLog.d(TAG, "code = " + code + ", msg = " + msg);
                sendUploadError(msg);
            }
        });
    }

    /**
     * 开始发布了(回调是主线程)
     */
    private void doUpload(final File file, final String bucket, final String object, String token) {
        String uploadContext = mNOSUpload.getUploadContext(file);
        try {
            mUploadExecutor = mNOSUpload.putFileByHttp(file, uploadContext, bucket, object, token, new NOSUploadHandler.UploadCallback() {
                @Override
                public void onUploadContextCreate(String oldUploadContext, String newUploadContext) {
                    mNOSUpload.setUploadContext(file, newUploadContext);
                }

                @Override
                public void onProcess(long current, long total) {
                    int progress = (int) ((100.0F * current / total));
                    if (mView != null) {
                        mView.setUploadProgress(progress);
                    }
                }

                @Override
                public void onSuccess(CallRet ret) {
                    String json = ret.getCallbackRetMsg();
                    try {
                        String vid = null;
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("ret");
                        if (jsonObject1 != null) {
                            vid = jsonObject1.optString("vid");
                        }
                        mUploadExecutor = null;
                        mNOSUpload.setUploadContext(file, "");
                        if (!TextUtils.isEmpty(vid)) {
                            mParams.put("video_path", vid);
                            uploadSuccess(mParams);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(CallRet ret) {
                    sendUploadError("发布失败");
                    mUploadExecutor = null;
                }

                @Override
                public void onCanceled(CallRet ret) {
                    try {
                        mUploadExecutor = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mUploadExecutor.join();
        } catch (Exception e) {
            KLog.d(TAG, "发布发生异常");
        }
    }

    private void uploadSuccess(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.addContentShowCvilize(map, getImageList())) {
            @Override
            protected void _onNext(Object o) {
                toast("提交成功");
                mView.finishActivity();
            }
        });
    }

    private void sendUploadError(String msg) {
        Message message = Message.obtain();
        message.what = MSG_UPLOAD_ERROR;
        message.obj = msg;
        mHandler.sendMessage(message);
    }
}
