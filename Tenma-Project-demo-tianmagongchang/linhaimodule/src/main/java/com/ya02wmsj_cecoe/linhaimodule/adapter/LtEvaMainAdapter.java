package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaMainEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

public class LtEvaMainAdapter extends CommonAdapter<LtEvaMainEntity> {
    public LtEvaMainAdapter(Context context, List<LtEvaMainEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_eva_activity, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtEvaMainEntity ltEvaMainEntity, int position) {
        holder.setText(R.id.tv_title, ltEvaMainEntity.getTitle());
        holder.setText(R.id.tv_points, ltEvaMainEntity.getFinal_audit_point() + "分");
        holder.setText(R.id.tv_status, ltEvaMainEntity.getStatus());
        holder.setText(R.id.tv_time, ltEvaMainEntity.getReport_time());
        if (ltEvaMainEntity.getStatus().contains("驳回")) {
            holder.setBackgroundColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.yl_theme_color));
            holder.setVisible(R.id.tv_refuse, true);
            holder.setText(R.id.tv_refuse, "驳回原因：" + ltEvaMainEntity.getReject_reason());
        } else if (ltEvaMainEntity.getStatus().contains("通过")) {
            holder.setVisible(R.id.tv_refuse, false);
            holder.setBackgroundColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.yl_main_green));
        } else {
            holder.setBackgroundColor(R.id.tv_status, Color.BLUE);
            holder.setVisible(R.id.tv_refuse, false);
        }
        LinearLayout wrap_img = holder.getView(R.id.wrap_img);
        if (ltEvaMainEntity.getPic() != null && ltEvaMainEntity.getPic().size() > 0) {
            for (int i = 0; i < ltEvaMainEntity.getPic().size(); i++) {
                if (i >= 3) break;
                ImageManager.getInstance().loadImage(mContext, ltEvaMainEntity.getPic().get(i), (RatioImageView) wrap_img.getChildAt(i));
            }
            wrap_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.gotoPreviewImageActivity(mContext, ltEvaMainEntity.getPic(), null, 0);
                }
            });
        }
        ExpandableTextView textView = holder.getView(R.id.tv_content);
        textView.updateForRecyclerView(ltEvaMainEntity.getContent(), textView.getWidth(), 0);       //1-显示“收起”，0-显示“展开”
    }
}
