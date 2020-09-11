package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

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
    private AppraiseEntity mActionEntity;
    private VoteAdapter mVoteAdapter;
    private OptionVoteAdapter mOptionAdapter;

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
        String url = Constant.getBaseUrl() + "application/ya02wmsj_cecoe/activityShare/index.html?id=" + mActionEntity.getId();
        tmLinkShare.setUrl(url);
        tmLinkShare.setTitle(mActionEntity.getTitle());
        tmLinkShare.setThumb(mActionEntity.getIcon_path());
        TMShareUtil.getInstance(mContext).shareLink(tmLinkShare);
    }

    @Override
    protected void initView() {
        mActionEntity = (AppraiseEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mRvVote = findViewById(R.id.rv_vote);
        setTitle(mActionEntity.getName());
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_icon_fx_white);
        setHtml(mActionEntity.getContent());
        mRvVote.setLayoutManager(new GridLayoutManager(this, 2));
        if (mActionEntity.getVoteInfo() != null) {
            mVoteAdapter = new VoteAdapter(this, mActionEntity.getVoteInfo());
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
                    Intent intent = new Intent(mContext,VoteAppraiseActivity.class);
                    intent.putExtra(Constant.KEY_BEAN,entity);
                    startActivity(intent);
                    /*DialogHelp.getMessageDialog(mContext, entity.getContent()).show();*/
                }
            });
            mRvVote.setAdapter(mVoteAdapter);
        } else if (mActionEntity.getOptionInfo() != null) {
            mOptionAdapter = new OptionVoteAdapter(this, mActionEntity.getOptionInfo());
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
    }

    @Override
    public void updateVoteCount(int position, String count) {
        if (mActionEntity.getVoteInfo() != null) {
            if (TextUtils.isEmpty(count)) {
                int vote = Integer.parseInt(mActionEntity.getVoteInfo().get(position).getVote_number());
                mActionEntity.getVoteInfo().get(position).setVote_number(String.valueOf(vote + 1));
            } else {
                mActionEntity.getVoteInfo().get(position).setVote_number(count);
            }
            mVoteAdapter.notifyDataSetChanged();
        } else if (mActionEntity.getOptionInfo() != null) {
            if (TextUtils.isEmpty(count)) {
                int vote = Integer.parseInt(mActionEntity.getOptionInfo().get(position).getVote_number());
                mActionEntity.getOptionInfo().get(position).setVote_number(String.valueOf(vote + 1));
            } else {
                mActionEntity.getOptionInfo().get(position).setVote_number(count);
            }
            mOptionAdapter.notifyDataSetChanged();
        }
    }
}
