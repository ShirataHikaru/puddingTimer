package com.shiratahikaru.puddingtimer;

/**
 * Created by ShirataHikaru on 2016/11/27.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AlertDialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.show(getSupportFragmentManager(), "alert_dialog");
    }
}
