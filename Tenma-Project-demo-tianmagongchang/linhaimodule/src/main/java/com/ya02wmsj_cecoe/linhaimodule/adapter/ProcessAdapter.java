package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.DealProcess;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class ProcessAdapter extends CommonAdapter<DealProcess> {
    public ProcessAdapter(Context context, List<DealProcess> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_process, datas);
    }

    @Override
    protected void convert(ViewHolder holder, DealProcess dealProcess, int position) {
        holder.setText(R.id.tv_process, dealProcess.getProcess());
    }
}
