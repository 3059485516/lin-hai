package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.ServiceConcatEntity;

import java.util.List;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/24 2:21 PM
 * desc   : EMPTY
 * ================================================
 */
public class ServiceConcat2Adapter extends BaseQuickAdapter<ServiceConcatEntity, BaseViewHolder> {
    public ServiceConcat2Adapter(@Nullable List<ServiceConcatEntity> data) {
        super(R.layout.ya02wmsj_cecoe_item_service_concat2,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ServiceConcatEntity item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.getView(R.id.iv_call_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(item.getPhone());
            }
        });
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
