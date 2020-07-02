package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtShowAdapter extends CommonAdapter<LtEntitiy> {
    public LtShowAdapter(Context context, List<LtEntitiy> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_show_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtEntitiy entitiy, int position) {
        holder.setText(R.id.tv_name, entitiy.getName());
        ImageManager.getInstance().loadCircleImage(mContext, entitiy.getPic(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LtDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, entitiy.getId() + "");
                intent.putExtra(Constant.KEY_STRING_2, entitiy.getName());
                mContext.startActivity(intent);
            }
        });
    }
}
