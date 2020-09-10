package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.MyEmptyWrapper;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.event.EventRefresh;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.MyScrollView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


/**
 * @author Yang Shihao
 */
public abstract class BaseListActivity<P extends AListPresenter> extends BaseActivity<P> implements OnRefreshListener, OnLoadMoreListener {

    private static final String TAG = "BaseListActivity";

    SmartRefreshLayout mRefreshLayout;

    protected RecyclerView mRecyclerView;

    MyScrollView mScrollView;

    private LinearLayout mEmptyView;
    private TextView mTvEmptyView;
    private MyEmptyWrapper mAdapter;
    private MultiItemTypeAdapter mMultiItemTypeAdapter;
    protected boolean mIsRefresh = false;
    private String mNoDataText = "暂无数据";
    private String mLoadErrorText = "加载失败";

    private boolean mEnableLoreMore = true;
    private Rect mScreeneRect;
    protected LinearLayoutManager mLLManager;

    @Override
    public void onResume() {
        super.onResume();
        mDestroy = false;
        if (mIsRefresh) {
            mRefreshLayout.autoRefresh();
            mIsRefresh = false;
        }
        GSYVideoManager.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDestroy = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_base_list;
    }

    public void setLinearLayoutManager(){
        LinearLayoutManager mLLManager = new LinearLayoutManager(this);
        mLLManager.setSmoothScrollbarEnabled(true);
        mLLManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLLManager);
    }

    @Override
    protected final void initUI() {
        super.initUI();
        mRefreshLayout = findViewById(R.id.refresh);
        mRecyclerView = findViewById(R.id.rv);
        mScrollView = findViewById(R.id.scroll);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        setLinearLayoutManager();
        mMultiItemTypeAdapter = getAdapter();
        mAdapter = new MyEmptyWrapper(mMultiItemTypeAdapter);
        mEmptyView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.ya02wmsj_cecoe_view_empty, mRecyclerView, false);
        mTvEmptyView = $(mEmptyView, R.id.tv_text);
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableOverScrollDrag(true);

        mScreeneRect = new Rect();
        mScreeneRect = new Rect(0, 0, DisplayUtils.getScreenWidth(this), DisplayUtils.getScreenHeight(this));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mDestroy) {
                    return;
                }
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    Glide.with(mContext).resumeRequests();
                } else {
                    Glide.with(mContext).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mScrollView != null) return;
                videoItemScroll();
            }

        });
        if (mScrollView != null) {
            mScrollView.setScrollStateListener(new MyScrollView.ScrollStateListener() {
                @Override
                public void scrollStart() {
                    if (!mDestroy) {
                        Glide.with(mContext).pauseRequests();
                    }
                }

                @Override
                public void scrollEnd() {
                    if (!mDestroy) {
                        Glide.with(mContext).resumeRequests();
                    }
                }

                @Override
                public void scrollChanged(int t, int oldt) {
                    videoItemScroll();
                }
            });
        }
    }

    protected void videoItemScroll() {
        //大于0说明有播放
        if (GSYVideoManager.instance().getPlayPosition() >= 0) {
            //当前播放的位置
            int position = GSYVideoManager.instance().getPlayPosition();
            Log.e("BaseNodeFragment", "position----->:" + position);
            //对应的播放列表TAG
            if (!checkIsVisible(mLLManager.getChildAt(position))) {
                //如果滑出去了上面和下面就是否，和今日头条一样
                if (!GSYVideoManager.isFullState(this)) {
                    GSYVideoManager.releaseAllVideos();
                    getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    public Boolean checkIsVisible(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return view.getLocalVisibleRect(mScreeneRect);
    }

    @Override
    protected void initData() {
        mRefreshLayout.autoRefresh();
    }

    protected abstract MultiItemTypeAdapter getAdapter();

    /**
     * 注册一个RxBus
     */
    protected void registerRxBus(final int refreshLoc) {
        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(EventRefresh.class).subscribe(eventRefresh -> mIsRefresh = eventRefresh.isRefresh(refreshLoc), throwable -> {

        }));
    }


    /**
     * 是否可以下拉刷新,默认true
     */
    protected void setRefreshEnabled(boolean enable) {
        mRefreshLayout.setEnableRefresh(enable);
    }

    /**
     * 是否可以上拉加载,默认true
     */
    protected void setLoadMoreEnabled(boolean enable) {
        mEnableLoreMore = enable;
    }

    /**
     * 设置LayoutManager,默认处垂直单列
     */
    protected void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 设置默认分割线
     */
    protected void setDefaultItemDecoration() {
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRecyclerView.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
    }

    /**
     * 设置自定义分割线
     */
    protected void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 设置背景色
     */
    protected void setBackgroundColor(int color) {
        mRecyclerView.setBackgroundColor(color);
    }

    /**
     * 设置Header和Footer的颜色
     */
    protected void setRefreshThemeColor(@ColorRes int colorId) {
        mRefreshLayout.setPrimaryColorsId(colorId);
    }

    public void setNoDataText(String noDataText) {
        mNoDataText = noDataText;
    }

    public void setLoadErrorText(String loadErrorText) {
        mLoadErrorText = loadErrorText;
    }

    private void enableScroll() {
        if (mToolbarLayout == null) {
            return;
        }
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        params.setScrollFlags(0);
        mToolbarLayout.setLayoutParams(params);
    }

    private void disableScroll() {
        if (mToolbarLayout == null) {
            return;
        }
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbarLayout.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
        mToolbarLayout.setLayoutParams(params);
    }

    private void scrollControl() {
        if (mAdapter.getItemCount() == 0) {
            disableScroll();
        } else {
            enableScroll();
        }
    }

    /**
     * 更新列表
     */
    public void updateList() {
        mAdapter.notifyDataSetChanged();
        if (mAdapter.getItemRealCount() >= AListPresenter.PAGE_SIZE) {
            mRefreshLayout.setEnableLoadMore(mEnableLoreMore);
        }
    }

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        mRefreshLayout.finishRefresh(0);
    }

    /**
     * 结束加载
     */
    public void finishLoadMore(boolean isNoMoreData) {
        mRefreshLayout.finishLoadMore(0, true, isNoMoreData);
    }

    /**
     * 没有数据
     */
    public void noData() {
        finishRefresh();
        mTvEmptyView.setText(mNoDataText);
    }

    /**
     * 刷新失败
     */
    public void refreshError() {
        mRefreshLayout.finishRefresh(0, false);
        mTvEmptyView.setText(mLoadErrorText);
    }

    /**
     * 加载失败
     */
    public void loadMoreError() {
        mRefreshLayout.finishLoadMore(0, false, false);
    }

    public void insert(int positionStart, int itemCount) {
        if (mAdapter != null) {
            mAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    public void changeItem(int position) {
        if (mAdapter != null) {
            mAdapter.notifyItemChanged(position);
        }
    }

    public void changeItem(int position, String payload) {
        if (mAdapter != null) {
            mAdapter.notifyItemChanged(position, payload);
        }
    }

    public void removeItem(int position) {
        if (mAdapter != null) {
            mAdapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (mPresenter != null) {
            GSYVideoManager.releaseAllVideos();
            mPresenter.getPageData(true);
        }
    }

    @Override
    public void onLoadMore(RefreshLayout refreshlayout) {
        if (mPresenter != null) {
            mPresenter.getPageData(false);
        }
    }
}
