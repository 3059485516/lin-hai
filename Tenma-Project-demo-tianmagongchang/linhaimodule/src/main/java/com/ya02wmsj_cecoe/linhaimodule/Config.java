package com.ya02wmsj_cecoe.linhaimodule;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.yl.NetIMCache;
import com.netease.nim.yl.config.preference.UserPreferences;
import com.netease.nim.yl.login.LogoutHelper;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.socks.library.KLog;
import com.tenma.ventures.bean.TMBaseConfig;
import com.tenma.ventures.bean.TMUser;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.tools.TMLoginManager;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtUser;
import com.ya02wmsj_cecoe.linhaimodule.bean.User;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindPhoneActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WebBridgeActivity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxManager;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.AppUtils;

import static com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode.CODE_0009;
import static com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode.CODE_20067;
import static com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode.CODE_40003;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class Config implements TMLoginManager.OnLoginListener {
    private static final String TAG = Config.class.getSimpleName();

    //网易云视频转码模板mp4
    public static final int NETEASE_VIDEO_TEMPLATE = 173371;

    private Context mContext;
    private String token = "";
    private User mUser;
    private RxManager mRxManager2Destroy;
    private TMBaseConfig mTMConfig;
    private TMUser mTmUser;
    private String mVolunteerToken = "";    //志愿汇登录返回的token
    private String mLtToken = "";    //志愿汇登录返回的token
    private static Config instance;
    private boolean isLoginIm = false;

    public synchronized static Config getInstance() {
        if (instance == null) instance = new Config();
        return instance;
    }

    private Config() {
        mContext = App.get().getApplication();
        TMLoginManager.registerLoginChangeListener(this);
        mTmUser = TMSharedPUtil.getTMUser(mContext);
        mTMConfig = TMSharedPUtil.getTMBaseConfig(mContext);
        if (mTmUser!= null&&!TextUtils.isEmpty(mTmUser.getToken())) {
            uploadUserInfo(mTmUser);
        } else {
            gotoLoginAct();
        }
    }

    public void destroy() {
        TMLoginManager.unregisterLoginChangeListener(this);
        if (mRxManager2Destroy != null) {
            mRxManager2Destroy.clear();
            mRxManager2Destroy = null;
        }
        token = "";
        mVolunteerToken = "";
        mLtToken = "";
        mUser = null;
    }

    public boolean isLoginIm() {
        return isLoginIm;
    }

    public String getToken() {
        return token;
    }

    public String getVolunteerToken() {
        if (TextUtils.isEmpty(mVolunteerToken)) {
            loginVolunteer();
        }
        return mVolunteerToken;
    }

    public String getLtToken() {
        return mLtToken;
    }

    public void setLtToken(String token) {
        mLtToken = token;
    }

    public void setVolunteerToken(String token) {
        mVolunteerToken = token;
    }

    public String getRegionCode() {
        if (TextUtils.isEmpty(getUser().getToken())) {
            uploadUserInfo(mTmUser);
            return "";
        }
        if (TextUtils.isEmpty(getUser().getRegion_code())) {
            gotoBindRegionAct();
            return "";
        }
        return getUser().getRegion_code();
    }

    public User getUser() {
        return mUser == null ? new User() : mUser;
    }

    public String getUserGroupName() {
        if (mUser != null) {
            return mUser.getGroup_name();
        }
        return "";
    }

    public TMBaseConfig getTMConfig() {
        return mTMConfig;
    }

    public TMUser getTMUser() {
        return mTmUser;
    }

    public void addRx2Destroy(RxSubscriber rxSubscriber) {
        if (mRxManager2Destroy == null) {
            mRxManager2Destroy = new RxManager();
        }
        mRxManager2Destroy.add(rxSubscriber);
    }

    public void gotoLoginAct() {
        if (isLoginActivityTop()) return;
        Intent intent = new Intent(mContext.getPackageName() + ".usercenter.login");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.e(TAG, mContext.getPackageName());
        mContext.startActivity(intent);
    }

    private void gotoBindRegionAct() {
        Intent intent = new Intent(mContext, BindRegionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private boolean isLoginActivityTop() {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.contains("login") || name.contains("Login");
    }

    @Override
    public void onLogin() {
        mTmUser = TMSharedPUtil.getTMUser(mContext);
        mTMConfig = TMSharedPUtil.getTMBaseConfig(mContext);
        uploadUserInfo(mTmUser);
    }

    public void uploadUserInfo(TMUser tmUser) {
        String user_json = new Gson().toJson(tmUser);
        String imei = "";
        if (!TextUtils.isEmpty(tmUser.getMobile())) {
            imei = AppUtils.getIMEI(mContext, tmUser.getMobile());
        }
        Log.i(TAG, user_json);
        addRx2Destroy(new RxSubscriber<User>(Api.login(user_json, imei)) {      // 上报用户信息
            @Override
            protected void _onNext(User user) {
                Log.i(TAG, user.toString());
                mUser = user;
                token = user.getToken();
                if (TextUtils.isEmpty(user.getRegion_code())) {
                    gotoBindRegionAct();
                }
                loginVolunteerBack();       //登录志愿汇
                uploadUserInfoToLt(tmUser);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if (CODE_20067.getCode().equals(code)) {
                    Intent intent = new Intent(mContext, BindPhoneActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
                onLogout();
            }
        });
    }

    public void uploadUserInfoToLt(TMUser tmUser) {
        String user_json = new Gson().toJson(tmUser);
        String imei = "";
        if (!TextUtils.isEmpty(tmUser.getMobile())) {
            imei = AppUtils.getIMEI(mContext, tmUser.getMobile());
        }
        Log.i(TAG, user_json);
        addRx2Destroy(new RxSubscriber<LtUser>(Api.loginLt(user_json, imei)) {      // 上报用户信息
            @Override
            protected void _onNext(LtUser user) {
                Log.i(TAG, user.toString());
                mLtToken = user.getToken();
                mUser.setGroup_id(user.getGroup_id());
                loginNim(user.getUuid(), user.getToken());
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if (CODE_20067.getCode().equals(code)) {
                    Intent intent = new Intent(mContext, BindPhoneActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    /**
     * 刷新志愿汇token
     */
    public void loginVolunteer() {
        Log.e("Config","loginVolunteer");
        addRx2Destroy(new RxSubscriber<String>(Api.loginByOpenId()) {
            @Override
            protected void _onNext(String string) {
                if (TextUtils.isEmpty(string)) _onError(CODE_40003.getCode());
                mVolunteerToken = string;
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);   //失败则手动登录
                Intent intent = new Intent(mContext, WebBridgeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    public void loginVolunteerBack() {
        addRx2Destroy(new RxSubscriber<String>(Api.loginByOpenId()) {
            @Override
            protected void _onNext(String string) {
                mVolunteerToken = string;
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }

    public void loginNim(final String account, final String token) {
        NimUIKit.login(new LoginInfo(account, token), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                KLog.d(TAG, "登录IM成功");
                NetIMCache.setAccount(account);
                NetIMCache.setToken(token);
                initNotificationConfig();
                isLoginIm = true;
            }

            @Override
            public void onFailed(int i) {
                KLog.d(TAG, "登录IM失败:code =  " + i);
                isLoginIm = false;
            }

            @Override
            public void onException(Throwable throwable) {
                KLog.d(TAG, "登录IM异常: " + throwable.getMessage());
                isLoginIm = false;
            }
        });
    }

    private static void initNotificationConfig() {
        //初始化消息提醒
        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
        //加载状态栏配置
        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
        if (statusBarNotificationConfig == null) {
            statusBarNotificationConfig = NetIMCache.getNotificationConfig();
            UserPreferences.setStatusConfig(statusBarNotificationConfig);
        }
        //初始化免打扰
        NIMClient.updateStatusBarNotificationConfig(UserPreferences.getStatusConfig());
        //更新配置
        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
    }

    @Override
    public void onLogout() {
        LogoutHelper.logout();  //im退出登录
        token = "";
        mVolunteerToken = "";
        mLtToken = "";
        mUser = new User();
        isLoginIm = false;
    }


//    public static class Holder {
//        public static final Config INSTANCE = new Config();
//    }
}
