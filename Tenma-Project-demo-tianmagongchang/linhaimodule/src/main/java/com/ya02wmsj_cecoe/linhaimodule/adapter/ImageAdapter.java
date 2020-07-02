package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends CommonAdapter<LocalMedia> {
    private int mMax = 8;

    public ImageAdapter(Context context, List<LocalMedia> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_image, datas);
    }

    public void setMax(int max) {
        mMax = max;
    }

    @Override
    public int getItemCount() {
        if (mDatas.size() < mMax) {
            return mDatas.size() + 1;
        } else {
            return mMax;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position < mDatas.size()) {
            ImageManager.getInstance().loadImage(mContext, mDatas.get(position).getPath(), holder.getView(R.id.iv_image));
            holder.setVisible(R.id.iv_delete, true).setOnClickListener(R.id.iv_delete, v -> {
                mDatas.remove(position);
                notifyDataSetChanged();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, null, position);
                }
            }).setOnClickListener(R.id.iv_image, v -> previewImages(position));
        } else {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_add_media, holder.getView(R.id.iv_image));
            holder.setVisible(R.id.iv_delete, false).setOnClickListener(R.id.iv_image, v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, null, position);
                }
            });
        }
    }

    @Override
    protected void convert(ViewHolder holder, LocalMedia localMedia, final int position) {
    }

    private void previewImages(int position) {
        if (mDatas.size() == 0) {
            return;
        }
        final ArrayList<String> list = new ArrayList<>();
        for (LocalMedia localMedia : mDatas) {
            list.add(localMedia.getPath());
        }
        JumpUtils.gotoPreviewImageActivity(mContext, list, null, position);
    }
}
