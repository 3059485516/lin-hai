package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveListEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.User;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LiveListAdapter extends CommonAdapter<LiveListEntity> {
    public LiveListAdapter(Context context, List<LiveListEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_live_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LiveListEntity liveListEntity, int position) {
        holder.setText(R.id.tv_title, liveListEntity.getName());
        holder.setText(R.id.tv_status, liveListEntity.getStatus());
        User user = liveListEntity.getUser();
        if (user != null) {
            holder.setText(R.id.tv_name, "主播：" + user.getName());
            String pic = liveListEntity.getIcon();
            if (!pic.contains("http") && !pic.contains("HTTP")) pic = Constant.getBaseUrl() + pic;
            ImageManager.getInstance().loadImage(mContext, pic, holder.getView(R.id.iv_pic));
        }
    }
}
