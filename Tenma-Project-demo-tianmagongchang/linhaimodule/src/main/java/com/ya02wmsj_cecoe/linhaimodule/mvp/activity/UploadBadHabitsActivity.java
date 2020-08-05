package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.UploadBadHabitsContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.UploadBadHabitsPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CircleProgressDialog;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.List;

public class UploadBadHabitsActivity extends BaseActivity<UploadBadHabitsContract.Presenter>implements UploadBadHabitsContract.View {

    private CircleProgressDialog mCircleProgressDialog;
    public static final int REQUEST_REGION_CODE = 400;
    private OptionsPickerView<Node> optionsPickerView;


    public static void launch(Context context, String filePath){
        context.startActivity(new Intent(context,UploadBadHabitsActivity.class)
        .putExtra(Constant.KEY_STRING_1,filePath));
    }
    protected EditText mEtDesc;

    protected EditText mEtTitle;

    private RecyclerView rvNode;
    protected YLTextViewGroup mTvRegion;
    protected YLTextViewGroup mTvNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_upload_bad_habits;
    }

    @Override
    protected void initMVP() {
        mPresenter = new UploadBadHabitsPresenter(this,getIntent());
    }

    @Override
    protected void initView() {
        mEtDesc = findViewById(R.id.et_desc);
        mEtTitle = findViewById(R.id.et_title);
        mTvRegion = findViewById(R.id.tv_region);
        mTvNode = findViewById(R.id.tv_node);
        View video = findViewById(R.id.video);
        final String filePath = mPresenter.getFilePath();
        if(TextUtils.isEmpty(filePath)){
            video.setVisibility(View.GONE);
        }
        mTvRegion.setOnClickListener(v -> startActivityForResult(new Intent(mContext, SelectRegionActivity.class), REQUEST_REGION_CODE));
//        rvNode = findViewById(R.id.rv_node);
//        rvNode.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIvPlayClicked();
            }
        };
        findViewById(R.id.iv_play).setOnClickListener(onClickListener);
        findViewById(R.id.btn_release).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String videoTitle = getVideoTitle();
                if(TextUtils.isEmpty(videoTitle)){
                    toast("请填写标题！");
                    return;
                }
                final String content = getContent();
                if(TextUtils.isEmpty(content)){
                    toast("请填写内容！");
                    return;
                }
                final String regionCode = getRegionCode();
                if(TextUtils.isEmpty(regionCode)){
                    toast("请选择地区！");
                    return;
                }
                final String badHabitsNode = getBadHabitsNode();
                if(TextUtils.isEmpty(badHabitsNode)){
                    toast("请选择栏目！");
                    return;
                }
                mPresenter.addContent();
            }
        });
        mTvNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionsPickerView != null){
                    optionsPickerView.show();
                }else {
                    mPresenter.getBadHabitList();
                }
            }
        });
    }

    @Override
    protected void initData() {
//        mPresenter.getBadHabitList();
    }

    @Override
    public void setUploadProgress(int progress) {
        if (mCircleProgressDialog != null){
            mCircleProgressDialog.setProgress(progress);
        }
    }

    @Override
    public void dissCircleProgressDialog() {
        if (mCircleProgressDialog != null && mCircleProgressDialog.isShowing()){
            mCircleProgressDialog.dismiss();
        }
    }

    @Override
    public String getVideoTitle() {
        return mEtTitle.getText().toString();
    }

    @Override
    public String getContent() {
        return mEtDesc.getText().toString();
    }

    @Override
    public String getRegionCode() {
        return (String) mTvRegion.getTag();
    }

    @Override
    public String getBadHabitsNode() {
        return (String) mTvNode.getTag();
    }

    @Override
    public void showCircleProgressDialog() {
        if (mCircleProgressDialog == null){
            mCircleProgressDialog = new CircleProgressDialog(mContext);
        }
        if (!mCircleProgressDialog.isShowing()){
            mCircleProgressDialog.setProgress(0);
            mCircleProgressDialog.show();
        }
    }

    public void onIvPlayClicked() {
        final String filePath = mPresenter.getFilePath();
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        Intent intent = new Intent(mContext, VideoPreviewActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, filePath);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_REGION_CODE) {
            mTvRegion.setTextRight(data.getStringExtra(Constant.KEY_STRING_1));
            mTvRegion.setTag(data.getStringExtra(Constant.KEY_STRING_2));
        }
    }


    @Override
    public void showBadHabitNode(List<Node> o) {
        optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final Node node = o.get(options1);
                mTvNode.setTextRight(node.getTitle());
                mTvNode.setTag(node.getId());
                mPresenter.setSelectedNodeId(node.getId());
            }
        }).build();
        optionsPickerView.setPicker(o);
        if(!optionsPickerView.isShowing()){
            optionsPickerView.show();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        mPresenter.submitCancel();
    }

    @Override
    protected void onDestroy() {
        if(mCircleProgressDialog!=null&&mCircleProgressDialog.isShowing()){
            mCircleProgressDialog.dismiss();
        }
        super.onDestroy();
    }
}