package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.wx.goodview.GoodView;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TextContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TextContentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.CoverVideo;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommentDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.reactivex.functions.Consumer;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class TextContentActivity extends BaseWebViewActivity<TextContentContract.Presenter> implements TextContentContract.View {
    RatioImageView mIvTop;

    protected TextView mTvTitle;

    protected TextView mTvSource;

    TextView mTvArea;

    CoverVideo mVideo;

    protected TextView mTvLastContent, mTvNextContent;

    protected TextView tv_operate_sum;

    protected RecyclerView mRvComment;

    protected ImageButton mIbLike;

    protected ImageButton mIbCollect;

    protected GoodView mGoodView;
    private CommentAdapter mCommentAdapter;
    private String mContentId = "";
    private String mRegionCode = "";
    private String mTop_status = "";
    private String mNodeId = "";
    private boolean hasLike, hasCollect;
    private NodeContent mNodeContent;


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
        mPresenter = new TextContentPresenter(this);
        Map<String, Object> map = new HashMap<>();
        map.put("region_code", mRegionCode);
        map.put("is_announce", "n");
        map.put("top_status", TextUtils.isEmpty(mTop_status) ? "" : mTop_status);
        map.put("node_id", mNodeId);
        map.put("c_id", mContentId);
        mPresenter.getContentDetail(map);
    }

    @Override
    protected void initView() {
        setTitle("资讯详情");
        mIvTop = findViewById(R.id.iv_top);
        mTvTitle = findViewById(R.id.tv_title);
        mTvLastContent = findViewById(R.id.tv_last_content);
        mTvNextContent = findViewById(R.id.tv_next_content);
        mTvSource = findViewById(R.id.tv_source);
        mTvArea = findViewById(R.id.tv_area);
        mVideo = findViewById(R.id.coverView);
        tv_operate_sum = findViewById(R.id.tv_operate_sum);
        mRvComment = findViewById(R.id.rv_comment);
        mIbLike = findViewById(R.id.ib_like);
        mIbCollect = findViewById(R.id.ib_collect);

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
                        if (mContentId.isEmpty()) return;
                        if (dialog.getCommentText() != null
                                && dialog.getCommentText().length() > 0) {
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
                //  点赞
                if (mContentId.isEmpty()) return;
                mPresenter.collect(mContentId);
            }
        });

        findViewById(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点赞
                if (mContentId.isEmpty()) return;
                mPresenter.like(mContentId);
            }
        });

        findViewById(R.id.ib_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享
                if (mContentId.isEmpty() && mNodeContent == null) return;
                TMLinkShare tmLinkShare = new TMLinkShare();
                String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/share/index.html?id=" + mContentId;
                tmLinkShare.setUrl(url);
                tmLinkShare.setTitle(mNodeContent.getTitle());
                tmLinkShare.setThumb(mNodeContent.getIcon_path());
                TMShareUtil.getInstance(mContext).shareLink(tmLinkShare);
            }
        });
    }

    @Override
    public void onMenuClicked() {
        Intent intent = new Intent(this, PublishOpinionActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, mNodeId);
        intent.putExtra(Constant.KEY_STRING_2, "内容");
        startActivity(intent);
    }

    @Override
    protected void initData() {
        mPresenter.browse(mContentId);
    }

    @Override
    public void updateComment() {
        int count = mPresenter.getCommentList().size();
        tv_operate_sum.setText("评论（" + count + "）");
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void addCommentSuc(String count) {
        tv_operate_sum.setText("评论（" + count + "）");
        mPresenter.getCommentById(mContentId);  //重新请求评论
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

    @Override
    public void updateView(NodeContent nodeContent) {
        mNodeContent = nodeContent;
        if (mNodeContent.getNode_id().equals("21") || mNodeContent.getNode_id().equals("22") || mNodeContent.getNode_id().equals("23")) {
            setMenuText("拍同款");
            mNodeId = mNodeContent.getNode_id();
        }
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

        mTvTitle.setText(mNodeContent.getTitle());
        mTvSource.setText(mNodeContent.getPublish_time());
        mTvArea.setText(mNodeContent.getName());
        String img = mNodeContent.getIcon_path();
        if (!TextUtils.isEmpty(img)) {
            if (img.contains("http") || img.contains("HTTP")) {
                ImageManager.getInstance().loadImage(this, img, R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
            } else {
                ImageManager.getInstance().loadImage(this, Constant.getBaseUrl() + img, R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
            }
        }
        if (mNodeContent.getVideo_path() != null && !TextUtils.isEmpty(mNodeContent.getVideo_path().getOrigUrl())) {
            mVideo.setVisibility(View.VISIBLE);
            mIvTop.setVisibility(View.GONE);
            mVideo.loadCoverImage(mNodeContent.getVideo_path().getSnapshotUrl(), R.color.black);
            mVideo.getTitleTextView().setVisibility(View.GONE);

            //设置返回键
            mVideo.getBackButton().setVisibility(View.GONE);
            mVideo.setUp(mNodeContent.getVideo_path().getOrigUrl(), false, "");
            mVideo.startPlayLogic();

            mVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FullScreenVideoActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                    intent.putExtra(Constant.KEY_STRING_2, mRegionCode);
                    intent.putExtra(Constant.KEY_STRING_3, mNodeId);
                    intent.putExtra(Constant.KEY_INT_1, mVideo.getCurrentPositionWhenPlaying());
                    gotoActivity(intent);
                }
            });
        }

        setHtml(mNodeContent.getContents());
        dismissDialog();
        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRvComment.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
        mCommentAdapter = new CommentAdapter(this, mPresenter.getCommentList());
        mRvComment.setAdapter(mCommentAdapter);
        mPresenter.getCommentById(mContentId);

        if (nodeContent.getPrev() != null && !TextUtils.isEmpty(nodeContent.getPrev().getId())) {
            mTvLastContent.setText(Html.fromHtml("<u>" + "上一条：" + nodeContent.getPrev().getTitle() + "</u>"));
            mTvLastContent.setTag(nodeContent.getPrev().getId());
            mTvLastContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(mContext, TextContentActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getPrev().getId());
                    intent.putExtra(Constant.KEY_STRING_2, mRegionCode);
                    intent.putExtra(Constant.KEY_STRING_3, mNodeId);
                    gotoActivity(intent);
                    finish();*/
                    gotoLastContent(nodeContent);
                }
            });
        }
        if (nodeContent.getNext() != null && !TextUtils.isEmpty(nodeContent.getNext().getId())) {
            mTvNextContent.setText(Html.fromHtml("<u>" + "下一条：" + nodeContent.getNext().getTitle() + "</u>"));
            mTvNextContent.setTag(nodeContent.getNext().getId());
            mTvNextContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent intent = new Intent(mContext, TextContentActivity.class);
                    intent.putExtra(Constant.KEY_STRING_1, nodeContent.getNext().getId());
                    intent.putExtra(Constant.KEY_STRING_2, mRegionCode);
                    intent.putExtra(Constant.KEY_STRING_3, mNodeId);
                    gotoActivity(intent);
                    finish();*/
                   gotoNextContent(nodeContent);
                }
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
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
