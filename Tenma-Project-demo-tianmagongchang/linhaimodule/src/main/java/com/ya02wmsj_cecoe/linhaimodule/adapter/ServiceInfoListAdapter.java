package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ServiceInfoDetail2Activity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ServiceInfoListAdapter extends CommonAdapter<ServiceInfoEntity> {
    public ServiceInfoListAdapter(Context context, List<ServiceInfoEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_service_info, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ServiceInfoEntity info, int position) {
        holder.setText(R.id.tv_name, info.getTitle());
        holder.setText(R.id.tv_addr, info.getServe_address());
        holder.setText(R.id.tv_time, info.getServe_time()+"营业");
        holder.setText(R.id.tv_distance, "距离当前：" + info.getDistance());
        ImageView ivCover = holder.getView(R.id.iv_cover);
        String icon_path = info.getIcon_path();
        if(TextUtils.isEmpty(icon_path)){
            ImageManager.getInstance().loadImage(mContext,R.mipmap.ya02wmsj_cecoe_service_place,ivCover);
        }else {
            ImageManager.getInstance().loadImage(mContext,icon_path,ivCover);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转
             /*   Intent intent = new Intent(mContext, ServiceInfoDetailActivity.class);
                intent.putExtra(Constant.KEY_BEAN, info);
                mContext.startActivity(intent);*/
                ServiceInfoDetail2Activity.launch(mContext,info);
            }
        });
    }
}
