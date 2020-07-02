package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenma.ventures.share.event.ShellEvent;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LTViewPagerAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMainMarkAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMainNodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtContentDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtFullScreenVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LTFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.functions.Consumer;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class FragmentLiTang extends BaseAreaListFragment {
    protected ToolbarLayout mToolBar;

    protected Banner mBanner;
    private LinearLayout mWrapIndication;
    private View mIndicator1, mIndicator2;
    private RecyclerView mRvMark;
    private LtMainMarkAdapter mMarkAdapter;
    private LTViewPagerAdapter mNodeAdapter;

    private boolean mbBackMain = false;
    private boolean mbHasActionBar = false;

    public static FragmentLiTang start(boolean backMain, boolean hasActionBar) {
        FragmentLiTang fragmentLiTang = new FragmentLiTang();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.KEY_BOOLEAN_1, backMain);
        bundle.putBoolean(Constant.KEY_BOOLEAN_2, hasActionBar);
        fragmentLiTang.setArguments(bundle);
        return fragmentLiTang;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_lt;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        LtMainNodeContentAdapter adapter = new LtMainNodeContentAdapter(mActivity, mPresenter.getDataList());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                NodeContent nodeContent = mPresenter.getDataList().get(position);
                String type = nodeContent.getType();
                if ("图文视频".equals(type)) {
                    if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
                        Intent intent = new Intent(mActivity, LtFullScreenVideoActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                        mActivity.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mActivity, LtContentDetailActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                        intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                        mActivity.startActivity(intent);
                    }
                } else if ("相册".equals(type)) {
                    JumpUtils.gotoPreviewImageActivity(mActivity, new ArrayList<>(Arrays.asList(nodeContent.getPath().split(","))), nodeContent.getAlbumDesc(), 0);
                } else if ("直播".equals(type)) {
                    if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                        T.showShort(mActivity, "直播空闲中");
                        return;
                    }
                    Intent intent = new Intent(mActivity, LiveActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getLiveinfo().getName());
                    intent.putExtra(Constant.KEY_STRING_2, nodeContent.getLiveinfo().getHls_pull_url());
                    mActivity.startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return adapter;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LTFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        mToolBar = mRootView.findViewById(R.id.toolbar);
        mBanner = mRootView.findViewById(R.id.banner);
        mWrapIndication = mRootView.findViewById(R.id.wrap_indication);
        mIndicator1 = mRootView.findViewById(R.id.indicatior1);
        mIndicator2 = mRootView.findViewById(R.id.indicatior2);
        ViewPager vp_node = mRootView.findViewById(R.id.vp_node);
        mNodeAdapter = new LTViewPagerAdapter(mActivity);
        vp_node.setAdapter(mNodeAdapter);
        vp_node.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mIndicator1.setBackgroundColor(Color.parseColor("#F5B622"));
                    mIndicator2.setBackgroundColor(Color.parseColor("#B0AA81"));
                } else {
                    mIndicator1.setBackgroundColor(Color.parseColor("#B0AA81"));
                    mIndicator2.setBackgroundColor(Color.parseColor("#F5B622"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mToolBar.setTitle("文化礼堂");
        if (getArguments() != null) {
            mbBackMain = getArguments().getBoolean(Constant.KEY_BOOLEAN_1, false);
            mbHasActionBar = getArguments().getBoolean(Constant.KEY_BOOLEAN_2, false);
            if (mbHasActionBar) {
                mToolBar.setVisibility(View.GONE);
            }
        }
        mToolBar.showBack();
        mToolBar.setOnClickListener(v ->
                {
                    if (mbBackMain) {
                        finishActivity();
                    } else {
                        ShellEvent shellEvent = new ShellEvent();
                        shellEvent.setType(0);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("page", 0);
                        Gson gson = new Gson();
                        shellEvent.setParams(gson.toJson(jsonObject));
                        EventBus.getDefault().post(shellEvent);
                    }
                }
                , null);

        setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        setItemDecoration(new StaggeredDividerItemDecoration(mActivity, 10, 2));
        setLoadMoreEnabled(true);
        initBanner();

        mRvMark = mRootView.findViewById(R.id.rv_mark);
        mRvMark.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mMarkAdapter = new LtMainMarkAdapter(mActivity, mPresenter.getMarkDataList(), new LtMainMarkAdapter.IMarkItemClickLisenter() {
            @Override
            public void onMarkItemClick(LtMarkEntity ltMarkEntity) {
                mPresenter.setMarkId(ltMarkEntity.getId() + "");
                mPresenter.getPageData(true);
            }
        });
        mRvMark.setAdapter(mMarkAdapter);

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UserOperateEvent.class).subscribe(new Consumer<UserOperateEvent>() {
            @Override
            public void accept(UserOperateEvent userOperateEvent) throws Exception {
                for (NodeContent content : mPresenter.getDataList()) {
                    if (content.getId().equals(userOperateEvent.getId())) {
                        content.setThumb_num(userOperateEvent.getLikeNum());
                        break;
                    }
                }
                updateList();
            }
        }));
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPageData(true);
    }

    private void initBanner() {
        mBanner.setImages(mPresenter.getDefaultImgUrls())
                .setBannerTitles(mPresenter.getDefaultImgUrls())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        ImageManager.getInstance().loadRoundCornerImage(context, path, DisplayUtils.dip2px(context, 10), imageView);
                    }
                }).setBannerAnimation(Transformer.DepthPage).setDelayTime(3000).start();
        mBanner.setOnBannerListener(position -> {
            //  轮播图点击
            if (mPresenter.getBannerDataList().size() <= 0) return;
            Intent intent = new Intent(mActivity, LtContentDetailActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, mPresenter.getBannerDataList().get(position).getId());
            intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
            intent.putExtra(Constant.KEY_STRING_3, mPresenter.getDataList().get(position).getNode_id());
            gotoActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mNodeAdapter.notifyDataSetChanged();
        mBanner.startAutoPlay();
        int bannerHeight = DisplayUtils.getScreenWidth(mActivity) / 16 * 8;
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        params.height = bannerHeight;
        mBanner.setLayoutParams(params);
        if (!Config.getInstance().getUser().isLtManager()) {
            mWrapIndication.setVisibility(View.GONE);
        } else {
            mWrapIndication.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.stopAutoPlay();
    }

    @Override
    protected void updateRegion() {
//        mToolBar.setMenuText(RegionManager.getInstance().getCurrentVillageName());
//        mPresenter.getBanner(RegionManager.getInstance().getCurrentVillageCode());
    }

    @Override
    public void updateOnlineList() {

    }

    @Override
    public void updataBanner() {
        mBanner.update(mPresenter.getBannerImageList());
    }

    @Override
    public void updateNodeList() {
    }

    @Override
    public void updateMarkList() {
        mPresenter.getMarkDataList().get(0).setSelected(true);
        mMarkAdapter.notifyDataSetChanged();
    }
}
