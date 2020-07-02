package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class AppraiseOptionView extends LinearLayout {
    private TextView mTvName;
    private YLTextViewGroup mTvPic;
    private YLEditTextGroup mEtOptionName;
    private EditText mEtDesc;
    private String id = "0";

    public AppraiseOptionView(Context context) {
        super(context);
        init(context);
    }

    public AppraiseOptionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppraiseOptionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_view_appraise_option, this, true);
        mTvName = findViewById(R.id.tv_name);
        mTvPic = findViewById(R.id.tv_pic);
        mEtOptionName = findViewById(R.id.et_option_name);
        mEtDesc = findViewById(R.id.et_desc);
    }

    public TextView getTitleView() {
        return mTvName;
    }

    public YLTextViewGroup getOptionPicView() {
        return mTvPic;
    }

    public YLEditTextGroup getOptionNameView() {
        return mEtOptionName;
    }

    public EditText getDescView() {
        return mEtDesc;
    }

    public void setOptionId(String id) {
        this.id = id;
    }

    public String getOptionId() {
        return id;
    }
}
