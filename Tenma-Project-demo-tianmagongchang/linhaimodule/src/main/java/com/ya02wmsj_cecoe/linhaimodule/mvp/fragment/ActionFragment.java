package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ActionAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseListFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.KeyBoardUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class ActionFragment extends BaseListFragment<ActionContract.Presenter> implements ActionContract.View {
    protected ImageView ivSearch;
    protected EditText etSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_action;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ActionAdapter(mActivity, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionPresenter(this);
    }

    @Override
    protected void initView() {
        setDefaultItemDecoration();
        etSearch = mRootView.findViewById(R.id.et_search);
        ivSearch = mRootView.findViewById(R.id.iv_search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                KeyBoardUtils.closeSoftInput(getActivity());
                mPresenter.getPageData(true);
            }
        });

        ivSearch.setOnClickListener(v -> {
            KeyBoardUtils.closeSoftInput(getActivity());
            mPresenter.getPageData(true);
        });
    }

    @Override
    public String getSearchStr() {
        String searchStr = etSearch.getText().toString();
        if (TextUtils.isEmpty(searchStr)){
            searchStr = "";
        }
        return searchStr;
    }

}
