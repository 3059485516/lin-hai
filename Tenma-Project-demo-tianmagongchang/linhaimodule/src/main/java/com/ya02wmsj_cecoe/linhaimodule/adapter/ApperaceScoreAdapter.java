package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

public class ApperaceScoreAdapter extends CommonAdapter<VoteEntity> {
    private boolean mCheckEnable;

    public ApperaceScoreAdapter(Context context, List<VoteEntity> datas, boolean enable) {
        super(context, R.layout.ya02wmsj_cecoe_apperace_score_vote_item, datas);
        mCheckEnable = enable;
    }

    @Override
    protected void convert(ViewHolder holder, VoteEntity voteEntity, int position) {
        ImageManager.getInstance().loadCircleImage(mContext, voteEntity.getPic(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_head));
        holder.setText(R.id.tv_name, voteEntity.getTitle());
        holder.setText(R.id.tv_ave, "平均分：" + voteEntity.getScore_ave());
        ExpandableTextView tv_content = holder.getView(R.id.tv_content);
        tv_content.updateForRecyclerView(voteEntity.getContent(), tv_content.getWidth(), 0);
        LinearLayout wrap_check = holder.getView(R.id.wrap_check);
        int me = voteEntity.getScore_me();
        if (!mCheckEnable) {
            //  显示自己打得分
            for (int i = 0; i < wrap_check.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) wrap_check.getChildAt(i);
                if (i < me) {
                    checkBox.setChecked(true);
                }
                checkBox.setEnabled(false); //设置不可点击
            }
        } else {
            //  显示灰色
            for (int i = 0; i < wrap_check.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) wrap_check.getChildAt(i);
                checkBox.setEnabled(true); //设置不可点击
                checkBox.setChecked(false);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            for (int i = 0; i < wrap_check.getChildCount(); i++) {
                                CheckBox checkBox = (CheckBox) wrap_check.getChildAt(i);
                                if (buttonView.getId() == checkBox.getId()) {
                                    voteEntity.setMy_score(i + 1);      //保存我打的分数
                                    break;
                                }
                                checkBox.setChecked(true);  //将之前的全部勾选上
                            }
                        } else {
                            for (int i = wrap_check.getChildCount() - 1; i >= 0; i--) {
                                CheckBox checkBox = (CheckBox) wrap_check.getChildAt(i);
                                checkBox.setChecked(false); //将取消勾选button后面的全部取消
                                if (buttonView.getId() == checkBox.getId()) {
                                    voteEntity.setMy_score(i);      //保存我打的分数
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
