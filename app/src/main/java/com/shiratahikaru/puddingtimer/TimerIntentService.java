package com.shiratahikaru.puddingtimer;

import android.app.IntentService;

/**
 * Created by ShirataHikaru on 2016/11/28.
 */

import android.content.Intent;
import android.os.SystemClock;
import android.content.SharedPreferences;

public class TimerIntentService extends IntentService {

    public TimerIntentService(String name) {
        super(name);
    }

    public TimerIntentService() {
        super("TimerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent data) {
        SharedPreferences pref = getSharedPreferences("DataSave",MODE_PRIVATE);
        long time = pref.getLong("TIME",0 );
        SystemClock.sleep(time);

        Intent intent = new Intent();
        intent.setAction("TIMER_FINISHED");
        sendBroadcast(intent);
    }
}