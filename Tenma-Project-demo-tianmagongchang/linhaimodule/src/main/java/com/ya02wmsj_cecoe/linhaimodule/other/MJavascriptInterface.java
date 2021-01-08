package com.ya02wmsj_cecoe.linhaimodule.other;

import android.content.Context;
import android.content.Intent;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.WebActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 寻水的鱼 on 2018/1/5.
 */
public class MJavascriptInterface {
    private static final String TAG = "MJavascriptInterface";
    private Context context;

    public MJavascriptInterface(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img, String[] imageUrls, int position) {
        if (imageUrls == null) {
            return;
        }
        if (position >= imageUrls.length) {
            position = 0;
        }
        List<String> imageList = new ArrayList<>(Arrays.asList(imageUrls));
        JumpUtils.gotoPreviewImageActivity(context, imageList, null, position);
    }

    @android.webkit.JavascriptInterface
    public void openBrowse(String url) {
        Intent intent = new Intent();
        intent.setClass(context, WebActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, "新时代文明实践");
        intent.putExtra(Constant.KEY_STRING_2, url);
        context.startActivity(intent);
    }
}
