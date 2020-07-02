package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppealHistoryEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.EventDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class AppealHistoryAdapter extends CommonAdapter<AppealHistoryEntity> {
    public AppealHistoryAdapter(Context context, List<AppealHistoryEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_appeal_history, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppealHistoryEntity appealHistoryEntity, int position) {
        holder.setText(R.id.tv_title, appealHistoryEntity.getTitle());
        holder.setText(R.id.tv_region, appealHistoryEntity.getRegion_info().toString());
        holder.setText(R.id.tv_status, appealHistoryEntity.getEvent_status());
        holder.setText(R.id.tv_time, appealHistoryEntity.getCtime());
        holder.setText(R.id.tv_reason, appealHistoryEntity.getReject_reason());
        holder.setVisible(R.id.tv_reason, !TextUtils.isEmpty(appealHistoryEntity.getReject_reason()));
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EventDetailActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, appealHistoryEntity.getId());
            mContext.startActivity(intent);
        });
    }
}
