package com.tenma.ventures.navigation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Aliicontest extends Activity {
    private TextView txt_title;
    private static final String TAG = "Aliicontest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aliicontest);
        Log.d(TAG, "onCreate: assdadsad");
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setTextColor(Color.parseColor("#000000"));
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "fontali/iconfont.ttf");
        txt_title.setTypeface(iconfont);

    }
}
