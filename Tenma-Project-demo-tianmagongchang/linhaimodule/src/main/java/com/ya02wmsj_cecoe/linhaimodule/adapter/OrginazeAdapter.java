package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrginazeDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class OrginazeAdapter extends CommonAdapter<OrginazeListEntity> {
    public OrginazeAdapter(Context context, List<OrginazeListEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_orginaze, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrginazeListEntity orginazeListEntity, int position) {
        holder.setText(R.id.tv_name, orginazeListEntity.getName());
        holder.setVisible(R.id.tv_time, false);
        holder.setVisible(R.id.tv_number, false);
        holder.setText(R.id.tv_time, orginazeListEntity.getHours() + "小时");
        holder.setText(R.id.tv_number, orginazeListEntity.getPerson() + "人");
        if (!TextUtils.isEmpty(orginazeListEntity.getAvatar())) {
            ImageManager.getInstance().loadCircleImage(mContext, orginazeListEntity.getAvatar(), holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_worth_1, holder.getView(R.id.iv_icon));
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrginazeDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, orginazeListEntity.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
