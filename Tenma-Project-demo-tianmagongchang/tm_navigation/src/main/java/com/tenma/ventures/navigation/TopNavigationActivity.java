package com.tenma.ventures.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tenma.ventures.base.TMFragment;
import com.tenma.ventures.base.TMWebFragment;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMConstant;

import com.tenma.ventures.base.TMActivity;
import com.tenma.ventures.navigation.adapter.MainViewPagerAdapter;
import com.tenma.ventures.navigation.bean.TopNavigationBean;
import com.tenma.ventures.usercenter.UserCenterActivity;
import com.tenma.ventures.widget.ProhibitSlideViewPager;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 顶部导航模板
 * Created by bin on 2017/12/26.
 */

public class TopNavigationActivity extends TMActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private LinearLayout tab_item_ll;

    private RelativeLayout layout_bar;

    private List<TextView> tabTextViews;
    private List<View> tabLineViews;

    private RelativeLayout searchLayout;

    private ImageView icon_navi;

    private List<TopNavigationBean> topNavigationBeen;


    private ProhibitSlideViewPager mainViewPager;

    /**
     * Fragment集合
     */
    private List<Fragment> mMainFragments;

    /**
     * 适配器
     */
    private MainViewPagerAdapter mMainViewPagerAdapter;

    private String tabBgColor;
    private String tabSelTextColor;
    private String tabUnSelTextColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_navigation);

        initView();
    }

    private void initView() {

        tabTextViews = new ArrayList<>();
        tabLineViews = new ArrayList<>();
        mMainFragments = new ArrayList<>();
        topNavigationBeen = new ArrayList<>();
        layout_bar = findViewById(R.id.layout_bar);
        tab_item_ll = (LinearLayout) findViewById(R.id.tab_item_ll);
        mainViewPager = (ProhibitSlideViewPager) findViewById(R.id.viewPager);
        icon_navi = (ImageView) findViewById(R.id.icon_navi);
        searchLayout = findViewById(R.id.layout_search);
        icon_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TopNavigationActivity.this, UserCenterActivity.class));
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TopNavigationActivity.this, SearchActivity.class));
            }
        });

        try {
            FileInputStream is = openFileInput(TMConstant.Config.CONFIG_FILE_NAME);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.

            Gson gson = new Gson();
            JsonObject moduleJson = gson.fromJson(text, JsonObject.class);

            JsonObject configJson = moduleJson.getAsJsonObject("config");
            tabBgColor = "#" + configJson.get("themeColor").getAsString();
            tabSelTextColor = "#" + configJson.get("contentSelectColor").getAsString();
            tabUnSelTextColor = "#" + configJson.get("backgroundColor").getAsString();
            String nightThemeColor = "#" + configJson.get("nightThemeColor").getAsString();
            layout_bar.setBackgroundColor(Color.parseColor(tabBgColor));

            TMSharedPUtil.saveTMThemeColor(this, tabBgColor);
            TMSharedPUtil.saveTMNightThemeColor(this, nightThemeColor);

            tab_item_ll.removeAllViews();
            JsonArray contentArray = moduleJson.getAsJsonArray("content");
            for (int i = 0; i < contentArray.size(); i++) {
                TopNavigationBean moduleBean = gson.fromJson(contentArray.get(i).getAsJsonObject(), TopNavigationBean.class);
                topNavigationBeen.add(moduleBean);
                LinearLayout tabItem = (LinearLayout) getLayoutInflater().inflate(R.layout.item_tab, null);
                TextView tabTitle = tabItem.findViewById(R.id.tab_title);
                View tabLine = tabItem.findViewById(R.id.line_tab);

                tabTitle.setTag(moduleBean.getKey());
                tabLine.setTag(moduleBean.getKey());
                tabTitle.setText(moduleBean.getTitle());
                tabTitle.setOnClickListener(this);
                tabTextViews.add(tabTitle);
                tabLineViews.add(tabLine);

                LinearLayout.LayoutParams tabItemParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                tabItem.setLayoutParams(tabItemParams);
                tab_item_ll.addView(tabItem);

//                moduleBean.setNativeX(false);
                if (moduleBean.isNativeX()) {
                    Fragment nativeFragment = Fragment.instantiate(this, moduleBean.getAndroidSrc());
                    mMainFragments.add(nativeFragment);
                } else {
                    Bundle tmBundle = new Bundle();
                    tmBundle.putString(TMConstant.BundleParams.LOAD_URL, TMConstant.TM_HTML_FOLDER + moduleBean.getWwwFolderAndroid() + moduleBean.getAndroidSrc());
                    String configXmlName = moduleBean.getKey() + "_config";
                    int id = getResources().getIdentifier(configXmlName, "xml", getClass().getPackage().getName());
                    if (id == 0) {
                        tmBundle.putString(TMConstant.BundleParams.CONFIG_XML, configXmlName);
                    } else {
                        tmBundle.putString(TMConstant.BundleParams.CONFIG_XML, "");
                    }
                    Fragment h5Fragment = TMWebFragment.newInstance(tmBundle);
                    mMainFragments.add(h5Fragment);

//                    Bundle tmBundle = new Bundle();
//                    tmBundle.putString(TMConstant.BundleParams.LOAD_URL, TMConstant.TM_HTML_FOLDER + "login01/login_index.html");
//                    tmBundle.putString(TMConstant.BundleParams.CONFIG_XML, "login01_config");
//                    Fragment h5Fragment = TMFragment.newInstance(tmBundle);
//                    mMainFragments.add(h5Fragment);
                }
            }

            String firstTag = null != topNavigationBeen && topNavigationBeen.size() > 0 ? topNavigationBeen.get(0).getKey() : "";
            setTabBackgroundAndColor(firstTag);
            switchFragment(firstTag);
        } catch (IOException e) {
        }

        mainViewPager.addOnPageChangeListener(this);
        mMainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mMainFragments);
        mainViewPager.setAdapter(mMainViewPagerAdapter);
        mainViewPager.setCurrentItem(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tab_title) {
            setTabBackgroundAndColor((String) v.getTag());
            switchFragment((String) v.getTag());
        }
    }

    private void switchFragment(String tag) {
        for (int i = 0; i < tabTextViews.size(); i++) {
            TextView textView = tabTextViews.get(i);
            if (tag.equals(textView.getTag())) {
                mainViewPager.setCurrentItem(i);
            }
        }
    }

    public void setTabBackgroundAndColor(String tag) {
        for (TextView textView : tabTextViews) {
            textView.setTextColor(tag.equals(textView.getTag()) ? (TMSharedPUtil.getTMNight(this) ? getResources().getColor(R.color.white) : Color.parseColor(tabSelTextColor)) : Color.parseColor(tabUnSelTextColor));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tag.equals(textView.getTag()) ? 16 : 15);
        }
        for (View view : tabLineViews) {
//            view.setVisibility(tag.equals(view.getTag()) ? View.VISIBLE : View.INVISIBLE);
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TopNavigationBean topNavigationBean = topNavigationBeen.get(position);
        if (topNavigationBean.isInterceptScroll()) {
            mainViewPager.setTouchEnabled(false);
        } else {
            mainViewPager.setTouchEnabled(true);
        }

        setTabBackgroundAndColor((String) tabTextViews.get(position).getTag());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void initTheme() {
        super.initTheme();
        layout_bar.setBackgroundColor(TMSharedPUtil.getTMNight(this) ? getResources().getColor(R.color.basics_bg_color_night) : Color.parseColor(tabBgColor));
        icon_navi.setImageResource(TMSharedPUtil.getTMNight(this) ? R.mipmap.icon_logo_night : R.mipmap.icon_logo);
        setTabBackgroundAndColor(topNavigationBeen.get(mainViewPager.getCurrentItem()).getKey());
    }
}
