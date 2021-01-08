package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ApperaceScoreActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 评一评
 */
public class ApperaceAdapter extends CommonAdapter<AppraiseEntity> {

    public ApperaceAdapter(Context context, List<AppraiseEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_fragment_apperace, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppraiseEntity appraiseEntity, int position) {
        holder.setText(R.id.tv_name, appraiseEntity.getName());
        holder.setText(R.id.tv_time, appraiseEntity.getCtime());
        holder.setText(R.id.tv_title, appraiseEntity.getTitle());
        holder.setText(R.id.tv_number, appraiseEntity.getParticipate_total() + "人参与");
        holder.setText(R.id.tv_endTime, appraiseEntity.getEnd_time() + "截止");

        ConstraintLayout cl_thumbnail = holder.getView(R.id.cl_thumbnail);

        String icon_path = appraiseEntity.getIcon_path();
        if (!TextUtils.isEmpty(icon_path)) {
            cl_thumbnail.setVisibility(View.VISIBLE);
            ImageManager.getInstance().loadImage(mContext, icon_path, holder.getView(R.id.iv_thumbnail));
        } else {
            cl_thumbnail.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(appraiseEntity.getPic_url())) {
            ImageManager.getInstance().loadCircleImage(mContext, appraiseEntity.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        }
        holder.getConvertView().setOnClickListener(v -> {
            //  详情页面
            if ("评分".equals(appraiseEntity.getForm_name())) {
                Intent intent = new Intent(mContext, ApperaceScoreActivity.class);
                intent.putExtra(Constant.KEY_BEAN, appraiseEntity);
                mContext.startActivity(intent);
            } else {
                JumpUtils.gotoActionDetailActivity(mContext, appraiseEntity);
            }
        });
    }
}
