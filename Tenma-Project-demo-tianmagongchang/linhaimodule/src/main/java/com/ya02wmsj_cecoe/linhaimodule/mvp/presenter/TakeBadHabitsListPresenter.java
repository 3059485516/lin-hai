package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TakeBadHabitsListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

import java.util.List;

import io.reactivex.Observable;

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
    public void onRefresh() {
        super.onRefresh();
        getContentList(true);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getContentList(false);
    }

    private void getContentList(boolean isRefresh){
        addDataObservable(contentList,isRefresh);
    }
    private final Observable<List<NodeContent>> contentList = Api.getContentList("", "", "", "74",
            String.valueOf(getPage()), String.valueOf(getOnePageMaxSize()));
}
