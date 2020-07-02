package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyPublishEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.HtmlUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

public class LtMyPublishAdapter extends CommonAdapter<LtMyPublishEntity> {
    public LtMyPublishAdapter(Context context, List<LtMyPublishEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_lt_my_publish, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtMyPublishEntity o, int position) {
        holder.setText(R.id.tv_title, o.getTitle());
        holder.setText(R.id.tv_time, o.getOperate_time());
        if (!TextUtils.isEmpty(o.getReview_msg())) {
            holder.setVisible(R.id.tv_refuse, true);
            holder.setText(R.id.tv_refuse, "理由：" + o.getReview_msg());
        } else {
            holder.setVisible(R.id.tv_refuse, false);
        }
        LinearLayout wrap_img = holder.getView(R.id.wrap_img);
        if (o.getImg() != null && o.getImg().size() > 0) {
            holder.setVisible(R.id.wrap_img, true);
            for (int i = 0; i < o.getImg().size(); i++) {
                if (i > 2) break;
                RatioImageView imageView = (RatioImageView) wrap_img.getChildAt(i);
                ImageManager.getInstance().loadImage(mContext, o.getImg().get(i), imageView);
            }
        } else {
            holder.setVisible(R.id.wrap_img, false);
        }
        wrap_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.gotoPreviewImageActivity(mContext, o.getImg(), null, 0);
            }
        });
        holder.setText(R.id.tv_status, o.getStatus());
        if (o.getStatus().contains("审核")) {
            holder.setBackgroundColor(R.id.tv_status, Color.BLUE);
        } else if (o.getStatus().contains("驳回")) {
            holder.setBackgroundColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.theme_color));
        } else if (o.getStatus().contains("通过")) {
            holder.setBackgroundColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.yl_main_green));
        } else {
            holder.setBackgroundColor(R.id.tv_status, ContextCompat.getColor(mContext, R.color.gray));
        }
        ExpandableTextView textView = holder.getView(R.id.tv_content);
        textView.updateForRecyclerView(HtmlUtil.getTextFromHtml(o.getContents()), 0);
    }
}
