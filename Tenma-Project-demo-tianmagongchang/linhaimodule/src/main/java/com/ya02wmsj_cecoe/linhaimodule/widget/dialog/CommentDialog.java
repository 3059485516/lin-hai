package com.ya02wmsj_cecoe.linhaimodule.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;

/**
 *
 */
public class CommentDialog extends CommonDialog {
    private Context context;

    //取消
    TextView mCancelCheckNote;

    //确定
    TextView mSubmitCheckNote;

    //补充内容
    EditText mEtCheckNote;

    private boolean isEmptyable = false;

    public void setEmptyable(boolean emptyable) {
        isEmptyable = emptyable;
        if (isEmptyable) {
            mSubmitCheckNote.setBackgroundResource(R.drawable.ya02wmsj_cecoe_textview_border_orange);
        }
    }

    @SuppressLint("InflateParams")
    private CommentDialog(Context context, int defStyle) {
        super(context, defStyle);
        this.context = context;
        View view = getLayoutInflater().inflate(R.layout.ya02wmsj_cecoe_dialog_comment, null);
        mEtCheckNote = view.findViewById(R.id.et_comment);
        mSubmitCheckNote = view.findViewById(R.id.tv_submitComment);
        mCancelCheckNote = view.findViewById(R.id.tv_cancelComment);
        initViewListener();
        setContent(view, 0);
    }

    public CommentDialog(Context context) {
        this(context, R.style.dialog_bottom);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    //初始化 控件的事件
    private void initViewListener() {
        mEtCheckNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isEmptyable) {
                    return;
                }
                String string = s.toString();
                if (TextUtils.isEmpty(string)) {
                    mSubmitCheckNote.setBackgroundResource(R.drawable.ya02wmsj_cecoe_textview_border_light_gray);
                } else {
                    mSubmitCheckNote.setBackgroundResource(R.drawable.ya02wmsj_cecoe_textview_border_orange);
                }
            }
        });
    }


    public void setCommitListener(View.OnClickListener listener) {
        mSubmitCheckNote.setOnClickListener(listener);
    }

    public void setCancelListener(View.OnClickListener listener) {
        mCancelCheckNote.setOnClickListener(listener);
    }

    public EditText getEditText() {
        return mEtCheckNote;
    }

    public String getCommentText() {
        return mEtCheckNote.getText().toString().trim();
    }
}
