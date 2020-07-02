package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.FullScreenVideoActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.TextContentActivity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.CoverVideo;
import com.ya02wmsj_cecoe.linhaimodule.widget.MyItemViewDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BenyChan on 2019-07-11.
 */
public class NodeContentAdapter extends MultiItemTypeAdapter<NodeContent> {
    public static final String TAG = "NodeContentAdapter";
    private GSYVideoOptionBuilder mGSVideoOptionBuilder;

    public NodeContentAdapter(Context context, List<NodeContent> datas) {
        super(context, datas);
        addItemType();
        mGSVideoOptionBuilder = new GSYVideoOptionBuilder();
    }

    protected void addItemType() {
        addItemViewDelegate(new TextPicture(R.layout.ya02wmsj_cecoe_item_node_content_image));
        addItemViewDelegate(new AlbumItem(R.layout.ya02wmsj_cecoe_item_list_album));
        addItemViewDelegate(new VideoItem(R.layout.ya02wmsj_cecoe_item_node_content_video));
        addItemViewDelegate(new LiveItem(R.layout.ya02wmsj_cecoe_item_node_content_video));
    }

    /**
     * 图文样式
     */
    class TextPicture extends MyItemViewDelegate<NodeContent> {

        public TextPicture(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(NodeContent item, int position) {
            if ("图文视频".equals(item.getType())) {
                if (item.getVideo_path() == null) return true;
                else return TextUtils.isEmpty(item.getVideo_path().getOrigUrl());
            }
            return false;
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
            holder.setText(R.id.tv_time, nodeContent.getName());
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
                Intent intent = new Intent(mContext, TextContentActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                mContext.startActivity(intent);
            });
        }
    }

    /**
     * 相册样式
     */
    class AlbumItem extends MyItemViewDelegate<NodeContent> {

        public AlbumItem(int layoutId) {
            super(layoutId);
        }

        private int getLikeCount(String str) {
            String[] likes = str.split(",");
            return likes.length;
        }

        private String[] getAlbumUrl(String str) {
            return str.split(",");
        }

        @Override
        public boolean isForViewType(NodeContent item, int position) {
            return "相册".equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, NodeContent nodeContent, int position) {
            holder.setText(R.id.tv_title, nodeContent.getTitle());
            holder.setText(R.id.tv_browse, nodeContent.getComment_count() + "");
            if (!TextUtils.isEmpty(nodeContent.getLikes_list())) {
                holder.setText(R.id.tv_like, getLikeCount(nodeContent.getLikes_list()) + "");
            } else {
                holder.setText(R.id.tv_like, "0");
            }
            holder.setText(R.id.tv_time, nodeContent.getName());
            if (!TextUtils.isEmpty(nodeContent.getPath())) {
                String[] urls = getAlbumUrl(nodeContent.getPath());
                LinearLayout imageParent = holder.getView(R.id.lay_image_parent);
                if (imageParent != null) {
                    for (int i = 0; i < 3; i++) {
                        ImageView imageView = (ImageView) imageParent.getChildAt(i);
                        ViewTreeObserver vto = imageView.getViewTreeObserver();
                        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                int height = imageView.getWidth() / 20 * 13;
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.height = height;
                                imageView.setLayoutParams(params);
                            }
                        });

                        if (urls.length > i) {
                            ImageManager.getInstance().loadImage(mContext, Constant.getBaseUrl() + urls[i], R.mipmap.ya02wmsj_cecoe_placeholder, imageView);
                        } else {
                            imageView.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
            holder.getConvertView().setOnClickListener(v -> {
                JumpUtils.gotoPreviewImageActivity(mContext, new ArrayList<>(Arrays.asList(nodeContent.getPath().split(","))), nodeContent.getAlbumDesc(), 0);
            });
        }
    }

    /**
     * 窗口视频样式
     */
    class VideoItem extends MyItemViewDelegate<NodeContent> {

