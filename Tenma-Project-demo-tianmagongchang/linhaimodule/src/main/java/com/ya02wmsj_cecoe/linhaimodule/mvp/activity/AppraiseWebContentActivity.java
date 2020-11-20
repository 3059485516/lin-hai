package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OptionVoteAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.VoteAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionWebContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionVotePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BenyChan on 2019-06-19.
 */
public class AppraiseWebContentActivity extends BaseWebViewActivity<ActionWebContract.Presenter> implements ActionWebContract.View {
    protected RecyclerView mRvVote;
    private VoteAdapter mVoteAdapter;
    private OptionVoteAdapter mOptionAdapter;

    private String mId;
    private AppraiseEntity mAppraiseEntity;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_action_web;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionVotePresenter(this);
    }

    @Override
    public void onMenuClicked() {
        //分享链接
        TMLinkShare tmLinkShare = new TMLinkShare();
        String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/activityShare/index.html?id=" + mAppraiseEntity.getId();
        tmLinkShare.setUrl(url);
        tmLinkShare.setTitle(mAppraiseEntity.getTitle());
        tmLinkShare.setThumb(mAppraiseEntity.getIcon_path());
        TMShareUtil.getInstance(mContext).shareLink(tmLinkShare);
    }

    @Override
    protected void initView() {
        AppraiseEntity actionEntity = (AppraiseEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        setTitle(actionEntity.getName());
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_icon_fx_white);
        mRvVote = findViewById(R.id.rv_vote);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateInfo(AppraiseEntity appraiseEntity) {
        mAppraiseEntity = appraiseEntity;
        setHtml(mAppraiseEntity.getContent());
        mRvVote.setLayoutManager(new GridLayoutManager(this, 2));
        if (mAppraiseEntity.getVoteInfo() != null) {
            mVoteAdapter = new VoteAdapter(this, mAppraiseEntity.getVoteInfo());
            mVoteAdapter.setVoteListener(new VoteAdapter.IVote() {
                @Override
                public void onVoteClick(VoteEntity entity, int position) {
                    // 判断用户角色并投票    人物评选
                    if (Config.getInstance().getUser() != null) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("activityId", entity.getActivity_id());
                        map.put("operateType", "select");   //人物评选
                        map.put("voteId", entity.getId());  //人物选项ID
                        mPresenter.vote(position, map);
                    } else {
                        Config.getInstance().uploadUserInfo(Config.getInstance().getTMUser());
                    }
                }

                @Override
                public void onVoteDetailClick(VoteEntity entity, int position) {
                    Intent intent = new Intent(mContext, VoteAppraiseActivity.class);
                    intent.putExtra(Constant.KEY_BEAN, entity);
                    startActivity(intent);
                }
            });
            mRvVote.setAdapter(mVoteAdapter);
        } else if (mAppraiseEntity.getOptionInfo() != null) {
            mOptionAdapter = new OptionVoteAdapter(this, mAppraiseEntity.getOptionInfo());
            mOptionAdapter.setVoteListener((entity, position) -> {
                // 判断用户角色并投票        征询
                if (Config.getInstance().getUser() != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("activityId", entity.getActivity_id());
                    map.put("operateType", "consult");   //征询
                    map.put("optId", entity.getId());      //征询选项ID
                    mPresenter.vote(position, map);
                } else {
                    Config.getInstance().uploadUserInfo(Config.getInstance().getTMUser());
                }
            });
            mRvVote.setAdapter(mOptionAdapter);
        }
    }

    @Override
    protected void initData() {
        mPresenter.getOnlineActivityDetail(mId);
    }

    @Override
    public void updateVoteCount(int position, String count) {
        if (mAppraiseEntity.getVoteInfo() != null) {
            if (TextUtils.isEmpty(count)) {
                int vote = Integer.parseInt(mAppraiseEntity.getVoteInfo().get(position).getVote_number());
                mAppraiseEntity.getVoteInfo().get(position).setVote_number(String.valueOf(vote + 1));
            } else {
                mAppraiseEntity.getVoteInfo().get(position).setVote_number(count);
            }
            mVoteAdapter.notifyDataSetChanged();
        } else if (mAppraiseEntity.getOptionInfo() != null) {
            if (TextUtils.isEmpty(count)) {
                int vote = Integer.parseInt(mAppraiseEntity.getOptionInfo().get(position).getVote_number());
                mAppraiseEntity.getOptionInfo().get(position).setVote_number(String.valueOf(vote + 1));
            } else {
                mAppraiseEntity.getOptionInfo().get(position).setVote_number(count);
            }
            mOptionAdapter.notifyDataSetChanged();
        }
    }
}
