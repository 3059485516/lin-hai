package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class CommentInnerPicAdapter extends CommonAdapter<String> {
    public CommentInnerPicAdapter(Context context, List<String> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_inner_pic, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        ImageManager.getInstance().loadImage(mContext, s, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_pic));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.gotoPreviewImageActivity(mContext, getDatas(), null, position);
            }
        });
    }
}
