package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.EduEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 教育查询适配器
 */
public class EduCheckAdapter extends CommonAdapter<EduEntity> {

    public EduCheckAdapter(Context context, List<EduEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_node, datas);
    }

    @Override
    protected void convert(ViewHolder holder, EduEntity eduEntity, int position) {
        holder.setText(R.id.tv_node_name, eduEntity.getTitle());
        ImageManager.getInstance().loadImage(mContext, eduEntity.getIcon(), R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
    }
}
