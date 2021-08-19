package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.app.Dialog;
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

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.DiscussCommentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.DiscussDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.DiscussDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.HashMap;
import java.util.Map;

import static com.ya02wmsj_cecoe.linhaimodule.utils.TDevice.getScreenWidth;


public class DiscussDetailActivity extends BaseWebViewActivity<DiscussDetailContract.Presenter> implements DiscussDetailContract.View, DiscussCommentAdapter.IReplyComment {
    protected TextView mTvTitle;
    protected TextView mTvPublish;
    protected RecyclerView mRvDiscuss;
    private Dialog mDiscussDialog;
    private Dialog mReplyComDialog;
    private NodeContent mContent;
    private CommentEntity mReplyComment;
    private DiscussCommentAdapter mCommentAdapter;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_discuss;
    }

    @Override
    protected void initMVP() {
        mPresenter = new DiscussDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我的评议");
        mContent = (NodeContent) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mTvTitle = findViewById(R.id.tv_title);
        mTvPublish = findViewById(R.id.tv_publish);
        mRvDiscuss = findViewById(R.id.rv_discuss);
        if (mContent != null) {
            mTvTitle.setText(mContent.getTitle());
            mTvPublish.setText(mContent.getName());
            setHtml(mContent.getContents());
            mPresenter.getCommentByConId(mContent.getId());
        }
        mRvDiscuss.setLayoutManager(new LinearLayoutManager(this));
        mCommentAdapter = new DiscussCommentAdapter(mContext, mPresenter.getComnentList(), this);
        mRvDiscuss.setAdapter(mCommentAdapter);
        findViewById(R.id.btn_add).setOnClickListener(v -> showDiscussDialog());
    }

    @Override
    protected void initData() {
    }

    @Override
    public void getCommentSuc() {
        mCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSubCommentSuc() {
        showReplyCommentDialog();
    }

    @Override
    public void discussSuc() {
        if (mDiscussDialog != null) {
            mDiscussDialog.dismiss();
            mDiscussDialog = null;
        }
        if (mReplyComDialog != null) {
            mReplyComDialog.dismiss();
            mReplyComDialog = null;
        }
        mPresenter.getCommentByConId(mContent.getId());
    }

    private void showDiscussDialog() {
        if (mDiscussDialog != null) {
            mDiscussDialog.show();
            return;
        }
        if (mContent == null) return;
        mDiscussDialog = new Dialog(this, R.style.BottomDialogStyle);
        mDiscussDialog.setCanceledOnTouchOutside(true);
        mDiscussDialog.setCancelable(true);
        mDiscussDialog.setOnCancelListener(dialog -> {
            if (mDiscussDialog != null) {
                mDiscussDialog.dismiss();
                mDiscussDialog = null;
            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_dialog_discuss, null);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_publish = view.findViewById(R.id.tv_publish);
        EditText et_content = view.findViewById(R.id.et_content);
        view.findViewById(R.id.iv_close).setOnClickListener(v -> {
            mDiscussDialog.dismiss();
            mDiscussDialog = null;
        });
        if (!TextUtils.isEmpty(mContent.getIcon_path())) {
            ImageManager.getInstance().loadCircleImage(this, mContent.getIcon_path(), R.mipmap.ya02wmsj_cecoe_head, iv_icon);
        }
        tv_name.setText(mContent.getName());
        tv_title.setText(mContent.getTitle());
        tv_publish.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_content.getText())) {
                toast("请填写你的看法");
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("operate_type", "add");
            map.put("c_id", mContent.getId());
            map.put("content", et_content.getText());
            mPresenter.addDiscuss(map);
        });
        mDiscussDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = mDiscussDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        mDiscussDialog.show();
    }

    private void showReplyCommentDialog() {
        if (mReplyComDialog != null) {
            mReplyComDialog.show();
            return;
        }
        if (mContent == null) return;
        if (mReplyComment == null) return;
        mReplyComDialog = new Dialog(this, R.style.BottomDialogStyle);
        mReplyComDialog.setCanceledOnTouchOutside(true);
        mReplyComDialog.setCancelable(true);
        mReplyComDialog.setOnCancelListener(dialog -> {
            if (mReplyComDialog != null) {
                mReplyComDialog.dismiss();
                mReplyComDialog = null;
            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_diaolog_reply_comment, null);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_publish = view.findViewById(R.id.tv_publish);
        EditText et_content = view.findViewById(R.id.et_content);
        view.findViewById(R.id.iv_close).setOnClickListener(v -> {
            mReplyComDialog.dismiss();
            mReplyComDialog = null;
        });
        RecyclerView rv_comment = view.findViewById(R.id.rv_comment);
        rv_comment.setLayoutManager(new LinearLayoutManager(mContext));
        CommentAdapter adapter = new CommentAdapter(mContext, mPresenter.getComnentSubList());
        rv_comment.setAdapter(adapter);
        if (!TextUtils.isEmpty(mContent.getIcon_path())) {
            ImageManager.getInstance().loadCircleImage(this, mReplyComment.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, iv_icon);
        }
        tv_name.setText(mReplyComment.getName());
        tv_title.setText(mReplyComment.getContent());
        tv_publish.setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_content.getText())) {
                toast("请填写你的看法");
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("operate_type", "add");
            map.put("c_id", mContent.getId());
            map.put("content", et_content.getText());
            map.put("pid", mReplyComment.getId());
            map.put("puser", mReplyComment.getUuid());
            map.put("pcontent", mReplyComment.getContent());
            mPresenter.addDiscuss(map);
        });

        mReplyComDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = mReplyComDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        mReplyComDialog.show();
    }


    @Override
    public void replyComment(CommentEntity commentEntity) {
        // 回复评论
        mReplyComment = commentEntity;
        mPresenter.getCommentByCommentId(mContent.getId(), commentEntity.getId());
    }
}
