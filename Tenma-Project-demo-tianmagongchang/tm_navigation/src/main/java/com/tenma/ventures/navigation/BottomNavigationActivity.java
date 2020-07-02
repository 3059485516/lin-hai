package com.tenma.ventures.navigation;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.tenma.ventures.base.TMActivity;
import com.tenma.ventures.base.TMWebFragment;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMConstant;
import com.tenma.ventures.navigation.adapter.MainViewPagerAdapter;
import com.tenma.ventures.navigation.bean.BottomNavigationBean;
import com.tenma.ventures.navigation.bean.NavIconBean;
import com.tenma.ventures.tools.TMStatusBarUtil;
import com.tenma.ventures.tools.TMThemeManager;
import com.tenma.ventures.tools.change_activity.BackPressListener;
import com.tenma.ventures.tools.change_activity.TablayoutChange;
import com.tenma.ventures.widget.ProhibitSlideViewPager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 底部导航模板
 */
public class BottomNavigationActivity extends TMActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, TablayoutChange {

    public ProhibitSlideViewPager mainViewPager;

    public LinearLayout tabLayout;

    /**
     * Fragment集合
     */
    private List<Fragment> mMainFragments;

    /**
     * 适配器
     */
    private MainViewPagerAdapter mMainViewPagerAdapter;

    private List<BottomNavigationBean> bottomNavigationBeans;
    private List<NavIconBean> navIconBeanDatas = new ArrayList<NavIconBean>();
    private String tabBgColor;
    private String tabSelTextColor;
    private String tabUnSelTextColor;

    private List<TextView> tabTextViews;
    private List<TextView> tabIconViews;
    private List<ImageView> tabImageViews;

    private TextView bottomNavLineTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        IntentFilter intentFilter;

