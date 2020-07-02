package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceConcatEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ServiceConcatAdapter extends CommonAdapter<ServiceConcatEntity> {
    public ServiceConcatAdapter(Context context, List<ServiceConcatEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_service_concat, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ServiceConcatEntity serviceConcatEntity, int position) {
        holder.setText(R.id.tv_name, serviceConcatEntity.getName());
        holder.setText(R.id.tv_phone, "联系方式" + serviceConcatEntity.getPhone());
    }
}
