package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import io.reactivex.functions.Consumer;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class NodeContentFragment extends BaseListFragment<NodeContentContract.Presenter> implements NodeContentContract.View {

    public static NodeContentFragment start(String region_code, String node_id) {
        NodeContentFragment fragment = new NodeContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, region_code);
        bundle.putString(Constant.KEY_STRING_2, node_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        Bundle bundle = getArguments();
        mPresenter = new NodeContentPresenter(this, bundle.getString(Constant.KEY_STRING_2), bundle.getString(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UserOperateEvent.class).subscribe(new Consumer<UserOperateEvent>() {
            @Override
            public void accept(UserOperateEvent userOperateEvent) throws Exception {
                for (NodeContent content : mPresenter.getDataList()) {
                    if (content.getId().equals(userOperateEvent.getId())) {
                        content.setThumb_num(userOperateEvent.getLikeNum());
                        break;
                    }
                }
                updateList();
            }
        }));
    }
}
