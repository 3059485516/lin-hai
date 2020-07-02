package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyAdvanceEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.JumpUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class LtMyAdvanceAdapter extends CommonAdapter<LtMyAdvanceEntity> {
    public LtMyAdvanceAdapter(Context context, List<LtMyAdvanceEntity> datas) {
        super(context, R.layout.ya02wmsj_cecoe_item_lt_my_advance, datas);
    }

    @Override
    protected void convert(ViewHolder holder, LtMyAdvanceEntity o, int position) {
        holder.setText(R.id.tv_name, o.getCa_name());
        holder.setText(R.id.tv_time, o.getCtime());
        holder.setText(R.id.tv_content, o.getContent());
        LinearLayout layout = holder.getView(R.id.wrap_pic);
        if (o.getPic() == null || o.getPic().size() == 0) {
            holder.setVisible(R.id.wrap_pic, false);
        } else {
            holder.setVisible(R.id.wrap_pic, true);
            for (int i = 0; i < o.getPic().size(); i++) {
                if (i > 2) break;
                RatioImageView imageView = (RatioImageView) layout.getChildAt(i);
                ImageManager.getInstance().loadImage(mContext, o.getPic().get(i), imageView);
            }
        }
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.gotoPreviewImageActivity(mContext, o.getPic(), null, 0);
            }
        });
    }
}
