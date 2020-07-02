package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtEvaluationPublishContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtEvaluationPublishPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LtEvaluationPublishActivity extends BaseActivity<LtEvaluationPublishPresenter> implements LtEvaluationPublishContract.View {
    private YLTextViewGroup mTvType, mTvTime;
    private YLEditTextGroup mEtTitle;
    private EditText mEtContent;
    private RecyclerView mRvPic;

    private ImageAdapter mImgAdapter;
    private static final int REQUEST_CODE = 100;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_evaluation_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtEvaluationPublishPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("上报");
        setMenuText("提交");
        mTvType = findViewById(R.id.tv_type);
        mTvTime = findViewById(R.id.tv_time);
        mEtTitle = findViewById(R.id.et_title);
        mEtContent = findViewById(R.id.et_content);
        mRvPic = findViewById(R.id.rv_pic);
        mRvPic.setLayoutManager(new GridLayoutManager(this, 4));
        mImgAdapter = new ImageAdapter(this, mPresenter.getImageList());
        mImgAdapter.setMax(9);
        mRvPic.setAdapter(mImgAdapter);
        mImgAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int id = view.getId();
                if (R.id.iv_image == id) {
                    PictureSelectorUtils.getImageMultipleOption(mContext, mPresenter.getImageList(), 9, PictureConfig.TYPE_IMAGE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickDialog(mTvTime);
            }
        });

        mTvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivityForResult(new Intent(mContext, LtEvaTypeListActivity.class), REQUEST_CODE);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMenuClicked() {
        if (mTvType.getTag() == null) {
            toast("请选择测评项");
            return;
        }
        if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
            toast("请填写标题");
            return;
        }
        if (TextUtils.isEmpty(mTvTime.getTextRight())) {
            toast("请选择时间");
            return;
        }
        if (TextUtils.isEmpty(mEtContent.getText())) {
            toast("请填写描述");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("er_id", mTvType.getTag());
        map.put("title", mEtTitle.getTextRight());
        map.put("content", mEtContent.getText());
        map.put("ctime", mTvTime.getTextRight());
        mPresenter.caEvaApply(map);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PictureConfig.TYPE_IMAGE) {
            mPresenter.getImageList().clear();
            mPresenter.getImageList().addAll(PictureSelector.obtainMultipleResult(data));
            mImgAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE) {
            LtEvaEntity evaEntity = (LtEvaEntity) data.getSerializableExtra(Constant.KEY_BEAN);
            if (evaEntity != null) {
                mTvType.setTextRight(evaEntity.getTitle());
                mTvType.setTag(evaEntity.getId() + "");
            }
        }
    }

    @Override
    public void showRulesDialog() {

    }

    private void showTimePickDialog(YLTextViewGroup ylTextViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.YEAR, 1);  //一年之内

        TimePickerView timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                ylTextViewGroup.setTextRight(DateUtil.date2Str(date, DateUtil.FORMAT));
            }
        }).setType(new boolean[]{true, true, true, true, true, true})
                .setRangDate(startTime, endTime)
                .setDate(startTime)
                .isDialog(false).build();
        timePickerView.show();
    }
}
