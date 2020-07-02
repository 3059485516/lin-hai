package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ZjkNodeAdapter extends CommonAdapter<ZjkProfessionEntity> {
    private IZjkNodeClickListener listener;

    public ZjkNodeAdapter(Context context, List<ZjkProfessionEntity> datas, IZjkNodeClickListener listener) {
        super(context, R.layout.ya02wmsj_cecoe_lt_item_choose_2, datas);
        this.listener = listener;
    }

    public interface IZjkNodeClickListener {
        void onItemClickListener(ZjkProfessionEntity entity);
    }

    @Override
    protected void convert(ViewHolder holder, ZjkProfessionEntity zjkProfessionEntity, int position) {
        holder.setText(R.id.tv_name, zjkProfessionEntity.getName());
        holder.getView(R.id.tv_name).setSelected(zjkProfessionEntity.isSelected());
        holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ZjkProfessionEntity entity1 : getDatas()) {
                    if (zjkProfessionEntity.getId().equals(entity1.getId())) {
                        entity1.setSelected(true);
                    } else {
                        entity1.setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onItemClickListener(zjkProfessionEntity);
                }
            }
        });
    }
}
