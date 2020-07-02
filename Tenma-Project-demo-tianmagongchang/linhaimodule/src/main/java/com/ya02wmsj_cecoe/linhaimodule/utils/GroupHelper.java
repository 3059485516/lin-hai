package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.text.TextUtils;

import com.netease.nim.yl.Cons;
import com.netease.nim.yl.Group;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.model.Friend;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yang Shihao
 */
public class GroupHelper {
    private static final String TAG = "GroupHelper";
    private List<Group> mGroupList = new ArrayList<>();
    private Map<String, Group> mGroupMap = new HashMap<String, Group>();

    public List<Group> getGroupList() {
        return mGroupList;
    }

    /**
     * 创建空分组
     */
    public void getRemoteGroupError() {
        List<Group> list = new ArrayList<>();
        list.add(new Group(Cons.DEFAULT_ID, Cons.DEFAULT_GROUP));
        updateGroupList(list);
    }

    public void updateGroupList(List<Group> groupList) {
        mGroupList.clear();
        mGroupList.addAll(groupList);
        mGroupMap.clear();
        for (Group group : groupList) {
            mGroupMap.put(group.getName(), group);
        }
    }

    public List<Group> grouping(List<NimUserInfo> list) {
        for (Group g : mGroupList) {
            g.clearGroup();
        }
        if (mGroupList.size() == 0 || mGroupMap.size() == 0) {
            getRemoteGroupError();
        }
        if (list == null || list.size() == 0) {
            return mGroupList;
        }

        for (NimUserInfo friend : list) {
            String friendGroupName = getFriendGroupName(friend);
            if (TextUtils.isEmpty(friendGroupName)) {
                Group defaultGroup = mGroupMap.get(Cons.DEFAULT_GROUP);
                if (defaultGroup == null) {
                    Group group = new Group("", Cons.DEFAULT_GROUP);
                    group.addFriend(friend);
                    mGroupList.add(0, group);
                    mGroupMap.put(Cons.DEFAULT_GROUP, group);
                } else {
                    defaultGroup.addFriend(friend);
                }
            } else {
                Group temp = mGroupMap.get(friendGroupName);
                if (temp == null) {
                    Group group = new Group("", friendGroupName);
                    group.addFriend(friend);
                    mGroupList.add(group);
                    mGroupMap.put(friendGroupName, group);
                } else {
                    temp.addFriend(friend);
                }
            }
        }
        return mGroupList;
    }

    private String getFriendGroupName(NimUserInfo friend) {
        Friend friendByAccount = NIMClient.getService(FriendService.class).getFriendByAccount(friend.getAccount());
        if (friendByAccount == null) {
            return null;
        }
        Map<String, Object> extension = friendByAccount.getExtension();
        if (extension == null) {
            return null;
        }
        Object o = extension.get("name");
        if (o == null) {
            return null;
        }
        if (!(o instanceof String)) {
            return null;
        }
        return (String) o;
    }

    public static String getFriendAlias(NimUserInfo friend) {
        Friend friendByAccount = NIMClient.getService(FriendService.class).getFriendByAccount(friend.getAccount());
        if (friendByAccount == null) {
            return friend.getName();
        }
        String alias = friendByAccount.getAlias();
        if (TextUtils.isEmpty(alias) || "()".equals(alias)) {
            return friend.getName();
        }
        return alias;
    }

    public static GroupHelper getInstance() {
        return GroupHelper.Holder.INSTANCE;
    }

    private static class Holder {
        public static final GroupHelper INSTANCE = new GroupHelper();
    }
}
