package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/27 3:43 PM
 * desc   : EMPTY
 * ================================================
 */
public class ZhiYuanFuWuViewPageAdapter extends PagerAdapter {
    private List<List<Node>> data = new ArrayList<>();
    private int mOnePageMax = 8;
    public ZhiYuanFuWuViewPageAdapter(@NonNull List<Node> nodeList){
        this(nodeList,8);
    }
    public ZhiYuanFuWuViewPageAdapter(@NonNull List<Node> nodeList,int onePageMax) {
        List<Node> source = new ArrayList<>();
        source.addAll(nodeList);
        mOnePageMax = onePageMax;
        int size = nodeList.size();
        if(size>(mOnePageMax*2)){
            throw new IllegalArgumentException("The adapter up to two pages,but more than that!");
        }
        if (size > mOnePageMax) {
            int total = size % mOnePageMax;
            for (int i = 0;i<total;i++){
                data.add(nodeList.subList(i*mOnePageMax,i*mOnePageMax+mOnePageMax));
            }
            if(total * mOnePageMax < size){
                data.add(nodeList.subList(total*mOnePageMax,size));
            }
        } else {
            data.add(nodeList);
        }
        /*Single<List<List<Node>>> single = Single.just(source).subscribeOn(AndroidSchedulers.mainThread()).map(new Function<List<Node>, List<List<Node>>>() {
            @Override
            public List<List<Node>> apply(List<Node> nodeList) throws Exception {
                List<List<Node>> data = new ArrayList<>();

                return data;
            }
        });
        single.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<List<Node>>>() {
            @Override
            public void accept(List<List<Node>> lists) throws Exception {
                data.clear();
                data.addAll(lists);
                notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate( R.layout.ya02wmsj_cecoe_item_vp_node, container,false);
//        View root = mInflater.inflate(R.layout.ya02wmsj_cecoe_item_vp_node, container, false);
        RecyclerView rv_node = root.findViewById(R.id.rv_node);
        rv_node.setLayoutManager(new GridLayoutManager(container.getContext(), 4));
        List<Node> list = data.get(position);
        rv_node.setAdapter(new NodeAdapter(container.getContext(), list));
        container.addView(root);
        return root;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        (container).removeView((View) object);
    }
}
