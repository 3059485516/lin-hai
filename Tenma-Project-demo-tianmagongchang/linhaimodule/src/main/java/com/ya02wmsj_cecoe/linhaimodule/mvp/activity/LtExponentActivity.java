package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.event.ExchangeResult;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtExponentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;

public class LtExponentActivity extends BaseViewPagerActivity<LtExponentPresenter> implements LtChooseLtContract.View {

    @Override
    public String[] getTitles() {
        return new String[0];
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[0];
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtExponentPresenter(this);
        mPresenter.getAllCABranchList();
    }

    @Override
    protected void initView() {
        setTitle("礼堂指数");
        setMenuText("选择年份");
    }

    @Override
    public void update(List<LtStreetEntity> list) {
        setViewPagerData(mPresenter.getTitles(), mPresenter.getFragments());
    }

    @Override
    public void onMenuClicked() {
        initTimePicker();
    }

    private TimePickerView pvTime;

    private void initTimePicker() {
        if (pvTime == null) {
            Calendar startDate = Calendar.getInstance();
            startDate.add(Calendar.YEAR, -3);
            Calendar endDate = Calendar.getInstance();
            pvTime = new TimePickerBuilder(this, (date, v) -> {
                String strDate = DateUtil.date2Str(date, DateUtil.FORMAT_Y);
                setMenuText(strDate);
                EventBus.getDefault().post(new ExchangeResult());
            }).setType(new boolean[]{true, false, false, false, false, false}).setLabel("年", "月", "日", "时", "分", "秒")
                    .isCenterLabel(false).setDividerColor(Color.DKGRAY)
                    .setRangDate(startDate, endDate).setDate(endDate).setDecorView(null).build();
        }
        pvTime.show();
    }


    public String getSelectDate() {
        String title = getMenuText();
        if (TextUtils.isEmpty(title) || "选择年份".equals(title)) {
            return "";
        } else {
            return title;
        }
    }
}
