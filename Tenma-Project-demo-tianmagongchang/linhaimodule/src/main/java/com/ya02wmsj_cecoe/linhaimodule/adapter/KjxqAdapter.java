package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.KjxqDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class KjxqAdapter extends CommonAdapter<KjcgListEntity> {
    public KjxqAdapter(Context context, List<KjcgListEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_kjcg, datas);
    }

    @Override
    protected void convert(ViewHolder holder, KjcgListEntity kjcgListEntity, int position) {
        holder.setText(R.id.tv_title, kjcgListEntity.getTitle());
        holder.setText(R.id.tv_palce, kjcgListEntity.getCreateDate());
        ImageManager.getInstance().loadImage(mContext, kjcgListEntity.getImage(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, KjxqDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, kjcgListEntity.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
