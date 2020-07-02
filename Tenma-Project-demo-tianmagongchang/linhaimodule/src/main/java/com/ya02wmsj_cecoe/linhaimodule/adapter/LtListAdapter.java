package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtChooseLtResourceActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtListAdapter extends CommonAdapter<LtEntitiy> {
    public LtListAdapter(Context context, List<LtEntitiy> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_item_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtEntitiy lt, int position) {
        holder.setText(R.id.tv_name, lt.getName());
        ImageManager.getInstance().loadCircleImage(mContext, lt.getPic(), R.mipmap.ya02wmsj_cecoe_net_1_3, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  获取礼堂下资源列表
                Intent intent = new Intent(mContext, LtChooseLtResourceActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, lt.getId() + "");
                mContext.startActivity(intent);
            }
        });
    }
}
