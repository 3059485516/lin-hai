package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;

import java.util.List;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/8/4 9:10 AM
 * desc   : EMPTY
 * ================================================
 */
public class BadHabitNodeAdapter extends BaseQuickAdapter<Node, BaseViewHolder> {
    public BadHabitNodeAdapter(@Nullable List<Node> data) {
        super(R.layout.ya02wmsj_cecoe_item_bad_habits_node,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Node item) {

    }
}
