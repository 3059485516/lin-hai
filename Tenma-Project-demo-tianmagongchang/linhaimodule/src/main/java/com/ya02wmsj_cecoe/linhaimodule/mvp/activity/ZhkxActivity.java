package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZhkxContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.KjcgFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.KjxqFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZhkxPresenter;

import java.util.Arrays;

public class ZhkxActivity extends BaseViewPagerActivity<ZhkxPresenter> implements ZhkxContract.View {
    protected RecyclerView mRvNode;
    private Node mEbookNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_zhkx_activity;
    }

    @Override
    public String[] getTitles() {
        return new String[]{"科技成果", "科技需求"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{new KjcgFragment(), new KjxqFragment()};
    }

    @Override
    protected void initMVP() {
        mPresenter = new ZhkxPresenter(this);
        mPresenter.logToEBook();
    }

    @Override
    protected void initView() {
        setTitle("智慧科普");
        mRvNode = findViewById(R.id.rv_node);
        mRvNode.setLayoutManager(new GridLayoutManager(this, 4));
        mEbookNode = new Node("书城", R.mipmap.ya02wmsj_cecoe_sc + "", true);
        NodeAdapter mNodeAdapter = new NodeAdapter(this, Arrays.asList(
                new Node("期刊", R.mipmap.ya02wmsj_cecoe_qk + "", true),
                mEbookNode,
                new Node("专家库", R.mipmap.ya02wmsj_cecoe_zjk + "", true),
                new Node("公开课", R.mipmap.ya02wmsj_cecoe_gkk + "", true)
        ));
        mRvNode.setAdapter(mNodeAdapter);
    }

    @Override
    public void loginChaoxSuc(String url) {
        mEbookNode.setExtra(url);
    }
}
