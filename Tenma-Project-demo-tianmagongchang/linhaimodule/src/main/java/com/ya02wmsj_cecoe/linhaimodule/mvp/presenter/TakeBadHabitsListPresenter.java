package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TakeBadHabitsListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/30 10:48 AM
 * desc   : EMPTY
 * ================================================
 */
public class TakeBadHabitsListPresenter extends TakeBadHabitsListContract.Presenter {
    public TakeBadHabitsListPresenter(TakeBadHabitsListContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList("", "", "", "74", String.valueOf(getPage()),PAGE_SIZE + ""));
    }
}
