package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class YLEditTextGroup extends RelativeLayout {
    private TextView mTvLeft;
    private EditText mEtRight;
    private ImageView mIvLeft;
    private String mTextLeft = "";
    private String mTextRight = "";
    private String mHint = "";
    Drawable drawable;
    private boolean mRequired = false;
    private int mTextRightGravity = 3;
    private int mEditInputType = -1;

    public YLEditTextGroup(Context context) {
        super(context);
        init(context, null);
    }

    public YLEditTextGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public YLEditTextGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ya02wmsj_cecoe_edit_text_group, this, true);
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YLEditTextGroup);
        mTextLeft = typedArray.getString(R.styleable.YLEditTextGroup_e_text_left);
        mTextRight = typedArray.getString(R.styleable.YLEditTextGroup_e_text_right);
        mHint = typedArray.getString(R.styleable.YLEditTextGroup_e_hint);
        mRequired = typedArray.getBoolean(R.styleable.YLEditTextGroup_e_required, false);
        drawable = typedArray.getDrawable(R.styleable.YLEditTextGroup_iv_left_src);
        mTextRightGravity = typedArray.getInt(R.styleable.YLEditTextGroup_text_gravity, mTextRightGravity);
        mEditInputType = typedArray.getInt(R.styleable.YLEditTextGroup_edit_input_style, mEditInputType);
        typedArray.recycle();
    }

    public void setTextLeft(String s) {
        if (mRequired) {
            mTvLeft.setText(Html.fromHtml("<font color='#FF0000'>*</font>" + s));
        } else {
            mTvLeft.setText(s);
        }
    }

    public String getTextLeft() {
        return mTvLeft.getText().toString();
    }

    public void setTextRight(String s) {

        mEtRight.setText(s);
    }

    public String getTextRight() {
        String string = mEtRight.getText().toString().trim();
        if (mEditInputType == -1) {
            return string;
        }
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            if (mEditInputType == InputType.TYPE_CLASS_NUMBER) {
                int i = Integer.parseInt(string);
                return i + "";
            } else if (mEditInputType == InputType.TYPE_NUMBER_FLAG_DECIMAL) {
                float f = Float.parseFloat(string);
                DecimalFormat format = new DecimalFormat("##0.0");
                return format.format(f);
            } else {
                return string;
            }

        } catch (RuntimeException e) {
            return string;
        }
    }

    public EditText getEditText() {
        return mEtRight;
    }

    public void setEnableEidt(boolean enable) {
        mEtRight.setEnabled(enable);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTvLeft = (TextView) findViewById(R.id.vg_tv_left);
        mEtRight = (EditText) findViewById(R.id.vg_et_right);
        mIvLeft = (ImageView) findViewById(R.id.vg_iv_left);
        if (drawable != null) {
            mIvLeft.setVisibility(VISIBLE);
            mTvLeft.setVisibility(GONE);
            mIvLeft.setImageDrawable(drawable);
        }
        if (!TextUtils.isEmpty(mTextLeft)) {
            setTextLeft(mTextLeft);
        }
        mEtRight.setGravity(mTextRightGravity);
        if (mEditInputType == InputType.TYPE_CLASS_NUMBER) {
            mEtRight.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (mEditInputType == InputType.TYPE_NUMBER_FLAG_DECIMAL) {
            mEtRight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        mEtRight.setText(mTextRight);
        if (!TextUtils.isEmpty(mHint)) {
            mEtRight.setHint(mHint);
        }
        mEtRight.setFilters(new InputFilter[]{mInputFilter});
    }

    public TextView getmTvLeft() {
        return mTvLeft;
    }

    private InputFilter mInputFilter = new InputFilter() {

        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Matcher emojiMatcher = emoji.matcher(charSequence);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };
}
