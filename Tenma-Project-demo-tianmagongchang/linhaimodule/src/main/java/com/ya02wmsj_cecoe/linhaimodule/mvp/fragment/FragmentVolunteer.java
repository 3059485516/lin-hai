package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenma.ventures.share.event.ShellEvent;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.FragmentVolunteerAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ZhiYuanFuWuViewPageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LittleVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentVolunteerContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.FragmentVolunteerPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

public class FragmentVolunteer extends BaseListFragment<FragmentVolunteerContract.Presenter> implements FragmentVolunteerContract.View {
    protected ToolbarLayout mToolBar;
    protected Banner mBanner;
    protected RecyclerView mRvNode;
    private NodeAdapter mNodeAdapter;

    private boolean mbBackMain = false;
    private boolean mbHasActionBar = false;

    public static FragmentVolunteer start(boolean backMain, boolean hasActionBar) {
        FragmentVolunteer fragmentLiTang = new FragmentVolunteer();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.KEY_BOOLEAN_1, backMain);
        bundle.putBoolean(Constant.KEY_BOOLEAN_2, hasActionBar);
        fragmentLiTang.setArguments(bundle);
        return fragmentLiTang;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_volunteer;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        FragmentVolunteerAdapter adapter = new FragmentVolunteerAdapter(mActivity, mPresenter.getDataList());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                NodeContent nodeContent = mPresenter.getDataList().get(position);
                if ("直播".equals(nodeContent.getType())) {
                    mPresenter.clickContent();
                    if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                        T.showShort(getContext(), "直播空闲中");
                        return;
                    } else {
                        Intent intent = new Intent(getContext(), LiveActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getLiveinfo().getName());
                        intent.putExtra(Constant.KEY_STRING_2, nodeContent.getLiveinfo().getHls_pull_url());
                        startActivity(intent);
                    }
                } else if ("图文视频".equals(nodeContent.getType())) {
                    mPresenter.clickContent();
                    Intent intent = null;
                    if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
                        LittleVideoActivity.launch(getContext(),nodeContent.getId(), RegionManager.getInstance().getCurrentCountyCode(),"30");
                    } else {
                        intent = new Intent(getContext(), TextContentActivity.class);
                        intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                        intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                        intent.putExtra(Constant.KEY_STRING_3, "30");
                    }
                    if (intent != null)
                        getContext().startActivity(intent);
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
        mPresenter = new FragmentVolunteerPresenter(this);
    }

    @Override
    protected void initView() {
        mToolBar = mRootView.findViewById(R.id.toolbar);
        mBanner = mRootView.findViewById(R.id.banner);
        mRvNode = mRootView.findViewById(R.id.rv_node);
        ViewPager viewPager = mRootView.findViewById(R.id.vp_node);
        View viewPagerIndication = mRootView.findViewById(R.id.wrap_indication);
        mToolBar.setTitle("志愿服务");
        if (getArguments() != null) {
            mbBackMain = getArguments().getBoolean(Constant.KEY_BOOLEAN_1, false);
            mbHasActionBar = getArguments().getBoolean(Constant.KEY_BOOLEAN_2, false);
            if (mbHasActionBar) {
                mToolBar.setVisibility(View.GONE);
            }
        }
        mToolBar.showBack();
        mToolBar.setOnClickListener(v -> {
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
        }, null);
        setLayoutManager(new LinearLayoutManager(mActivity));
        initBanner();
        setLoadMoreEnabled(true);
        List<Node> nodeList = getNodeList();
        if (nodeList.size() > 8) {
            mRvNode.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
            viewPagerIndication.setVisibility(View.VISIBLE);
            View indicator1, indicator2;
            indicator1 = mRootView.findViewById(R.id.indicatior1);
            indicator2 = mRootView.findViewById(R.id.indicatior2);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        indicator1.setBackgroundColor(Color.parseColor("#F5B622"));
                        indicator2.setBackgroundColor(Color.parseColor("#B0AA81"));
                    } else {
                        indicator1.setBackgroundColor(Color.parseColor("#B0AA81"));
                        indicator2.setBackgroundColor(Color.parseColor("#F5B622"));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            viewPager.setAdapter(new ZhiYuanFuWuViewPageAdapter(nodeList));
        } else {
            viewPager.setVisibility(View.GONE);
            viewPagerIndication.setVisibility(View.GONE);
            mRvNode.setVisibility(View.VISIBLE);
            mRvNode.setLayoutManager(new GridLayoutManager(mActivity, 4));
            mNodeAdapter = new NodeAdapter(mActivity, nodeList);
            mRvNode.setAdapter(mNodeAdapter);
        }

        mNodeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position != 1 || position != 4 || position != 6 || position != 8){
                    mPresenter.clickContent();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mPresenter.clickContent();
    }

    private List<Node> getNodeList() {
        Node node1 = new Node();
        node1.setTitle("我要做志愿");
        node1.setLocal(true);
        node1.setIcon(R.mipmap.ya02wmsj_cecoe_worth_1 + "");

        Node node2 = new Node();
        node2.setTitle("我要点服务");
        node2.setLocal(true);
        node2.setIcon(R.mipmap.ya02wmsj_cecoe_net_1_2 + "");

        Node node3 = new Node();
        node3.setTitle("我要兑福利");
        node3.setLocal(true);
        node3.setIcon(R.mipmap.ya02wmsj_cecoe_worth_5 + "");

        Node node4 = new Node();
        node4.setTitle("我要看排名");
        node4.setLocal(true);
        node4.setIcon(R.mipmap.ya02wmsj_cecoe_ideology_1 + "");

        Node node5 = new Node();
        node5.setTitle("我有微心愿");
        node5.setLocal(true);
        node5.setIcon(R.mipmap.ya02wmsj_cecoe_main_3 + "");

        Node node6 = new Node();
        node6.setTitle("我要加团队");
        node6.setLocal(true);
        node6.setIcon(R.mipmap.ya02wmsj_cecoe_net_1_7 + "");

        Node node7 = new Node();
        node7.setTitle("我要秀文明");
        node7.setLocal(true);
        node7.setIcon(R.mipmap.ya02wmsj_cecoe_main_2 + "");

        Node node8 = new Node();
        node8.setTitle("我要签到");
        node8.setLocal(true);
        node8.setIcon(R.mipmap.ya02wmsj_cecoe_icon_wode_en + "");

        Node node9 = new Node();
        node9.setTitle("我要拍陋习");
        node9.setLocal(true);
        node9.setIcon(R.mipmap.ya02wmsj_cecoe_lou_xi + "");
        return Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9);
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
            Intent intent = new Intent(mActivity, TextContentActivity.class);
            intent.putExtra(Constant.KEY_STRING_1, mPresenter.getBannerContent().get(position).getId());
            intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
            intent.putExtra(Constant.KEY_STRING_3, "30");
            intent.putExtra(Constant.KEY_STRING_4, "y");
            mActivity.startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
        int bannerHeight = DisplayUtils.getScreenWidth(mActivity) / 16 * 8;
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        params.height = bannerHeight;
        mBanner.setLayoutParams(params);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.stopAutoPlay();
    }

    @Override
    public void updateBanner() {
        mBanner.update(mPresenter.getBannerUrls());
    }
}
