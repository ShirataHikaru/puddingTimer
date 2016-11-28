package com.shiratahikaru.puddingtimer;

/**
 * Created by ShirataHikaru on 2016/11/27.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;


public class Notifier extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent sendIntent = new Intent(context, MainActivity.class);
        final PendingIntent sender = getActivity(context, 0, sendIntent, 0);

        //通知オブジェクトの生成
        Notification noti = new NotificationCompat.Builder(context)
                .setTicker("プリンタイマー")
                .setContentTitle("プリンできました！")
                .setContentText("鍋の火を消してください")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{0, 200, 100, 200, 100, 200})
                .setAutoCancel(true)
                .setContentIntent(sender)
                .build();

        Toast.makeText(context, "Notifier", Toast.LENGTH_SHORT).show();
        System.out.println("Notifier");

        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, noti);

    }


}
