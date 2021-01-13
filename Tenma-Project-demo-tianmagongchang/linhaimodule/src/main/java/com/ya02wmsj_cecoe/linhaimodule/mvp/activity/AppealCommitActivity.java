package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.CategoryTypeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppealCommitContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppealCommitPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class AppealCommitActivity extends BaseActivity<AppealCommitContract.Presenter> implements AppealCommitContract.View {
    protected YLEditTextGroup mEtTitle;
    protected YLTextViewGroup mTvType;
    protected EditText mEtDesc;

    private static final int CODE_REQUEST_CATEGORY = 110;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_commit_appeal;
    }

    @Override
    protected void initMVP() {
        mPresenter = new AppealCommitPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("诉求");
        setMenuText("诉求历史");
        mEtTitle = findViewById(R.id.et_title);
        mTvType = findViewById(R.id.tv_type);
        mEtDesc = findViewById(R.id.et_desc);
        mTvType.setOnClickListener(v -> startActivityForResult(new Intent(mContext, CategorySelectActivity.class), CODE_REQUEST_CATEGORY));
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  提交诉求
                if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                    toast("请填写标题");
                    return;
                }
                if (null == mTvType.getTag()) {
                    toast("请选择诉求类型");
                    return;
                }
                if (TextUtils.isEmpty(mEtDesc.getText())) {
                    toast("请填写描述");
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put("region_code", Config.getInstance().getRegionCode());
                map.put("title", mTvType.getTextRight());
                map.put("category_id", mTvType.getTag());
                map.put("desc", mEtDesc.getText().toString());
                mPresenter.addEvent(map);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        //  查看历史
        gotoActivity(AppealHistoryActivity.class);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode || null == data) {
            return;
        }
        if (requestCode == CODE_REQUEST_CATEGORY) {
            CategoryTypeEntity entity = (CategoryTypeEntity) data.getSerializableExtra(Constant.KEY_BEAN);
            mTvType.setTextRight(entity.getName());
            mTvType.setTag(entity.getId());
        }
    }
}
