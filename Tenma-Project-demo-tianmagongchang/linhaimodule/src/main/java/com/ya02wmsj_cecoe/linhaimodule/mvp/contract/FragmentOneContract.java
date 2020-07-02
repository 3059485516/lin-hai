package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-11.
 */
public interface FragmentOneContract {
    interface View extends IListView {
        void updataBanner();

        void updateNodeList();

        void updateTips();
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        private List<Node> mNodeList = new ArrayList<>();
        private List<NodeContent> mBannerContent = new ArrayList<>();
        private List<NodeContent> mTips = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract List<String> getDefaultImgUrls();

        public abstract List<String> getBannerImageList();

        public abstract void getBanner(String region_code);

        public abstract void getNodeList();

        public abstract void getTips();

        public List<Node> getNodeData() {
            return mNodeList;
        }

        public List<NodeContent> getBannerContent() {
            return mBannerContent;
        }

        public List<NodeContent> getTipsList() {
            return mTips;
        }
    }
}
