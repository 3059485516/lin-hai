package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class NetPersonAdapter extends CommonAdapter<NodeContent> {
    public NetPersonAdapter(Context context, List<NodeContent> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_net_person, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NodeContent nodeContent, int position) {
        holder.setVisible(R.id.tv_top, position == 0);
        holder.setVisible(R.id.view_divider, position == 0);
        holder.setText(R.id.tv_content, nodeContent.getTitle());
        holder.setText(R.id.tv_source, nodeContent.getName());
        holder.setText(R.id.tv_time, nodeContent.getOperate_time());
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TextContentActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
            intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
            intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
            mContext.startActivity(intent);
        });
    }
}
