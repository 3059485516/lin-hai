package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtExponentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.event.ExchangeResult;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtExponentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtExponentListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtExponentListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 礼堂指数
 */
public class LtExponentFragment extends BaseListFragment<LtExponentListContract.Presenter>
        implements LtExponentListContract.View {

    public static LtExponentFragment create(String code) {
        LtExponentFragment fragment = new LtExponentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_new_base_list;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        LtExponentAdapter ltExponentAdapter = new LtExponentAdapter(mActivity, mPresenter.getDataList());
        ltExponentAdapter.setScrollCall(position -> {
            mRecyclerView.getLayoutManager().scrollToPosition(position);
        });
        return ltExponentAdapter;
    }

    @Override
    protected void initMVP() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mPresenter = new LtExponentListPresenter(this, getArguments().getString(Constant.KEY_STRING_1));
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageEvent(ExchangeResult eventMain) {
        //更新数据
        if (eventMain != null) {
            mRefreshLayout.autoRefresh();
            mIsRefresh = false;
        }
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }

    @Override
    public String getSelectDate(){
        if (getActivity() instanceof LtExponentActivity){
            return ((LtExponentActivity)getActivity()).getSelectDate();
        }else {
            return "";
        }
    }
}
