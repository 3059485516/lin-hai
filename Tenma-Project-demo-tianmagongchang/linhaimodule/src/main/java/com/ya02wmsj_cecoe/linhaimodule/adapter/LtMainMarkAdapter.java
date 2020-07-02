package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtMainMarkAdapter extends CommonAdapter<LtMarkEntity> {
    private IMarkItemClickLisenter callback;

    public LtMainMarkAdapter(Context context, List<LtMarkEntity> datas, IMarkItemClickLisenter callback) {
        super(context, R.layout.ya02wmsj_cecoe_lt_main_mark_item, datas);
        this.callback = callback;
    }

    public interface IMarkItemClickLisenter {
        void onMarkItemClick(LtMarkEntity ltMarkEntity);
    }

    @Override
    protected void convert(ViewHolder holder, LtMarkEntity ltMarkEntity, int position) {
        holder.setText(R.id.tv_mark, ltMarkEntity.getTitle());
        holder.getView(R.id.tv_mark).setSelected(ltMarkEntity.isSelected());
        holder.getView(R.id.tv_mark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LtMarkEntity entity : getDatas()) {
                    entity.setSelected(entity.getId() == ltMarkEntity.getId());
                }
                notifyDataSetChanged();
                if (callback != null) {
                    callback.onMarkItemClick(ltMarkEntity);
                }
            }
        });
    }
}
