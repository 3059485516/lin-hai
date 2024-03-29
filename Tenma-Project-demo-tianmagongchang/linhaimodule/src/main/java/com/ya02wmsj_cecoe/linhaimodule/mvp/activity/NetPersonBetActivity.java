package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NetPersonBetContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NetPersonBetPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

public class NetPersonBetActivity extends BaseActivity<NetPersonBetContract.Presenter> implements NetPersonBetContract.View {
    protected RecyclerView mRvNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_net_person_bet_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new NetPersonBetPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("网络惠民");
        mRvNode = findViewById(R.id.rv_node);
        mRvNode.setLayoutManager(new GridLayoutManager(this, 3));
        NodeAdapter nodeAdapter = new NodeAdapter(this, Arrays.asList(
                new Node("电子图书馆", R.mipmap.ya02wmsj_cecoe_book + "", true),
                new Node("体育馆地图", R.mipmap.ya02wmsj_cecoe_ditu + "", true),
                new Node("律师咨询", R.mipmap.ya02wmsj_cecoe_lvshi + "", true),
                new Node("发现非遗", R.mipmap.ya02wmsj_cecoe_discover + "", true),
                new Node("免费发药点", R.mipmap.ya02wmsj_cecoe_mffyd + "", true),
                new Node("智慧养老", R.mipmap.ya02wmsj_cecoe_zhyl + "", true)
        ));
        mRvNode.setAdapter(nodeAdapter);
        nodeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position == 0){
                    mPresenter.clickContent("电子图书馆");
                }else if (position == 5){
                    mPresenter.clickContent("智慧养老");
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        findViewById(R.id.iv_zixun).setOnClickListener(v -> {
            mPresenter.clickContent("政务举报网");
            Intent intentZX = new Intent(mContext, WebActivity.class);
            intentZX.putExtra(Constant.KEY_STRING_1, "我要咨询");
            intentZX.putExtra(Constant.KEY_STRING_2, "http://www.zjzwfw.gov.cn/jfaqfront/xiaomi/index.do");
            mContext.startActivity(intentZX);
        });

        findViewById(R.id.iv_shouji).setOnClickListener(v ->{
            startActivity(new Intent(mContext, SocialCollectActivity.class));
        });
    }

    @Override
    protected void initData() {
    }
}
