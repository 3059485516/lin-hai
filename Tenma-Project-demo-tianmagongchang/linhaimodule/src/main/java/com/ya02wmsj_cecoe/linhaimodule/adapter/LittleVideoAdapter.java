package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.List;
import java.util.Objects;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 5:31 PM
 * desc   : EMPTY
 * ================================================
 */
public class LittleVideoAdapter extends BaseQuickAdapter<NodeContent, BaseViewHolder> {
    public LittleVideoAdapter(@Nullable List<NodeContent> data) {
        super(R.layout.ya02wmsj_cecoe_item_little_video,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NodeContent item) {
        ImageView ivHead = helper.getView(R.id.iv_icon);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvAddr = helper.getView(R.id.tv_addr);
        TextView tvLike = helper.getView(R.id.tv_like);
        TextView tvCollect = helper.getView(R.id.tv_collect);
        TextView tvShare = helper.getView(R.id.tv_share);
        TextView tvComment = helper.getView(R.id.tv_comment);
        tvTitle.setText(item.getTitle());
        tvName.setText(item.getName());
        tvAddr.setText(item.getOperate_time());
        tvLike.setText(item.getThumb_num() + "");
        tvCollect.setText(item.getCollect_num() + "");
        tvShare.setText(item.getShare_num() + "");
        tvComment.setText(item.getComment_count() + "");
        if (!TextUtils.isEmpty(item.getPic_url())) {
            ImageManager.getInstance().loadCircleImage(mContext, item.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, ivHead);
        }
        updateLike(isHasLike(item),tvLike);
        updateCollect(isHasCollect(item),tvCollect);
        helper.addOnClickListener(R.id.tv_like,R.id.tv_collect,R.id.tv_share,R.id.tv_comment);
    }

    public boolean isHasLike(@NonNull NodeContent nodeContent){
        return  Objects.requireNonNull(nodeContent).getThumb() == 1;      //是否已经点过赞

    }
    public boolean isHasCollect(@NonNull NodeContent nodeContent){
        return Objects.requireNonNull(nodeContent).getCollect() == 1; //是否已经收藏过
    }

    private void updateLike(boolean hasLike, TextView tvLike) {
        if (hasLike) {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_like_press, 0, 0, 0);
        } else {
            tvLike.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_good, 0, 0, 0);
        }
        tvLike.setCompoundDrawablePadding(DisplayUtils.dip2px(mContext, 6));
    }

    private void updateCollect(boolean hasCollect, TextView tvCollect) {
        if (hasCollect) {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect_press, 0, 0, 0);
        } else {
            tvCollect.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ya02wmsj_cecoe_collect, 0, 0, 0);
        }
        tvCollect.setCompoundDrawablePadding(DisplayUtils.dip2px(mContext, 6));
    }
}
