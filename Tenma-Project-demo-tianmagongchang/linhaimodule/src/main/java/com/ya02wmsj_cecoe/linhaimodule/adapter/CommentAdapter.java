package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-05-30.
 */
public class CommentAdapter extends CommonAdapter<CommentEntity> {
    public CommentAdapter(Context context, List<CommentEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_comment, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CommentEntity comment, int position) {
        holder.setText(R.id.tv_time, comment.getCreate_time());
        if (TextUtils.isEmpty(comment.getName())) {
            holder.setText(R.id.tv_name, coveUp(comment.getUuid()));
        } else {
            holder.setText(R.id.tv_name, comment.getName());
        }
        holder.setText(R.id.tv_content, comment.getContent());
        String icon_path = comment.getPic_url();
        if (!TextUtils.isEmpty(icon_path)) {
            if (icon_path.contains("http") || icon_path.contains("HTTP")) {
                ImageManager.getInstance().loadCircleImage(mContext, icon_path, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_head));
            } else {
                ImageManager.getInstance().loadCircleImage(mContext, Constant.getBaseUrl() + icon_path, R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_head));
            }
        }
    }

    private String coveUp(String str) {
        if (TextUtils.isEmpty(str)) return "";
        return str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
