package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveRecordEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LiveRecordListAdapter extends CommonAdapter<LiveRecordEntity> {
    public LiveRecordListAdapter(Context context, List<LiveRecordEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_live_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LiveRecordEntity entity, int position) {
        if (TextUtils.isEmpty(entity.getSnapshotUrl())) {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_pic));
        } else {
            ImageManager.getInstance().loadImage(mContext, entity.getSnapshotUrl(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_pic));
        }
        holder.setText(R.id.tv_title, entity.getVideoName());
        holder.setText(R.id.tv_name, entity.getUser_name());
        holder.setText(R.id.tv_status, "录播");
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LiveActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, entity.getVideoName());
                intent.putExtra(Constant.KEY_STRING_2, entity.getOrigUrl());
                mContext.startActivity(intent);
            }
        });
    }
}
