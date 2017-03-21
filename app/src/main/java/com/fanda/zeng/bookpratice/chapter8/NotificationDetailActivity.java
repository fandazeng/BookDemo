package com.fanda.zeng.bookpratice.chapter8;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/21.
 * des 通知
 */

public class NotificationDetailActivity extends BaseActivity {

    private Button sendNotification;
    private NotificationManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        sendNotification = (Button) findViewById(R.id.btn_send_notification);
        sendNotification.setText("我是详情界面");
//        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.cancel(1);
    }
}
