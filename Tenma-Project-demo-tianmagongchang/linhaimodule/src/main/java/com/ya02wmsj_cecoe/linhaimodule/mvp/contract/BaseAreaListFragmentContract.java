package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.ArrayList;
import java.util.List;

public interface BaseAreaListFragmentContract {
    interface View extends IListView {
        void updateContry();

        void updateTown();

        void updateVillage();

        void updateOnlineList();

        void updataBanner();

        void updateNodeList();

        void updateMarkList();
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        private List<Node> mNodeList = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getRegionCountyData();

        public abstract void getRegionTownData();

        public abstract void getRegionVillageData();

        public abstract void getOnlineActivityList();

        public abstract List<AppraiseEntity> getOnlineList();

        public abstract List<String> getDefaultImgUrls();

        public abstract void getBanner(String region_code);

        public abstract List<String> getBannerImageList();

        public abstract void getNodeList();

        public List<Node> getNodeData() {
            return mNodeList;
        }
    }
}
