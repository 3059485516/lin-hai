package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtExpoenentEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;
import java.util.Random;

public class LtExponentAdapter extends CommonAdapter<LtExpoenentEntity> {
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
    }
}
