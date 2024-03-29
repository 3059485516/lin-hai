package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文化礼堂适配器
 */
public class LTViewPagerAdapter extends PagerAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Node> list1 = new ArrayList<>(Arrays.asList(
            new Node("礼堂地图", R.mipmap.ya02wmsj_cecoe_net_2_4 + "", true),
            new Node("礼堂秀场", R.mipmap.ya02wmsj_cecoe_platform_4 + "", true),
            new Node("礼堂指数", R.mipmap.ya02wmsj_cecoe_net_1_6 + "", true),
            new Node("我要点单", R.mipmap.ya02wmsj_cecoe_ideology_2 + "", true),
            new Node("礼堂建议", R.mipmap.ya02wmsj_cecoe_platform_1 + "", true)));

    private List<Node> list2 = new ArrayList<>(Arrays.asList(
            new Node("随手拍", R.mipmap.ya02wmsj_cecoe_net_1_5 + "", true),
            new Node("测评记录", R.mipmap.ya02wmsj_cecoe_ceping + "", true),
            new Node("消息", R.mipmap.ya02wmsj_cecoe_ideology_4 + "", true),
            new Node("视频监控", R.mipmap.ya02wmsj_cecoe_ideology_3 + "", true)
    ));

    public interface OnNodeItemClickCall{
        public void onItemClicked(String name);
    }

    private OnNodeItemClickCall onNodeItemClickCall;

    public void setOnNodeItemClickCall(OnNodeItemClickCall onNodeItemClickCall) {
        this.onNodeItemClickCall = onNodeItemClickCall;
    }

    public LTViewPagerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (!Config.getInstance().getUser().isLtManager()) {
            return 1;
        }
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View root = mInflater.inflate(R.layout.ya02wmsj_cecoe_item_vp_node, container, false);
        RecyclerView rv_node = root.findViewById(R.id.rv_node);
        rv_node.setLayoutManager(new GridLayoutManager(mContext, 5));
        List<Node> list;
        if (position == 0) {
            list = list1;
        } else {
            list = list2;
        }
        NodeAdapter adapter = new NodeAdapter(mContext, list);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (onNodeItemClickCall != null){
                    onNodeItemClickCall.onItemClicked(adapter.getDatas().get(position).getTitle());
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv_node.setAdapter(adapter);
        container.addView(root);
        return root;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }
}
