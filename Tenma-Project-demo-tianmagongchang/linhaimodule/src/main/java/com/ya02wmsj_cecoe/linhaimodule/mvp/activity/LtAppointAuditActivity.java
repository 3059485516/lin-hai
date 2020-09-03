package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtAppointEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtAppointAuditPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import static com.ya02wmsj_cecoe.linhaimodule.utils.TDevice.getScreenWidth;

public class LtAppointAuditActivity extends BaseActivity<LtAppointAuditPresenter> implements IView {
    private TextView mTvTitle, mTvTopTime, mTvReason, mTvRefuse;
    private YLTextViewGroup mTvResource, mTvUnit, mTvName, mTvPhone, mTvSTime, mTvETime;
    private LtAppointEntity mLtAppointEntity;
    private LinearLayout mWrapBtn;
    private Dialog mRefuseDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_appoint_audit_activity;
    }

    @Override
    protected void initMVP() {
        mLtAppointEntity = (LtAppointEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new LtAppointAuditPresenter(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setTitle("预约审核");
        mTvTitle = findViewById(R.id.tv_title);
        mTvTopTime = findViewById(R.id.tv_top_time);
        mTvReason = findViewById(R.id.tv_reason);
        mTvRefuse = findViewById(R.id.tv_refuse);
        mTvResource = findViewById(R.id.tv_resource);
        mTvUnit = findViewById(R.id.tv_unit);
        mTvName = findViewById(R.id.tv_name);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvSTime = findViewById(R.id.tv_s_time);
        mTvETime = findViewById(R.id.tv_e_time);
        mWrapBtn = findViewById(R.id.wrap_btn);

        mTvTitle.setText("申请" + mLtAppointEntity.getCa_name() + mLtAppointEntity.getSource_name());
        mTvTopTime.setText(mLtAppointEntity.getCtime());
        mTvResource.setTextRight(mLtAppointEntity.getSource_name());
        mTvUnit.setTextRight(mLtAppointEntity.getUse_unit());
        mTvName.setTextRight(mLtAppointEntity.getLinkman());
        mTvPhone.setTextRight(mLtAppointEntity.getPhone());
        mTvSTime.setTextRight(mLtAppointEntity.getS_time());
        mTvETime.setTextRight(mLtAppointEntity.getE_time());
        mTvReason.setText("申请理由：" + mLtAppointEntity.getReason());
        if (!TextUtils.isEmpty(mLtAppointEntity.getReview_msg())) {
            mTvRefuse.setText("驳回理由：" + mLtAppointEntity.getReview_msg());
        } else {
            mTvRefuse.setVisibility(View.GONE);
        }

        if ("已审核".equals(mLtAppointEntity.getStatus())
                || "审核通过".equals(mLtAppointEntity.getStatus())
                || "驳回".equals(mLtAppointEntity.getStatus())) {
            mWrapBtn.setVisibility(View.GONE);
        } else {
            mWrapBtn.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.btn_agree).setOnClickListener(v -> mPresenter.auditSourceApply(mLtAppointEntity.getId() + "", "审核通过", ""));

        findViewById(R.id.btn_refuse).setOnClickListener(v -> showRefuseDialog());
    }

    @Override
    protected void initData() {
    }

    private void showRefuseDialog() {
        if (mRefuseDialog != null) {
            mRefuseDialog.show();
            return;
        }
        mRefuseDialog = new Dialog(this, R.style.BottomDialogStyle);
        mRefuseDialog.setCanceledOnTouchOutside(true);
        mRefuseDialog.setCancelable(true);
        mRefuseDialog.setOnCancelListener(dialog -> {
            if (mRefuseDialog != null) {
                mRefuseDialog.dismiss();
                mRefuseDialog = null;
            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_lt_appoint_audit_refuse_dialog, null);
        EditText et_content = view.findViewById(R.id.et_content);
        view.findViewById(R.id.iv_close).setOnClickListener(v -> {
            mRefuseDialog.dismiss();
            mRefuseDialog = null;
        });
        view.findViewById(R.id.btn_commit).setOnClickListener(v -> {
            if (TextUtils.isEmpty(et_content.getText())) {
                toast("请填写理由");
                return;
            }
            mPresenter.auditSourceApply(mLtAppointEntity.getId() + "", "驳回", et_content.getText().toString());
            mRefuseDialog.dismiss();
            mRefuseDialog = null;
        });
        mRefuseDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = mRefuseDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) getScreenWidth();
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        mRefuseDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRefuseDialog != null) {
            mRefuseDialog.dismiss();
            mRefuseDialog = null;
        }
    }
}
