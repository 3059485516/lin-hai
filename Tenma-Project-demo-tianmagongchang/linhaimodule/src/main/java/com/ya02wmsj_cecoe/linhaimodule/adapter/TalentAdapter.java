package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.TalentEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TalentAdapter extends CommonAdapter<TalentEntity> {
    public TalentAdapter(Context context, List<TalentEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_talent, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TalentEntity talentEntity, int position) {
        holder.setText(R.id.tv_name, talentEntity.getName());
        holder.setText(R.id.tv_team, talentEntity.getTeam_name());
        holder.setText(R.id.tv_desc, talentEntity.getDesc());
        holder.setText(R.id.tv_region, talentEntity.getRegion_name());
        if (talentEntity.getPic_url().isEmpty()) {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, talentEntity.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        }
    }
}
