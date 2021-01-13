package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OptionEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class OptionVoteAdapter extends CommonAdapter<OptionEntity> {
    private IOptionVote mListerner;

    public OptionVoteAdapter(Context context, List<OptionEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_vote_old, datas);
    }

    public void setVoteListener(OptionVoteAdapter.IOptionVote voteListener) {
        mListerner = voteListener;
    }

    @Override
    protected void convert(ViewHolder holder, OptionEntity optionEntity, int position) {
        holder.setVisible(R.id.iv_icon, false);
        holder.setText(R.id.tv_vote, "投票 (" + optionEntity.getVote_number() + "票)");
        holder.getView(R.id.tv_vote).setOnClickListener(v -> {
            if (mListerner != null) {
                mListerner.onOptionVoteClick(optionEntity, position);
            }
        });
        holder.setVisible(R.id.tv_title, false);
        holder.setText(R.id.tv_content, optionEntity.getOption_desc());
        TextView tv_content = holder.getView(R.id.tv_content);
        tv_content.setTextColor(ContextCompat.getColor(mContext, R.color.yl_text_dark));
        tv_content.setTextSize(15);
    }

    public interface IOptionVote {
        void onOptionVoteClick(OptionEntity entity, int position);
    }
}
