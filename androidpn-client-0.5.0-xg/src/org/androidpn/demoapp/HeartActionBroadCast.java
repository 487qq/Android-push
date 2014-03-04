package org.androidpn.demoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.androidpn.client.Constants;

/**
 * Created by qiuqiaohua on 3/4/14.
 */
public class HeartActionBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            //发送心跳包
            Constants.xmppManager.getConnection().startKeepAliveThread(Constants.xmppManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
