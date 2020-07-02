package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LocalVideo;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * @author Yang Shihao
 */
public class VideoAdapter extends CommonAdapter<LocalVideo> {
    private int mCurrentPosition = 1;

    public VideoAdapter(Context context, List<LocalVideo> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_video, datas);
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public void selectFirst() {
        mCurrentPosition = 1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        TextView tvDuration = holder.getView(R.id.tv_duration);
        if (position == 0) {
            ImageManager.getInstance().loadImage(mContext, R.mipmap.ya02wmsj_cecoe_add_media,  holder.getView(R.id.iv_thumbnail));
            tvDuration.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, null, position - 1);
                }
            });
        } else {
            LocalVideo localVideo = mDatas.get(position - 1);
            tvDuration.setVisibility(View.VISIBLE);
            tvDuration.setText(DateUtil.millis2time(localVideo.getDuration()));
            tvDuration.setSelected(mCurrentPosition == position);
            ImageManager.getInstance().loadImage(mContext, localVideo.getPath(),holder.getView(R.id.iv_thumbnail));
            holder.itemView.setOnClickListener(v -> {
                notifyItemChanged(position);
                notifyItemChanged(mCurrentPosition);
                mCurrentPosition = position;
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(null, null, position - 1);
                }
            });
        }
    }

    @Override
    protected void convert(ViewHolder holder, LocalVideo localVideo, int position) {
    }
}
