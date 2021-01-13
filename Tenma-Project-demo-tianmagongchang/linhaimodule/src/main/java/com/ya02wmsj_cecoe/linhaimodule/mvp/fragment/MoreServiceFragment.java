package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;

import java.util.Arrays;
import java.util.Collections;

/**
 * 更多服务
 */
public class MoreServiceFragment extends BaseFragment {
    protected RecyclerView mRv1;
    protected RecyclerView mRv2;
    protected RecyclerView mRv3;
    protected RecyclerView mRv4;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_more_service;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        mRv1 = mRootView.findViewById(R.id.rv_1);
        mRv2 = mRootView.findViewById(R.id.rv_2);
        mRv3 = mRootView.findViewById(R.id.rv_3);
        mRv4 = mRootView.findViewById(R.id.rv_4);

        mRv1.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRv2.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRv3.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mRv4.setLayoutManager(new GridLayoutManager(mActivity, 4));
        NodeAdapter adapter1 = new NodeAdapter(mActivity, Arrays.asList(
                new Node("理论宣讲服务", R.mipmap.ya02wmsj_cecoe_lilun + "", true, "1"),
                new Node("教育志愿服务", R.mipmap.ya02wmsj_cecoe_jiaoyu + "", true, "2"),
                new Node("文化服务", R.mipmap.ya02wmsj_cecoe_wenhua + "", true, "3"),
                new Node("科技与科普服务", R.mipmap.ya02wmsj_cecoe_keji + "", true, "4"),
                new Node("修改区域", R.mipmap.ya02wmsj_cecoe_xgqy + "", true, "5")
        ));
        NodeAdapter adapter2 = new NodeAdapter(mActivity, Arrays.asList(
                new Node("家政", R.mipmap.ya02wmsj_cecoe_jiazheng + "", true),
                new Node("开锁", R.mipmap.ya02wmsj_cecoe_kecheng + "", true),
                new Node("管道疏通", R.mipmap.ya02wmsj_cecoe_chan + "", true),
                new Node("生活缴费", R.mipmap.ya02wmsj_cecoe_jiaofei + "", true),
                new Node("找厕所", R.mipmap.ya02wmsj_cecoe_cesuo + "", true),
                new Node("智慧养老", R.mipmap.ya02wmsj_cecoe_zhyl + "", true)
        ));
        NodeAdapter adapter3 = new NodeAdapter(mActivity, Collections.singletonList(
                new Node("我有微心愿", R.mipmap.ya02wmsj_cecoe_xinyuan + "", true)
        ));
        NodeAdapter adapter4 = new NodeAdapter(mActivity, Arrays.asList(
                new Node("我要咨询", R.mipmap.ya02wmsj_cecoe_zixun + "", true),
                new Node("律师咨询", R.mipmap.ya02wmsj_cecoe_lvshi + "", true),
                new Node("农残检测", R.mipmap.ya02wmsj_cecoe_nongyao + "", true),
                new Node("发现非遗", R.mipmap.ya02wmsj_cecoe_discover + "", true),
                new Node("电子图书馆", R.mipmap.ya02wmsj_cecoe_book + "", true),
                new Node("体育馆地图", R.mipmap.ya02wmsj_cecoe_ditu + "", true),
                new Node("免费发药点", R.mipmap.ya02wmsj_cecoe_mffyd + "", true)
        ));
        mRv1.setAdapter(adapter1);
        mRv2.setAdapter(adapter2);
        mRv3.setAdapter(adapter3);
        mRv4.setAdapter(adapter4);
    }

    @Override
    protected void initData() {
    }
}
