package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.FullScreenVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class FragmentVolunteerAdapter extends CommonAdapter<NodeContent> {
    public FragmentVolunteerAdapter(Context context, List<NodeContent> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_volunteer, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NodeContent nodeContent, int position) {
        holder.setText(R.id.tv_title, nodeContent.getTitle());
        ImageManager.getInstance().loadImage(mContext, nodeContent.getIcon_path(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("直播".equals(nodeContent.getType())) {
                    if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                        T.showShort(mContext, "直播空闲中");
                        return;
                    } else {
                        Intent intent = new Intent(mContext, LiveActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getLiveinfo().getName());
                        intent.putExtra(Constant.KEY_STRING_2, nodeContent.getLiveinfo().getHls_pull_url());
                        mContext.startActivity(intent);
                    }
                } else if ("图文视频".equals(nodeContent.getType())) {
                    Intent intent;
                    if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
                        intent = new Intent(mContext, FullScreenVideoActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, "30");
                    } else {
                        intent = new Intent(mContext, TextContentActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, "30");
                    }
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
