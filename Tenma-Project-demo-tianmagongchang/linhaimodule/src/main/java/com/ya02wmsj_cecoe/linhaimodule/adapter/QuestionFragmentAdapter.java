package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ya02wmsj_cecoe.linhaimodule.bean.QuestionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.QuestionFragment;

import java.util.List;


/**
 * Created by BenyChan on 2019-06-20.
 */
public class QuestionFragmentAdapter extends FragmentPagerAdapter {
    private List<QuestionEntity> mDataList;

    public QuestionFragmentAdapter(FragmentManager fm, List<QuestionEntity> list) {
        super(fm);
        mDataList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return QuestionFragment.start(mDataList.get(i), i);
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }
}
