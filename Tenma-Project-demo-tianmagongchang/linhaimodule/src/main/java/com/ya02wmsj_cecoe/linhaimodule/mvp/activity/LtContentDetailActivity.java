package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.wx.goodview.GoodView;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtContentDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtContentDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.CoverVideo;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommentDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.ya02wmsj_cecoe.linhaimodule.Constant.BASE_URL_LT_DEBUG;

public class LtContentDetailActivity extends BaseWebViewActivity<LtContentDetailPresenter> implements LtContentDetailContract.View {
    RatioImageView mIvTop;

    protected TextView mTvTitle;

    protected TextView mTvSource;

    private ImageView mIvNextDetail;

    TextView mTvArea;

    CoverVideo mVideo;

    protected TextView tv_operate_sum;

    protected RecyclerView mRvComment;

    protected ImageButton mIbLike;

    protected ImageButton mIbCollect;

    protected LinearLayout mViewBottom;

    protected GoodView mGoodView;
    private CommentAdapter mCommentAdapter;
    private String mContentId = "";
    private String mSharedString = "";
    private String mRegionCode = "";
    private String mNodeId = "";
    private String mTop_status = "";
    private boolean hasLike, hasCollect;
    private GSYVideoOptionBuilder mGSVideoOptionBuilder;
    private NodeContent mNodeContent;
    private int mCurrentPage = 1;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_web_content;
    }

    @Override
    protected void initMVP() {
        mContentId = getIntent().getStringExtra(Constant.KEY_STRING_1);
        mRegionCode = getIntent().getStringExtra(Constant.KEY_STRING_2);
        mNodeId = getIntent().getStringExtra(Constant.KEY_STRING_3);
        mTop_status = getIntent().getStringExtra(Constant.KEY_STRING_4);
        mPresenter = new LtContentDetailPresenter(this);
        Map<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(mRegionCode)) {
            map.put("region_code", mRegionCode);
        }
        if (!TextUtils.isEmpty(mNodeId)) {
            map.put("node_id", mNodeId);
        }
        map.put("top_status", TextUtils.isEmpty(mTop_status) ? "" : mTop_status);
        map.put("is_announce", "n");
        map.put("c_id", mContentId);
        mPresenter.getLtContentDetail(map);
    }

    @Override
    protected void initView() {
        setTitle("资讯详情");
        mIvTop = findViewById(R.id.iv_top);
        mTvTitle = findViewById(R.id.tv_title);
        mTvSource = findViewById(R.id.tv_source);
        mTvArea = findViewById(R.id.tv_area);
        mVideo = findViewById(R.id.coverView);
        tv_operate_sum = findViewById(R.id.tv_operate_sum);
        mRvComment = findViewById(R.id.rv_comment);
        mIbLike = findViewById(R.id.ib_like);
        mIbCollect = findViewById(R.id.ib_collect);

        mViewBottom = findViewById(R.id.lay_bottom);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvComment.setLayoutManager(linearLayoutManager);
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRvComment.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
        mCommentAdapter = new CommentAdapter(this, mPresenter.getCommentList());
        mRvComment.setAdapter(mCommentAdapter);

        mRvComment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition == linearLayoutManager.getItemCount() - 1) {
                    // 滑动到底部
                    if (!TextUtils.isEmpty(mContentId)) {
                        mPresenter.getLtCommentByConId(mContentId, mCurrentPage);
                    }
                }
            }
        });

        mGoodView = new GoodView(this);

        findViewById(R.id.ll_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialog dialog = new CommentDialog(mContext);
                dialog.show();
                dialog.setEmptyable(false);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCommitListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.getCommentText() != null
                                && dialog.getCommentText().length() > 0) {
                            //  评论
                            mPresenter.addComment(mContentId, dialog.getCommentText());
                        }
                        dialog.dismiss();
                    }
                });
                dialog.setCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        findViewById(R.id.ib_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   收藏
                mPresenter.collect(mContentId);
            }
        });

        findViewById(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  点赞
                mPresenter.like(mContentId);
            }
        });

        findViewById(R.id.ib_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNodeContent == null) return;
                // 分享
                TMLinkShare tmLinkShare = new TMLinkShare();
                String url = BASE_URL_LT_DEBUG + "application/ya02lhwhlt_wdhaw/share/index.html?id=" + mContentId;
                tmLinkShare.setUrl(url);
                tmLinkShare.setTitle(mNodeContent.getTitle());
                tmLinkShare.setThumb(mNodeContent.getIcon_path());
                TMShareUtil.getInstance(mContext).shareLink(tmLinkShare);
            }
        });
        mIvNextDetail = findViewById(R.id.iv_floating_next_detail);

    }

    @Override
    protected void initData() {
        mContentId = getIntent().getStringExtra(Constant.KEY_STRING_1);
        mPresenter.getLtCommentByConId(getIntent().getStringExtra(Constant.KEY_STRING_1), mCurrentPage);
        mPresenter.browse(mContentId);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideo != null) {
            mVideo.onVideoPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideo != null) {
            mVideo.onVideoResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideo != null) {
            mVideo.release();
        }
    }

    @Override
    public void updateView(NodeContent nodeContent) {
        mNodeContent = nodeContent;
        mContentId = nodeContent.getId();
        mSharedString = nodeContent.getContents();
        mTvTitle.setText(nodeContent.getTitle());
        mTvSource.setText(nodeContent.getPublish_time());
        tv_operate_sum.setText("评论  （" + nodeContent.getComment_count() + "）");
        mTvArea.setText(nodeContent.getName());
        setHtml(nodeContent.getContents());
        dismissDialog();

        hasLike = mNodeContent.getThumb() == 1;      //是否已经点过赞
        hasCollect = mNodeContent.getCollect() == 1; //是否已经收藏过
        if (hasLike) {
            mIbLike.setImageResource(R.mipmap.ya02wmsj_cecoe_like_press);
        } else {
            mIbLike.setImageResource(R.mipmap.ya02wmsj_cecoe_good);
        }
        if (hasCollect) {
            mIbCollect.setImageResource(R.mipmap.ya02wmsj_cecoe_collect_press);
        } else {
            mIbCollect.setImageResource(R.mipmap.ya02wmsj_cecoe_collect);
        }

        String img = nodeContent.getIcon_path();
        if (!TextUtils.isEmpty(img)) {
            if (img.contains("http") || img.contains("HTTP")) {
                ImageManager.getInstance().loadImage(this, img, R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
            } else {
                ImageManager.getInstance().loadImage(this, Constant.getBaseUrl() + img, R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
            }
        }

        if (nodeContent.getVideo_path() != null && !TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
            mGSVideoOptionBuilder = new GSYVideoOptionBuilder();
            mVideo.setVisibility(View.VISIBLE);
            mIvTop.setVisibility(View.GONE);
            mVideo.loadCoverImage(nodeContent.getVideo_path().getSnapshotUrl(), R.color.black);
            //增加title
            mVideo.getTitleTextView().setText(nodeContent.getVideo_path().getVideoName());
            mVideo.getTitleTextView().setVisibility(View.VISIBLE);

            //设置返回键
            mVideo.getBackButton().setVisibility(View.GONE);
            mGSVideoOptionBuilder
                    .setIsTouchWiget(false)
                    .setSetUpLazy(true)//lazy可以防止滑动卡顿
                    .setVideoTitle(nodeContent.getVideo_path().getVideoName())
                    .setCacheWithPlay(false)
                    .setRotateViewAuto(false)
                    .setLockLand(true)
                    .setPlayTag("SimpleVideoActivity")
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setVideoAllCallBack(new GSYSampleCallBack() {
                        @Override
                        public void onEnterFullscreen(String url, Object... objects) {
                            super.onEnterFullscreen(url, objects);
                            mVideo.getBackButton().setVisibility(View.VISIBLE);
                            mVideo.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                        }

                        @Override
                        public void onQuitFullscreen(String url, Object... objects) {
                            super.onQuitFullscreen(url, objects);
                            mVideo.getBackButton().setVisibility(View.VISIBLE);
                        }
                    })
                    .setUrl(nodeContent.getVideo_path().getOrigUrl())
                    .build(mVideo);
            mVideo.setUp(nodeContent.getVideo_path().getOrigUrl(), false, "");
            mVideo.startPlayLogic();

            mVideo.getStartButton().setVisibility(View.GONE);
            mVideo.doubleTouch();

//            mVideo.getStartButton().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    boolean inPlayingState = mVideo.isInPlayingState();
//                    if (inPlayingState) {
//                        mVideo.doubleTouch();
//                    } else {
//                        // 测试地址
////                        String playUrl = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
//                        view.setClickable(false);
//                        if (!TextUtils.isEmpty(nodeContent.getVideo_path().getOrigUrl())) {
//                            mGSVideoOptionBuilder.setVideoTitle(nodeContent.getVideo_path().getVideoName());
//                            mGSVideoOptionBuilder.setUrl(nodeContent.getVideo_path().getOrigUrl());
//                            mGSVideoOptionBuilder.build(mVideo);
//                            mVideo.startBtnPlay();
//                            view.setClickable(true);
//                        }
//                    }
//
//                }
//            });

            mVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVideo.onVideoPause();
                    Intent intent = new Intent(mContext, LtFullScreenVideoActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                    intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                    intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                    intent.putExtra(Constant.KEY_INT_1, mVideo.getCurrentPositionWhenPlaying());
                    gotoActivity(intent);
                }
            });
        }
    }

    @Override
    public void commentSuc() {

    }

    @Override
    public void loadCommentSuc() {
        mCurrentPage++;
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void likeSuc() {
        hasLike = !hasLike;
        int[] colors = {R.color.yl_red, R.color.blue, R.color.yl_orange, R.color.yl_main_green, R.color.yl_pool_blue, R.color.yl_end_patrol};
        int index = new Random().nextInt(colors.length);
        UserOperateEvent event = new UserOperateEvent();
        event.setId(mNodeContent.getId());
        if (hasLike) {
            mGoodView.setTextInfo("+1", ContextCompat.getColor(mContext, colors[index]), 16);
            mIbLike.setImageResource(R.mipmap.ya02wmsj_cecoe_like_press);
            event.setLikeNum(mNodeContent.getThumb_num() + 1);
        } else {
            mGoodView.setTextInfo("-1", ContextCompat.getColor(mContext, colors[index]), 16);
            mIbLike.setImageResource(R.mipmap.ya02wmsj_cecoe_good);
            event.setLikeNum(mNodeContent.getThumb_num() - 1);
        }
        mGoodView.show(mIbLike);
        RxBus.getInstance().send(event);
    }

    @Override
    public void collectSuc() {
        hasCollect = !hasCollect;
        int[] colors = {R.color.yl_red, R.color.blue, R.color.yl_orange, R.color.yl_main_green, R.color.yl_pool_blue, R.color.yl_end_patrol};
        int index = new Random().nextInt(colors.length);
        if (hasCollect) {
            mGoodView.setTextInfo("+1", ContextCompat.getColor(mContext, colors[index]), 16);
            mIbCollect.setImageResource(R.mipmap.ya02wmsj_cecoe_collect_press);
        } else {
            mGoodView.setTextInfo("-1", ContextCompat.getColor(mContext, colors[index]), 16);
            mIbCollect.setImageResource(R.mipmap.ya02wmsj_cecoe_collect);
        }
        mGoodView.show(mIbCollect);
    }

    private void gotoLastContent(NodeContent currentNodeContent) {
        Intent intent = new Intent(mContext, TextContentActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, currentNodeContent.getPrev().getId());
        intent.putExtra(Constant.KEY_STRING_2, mRegionCode);
        intent.putExtra(Constant.KEY_STRING_3, mNodeId);
        gotoActivity(intent);
        finish();
    }
    private void gotoNextContent(NodeContent currentNodeContent){
        Intent intent = new Intent(mContext, TextContentActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, currentNodeContent.getNext().getId());
        intent.putExtra(Constant.KEY_STRING_2, mRegionCode);
        intent.putExtra(Constant.KEY_STRING_3, mNodeId);
        gotoActivity(intent);
        finish();
    }
}
