package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.adapter.TestFragmentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;


/**
 * Created by BenyChan on 2019-07-09.
 */
public class TestFragment extends BaseListFragment {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        TestFragmentAdapter adapter = new TestFragmentAdapter(mActivity, Arrays.asList("登录", "会员中心", "个人资料", "绑定手机",
                "注册", "我的评议", "网络社区", "文明资讯", "六大平台", "组织结构", "新时代", "新征程",
                "实践中心", "新闻资讯", "评议预告", "评议列表", "评议结构", "投票选举"));
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (adapter.getDatas().get(position).contains("登录")) {
                    Intent intent = new Intent(mActivity.getPackageName() + ".usercenter.login");
                    Log.e("PackageName : ", mActivity.getPackageName());
                    startActivity(intent);
                } else if (adapter.getDatas().get(position).contains("会员")) {
                    Intent intent = new Intent(mActivity.getPackageName() + ".usercenter.UCMain");
                    Log.e("PackageName : ", mActivity.getPackageName());
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return adapter;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {

    }
}
