package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentVolunteerContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentVolunteerPresenter extends FragmentVolunteerContract.Presenter {
    private List<String> mBannerImageUrls = new ArrayList<>();

    public FragmentVolunteerPresenter(FragmentVolunteerContract.View view) {
        super(view);
    }

    @Override
    public void getNodeList() {
//        addRx2Destroy(new RxSubscriber<List<Node>>(Api.getNodeList("25")) {
//            @Override
//            protected void _onNext(List<Node> list) {
//                if (list != null) {
//                    getNodeData().clear();
//                    getNodeData().addAll(list);
//                    mView.updateNodeList();
//                }
//            }
//        });
    }

    @Override
    public void getBanner(String region_code, String node_id) {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentList(region_code, "y", "n", node_id, "1", "5")) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null) {
                    mBannerImageUrls.clear();
                    getBannerContent().clear();
                    getBannerContent().addAll(list);
                    for (NodeContent nodeContent : list) {
                        if (nodeContent.getTop_icon().contains("http") || nodeContent.getTop_icon().contains("HTTP")) {
                            mBannerImageUrls.add(nodeContent.getTop_icon());
                        } else {
                            mBannerImageUrls.add(Constant.getBaseUrl() + nodeContent.getTop_icon());
                        }
                    }
                    mView.updateBanner();
                }
            }
        });
    }

    @Override
    public List<String> getDefaultImgUrls() {
        return new ArrayList<>(Arrays.asList(
                "http://192.168.1.92:8026//upload//image//20190415//eab818686ece091a45efcaf4a0f8ca91.jpg",
                "http://192.168.1.92:8026//upload//image//20190415//70c4f8a0a2f022d6ea8d39ef759cdcc2.jpg",
                "http://192.168.1.92:8026//upload//image//20190415//f4db8345b20dcbae5f3b22f9d4646c67.jpg"
        ));
    }

    @Override
    public List<String> getBannerUrls() {
        return mBannerImageUrls;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        getBanner(RegionManager.getInstance().getCurrentCountyCode(), "30");
        setDataSource(Api.getContentList(RegionManager.getInstance().getCurrentCountyCode(), "","n", "30", getPage() + "", PAGE_SIZE + ""));
    }
}
