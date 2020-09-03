package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.ya02wmsj_cecoe.linhaimodule.R;
import java.util.Objects;

/**
 * 圆形 进度条 对话框
 */
public class CircleProgressDialog extends Dialog {
    private final static int[] COLORS = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private CircleProgress mCircleProgress;

    public CircleProgressDialog(Context context) {
        this(context, R.style.dialog_common);
    }

    public CircleProgressDialog(Context context, int defStyle) {
        super(context, defStyle);
        View view = getLayoutInflater().inflate(R.layout.ya02wmsj_cecoe_dialog_circle_progress,null);
        mCircleProgress = view.findViewById(R.id.circle_progress);
        super.setContentView(view);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Objects.requireNonNull(getWindow()).setGravity(Gravity.CENTER);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth()/2;
        getWindow().setAttributes(p);
    }

    public void setProgress(float progress){
        mCircleProgress.setGradientColors(COLORS);
        mCircleProgress.setValue(progress);
    }
}
