package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtChooseAreaAdapter2 extends CommonAdapter<LtStreetEntity> {
    private IClickAreaItemListener listener;

    public LtChooseAreaAdapter2(Context context, List<LtStreetEntity> datas, IClickAreaItemListener listener) {
        super(context, R.layout.ya02wmsj_cecoe_lt_item_choose_2, datas);
        this.listener = listener;
    }

    public interface IClickAreaItemListener {
        void onAreaItemClick(LtStreetEntity entity);
    }

    @Override
    protected void convert(ViewHolder holder, LtStreetEntity entity, int position) {
        holder.setText(R.id.tv_name, entity.getRegion_name());
        holder.getView(R.id.tv_name).setSelected(entity.isSelected());
        holder.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LtStreetEntity entity1 : getDatas()) {
                    if (entity.getCode().equals(entity1.getCode())) {
                        entity1.setSelected(true);
                    } else {
                        entity1.setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onAreaItemClick(entity);
                }
            }
        });
    }
}
