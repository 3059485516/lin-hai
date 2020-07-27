package com.ya02wmsj_cecoe.linhaimodule.base.mvp;


import android.support.v7.widget.RecyclerView;

/**
 * Created by 寻水的鱼 on 2019-09-16.
 */
public interface BaseCleanListContract {
    interface View extends IView{
        void refreshError();

        void loadMoreError();

        void refreshSucceed();

        void loadMoreSucceed();

        void noMoreData(boolean isRefresh);

        void upDataList();
    }

    abstract class Presenter<A extends RecyclerView.Adapter<?>,V extends View> extends APresenter<V>{
        protected A mAdapter;
        public Presenter(V view) {
            super(view);
        }

        public A getAdapter() {
            return mAdapter;
        }

        private int mPage = 1;


        protected void resetPage(){
            mPage = 1;
        }

        protected int getPage(){
            return mPage;
        }

        protected int getOnePageMaxSize(){
            return AListPresenter.PAGE_SIZE;
        }

        /***
         * 子类继承，重写
         */
        public void onRefresh(){
            resetPage();
        }

        /***
         * 子类继承，重写
         */
        public void onLoadMore(){
            mPage++;
        }
    }
}
