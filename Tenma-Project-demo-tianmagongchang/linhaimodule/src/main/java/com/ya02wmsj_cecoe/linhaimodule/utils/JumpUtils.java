package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.AppraiseWebContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.PreviewImageActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.QuestionActivity;

import java.util.ArrayList;
import java.util.List;

public class JumpUtils {

    /**
     * 打开大图
     *
     * @param context
     * @param images    图片集合
     * @param desc      图片对应的说明集合,可以传null
     * @param showIndex 要显示的Index
     */
    public static void gotoPreviewImageActivity(Context context, List<String> images, List<String> desc, int showIndex) {
        Intent intent = new Intent(context, PreviewImageActivity.class);
        intent.putStringArrayListExtra(Constant.KEY_BEAN, (ArrayList<String>) images);
        intent.putStringArrayListExtra(Constant.KEY_BEAN_2, (ArrayList<String>) desc);
        intent.putExtra(Constant.KEY_INT_1, showIndex);
        context.startActivity(intent);
    }

    public static void gotoActionDetailActivity(Context context, AppraiseEntity entity) {
        Intent intent;
        if ("征文".equals(entity.getForm_name())
                || "征询".equals(entity.getForm_name())
                || "评选".equals(entity.getForm_name())) {
            intent = new Intent(context, AppraiseWebContentActivity.class);
        } else if ("有奖竞赛".equals(entity.getForm_name())) {
            // 答题界面
            intent = new Intent(context, QuestionActivity.class);
        } else {
            intent = new Intent(context, AppraiseWebContentActivity.class);
        }
        intent.putExtra(Constant.KEY_BEAN, entity);
        context.startActivity(intent);
    }

}
