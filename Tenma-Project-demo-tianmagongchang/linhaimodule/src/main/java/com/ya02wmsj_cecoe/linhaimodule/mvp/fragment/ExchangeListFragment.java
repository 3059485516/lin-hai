package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ExchangeListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.ExchangeEntitly;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ExchangeFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ExchangeFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.ya02wmsj_cecoe.linhaimodule.utils.TDevice.getScreenWidth;

public class ExchangeListFragment extends BaseListFragment<ExchangeFragmentContract.Presenter> implements ExchangeFragmentContract.View, ExchangeListAdapter.ExchangeImp {

    public static ExchangeListFragment create(String price) {
        ExchangeListFragment fragment = new ExchangeListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, price);
        fragment.setArguments(bundle);
        return fragment;
    }

    private Dialog mExchangeDialog;
    private int mCount = 1;
    private double mTotalPrice = 0;

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ExchangeListAdapter(mActivity, mPresenter.getDataList(), this);
    }

    @Override
    protected void initMVP() {
        mPresenter = new ExchangeFragmentPresenter(this, getArguments().getString(Constant.KEY_STRING_1, ""));
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
    }

    @Override
    public void exchangeCompleted() {

    }

    @Override
    public void onExchangeClick(ExchangeEntitly entitly) {
        // show dialog
        showExchangeDialog(entitly);
    }

    private void dismissExchangeDialog() {
        if (mExchangeDialog != null) {
            mExchangeDialog.dismiss();
            mExchangeDialog = null;
        }
    }

    private void showExchangeDialog(ExchangeEntitly entitly) {
        if (mExchangeDialog != null) {
            mExchangeDialog.show();
            return;
        }
        mCount = 1;
        mTotalPrice = Double.parseDouble(entitly.getGoods_price()) * mCount;
        mExchangeDialog = new Dialog(mActivity, R.style.BottomDialogStyle);
        mExchangeDialog.setCanceledOnTouchOutside(true);
        mExchangeDialog.setCancelable(true);
        mExchangeDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mExchangeDialog != null) {
                    mExchangeDialog.dismiss();
                    mExchangeDialog = null;
                }
            }
        });
        View view = LayoutInflater.from(mActivity).inflate(R.layout.ya02wmsj_cecoe_dialog_exchange, null);
        ImageView iv_arrow = view.findViewById(R.id.iv_arrow);
        RatioImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_addr = view.findViewById(R.id.tv_addr);
        TextView tv_coins = view.findViewById(R.id.tv_coins);
        TextView tv_add = view.findViewById(R.id.tv_add);
        TextView tv_count = view.findViewById(R.id.tv_count);
        TextView tv_remove = view.findViewById(R.id.tv_remove);
        TextView tv_total = view.findViewById(R.id.tv_total);
        TextView tv_exchange = view.findViewById(R.id.tv_exchange);

        ImageManager.getInstance().loadImage(mActivity, entitly.getGoods_pic(), R.mipmap.ya02wmsj_cecoe_placeholder, iv_icon);
        tv_title.setText(entitly.getGoods_title());
        tv_addr.setText("领取地址：" + entitly.getClaim_address());
        tv_coins.setText(entitly.getGoods_price() + "益币");
        tv_total.setText("合计：" + mTotalPrice + "益币");
        tv_count.setText(mCount + "");

        iv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissExchangeDialog();
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount++;
                tv_count.setText(mCount + "");
                Double d = Double.parseDouble(entitly.getGoods_price());
                BigDecimal price = new BigDecimal(Double.toString(d));
                BigDecimal count = new BigDecimal(Integer.toString(mCount));
                Double result = price.multiply(count).doubleValue();
                mTotalPrice = result;
                tv_total.setText("合计：" + result + "益币");
            }
        });

        tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount == 1) return;
                mCount--;
                tv_count.setText(mCount + "");
                Double d = Double.parseDouble(entitly.getGoods_price());
                BigDecimal price = new BigDecimal(Double.toString(d));
                BigDecimal count = new BigDecimal(Integer.toString(mCount));
                Double result = price.multiply(count).doubleValue();
                mTotalPrice = result;
                tv_total.setText("合计：" + result + "益币");
            }
        });

        tv_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  兑换
                Map<String, Object> map = new HashMap<>();
                map.put("goods_no", entitly.getGoods_no());
                map.put("goods_num", mCount + "");
                map.put("total_amount", mTotalPrice);
                mPresenter.exchange(map);
                dismissExchangeDialog();
            }
        });

        mExchangeDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = mExchangeDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        mExchangeDialog.show();
    }
}
