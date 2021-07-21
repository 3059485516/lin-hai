package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
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
    }
}
