package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;

/**
 * @author Yang Shihao
 */
public class ToolbarLayout extends RelativeLayout {
    private static final String TAG = "ToolbarLayout";

    private int mMinHeight;
    private int mMaxHeight;
    private int mHeightDiff;

    private int mMinTitleTextSize;
    private float mTitleTextSizeDiff;

    private ImageView mIvBack;
    private ImageView mIvMenu;
    private TextView mTvTitle;
    private TextView mTvMenu;

    private boolean mShowBack = true;
    private String mTitleText;
    private String mMenuText;
    private Drawable mMenuIcon;

    private ScrollListener mScrollListener;

    public ToolbarLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_toolbar_layout, this);
        mMinHeight = (int) getResources().getDimension(R.dimen.yl_title_min_height);
        mMaxHeight = (int) getResources().getDimension(R.dimen.yl_title_height);
        mHeightDiff = mMaxHeight - mMinHeight;

        mMinTitleTextSize = DisplayUtils.sp2px(context, 10);
        int mMaxTitleTextSize = DisplayUtils.sp2px(context, 20);
        mTitleTextSizeDiff = mMaxTitleTextSize * 1.0F - mMinTitleTextSize;

        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolbarLayout);
        mShowBack = typedArray.getBoolean(R.styleable.ToolbarLayout_tb_show_back, mShowBack);
        mTitleText = typedArray.getString(R.styleable.ToolbarLayout_tb_title_text);
        mMenuText = typedArray.getString(R.styleable.ToolbarLayout_tb_menu_text);
        mMenuIcon = typedArray.getDrawable(R.styleable.ToolbarLayout_tb_menu_icon);
        typedArray.recycle();
    }

    public void setTitle(String text) {
        ViewGroup.LayoutParams params = mTvTitle.getLayoutParams();
        params.width = DisplayUtils.getScreenWidth(getContext()) / 2;
        mTvTitle.setLayoutParams(params);
        mTvTitle.setText(text);
    }

    public void setScrollListener(ScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }

    //----------------------------------------------------------------------------------------------

    public void showBack() {
        mIvBack.setVisibility(VISIBLE);
    }

    public void hideBack() {
        mIvBack.setVisibility(GONE);
    }

    //----------------------------------------------------------------------------------------------

    public void showTextMenu() {
        mTvMenu.setVisibility(VISIBLE);
    }

    public void hideTextMenu() {
        mTvMenu.setVisibility(GONE);
    }

    public void setMenuText(String text) {

        mIvMenu.setVisibility(GONE);
        mTvMenu.setVisibility(VISIBLE);
        mTvMenu.setText(text);
    }

    public ImageView getMenuView() {
        return mIvMenu;
    }

    public TextView getMenuTextView() {
        return mTvMenu;
    }

    //----------------------------------------------------------------------------------------------

    public void showIconMenu() {
        mIvMenu.setVisibility(VISIBLE);
    }

    public void hideIconMenu() {
        mIvMenu.setVisibility(GONE);
    }

    public void setMenuIcon(@DrawableRes int resId) {

        mTvMenu.setVisibility(GONE);
        mIvMenu.setVisibility(VISIBLE);
        mIvMenu.setImageResource(resId);
    }

    public void setOnClickListener(@Nullable OnClickListener backListener, @Nullable OnClickListener menuListener) {
        mIvBack.setOnClickListener(backListener);
        mTvMenu.setOnClickListener(menuListener);
        mIvMenu.setOnClickListener(menuListener);
    }

    private int scrollY = 0;

    public void scrollChange(int verticalOffset) {


        if (scrollY == verticalOffset) {
            return;
        }
        scrollY = verticalOffset;

        boolean enable = scrollY < mMinHeight;
        setEnabled(enable);

        int h = Math.max(mMaxHeight - scrollY, mMinHeight);

        int beforeHeight = mTvTitle.getHeight();
        ValueAnimator scaleY = ValueAnimator.ofInt(beforeHeight, h);
        scaleY.addUpdateListener(animation -> {
            int h1 = Integer.valueOf(animation.getAnimatedValue() + "");
            ViewGroup.LayoutParams params = mTvTitle.getLayoutParams();
            params.height = h1;
            mTvTitle.setLayoutParams(params);

            float alpha = (Math.max(mHeightDiff, h1) - mHeightDiff) * 1.0F / mMinHeight;
            setAlpha(alpha);

            float y = h1 - mMaxHeight;
            setY(y);
            if (mScrollListener != null) {
                mScrollListener.scrollChanged(y, alpha, enable);
            }
            float size = mMinTitleTextSize + mTitleTextSizeDiff * (h1 - mMinHeight) / mHeightDiff;
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        });

        int d = Math.abs(h - beforeHeight) * 3;
        scaleY.setDuration(d);
        scaleY.start();
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    @Override
    public void setAlpha(float alpha) {
        mIvBack.setAlpha(alpha);
        mIvMenu.setAlpha(alpha);
        mTvMenu.setAlpha(alpha);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIvBack = findViewById(R.id.toolbar_iv_back);
        mIvMenu = findViewById(R.id.toolbar_iv_menu);
        mTvTitle = findViewById(R.id.toolbar_tv_title);
        mTvMenu = findViewById(R.id.toolbar_tv_menu);


        if (!mShowBack) {
            mIvBack.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(mTitleText)) {
            setTitle(mTitleText);
        }

        if (mMenuIcon != null) {
            mIvMenu.setVisibility(VISIBLE);
            mIvMenu.setImageDrawable(mMenuIcon);

        } else if (!TextUtils.isEmpty(mMenuText)) {
            mTvMenu.setVisibility(VISIBLE);
            mTvMenu.setText(mMenuText);
        }

        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yl_theme_color));
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isEnabled()) {
            return super.dispatchTouchEvent(ev);
        } else {
            return true;
        }
    }

    public interface ScrollListener {
        void scrollChanged(float scrollY, float alpha, boolean enable);
    }
}
