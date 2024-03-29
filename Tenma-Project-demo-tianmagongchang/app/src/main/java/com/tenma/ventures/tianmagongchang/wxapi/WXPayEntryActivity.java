package com.tenma.ventures.tianmagongchang.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tenma.ventures.bean.utils.TMPayUtil;
import com.tenma.ventures.bean.utils.TMSharedPUtil;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, getId());
        api.handleIntent(getIntent(), this);
    }

    private String getId() {
        try {
            String baseConfig = TMSharedPUtil.getTMBaseConfigString(this);
            Gson gson = new Gson();
            JsonObject baseConfigJson = gson.fromJson(baseConfig, JsonObject.class);
            JsonObject thirdPlatformJson = gson.fromJson(baseConfigJson.get("thirdPlatform"), JsonObject.class);
            JsonObject androidJson = gson.fromJson(thirdPlatformJson.get("Android"), JsonObject.class);
            JsonObject weChatJson = gson.fromJson(androidJson.get("Wechat"), JsonObject.class);
            return weChatJson.get("AppId").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "xxxxxxxxxx";
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            TMPayUtil.wechatPayResult(code);
            finish();
        }
    }
}