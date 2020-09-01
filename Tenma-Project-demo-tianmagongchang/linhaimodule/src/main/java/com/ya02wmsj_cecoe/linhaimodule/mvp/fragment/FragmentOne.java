package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenma.ventures.share.event.ShellEvent;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ApperaceMainViewAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.MainNodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WebActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FragmentOneContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.FragmentOnePresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.ya02wmsj_cecoe.linhaimodule.widget.TextSwitcherAnimation;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;
import static com.ya02wmsj_cecoe.linhaimodule.Constant.DEFAULT_REGION_CODE;
import static com.ya02wmsj_cecoe.linhaimodule.Constant.MAIN_NEW;

/**
 * Created by BenyChan on 2019-07-11.
 */
public class FragmentOne extends BaseListFragment<FragmentOneContract.Presenter> implements FragmentOneContract.View {
    protected ToolbarLayout mToolBar;
    protected Banner mBanner;
    protected RecyclerView mRvNode;
    protected TextSwitcher mTextSwitch;
    protected LinearLayout mWrapTips;
    private NodeAdapter mNodeAdapter;
    private List<String> titles = new ArrayList<>();
    private TextSwitcherAnimation animation;

    //首页活动
    protected LinearLayout mLLActivity;
    protected RecyclerView mRvActivity;
    protected ApperaceMainViewAdapter mMainViewAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_one;

    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new MainNodeContentAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new FragmentOnePresenter(this);
    }

    @Override
    protected void initView() {
        mToolBar = mRootView.findViewById(R.id.toolbar);
        mBanner = mRootView.findViewById(R.id.banner);
        mRvNode = mRootView.findViewById(R.id.rv_node);
        mTextSwitch = mRootView.findViewById(R.id.textSwitcher);
        mWrapTips = mRootView.findViewById(R.id.wrap_tips);

        mLLActivity = mRootView.findViewById(R.id.wrap_activity);
        mRvActivity = mRootView.findViewById(R.id.rv_activity);
        mMainViewAdapter = new ApperaceMainViewAdapter(mActivity, mPresenter.getAppraiseEntityList());
        mRvActivity.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvActivity.setAdapter(mMainViewAdapter);

        animation = new TextSwitcherAnimation(mTextSwitch, titles);
        mTextSwitch.setFactory(() -> {
            TextView t = new TextView(mActivity);
            t.setTextColor(ContextCompat.getColor(mActivity, R.color.yl_text_dark));
            t.setTextSize(14);
            t.setLines(2);
            t.setEllipsize(TextUtils.TruncateAt.END);
            return t;
        });

        mToolBar.setTitle("新时代文明实践中心");
        mToolBar.showBack();
        mToolBar.setOnClickListener(v -> {
            ShellEvent shellEvent = new ShellEvent();
            shellEvent.setType(0);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("page", 0);
            Gson gson = new Gson();
            shellEvent.setParams(gson.toJson(jsonObject));
            EventBus.getDefault().post(shellEvent);
        });
        setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        setItemDecoration(new StaggeredDividerItemDecoration(mActivity, 10, 2));
        initBanner();
        setLoadMoreEnabled(true);

        mRvNode.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mNodeAdapter = new NodeAdapter(mActivity, mPresenter.getNodeData());      //暂缺
        mRvNode.setAdapter(mNodeAdapter);

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UserOperateEvent.class).subscribe(userOperateEvent -> {
            for (NodeContent content : mPresenter.getDataList()) {
                if (content.getId().equals(userOperateEvent.getId())) {
                    content.setThumb_num(userOperateEvent.getLikeNum());
                    break;
                }
            }
            updateList();
        }));
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getBanner(DEFAULT_REGION_CODE);
    }

    private void initBanner() {
        mBanner.setImages(mPresenter.getDefaultImgUrls()).setBannerTitles(mPresenter.getDefaultImgUrls()).setBannerStyle(BannerConfig.NOT_INDICATOR).setIndicatorGravity(BannerConfig.CENTER).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageManager.getInstance().loadRoundCornerImage(context, path, DisplayUtils.dip2px(context, 10), imageView);
            }
        }).setBannerAnimation(Transformer.DepthPage).setDelayTime(3000).start();
        mBanner.setOnBannerListener(position -> {
            //  轮播图点击
            Intent intent = null;
            if (position == 0) {
                intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, "临海全景文明");
                intent.putExtra(Constant.KEY_STRING_2, mPresenter.getBannerContent().get(position).getContents());
            } else {
                intent = new Intent(mActivity, TextContentActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, mPresenter.getBannerContent().get(position).getId());
                intent.putExtra(Constant.KEY_STRING_2, DEFAULT_REGION_CODE);
                intent.putExtra(Constant.KEY_STRING_3, String.valueOf(MAIN_NEW));
                intent.putExtra(Constant.KEY_STRING_4, "y");
            }
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
    public void updataBanner() {
        mBanner.update(mPresenter.getBannerImageList());
    }

    @Override
    public void updateNodeList() {
        mNodeAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTips() {
        if (mPresenter.getTipsList().size() > 0) {
            mWrapTips.setVisibility(View.VISIBLE);
            animation.stop();
            titles.clear();
            for (NodeContent content : mPresenter.getTipsList()) {
                titles.add(content.getTitle());
            }
            animation.setTexts(titles);
            animation.create();
            mTextSwitch.setOnClickListener(v -> {
                NodeContent nodeContent = mPresenter.getTipsList().get(animation.getMarker());
                Intent intent = new Intent(mActivity, TextContentActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                intent.putExtra(Constant.KEY_STRING_4, "y");
                mActivity.startActivity(intent);
            });
        }
    }

    @Override
    public void updateMainActivityView() {
        if (mPresenter.getAppraiseEntityList() != null && mPresenter.getAppraiseEntityList().size() > 0) {
            mLLActivity.setVisibility(View.VISIBLE);
            mMainViewAdapter.notifyDataSetChanged();
        } else {
            mLLActivity.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animation.stop();
    }
}
