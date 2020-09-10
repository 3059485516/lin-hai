package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ServiceConcat2Adapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceConcatEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceInfoEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.List;


public class ServiceInfoDetail2Activity extends BaseActivity {
    public static void launch(Context context, ServiceInfoEntity serviceInfoEntity){
        context.startActivity(new Intent(context,ServiceInfoDetail2Activity.class)
        .putExtra(Constant.KEY_BEAN,serviceInfoEntity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_info_detail2;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.rv);
        TextView tvTitle = findViewById(R.id.tv_title);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ServiceInfoEntity serviceInfoEntity = (ServiceInfoEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        setTitle(serviceInfoEntity.getCategory_name());
        tvTitle.setText(serviceInfoEntity.getTitle());
        String icon_path = serviceInfoEntity.getIcon_path();
        if(!TextUtils.isEmpty(icon_path)){
            ImageView imageView = findViewById(R.id.iv_cover);
            ImageManager.getInstance().loadImage(this,icon_path,imageView);
        }
        List<ServiceConcatEntity> serve_concat = serviceInfoEntity.getServe_concat();
        View header = View.inflate(this, R.layout.ya02wmsj_cecoe_head_service_detail, null);
        TextView tvDesc = header.findViewById(R.id.tv_desc);
        String desc = serviceInfoEntity.getDesc();
        if(!TextUtils.isEmpty(desc)){
            tvDesc.setText(desc +"");
        }else {
            tvDesc.setText("");
        }
        TextView tvTime = header.findViewById(R.id.tv_time);
        tvTime.setText(serviceInfoEntity.getServe_time());
        TextView tvAddress = header.findViewById(R.id.tv_address);
        tvAddress.setText(serviceInfoEntity.getServe_address());

        ServiceConcat2Adapter serviceConcat2Adapter = new ServiceConcat2Adapter(serve_concat);
        serviceConcat2Adapter.addHeaderView(header);
        serviceConcat2Adapter.bindToRecyclerView(mRecyclerView);
    }

    @Override
    protected void initData() {
    }
}