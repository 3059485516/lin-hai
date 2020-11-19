package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.DiscussDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-01.
 */
public class AppraiseListAdapter extends CommonAdapter<NodeContent> {
    public AppraiseListAdapter(Context context, List<NodeContent> datas) {
        super(context, R.layout.ya02wmsj_cecoe_fragment_apperace, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NodeContent content, int position) {
        holder.setText(R.id.tv_title, content.getName());
        holder.setText(R.id.tv_make, "发起话题：");
        holder.setText(R.id.tv_inner_title, content.getTitle());
        holder.setText(R.id.tv_inner_conntent, content.getSubtitle());
        holder.setText(R.id.tv_time, content.getOperate_time());
        ImageManager.getInstance().loadCircleImage(mContext, content.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DiscussDetailActivity.class);
            intent.putExtra(Constant.KEY_BEAN, content);
            mContext.startActivity(intent);
        });
    }
}
