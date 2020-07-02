package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.contact.ContactChangedObserver;
import com.netease.nim.uikit.api.model.main.LoginSyncDataStatusObserver;
import com.netease.nim.uikit.api.model.main.OnlineStateChangeObserver;
import com.netease.nim.uikit.api.model.user.UserInfoObserver;
import com.netease.nim.uikit.business.contact.core.item.AbsContactItem;
import com.netease.nim.uikit.business.contact.core.item.ItemTypes;
import com.netease.nim.uikit.business.contact.core.model.ContactDataAdapter;
import com.netease.nim.uikit.business.contact.core.model.ContactGroupStrategy;
import com.netease.nim.uikit.business.contact.core.provider.ContactDataProvider;
import com.netease.nim.uikit.business.contact.core.query.IContactDataProvider;
import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nim.uikit.impl.cache.UIKitLogTag;
import com.netease.nim.yl.Group;
import com.netease.nim.yl.contact.activity.UserProfileActivity;
import com.netease.nim.yl.main.activity.GlobalSearchActivity;
import com.netease.nim.yl.main.activity.SystemMessageActivity;
import com.netease.nim.yl.main.activity.TeamListActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.IMContactsAdapter;
import com.ya02wmsj_cecoe.linhaimodule.event.EventRefreshContactsList;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.GroupHelper;
import com.ya02wmsj_cecoe.linhaimodule.utils.ReloadFrequencyControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IMContactsFragment extends TFragment {
    public static final int GROUP_SYSTEM_MSG = -3;
    private static final int GROUP_SECRETARY = -2;
    private static final int GROUP_SEARCH = -1;

    private static final String TAG = "ContactsFragment";

    private Activity mActivity;

    private SmartRefreshLayout mRefreshLayout;

    private ExpandableListView mListView;

    private ContactDataAdapter mLoadAdapter;
    private IMContactsAdapter mIMContactsAdapter;

    private List<Group> mGroupList = new ArrayList<>();
    private RxManager mRxManager = new RxManager();

    private ReloadFrequencyControl reloadControl = new ReloadFrequencyControl();

    private static final class ContactsGroupStrategy extends ContactGroupStrategy {
        public ContactsGroupStrategy() {
            add(ContactGroupStrategy.GROUP_NULL, -1, "");
            addABC(0);
        }
    }

    /**
     * ***************************************** 生命周期 *****************************************
     */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ya02wmsj_cecoe_fragment_contacts, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 界面初始化
        initViews();
        initData();
        // 注册观察者
        registerObserver(true);
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(sysMsgUnreadCountChangedObserver, true);
        registerOnlineStateChangeListener(true);
        // 加载本地数据
        reload(false);
        mRxManager.add(RxBus.getInstance().register(EventRefreshContactsList.class).subscribe(eventRefreshContactsList -> updateList(), throwable -> {
        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerObserver(false);
        registerOnlineStateChangeListener(false);
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(sysMsgUnreadCountChangedObserver, false);
        mRxManager.clear();
    }

    private void initViews() {
        mRefreshLayout = findView(R.id.refresh);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> reload(true));
        mListView = findView(R.id.list);
        mListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            NimUserInfo info = mGroupList.get(groupPosition).getFriends().get(childPosition);
            KLog.d(TAG, "onChildClick: ");
            UserProfileActivity.start(mActivity, info.getAccount(), (ArrayList) GroupHelper.getInstance().getGroupList());
            return true;
        });
        mListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            KLog.d(TAG, "onGroupClick: ");
            Group group = mGroupList.get(groupPosition);
            if (group != null) {
                if (GROUP_SYSTEM_MSG == group.getSort()) {
                    SystemMessageActivity.start(mActivity);
                } else if (GROUP_SECRETARY == group.getSort()) {
                    TeamListActivity.start(mActivity, ItemTypes.TEAMS.NORMAL_TEAM);
                } else if (GROUP_SEARCH == group.getSort()) {
                    GlobalSearchActivity.start(mActivity);
                }
            }
            return false;
        });
    }


    private void initData() {
        IContactDataProvider dataProvider = new ContactDataProvider(ItemTypes.FRIEND);
        mLoadAdapter = new ContactDataAdapter(getActivity(), new ContactsGroupStrategy(), dataProvider) {
            @Override
            protected List<AbsContactItem> onNonDataItems() {
                return new ArrayList<>();
            }

            @Override
            protected void onPreReady() {
            }

            @Override
            protected void onPostLoad(boolean empty, String queryText, boolean all) {
                updateList();
            }
        };
    }


    /**
     * 加载通讯录数据并刷新
     *
     * @param reload true则重新加载数据；false则判断当前数据源是否空，若空则重新加载，不空则不加载
     */
    private void reload(boolean reload) {
        if (!reloadControl.canDoReload(reload)) {
            finishRefresh();
            return;
        }

        if (mLoadAdapter == null) {
            if (getActivity() == null) {
                return;
            }

            initData();
        }

        // 开始加载
        if (!mLoadAdapter.load(reload)) {
            // 如果不需要加载，则直接当完成处理
            onReloadCompleted();
        } else {
            finishRefresh();
        }
    }

    private void onReloadCompleted() {
        finishRefresh();
        if (reloadControl.continueDoReloadWhenCompleted()) {
            // 计划下次加载，稍有延迟
            getHandler().postDelayed(() -> {
                boolean reloadParam = reloadControl.getReloadParam();
                KLog.i(UIKitLogTag.CONTACT, "continue reload " + reloadParam);
                reloadControl.resetStatus();
                reload(reloadParam);
            }, 50);
        } else {
            // 本次加载完成
            reloadControl.resetStatus();
        }

        LogUtil.i(UIKitLogTag.CONTACT, "contact load completed");
    }

    /**
     * *********************************** 用户资料、好友关系变更、登录数据同步完成观察者 *******************************
     */
    private void registerObserver(boolean register) {
        NimUIKit.getUserInfoObservable().registerObserver(userInfoObserver, register);
        NimUIKit.getContactChangedObservable().registerObserver(friendDataChangedObserver, register);
        LoginSyncDataStatusObserver.getInstance().observeSyncDataCompletedEvent(loginSyncCompletedObserver);
    }

    ContactChangedObserver friendDataChangedObserver = new ContactChangedObserver() {
        @Override
        public void onAddedOrUpdatedFriends(List<String> accounts) {
            reloadWhenDataChanged(accounts, "onAddedOrUpdatedFriends", true);
        }

        @Override
        public void onDeletedFriends(List<String> accounts) {
            reloadWhenDataChanged(accounts, "onDeletedFriends", true);
        }

        @Override
        public void onAddUserToBlackList(List<String> accounts) {
            reloadWhenDataChanged(accounts, "onAddUserToBlackList", true);
        }

        @Override
        public void onRemoveUserFromBlackList(List<String> accounts) {
            reloadWhenDataChanged(accounts, "onRemoveUserFromBlackList", true);
        }
    };


    private UserInfoObserver userInfoObserver = accounts -> {
        reloadWhenDataChanged(accounts, "onUserInfoChanged", true, false); // 非好友资料变更，不用刷新界面
    };

    private Observer<Void> loginSyncCompletedObserver = (Observer<Void>) aVoid -> getHandler().postDelayed(() -> reloadWhenDataChanged(null, "onLoginSyncCompleted", false), 50);

    private void reloadWhenDataChanged(List<String> accounts, String reason, boolean reload) {
        reloadWhenDataChanged(accounts, reason, reload, true);
    }


    private void reloadWhenDataChanged(List<String> accounts, String reason, boolean reload, boolean force) {
        if (accounts == null || accounts.isEmpty()) {
            return;
        }

        boolean needReload = false;
        if (!force) {
            // 非force：与通讯录无关的（非好友）变更通知，去掉
            for (String account : accounts) {
                if (NimUIKit.getContactProvider().isMyFriend(account)) {
                    needReload = true;
                    break;
                }
            }
        } else {
            needReload = true;
        }

        if (!needReload) {
            Log.d(UIKitLogTag.CONTACT, "no need to reload contact");
            return;
        }

        // log
        StringBuilder sb = new StringBuilder();
        sb.append("ContactFragment received data changed as [").append(reason).append("] : ");
        if (!accounts.isEmpty()) {
            for (String account : accounts) {
                sb.append(account);
                sb.append(" ");
            }
            sb.append(", changed size=").append(accounts.size());
        }
        Log.i(UIKitLogTag.CONTACT, sb.toString());

        // reload
        reload(reload);
    }

    /**
     * *********************************** 在线状态 *******************************
     */

    OnlineStateChangeObserver onlineStateChangeObserver = new OnlineStateChangeObserver() {
        @Override
        public void onlineStateChange(Set<String> accounts) {
            // 更新
            mLoadAdapter.notifyDataSetChanged();
        }
    };

    private void registerOnlineStateChangeListener(boolean register) {
        if (!NimUIKitImpl.enableOnlineState()) {
            return;
        }
        NimUIKitImpl.getOnlineStateChangeObservable().registerOnlineStateChangeListeners(onlineStateChangeObserver, register);
    }

    public void updateList() {
        groupCreate();
        List<String> accounts = NIMClient.getService(FriendService.class).getFriendAccounts(); // 获取所有好友帐号
        if (accounts != null && accounts.size() != 0) {
            List<NimUserInfo> users = NIMClient.getService(UserService.class).getUserInfoList(accounts); // 获取所有好友用户资
            mGroupList.addAll(GroupHelper.getInstance().grouping(users));
        } else {
            mGroupList.addAll(GroupHelper.getInstance().grouping(null));
        }
        mIMContactsAdapter = new IMContactsAdapter(mActivity, mGroupList);
        mListView.setAdapter(mIMContactsAdapter);
        finishRefresh();
        onReloadCompleted();
    }


    /**
     * 加载对话框------------------------------------------------------------------------------------
     */
    protected ProgressDialog mDialog;

    public void showDialog() {
        showDialog("正在加载...");
    }

    public void showDialog(String message) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mActivity);
        }
        mDialog.setMessage(message);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        if (mRefreshLayout == null) return;
        mRefreshLayout.finishRefresh(0);
    }

    private void groupCreate() {
        mGroupList.clear();
        mGroupList.add(new Group("验证提醒", R.mipmap.ya02wmsj_cecoe_icon_verify_remind, GROUP_SYSTEM_MSG));
        mGroupList.add(new Group("讨论组", R.mipmap.ya02wmsj_cecoe_ic_secretary, GROUP_SECRETARY));
        mGroupList.add(new Group("搜索", R.mipmap.ya02wmsj_cecoe_ic_im_search, GROUP_SEARCH));
    }

    private Observer<Integer> sysMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            if (mIMContactsAdapter != null) {
                mIMContactsAdapter.updateUnreadCount(unreadCount);
            }
        }
    };
}
