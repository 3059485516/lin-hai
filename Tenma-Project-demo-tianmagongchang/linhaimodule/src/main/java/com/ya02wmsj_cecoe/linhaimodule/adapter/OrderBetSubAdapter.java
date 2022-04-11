package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrderDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class OrderBetSubAdapter extends CommonAdapter<Node> {

    public OrderBetSubAdapter(Context context, List<Node> datas) {
        super(context, R.layout.ya02wmsj_cecoe_order_sub_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Node node, int position) {
        ImageManager.getInstance().loadImage(mContext, node.getIcon(), R.mipmap.ya02wmsj_cecoe_platform_2, holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_title, node.getTitle());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, node.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
