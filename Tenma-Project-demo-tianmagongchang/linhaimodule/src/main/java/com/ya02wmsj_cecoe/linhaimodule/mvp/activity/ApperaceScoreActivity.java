package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenma.ventures.share.bean.TMTextShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ApperaceScoreAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppreaceScoreContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppreaceScorePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.HtmlUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommentDialog;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

/**
 * 我的评议-评分
 */
public class ApperaceScoreActivity extends BaseActivity<AppreaceScoreContract.Presenter> implements AppreaceScoreContract.View {
    private ImageView mIvIcon;
    private TextView mTvTitle, mTvInnerTitle, mTvTime, mTvCommentTop;
    private ExpandableTextView mTvContent;
    private RecyclerView mRvVote, mRvComment;

    protected ImageButton mIbLike;

    protected ImageButton mIbCollect;
    private AppraiseEntity mEntity;
    private CommentAdapter mCommentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_apperace_score;
    }

    @Override
    protected void initMVP() {
        mEntity = (AppraiseEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new AppreaceScorePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我的评议");
        mIvIcon = findViewById(R.id.iv_icon);
        mTvTitle = findViewById(R.id.tv_title);
        mTvInnerTitle = findViewById(R.id.tv_title_inner);
        mTvTime = findViewById(R.id.tv_time);
        mTvContent = findViewById(R.id.tv_content);
        mTvCommentTop = findViewById(R.id.tv_comment_top);
        mRvVote = findViewById(R.id.rv_vote);
        mRvComment = findViewById(R.id.rv_comment);

        mIbLike = findViewById(R.id.ib_like);
        mIbCollect = findViewById(R.id.ib_collect);

        ImageManager.getInstance().loadCircleImage(this, mEntity.getIcon_path(), R.mipmap.ya02wmsj_cecoe_head, mIvIcon);
        mTvTitle.setText(mEntity.getName());
        mTvTime.setText(mEntity.getStatus() + "     " + mEntity.getCtime());
        mTvInnerTitle.setText("发布" + mEntity.getName());
        mTvContent.setText(HtmlUtil.getTextFromHtml(mEntity.getContent()));
        mTvCommentTop.setText("评论（0）");

        mRvVote.setLayoutManager(new LinearLayoutManager(this));
        ApperaceScoreAdapter voteAdapter = new ApperaceScoreAdapter(mContext, mEntity.getVoteInfo(), !mEntity.getParticipate());
        mRvVote.setAdapter(voteAdapter);

        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRvComment.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
        mCommentAdapter = new CommentAdapter(this, mPresenter.getCommentList());
        mRvComment.setAdapter(mCommentAdapter);

        if (mEntity.getParticipate()) {
            findViewById(R.id.btn_commit).setVisibility(View.GONE);     //已经参与评分则隐藏提交按钮
        }
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提交
                if (mEntity.getStatus().equals("已结束")) {
                    toast("活动已结束");
                    return;
                }
                StringBuilder sbVoteId = new StringBuilder();
                StringBuilder sbVoteScore = new StringBuilder();
                for (VoteEntity entity : mEntity.getVoteInfo()) {
                    if (entity.getMy_score() == 0) {
                        toast("还有未完成的评分");
                        return;
                    }
                    sbVoteId.append(entity.getId()).append(",");
                    sbVoteScore.append(entity.getMy_score()).append(",");
                }
                mPresenter.commit(mEntity.getId(), sbVoteId.toString(), sbVoteScore.toString());
            }
        });

        findViewById(R.id.ll_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialog dialog = new CommentDialog(mContext);
                dialog.show();
                dialog.setEmptyable(false);
                dialog.setCommitListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog.getCommentText() != null
                                && dialog.getCommentText().length() > 0) {
                            mPresenter.addComment(mEntity.getId(), dialog.getCommentText());
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
                mPresenter.collect(mEntity.getId());
            }
        });

        findViewById(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点赞
                mPresenter.like(mEntity.getId());
            }
        });

        findViewById(R.id.ib_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享
                TMTextShare share = new TMTextShare();
                share.setContent(mEntity.getContent());
                TMShareUtil.getInstance(mContext).shareText(share);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getComment(mEntity.getId());
    }

    @Override
    public void updateComment() {
        mTvCommentTop.setText("评论（" + mPresenter.getCommentList().size() + "）");
        mCommentAdapter.notifyDataSetChanged();
    }
}
