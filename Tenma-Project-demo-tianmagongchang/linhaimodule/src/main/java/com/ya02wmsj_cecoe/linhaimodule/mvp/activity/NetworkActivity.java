package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NetworkAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.MainNodeEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NetworkEntity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

/**
 * Created by BenyChan on 2019-07-17.
 */
public class NetworkActivity extends BaseListActivity {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NetworkAdapter(this, Arrays.asList(
                new NetworkEntity("常用工具", Arrays.asList(
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_1, "天气查询", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_2, "读报", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_3, "情报站", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_4, "违章查询", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_5, "快递查询", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_6, "数字电视缴费", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_7, "居住证办理", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_1_8, "导诊服务", 0, null))),
                new NetworkEntity("政务办理", Arrays.asList(
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_2_1, "临海门户网站", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_2_2, "临海第一人民医院", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_2_3, "临海公安", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_2_4, "临海旅游局", 0, null)
                )),
                new NetworkEntity("生活服务", Arrays.asList(
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_3_1, "看电影", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_3_2, "查火车", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_3_3, "找酒店", 0, null),
                        new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_net_3_4, "坐飞机", 0, null)
                ))
        ));
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
    }
}
