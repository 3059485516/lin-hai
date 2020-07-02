package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherEntity;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;

public class LtWatchListAdapter extends BaseNodeViewFactory {
    private IClickWatcherListener mListener;

    public interface IClickWatcherListener {
        void expand(TreeNode treeNode);

        void gotoDetail(TreeNode treeNode);
    }

    public LtWatchListAdapter(IClickWatcherListener listener) {
        mListener = listener;
    }


    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        return new LtWatcherItemView(view, level);
    }

    public class LtWatcherItemView extends BaseNodeViewBinder {
        private int level;
        private ImageView iv_icon, iv_arrow;
        private TextView tv_name;
        private View root_view;

        public LtWatcherItemView(View itemView, int level) {
            super(itemView);
            this.level = level;
            iv_icon = itemView.findViewById(R.id.iv_icon);
            iv_arrow = itemView.findViewById(R.id.iv_arrow);
            tv_name = itemView.findViewById(R.id.tv_name);
            root_view = itemView;
        }

        @Override
        public int getLayoutId() {
            return R.layout.ya02wmsj_cecoe_lt_watcher_item;
        }

        @Override
        public void bindView(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            LtWatcherEntity entity = (LtWatcherEntity) treeNode.getValue();
            if (entity != null) {
                tv_name.setText(entity.getTitle());
                if (level == 0) {
                    iv_icon.setVisibility(View.GONE);
                    iv_arrow.setVisibility(View.VISIBLE);
                } else if (level == 1) {
                    iv_icon.setVisibility(View.VISIBLE);
                    iv_arrow.setVisibility(View.VISIBLE);
                    iv_icon.setImageResource(R.mipmap.ya02wmsj_cecoe_icon_level);
                } else if (level == 2) {
                    iv_arrow.setVisibility(View.GONE);
                    iv_icon.setVisibility(View.VISIBLE);
                    iv_icon.setImageResource(R.mipmap.ya02wmsj_cecoe_watcher);
                }
                if (treeNode.isExpanded()) {
                    iv_arrow.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_down_1);
                } else {
                    iv_arrow.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_left);
                }
                root_view.setPadding(level * 50, 0, 0, 0);
            }
            if (mListener != null) {
                root_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (level < 2) {
                            mListener.expand(treeNode);
                        } else {
                            mListener.gotoDetail(treeNode);
                        }
                    }
                });
            }
        }
    }
}
