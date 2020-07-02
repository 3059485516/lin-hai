package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.VolunteerOrderEntity;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;

/**
 * Created by BenyChan on 2019-06-03.
 */
public class VolunteerOrderAdapter extends BaseNodeViewFactory {
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level) {
            case 1:
                return new LevelViewBinder(R.layout.ya02wmsj_cecoe_item_order_volunteer, view, 0);
            case 2:
                return new LevelViewBinder(R.layout.ya02wmsj_cecoe_item_order_volunteer, view, 1);
            case 3:
                return new LevelViewBinder(R.layout.ya02wmsj_cecoe_item_order_volunteer, view, 2);
            case 4:
                return new LevelViewBinder(R.layout.ya02wmsj_cecoe_item_order_volunteer, view, 3);
            default:
                return null;
        }
    }

    public class LevelViewBinder extends BaseNodeViewBinder {
        private int layoutId;
        private int level;
        private TextView textView;
        private ImageView imageView;
        private LinearLayout mRootView;

        public LevelViewBinder(int layoutId, View itemView, int level) {
            super(itemView);
            this.layoutId = layoutId;
            this.level = level;
            textView = itemView.findViewById(R.id.id_treenode_label);
            imageView = itemView.findViewById(R.id.iv_arrow);
            mRootView = itemView.findViewById(R.id.root_view);
        }

        @Override
        public int getLayoutId() {
            return layoutId;
        }

        @Override
        public void bindView(TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            VolunteerOrderEntity orderType = (VolunteerOrderEntity) treeNode.getValue();
            if (orderType != null) {
                textView.setText(orderType.getName());
                if (orderType.getChildren() == null || orderType.getChildren().size() == 0) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }
            }
            if (treeNode.isExpanded()) {
                imageView.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_down_1);
            } else {
                imageView.setImageResource(R.mipmap.ya02wmsj_cecoe_arrow_left);
            }
            mRootView.setPadding(level * 50, 0, 0, 0);
            textView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(treeNode);
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(TreeNode treeNode);
    }
}
