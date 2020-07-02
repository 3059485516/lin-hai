package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceListEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class ServiceListAdapter extends CommonAdapter<ServiceListEntity> {
    public ServiceListAdapter(Context context, List<ServiceListEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_service_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ServiceListEntity serviceListEntity, int position) {
        holder.setText(R.id.tv_title, serviceListEntity.getName());
        holder.setText(R.id.tv_desc, serviceListEntity.getDesc());
        holder.setText(R.id.tv_time, serviceListEntity.getCtime());
        holder.setText(R.id.tv_type, "类型：" + serviceListEntity.getType());
    }
}
