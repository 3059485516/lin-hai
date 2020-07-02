package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionRecruitEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ActionDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class ActionRecruitAdapter extends CommonAdapter<ActionRecruitEntity> {

    public ActionRecruitAdapter(Context context, List<ActionRecruitEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_action_recruit, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ActionRecruitEntity entity, int position) {
        ImageManager.getInstance().loadImage(mContext,
                Constant.getBaseUrl() + entity.getIcon_path(),
                R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_title, entity.getService_name());
        holder.setText(R.id.tv_address, entity.getAddress());
        holder.setText(R.id.tv_count, "报名人数：" + entity.getCur_num());
        holder.setText(R.id.tv_time, entity.getStart_time());
        holder.setText(R.id.tv_status, entity.getStatus());
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ActionDetailActivity.class);
            intent.putExtra(Constant.KEY_BEAN, entity);
            mContext.startActivity(intent);
        });
    }
}
