package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.FullScreenVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LittleVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.utils.TDevice;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainNodeContentAdapter extends CommonAdapter<NodeContent> {


    public MainNodeContentAdapter(Context context, List<NodeContent> datas) {
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
        holder.getView(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = nodeContent.getType();
                if ("图文视频".equals(type)) {
                    if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
                      /*  Intent intent = new Intent(mContext, FullScreenVideoActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                        mContext.startActivity(intent);*/
                        LittleVideoActivity.launch(mContext,nodeContent.getId(),RegionManager.getInstance().getCurrentCountyCode(),nodeContent.getNode_id());
                    } else {
                        Intent intent = new Intent(mContext, TextContentActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                        intent.putExtra(Constant.KEY_STRING_4, "y");
                        mContext.startActivity(intent);
                    }
                } else if ("相册".equals(type)) {
                    JumpUtils.gotoPreviewImageActivity(mContext, new ArrayList<>(Arrays.asList(nodeContent.getPath().split(","))), nodeContent.getAlbumDesc(), 0);
                } else if ("直播".equals(type)) {
                    if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                        T.showShort(mContext, "直播空闲中");
                        return;
                    }
                    Intent intent = new Intent(mContext, LiveActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getLiveinfo().getName());
                    intent.putExtra(Constant.KEY_STRING_2, nodeContent.getLiveinfo().getHls_pull_url());
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
