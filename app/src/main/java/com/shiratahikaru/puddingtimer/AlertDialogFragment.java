package com.shiratahikaru.puddingtimer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder
                .setTitle("プリンが完成しました")
                .setMessage("鍋の火を止めてください！")
                .setPositiveButton("ok!", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        startActivity(intent);
                    }
                })
                .create();

        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }
}