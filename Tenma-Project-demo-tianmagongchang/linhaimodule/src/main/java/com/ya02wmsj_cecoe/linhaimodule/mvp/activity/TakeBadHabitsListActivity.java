package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseQuickAdapterListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.TakeBadHabitsListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TakeBadHabitsListPresenter;
import com.yl.edit.video.activity.VideoShootActivity;

public class TakeBadHabitsListActivity extends BaseQuickAdapterListActivity<TakeBadHabitsListContract.Presenter>implements TakeBadHabitsListContract.View {

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_bad_habits_list);
    }
*/
    @Override
    protected void initMVP() {
        mPresenter = new TakeBadHabitsListPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我要拍陋习");
        setMenuIcon(R.mipmap.ya02wmsj_cecoe_take_bad_habits);
        mRefreshLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.yl_graybg));
    }

    @Override
    protected void initRecyclerViewLayoutManager() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onMenuClicked() {
        super.onMenuClicked();
       /* VideoShootActivity.start(this, new VideoShootActivity.OnCompleteListener() {
            @Override
            public void onComplete(String filePath) {
                UploadBadHabitsActivity.launch(TakeBadHabitsListActivity.this,filePath);
            }
        });*/
        SelectVideoActivity.start(this, new SelectVideoActivity.OnCompleteListener() {
            @Override
            public void onComplete(String path) {
                UploadBadHabitsActivity.launch(TakeBadHabitsListActivity.this,path);
            }
        });
    }
}