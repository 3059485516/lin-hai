package com.tenma.ventures.navigation;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tenma.ventures.base.TMFragment;
import com.tenma.ventures.base.TMWebFragment;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMConstant;
import com.tenma.ventures.navigation.bean.BottomNavigationBean;
import com.tenma.ventures.tools.TMThemeManager;

/**
 * 精选（应急）
 * Created by bin on 2018/3/21.
 */

public class PSubjectFragment extends TMFragment {

    private View mContentView;

    private int themeColor;
    private int nightThemeColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_p_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        nightThemeColor = Color.parseColor(TMSharedPUtil.getTMNightThemeColor(getActivity()));
        mContentView = view;

        Bundle bundle = getArguments();
        ((RelativeLayout) view.findViewById(R.id.back_rl)).setVisibility(View.INVISIBLE);
        BottomNavigationBean bottomNavigationBean = bundle.getParcelable("bottomNavigationBean");
        if (null != bottomNavigationBean) {
            ((TextView) view.findViewById(R.id.title_tv)).setText(bottomNavigationBean.getTitle());
            Fragment fragment;
            if (bottomNavigationBean.isNativeX()) {
                fragment = Fragment.instantiate(getActivity(), bottomNavigationBean.getAndroidSrc());
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
                fragment = TMWebFragment.newInstance(tmBundle);
            }
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.content_fl, fragment).commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded()) {
            TMThemeManager.setThemeMode(TMSharedPUtil.getTMNight(getActivity()) ? TMThemeManager.ThemeMode.NIGHT : TMThemeManager.ThemeMode.DAY);
            onThemeChanged();
        }
    }

    @Override
    protected void initTheme() {
        super.initTheme();
        LinearLayout basicsLl = (LinearLayout) mContentView.findViewById(R.id.basics_ll);
//        LinearLayout basicsLl = (LinearLayout) mContentView;
        View baseHeaderRL = mContentView.findViewById(R.id.base_header_rl);

        basicsLl.setBackgroundColor(getResources().getColor(TMThemeManager.getCurrentThemeRes(getActivity(), R.color.basics_bg_color)));
        if (TMThemeManager.getThemeMode() == TMThemeManager.ThemeMode.DAY) {
            if (TMSharedPUtil.getTMTitleBarColor(getContext()) != null) {
                Drawable drawable = new BitmapDrawable(getResources(), TMSharedPUtil.getTMTitleBarColor(getContext()));
                baseHeaderRL.setBackground(drawable);
            } else {
                baseHeaderRL.setBackgroundColor(themeColor);
            }
        } else {
            baseHeaderRL.setBackgroundColor(nightThemeColor);
        }
        View statusView = mContentView.findViewById(R.id.base_status_ll);
        ViewGroup.LayoutParams layoutParams = statusView.getLayoutParams();
        layoutParams.height = getStatusBarHeight();
        statusView.setLayoutParams(layoutParams);
    }

    private int getStatusBarHeight() {
        int result = 0;
        try {
            int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = this.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0)
            result = (int) Math.ceil(20 * this.getResources().getDisplayMetrics().density);
        return result;
    }
}
