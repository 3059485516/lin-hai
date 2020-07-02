package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkzjEntiey;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.ZjkZjDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ZjkzkAdapter extends CommonAdapter<ZjkzjEntiey> {
    public ZjkzkAdapter(Context context, List<ZjkzjEntiey> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_zjk_zj, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ZjkzjEntiey zjkzjEntiey, int position) {
        if (TextUtils.isEmpty(zjkzjEntiey.getHeadImg())) {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, zjkzjEntiey.getHeadImg(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_icon));
        }
        holder.setText(R.id.tv_name, zjkzjEntiey.getName());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  跳转详情
                Intent intent = new Intent(mContext, ZjkZjDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, zjkzjEntiey.getId() + "");
                mContext.startActivity(intent);
            }
        });
    }
}
