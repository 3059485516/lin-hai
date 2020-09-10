package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommonDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * 微心愿 认领页面
 */
public class WishDetailDialog extends CommonDialog {
    private Context context;
    private YLEditTextGroup mApplyName;
    private YLEditTextGroup mApplyPhone;
    private YLEditTextGroup mApplyAddress;
    private Button mBtnApply;
    private Button mBtnCancel;

    @SuppressLint("InflateParams")
    private WishDetailDialog(Context context, int defStyle) {
        super(context, defStyle);
        this.context = context;
        View view = getLayoutInflater().inflate(R.layout.ya02wmsj_cecoe_dialog_wish_detail, null);
        mApplyName = view.findViewById(R.id.tv_apply_name);
        mApplyPhone = view.findViewById(R.id.tv_apply_phone);
        mApplyAddress = view.findViewById(R.id.tv_apply_addr);
        mBtnApply = view.findViewById(R.id.btn_submit);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        initViewListener();
        setContent(view, 0);
    }

    public WishDetailDialog(Context context) {
        this(context, R.style.dialog_bottom);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    //初始化 控件的事件
    private void initViewListener() {
        mBtnApply.setOnClickListener(v -> dataSubmit());
        mBtnCancel.setOnClickListener(v -> dismiss());
    }

    private void dataSubmit() {
        // 认领
        if (TextUtils.isEmpty(mApplyName.getTextRight())) {
            toast("请填写申请人姓名");
            return;
        }
        if (TextUtils.isEmpty(mApplyPhone.getTextRight())) {
            toast("请填写申请人电话");
            return;
        }
        if (TextUtils.isEmpty(mApplyAddress.getTextRight())) {
            toast("请填写申请人地址");
            return;
        }
        if (TextUtils.isEmpty(Config.getInstance().getRegionCode())) {
            toast("请先绑定区域");
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("region_code", Config.getInstance().getRegionCode());
        params.put("name", mApplyName.getTextRight());
        params.put("phone", mApplyPhone.getTextRight());
        params.put("address", mApplyAddress.getTextRight());
        wishDetailCall.onWishDetailSubmit(params);
    }

    public void toast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        T.showShort(context, msg);
    }

    private WishDetailCall wishDetailCall;

    public void setWishDetailCall(WishDetailCall wishDetailCall) {
        this.wishDetailCall = wishDetailCall;
    }

    public interface WishDetailCall{
        public void onWishDetailSubmit(Map<String,Object> params);
    }
}
