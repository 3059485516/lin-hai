package com.ya02wmsj_cecoe.linhaimodule.adapter;


import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class MainFocusAdapter extends CommonAdapter<Object> {
    public MainFocusAdapter(Context context, List<Object> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_main_focus, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Object o, int position) {

    }
}
