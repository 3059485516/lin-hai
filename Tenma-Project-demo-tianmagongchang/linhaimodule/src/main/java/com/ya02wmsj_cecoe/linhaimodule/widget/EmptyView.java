package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;


/**
 * @author Yang Shihao
 */
public class EmptyView extends LinearLayout {
    private String mNoDataText = "暂无数据";
    private String mLoadErrorText = "加载失败";
    private TextView mTextView;

    public EmptyView(Context context) {
        super(context);
        init(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_view_empty, this, true);
        mTextView = findViewById(R.id.tv_text);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void noData() {
        show();
        mTextView.setText(mNoDataText);
    }

    public void loadError() {
        show();
        mTextView.setText(mLoadErrorText);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mTextView.setOnClickListener(l);
        findViewById(R.id.iv_pic).setOnClickListener(l);
    }
}
