package com.ya02wmsj_cecoe.linhaimodule.widget.regionpicker;


import android.content.Context;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-05-31.
 */
public class RegionPickerAdapter<T extends IRegionPickImp> extends CommonAdapter<T> {
    private OnRegionItemClickListener mListener;

    public RegionPickerAdapter(Context context, List<T> datas, OnRegionItemClickListener listener) {
        super(context, R.layout.ya02wmsj_cecoe_item_region_pick, datas);
        mListener = listener;
    }

    public void setDefaultPosition(int position) {
        if (getDatas() != null && getDatas().size() > position) {
            setSelectStatus(getDatas().get(position).getId());
        }
    }

    @Override
    protected void convert(ViewHolder holder, T t, int position) {
        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(t.getText());
        tv_name.setOnClickListener(v -> {
            setSelectStatus(t.getId());
            notifyDataSetChanged();
            if (mListener != null) {
                mListener.onRegionItemClickListener(t, position);
            }
        });
        tv_name.setSelected(t.isSelected());
    }

    private void setSelectStatus(String id) {
        for (T t : getDatas()) {
            if (id.equals(t.getId())) {
                t.setSelected(true);
            } else {
                t.setSelected(false);
            }
        }
    }
}
