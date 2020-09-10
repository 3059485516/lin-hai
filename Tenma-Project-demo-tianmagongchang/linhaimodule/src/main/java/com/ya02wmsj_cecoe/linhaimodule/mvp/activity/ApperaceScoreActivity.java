package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.bean.TMTextShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ApperaceScoreAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ScoreInfo;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppreaceScoreContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.AppreaceScorePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.HtmlUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommentDialog;

import java.util.List;

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
    public void onMenuClicked() {
        //分享链接
        TMLinkShare tmLinkShare = new TMLinkShare();
        String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/share/index.html?id=" + mEntity.getId();
        tmLinkShare.setUrl(url);
        tmLinkShare.setTitle(mEntity.getTitle());
        tmLinkShare.setThumb(mEntity.getIcon_path());
        TMShareUtil.getInstance(mContext).shareLink(tmLinkShare);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setTitle("我的评议");
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_icon_fx_white);
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
        ApperaceScoreAdapter voteAdapter = new ApperaceScoreAdapter(mContext, mEntity.getScoreInfo(), !mEntity.getParticipate());
        mRvVote.setAdapter(voteAdapter);

        mRvComment.setLayoutManager(new LinearLayoutManager(this));
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRvComment.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));
        mCommentAdapter = new CommentAdapter(this, mPresenter.getCommentList());
        mRvComment.setAdapter(mCommentAdapter);

        if (mEntity.getParticipate()) {
            findViewById(R.id.btn_commit).setVisibility(View.GONE);
            //已经参与评分则隐藏提交按钮
        }
        findViewById(R.id.btn_commit).setOnClickListener(v -> {
            // 提交
            if (mEntity.getStatus().equals("已结束")) {
                toast("活动已结束");
                return;
            }
            StringBuilder sbScoreIds = new StringBuilder();
            StringBuilder sbOptionIds = new StringBuilder();
            StringBuilder sbScores = new StringBuilder();
            List<ScoreInfo> scoreInfoList = mEntity.getScoreInfo();
            if (scoreInfoList != null && scoreInfoList.size() > 0) {
                for (int i = 0; i < scoreInfoList.size(); i++) {
                    ScoreInfo scoreInfo = scoreInfoList.get(i);
                    if (i == 0) {
                        sbScoreIds.append(scoreInfo.getId());
                    } else {
                        sbScoreIds.append("#").append(scoreInfo.getId());
                        sbScores.append("#");
                    }
                    List<ScoreInfo.Options> optionsList = scoreInfo.getOptions();
                    for (int j = 0; j < optionsList.size(); j++) {
                        ScoreInfo.Options options = optionsList.get(j);
                        if (options.getMy_score() == 0) {
                            toast("还有未完成的评分");
                            return;
                        }
                        if (i == 0) {
                            if (j == 0) {
                                sbOptionIds.append(options.getId());
                            } else {
                                sbOptionIds.append(",").append(options.getId());
                            }
                        }
                        if (j == 0) {
                            sbScores.append(options.getMy_score());
                        } else {
                            sbScores.append(",").append(options.getMy_score());
                        }
                    }
                }
            }
            mPresenter.commit(mEntity.getId(), sbScoreIds.toString(), sbOptionIds.toString(), sbScores.toString());
        });

        findViewById(R.id.ll_comment).setOnClickListener(v -> {
            CommentDialog dialog = new CommentDialog(mContext);
            dialog.show();
            dialog.setEmptyable(false);
            dialog.setCommitListener(v1 -> {
                if (dialog.getCommentText() != null && dialog.getCommentText().length() > 0) {
                    mPresenter.addComment(mEntity.getId(), dialog.getCommentText());
                }
                dialog.dismiss();
            });
            dialog.setCancelListener(v12 -> dialog.dismiss());
        });

        findViewById(R.id.ib_collect).setOnClickListener(v -> {
            //  点赞
            mPresenter.collect(mEntity.getId());
        });

        findViewById(R.id.ib_like).setOnClickListener(v -> {
            // 点赞
            mPresenter.like(mEntity.getId());
        });

        findViewById(R.id.ib_share).setOnClickListener(v -> {
            // 分享
            TMTextShare share = new TMTextShare();
            share.setContent(mEntity.getContent());
            TMShareUtil.getInstance(mContext).shareText(share);
        });
    }

    @Override
    protected void initData() {
        mPresenter.getComment(mEntity.getId());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateComment() {
        mTvCommentTop.setText("评论（" + mPresenter.getCommentList().size() + "）");
        mCommentAdapter.notifyDataSetChanged();
    }
}
