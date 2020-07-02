package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LtPublishMarkAdapter extends CommonAdapter<LtMarkEntity> {
    private IMarkSelectedCallback callback;
    private List<LtMarkEntity> mSelectedList = new ArrayList<>();

    public LtPublishMarkAdapter(Context context, List<LtMarkEntity> datas,IMarkSelectedCallback callback) {
        super(context, R.layout.ya02wmsj_cecoe_lt_main_mark_item, datas);
        this.callback = callback;
    }

    public interface IMarkSelectedCallback{
        void getSelectedList(List<LtMarkEntity> list);
    }

    @Override
    protected void convert(ViewHolder holder, LtMarkEntity ltMarkEntity, int position) {
        holder.setText(R.id.tv_mark, ltMarkEntity.getTitle());
        holder.getView(R.id.tv_mark).setSelected(ltMarkEntity.isSelected());
        holder.getView(R.id.tv_mark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = ltMarkEntity.isSelected();
                ltMarkEntity.setSelected(!status);
                notifyDataSetChanged();
                if (callback != null) {
                    callback.getSelectedList(getSelectedList());
                }
            }
        });
    }

    private List<LtMarkEntity> getSelectedList() {
        mSelectedList.clear();
        for (LtMarkEntity entity : getDatas()) {
            if (entity.isSelected()) {
                mSelectedList.add(entity);
            }
        }
        return mSelectedList;
    }
}
