package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.TDevice;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtMainNodeContentAdapter extends CommonAdapter<NodeContent> {
    public LtMainNodeContentAdapter(Context context, List<NodeContent> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_main, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NodeContent nodeContent, int position) {
        ImageView imageView = holder.getView(R.id.iv);
//        imageView.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (position % 2 == 0) {
            layoutParams.height = (int) TDevice.dpToPixel(160);
        } else {
            layoutParams.height = (int) TDevice.dpToPixel(200);
        }
        imageView.setLayoutParams(layoutParams);
        if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getSnapshotUrl())) {
            if (nodeContent.getVideo_path().getSnapshotUrl().contains(".gif") || nodeContent.getVideo_path().getSnapshotUrl().contains(".webp")) {
                ImageManager.getInstance().loadGifImage(mContext, nodeContent.getVideo_path().getSnapshotUrl(), imageView);
            } else {
                ImageManager.getInstance().loadImage(mContext, nodeContent.getVideo_path().getSnapshotUrl(), imageView);
            }
        } else if (!nodeContent.getIcon_path().isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            String icon_path = nodeContent.getIcon_path();
            if (icon_path.contains(".gif") || icon_path.contains(".webp")) {
                ImageManager.getInstance().loadGifImage(mContext, icon_path, imageView);
            } else {
                ImageManager.getInstance().loadImage(mContext, icon_path, imageView);
            }
        } else {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_placeholder, imageView);
        }

        if (!TextUtils.isEmpty(nodeContent.getPic_url())) {
            ImageManager.getInstance().loadCircleImage(mContext, nodeContent.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.photo));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.photo));
        }
        ImageView ivLike = holder.getView(R.id.iv_like);

        holder.setText(R.id.name, nodeContent.getName());
        holder.setText(R.id.title, nodeContent.getTitle());
        holder.setText(R.id.like_num, nodeContent.getThumb_num() + "");
    }
}
