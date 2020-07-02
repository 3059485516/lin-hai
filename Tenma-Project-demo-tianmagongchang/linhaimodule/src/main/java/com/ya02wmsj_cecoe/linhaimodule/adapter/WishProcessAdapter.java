package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishProcessItem;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishProcessAdapter extends CommonAdapter<WishProcessItem> {
    public WishProcessAdapter(Context context, List<WishProcessItem> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_wish_process, datas);
    }

    @Override
    protected void convert(ViewHolder holder, WishProcessItem wishProcessItem, int position) {
        holder.setText(R.id.tv_desc, wishProcessItem.getTitle());
        holder.setText(R.id.tv_time, wishProcessItem.getTime());
        if (position != getDatas().size() - 1) {
            holder.setImageResource(R.id.iv_circle, R.mipmap.ya02wmsj_cecoe_circle_red);
            holder.setVisible(R.id.view_line, true);
        } else {
            holder.setImageResource(R.id.iv_circle, R.mipmap.ya02wmsj_cecoe_circle_gray);
            holder.setVisible(R.id.view_line, false);
        }
    }
}
