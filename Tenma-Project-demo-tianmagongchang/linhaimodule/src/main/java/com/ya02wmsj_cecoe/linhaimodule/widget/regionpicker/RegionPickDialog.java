package com.ya02wmsj_cecoe.linhaimodule.widget.regionpicker;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-05-31.
 */
public class RegionPickDialog<T extends IRegionPickImp> {

    private Activity mActivity;
    private YLPopWindow mPopWindow;
    private List<T> mData_p = new ArrayList<>();
    private List<T> mData_c = new ArrayList<>();
    private RegionPickerAdapter mParentAdapter;
    private RegionPickerAdapter mChildAdapter;
    private OnRegionItemClickListener mParentClickListener;
    private OnRegionItemClickListener mChildClickListener;
    private boolean mHasChild;

    public RegionPickDialog(Activity activity, List<T> data_p, List<T> data_c, boolean hasChild) {
        mActivity = activity;
        mData_p.addAll(data_p);
        mData_c.addAll(data_c);
        mHasChild = hasChild;
    }

    public void setDataParentList(List<T> list) {
        if (list == null) return;
        mData_p.clear();
        mData_p.addAll(list);
        if (mPopWindow == null) {
            createPopWindow();
        }
        mParentAdapter.setDefaultPosition(0);
        // 刷新适配器
        mParentAdapter.notifyDataSetChanged();
    }

    public void setDataChildList(List<T> list) {
        if (list == null) return;
        mData_c.clear();
        mData_c.addAll(list);
        // 刷新适配器
        if (mPopWindow == null) {
            createPopWindow();
        }
        mChildAdapter.setDefaultPosition(0);
        mChildAdapter.notifyDataSetChanged();
    }

    public void setOnParentClickListener(OnRegionItemClickListener listener) {
        mParentClickListener = listener;
    }

    public void setOnChildClickListener(OnRegionItemClickListener listener) {
        mChildClickListener = listener;
    }

    public void show(View view) {
        if (mPopWindow == null) {
            createPopWindow();
        }
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
        View view = View.inflate(mActivity, R.layout.ya02wmsj_cecoe_dialog_region_pick, null);
        RecyclerView recyclerViewP = view.findViewById(R.id.rv_region_1);
        recyclerViewP.setLayoutManager(new LinearLayoutManager(mActivity));
        mParentAdapter = new RegionPickerAdapter(mActivity, mData_p, mParentClickListener);
        recyclerViewP.setAdapter(mParentAdapter);

        RecyclerView recyclerViewC = view.findViewById(R.id.rv_region_2);
        recyclerViewC.setLayoutManager(new LinearLayoutManager(mActivity));
        mChildAdapter = new RegionPickerAdapter(mActivity, mData_c, mChildClickListener);
        recyclerViewC.setAdapter(mChildAdapter);
        mPopWindow = new YLPopWindow.PopupWindowBuilder(mActivity).setView(view).create();

        if (!mHasChild) {
            recyclerViewC.setVisibility(View.GONE);
        }
    }
}
