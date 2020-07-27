package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseLittleVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.VideoPath;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LittleVideoContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LittleVideoPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

import static com.ya02wmsj_cecoe.linhaimodule.utils.TDevice.getScreenWidth;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 5:31 PM
 * desc   : EMPTY
 * ================================================
 */
public class LittleVideoActivity extends BaseLittleVideoActivity<LittleVideoContract.Presenter> implements LittleVideoContract.View {
    private int mLastStopPosition;

    public static void launch(Context context, String contentId, String regionCode, String nodeId) {
        context.startActivity(new Intent(context, LittleVideoActivity.class)
                .putExtra(Constant.KEY_STRING_1, contentId)
                .putExtra(Constant.KEY_STRING_2, regionCode)
                .putExtra(Constant.KEY_STRING_3, nodeId));
    }

    /**
     * 预加载条目数
     */
    private static final int DEFAULT_PRELOAD_NUMBER = 5;
    /**
     * 是否正在加载数据
     */
    private boolean isLoadingData = false;

    /**
     * 是否加载完毕
     */
    private boolean isEnd;

    private boolean isNoMoreData = false;

    @Override
    protected void initMVP() {
        mPresenter = new LittleVideoPresenter(this, getIntent());
    }

    @Override
    protected void initView() {
        mPresenter.getAdapter().setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                if (id == R.id.tv_like) {
                    likeClicked((TextView) view, position);
                } else if (id == R.id.tv_collect) {
                    collectClicked((TextView) view, position);
                } else if (id == R.id.tv_share) {
                    shareClicked((TextView) view, position);
                } else if (id == R.id.tv_comment) {
                    commentClicked((TextView) view,position);
                }
            }
        });
    }


    @Override
    protected void initData() {
        mPresenter.getAdapter().bindToRecyclerView(mRecyclerView);
        mPresenter.refresh();
    }

    @Override
    public void onPageInitComplete() {
        int position = mPagerLayoutManager.findFirstVisibleItemPosition();
        if (position != -1) {
            mCurrentPosition = position;
        }

        // 预加载，请求数据内容
        int itemCount = mPresenter.getAdapter().getItemCount();
        if (itemCount - position < DEFAULT_PRELOAD_NUMBER && !isLoadingData && !isEnd) {
//            requestNewData();
            mPresenter.loadMore();
        }

        startPlay(mCurrentPosition);

        mLastStopPosition = -1;
    }

    @Override
    public void onPageRelease(int position, boolean isNext) {
        if (mCurrentPosition == position) {
            mLastStopPosition = position;
            stopPlay();
            BaseViewHolder viewHolder = (BaseViewHolder) mRecyclerView.findViewHolderForLayoutPosition(mCurrentPosition);
            if (viewHolder != null) {
                ImageView mVideoThumb = viewHolder.getView(R.id.iv_thumb_item);
                if (mVideoThumb != null) {
                    mVideoThumb.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position, boolean isLast) {
        if (mCurrentPosition == position && mLastStopPosition != position) {
            return;
        }
        // 预加载，请求数据内容
        int itemCount = mPresenter.getAdapter().getItemCount();
        if (!isNoMoreData && itemCount - position < DEFAULT_PRELOAD_NUMBER && !isLoadingData) {
            // 正在加载中, 防止网络太慢或其他情况造成重复请求列表
//            isLoadMoreData = true;
            isLoadingData = true;
            mPresenter.loadMore();
        }
        //如果是最后一条，并且无网络请求,并且无数据
        if (itemCount == position + 1 && !isLoadingData && isNoMoreData) {
//            Toast.makeText(MainActivity.this, "No more video.", Toast.LENGTH_SHORT).show();
        }
        startPlay(position);
        mCurrentPosition = position;
    }

    /**
     * 开始播放视频内容，进行播放器视图加载
     */
    private void startPlay(int position) {
        if (position < 0 || position >= mPresenter.getAdapter().getData().size()) {
            return;
        }
        FrameLayout mVideoContent = (FrameLayout) mPresenter.getAdapter().getViewByPosition(position, R.id.fl_content_item);
        ViewParent parent = mVideoView.getParent();
        if (parent instanceof FrameLayout) {
            ((ViewGroup) parent).removeView(mVideoView);
        }
        if (mVideoContent != null) {
            mVideoContent.addView(mVideoView, 0);
            VideoPath video_path = mPresenter.getData().get(position).getVideo_path();
            ImageView ivThumb = mVideoView.getIvThumb();
            String snapshotUrl = video_path.getSnapshotUrl();
            if(!TextUtils.isEmpty(snapshotUrl)){
                ImageManager.getInstance().loadImage(this,snapshotUrl,ivThumb);
            }
            mGsySmallVideoHelperBuilder.setLooping(true);
            mGsySmallVideoHelperBuilder.setUrl(video_path.getOrigUrl());
            mGsySmallVideoHelperBuilder.build(mVideoView);
            mVideoView.startPlayLogic();
        }
    }

    /**
     * 停止播放，移除视图
     */
    private void stopPlay() {
        mVideoView.release();
        ViewParent parent = mVideoView.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(mVideoView);
        }
    }

    private void likeClicked(TextView view, int position) {
        view.setClickable(false);
        NodeContent nodeContent = mPresenter.getData().get(position);
        mPresenter.like(nodeContent.getId(), view,position);
    }

    private void collectClicked(TextView view, int position) {
        view.setClickable(false);
        NodeContent nodeContent = mPresenter.getData().get(position);
        mPresenter.collect(nodeContent.getId(), view,position);
    }

    private void shareClicked(TextView tvShare, int position) {
        NodeContent nodeContent = mPresenter.getData().get(position);
        // 分享
        TMLinkShare tmLinkShare = new TMLinkShare();
        String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/share/index.html?id=" + nodeContent.getId();
        tmLinkShare.setUrl(url);
        tmLinkShare.setTitle(nodeContent.getTitle());
        tmLinkShare.setThumb(nodeContent.getIcon_path());
        TMShareUtil.getInstance(mContext).shareLink(tmLinkShare, new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                toast("分享成功");
                tvShare.setText(nodeContent.getShare_num() + 1 + "");

//                mPresenter.share(mPresenter.getContentId());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
//                        toast("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
//                        toast("分享取消");
            }
        });
    }
    private Dialog mCommentDialog;

    private void dismissCommentDialog() {
        if (mCommentDialog != null) {
            mCommentDialog.dismiss();
            mCommentDialog = null;
        }
    }

    private void commentClicked(TextView tvComment, int position) {
        // TODO: 2020/7/22
        NodeContent nodeContent = mPresenter.getData().get(position);

       /* if (mCommentDialog != null) {
            mCommentDialog.show();
            return;
        }*/
        mCommentDialog = new Dialog(this, R.style.BottomDialogStyle);
        mCommentDialog.setCanceledOnTouchOutside(true);
        mCommentDialog.setCancelable(true);
        mCommentDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mCommentDialog != null) {
                    mCommentDialog.dismiss();
                    mCommentDialog = null;
                }
            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_dialog_video_comment, null);
        ProgressBar progressBar = view.findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        RecyclerView rv_comment = view.findViewById(R.id.rv_comment);
        rv_comment.setLayoutManager(new LinearLayoutManager(this));
        CommentAdapter adapter = new CommentAdapter(this, mPresenter.getCommentList());
        rv_comment.setAdapter(adapter);
        EditText et_content = view.findViewById(R.id.et_content);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_content.getText())) {
                    mPresenter.addComment(nodeContent.getId(), et_content.getText().toString(),tvComment);
                }
            }
        });

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissCommentDialog();
            }
        });

        mCommentDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = mCommentDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        mCommentDialog.show();
        mPresenter.getCommentById(nodeContent.getId(),adapter,progressBar);
    }

    private void updateLike(boolean hasLike, TextView tvLike) {
        if (hasLike) {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_like_press, 0, 0, 0);
        } else {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_good, 0, 0, 0);
        }
        tvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
    }

    private void updateCollect(boolean hasCollect, TextView tvCollect) {
        if (hasCollect) {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect_press, 0, 0, 0);
        } else {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect, 0, 0, 0);
        }
        tvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
    }


    @Override
    public void changeLikeState(boolean isSucceed, TextView tvLike, int position) {
        if (isSucceed) {
            NodeContent nodeContent = mPresenter.getData().get(position);
            tvLike.setText(String.valueOf(nodeContent.getThumb_num()));
            updateLike(mPresenter.isHasLike(nodeContent), tvLike);
        } else {

        }
        tvLike.setClickable(true);
    }

    @Override
    public void changeCollectState(boolean isSucceed, TextView tvCollect, int position) {
        if (isSucceed) {
            NodeContent nodeContent = mPresenter.getData().get(position);
            tvCollect.setText(String.valueOf(nodeContent.getCollect_num()));
            updateCollect(mPresenter.isHasCollect(nodeContent), tvCollect);
        } else {

        }
        tvCollect.setClickable(true);
    }

    @Override
    public void changeShareState(boolean isSucceed) {
        if (isSucceed) {

        } else {

        }
    }

    @Override
    public void refreshSucceed() {
        isLoadingData = false;
    }

    @Override
    public void refreshError() {
        isLoadingData = false;
    }

    @Override
    public void loadMoreError() {
        isLoadingData = false;
    }

    @Override
    public void loadMoreSucceed() {
        isLoadingData = false;
    }

    @Override
    public void noMoreData(boolean isRefresh) {
//        T.showShort(this,"已经到底了!");
        isNoMoreData = true;
    }

    @Override
    public void commentSuc(String count, TextView tvComment) {
        tvComment.setText(count);
        dismissCommentDialog();
    }
}
