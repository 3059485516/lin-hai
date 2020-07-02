package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.FullScreenVideoContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.FullScreenVideoPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.SoonCoverVideo;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

import static com.ya02wmsj_cecoe.linhaimodule.utils.TDevice.getScreenWidth;

public class FullScreenVideoActivity extends BaseActivity<FullScreenVideoContract.Presenter> implements FullScreenVideoContract.View {
    protected SoonCoverVideo mSoonCoverVideo;
    private ImageView mIvBack, mIvHead;
    private TextView mTvTitle, mTvName, mTvAddr, mTvLike, mTvCollect, mTvShare, mTvComment;

    private Dialog mCommentDialog;

    private NodeContent mNodeContent;
    private String mContentId, mRegionCode, mNodeId;
    private int mCurrentSeek = -1;
    private boolean hasLike, hasCollect;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fullscreen_video;
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected boolean fullScreen() {
        return true;
    }

    @Override
    protected void initMVP() {
        mPresenter = new FullScreenVideoPresenter(this);
    }

    @Override
    protected void initView() {
        mContentId = getIntent().getStringExtra(Constant.KEY_STRING_1);
        mRegionCode = getIntent().getStringExtra(Constant.KEY_STRING_2);
        mNodeId = getIntent().getStringExtra(Constant.KEY_STRING_3);
        mCurrentSeek = getIntent().getIntExtra(Constant.KEY_INT_1, -1);
        mSoonCoverVideo = findViewById(R.id.video);
        mSoonCoverVideo.getBackButton().setVisibility(View.GONE);
        mIvBack = findViewById(R.id.iv_back);
        mIvHead = findViewById(R.id.iv_icon);
        mTvTitle = findViewById(R.id.tv_title);
        mTvName = findViewById(R.id.tv_name);
        mTvAddr = findViewById(R.id.tv_addr);
        mTvLike = findViewById(R.id.tv_like);
        mTvCollect = findViewById(R.id.tv_collect);
        mTvShare = findViewById(R.id.tv_share);
        mTvComment = findViewById(R.id.tv_comment);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.like(mContentId);
            }
        });

        mTvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.collect(mContentId);
            }
        });

        mTvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享
                TMLinkShare tmLinkShare = new TMLinkShare();
                String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/share/index.html?id=" + mContentId;
                tmLinkShare.setUrl(url);
                tmLinkShare.setTitle(mNodeContent.getTitle());
                tmLinkShare.setThumb(mNodeContent.getIcon_path());
                TMShareUtil.getInstance(mContext).shareLink(tmLinkShare, new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        toast("分享成功");
                        if (mNodeContent != null) {
                            mTvShare.setText(mNodeContent.getShare_num() + 1 + "");
                        }
                        mPresenter.share(mContentId);
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
        });

        mTvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 评论
                mPresenter.getCommentById(mContentId);
            }
        });
    }

    private void showCommentDialog() {
        if (mCommentDialog != null) {
            mCommentDialog.show();
            return;
        }
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
        ImageView iv_close = view.findViewById(R.id.iv_close);
        RecyclerView rv_comment = view.findViewById(R.id.rv_comment);
        rv_comment.setLayoutManager(new LinearLayoutManager(this));
        rv_comment.setAdapter(new CommentAdapter(this, mPresenter.getCommentList()));
        EditText et_content = view.findViewById(R.id.et_content);
        TextView tv_commit = view.findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_content.getText())) {
                    mPresenter.addComment(mContentId, et_content.getText().toString());
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
    }

    private void dismissCommentDialog() {
        if (mCommentDialog != null) {
            mCommentDialog.dismiss();
            mCommentDialog = null;
        }
    }

    @Override
    protected void initData() {
        Map<String, Object> map = new HashMap<>();
        map.put("region_code", mRegionCode);
        map.put("top_status", "n");
        map.put("is_announce", "n");
        map.put("node_id", mNodeId);
        map.put("c_id", mContentId);
        mPresenter.getDetailContent(map);
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissCommentDialog();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void updateView(Object obj) {
        mNodeContent = (NodeContent) obj;
        if (!TextUtils.isEmpty(mNodeContent.getPic_url())) {
            ImageManager.getInstance().loadCircleImage(this, mNodeContent.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, mIvHead);
        }

        if (mNodeContent.getVideo_path() != null) {
            mSoonCoverVideo.setLooping(true);
            mSoonCoverVideo.setUp(mNodeContent.getVideo_path().getOrigUrl(), false, "");
            if (mCurrentSeek > 0) {
                mSoonCoverVideo.setSeekOnStart(mCurrentSeek);
            }
            mSoonCoverVideo.startPlayLogic();
        }

        mTvTitle.setText(mNodeContent.getTitle());
        mTvName.setText(mNodeContent.getName());
        mTvAddr.setText(mNodeContent.getOperate_time());
        mTvLike.setText(mNodeContent.getThumb_num() + "");
        mTvCollect.setText(mNodeContent.getCollect_num() + "");
        mTvShare.setText(mNodeContent.getShare_num() + "");
        mTvComment.setText(mNodeContent.getComment_count() + "");

        hasLike = mNodeContent.getThumb() == 1;      //是否已经点过赞
        hasCollect = mNodeContent.getCollect() == 1; //是否已经收藏过
        if (hasLike) {
            mTvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_like_press, 0, 0, 0);
            mTvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
        } else {
            mTvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_good, 0, 0, 0);
            mTvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
        }
        if (hasCollect) {
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect_press, 0, 0, 0);
            mTvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
        } else {
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect, 0, 0, 0);
            mTvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
        }
    }

    @Override
    public void likeSuc() {
        hasLike = !hasLike;
        UserOperateEvent event = new UserOperateEvent();
        event.setId(mNodeContent.getId());
        if (hasLike) {
            toast("点赞成功");
            mTvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_like_press, 0, 0, 0);
            mTvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
            if (mNodeContent != null) {
                mTvLike.setText(mNodeContent.getThumb_num() + 1 + "");
                event.setLikeNum(mNodeContent.getThumb_num() + 1);
            }
        } else {
            toast("取消点赞成功");
            mTvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_good, 0, 0, 0);
            mTvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
            if (mNodeContent != null) {
                mTvLike.setText(mNodeContent.getThumb_num() - 1 + "");
                event.setLikeNum(mNodeContent.getThumb_num() - 1);
            }
        }
        RxBus.getInstance().send(event);
    }

    @Override
    public void collectSuc() {
        hasCollect = !hasCollect;
        if (hasCollect) {
            toast("收藏成功");
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect_press, 0, 0, 0);
            mTvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
            if (mNodeContent != null) {
                mTvCollect.setText(mNodeContent.getCollect_num() + 1 + "");
            }
        } else {
            toast("取消收藏成功");
            mTvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect, 0, 0, 0);
            mTvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(this, 6));
            if (mNodeContent != null) {
                mTvCollect.setText(mNodeContent.getCollect_num() - 1 + "");
            }
        }
    }

    @Override
    public void shareSuc() {
//        if (mNodeContent != null) {
//            mTvShare.setText(mNodeContent.getShare_num() + 1 + "");
//        }
    }

    @Override
    public void commentSuc(String count) {
        mTvComment.setText(count);
        dismissCommentDialog();
    }

    @Override
    public void updateComment() {
        showCommentDialog();
    }
}
