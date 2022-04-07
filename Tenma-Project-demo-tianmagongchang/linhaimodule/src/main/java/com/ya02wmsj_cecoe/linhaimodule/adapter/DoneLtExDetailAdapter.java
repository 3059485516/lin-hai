package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.DoneLtExDetail;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 礼堂指数详情展示
 */
public class DoneLtExDetailAdapter extends CommonAdapter<DoneLtExDetail> {

    public DoneLtExDetailAdapter(Context context, List<DoneLtExDetail> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_done_lt_detail, datas);
    }

    @Override
    protected void convert(ViewHolder holder, DoneLtExDetail doneLtExDetail, int position) {
        holder.setText(R.id.tv_content, doneLtExDetail.getTitle());
        holder.setText(R.id.tv_time, "活动时间：" + doneLtExDetail.getCtime());
        float j = ((float) doneLtExDetail.getFinal_audit_point()) / 10;

        holder.setText(R.id.tv_time, "活动时间：" + doneLtExDetail.getCtime() + "       积分：" + j);
    }
}