        public VideoItem(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(NodeContent item, int position) {
            return item.getVideo_path() != null && !TextUtils.isEmpty(item.getVideo_path().getOrigUrl());
        }

        @Override
        public void convert(ViewHolder holder, NodeContent nodeContent, int position) {
            holder.setText(R.id.tv_title, nodeContent.getTitle());
            CoverVideo gsyVideoPlayer = holder.getView(R.id.video_item_player);
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
            mGSVideoOptionBuilder
                    .setIsTouchWiget(false)
                    .setSetUpLazy(true)//lazy可以防止滑动卡顿
//                    .setVideoTitle(name)
                    .setCacheWithPlay(false)
                    .setRotateViewAuto(true)
                    .setLockLand(true)
                    .setPlayTag(TAG)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setPlayPosition(position)
                    .setVideoAllCallBack(new GSYSampleCallBack() {
                        @Override
                        public void onEnterFullscreen(String url, Object... objects) {
                            super.onEnterFullscreen(url, objects);
                            gsyVideoPlayer.getBackButton().setVisibility(View.VISIBLE);
                            gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                        }

                        @Override
                        public void onQuitFullscreen(String url, Object... objects) {
                            super.onQuitFullscreen(url, objects);
                            gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
                        }
                    });

            //增加title
//            gsyVideoPlayer.getTitleTextView().setText(name);
            gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
            gsyVideoPlayer.loadCoverImage(nodeContent.getVideo_path().getSnapshotUrl(), R.mipmap.ya02wmsj_cecoe_placeholder);

            //设置返回键
            gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

            gsyVideoPlayer.getStartButton().setOnClickListener(view -> {
//                boolean inPlayingState = gsyVideoPlayer.isInPlayingState();
////                if (inPlayingState) {
////                    gsyVideoPlayer.doubleTouch();
////                } else {
////                    String playUrl = nodeContent.getVideo_path().getOrigUrl();
////                    String title = nodeContent.getTitle();
////                    view.setClickable(false);
////                    if (!TextUtils.isEmpty(playUrl)) {
//////                        mGSVideoOptionBuilder.setVideoTitle(title);
////                        mGSVideoOptionBuilder.setUrl(playUrl);
////                        mGSVideoOptionBuilder.build(gsyVideoPlayer);
////                        Log.e(TAG, "adater potion--->" + position);
////                        gsyVideoPlayer.setPlayPosition(position);
////                        gsyVideoPlayer.startBtnPlay();
////                        view.setClickable(true);
////                    } else {
////                        view.setClickable(true);
////                    }
////                }
                Intent intent = new Intent(mContext, FullScreenVideoActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
                intent.putExtra(Constant.KEY_STRING_2, RegionManager.getInstance().getCurrentCountyCode());
                intent.putExtra(Constant.KEY_STRING_3, nodeContent.getNode_id());
                intent.putExtra(Constant.KEY_INT_1, gsyVideoPlayer.getCurrentPositionWhenPlaying());
                mContext.startActivity(intent);

            });
            //设置全屏按键功能
            gsyVideoPlayer.getFullscreenButton().setVisibility(View.GONE);
//            gsyVideoPlayer.getFullscreenButton().setOnClickListener(v -> {
//                Intent intent = new Intent(mContext, FullScreenVideoActivity.class);
//                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getId());
//                intent.putExtra(Constant.KEY_INT_1, gsyVideoPlayer.getCurrentPositionWhenPlaying());
//                mContext.startActivity(intent);
//            });
        }
    }

    class LiveItem extends MyItemViewDelegate<NodeContent> {

        public LiveItem(int layoutId) {
            super(layoutId);
        }

        @Override
        public boolean isForViewType(NodeContent item, int position) {
            return "直播".equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, NodeContent nodeContent, int position) {
            holder.setText(R.id.tv_title, nodeContent.getTitle());
            CoverVideo gsyVideoPlayer = holder.getView(R.id.video_item_player);
            GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
            mGSVideoOptionBuilder
                    .setIsTouchWiget(false)
                    .setSetUpLazy(true)//lazy可以防止滑动卡顿
//                    .setVideoTitle(name)
                    .setCacheWithPlay(false)
                    .setRotateViewAuto(true)
                    .setLockLand(true)
                    .setPlayTag(TAG)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setPlayPosition(position)
                    .setVideoAllCallBack(new GSYSampleCallBack() {
                        @Override
                        public void onEnterFullscreen(String url, Object... objects) {
                            super.onEnterFullscreen(url, objects);
                            gsyVideoPlayer.getBackButton().setVisibility(View.VISIBLE);
                            gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                        }

                        @Override
                        public void onQuitFullscreen(String url, Object... objects) {
                            super.onQuitFullscreen(url, objects);
                            gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
                        }
                    });

            //增加title
//            gsyVideoPlayer.getTitleTextView().setText(name);
            gsyVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
            if (nodeContent.getLiveinfo() != null) {
                gsyVideoPlayer.loadCoverImage(nodeContent.getLiveinfo().getIcon(), R.mipmap.ya02wmsj_cecoe_placeholder);
            }

            //设置返回键
            gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

            gsyVideoPlayer.getStartButton().setOnClickListener(view -> {
                if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                    T.showShort(mContext, "直播空闲中");
                    return;
                }
                boolean inPlayingState = gsyVideoPlayer.isInPlayingState();
                if (inPlayingState) {
                    gsyVideoPlayer.doubleTouch();
                } else {
                    String playUrl = nodeContent.getLiveinfo().getHls_pull_url();
                    String title = nodeContent.getLiveinfo().getName();
                    view.setClickable(false);
                    if (!TextUtils.isEmpty(playUrl)) {
                        mGSVideoOptionBuilder.setVideoTitle(title);
                        mGSVideoOptionBuilder.setUrl(playUrl);
                        mGSVideoOptionBuilder.build(gsyVideoPlayer);
                        Log.e(TAG, "adater potion--->" + position);
                        gsyVideoPlayer.setPlayPosition(position);
                        gsyVideoPlayer.startBtnPlay();
                        view.setClickable(true);
                    } else {
                        view.setClickable(true);
                    }
                }

            });
            //设置全屏按键功能
            gsyVideoPlayer.getFullscreenButton().setOnClickListener(v -> {
                if (nodeContent.getLiveinfo() == null || "空闲".equals(nodeContent.getLiveinfo().getStatus())) {
                    T.showShort(mContext, "直播空闲中");
                    return;
                }
                Intent intent = new Intent(mContext, LiveActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, nodeContent.getLiveinfo().getName());
                intent.putExtra(Constant.KEY_STRING_2, nodeContent.getLiveinfo().getHls_pull_url());
                mContext.startActivity(intent);
            });
        }
    }
}
