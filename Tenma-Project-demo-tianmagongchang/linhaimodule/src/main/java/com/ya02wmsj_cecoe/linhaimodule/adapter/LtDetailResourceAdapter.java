package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.MyItemViewDelegate;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtDetailResourceAdapter extends MultiItemTypeAdapter<LtDetailEntity.CaSourceList> {
    public LtDetailResourceAdapter(Context context, List<LtDetailEntity.CaSourceList> datas) {
        super(context, datas);
        addItemType();
    }

    private void addItemType() {
        addItemViewDelegate(new MultiPicture(R.layout.ya02wmsj_cecoe_lt_detail_source_item_multi));
        addItemViewDelegate(new SinglePicture(R.layout.ya02wmsj_cecoe_lt_detail_source_item_single));
    }

    class MultiPicture extends MyItemViewDelegate<LtDetailEntity.CaSourceList> {

        public MultiPicture(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(LtDetailEntity.CaSourceList item, int position) {
            return item.getPic() != null && item.getPic().size() > 2;   //多图样式
        }

        @Override
        public void convert(ViewHolder holder, LtDetailEntity.CaSourceList caSourceList, int position) {
            LinearLayout wrapPic = holder.getView(R.id.wrap_pic);
            holder.setText(R.id.tv_type, caSourceList.getName());
            holder.setText(R.id.tv_desc, Html.fromHtml(caSourceList.getDesc()).toString());
            for (int i = 0; i < caSourceList.getPic().size(); i++) {
                if (i >= 3) break;
                RatioImageView ivPic = (RatioImageView) wrapPic.getChildAt(i);
                ImageManager.getInstance().loadImage(mContext, caSourceList.getPic().get(i), ivPic);
            }
            holder.getView(R.id.wrap_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.gotoPreviewImageActivity(mContext, caSourceList.getPic(), null, 0);
                }
            });
        }
    }

    class SinglePicture extends MyItemViewDelegate<LtDetailEntity.CaSourceList> {

        public SinglePicture(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(LtDetailEntity.CaSourceList item, int position) {
            return item.getPic() == null || item.getPic().size() <= 2;
        }

        @Override
        public void convert(ViewHolder holder, LtDetailEntity.CaSourceList caSourceList, int position) {
            holder.setText(R.id.tv_type, caSourceList.getName());
            holder.setText(R.id.tv_desc, Html.fromHtml(caSourceList.getDesc()).toString());
            if (caSourceList.getPic() == null || caSourceList.getPic().size() == 0) {
                holder.setVisible(R.id.iv_pic, false);
            } else {
                holder.setVisible(R.id.iv_pic, true);
                RatioImageView ratioImageView = holder.getView(R.id.iv_pic);
                ImageManager.getInstance().loadImage(mContext, caSourceList.getPic().get(0), ratioImageView);
            }
            holder.getView(R.id.iv_pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtils.gotoPreviewImageActivity(mContext, caSourceList.getPic(), null, 0);
                }
            });
        }
    }
}
