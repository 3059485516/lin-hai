package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishOpinionContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.PublishOpinionPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.PictureSelectorUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.HashMap;
import java.util.Map;


import static android.app.Activity.RESULT_OK;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class FragmentPublishOpinion extends BaseFragment<PublishOpinionContract.Presenter> implements PublishOpinionContract.View {
    protected YLEditTextGroup mEtTitle;

    protected EditText mEtDesc;

    protected RecyclerView mRvPic;

    protected ImageAdapter mImgAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_opinion;
    }

    @Override
    protected void initMVP() {
        // TODO: 2020/8/5
        mPresenter = new PublishOpinionPresenter(this,null);
    }

    @Override
    protected void initView() {
        mEtTitle = mRootView.findViewById(R.id.et_title);
        mEtDesc = mRootView.findViewById(R.id.et_desc);
        mRvPic = mRootView.findViewById(R.id.rv_pic);

        mRvPic.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mImgAdapter = new ImageAdapter(mActivity, mPresenter.getImageList());
        mImgAdapter.setMax(9);
        mRvPic.setAdapter(mImgAdapter);
        mImgAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int id = view.getId();
                if (R.id.iv_image == id) {
                    PictureSelectorUtils.getImageMultipleOption(mActivity, mPresenter.getImageList(), 9, PictureConfig.TYPE_IMAGE);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRootView.findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> params = new HashMap<>();
                params.put("show_type", "评议");
                params.put("title", mEtTitle.getTextRight());
                params.put("contents", mEtDesc.getText().toString());
                mPresenter.commit(params);
            }
        });
    }

    @Override
    protected void initData() {

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
        }
    }

    @Override
    public void dissCircleProgressDialog() {
        // TODO: 2020/8/5
    }

    @Override
    public void setUploadProgress(int progress) {
        // TODO: 2020/8/5
    }

    @Override
    public String getContentTitle() {
        // TODO: 2020/8/5
        return null;
    }

    @Override
    public void showCircleProgressDialog() {
        // TODO: 2020/8/5
    }
}
