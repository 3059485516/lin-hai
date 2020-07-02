package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActivityThemeEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class ActionThemeAdapter extends CommonAdapter<ActivityThemeEntity> {
    public ActionThemeAdapter(Context context, List<ActivityThemeEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_activity_theme, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ActivityThemeEntity activityThemeEntity, int position) {
        holder.setText(R.id.tv_text, activityThemeEntity.getText());
    }
}
