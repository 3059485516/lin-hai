package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OptionVoteAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.VoteAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OptionEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionWebContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionVotePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.HashMap;
import java.util.List;
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

    private ImageView iv_icon;
    private TextView tv_mainTitle;
    private TextView tv_time;
    private TextView tv_totalNum;
    private TextView tv_Mtitle;

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
        mId = actionEntity.getId();
        setTitle(actionEntity.getName());
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_icon_fx_white);
        mRvVote = findViewById(R.id.rv_vote);

        iv_icon = findViewById(R.id.iv_icon);
        tv_mainTitle = findViewById(R.id.tv_mainTitle);
        tv_time = findViewById(R.id.tv_time);
        tv_totalNum = findViewById(R.id.tv_totalNum);
        tv_Mtitle = findViewById(R.id.tv_Mtitle);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateInfo(AppraiseEntity appraiseEntity) {
        mAppraiseEntity = appraiseEntity;
        setHtml(mAppraiseEntity.getContent());

        ImageManager.getInstance().loadCircleImage(mContext, appraiseEntity.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, iv_icon);
        tv_mainTitle.setText(appraiseEntity.getName());
        tv_time.setText("截止时间：" + appraiseEntity.getEnd_time());

        tv_Mtitle.setText(appraiseEntity.getTitle());

        mRvVote.setLayoutManager(new GridLayoutManager(this, 2));
        if (mAppraiseEntity.getVoteInfo() != null) {
            List<VoteEntity> voteEntityList = mAppraiseEntity.getVoteInfo();
            int allNum = 0;
            for (int i = 0; i < voteEntityList.size(); i++) {
                VoteEntity voteEntity = voteEntityList.get(i);
                if (voteEntity != null) {
                    String vote_number = voteEntity.getVote_number();
                    try {
                        int num = Integer.valueOf(vote_number);
                        allNum = allNum + num;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            tv_totalNum.setText("总票：" + allNum);

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
            List<OptionEntity> voteEntityList = mAppraiseEntity.getOptionInfo();
            int allNum = 0;
            for (int i = 0; i < voteEntityList.size(); i++) {
                OptionEntity voteEntity = voteEntityList.get(i);
                if (voteEntity != null) {
                    String vote_number = voteEntity.getVote_number();
                    try {
                        int num = Integer.valueOf(vote_number);
                        allNum = allNum + num;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            tv_totalNum.setText("总票：" + allNum);

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
    public void updateVoteCount(int position) {
        if (mAppraiseEntity.getVoteInfo() != null) {
            int vote = Integer.parseInt(mAppraiseEntity.getVoteInfo().get(position).getVote_number());
            mAppraiseEntity.getVoteInfo().get(position).setVote_number(String.valueOf(vote + 1));
            mVoteAdapter.notifyDataSetChanged();
        } else if (mAppraiseEntity.getOptionInfo() != null) {
            int vote = Integer.parseInt(mAppraiseEntity.getOptionInfo().get(position).getVote_number());
            mAppraiseEntity.getOptionInfo().get(position).setVote_number(String.valueOf(vote + 1));
            mOptionAdapter.notifyDataSetChanged();
        }
    }
}
