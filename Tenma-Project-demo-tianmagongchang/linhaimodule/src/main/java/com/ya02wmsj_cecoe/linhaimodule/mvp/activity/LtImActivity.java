package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.drop.DropCover;
import com.netease.nim.uikit.common.ui.drop.DropManager;
import com.netease.nim.yl.main.fragment.SessionListFragment;
import com.netease.nim.yl.main.helper.SystemMessageUnreadManager;
import com.netease.nim.yl.main.reminder.ReminderItem;
import com.netease.nim.yl.main.reminder.ReminderManager;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.FragmentWithTitleAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.IMContactsFragment;

import java.util.Objects;

public class LtImActivity extends BaseViewPagerActivity {
    private static final String TAG = "LtImActivity";

    private TextView mTvBadgedChat;
    private TextView mTvBadgedContact;
    protected DropCover mUnreadCover;


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_im_activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        enableMsgNotification(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        enableMsgNotification(true);
    }

    @Override
    public void onDestroy() {
        unregisterIMObserver();
        super.onDestroy();
    }

    @Override
    public String[] getTitles() {
        return null;
    }

    @Override
    public Fragment[] getFragments() {
        return null;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("聊天消息");
        mUnreadCover = findViewById(R.id.unread_cover);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        String[] titles = {"消息", "联系人"};
        Fragment[] fragments = {new SessionListFragment(), new IMContactsFragment()};
        mViewPager.setAdapter(new FragmentWithTitleAdapter(getSupportFragmentManager(), titles, fragments));
        LayoutInflater from = LayoutInflater.from(this);
        for (int i = 0; i < titles.length; i++) {
            View view = from.inflate(R.layout.ya02wmsj_cecoe_tab_layout_item, null);
            TextView textView = view.findViewById(R.id.tv_title_name);
            textView.setText(titles[i]);
            if (i == 0) {
                textView.setSelected(true);
                mTvBadgedChat = view.findViewById(R.id.tv_count);
            } else {
                textView.setSelected(false);
                mTvBadgedContact = view.findViewById(R.id.tv_count);
            }
            Objects.requireNonNull(mTabLayout.getTabAt(i)).setCustomView(view);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.tv_title_name);
                if (view != null) {
                    view.setSelected(true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.tv_title_name);
                if (view != null) {
                    view.setSelected(false);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        registerIMObserver();
        requestMessageUnreadCount();
        initUnreadCover();
    }

    private void enableMsgNotification(boolean enable) {
        boolean msg = (getCurrentItem() != 0);
        if (enable | msg) {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        } else {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
        }
    }

    private void registerIMObserver() {
        //注册未读消息数量观察者
        ReminderManager.getInstance().registerUnreadNumChangedCallback(mChatUnreadNumChangedCallback);
        //注册/注销系统消息未读数变化
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(mSystemMsgUnreadCountChangedObserver, true);
    }

    private void unregisterIMObserver() {
        ReminderManager.getInstance().unregisterUnreadNumChangedCallback(mChatUnreadNumChangedCallback);
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(mSystemMsgUnreadCountChangedObserver, false);
    }

    /**
     * 聊天消息
     */
    private ReminderManager.UnreadNumChangedCallback mChatUnreadNumChangedCallback = new ReminderManager.UnreadNumChangedCallback() {
        @Override
        public void onUnreadNumChanged(ReminderItem item) {
            KLog.d(TAG, "onUnreadNumChanged: " + item.getUnread());
            updateMsgCount(mTvBadgedChat, item.getUnread());
        }
    };

    /**
     * 系统消息
     */
    private Observer<Integer> mSystemMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            KLog.d(TAG, "onEvent: " + unreadCount);
            SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unreadCount);
            updateMsgCount(mTvBadgedContact, unreadCount);
        }
    };

    /**
     * 查询消息未读数
     */
    private void requestMessageUnreadCount() {
        //聊天
        int chatMsg = NIMClient.getService(MsgService.class).getTotalUnreadCount();
        updateMsgCount(mTvBadgedChat, chatMsg);

        //系统
        int systemMsg = NIMClient.getService(SystemMessageService.class).querySystemMessageUnreadCountBlock();
        SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(systemMsg);
        updateMsgCount(mTvBadgedContact, systemMsg);
    }

    /**
     * 初始化未读红点动画
     */
    private void initUnreadCover() {
        DropManager.getInstance().init(this, mUnreadCover,
                (id, explosive) -> {
                    if (id == null || !explosive) {
                        return;
                    }

                    if (id instanceof RecentContact) {
                        RecentContact r = (RecentContact) id;
                        NIMClient.getService(MsgService.class).clearUnreadCount(r.getContactId(), r.getSessionType());
                    }
                });
    }

    private void updateMsgCount(TextView textView, int count) {
        if (textView == null) {
            return;
        }
        if (count < 1) {
            if (textView.isShown()) {
                textView.setVisibility(View.GONE);
            }

        } else {
            if (!textView.isShown()) {
                textView.setVisibility(View.VISIBLE);
            }
            if (count < 100) {
                textView.setText(count + "");
            } else {
                textView.setText("99+");
            }
        }
    }
}
