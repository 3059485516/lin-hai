package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ya02wmsj_cecoe.linhaimodule.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * @author Yang Shihao
 * <p>
 * Glide图片管理类
 */
public class ImageManager {
    private RequestOptions requestOptions = new RequestOptions().error(R.mipmap.ya02wmsj_cecoe_placeholder).skipMemoryCache(true).dontAnimate().encodeQuality(80).diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    private RequestOptions requestCircleOptions = new RequestOptions().error(R.mipmap.ya02wmsj_cecoe_placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).transform(new CircleCrop());

    public static ImageManager getInstance() {
        return ImageManager.Holder.INSTANCE;
    }

    /**
     * 加载普通图片
     */
    public void loadImage(Context context, Object url, ImageView iv) {
        if (url == null) {
            Glide.with(context).load(R.mipmap.ya02wmsj_cecoe_placeholder).apply(requestOptions).into(iv);
        } else {
            Glide.with(context).load(url).apply(requestOptions).into(iv);
        }
    }

    /**
     * 加载普通图片
     */
    public void loadGifImage(Context context, Object url, ImageView iv) {
        if (url == null) {
            Glide.with(context).load(R.mipmap.ya02wmsj_cecoe_placeholder).apply(requestOptions).into(iv);
        } else {
            Glide.with(context).asGif().load(url).into(iv);
        }
    }

    public void loadImage(Context context, Object url, int placeholder, ImageView iv) {
        if (url == null) {
            Glide.with(context).load(placeholder).into(iv);
        } else {
            RequestOptions options = new RequestOptions().placeholder(placeholder).error(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(context).load(url).apply(options).into(iv);
        }
    }

    /**
     * 加载圆形图片
     */
    public void loadCircleImage(Context context, Object url, ImageView iv) {
        if (url == null) {
            Glide.with(context).load(R.mipmap.ya02wmsj_cecoe_placeholder).apply(requestCircleOptions).into(iv);
        } else {
            Glide.with(context).load(url).apply(requestCircleOptions).into(iv);
        }
    }

    public void loadCircleImage(Context context, Object url, int placeholder, ImageView iv) {
        if (url == null) {
            Glide.with(context).load(placeholder).apply(requestCircleOptions).into(iv);
        } else {
            RequestOptions options = new RequestOptions().placeholder(placeholder).error(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).transform(new CircleCrop());
            Glide.with(context).load(url).apply(options).into(iv);
        }
    }

    /**
     * 加载圆角图片
     */
    public void loadRoundCornerImage(Context context, Object url, int radius, ImageView iv) {
        RequestOptions requestRoundOptions = new RequestOptions().placeholder(R.mipmap.ya02wmsj_cecoe_placeholder).error(R.mipmap.ya02wmsj_cecoe_placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).transform(new RoundedCorners(radius));
        Glide.with(context).load(url).apply(requestRoundOptions).into(iv);
    }

    /**
     * 加载Bitmap
     */
    public void loadImage(Context context, Bitmap bitmap, ImageView imageView) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, baos);
        Glide.with(context).load(baos.toByteArray()).apply(requestOptions).into(imageView);
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        if (baos != null) {
            try {
                baos.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadIntoUseFitWidth(Context context, Object imageUrl, final ImageView imageView) {
        Glide.with(context).asBitmap().load(imageUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                int realWidth = bitmap.getWidth();
                int realHeight = bitmap.getHeight();

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();

                float scale = (float) vw / (float) realWidth;
                int vh = Math.round(realHeight * scale);
                params.height = vh;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(params);
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public boolean saveImage(Context context, String imageUrl) {
        boolean result = false;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            FutureTarget<File> future = Glide.with(context).load(imageUrl).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
            File cacheFile = future.get();
            String oldPath = cacheFile.getAbsolutePath();
            File oldFile = new File(oldPath);
            if (oldFile.exists()) {
                is = new FileInputStream(oldPath);
                File newFile = new File(FileLocalUtils.getSaveDir(), String.format("miyou-%d.jpg", System.currentTimeMillis()));
                if (newFile.exists()) {
                    newFile.delete();
                }
                fos = new FileOutputStream(newFile);
                int b;
                byte[] buffer = new byte[512];
                while ((b = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, b);
                }
                Intent intentBroadcast = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intentBroadcast.setData(Uri.fromFile(newFile));
                context.sendBroadcast(intentBroadcast);
                result = true;
            }
        } catch (InterruptedException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ExecutionException e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
        }
        return result;
    }

    public boolean saveBitmap(Context context, Bitmap bitmap, String saveName) {
        boolean result = false;
        FileOutputStream fos = null;
        try {
            File file = new File(FileLocalUtils.getSaveDir(), saveName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            Intent intentBroadcast = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intentBroadcast.setData(Uri.fromFile(file));
            context.sendBroadcast(intentBroadcast);
            result = true;
        } catch (Exception e) {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
            }
        }
        return result;
    }

    /**
     * 释放内存
     */
    public void clearMemory(Context context) {
        try {
            Glide.get(context).clearMemory();
        } catch (RuntimeException e) {

        }
    }

    /**
     * 清除磁盘缓存
     */
    public void clearDiskCache(final Context context) {
        try {
            Glide.get(context).clearDiskCache();
        } catch (RuntimeException e) {

        }
    }

    public static byte[] bmpToByteArray(Bitmap var0, boolean var1) {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
        var0.compress(Bitmap.CompressFormat.JPEG, 100, var2);
        if (var1) {
            var0.recycle();
        }
        byte[] var4 = var2.toByteArray();
        try {
            var2.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var4;
    }

    /**
     * 清除所有缓存
     */
    public void cleanAll(Context context) {
        clearDiskCache(context);
        clearMemory(context);
    }

    public static class Holder {
        public static final ImageManager INSTANCE = new ImageManager();
    }
}
