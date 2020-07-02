package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class AdvanceTopAdapter extends CommonAdapter<NodeContent> {
    public AdvanceTopAdapter(Context context, List<NodeContent> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_advance, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NodeContent nodeContent, int position) {
        holder.setText(R.id.tv_title, nodeContent.getTitle());
        holder.setText(R.id.tv_time, nodeContent.getOperate_time());
    }
}
