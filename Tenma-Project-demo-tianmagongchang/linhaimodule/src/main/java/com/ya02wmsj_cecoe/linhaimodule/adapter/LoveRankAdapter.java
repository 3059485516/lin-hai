package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.RankingEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class LoveRankAdapter extends CommonAdapter<RankingEntity> {
    private boolean mbOrginaze;

    public LoveRankAdapter(Context context, List<RankingEntity> datas, boolean orginaze) {
        super(context, R.layout.ya02wmsj_cecoe_item_love_rank, datas);
        mbOrginaze = orginaze;
    }

    @Override
    protected void convert(ViewHolder holder, RankingEntity entity, int position) {
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
        holder.setText(R.id.tv_time, entity.getDuration() + "小时");
//        String url = mbOrginaze ? entity.getHeadImageUrl() : entity.getHeadimage();
        String url = entity.getHeadimage();
        ImageManager.getInstance().loadCircleImage(mContext, url, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
    }
}
