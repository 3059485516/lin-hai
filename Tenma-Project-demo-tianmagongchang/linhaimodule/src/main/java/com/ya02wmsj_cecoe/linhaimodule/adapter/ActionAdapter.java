package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ActionDetailBetActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ActionAdapter extends CommonAdapter<ZhiyuanhuiEntity> {
    public ActionAdapter(Context context, List<ZhiyuanhuiEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_action, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ZhiyuanhuiEntity zhiyuanhuiEntity, int position) {
        ImageManager.getInstance().loadImage(mContext, zhiyuanhuiEntity.getThumb(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_title, zhiyuanhuiEntity.getTitle());
        holder.setText(R.id.tv_location, zhiyuanhuiEntity.getDetailaddress());

        String address = zhiyuanhuiEntity.getDetailaddress();
        if (TextUtils.isEmpty(address)) {
            address = zhiyuanhuiEntity.getProvince_name() + zhiyuanhuiEntity.getCity_name() + zhiyuanhuiEntity.getCounty_name();
        }
        holder.setText(R.id.tv_location, address);

        int finish = zhiyuanhuiEntity.getIs_finish();
        TextView tv_status = holder.getView(R.id.tv_status);

        if (1 == finish) {
            tv_status.setTextColor(Color.parseColor("#999999"));
            tv_status.setText(zhiyuanhuiEntity.getStatus());
        } else {
            tv_status.setTextColor(Color.parseColor("#3bbd66"));
            tv_status.setText(zhiyuanhuiEntity.getStatus());
        }
        int join = zhiyuanhuiEntity.getSignup_people() != 0 ? zhiyuanhuiEntity.getSignup_people() : zhiyuanhuiEntity.getJoin_num();
        holder.setText(R.id.tv_people, join + "/" + zhiyuanhuiEntity.getRecruit_people());
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ActionDetailBetActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, zhiyuanhuiEntity.getId());
            mContext.startActivity(intent);
        });
    }
}