        init();
        initNavIcon();
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = mMainFragments.get(mainViewPager.getCurrentItem());
        if (currentFragment instanceof BackPressListener) {
            if (((BackPressListener) currentFragment).onBackPress()) {
                return;
            }
        }
        moveTaskToBack(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TMThemeManager.setThemeMode(TMSharedPUtil.getTMNight(this) ? TMThemeManager.ThemeMode.NIGHT :
                TMThemeManager.ThemeMode.DAY);
        onThemeChanged();
        TMStatusBarUtil.translucentStatusBar(this, true);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bottom_item_ll) {
            setTabBackgroundAndColor((String) v.getTag());
            switchFragment((String) v.getTag());

        }
    }


    private void setTabBackgroundAndColor(String tag) {
        boolean isNight = TMSharedPUtil.getTMNight(this);
        for (TextView textView : tabTextViews) {
            textView.setTextColor(tag.equals(textView.getTag()) ? (isNight ? Color.parseColor(tabSelTextColor) : Color.parseColor(tabSelTextColor)) : (isNight ? getResources().getColor(R.color.white) : Color.parseColor(tabUnSelTextColor)));
        }
        for (TextView textView1 : tabIconViews) {
            textView1.setTextColor(tag.equals(textView1.getTag()) ? (isNight ? Color.parseColor(tabSelTextColor) : Color.parseColor(tabSelTextColor)) : (isNight ? getResources().getColor(R.color.white) : Color.parseColor(tabUnSelTextColor)));
        }

        for (int i = 0; i < tabImageViews.size(); i++) {
            ImageView imageView = tabImageViews.get(i);
            BottomNavigationBean bottomBean = bottomNavigationBeans.get(i);
            if (bottomBean.getImage().startsWith("http")) {
                Picasso.with(this).load(tag.equals(imageView.getTag()) ? bottomBean.getSelectedImage() : bottomBean.getImage()).into(imageView);
            } else {
                int imageId = getResources().getIdentifier(tag.equals(imageView.getTag()) ? bottomBean.getSelectedImage() : bottomBean.getImage(), "drawable", getPackageName());
                if (imageId > 0) {
                    imageView.setImageResource(imageId);
                }
            }
        }
    }

    private void switchFragment(String tag) {
        for (int i = 0; i < tabTextViews.size(); i++) {
            TextView textView = tabTextViews.get(i);
            if (tag.equals(textView.getTag())) {
                mainViewPager.setCurrentItem(i);
            }
        }

        for (int i = 0; i < tabIconViews.size(); i++) {
            TextView textView1 = tabIconViews.get(i);
            if (tag.equals(textView1.getTag())) {
                mainViewPager.setCurrentItem(i);
            }
        }

    }

    @SuppressWarnings("ConstantConditions")
    private void init() {
        tabLayout = (LinearLayout) findViewById(R.id.tab_item_ll);
        mainViewPager = (ProhibitSlideViewPager) findViewById(R.id.main_view_pager);

        bottomNavLineTv = findViewById(R.id.bottom_nave_line_tv);
        tabIconViews = new ArrayList<>();
        tabTextViews = new ArrayList<>();
        tabImageViews = new ArrayList<>();

        mMainFragments = new ArrayList<>();
        bottomNavigationBeans = new ArrayList<>();

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
            tabBgColor = "#" + configJson.get("backgroundColor").getAsString();
            tabSelTextColor = "#" + configJson.get("themeColor").getAsString();
            tabUnSelTextColor = "#" + configJson.get("contentColor").getAsString();
            String titleTextColor = "#" + configJson.get("titleTextColor").getAsString();
            String nightThemeColor = "#" + configJson.get("nightThemeColor").getAsString();
            tabLayout.setBackgroundColor(Color.parseColor(tabBgColor));

            TMSharedPUtil.saveTMTitleTextColor(this, titleTextColor);
            TMSharedPUtil.saveTMThemeColor(this, tabSelTextColor);
            TMSharedPUtil.saveTMNightThemeColor(this, nightThemeColor);

            tabLayout.removeAllViews();
            JsonArray contentArray = moduleJson.getAsJsonArray("content");
            for (int i = 0; i < contentArray.size(); i++) {
                BottomNavigationBean bottomNavigationBean = gson.fromJson(contentArray.get(i).getAsJsonObject(), BottomNavigationBean.class);
                bottomNavigationBeans.add(bottomNavigationBean);

                if (bottomNavigationBean.isNativeX()) {
                    if (bottomNavigationBean.isNeedTitleBar()) {
                        Fragment pSubjectFragment = new PSubjectFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bottomNavigationBean", bottomNavigationBean);
                        pSubjectFragment.setArguments(bundle);
                        mMainFragments.add(pSubjectFragment);
                    } else {
                        Fragment nativeFragment = Fragment.instantiate(this, bottomNavigationBean.getAndroidSrc());
                        Bundle bundle = new Bundle();
                        bundle.putString("title", bottomNavigationBean.getTitle());
                        nativeFragment.setArguments(bundle);
                        mMainFragments.add(nativeFragment);
                    }
                } else {
                    if (bottomNavigationBean.isNeedTitleBar()) {
                        Fragment pSubjectFragment = new PSubjectFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bottomNavigationBean", bottomNavigationBean);
                        pSubjectFragment.setArguments(bundle);
                        mMainFragments.add(pSubjectFragment);
                    } else {
                        Bundle tmBundle = new Bundle();
                        String url = bottomNavigationBean.getWwwFolderAndroid() + bottomNavigationBean.getAndroidSrc();
                        if (url.startsWith("http://") || url.startsWith("https://")) {
                            tmBundle.putString(TMConstant.BundleParams.LOAD_URL, url);
                        } else {
                            tmBundle.putString(TMConstant.BundleParams.LOAD_URL, TMConstant.TM_HTML_FOLDER + url);
                        }
                        String configXmlName = bottomNavigationBean.getKey() + "_config";
                        int id = getResources().getIdentifier(configXmlName, "xml", getClass().getPackage().getName());
                        if (id == 0) {
                            tmBundle.putString(TMConstant.BundleParams.CONFIG_XML, configXmlName);
                        } else {
                            tmBundle.putString(TMConstant.BundleParams.CONFIG_XML, "");
                        }
                        Fragment h5Fragment = TMWebFragment.newInstance(tmBundle);
                        mMainFragments.add(h5Fragment);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainViewPager.addOnPageChangeListener(this);
        //mMainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mMainFragments);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mMainFragments.get(position);
            }

            @Override
            public int getCount() {
                return mMainFragments.size();
            }
        };
        mainViewPager.setAdapter(fragmentPagerAdapter);
        mainViewPager.setOffscreenPageLimit(mMainFragments.size() - 1);
        mainViewPager.setCurrentItem(0);

        setupTabIcons();

        String firstTag = null != bottomNavigationBeans && bottomNavigationBeans.size() > 0 ? bottomNavigationBeans.get(0).getKey() : "";
        setTabBackgroundAndColor(firstTag);
        switchFragment(firstTag);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BottomNavigationBean bottomNavigationBean = bottomNavigationBeans.get(position);
        if (bottomNavigationBean.isInterceptScroll()) {
            mainViewPager.setTouchEnabled(false);
        } else {
            mainViewPager.setTouchEnabled(true);
        }
        setTabBackgroundAndColor((String) tabTextViews.get(position).getTag());
        setTabBackgroundAndColor((String) tabIconViews.get(position).getTag());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setupTabIcons() {
        for (int i = 0; i < bottomNavigationBeans.size(); i++) {
            BottomNavigationBean bottomBean = bottomNavigationBeans.get(i);
            getTabView(bottomBean);
        }
    }


    private void initNavIcon() {
        navIconBeanDatas.add(new NavIconBean("icon1", "&#xe604;"));
        navIconBeanDatas.add(new NavIconBean("icon2", "&#xe64e;"));
        navIconBeanDatas.add(new NavIconBean("icon3", "&#xe663;"));
        navIconBeanDatas.add(new NavIconBean("icon4", "&#xe659;"));
        navIconBeanDatas.add(new NavIconBean("icon5", "&#xe601;"));
        navIconBeanDatas.add(new NavIconBean("icon6", "&#xe7fa;"));
        navIconBeanDatas.add(new NavIconBean("icon7", "&#xe62c;"));

        navIconBeanDatas.add(new NavIconBean("icon8", "&#xe658;"));
        navIconBeanDatas.add(new NavIconBean("icon9", "&#xe83f;"));
        navIconBeanDatas.add(new NavIconBean("icon10", "&#xe722;"));
        navIconBeanDatas.add(new NavIconBean("icon11", "&#xe619;"));
        navIconBeanDatas.add(new NavIconBean("icon12", "&#xe621;"));
        navIconBeanDatas.add(new NavIconBean("icon13", "&#xe600;"));
        navIconBeanDatas.add(new NavIconBean("icon14", "&#xe610;"));

        navIconBeanDatas.add(new NavIconBean("icon15", "&#xe63e;"));
        navIconBeanDatas.add(new NavIconBean("icon16", "&#xe60a;"));
        navIconBeanDatas.add(new NavIconBean("icon17", "&#xe60b;"));
        navIconBeanDatas.add(new NavIconBean("icon18", "&#xe8c9;"));
        navIconBeanDatas.add(new NavIconBean("icon19", "&#xe670;"));
        navIconBeanDatas.add(new NavIconBean("icon20", "&#xe8cb;"));
        navIconBeanDatas.add(new NavIconBean("icon21", "&#xe8cc;"));

        navIconBeanDatas.add(new NavIconBean("icon22", "&#xe8ce;"));
    }

    public void getTabView(BottomNavigationBean bottomBean) {

        View itemView = LayoutInflater.from(this).inflate(R.layout.bottom_item_tab, null);
        LinearLayout bottom_item_ll = itemView.findViewById(R.id.bottom_item_ll);
        TextView txt_title = itemView.findViewById(R.id.txt_title);
        TextView icon = itemView.findViewById(R.id.icon1);
        switch (bottomBean.getSelectedImage()) {
            case "&#xe604;":
                icon = itemView.findViewById(R.id.icon1);
                break;
            case "&#xe64e;":
                icon = itemView.findViewById(R.id.icon2);
                break;
            case "&#xe663;":
                icon = itemView.findViewById(R.id.icon3);
                break;
            case "&#xe659;":
                icon = itemView.findViewById(R.id.icon4);
                break;
            case "&#xe601;":
                icon = itemView.findViewById(R.id.icon5);
                break;
            case "&#xe7fa;":
                icon = itemView.findViewById(R.id.icon6);
                break;
            case "&#xe62c;":
                icon = itemView.findViewById(R.id.icon7);
                break;
            case "&#xe658;":
                icon = itemView.findViewById(R.id.icon8);
                break;
            case "&#xe83f;":
                icon = itemView.findViewById(R.id.icon9);
                break;
            case "&#xe722;":
                icon = itemView.findViewById(R.id.icon10);
                break;
            case "&#xe619;":
                icon = itemView.findViewById(R.id.icon11);
                break;
            case "&#xe621;":
                icon = itemView.findViewById(R.id.icon12);
                break;
            case "&#xe600;":
                icon = itemView.findViewById(R.id.icon13);
                break;
            case "&#xe610;":
                icon = itemView.findViewById(R.id.icon14);
                break;
            case "&#xe63e;":
                icon = itemView.findViewById(R.id.icon15);
                break;
            case "&#xe60a;":
                icon = itemView.findViewById(R.id.icon16);
                break;
            case "&#xe60b;":
                icon = itemView.findViewById(R.id.icon17);
                break;
            case "&#xe8c9;":
                icon = itemView.findViewById(R.id.icon18);
                break;
            case "&#xe670;":
                icon = itemView.findViewById(R.id.icon19);
                break;
            case "&#xe8cb;":
                icon = itemView.findViewById(R.id.icon20);
                break;
            case "&#xe8cc;":
                icon = itemView.findViewById(R.id.icon21);
                break;
            case "&#xe8ce;":
                icon = itemView.findViewById(R.id.icon22);
                break;
        }

        txt_title.setText(bottomBean.getTitle());
        ImageView img_title = itemView.findViewById(R.id.img_title);
        Picasso.with(this).load(bottomBean.getImage()).into(img_title);
        txt_title.setTextColor(Color.parseColor(tabUnSelTextColor));

        img_title.setVisibility(View.VISIBLE);


        bottom_item_ll.setTag(bottomBean.getKey());
        txt_title.setTag(bottomBean.getKey());
        icon.setTag(bottomBean.getKey());
        img_title.setTag(bottomBean.getKey());

        bottom_item_ll.setOnClickListener(this);
        tabTextViews.add(txt_title);
        tabImageViews.add(img_title);
        tabIconViews.add(icon);

        LinearLayout.LayoutParams tabItemParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        itemView.setLayoutParams(tabItemParams);
        tabLayout.addView(itemView);
    }

    @Override
    protected void initTheme() {
        super.initTheme();
        LinearLayout basicsLl = (LinearLayout) findViewById(R.id.bottom_basics_ll);
        basicsLl.setBackgroundColor(getResources().getColor(TMThemeManager.getCurrentThemeRes(this, R.color.basics_bg_color)));

        tabLayout.setBackgroundColor(TMSharedPUtil.getTMNight(this) ? getResources().getColor(R.color.basics_bg_color_night) : Color.parseColor(tabBgColor));
        setTabBackgroundAndColor(bottomNavigationBeans.get(mainViewPager.getCurrentItem()).getKey());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(TMThemeManager.getCurrentThemeRes(this, R.color.user_head_bg_color)));
        }

        bottomNavLineTv.setBackgroundColor(getResources().getColor(TMThemeManager.getCurrentThemeRes(this, R.color.line_color)));
    }

    @Override
    public void showTablayout() {
        findViewById(R.id.tab_layout_ll).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTablayout() {
        findViewById(R.id.tab_layout_ll).setVisibility(View.GONE);
    }
}
