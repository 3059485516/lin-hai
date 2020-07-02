package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.OrganizationSubFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationSubActivity extends BaseViewPagerActivity {
    private List<OrganizationSubEntity> mSubList_1 = new ArrayList<>();
    private List<OrganizationSubEntity> mSubList_2 = new ArrayList<>();

    @Override
    public String[] getTitles() {
        return new String[]{"下属队伍", "下属礼堂"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{OrganizationSubFragment.start(mSubList_1), OrganizationSubFragment.start(mSubList_2)};
    }

    @Override
    protected void initMVP() {
        OrganizationDetailEntity entity = (OrganizationDetailEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mSubList_1.addAll(entity.getOrgan_practice());
        mSubList_2.addAll(entity.getCultural_auditorium());
    }

    @Override
    protected void initView() {

    }
}
