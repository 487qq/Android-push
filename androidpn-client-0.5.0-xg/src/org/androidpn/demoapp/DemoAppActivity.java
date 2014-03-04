/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.demoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import org.androidpn.client.ServiceManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This is an androidpn client demo application.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class DemoAppActivity extends Activity {
    AlarmManager am;
    PendingIntent pi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("DemoAppActivity", "onCreate()...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Settings
        Button okButton = (Button) findViewById(R.id.btn_settings);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ServiceManager.viewNotificationSettings(DemoAppActivity.this);
            }
        });

        // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.notification);
        serviceManager.startService();
    }


    public void registerAlarmManager(){
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent=new Intent();
        intent.setClass(this,HeartActionBroadCast.class);
        pi = PendingIntent.getBroadcast(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        long firstime= SystemClock.elapsedRealtime();
        /**
         * 此处最好不要用RTC_WARKUP，因为这个属性部分机型不支持（小米）
         */
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,  30 * 1000, pi);
    }

}