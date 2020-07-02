package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class AppraiseOptionView2 extends LinearLayout {
    private TextView mTvName;
    private YLEditTextGroup mEtOptionName;
    private String id = "0";

    public AppraiseOptionView2(Context context) {
        super(context);
        init(context);
    }

    public AppraiseOptionView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppraiseOptionView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_view_option2, this, true);
        mTvName = findViewById(R.id.tv_name);
        mEtOptionName = findViewById(R.id.et_option_name);
    }

    public TextView getTitleView() {
        return mTvName;
    }

    public YLEditTextGroup getOptionNameView() {
        return mEtOptionName;
    }

    public void setOptionId(String id) {
        this.id = id;
    }

    public String getOptionId() {
        return id;
    }
}
