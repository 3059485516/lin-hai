package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.ZoomImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PreviewImageAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mImageUrlList = new ArrayList<>();
    private LinkedList<ZoomImageView> mImageViewCacheList = new LinkedList<>();

    public PreviewImageAdapter(Context context, List<String> imageUrlList) {
        mContext = context;
        mImageUrlList = imageUrlList;
    }

    @Override
    public int getCount() {
        return mImageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ZoomImageView imageView;
        if (mImageViewCacheList.size() > 0) {
            imageView = mImageViewCacheList.remove();
            imageView.reset();
        } else {
            imageView = new ZoomImageView(mContext);
            ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(imageParams);
            imageView.setBackgroundColor(Color.BLACK);
        }
        container.addView(imageView);
        final String path = mImageUrlList.get(position);
        ImageManager.getInstance().loadIntoUseFitWidth(mContext, path, imageView);
        //长按保存图片
        imageView.setOnLongClickListener(view -> {
            if (!TextUtils.isEmpty(path) && path.startsWith("http")) {
                saveBitmap(imageView);
                return true;
            }
            return false;
        });
        return imageView;
    }

    //保存图片
    @SuppressLint("CheckResult")
    private void saveBitmap(final ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        final Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        if (bitmap == null) {
            return;
        }
        imageView.setEnabled(false);
        Observable.create((ObservableOnSubscribe<String>) e -> {
            String fileName = "" + System.nanoTime() + ".png";
            boolean result = ImageManager.getInstance().saveBitmap(mContext, bitmap, fileName);
            e.onNext(result ? "保存成功" : "保存失败");
            e.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            T.showShort(mContext, s);
            imageView.setEnabled(true);
        }, throwable -> {
            T.showShort(mContext, "保存失败");
            imageView.setEnabled(true);
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ZoomImageView imageView = (ZoomImageView) object;
        container.removeView(imageView);
        mImageViewCacheList.add(imageView);
    }
}
