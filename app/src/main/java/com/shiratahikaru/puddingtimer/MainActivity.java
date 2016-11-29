package com.shiratahikaru.puddingtimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private final MainActivity self = this;

    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("DataSave",MODE_PRIVATE);
        time = pref.getLong("TIME",0 );

        final TextView timerView = (TextView)findViewById(R.id.textView);
        timerView.setText(time2string(time));

        final CountDownTimer ctdTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                // 残り時間を分、秒に分割
                long mm = l / 1000 / 60;
                long ss = l / 1000 % 60;

                timerView.setText(String.format("%1$02d:%2$02d", mm, ss));
            }

            @Override
            public void onFinish() {
                timerView.setText("00:00");
            }
        };

        Button start_button = (Button)findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctdTimer.start();
                //呼び出す日時を設定する
                Calendar triggerTime = Calendar.getInstance();
                //設定した日時で発行するIntentを生成
                triggerTime.add(Calendar.SECOND, Integer.parseInt(String.valueOf(time/10)));    //セットタイマー秒

                Intent intent = new Intent(getApplicationContext(), TimerIntentService.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, triggerTime.getTimeInMillis(), sender);
                startService(intent);
            }
        });
    }

    protected  String time2string(long sencond){
        long mm = sencond / 1000 / 60;
        long ss = sencond / 1000 % 60;
        return String.format("%1$02d:%2$02d", mm, ss);
    }

}
