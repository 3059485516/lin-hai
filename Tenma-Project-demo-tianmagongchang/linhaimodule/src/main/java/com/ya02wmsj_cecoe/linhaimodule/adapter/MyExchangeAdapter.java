package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.MyExchangeEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class MyExchangeAdapter extends CommonAdapter<MyExchangeEntity> {
    private RefundImp mImp;

    public interface RefundImp {
        void onRefund(MyExchangeEntity entity);
    }

    public MyExchangeAdapter(Context context, List<MyExchangeEntity> datas, RefundImp imp) {
        super(context, R.layout.ya02wmsj_cecoe_item_my_exchange, datas);
        mImp = imp;
    }

    @Override
    protected void convert(ViewHolder holder, MyExchangeEntity o, int position) {
        MyExchangeEntity.GoodInfo goodInfo = o.getGoods_info();
        if (goodInfo != null) {
            ImageManager.getInstance().loadImage(mContext, goodInfo.getGoods_pic(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
            holder.setText(R.id.tv_title, goodInfo.getGoods_title());
            holder.setText(R.id.tv_coins, goodInfo.getGoods_price() + "益币");
            holder.setText(R.id.tv_addr, "领取地点：" + goodInfo.getClaim_address());
            holder.setText(R.id.tv_name, "负责人：" + goodInfo.getClaim_charge() + "    " + goodInfo.getClaim_concat() + "      兑换码：" + o.getClaim_code());
        }
        holder.setText(R.id.tv_count, "数量x" + o.getGoods_num());
        holder.setText(R.id.tv_time, o.getClaim_time());

        if ("待领取".equals(o.getTrade_status())) {
            holder.setVisible(R.id.tv_back, true);
            holder.getView(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImp != null) {
                        mImp.onRefund(o);
                    }
                }
            });

        } else if ("已领取".equals(o.getTrade_status())) {
            holder.setVisible(R.id.tv_time, true);
        } else if ("已退还".equals(o.getTrade_status())) {
            holder.setVisible(R.id.divider, false);
            holder.setVisible(R.id.tv_addr, false);
            holder.setVisible(R.id.tv_name, false);
        }
    }
}
