package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeTreeEntity;

import me.texy.treeview.TreeNode;
import me.texy.treeview.base.BaseNodeViewBinder;
import me.texy.treeview.base.BaseNodeViewFactory;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizeTreeAdapter extends BaseNodeViewFactory {
    private IOrganizeItemClickOption mOption;

    public void setListener(IOrganizeItemClickOption option) {
        mOption = option;
    }

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level) {
            case 1:
                return new OrganizeItemView(R.layout.ya02wmsj_cecoe_item_organize_tree, view, 0);
            case 2:
                return new OrganizeItemView(R.layout.ya02wmsj_cecoe_item_organize_tree, view, 1);
            case 3:
                return new OrganizeItemView(R.layout.ya02wmsj_cecoe_item_organize_tree, view, 2);
            case 4:
                return new OrganizeItemView(R.layout.ya02wmsj_cecoe_item_organize_tree, view, 3);
            default:
                return null;
        }
    }

    public class OrganizeItemView extends BaseNodeViewBinder {
        private int layoutId;
        private int level;
        private TextView textView, tv_addr;
        private ImageView imageView, iv_level;
        private View mRootView, view_content;

        public OrganizeItemView(int layoutId, View itemView, int level) {
            super(itemView);
            this.layoutId = layoutId;
            this.level = level;
            textView = itemView.findViewById(R.id.id_treenode_label);
            imageView = itemView.findViewById(R.id.iv_arrow);
            iv_level = itemView.findViewById(R.id.iv_level);
            tv_addr = itemView.findViewById(R.id.tv_addr);
            view_content = itemView.findViewById(R.id.view_content);
            mRootView = itemView;
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
            OrganizeTreeEntity entity = (OrganizeTreeEntity) treeNode.getValue();
            if (entity != null) {
                textView.setText(entity.getName());
                tv_addr.setText(entity.getAddress());
                if (entity.getList() == null || entity.getList().size() == 0) {
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
            if (level > 0) {
                if (entity.getList() != null && entity.getList().size() > 0 && treeNode.isExpanded()) {
                    iv_level.setVisibility(View.VISIBLE);
                } else {
                    iv_level.setVisibility(View.GONE);
                }
            } else {
                iv_level.setVisibility(View.GONE);
            }
            imageView.setOnClickListener(v -> {
                if (mOption != null) {
                    mOption.expand(treeNode);
                }
            });
            view_content.setOnClickListener(v -> {
                if (mOption != null) {
                    mOption.gotoDetail(treeNode);
                }
            });
            mRootView.setOnClickListener(v -> {

            });
        }
    }

    public interface IOrganizeItemClickOption {
        void expand(TreeNode treeNode);

        void gotoDetail(TreeNode treeNode);
    }
}
