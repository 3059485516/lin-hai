package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.PreviewImageActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-19.
 */
public class VoteAdapter extends CommonAdapter<VoteEntity> {
    private IVote mVoteListener;

    public VoteAdapter(Context context, List<VoteEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_vote, datas);
    }

    public void setVoteListener(IVote voteListener) {
        mVoteListener = voteListener;
    }

    @Override
    protected void convert(ViewHolder holder, final VoteEntity voteEntity, final int position) {
        holder.setText(R.id.tv_xh, String.valueOf(position + 1));
        String url = voteEntity.getPic();
        if (!url.contains("http") && !url.contains("HTTP")) {
            url = Constant.getBaseUrl() + url;
        }
        ImageManager.getInstance().loadImage(mContext, url, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_icon));
        holder.setText(R.id.tv_vote, voteEntity.getVote_number());
        holder.getView(R.id.btn_vote).setOnClickListener(v -> {
            if (mVoteListener != null) {
                mVoteListener.onVoteClick(voteEntity, position);
            }
        });
        holder.setText(R.id.tv_title, voteEntity.getTitle());
        holder.getView(R.id.tv_content).setOnClickListener(v -> {
            if (mVoteListener != null) {
                mVoteListener.onVoteDetailClick(voteEntity, position);
            }
        });

        holder.getView(R.id.iv_icon).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, PreviewImageActivity.class);
            ArrayList list = new ArrayList();
            list.add(voteEntity.getPic());
            intent.putExtra(Constant.KEY_BEAN, list);
            mContext.startActivity(intent);
        });
    }

    public interface IVote {
        void onVoteClick(VoteEntity entity, int position);

        void onVoteDetailClick(VoteEntity entity, int position);
    }
}
