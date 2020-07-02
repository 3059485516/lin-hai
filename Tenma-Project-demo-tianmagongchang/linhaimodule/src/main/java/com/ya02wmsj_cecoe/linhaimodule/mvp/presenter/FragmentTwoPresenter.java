package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentTwoContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

import static com.ya02wmsj_cecoe.linhaimodule.Constant.PLATFORM;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class FragmentTwoPresenter extends FragmentTwoContract.Presenter {
    public FragmentTwoPresenter(FragmentTwoContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(Constant.DEFAULT_REGION_CODE, "n","n",
                String.valueOf(PLATFORM), String.valueOf(getPage()), String.valueOf(PAGE_SIZE)));
    }
}
