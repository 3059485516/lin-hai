package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LittleVideoAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 6:02 PM
 * desc   : EMPTY
 * ================================================
 */
public interface LittleVideoContract {
    interface View extends IView{
        void changeLikeState(boolean isSucceed, TextView tvLike, int position);
        void changeCollectState(boolean isSucceed, TextView tvCollect, int position);
        void changeShareState(boolean isSucceed);

        void refreshSucceed();
        void refreshError();
        void loadMoreError();
        void loadMoreSucceed();

        void noMoreData(boolean isRefresh);

        void commentSuc(String count, TextView tvComment);
    }
    abstract class Presenter extends APresenter<View>{

        private final String mContentId;
        private final String mRegionCode;
        private final String mNodeId;


        public Presenter(View view, Intent intent) {
            super(view);
            mContentId = intent.getStringExtra(Constant.KEY_STRING_1);
            mRegionCode = intent.getStringExtra(Constant.KEY_STRING_2);
            mNodeId = intent.getStringExtra(Constant.KEY_STRING_3);
        }

        public boolean isHasLike(@NonNull NodeContent nodeContent){
            return  Objects.requireNonNull(nodeContent).getThumb() == 1;      //是否已经点过赞

        }
        public boolean isHasCollect(@NonNull NodeContent nodeContent){
            return Objects.requireNonNull(nodeContent).getCollect() == 1; //是否已经收藏过
        }

        protected int mPage = 1;
        protected int mRow = 20;

        public int getRow() {
            return mRow;
        }

        public int getPage() {
            return mPage;
        }

        protected void resetPage(){
            mPage = 1;
        }

        public String getContentId() {
            return mContentId;
        }

        public String getRegionCode() {
            return mRegionCode;
        }

        public String getNodeId() {
            return mNodeId;
        }

        public abstract void like(String id, TextView view, int position);

        public abstract void collect(String id, TextView view, int position);

        public abstract void share(String id);

//        public abstract void getDetailContent();


        public void refresh(){
            resetPage();
            requestData(true);
        }

        public void loadMore(){
            mPage++;
            requestData(false);
        }

        public abstract void requestData(boolean isRefresh);

        private List<NodeContent> mData = new ArrayList<>();

        public List<NodeContent> getData() {
            return mData;
        }

        private BaseQuickAdapter<NodeContent,?> mAdapter = new LittleVideoAdapter(getData());

        public BaseQuickAdapter<NodeContent, ?> getAdapter() {
            return mAdapter;
        }

        public void handleData(List<NodeContent> data, boolean isRefresh){
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
//                        mData.clear();
//                        mData.addAll(data);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.getData().clear();
                        mAdapter.addData(data);
                    }else {
//                        mData.addAll(data);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.addData(data);
                    }
                    mView.noMoreData(isRefresh);
                } else {
                    if(isRefresh){
//                        mData.clear();
//                        mData.addAll(data);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.getData().clear();
                        mAdapter.addData(data);
                        mView.refreshSucceed();
                    }else {
//                        mData.addAll(data);
//                        mAdapter.notifyDataSetChanged();
                        mAdapter.addData(data);
                        mView.loadMoreSucceed();

                    }
                }
            }
        }

        public void addDataObservable(Observable<List<NodeContent>> dataObservable, boolean isRefresh){
            addRx2Destroy(new RxSubscriber<List<NodeContent>>(dataObservable) {
                @Override
                protected void _onNext(List<NodeContent> o) {
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

        private int getOnePageMaxSize() {
            return getRow();
        }

        public abstract void getCommentById(String c_id, CommentAdapter adapter, ProgressBar progressBar);

        private List<CommentEntity> commentEntityList = new ArrayList<>();
        public List<CommentEntity> getCommentList() {
            return commentEntityList;
        }

        public abstract void addComment(String id, String comment, TextView tvComment);
    }
}
