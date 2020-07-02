package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.tenma.ventures.tools.change_activity.TablayoutChange;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.OrganizeTreeActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.SearchOrganizeActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class FragmentThree extends BaseFragment {
    protected ToolbarLayout mToolbar;

    protected MapView mMapView;

    private AMap aMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mMapView.onCreate(savedInstanceState);  //该方法必须重写
        aMap = mMapView.getMap();
        return mRootView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_three;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mMapView = mRootView.findViewById(R.id.map_view);
        TextView tv_menu = mToolbar.getMenuTextView();
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.mipmap.ya02wmsj_cecoe_double_arrow_2);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_menu.setCompoundDrawables(drawable, null, null, null);
        tv_menu.setCompoundDrawablePadding(DisplayUtils.dip2px(mActivity, 5));
        mToolbar.setMenuText("图表显示");
        mToolbar.showBack();
        mToolbar.setOnClickListener(v ->
                {
                    if (getActivity() instanceof TablayoutChange) {     //显示底部Tab栏
                        ((TablayoutChange) getActivity()).showTablayout();
                    }
                    Flowable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> {
                        mActivity.runOnUiThread(() -> {
                            if (getActivity() instanceof TablayoutChange) {     //隐藏底部Tab栏
                                ((TablayoutChange) getActivity()).hideTablayout();
                            }
                        });
                    });
                }
                , v -> {
                    gotoActivity(OrganizeTreeActivity.class);
//                    Intent intent = new Intent();
//                    intent.setAction("android.intent.action.VIEW");
//                    Uri content_url = Uri.parse("https://weixin.sogou.com/weixin?type=2&s_from=input&query=lh-shaojiadu&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=1148&sst0=1564973677539&lkt=0%2C0%2C0");
//                    intent.setData(content_url);
//                    startActivity(intent);
                });

        mRootView.findViewById(R.id.et_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(SearchOrganizeActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
