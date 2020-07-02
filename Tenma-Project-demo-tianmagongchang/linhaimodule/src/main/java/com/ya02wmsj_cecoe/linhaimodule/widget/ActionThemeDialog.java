package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ActionThemeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActivityThemeEntity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActionThemeDialog {
    private Activity mActivity;
    private YLPopWindow mPopWindow;
    List<ActivityThemeEntity> data = new ArrayList<>();
    private ActionThemeAdapter mAdapter;
    private IActionThemeItemClick mCallback;

    public ActionThemeDialog(Activity activity, IActionThemeItemClick callback) {
        mActivity = activity;
        mCallback = callback;
    }

    public void show(View view, List<ActivityThemeEntity> list) {
        if (mPopWindow == null) {
            createPopWindow();
        }
        data.clear();
        data.addAll(list);
        mAdapter.notifyDataSetChanged();
        mPopWindow.showAsDropDown(view, 0, 10);
    }

    private void dismiss() {
        if (mPopWindow != null) {
            mPopWindow.dismiss();
        }
    }

    public void destroy() {
        dismiss();
        mPopWindow = null;
    }

    private void createPopWindow() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.ya02wmsj_cecoe_dialog_action_theme, null);
        RecyclerView rv_theme = view.findViewById(R.id.rv_theme);
        rv_theme.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new ActionThemeAdapter(mActivity, data);
        rv_theme.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (mCallback != null) {
                    mCallback.onClickActionThemeItem(data.get(position));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mPopWindow = new YLPopWindow.PopupWindowBuilder(mActivity).setView(view).create();
    }

    public interface IActionThemeItemClick {
        void onClickActionThemeItem(ActivityThemeEntity entity);
    }

}
