package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-15.
 */
public class TestFragmentAdapter extends CommonAdapter<String> {
    public TestFragmentAdapter(Context context, List<String> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_test, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_text, s);
    }
}
