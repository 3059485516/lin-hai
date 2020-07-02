package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tenma.ventures.tools.change_activity.TablayoutChange;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AppraiseAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.FragmentWithTitleAdapter;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.AppraiseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BaseAreaFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * Created by BenyChan on 2019-07-15.
 */
public class FragmentFour extends BaseAreaFragment {
    protected ToolbarLayout mToolbar;

    protected RecyclerView mRvNode;

    protected TabLayout mTabLayout;

    protected ViewPager mViewPager;

    private AppraiseAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_four;
    }


    @Override
    protected void initMVP() {
        mPresenter = new BaseAreaFragmentPresenter(this);
        mPresenter.getOnlineActivityList();
    }

    @Override
    protected void initView() {
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mRvNode = mRootView.findViewById(R.id.rv);
        mTabLayout = mRootView.findViewById(R.id.tab);
        mViewPager = mRootView.findViewById(R.id.vp);

        TextView tv_menu = mToolbar.getMenuTextView();
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.mipmap.ya02wmsj_cecoe_arrow_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_menu.setCompoundDrawables(null, null, drawable, null);
        tv_menu.setCompoundDrawablePadding(DisplayUtils.dip2px(mActivity, 5));
        mToolbar.setMenuText(RegionManager.getInstance().getCurrentTownName());
        mToolbar.showBack();
        mToolbar.setOnClickListener(v -> {
                    if (getActivity() instanceof TablayoutChange) {     //显示底部Tab栏
                        ((TablayoutChange) getActivity()).showTablayout();
                    }
                    Flowable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
                        mActivity.runOnUiThread(() -> {
                            if (getActivity() instanceof TablayoutChange) {     //隐藏底部Tab栏
                                ((TablayoutChange) getActivity()).hideTablayout();
                            }
                        });
                    });
                }
                , v -> {
                    // 选择区域
                    showRegionPickerDialog(mToolbar.getMenuTextView());
                });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new FragmentWithTitleAdapter(getChildFragmentManager(),
                Arrays.asList("评议列表", "评议结果", "议一议", "网络评议"),
                Arrays.asList(AppraiseFragment.start("going"), AppraiseFragment.start("end"), new AppraiseListFragment(), new TestFragment())));

        mRvNode.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new AppraiseAdapter(mActivity, mPresenter.getOnlineList());
        mRvNode.setAdapter(mAdapter);
        mRootView.findViewById(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(AppraiseListActivity.class);
            }
        });
    }

    @Override
    protected void updateRegion() {
        mToolbar.setMenuText(RegionManager.getInstance().getCurrentTownName());
    }

    @Override
    public void updateOnlineList() {
        mAdapter.notifyDataSetChanged();
    }
}
