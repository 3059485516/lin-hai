package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态测量包裹内容高度ViewPager，适用于滑动嵌套
 * Created by BenyChan on 2019-07-15.
 */
public class WrapContentHeightViewPager extends ViewPager {
    private Map<Integer, Integer> map = new HashMap<>(2);
    private int currentPage;

    public WrapContentHeightViewPager(@NonNull Context context) {
        super(context);
    }

    public WrapContentHeightViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if (map.size() > 0 && map.containsKey(currentPage)) {
            height = map.get(currentPage);
        }
        //得到ViewPager的MeasureSpec，使用固定值和MeasureSpec.EXACTLY，
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在切换tab的时候，重置ViewPager的高度
     *
     * @param current
     */
    public void resetHeight(int current) {
        this.currentPage = current;
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        if (map.size() > 0 && map.containsKey(currentPage)) {
            if (params == null) {
                params = new MarginLayoutParams(LayoutParams.MATCH_PARENT, map.get(current));
            } else {
                params.height = map.get(current);
            }
            setLayoutParams(params);
        }
    }

    /**
     * 获取、存储每一个tab的高度，在需要的时候显示存储的高度
     *
     * @param current tab的position
     * @param height  当前tab的高度
     */
    public void addHeight(int current, int height) {
        map.put(current, height);
    }
}
