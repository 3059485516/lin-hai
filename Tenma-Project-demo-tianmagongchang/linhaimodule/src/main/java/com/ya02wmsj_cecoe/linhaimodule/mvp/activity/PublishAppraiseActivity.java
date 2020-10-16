package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishAppraiseContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.PublishAppraisePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.AppraiseOptionView;
import com.ya02wmsj_cecoe.linhaimodule.widget.AppraiseOptionView2;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by BenyChan on 2019-08-07.
 */
public class PublishAppraiseActivity extends BaseActivity<PublishAppraiseContract.Presenter> implements PublishAppraiseContract.View {
    protected YLTextViewGroup mTvType;

    protected YLEditTextGroup mEtTitle;

    protected YLTextViewGroup mTvStartTime;

    protected YLTextViewGroup mTvEndTime;

    protected YLTextViewGroup mTvIcon;

    protected EditText mEtDesc;

    protected LinearLayout mLayAdd;

    protected Button mBtnAdd;

    protected Button mBtnCommit;

    private final int REQUEST_ICON_CODE = 101;

    private int mOptionCount = 0;
    private int mCurrentOptionIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_appraise;
    }

    @Override
    protected void initMVP() {
        mPresenter = new PublishAppraisePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("网络评议");
        mTvType = findViewById(R.id.tv_type);
        mEtTitle = findViewById(R.id.et_title);
        mTvStartTime = findViewById(R.id.tv_start_time);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mTvIcon = findViewById(R.id.tv_icon);
        mEtDesc = findViewById(R.id.et_desc);
        mLayAdd = findViewById(R.id.lay_add);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnCommit = findViewById(R.id.btn_commit);

        mTvType.setOnClickListener(v -> {
            showTypePickDialog(Arrays.asList("评选", "征询"));
        });
        mTvStartTime.setOnClickListener(v -> {
            showTimePickDialog(true);
        });
        mTvEndTime.setOnClickListener(v -> {
            showTimePickDialog(false);
        });
        mTvIcon.setOnClickListener(v -> {
            // 选择封面图片
            PictureSelectorUtils.getImageSingleOption(mContext, REQUEST_ICON_CODE);
        });
        mBtnAdd.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mTvType.getTextRight())) {
                toast("请先选择类型");
                return;
            }
            // 新增选项
            mOptionCount++;
            if ("评选".equals(mTvType.getTextRight())) {
                AppraiseOptionView view = new AppraiseOptionView(mContext);
                view.getTitleView().setText("选项" + mOptionCount);
                view.getOptionPicView().setOnClickListener(v1 -> {
                    mCurrentOptionIndex = mOptionCount - 1;
                    PictureSelectorUtils.getImageSingleOption(mContext, PictureConfig.CHOOSE_REQUEST);
                });
                mLayAdd.addView(view);
            } else {
                AppraiseOptionView2 view = new AppraiseOptionView2(mContext);
                view.getTitleView().setText("选项" + mOptionCount);
                mLayAdd.addView(view);
            }
        });
        mBtnCommit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mTvType.getTextRight())) {
                toast("请选择类型");
                return;
            }
            if (TextUtils.isEmpty(mEtTitle.getTextRight())) {
                toast("标题不能为空");
                return;
            }
            if (TextUtils.isEmpty(mTvStartTime.getTextRight())) {
                toast("开始时间不能为空");
                return;
            }
            if (TextUtils.isEmpty(mTvEndTime.getTextRight())) {
                toast("结束时间不能为空");
                return;
            }
            if (TextUtils.isEmpty(mEtDesc.getText().toString())) {
                toast("描述不能为空");
                return;
            }
            if (TextUtils.isEmpty(Config.getInstance().getRegionCode())) {
                gotoActivity(BindRegionActivity.class);
            }
            // 提交
            Map<String, String> map = new HashMap<>();
            List<String> imagePaths = new ArrayList<>();
            map.put("node_id", "33");        // 我的评议 32; 网络评议 33
            map.put("region_code", Config.getInstance().getRegionCode());
            map.put("name", mEtTitle.getTextRight());
            map.put("content", mEtDesc.getText().toString());
            map.put("start_time", mTvStartTime.getTextRight());
            map.put("end_time", mTvEndTime.getTextRight());
            map.put("form_id", (String) mTvType.getTag());
            map.put("icon_path", TextUtils.isEmpty(mTvIcon.getTextRight()) ? "" : mTvIcon.getTextRight());

            if ("评选".equals(mTvType.getTextRight())) {
                StringBuilder sb_desc = new StringBuilder();
                StringBuilder sb_title = new StringBuilder();
                StringBuilder sb_id = new StringBuilder();
                StringBuilder sb_pic = new StringBuilder();
                for (int i = 0; i < mLayAdd.getChildCount(); i++) {
                    AppraiseOptionView view = (AppraiseOptionView) mLayAdd.getChildAt(i);
                    sb_pic.append(view.getOptionPicView().getTextRight()).append("!!");
                    sb_title.append(view.getOptionNameView().getTextRight()).append("!!");
                    sb_desc.append(view.getDescView().getText()).append("!!");
                    sb_id.append(view.getOptionId()).append("!!");
                    imagePaths.add(view.getOptionPicView().getTextRight());
                }
                StringBuilder result = new StringBuilder();
                result.append(sb_pic).append("@@")
                        .append(sb_title).append("@@")
                        .append(sb_desc).append("@@")
                        .append(sb_id);
                map.put("votes", result.toString());
            } else {
                StringBuilder sb_desc = new StringBuilder();
                StringBuilder sb_id = new StringBuilder();
                for (int i = 0; i < mLayAdd.getChildCount(); i++) {
                    AppraiseOptionView2 view2 = (AppraiseOptionView2) mLayAdd.getChildAt(i);
                    sb_desc.append(view2.getOptionNameView().getTextRight()).append("!!");
                    sb_id.append(view2.getOptionId()).append("!!");
                }
                String result = sb_desc + "@@" + sb_id;
                map.put("options", result);
            }
            mPresenter.commit(map, imagePaths);
        });
    }

    private void showTypePickDialog(List<String> list) {
        KeyBoardUtils.closeSoftInput(mContext);
        OptionsPickerView optionPicker = new OptionsPickerBuilder(mContext, (options1, options2, options3, v) -> {
            if (!list.get(options1).equals(mTvType.getTextRight())) {
                mLayAdd.removeAllViews();
                mOptionCount = 0;
                mCurrentOptionIndex = 0;
            }
            mTvType.setTextRight(list.get(options1));
            if ("评选".equals(list.get(options1))) {
                mTvType.setTag("1");    //评选id = 1
            } else {
                mTvType.setTag("4");    //征询id = 4
            }

        }).build();
        optionPicker.setPicker(list);
        if (!optionPicker.isShowing()) {
            optionPicker.show();
        }
    }

    private void showTimePickDialog(boolean isStart) {
        KeyBoardUtils.closeSoftInput(this);
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.set(1900, 0, 1);  // 默认始于1900-01-01

        TimePickerView timePickerView = new TimePickerBuilder(mContext, (date, v) -> {
            if (isStart) {
                mTvStartTime.setTextRight(DateUtil.date2Str(date, DateUtil.FORMAT_YMD));
            } else {
                mTvEndTime.setTextRight(DateUtil.date2Str(date, DateUtil.FORMAT_YMD));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startTime, endTime)
                .setDate(endTime)
                .isDialog(false).build();
        timePickerView.show();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (PictureConfig.CHOOSE_REQUEST == requestCode) {
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if (list != null && list.size() > 0) {
                String pic_path = list.get(0).getCompressPath();
                AppraiseOptionView view = (AppraiseOptionView) mLayAdd.getChildAt(mCurrentOptionIndex);
                view.getOptionPicView().setTextRight(pic_path);
                view.setTag(list.get(0));
            }
        } else if (REQUEST_ICON_CODE == requestCode) {
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if (list != null && list.size() > 0) {
                String pic_path = list.get(0).getCompressPath();
                mTvIcon.setTextRight(pic_path);
                mTvIcon.setTag(list.get(0));
            }
        }
    }
}
