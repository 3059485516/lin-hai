package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.QuestionEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.QuestionItem;
import com.ya02wmsj_cecoe.linhaimodule.bean.QuestionItemReq;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class QuestionFragment extends BaseFragment {
    protected TextView mTvContent;

    protected LinearLayout mLayCheck;

    private QuestionEntity mQuestionEntity;
    private int mCurrentCheckIndex = -1;    //当前选中的序号
    private QuestionItemReq mQuestionReq = new QuestionItemReq("", "");
    private List<String> mValueList = new ArrayList<>();

    public static QuestionFragment start(QuestionEntity question, int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_BEAN, question);
        bundle.putInt(Constant.KEY_INT_1, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_question;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        mTvContent = mRootView.findViewById(R.id.tv_content);
        mLayCheck = mRootView.findViewById(R.id.lay_check);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mQuestionEntity = (QuestionEntity) bundle.getSerializable(Constant.KEY_BEAN);
            mCurrentCheckIndex = bundle.getInt(Constant.KEY_INT_1);
            if (mQuestionEntity != null) {
                mQuestionReq.setQuesId(mQuestionEntity.getId());
                StringBuilder sb = new StringBuilder("(");
                sb.append(mQuestionEntity.getType())
                        .append("-").append(mQuestionEntity.getScore())
                        .append("分").append(")").append("  ")
                        .append(mQuestionEntity.getQues_content());
                mTvContent.setText(sb);
                initCheckBox(mQuestionEntity.getOptions(), !"多选".equals(mQuestionEntity.getType()));
            }
        }
    }

    @Override
    protected void initData() {

    }

    private void initCheckBox(List<QuestionItem> list, boolean single) {
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            QuestionItem item = list.get(i);
            CheckBox checkBox = new CheckBox(mActivity);
            checkBox.setId(i);
            checkBox.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            checkBox.setPadding(20, 10, 20, 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            checkBox.setLayoutParams(params);
            checkBox.setTextColor(Color.WHITE);
            checkBox.setTextSize(16);
            checkBox.setBackgroundResource(R.drawable.ya02wmsj_cecoe_bg_question_item);
            checkBox.setText(item.getOption_name() + "： " + item.getOption_value());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    setSingleCheck(checkBox.getId(), single);
                    addValue(item.getOption_name());
                } else {
                    removeValue(item.getOption_name());
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : mValueList) {
                    stringBuilder.append(s).append(",");
                }
                String result = stringBuilder.toString().trim();
                if (result.length() > 0) {
                    if (",".equals(result.substring(result.length() - 1))) {
                        result = result.substring(0, result.length() - 1);
                    }
                }
                mQuestionReq.setQuesOptNames(result);
                QuestionActivity.addQuestionReq(mQuestionReq, mCurrentCheckIndex);
            });
            mLayCheck.addView(checkBox);
        }
    }

    /**
     * @param index 序号
     */
    private void setSingleCheck(int index, boolean single) {
        int count = mLayCheck.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                CheckBox checkBox = (CheckBox) mLayCheck.getChildAt(i);
                if (single && i != index) {
                    checkBox.setChecked(false);
                }
            }
        }
    }

    private void addValue(String value) {
        if (mValueList.size() == 0) {
            mValueList.add(value);
            return;
        }
        for (String v : mValueList) {
            if (v.equals(value)) {
                return;
            }
        }
        mValueList.add(value);
    }

    private void removeValue(String value) {
        for (String v : mValueList) {
            if (v.equals(value)) {
                mValueList.remove(v);
                return;
            }
        }
    }
}
