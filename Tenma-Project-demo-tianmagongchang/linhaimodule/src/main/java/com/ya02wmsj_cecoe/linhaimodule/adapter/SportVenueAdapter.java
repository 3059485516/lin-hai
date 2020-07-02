package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.VenueDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class SportVenueAdapter extends CommonAdapter<VenueEntity> {
    public SportVenueAdapter(Context context, List<VenueEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_sport_venue, datas);
    }

    @Override
    protected void convert(ViewHolder holder, VenueEntity venue, int position) {
        holder.setText(R.id.tv_name, venue.getName());
        holder.setText(R.id.tv_address, venue.getAddress());
        holder.setText(R.id.tv_phone, "电话：" + venue.getPhone());
        holder.getConvertView().setOnClickListener(v -> {
            // 获取详情
            Intent intent = new Intent(mContext, VenueDetailActivity.class);
            intent.putExtra(Constant.KEY_BEAN, venue);
            mContext.startActivity(intent);
        });
    }
}
