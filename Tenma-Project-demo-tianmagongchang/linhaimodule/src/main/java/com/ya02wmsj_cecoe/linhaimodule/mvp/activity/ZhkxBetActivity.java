package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.text.TextUtils;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZhkxContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZhkxPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.MD5EncodeUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;

public class ZhkxBetActivity extends BaseActivity<ZhkxPresenter> implements ZhkxContract.View {
    private String mUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_zhkx_1;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ZhkxPresenter(this);
        mPresenter.logToEBook();
    }

    @Override
    protected void initView() {
        setTitle("智慧科普");
        findViewById(R.id.iv_qk).setOnClickListener(v -> {
            mPresenter.clickContent();
            Intent intentQK = new Intent(mContext, WebActivity.class);
            intentQK.putExtra(Constant.KEY_STRING_1, "期刊");
            intentQK.putExtra(Constant.KEY_STRING_2, "http://qk.chaoxing.com/mobile/index");
            mContext.startActivity(intentQK);
        });

        findViewById(R.id.iv_sc).setOnClickListener(v -> {
            if (TextUtils.isEmpty(mUrl)) {
                T.showShort(mContext, "未获取到书城相关信息");
                mPresenter.logToEBook();
                return;
            }
            mPresenter.clickContent();
            Intent intentSC = new Intent(mContext, WebActivity.class);
            intentSC.putExtra(Constant.KEY_STRING_1, "书城");
            intentSC.putExtra(Constant.KEY_STRING_2, mUrl);
            mContext.startActivity(intentSC);
        });

        findViewById(R.id.iv_zjk).setOnClickListener(v -> {
            mPresenter.clickContent();
            mContext.startActivity(new Intent(mContext, ZjkActivity.class));
        });

        findViewById(R.id.iv_gkk).setOnClickListener(v -> {
            mPresenter.clickContent();
            String phone = Config.getInstance().getUser().getPhone();
            String time = System.currentTimeMillis() + "";
            String enc = "40871" + phone + "webApp@#$%" + time;
            Intent intentGKK = new Intent(mContext, WebActivity.class);
            intentGKK.putExtra(Constant.KEY_STRING_1, "公开课");
            intentGKK.putExtra(Constant.KEY_STRING_2, "http://mc.m.5read.com/other/webapp4OpenClass_webApp4OpenClass_recommend.jspx?schoolid=40871"
                    + "&uid=" + phone
                    + "&_time=" + time
                    + "&enc=" + MD5EncodeUtil.md5(enc));
            mContext.startActivity(intentGKK);
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void loginChaoxSuc(String url) {
        mUrl = url;
    }
}
