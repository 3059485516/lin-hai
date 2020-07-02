package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ActivityThemeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-29.
 */
public class AppraiseFragmentPresenter extends AppraiseFragmentContract.Presenter {
    private String mRegionCode = RegionManager.getInstance().getCurrentCountyCode();
    private List<ActivityThemeEntity> mThemeList = new ArrayList<>();

    public AppraiseFragmentPresenter(AppraiseFragmentContract.View view) {
        super(view);
    }

    public void setRegionCode(String region) {
        mRegionCode = region;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getOnlineActivityList(getPage() + "", PAGE_SIZE + "", mRegionCode, ""));
    }

    public void getActivityTheme() {
        addRx2Destroy(new RxSubscriber<List<ActivityThemeEntity>>(Api.getActivityTheme()) {
            @Override
            protected void _onNext(List<ActivityThemeEntity> list) {
                if (list != null && list.size() > 0) {
                    mThemeList.clear();
                    mThemeList.addAll(list);
                    mView.showActionThemeDialog();
                }
            }
        });
    }

    public List<ActivityThemeEntity> getActionThemeList() {
        return mThemeList;
    }
}
