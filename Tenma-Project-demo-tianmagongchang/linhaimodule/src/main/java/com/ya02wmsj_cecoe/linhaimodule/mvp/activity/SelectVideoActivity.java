package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.luck.picture.lib.permissions.RxPermissions;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.VideoAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LocalVideo;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.FileLocalUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.FileUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.PermissionsUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 上传视频
 */
public class SelectVideoActivity extends BaseActivity implements MultiItemTypeAdapter.OnItemClickListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private static final int REQUEST_CODE_RECODE = 301;
    public static void start(Context context,OnCompleteListener listener){
        onCompleteListener = listener;
        context.startActivity(new Intent(context,SelectVideoActivity.class));
    }

    protected VideoView mVideoView;

    protected ImageView mIvPlayPause;

    protected TextView mTvTimeCurrent;

    protected SeekBar mSeekbar;

    protected TextView mTvTimeTotal;

    protected RecyclerView mRecyclerView;

    private boolean mStop = true;
    private String mPath;
    private File mRecordFile;

    private List<LocalVideo> mVideos = new ArrayList<>();
    private VideoAdapter mAdapter;
    private RxManager mRxManager = new RxManager();
    private Disposable mTimerDisposable;

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideoView.isPlaying()) {
            stopPlay();
        }
    }

    @Override
    protected void onDestroy() {
        if (mVideoView.isPlaying()) {
            stopPlay();
        }
        if(onCompleteListener != null){
            onCompleteListener = null;
        }
        mVideoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return R.layout.ya02wmsj_cecoe_activity_select_video;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        setTitle("选择视频");
        setMenuText("完成");

        mVideoView = findViewById(R.id.video);
        mIvPlayPause = findViewById(R.id.iv_play_pause);
        mTvTimeCurrent = findViewById(R.id.tv_time_current);
        mSeekbar = findViewById(R.id.seekbar);
        mTvTimeTotal = findViewById(R.id.tv_time_total);
        mRecyclerView = findViewById(R.id.rv);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new VideoAdapter(this, mVideos);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);

        findViewById(R.id.iv_play_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoView.isPlaying()) {
                    stopPlay();
                } else {
                    startPlay();
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        new RxPermissions(this).request(PermissionsUtils.SD).subscribe(aBoolean -> {
            if (aBoolean) {
                getVideos();
            } else {
                toast("获取储存权限失败");
                finish();
            }
        }, throwable -> {
        });
    }

    private void getVideos() {
        mRxManager.add(Observable.create((ObservableOnSubscribe<List<LocalVideo>>) e -> e.onNext(FileUtils.getAllLocalVideos(mContext)))
                .subscribe(localVideos -> {
                    if (localVideos == null || localVideos.size() == 0) {
                        hideTextMenu();
                    } else {
                        mVideos.clear();
                        mVideos.addAll(localVideos);
                        mAdapter.notifyDataSetChanged();
                        showTextMenu();
                    }
                }, throwable -> hideTextMenu()));
    }

    @Override
    public void onMenuClicked() {
        if (mVideos.size() == 0) {
            return;
        }
        String path = mPath;
        if (path == null) {
            path = mVideos.get(0).getPath();
        }
        if(onCompleteListener != null){
            onCompleteListener.onComplete(path);
            finishActivity();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(Constant.KEY_STRING_1, path);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void startPlay() {
        if (TextUtils.isEmpty(mPath)) {
            return;
        }
        mVideoView.start();
        mIvPlayPause.setImageResource(R.drawable.nemediacontroller_play);
        mStop = false;
        startTimer();
    }

    private void stopPlay() {
        mVideoView.pause();
        mIvPlayPause.setImageResource(R.drawable.nemediacontroller_pause);
        mStop = true;
    }

    private void startTimer() {
        if (mTimerDisposable == null) {
            mTimerDisposable = Flowable.interval(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        if (!mStop) {
                            int currentPosition = mVideoView.getCurrentPosition();
                            int duration = mVideoView.getDuration();
                            mTvTimeCurrent.setText(DateUtil.millis2time(currentPosition));
                            mTvTimeTotal.setText(DateUtil.millis2time(duration));
                            mSeekbar.setProgress((int) (Math.floor(currentPosition * 10000.0D / duration)));
                        }
                    }, throwable -> {

                    });
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (position == -1) {
            recordVideo();
        } else {
            play(mVideos.get(position).getPath());
        }
    }

    @SuppressLint("CheckResult")
    private void recordVideo() {
        new RxPermissions(this).request(PermissionsUtils.RECORD_VIDEO).subscribe(aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                Uri uri;
                mRecordFile = new File(FileLocalUtils.getTempDir(), System.currentTimeMillis() + ".mp4");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // 包名要改成掌心临海的  com.tenma.ventures.linhai
                    uri = FileProvider.getUriForFile(mContext, "com.tenma.ventures.linhai" + ".provider", mRecordFile);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                } else {
                    uri = Uri.fromFile(mRecordFile);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_CODE_RECODE);
            } else {
                toast("获取录音权限失败");
            }
        }, throwable -> toast("获取录音权限失败"));
    }

    private void play(String path) {
        if (!path.equals(mPath)) {
            try {
                mPath = path;
                mVideoView.pause();
                mVideoView.setVideoPath(path);
                mVideoView.start();
                mIvPlayPause.setImageResource(R.drawable.nemediacontroller_play);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mStop = false;
        mVideoView.start();
        startTimer();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mStop = true;
        return true;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mStop = true;
        mSeekbar.setProgress(10000);
        mTvTimeCurrent.setText(mTvTimeTotal.getText());
        mIvPlayPause.setImageResource(R.drawable.nemediacontroller_pause);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RECODE && resultCode == RESULT_OK && mRecordFile != null) {
            try {
                play(mRecordFile.getPath());
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(mRecordFile.getPath());
                String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                long d = 0;
                if (!TextUtils.isEmpty(duration) && TextUtils.isDigitsOnly(duration)) {
                    d = Long.parseLong(duration);
                }
                mVideos.add(0, new LocalVideo(mRecordFile.getName(), mRecordFile.getAbsolutePath(), d, 0));
                mAdapter.selectFirst();
                showTextMenu();
                mRecordFile = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static interface OnCompleteListener{
        void onComplete(String path);
    }

    private static OnCompleteListener onCompleteListener;

    public static void setOnCompleteListener(OnCompleteListener listener) {
        onCompleteListener = listener;
    }
}

