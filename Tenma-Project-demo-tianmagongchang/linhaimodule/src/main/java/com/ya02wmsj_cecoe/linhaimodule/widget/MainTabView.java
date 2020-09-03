package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;


public class MainTabView extends RelativeLayout {
    private static final String TAG = "MainTabView";
    private Fragment mFragment;
    private ImageView mIvIcon;
    private TextView mTvText;
    private DragPointView mTvRedNum;

    private Drawable mIconId;
    private String mText;
    private boolean mSelected = false;

    private Animator anim1;
    private Animator anim2;
    private Handler mHandler = new Handler();

    private TabClickListener mTabClickListener;
    private ClearUnreadListener mClearUnreadListener;

    public MainTabView(Context context) {
        super(context);
        init(context, null);
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_main_tab, this, true);

        PropertyValuesHolder valueHolder_1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.9f);
        PropertyValuesHolder valuesHolder_2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.9f);
        anim1 = ObjectAnimator.ofPropertyValuesHolder(this, valueHolder_1, valuesHolder_2);
        anim1.setDuration(200);
        anim1.setInterpolator(new LinearInterpolator());

        PropertyValuesHolder valueHolder_3 = PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f);
        PropertyValuesHolder valuesHolder_4 = PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f);
        anim2 = ObjectAnimator.ofPropertyValuesHolder(this, valueHolder_3, valuesHolder_4);
        anim2.setDuration(200);
        anim2.setInterpolator(new LinearInterpolator());
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainTabView);
        mIconId = typedArray.getDrawable(R.styleable.MainTabView_tab_icon);
        mText = typedArray.getString(R.styleable.MainTabView_tab_text);
        mSelected = typedArray.getBoolean(R.styleable.MainTabView_tab_selected, false);
        typedArray.recycle();
    }

    public void bindData(@DrawableRes int iconId, String text, Fragment fragment, boolean isSelected, TabClickListener tabClickListener, ClearUnreadListener clearUnreadListener) {
        mIvIcon.setImageResource(iconId);
        mTvText.setText(text);
        if (mFragment == null) {
            mFragment = fragment;
        }
        mTabClickListener = tabClickListener;
        mClearUnreadListener = clearUnreadListener;
        setSelected(isSelected);
    }

    public Fragment getFragment() {
        return mFragment;
    }


    public void setUnreadCount(int count) {
        if (count < 1) {
            if (mTvRedNum.isShown()) {
                mTvRedNum.setVisibility(View.GONE);
            }
        } else {
            if (!mTvRedNum.isShown()) {
                mTvRedNum.setVisibility(View.VISIBLE);
            }
            if (count < 100) {
                mTvRedNum.setText(count + "");
            } else {
                mTvRedNum.setText("99");
            }
        }
    }

    public interface TabClickListener {
        void tabClick(MainTabView mainTab);
    }

    public interface ClearUnreadListener {
        void clearUnread();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIvIcon = findViewById(R.id.iv_icon);
        mTvText = findViewById(R.id.tv_text);
        mTvRedNum = findViewById(R.id.tv_red_num);
        mTvRedNum.setDragListener(() -> {
            mTvRedNum.setVisibility(INVISIBLE);
            if (mClearUnreadListener != null) {
                mClearUnreadListener.clearUnread();
            }
        });
        if (mIconId != null) {
            mIvIcon.setImageDrawable(mIconId);
        }
        mTvText.setText(mText);
        setSelected(mSelected);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.post(() -> {
                    anim2.end();
                    anim1.start();
                });
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mHandler.post(() -> {
                    anim1.end();
                    anim2.start();
                });
                if (mTabClickListener != null) {
                    mTabClickListener.tabClick(MainTabView.this);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}