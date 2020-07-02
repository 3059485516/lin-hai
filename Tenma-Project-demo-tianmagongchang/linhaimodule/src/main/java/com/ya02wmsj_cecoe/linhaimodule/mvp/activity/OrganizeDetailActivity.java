package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ServiceListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizeDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrganizeDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;


/**
 * Created by BenyChan on 2019-07-25.
 */
public class OrganizeDetailActivity extends BaseActivity<OrganizeDetailContract.Presenter> implements OrganizeDetailContract.View {
    protected YLTextViewGroup mTvName;

    protected YLTextViewGroup mTvTel;

    protected YLTextViewGroup mTvUnit;

    protected YLTextViewGroup mTvPeople;

    protected TextView mTvAddress;

    protected TextView mTvIntro;

    protected RecyclerView mRvService;

    protected EditText mEtIntro;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_organize_detail;
    }

    @Override
    protected void initMVP() {
        OrganizeListEntity entity = (OrganizeListEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new OrganizeDetailPresenter(this, entity.getId());
    }

    @Override
    protected void initView() {
        setTitle("志愿者队伍详情");
        mTvName = findViewById(R.id.tv_name);
        mTvTel = findViewById(R.id.tv_tel);
        mTvUnit = findViewById(R.id.tv_unit);
        mTvPeople = findViewById(R.id.tv_people);
        mTvAddress = findViewById(R.id.tv_address);
        mTvIntro = findViewById(R.id.tv_intro);
        mRvService = findViewById(R.id.rv_service);
        mEtIntro = findViewById(R.id.et_intro);

        mRvService.setLayoutManager(new LinearLayoutManager(this));
        mRvService.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, 0, 0));

        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"志愿者".equals(Config.getInstance().getUserGroupName())) {
                    toast("请先申请成为志愿者");
                    gotoActivity(VolunteerActivity.class);
                    return;
                }
                //  提交申请
                if (mEtIntro.getVisibility() != View.VISIBLE) {
                    mEtIntro.setVisibility(View.VISIBLE);
                    toast("请先填写个人简介");
                    return;
                }
                if (TextUtils.isEmpty(mEtIntro.getText())) {
                    toast("请先填写个人简介");
                    return;
                }
                mPresenter.applyJoinVolunteerOrgan(mEtIntro.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getVolunteerOrganDetail();
    }

    @Override
    public void update() {
        OrganizeDetailEntity entity = mPresenter.getOrganizeDetailEntity();
        mTvName.setTextRight(entity.getName());
        mTvTel.setTextRight(entity.getPhone());
        mTvUnit.setTextRight(entity.getUnit());
        mTvPeople.setTextRight(entity.getCharge_name());
        mTvAddress.setText(entity.getAddress());
        mTvIntro.setText(entity.getDesc());
        if (entity.getService_list() != null) {
            ServiceListAdapter adapter = new ServiceListAdapter(this, entity.getService_list());
            mRvService.setAdapter(adapter);
        }
    }
}
