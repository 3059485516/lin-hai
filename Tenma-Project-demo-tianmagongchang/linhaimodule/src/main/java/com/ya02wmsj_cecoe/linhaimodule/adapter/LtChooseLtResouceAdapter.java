package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtResouceEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtApplyResourceActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtChooseLtResouceAdapter extends CommonAdapter<LtResouceEntity> {
    public LtChooseLtResouceAdapter(Context context, List<LtResouceEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_lt_choose_resouce, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtResouceEntity ltResouceEntity, int position) {
        holder.setText(R.id.tv_name, ltResouceEntity.getName());
        ImageManager.getInstance().loadImage(mContext, ltResouceEntity.getPic(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  预约资源界面
                Intent intent = new Intent(mContext, LtApplyResourceActivity.class);
                intent.putExtra(Constant.KEY_BEAN, ltResouceEntity);
                mContext.startActivity(intent);
            }
        });
    }
}
