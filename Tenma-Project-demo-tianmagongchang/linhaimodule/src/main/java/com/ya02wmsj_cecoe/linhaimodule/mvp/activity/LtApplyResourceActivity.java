package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.TextUtils;
import android.widget.EditText;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtResouceEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtApplyResourcePresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LtApplyResourceActivity extends BaseActivity<LtApplyResourcePresenter> implements IView {
    private YLEditTextGroup mEtUnit, mEtName, mEtPhone;
    private YLTextViewGroup mTvStartTime, mTvEndTime;
    private EditText mEtReason;
    private LtResouceEntity mEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_apply_resource_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtApplyResourcePresenter(this);
        mEntity = (LtResouceEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
    }

    @Override
    protected void initView() {
        setTitle("填写申请");
        mEtUnit = findViewById(R.id.et_unit);
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvStartTime = findViewById(R.id.tv_start_time);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mEtReason = findViewById(R.id.et_reason);

        mTvStartTime.setOnClickListener(v -> showTimePickDialog(mTvStartTime));

        mTvEndTime.setOnClickListener(v -> showTimePickDialog(mTvEndTime));

        findViewById(R.id.btn_apply).setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEtUnit.getTextRight())) {
                toast("请输入单位或个人");
                return;
            }
            if (TextUtils.isEmpty(mEtName.getTextRight())) {
                toast("请输入联系人");
                return;
            }
            if (TextUtils.isEmpty(mEtPhone.getTextRight())) {
                toast("请输入联系电话");
                return;
            }
            if (TextUtils.isEmpty(mTvStartTime.getTextRight())) {
                toast("请选择开始时间");
                return;
            }
            if (TextUtils.isEmpty(mTvEndTime.getTextRight())) {
                toast("请选择结束时间");
                return;
            }
            if (TextUtils.isEmpty(mEtReason.getText())) {
                toast("请输入申请理由");
                return;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("csm_id", mEntity.getId());
            map.put("uuid ", Config.getInstance().getUser().getUuid());
            map.put("use_unit", mEtUnit.getTextRight());
            map.put("linkman", mEtName.getTextRight());
            map.put("phone", mEtPhone.getTextRight());
            map.put("reason", mEtReason.getText());
            map.put("s_time", mTvStartTime.getTextRight());
            map.put("e_time", mTvEndTime.getTextRight());

            mPresenter.applyCASource(map);
        });
    }

    @Override
    protected void initData() {

    }

    private void showTimePickDialog(YLTextViewGroup ylTextViewGroup) {
        KeyBoardUtils.closeSoftInput(this);
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
//        endTime.set(1900, 0, 1);  // 默认始于1900-01-01
        endTime.add(Calendar.MONTH, 3);  //三个月之内

        TimePickerView timePickerView = new TimePickerBuilder(this, (date, v) -> ylTextViewGroup.setTextRight(DateUtil.date2Str(date, DateUtil.FORMAT))).setType(new boolean[]{true, true, true, true, true, true})
                .setRangDate(startTime, endTime)
                .setDate(startTime)
                .isDialog(false).build();
        timePickerView.show();
    }
}
