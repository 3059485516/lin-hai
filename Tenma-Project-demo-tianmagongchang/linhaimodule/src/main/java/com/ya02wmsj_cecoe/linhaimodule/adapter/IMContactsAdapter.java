package com.ya02wmsj_cecoe.linhaimodule.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.yl.Group;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.IMContactsFragment;
import com.ya02wmsj_cecoe.linhaimodule.utils.GroupHelper;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;

import java.util.List;

/**
 * @author Yang Shihao
 */
public class IMContactsAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Group> mGroupList;
    private TextView mTvUnreadSystemMsg;
    private int mUnreadCount = 0;

    public IMContactsAdapter() {
    }

    public IMContactsAdapter(Context context, List<Group> groupList) {
        mContext = context;
        mGroupList = groupList;
    }


    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupList.get(groupPosition).getFriends().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupList.get(groupPosition).getFriends().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_item_contact_group, parent, false);
            viewHolder = new GroupViewHolder();
            viewHolder.rlStyle1 = $(convertView, R.id.rl_style1);
            viewHolder.ivArrow = $(convertView, R.id.iv_arrow);
            viewHolder.tvGroupName = $(convertView, R.id.tv_group_name);
            viewHolder.tvCount = $(convertView, R.id.tv_count);
            viewHolder.rlStyle2 = $(convertView, R.id.rl_style2);
            viewHolder.ivHead = $(convertView, R.id.iv_head);
            viewHolder.tvName = $(convertView, R.id.tv_name);
            viewHolder.tvUnreadSystemMsg = $(convertView, R.id.tv_red_num);
            viewHolder.line = $(convertView, R.id.line);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        final Group group = mGroupList.get(groupPosition);
        if (group.getSort() < 0) {
            viewHolder.rlStyle1.setVisibility(View.GONE);
            viewHolder.rlStyle2.setVisibility(View.VISIBLE);
            viewHolder.tvName.setText(group.getName());
            viewHolder.ivHead.setImageResource(group.getIcon());
            convertView.setTag(R.id.yl_tag_group, -1);
            if (group.getSort() == IMContactsFragment.GROUP_SYSTEM_MSG) {
                setUnreadSystemMsgCount(viewHolder.tvUnreadSystemMsg);
            }
        } else {
            viewHolder.rlStyle1.setVisibility(View.VISIBLE);
            viewHolder.rlStyle2.setVisibility(View.GONE);
            viewHolder.tvGroupName.setText(group.getName());
            viewHolder.tvCount.setText(group.getFriends().size() + "");
            if (isExpanded) {
                viewHolder.ivArrow.setImageResource(R.drawable.ya02wmsj_cecoe_ic_expand_more_24dp);
            } else {
                viewHolder.ivArrow.setImageResource(R.drawable.ya02wmsj_cecoe_ic_chevron_right_24dp);
            }
            convertView.setTag(R.id.yl_tag_group, groupPosition);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ya02wmsj_cecoe_item_contact_child, parent, false);
            viewHolder = new ChildViewHolder();
            viewHolder.tvFriendName = $(convertView, R.id.tv_friend_name);
            viewHolder.ivHead = $(convertView, R.id.iv_head);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        NimUserInfo friend = mGroupList.get(groupPosition).getFriends().get(childPosition);
        viewHolder.tvFriendName.setText(GroupHelper.getFriendAlias(friend));
        if (TextUtils.isEmpty(friend.getAvatar())) {
            ImageManager.getInstance().loadCircleImage(mContext, R.mipmap.ya02wmsj_cecoe_head, viewHolder.ivHead);
        } else {
            ImageManager.getInstance().loadCircleImage(mContext, friend.getAvatar(),R.mipmap.ya02wmsj_cecoe_head, viewHolder.ivHead);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        RelativeLayout rlStyle1;
        ImageView ivArrow;
        TextView tvGroupName;
        TextView tvCount;
        RelativeLayout rlStyle2;
        ImageView ivHead;
        TextView tvName;
        TextView tvUnreadSystemMsg;
        TextView line;
    }

    static class ChildViewHolder {
        ImageView ivHead;
        TextView tvFriendName;
    }

    protected <T extends View> T $(View layoutView, @IdRes int resId) {
        return (T) layoutView.findViewById(resId);
    }

    private void setUnreadSystemMsgCount(TextView textView) {
        mTvUnreadSystemMsg = textView;
        mUnreadCount = NIMClient.getService(SystemMessageService.class).querySystemMessageUnreadCountBlock();
        updateUnreadCount(mUnreadCount);
    }

    @SuppressLint("SetTextI18n")
    public void updateUnreadCount(int unreadCount) {
        mUnreadCount = unreadCount;
        if (mTvUnreadSystemMsg == null) {
            return;
        }
        if (mUnreadCount < 1) {
            mTvUnreadSystemMsg.setVisibility(View.GONE);
        } else if (mUnreadCount < 100) {
            mTvUnreadSystemMsg.setVisibility(View.VISIBLE);
            mTvUnreadSystemMsg.setText(mUnreadCount + "");
        } else {
            mTvUnreadSystemMsg.setVisibility(View.VISIBLE);
            mTvUnreadSystemMsg.setText("99");
        }
    }
}
