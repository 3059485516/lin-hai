package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentOneContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ya02wmsj_cecoe.linhaimodule.Constant.DEFAULT_REGION_CODE;
import static com.ya02wmsj_cecoe.linhaimodule.Constant.MAIN_NEW;

/**
 * Created by BenyChan on 2019-07-11.
 */
public class FragmentOnePresenter extends FragmentOneContract.Presenter {
    private List<String> mBannerImageUrls = new ArrayList<>();
    private String mRegionCode = DEFAULT_REGION_CODE;

    public FragmentOnePresenter(FragmentOneContract.View view) {
        super(view);
    }

    @Override
    public List<String> getDefaultImgUrls() {
        return new ArrayList<>(Arrays.asList("http://192.168.1.92:8026//upload//image//20190415//eab818686ece091a45efcaf4a0f8ca91.jpg",
                "http://192.168.1.92:8026//upload//image//20190415//70c4f8a0a2f022d6ea8d39ef759cdcc2.jpg",
                "http://192.168.1.92:8026//upload//image//20190415//f4db8345b20dcbae5f3b22f9d4646c67.jpg"));
    }

    @Override
    public List<String> getBannerImageList() {
        return mBannerImageUrls;
    }

    @Override
    public void getBanner(String region_code) {
        mRegionCode = region_code;
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getIndexTop(region_code)) {
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
                        mView.updataBanner();
                    }
                }
            }
        });
    }

    @Override
    public void getNodeList() {
        addRx2Destroy(new RxSubscriber<List<Node>>(Api.getNodeList("57")) {
            @Override
            protected void _onNext(List<Node> list) {
                if (list != null) {
                    getNodeData().clear();
                    getNodeData().addAll(list);
                    mView.updateNodeList();
                }
            }
        });
    }

    @Override
    public void getTips() {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getContentList("", "", "", "72", "", "")) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null && list.size() > 0) {
                    getTipsList().clear();
                    getTipsList().addAll(list);
                    mView.updateTips();
                }
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(DEFAULT_REGION_CODE, "y", "n", String.valueOf(MAIN_NEW), String.valueOf(getPage()), String.valueOf(PAGE_SIZE)));
        if (isRefresh) {
            getBanner(mRegionCode);
            getTips();   //获取快讯
            getIndexActivity();
            getNodeList();
        }
    }


    @Override
    public void getIndexActivity() {
        addRx2Destroy(new RxSubscriber<List<AppraiseEntity>>(Api.getIndexActivity()) {
            @Override
            protected void _onNext(List<AppraiseEntity> list) {
                if (list != null && list.size() > 0) {
                    getAppraiseEntityList().clear();
                    getAppraiseEntityList().addAll(list);
                }
                mView.updateMainActivityView();
            }

            @Override
            protected void _onError(String code) {
                mView.updateMainActivityView();
            }
        });
    }
}
