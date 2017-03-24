package com.fanda.zeng.bookpratice.service;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/22.
 * des 线程练习
 */

public class ThreadActivity extends BaseActivity implements View.OnClickListener {

    private static final int UPDATE_TEXT = 1;

    private Button send;
    private TextView showText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    showText.setText((CharSequence) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initViews();
    }

    private void initViews() {
        send = (Button) findViewById(R.id.btn_send);
        showText = (TextView) findViewById(R.id.tv_show_text);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        message.obj = "我显示出来了";
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }
}
