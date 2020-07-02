package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

import java.util.ArrayList;
import java.util.List;

public interface FragmentVolunteerContract {
    interface View extends IListView {
        void updateNodeList();

        void updateBanner();
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        private List<Node> mNodeList = new ArrayList<>();
        private List<NodeContent> mBannerContent = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getNodeList();

        public List<Node> getNodeData() {
            return mNodeList;
        }

        public List<NodeContent> getBannerContent() {
            return mBannerContent;
        }

        public abstract void getBanner(String region_code, String node_id);

        public abstract List<String> getBannerUrls();

        public abstract List<String> getDefaultImgUrls();
    }
}
