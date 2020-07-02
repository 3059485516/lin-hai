package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.MyWishListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class MyWishListPresenter extends MyWishListContract.Presenter {
    private int mType;

    public MyWishListPresenter(MyWishListContract.View view, int type) {
        super(view);
        mType = type;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getMyWishList(mType, getPage() + "", PAGE_SIZE + ""));
    }
}
