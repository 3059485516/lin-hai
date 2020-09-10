package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LittleVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.TDevice;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/30 9:34 AM
 * desc   : EMPTY
 * ================================================
 */
public class TakeBadHabitsAdapter extends CommonAdapter<NodeContent> {

    public TakeBadHabitsAdapter(Context context, List<NodeContent> data) {
        super(context,R.layout.ya02wmsj_cecoe_item_take_bad_habits, data);
    }

    @Override
    protected void convert(ViewHolder helper, NodeContent item, int position) {
        ImageView imageView = helper.getView(R.id.iv_cover);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        final int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition % 2 == 0) {
            layoutParams.height = (int) TDevice.dpToPixel(160);
        } else {
            layoutParams.height = (int) TDevice.dpToPixel(200);
        }
        imageView.setLayoutParams(layoutParams);
        if (item.getVideo_path() != null && !TextUtils.isEmpty(item.getVideo_path().getSnapshotUrl())) {
            if (item.getVideo_path().getSnapshotUrl().contains(".gif") || item.getVideo_path().getSnapshotUrl().contains(".webp")) {
                ImageManager.getInstance().loadGifImage(mContext, item.getVideo_path().getSnapshotUrl(), imageView);
            } else {
                ImageManager.getInstance().loadImage(mContext, item.getVideo_path().getSnapshotUrl(), imageView);
            }
        } else if (!item.getIcon_path().isEmpty()) {
            imageView.setVisibility(View.VISIBLE);
            String icon_path = item.getIcon_path();
            if (icon_path.contains(".gif") || icon_path.contains(".webp")) {
                ImageManager.getInstance().loadGifImage(mContext, icon_path, imageView);
            } else {
                ImageManager.getInstance().loadImage(mContext, icon_path, imageView);
            }
        } else {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_placeholder, imageView);
        }

        if (!TextUtils.isEmpty(item.getPic_url())) {
            ImageManager.getInstance().loadCircleImage(mContext, item.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, helper.getView(R.id.iv_head));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, helper.getView(R.id.iv_head));
        }
        helper.setText(R.id.tv_name, item.getName()).setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_like_num, item.getThumb_num() + "");
        helper.itemView.setOnClickListener(v -> LittleVideoActivity.launch(mContext, item.getId(), RegionManager.getInstance().getCurrentCountyCode(), "74"));
    }
}
