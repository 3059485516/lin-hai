package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ServiceInfoDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/8/5 2:07 PM
 * desc   : EMPTY
 * ================================================
 */
public class ServiceInfoList2Adapter  extends CommonAdapter<ServiceInfoEntity> {
    public ServiceInfoList2Adapter(Context context, List<ServiceInfoEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_service_info2, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ServiceInfoEntity info, int position) {
        holder.setText(R.id.tv_name, info.getTitle());
        holder.setText(R.id.tv_addr, info.getServe_address());
        holder.setText(R.id.tv_time, info.getServe_time());
        holder.setText(R.id.tv_distance, "距离当前：" + info.getDistance());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转
                Intent intent = new Intent(mContext, ServiceInfoDetailActivity.class);
                intent.putExtra(Constant.KEY_BEAN, info);
                mContext.startActivity(intent);
            }
        });
    }
}