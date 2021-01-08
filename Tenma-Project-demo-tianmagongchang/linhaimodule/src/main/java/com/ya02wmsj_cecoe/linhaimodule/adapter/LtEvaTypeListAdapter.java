package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;

public class LtEvaTypeListAdapter extends BaseNodeViewFactory {
    private LtEvaTypeListAdapter.IClickWatcherListener mListener;

    public interface IClickWatcherListener {
        void expand(TreeNode treeNode);
        void gotoDetail(TreeNode treeNode);
    }

    public LtEvaTypeListAdapter(LtEvaTypeListAdapter.IClickWatcherListener listener) {
        mListener = listener;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        return new LtEvaTypeListAdapter.LtWatcherItemView(view, level);
    }

    public class LtWatcherItemView extends BaseNodeViewBinder {
        private int level;
        private TextView tv_title;
        private TextView tv_score;
        private ImageView iv_arrow, iv_left;
        private View root_view;

        public LtWatcherItemView(View itemView, int level) {
            super(itemView);
            this.level = level;
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_arrow = itemView.findViewById(R.id.iv_arrow);
            iv_left = itemView.findViewById(R.id.iv_left);
            tv_score = itemView.findViewById(R.id.tv_score);
            root_view = itemView;
        }

        @Override
        public int getLayoutId() {
            return R.layout.ya02wmsj_cecoe_lt_eva_type_item;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void bindView(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            LtEvaEntity entity = (LtEvaEntity) treeNode.getValue();
            if (entity != null) {
                tv_title.setText(entity.getTitle());
                tv_score.setText(entity.getPoint() + "分");
                if (entity.getChildren() == null || entity.getChildren().size() == 0) {
                    iv_arrow.setVisibility(View.GONE);
                    tv_score.setVisibility(View.VISIBLE);
                } else {
                    iv_arrow.setVisibility(View.VISIBLE);
                    tv_score.setVisibility(View.GONE);
                }
                if (treeNode.isExpanded()) {
                    iv_arrow.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_down_1);
                } else {
                    iv_arrow.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_left);
                }
                if (treeNode.getLevel() > 0) {
                    iv_left.setVisibility(View.VISIBLE);
                    root_view.setBackgroundColor(Color.parseColor("#F2F2F2"));
                    tv_title.setTextSize(14);
                } else {
                    iv_left.setVisibility(View.GONE);
                    root_view.setBackgroundColor(Color.WHITE);
                    tv_title.setTextSize(16);
                }
                root_view.setPadding(level * 50 + 20, 20, 0, 0);
                root_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (entity.getChildren() != null && entity.getChildren().size() > 0) {
                            mListener.expand(treeNode);
                        } else {
                            if (treeNode.getLevel() > 0) {
                                mListener.gotoDetail(treeNode);
                            }
                        }
                    }
                });
            }
        }
    }
}
