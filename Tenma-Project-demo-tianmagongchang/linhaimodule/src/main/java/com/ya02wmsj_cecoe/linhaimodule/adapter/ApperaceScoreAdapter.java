package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ScoreInfo;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.List;
import cn.carbs.android.expandabletextview.library.ExpandableTextView;


public class ApperaceScoreAdapter extends CommonAdapter<ScoreInfo> {
    private boolean mCheckEnable;

    public ApperaceScoreAdapter(Context context, List<ScoreInfo> datas, boolean enable) {
        super(context, R.layout.ya02wmsj_cecoe_apperace_score_vote_item, datas);
        mCheckEnable = enable;
    }

    @Override
    protected void convert(ViewHolder holder, ScoreInfo voteEntity, int position) {
        ImageManager.getInstance().loadCircleImage(mContext, voteEntity.getPic(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_head));
        holder.setText(R.id.tv_name, voteEntity.getTitle());
        ExpandableTextView tv_content = holder.getView(R.id.tv_content);
        tv_content.updateForRecyclerView(voteEntity.getContent(), tv_content.getWidth(), 0);

        RecyclerView rv_item = holder.getView(R.id.rv_item);
        List<ScoreInfo.Options> optionsList = voteEntity.getOptions();
        if (optionsList != null && optionsList.size() > 0) {
            rv_item.setVisibility(View.VISIBLE);
        } else {
            rv_item.setVisibility(View.GONE);
        }
        ApperaceScoreItemAdapter itemAdapter = new ApperaceScoreItemAdapter(mContext, optionsList, mCheckEnable);
        rv_item.setLayoutManager(new LinearLayoutManager(mContext));
        rv_item.setAdapter(itemAdapter);
    }
}
