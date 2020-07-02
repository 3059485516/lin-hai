package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class ActionSimpleAdapter extends CommonAdapter<AppraiseEntity> {
    public ActionSimpleAdapter(Context context, List<AppraiseEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_advance, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppraiseEntity actionEntity, int position) {
        holder.setText(R.id.tv_top, actionEntity.getForm_name());
        holder.setText(R.id.tv_title, actionEntity.getName());
        holder.setText(R.id.tv_time, actionEntity.getStart_time());
    }
}
