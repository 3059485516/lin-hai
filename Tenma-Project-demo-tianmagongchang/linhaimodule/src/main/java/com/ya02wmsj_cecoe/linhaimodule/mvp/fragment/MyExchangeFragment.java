package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.os.Bundle;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.MyExchangeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.MyExchangeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.MyExchangeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.MyExchangePresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.dialog.CommentDialog;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class MyExchangeFragment extends BaseListFragment<MyExchangeContract.Presenter> implements MyExchangeContract.View, MyExchangeAdapter.RefundImp {

    public static MyExchangeFragment create(String code) {
        MyExchangeFragment fragment = new MyExchangeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_STRING_1, code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new MyExchangeAdapter(mActivity, mPresenter.getDataList(), this);
    }

    @Override
    protected void initMVP() {
        mPresenter = new MyExchangePresenter(this, getArguments().getString(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {

    }

    @Override
    public void returnCompleted() {
        toast("退还成功");
    }

    @Override
    public void onRefund(MyExchangeEntity entity) {
        CommentDialog dialog = new CommentDialog(mActivity);
        dialog.show();
        dialog.setEmptyable(false);
        dialog.setCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.getCommentText() != null
                        && dialog.getCommentText().length() > 0) {
                    mPresenter.refund(entity.getOrder_no(), dialog.getCommentText());
                }
                dialog.dismiss();
            }
        });
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
