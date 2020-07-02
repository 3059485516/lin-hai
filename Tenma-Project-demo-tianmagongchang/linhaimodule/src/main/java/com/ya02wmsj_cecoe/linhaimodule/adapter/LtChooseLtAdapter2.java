package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtChooseLtAdapter2 extends CommonAdapter<LtEntitiy> {

    public LtChooseLtAdapter2(Context context, List<LtEntitiy> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_item_choose_2, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtEntitiy entitiy, int position) {
        holder.setText(R.id.tv_name, entitiy.getName());
        holder.getView(R.id.tv_name).setBackgroundColor(Color.WHITE);
    }
}
