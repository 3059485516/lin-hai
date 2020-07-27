package com.ya02wmsj_cecoe.linhaimodule.base.mvp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by 寻水的鱼 on 2019-09-16.
 *
 * adapter 只能继承于 BaseQuickAdapter
 */
public abstract class BaseQuickAdapterPresenter<A extends BaseQuickAdapter<T,? extends BaseViewHolder>,V extends BaseCleanListContract.View,T> extends BaseCleanListContract.Presenter<A,V> {
    public BaseQuickAdapterPresenter(V view) {
        super(view);
    }

    protected List<T> mData = new ArrayList<T>();

    public List<T> getData() {
        return mData;
    }

    @Override
    public A getAdapter() {
        return super.getAdapter();
    }

    public void addDataObservable(Observable<List<T>> dataObservable,boolean isRefresh){
        addRx2Destroy(new RxSubscriber<List<T>>(dataObservable) {
            @Override
            protected void _onNext(List<T> o) {
                handleData(o,isRefresh);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if(isRefresh){
                    mView.refreshError();
                }else {
                    mView.loadMoreError();
                }
            }
        });
    }

    public void handleData(List<T> data, boolean isRefresh){
        if(data != null){
            int size = data.size();
            if(size == 0){
                if(isRefresh){
                    mData.clear();
                    mAdapter.notifyDataSetChanged();
                }
                mView.noMoreData(isRefresh);
            }else if(size < getOnePageMaxSize()){
                if(isRefresh){
                    mAdapter.getData().clear();
                    mAdapter.addData(data);
                }else {
                    mAdapter.addData(data);
                }
                mView.noMoreData(isRefresh);
            } else {
                if(isRefresh){
                    mAdapter.getData().clear();
                    mAdapter.addData(data);
                    mView.refreshSucceed();
                }else {
                    mAdapter.addData(data);
                    mView.loadMoreSucceed();
                }
            }
        }
    }
}
