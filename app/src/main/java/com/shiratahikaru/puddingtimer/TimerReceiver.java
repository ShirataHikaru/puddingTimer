package com.shiratahikaru.puddingtimer;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class TimerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        Intent intent = new Intent(context, AlertDialogActivity.class);
        intent.putExtra("TIME",intent.getStringExtra("TIME"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
