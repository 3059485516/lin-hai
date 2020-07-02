package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-29.
 */
public class AppraiseAdapter extends CommonAdapter<AppraiseEntity> {
    public AppraiseAdapter(Context context, List<AppraiseEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_advance, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppraiseEntity appraiseOption, int position) {
        holder.setVisible(R.id.tv_top, "true".equals(appraiseOption.getParticipate()));
        holder.setText(R.id.tv_title, appraiseOption.getName());
        holder.setText(R.id.tv_time, appraiseOption.getStart_time() + " " + appraiseOption.getStatus());
        holder.getConvertView().setOnClickListener(v -> {
            //  详情页面
            JumpUtils.gotoActionDetailActivity(mContext, appraiseOption);
        });
    }
}
