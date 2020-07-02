package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderHistoryEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-02.
 */
public class OrderHistoryAdapter extends CommonAdapter<OrderHistoryEntity> {
    public OrderHistoryAdapter(Context context, List<OrderHistoryEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_appeal_history, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrderHistoryEntity orderHistoryEntity, int position) {
        holder.setText(R.id.tv_title, orderHistoryEntity.getService_name());
        holder.setText(R.id.tv_region, orderHistoryEntity.getRegion_info().toString());
        holder.setText(R.id.tv_status, orderHistoryEntity.getStatus());
        holder.setText(R.id.tv_time, orderHistoryEntity.getCtime());
    }
}
