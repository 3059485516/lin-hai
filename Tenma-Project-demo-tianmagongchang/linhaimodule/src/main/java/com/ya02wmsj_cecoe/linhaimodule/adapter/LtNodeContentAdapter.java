package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LtContentDetailActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.MyItemViewDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtNodeContentAdapter extends MultiItemTypeAdapter<NodeContent> {
    public LtNodeContentAdapter(Context context, List<NodeContent> datas) {
        super(context, datas);
        addItemType();
    }

    protected void addItemType() {
        addItemViewDelegate(new LtNodeContentAdapter.TextType(R.layout.ya02wmsj_cecoe_item_node_content_image));
    }

    class TextType extends MyItemViewDelegate<NodeContent> {

        public TextType(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(NodeContent item, int position) {
            return true;
        }

        @Override
        public void convert(ViewHolder holder, NodeContent nodeContent, int position) {
            ImageView imageView = holder.getView(R.id.iv_image);
            ViewTreeObserver vto = imageView.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int frameHeight = imageView.getWidth() / 20 * 13;
                    ViewGroup.LayoutParams params = imageView.getLayoutParams();
                    params.height = frameHeight;
                    imageView.setLayoutParams(params);
                }
            });
            holder.setText(R.id.tv_title, nodeContent.getTitle());
            holder.setText(R.id.tv_browse, nodeContent.getComment_count() + "");
            holder.setText(R.id.tv_time, nodeContent.getSource());
            holder.setText(R.id.tv_like, nodeContent.getThumb_num() + "");
            if (!TextUtils.isEmpty(nodeContent.getIcon_path())) {
                String path;
                if (nodeContent.getIcon_path().contains("http") || nodeContent.getIcon_path().contains("HTTP")) {
                    path = nodeContent.getIcon_path();
                } else {
                    path = Constant.getBaseUrl() + nodeContent.getIcon_path();
                }
                ImageManager.getInstance().loadImage(mContext, path, R.mipmap.ya02wmsj_cecoe_placeholder, holder.getView(R.id.iv_image));
            }
            holder.getConvertView().setOnClickListener(v -> {
                Intent intent = new Intent(mContext, LtContentDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                mContext.startActivity(intent);
            });
        }
    }
}
