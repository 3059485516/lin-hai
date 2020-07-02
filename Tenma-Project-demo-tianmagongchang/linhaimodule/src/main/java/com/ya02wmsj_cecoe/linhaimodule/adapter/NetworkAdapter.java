package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NetworkEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-17.
 */
public class NetworkAdapter extends CommonAdapter<NetworkEntity> {
    public NetworkAdapter(Context context, List<NetworkEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_newtwork, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NetworkEntity networkEntity, int position) {
        holder.setText(R.id.tv_title, networkEntity.getTitle());
        RecyclerView rv_sub = holder.getView(R.id.rv_sub);
        rv_sub.setLayoutManager(new GridLayoutManager(mContext, 4));
//        NodeAdapter adapter = new NodeAdapter(mContext, networkEntity.getSublist());
//        rv_sub.setAdapter(adapter);
    }
}
