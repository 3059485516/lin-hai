package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtAppointEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtMyAppointAdapter extends CommonAdapter<LtAppointEntity> {
    public LtMyAppointAdapter(Context context, List<LtAppointEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_item_my_appoint, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtAppointEntity entity, int position) {
        holder.setText(R.id.tv_name, entity.getCa_name());
        String stime = "";
        String etime = "";
        if (!TextUtils.isEmpty(entity.getS_time()) && entity.getS_time().length() > 8) {
            stime = entity.getS_time().substring(5, entity.getS_time().length() - 3);
        }
        if (!TextUtils.isEmpty(entity.getE_time()) && entity.getE_time().length() > 8) {
            etime = entity.getE_time().substring(5, entity.getE_time().length() - 3);
        }
        holder.setText(R.id.tv_time, "使用时间：" + stime + "~" + etime);
        holder.setText(R.id.tv_apply_reason, "申请理由：" + entity.getReason());
        holder.setVisible(R.id.wrap_refuse, !TextUtils.isEmpty(entity.getReview_msg()));
        holder.setText(R.id.tv_refuse_reason, "驳回原因: " + entity.getReview_msg());
        holder.setText(R.id.tv_status, entity.getStatus());
        if (entity.getStatus().contains("审核")) {
            holder.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.blue));
        } else if ("驳回".equals(entity.getStatus())) {
            holder.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.yl_theme_color));
        } else if ("审核通过".equals(entity.getStatus())) {
            holder.setTextColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.yl_main_green));
        }
    }
}
