package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrganizationDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationSubListAdapter extends CommonAdapter<OrganizationSubEntity> {
    public OrganizationSubListAdapter(Context context, List<OrganizationSubEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_sub_organization, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrganizationSubEntity organizationSubEntity, int position) {
        holder.setText(R.id.tv_name, organizationSubEntity.getName());
        holder.setText(R.id.tv_address, organizationSubEntity.getAddress());
        holder.getConvertView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, OrganizationDetailActivity.class);
            intent.putExtra(Constant.KEY_BEAN, organizationSubEntity);
            mContext.startActivity(intent);
        });
    }
}
