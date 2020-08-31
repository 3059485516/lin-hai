package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WishDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishListAdapter extends CommonAdapter<WishListEntity> {

    public WishListAdapter(Context context, List<WishListEntity>datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_wish_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, WishListEntity wishListEntity, int position) {
        holder.setText(R.id.tv_title, wishListEntity.getTitle());
        holder.setText(R.id.tv_name, wishListEntity.getName());
        holder.setText(R.id.tv_status, wishListEntity.getCtime());
        holder.setText(R.id.tv_time, wishListEntity.getStatus());
        String icon_path = wishListEntity.getIcon_path();
        if (!TextUtils.isEmpty(icon_path)) {
            if (!icon_path.contains("http") && !icon_path.contains("HTTP")) {
                icon_path = Constant.getBaseUrl() + icon_path;
            }
            ImageManager.getInstance().loadImage(mContext, icon_path, holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        }
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, WishDetailActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, wishListEntity.getId());
            mContext.startActivity(intent);
        });
    }
}
