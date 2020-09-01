package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ApperaceScoreActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 活动首页 适配器
 */
public class ApperaceMainViewAdapter extends CommonAdapter<AppraiseEntity> {

    public ApperaceMainViewAdapter(Context context, List<AppraiseEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_main_apperace, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppraiseEntity appraiseEntity, int position) {
        holder.setText(R.id.tv_title, appraiseEntity.getTitle());
        holder.setText(R.id.tv_number, appraiseEntity.getParticipate_total() + "人参与");
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
