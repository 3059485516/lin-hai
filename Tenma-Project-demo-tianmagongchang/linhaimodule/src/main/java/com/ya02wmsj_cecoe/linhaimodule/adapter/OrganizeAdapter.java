package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrganizeDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-24.
 */
public class OrganizeAdapter extends CommonAdapter<OrganizeListEntity> {
    public OrganizeAdapter(Context context, List<OrganizeListEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_organize, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrganizeListEntity o, int position) {
        holder.setText(R.id.tv_name, o.getName());
        holder.setText(R.id.tv_people, o.getCategory_name());
        holder.getConvertView().setOnClickListener(v -> {
            // 加入组织
            Intent intent = new Intent(mContext, OrganizeDetailActivity.class);
            intent.putExtra(Constant.KEY_BEAN, o);
            mContext.startActivity(intent);
        });
    }
}
