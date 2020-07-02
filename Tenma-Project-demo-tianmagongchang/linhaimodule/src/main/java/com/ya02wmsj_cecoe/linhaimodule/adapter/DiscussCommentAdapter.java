package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.content.Context;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.bean.CommentEntity;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class DiscussCommentAdapter extends CommonAdapter<CommentEntity> {
    private IReplyComment mInterface;

    public DiscussCommentAdapter(Context context, List<CommentEntity> datas, IReplyComment callback) {
        super(context, R.layout.ya02wmsj_cecoe_item_comment_bet, datas);
        mInterface = callback;
    }

    public interface IReplyComment {
        void replyComment(CommentEntity commentEntity);
    }

    @Override
    protected void convert(ViewHolder holder, CommentEntity commentEntity, int position) {
        holder.setText(R.id.tv_name, commentEntity.getName());
        holder.setText(R.id.tv_time, commentEntity.getCreate_time());
        holder.setText(R.id.tv_content, commentEntity.getContent());
        holder.setText(R.id.tv_reply, "回复（" + commentEntity.getReply_num() + "）");
        ImageManager.getInstance().loadCircleImage(mContext, commentEntity.getPic_url(), R.mipmap.ya02wmsj_cecoe_head, holder.getView(R.id.iv_head));
//        RecyclerView rvPic = holder.getView(R.id.rv_pic);
//        if (!TextUtils.isEmpty(commentEntity.getPic_url())) {
//            holder.setVisible(R.id.rv_pic, true);
//            String[] urls = commentEntity.getPic_url().split(",");
//            rvPic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//            CommentInnerPicAdapter innerPicAdapter = new CommentInnerPicAdapter(mContext, Arrays.asList(urls));
//            rvPic.setAdapter(innerPicAdapter);
//        } else {
//            holder.setVisible(R.id.rv_pic, false);
//        }
        holder.getView(R.id.tv_reply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  回复评论
                if (mInterface != null) {
                    mInterface.replyComment(commentEntity);
                }
            }
        });
    }
}
