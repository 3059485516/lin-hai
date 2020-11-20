package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.QuestionFragmentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.QuestionItemReq;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.QuestionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.QuestionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DialogHelp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class QuestionActivity extends BaseActivity<QuestionContract.Presenter> implements QuestionContract.View {
    protected TextView mTvContent;
    protected TextView mTvTime;
    protected LinearLayout mLayBottom;
    protected TextView mTvCountShow;
    protected ViewPager mVpQuestion;
    protected TextView mTvLast;
    protected TextView mTvNext;

    private String mId;
    private AppraiseEntity mAppraiseEntity;

    private int mCurrentQuestionIndex = 0;
    //当前在第几题 0为第一题
    private static Map<String, Object> _ANSWER_MAP_ = new HashMap<>();

    public static void addQuestionReq(QuestionItemReq req, int position) {
        _ANSWER_MAP_.remove(position + "");
        _ANSWER_MAP_.put(position + "", req);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_question;
    }

    @Override
    protected void initMVP() {
        AppraiseEntity mEntity = (AppraiseEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        if (mEntity != null) {
            setTitle(mEntity.getName());
            mId = mEntity.getId();
        } else {
            finishActivity();
        }
        mPresenter = new QuestionPresenter(this, mId);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateInfo(AppraiseEntity appraiseEntity) {
        mAppraiseEntity = appraiseEntity;
        mTvContent.setText(mAppraiseEntity.getContent());
        mTvTime.setText(mAppraiseEntity.getCtime());
        if (mAppraiseEntity.getQuestionInfo() == null || mAppraiseEntity.getQuestionInfo().size() == 0) {
            mLayBottom.setVisibility(View.GONE);
        } else {
            mTvCountShow.setText(getShowCountText());
            QuestionFragmentAdapter adapter = new QuestionFragmentAdapter(getSupportFragmentManager(), mAppraiseEntity.getQuestionInfo());
            mVpQuestion.setAdapter(adapter);
        }
    }

    @Override
    protected void initView() {
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_icon_fx_white);
        mTvContent = findViewById(R.id.tv_content);
        mTvTime = findViewById(R.id.tv_time);
        mLayBottom = findViewById(R.id.lay_bottom);
        mTvCountShow = findViewById(R.id.tv_count_show);
        mVpQuestion = findViewById(R.id.vp_question);
        mTvLast = findViewById(R.id.tv_last);
        mTvNext = findViewById(R.id.tv_next);
        mVpQuestion.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                mCurrentQuestionIndex = i;
                mTvCountShow.setText(getShowCountText());
                submitBtnIsVisibility();
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mTvLast.setOnClickListener(v -> {
            // 上一题逻辑
            if (mAppraiseEntity == null || mAppraiseEntity.getQuestionInfo() == null) {
                return;
            }
            if (mCurrentQuestionIndex <= 0) {
                toast("当前已经是第一题了");
            } else {
                mVpQuestion.setCurrentItem(mCurrentQuestionIndex - 1);
            }

        });

        mTvNext.setOnClickListener(v -> {
            // 下一题逻辑
            if (mAppraiseEntity == null || mAppraiseEntity.getQuestionInfo() == null) {
                return;
            }

            if (mCurrentQuestionIndex >= mAppraiseEntity.getQuestionInfo().size() - 1) {
                submitData();
            } else {
                mVpQuestion.setCurrentItem(mCurrentQuestionIndex + 1);
            }
        });
    }

    private void submitBtnIsVisibility() {
        if (mCurrentQuestionIndex >= mAppraiseEntity.getQuestionInfo().size() - 1) {
            mTvNext.setText("提交");
        } else {
            mTvNext.setText("下一题");
        }
    }

    private void submitData() {
        // 提交
        if (mAppraiseEntity == null || mAppraiseEntity.getQuestionInfo() == null) return;
        String message = "确认提交答案？";
        if (_ANSWER_MAP_.size() < mAppraiseEntity.getQuestionInfo().size()) {
            message = "还有题目没做完，确认提交？";
        }
        DialogHelp.getConfirmDialog(this, message, (dialog, which) -> {
            // 提交
            Map<String, Object> map = new HashMap<>();
            map.put("activityId", mAppraiseEntity.getId());
            String data = map2String(_ANSWER_MAP_);
            map.put("data", data);
            mPresenter.answerQuestion(map);
            dialog.dismiss();
        }).show();
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

    private String getShowCountText() {
        if (mAppraiseEntity == null || mAppraiseEntity.getQuestionInfo() == null) return "";
        if (mCurrentQuestionIndex <= 0) {
            mTvLast.setTextColor(ContextCompat.getColor(mContext, R.color.yl_text_light));
        } else {
            mTvLast.setTextColor(Color.parseColor("#F12E20"));
        }
        if (mCurrentQuestionIndex >= mAppraiseEntity.getQuestionInfo().size() - 1) {
            mTvNext.setTextColor(ContextCompat.getColor(mContext, R.color.yl_text_light));
        } else {
            mTvNext.setTextColor(Color.parseColor("#F12E20"));
        }
        return mCurrentQuestionIndex + 1 + "/" + mAppraiseEntity.getQuestionInfo().size();
    }

    private String map2String(Map<String, Object> map) {
        if (map == null || map.size() == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String value = entry.getValue().toString().trim();
            sb.append(value).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString().trim();
    }

    @Override
    protected void initData() {
        mPresenter.getOnlineActivityDetail(mId);
    }

    @Override
    public void showScore(String text) {
        DialogHelp.getMessageDialog(this, text).show();
    }
}
