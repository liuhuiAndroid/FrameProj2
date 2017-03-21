package com.android.loter.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.loter.R;
import com.android.loter.inter.OnRedPacketClickListener;
import com.android.loter.util.ThreadManager;
import com.android.loter.util.TimeUtil;

/**
 * Created by we-win on 2017/3/21.
 */

public class RedPacketDialog extends Dialog {


    private Context mContext;
    private TextView mTextView;

    public RedPacketDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public RedPacketDialog(Context context) {
        this(context, R.style.RedPacketDialog);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_red_packet, null);
        setContentView(view);
        view.findViewById(R.id.dialog_img_big).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRedPacketClickListener.dialogDissmiss();
            }
        });
        mTextView = (TextView) view.findViewById(R.id.tv_time);
        mTextView.setText(TimeUtil.getFullTime());
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                while (loop) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        });
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(TimeUtil.getFullTime());
        }
    };

    private OnRedPacketClickListener mOnRedPacketClickListener;

    public void setOnRedPacketClickListener(OnRedPacketClickListener onRedPacketClickListener) {
        mOnRedPacketClickListener = onRedPacketClickListener;
    }
}
