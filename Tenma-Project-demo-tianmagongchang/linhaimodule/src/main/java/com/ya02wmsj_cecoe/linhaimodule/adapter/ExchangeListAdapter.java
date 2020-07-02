package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ExchangeEntitly;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ExchangeListAdapter extends CommonAdapter<ExchangeEntitly> {
    private ExchangeImp mExchangeImp;

    public ExchangeListAdapter(Context context, List<ExchangeEntitly> datas, ExchangeImp imp) {
        super(context, R.layout.ya02wmsj_cecoe_item_exchange_list, datas);
        mExchangeImp = imp;
    }

    public interface ExchangeImp {
        void onExchangeClick(ExchangeEntitly entitly);
    }

    @Override
    protected void convert(ViewHolder holder, ExchangeEntitly entitly, int position) {
        holder.setText(R.id.tv_title, entitly.getGoods_title());
        holder.setText(R.id.tv_addr, "领取地址：" + entitly.getClaim_address());
        holder.setText(R.id.tv_value, entitly.getGoods_price() + "益币");
        if (entitly.getGoods_pic().isEmpty()) {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        } else {
            ImageManager.getInstance().loadImage(mContext, entitly.getGoods_pic(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        }
        holder.getView(R.id.iv_shopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mExchangeImp != null) {
                    mExchangeImp.onExchangeClick(entitly);
                }
            }
        });
    }
}
