package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tenma.ventures.tools.change_activity.TablayoutChange;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentTwoContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.FragmentTwoPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * Created by BenyChan on 2019-07-15.
 */
public class FragmentTwo extends BaseListFragment<FragmentTwoContract.Presenter> implements FragmentTwoContract.View {
    protected ToolbarLayout mToolBar;

    protected RecyclerView mRvNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_two;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new FragmentTwoPresenter(this);
    }

    @Override
    protected void initView() {
        mToolBar = mRootView.findViewById(R.id.toolbar);
        mRvNode = mRootView.findViewById(R.id.rv_node);
        mToolBar.showBack();
        mToolBar.setOnClickListener(v -> {
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
        }, null);
        setDefaultItemDecoration();
        mRvNode.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        NodeAdapter adapter = new NodeAdapter(mActivity, Arrays.asList(
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_1, "理论宣传", PLATFORM_THEORY, TheoryActivity.class),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_2, "教育服务", PLATFORM_EDUC, NodeContentActivity.class),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_3, "文化服务", PLATFORM_CULTURE, NodeContentActivity.class),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_4, "科技科普", PLATFORM_TECHNOLOGY, NodeContentActivity.class),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_5, "健身体育", PLATFORM_SPORT, SportActivity.class),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_platform_6, "网络惠民", PLATFORM_NET, NodeContentActivity.class)));
//        mRvNode.setAdapter(adapter);
    }
}
