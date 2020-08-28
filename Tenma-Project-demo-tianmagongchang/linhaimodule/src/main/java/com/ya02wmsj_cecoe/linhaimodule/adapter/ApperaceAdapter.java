package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ApperaceScoreActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.HtmlUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


public class ApperaceAdapter extends CommonAdapter<AppraiseEntity> {
    public ApperaceAdapter(Context context, List<AppraiseEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_fragment_apperace, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AppraiseEntity appraiseEntity, int position) {
        holder.setText(R.id.tv_title, appraiseEntity.getName());
        holder.setText(R.id.tv_time, appraiseEntity.getCtime());
        holder.setText(R.id.tv_make, "发布" + appraiseEntity.getForm_name());
        holder.setText(R.id.tv_inner_title, appraiseEntity.getTitle());
        // holder.setText(R.id.tv_inner_conntent, Html.fromHtml(appraiseEntity.getContent()).toString());
        holder.setText(R.id.tv_inner_conntent, HtmlUtil.getTextFromHtml(appraiseEntity.getContent()).toString());
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
