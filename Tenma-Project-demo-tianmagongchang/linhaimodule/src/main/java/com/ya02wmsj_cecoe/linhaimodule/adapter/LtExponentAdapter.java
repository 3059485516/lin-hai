package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtExpoenentEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 礼堂指数
 */
public class LtExponentAdapter extends CommonAdapter<LtExpoenentEntity> {
    public interface ScrollCall{
        void scrollCallPosition(int position);
    }

    public ScrollCall scrollCall;

    public void setScrollCall(ScrollCall scrollCall) {
        this.scrollCall = scrollCall;
    }

    public LtExponentAdapter(Context context, List<LtExpoenentEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_love_rank, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtExpoenentEntity entity, int position) {
        if (position == 0) {
            holder.setVisible(R.id.iv_reward, true);
            holder.setVisible(R.id.tv_index, false);
            holder.setImageResource(R.id.iv_reward, R.mipmap.ya02wmsj_cecoe_first);
        } else if (position == 1) {
            holder.setVisible(R.id.iv_reward, true);
            holder.setVisible(R.id.tv_index, false);
            holder.setImageResource(R.id.iv_reward, R.mipmap.ya02wmsj_cecoe_second);
        } else if (position == 2) {
            holder.setVisible(R.id.iv_reward, true);
            holder.setVisible(R.id.tv_index, false);
            holder.setImageResource(R.id.iv_reward, R.mipmap.ya02wmsj_cecoe_third);
        } else {
            holder.setVisible(R.id.iv_reward, false);
            holder.setVisible(R.id.tv_index, true);
            holder.setText(R.id.tv_index, position + 1 + "");
        }
        holder.setText(R.id.tv_name, entity.getName());
        holder.setText(R.id.tv_time, entity.getPoints() + "");
        ImageManager.getInstance().loadCircleImage(mContext, entity.getPic(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));

        RecyclerView recyclerView = holder.getView(R.id.rv_downRv);
        RelativeLayout relativeLayout = holder.getView(R.id.rl_down);
        ImageView imageView = holder.getView(R.id.iv_downList);
        if (entity.getDone_list() != null && entity.getDone_list().size() > 0) {
            relativeLayout.setVisibility(View.VISIBLE);
            DoneLtExDetailAdapter adapter = new DoneLtExDetailAdapter(mContext, entity.getDone_list());
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);

            if (entity.isExpand()) {
                recyclerView.setVisibility(View.VISIBLE);
                ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_arrow_4, imageView);
            } else {
                recyclerView.setVisibility(View.GONE);
                ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_arrow_3, imageView);
            }

            imageView.setOnClickListener(v -> {
                if (!entity.isExpand()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    entity.setExpand(true);
                    ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_arrow_4, imageView);
                } else {
                    entity.setExpand(false);
                    recyclerView.setVisibility(View.GONE);
                    ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_arrow_3, imageView);
                    if (scrollCall != null){
                        scrollCall.scrollCallPosition(position);
                    }
                }
            });
        } else {
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
