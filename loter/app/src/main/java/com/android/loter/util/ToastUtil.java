package com.android.loter.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loter.R;

public class ToastUtil {

    private static Toast toast;
    private static long lastToastTime;

    public static void Infotoast(Context mContext, String msg) {
        if(mContext == null || msg == null){
            return;
        }
        long curTime = System.currentTimeMillis();
        if (curTime - lastToastTime > 1000) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.toast, null);
            TextView txtView_Context = (TextView) view
                    .findViewById(R.id.txt_context);
            txtView_Context.setText(msg);
            toast = new Toast(mContext);
            toast.setGravity(Gravity.CENTER, 0, 0);

            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
        lastToastTime = curTime;
    }

}
