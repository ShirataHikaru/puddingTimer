package com.shiratahikaru.puddingtimer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;


/**
 * Created by ShirataHikaru on 2016/11/13.
 */

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button submit_button = (Button) findViewById(R.id.button);

        final RadioGroup hardness = (RadioGroup)findViewById(R.id.Hardness);
        final RadioGroup amount = (RadioGroup)findViewById(R.id.Amout);
        final RadioGroup cup = (RadioGroup)findViewById(R.id.Cup);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = hardness.getCheckedRadioButtonId();
                String data[] = new String[3];
                Intent intent = new Intent(getApplication(),MainActivity.class);

                int checkedNum = 0;
                if (-1 != checkedId) {
                    data[0] = (String)((RadioButton)findViewById(checkedId)).getText();
                    checkedNum++;
                } else {
                    Toast.makeText(SettingActivity.this, "量が選択されていません", Toast.LENGTH_SHORT).show();
                }

                checkedId = amount.getCheckedRadioButtonId();
                if (-1 != checkedId) {
                    data[1] = (String)((RadioButton)findViewById(checkedId)).getText();
                    checkedNum++;
                } else {
                    Toast.makeText(SettingActivity.this, "固さが選択されていません", Toast.LENGTH_SHORT).show();
                }

                checkedId = cup.getCheckedRadioButtonId();
                if (-1 != checkedId) {
                    data[2] = (String)((RadioButton)findViewById(checkedId)).getText();
                    checkedNum++;
                } else {
                    Toast.makeText(SettingActivity.this, "器の種類が選択されていません", Toast.LENGTH_SHORT).show();
                }

                if(checkedNum == 3){
                    intent.putExtra("TIME", setTime(data));
                    startActivity(intent);
                }
            }
        });
    }


    protected long setTime(String[] data){
        long time = 9*60;
        switch (data[0]){
            case "固め":time += 60;break;
            case "普通":break;
            case "トロトロ":time -= 30;break;
        }
        switch (data[1]){
            case "多め":time += 30;break;
            case "普通":;break;
            case "少なめ":time -= 30;break;
        }
        switch (data[2]){
            case "陶器":time += 10;break;
            case "ガラス・金属":break;
            case "プラスチック":time -= 10;break;
        }
        System.out.println(time*10);
        return time*10;
    }
}

