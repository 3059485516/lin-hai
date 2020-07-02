package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.event.ExchangeResult;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ExchangeFuliContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.ExchangeListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ExchangeFuliPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;

import io.reactivex.functions.Consumer;


public class ExchangeFuliActivity extends BaseViewPagerActivity<ExchangeFuliPresenter> implements ExchangeFuliContract.View {
    private TextView mTvCoins;


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_exchange_fuli_activity;
    }

    @Override
    public String[] getTitles() {
        return new String[]{"全部", "1-100", "101-300", "301-5000", "5000-1000000"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{ExchangeListFragment.create(""), ExchangeListFragment.create("1-100"),
                ExchangeListFragment.create("101-300"), ExchangeListFragment.create("301-5000"), ExchangeListFragment.create("5000-1000000")};
    }

    @Override
    protected void initMVP() {
        mPresenter = new ExchangeFuliPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getVolunteerScore();
    }

    @Override
    protected void initView() {
        setTitle("我要兑福利");
        setMenuText("我的兑换");
        mTvCoins = findViewById(R.id.tv_my_coins);
        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(ExchangeResult.class).subscribe(new Consumer<ExchangeResult>() {
            @Override
            public void accept(ExchangeResult exchangeResult) throws Exception {
                mPresenter.getVolunteerScore();     //更新益币
            }
        }));

        findViewById(R.id.tv_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://appapi.zyh365.com/qrcode/index.html?app_token=" + Config.getInstance().getVolunteerToken();
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, "兑换码");
                intent.putExtra(Constant.KEY_STRING_2, url);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(MyExchangeActivity.class);
    }

    @Override
    public void updateCoins(String coins) {
        mTvCoins.setText("当前益币：" + coins);
    }
}
