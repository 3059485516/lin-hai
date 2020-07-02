package com.tenma.ventures.navigation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tenma.ventures.usercenter.UserCenterActivity;

/**
 * Created by wcc on 2018/8/3.
 */

public class LocalReceiver extends BroadcastReceiver{
    private final String ACTION_BOOT = "com.nyl.orderlybroadcast.AnotherBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT.equals(intent.getAction())){
            context.startActivity(new Intent(context, UserCenterActivity.class));
        }
    }
}
