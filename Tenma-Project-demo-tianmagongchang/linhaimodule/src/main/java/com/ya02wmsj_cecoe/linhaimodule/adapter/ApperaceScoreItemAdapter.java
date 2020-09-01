package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ScoreInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 评分项 item
 */
public class ApperaceScoreItemAdapter extends CommonAdapter<ScoreInfo.Options> {
    private boolean mCheckEnable;

    public ApperaceScoreItemAdapter(Context context, List<ScoreInfo.Options> datas, boolean enable) {
        super(context, R.layout.ya02wmsj_cecoe_apperace_score_adapter_item, datas);
        mCheckEnable = enable;
    }

    @Override
    protected void convert(ViewHolder holder, ScoreInfo.Options options, int position) {
        holder.setText(R.id.tv_ave, "平均分：" + options.getScore_ave());
        holder.setText(R.id.tv_title, options.getOption_desc());

        LinearLayout wrap_check = holder.getView(R.id.wrap_check);
        int me = options.getScore_me();
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
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        for (int i1 = 0; i1 < wrap_check.getChildCount(); i1++) {
                            CheckBox checkBox1 = (CheckBox) wrap_check.getChildAt(i1);
                            if (buttonView.getId() == checkBox1.getId()) {
                                options.setMy_score(i1 + 1);      //保存我打的分数
                                break;
                            }
                            checkBox1.setChecked(true);
                            //将之前的全部勾选上
                        }
                    } else {
                        for (int i1 = wrap_check.getChildCount() - 1; i1 >= 0; i1--) {
                            CheckBox checkBox1 = (CheckBox) wrap_check.getChildAt(i1);
                            checkBox1.setChecked(false); //将取消勾选button后面的全部取消
                            if (buttonView.getId() == checkBox1.getId()) {
                                options.setMy_score(i1);      //保存我打的分数
                                break;
                            }
                        }
                    }
                });
            }
        }
    }
}
