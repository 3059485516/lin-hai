package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WorthModifyContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.NodeContentFragment;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-08-07.
 */
public class WorthModifyPresenter extends WorthModifyContract.Presenter {
    private String mNodeId;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public WorthModifyPresenter(WorthModifyContract.View view, String node_id) {
        super(view);
        mNodeId = node_id;
    }

    @Override
    public void getNodeList() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<Node>>(Api.getNodeList(mNodeId)) {
            @Override
            protected void _onNext(List<Node> list) {
                if (list != null) {
                    make(list);
                    mView.update();
                    mView.dismissDialog();
                }
            }
        });
    }

    @Override
    public List<String> getTitles() {
        return mTitleList;
    }

    @Override
    public List<Fragment> getFragments() {
        return mFragmentList;
    }

    private void make(List<Node> list) {
        mTitleList.clear();
        mFragmentList.clear();
        for (Node node : list) {
            mTitleList.add(node.getTitle());
            String region_code = Constant.DEFAULT_REGION_CODE;
            if ("county".equals(node.getNode_level())) {
                region_code = RegionManager.getInstance().getCurrentCountyCode();
            } else if ("town".equals(node.getNode_level())) {
                region_code = RegionManager.getInstance().getCurrentTownCode();
            } else if ("village".equals(node.getNode_level())) {
                region_code = RegionManager.getInstance().getCurrentVillageCode();
            }
            mFragmentList.add(NodeContentFragment.start(region_code, mNodeId));
        }
    }

}
