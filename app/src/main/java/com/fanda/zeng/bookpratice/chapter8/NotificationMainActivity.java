package com.fanda.zeng.bookpratice.chapter8;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/21.
 * des 通知
 */

public class NotificationMainActivity extends BaseActivity implements View.OnClickListener {

    private Button sendNotification;
    private NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        sendNotification = (Button) findViewById(R.id.btn_send_notification);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initNotification();
        sendNotification.setOnClickListener(this);
    }

    private void initNotification() {
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        notification = new NotificationCompat.Builder(this)
                .setContentTitle("我是标题").setContentText("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容")
                .setWhen(System.currentTimeMillis())
                .setTicker("我是通知")
                .setSmallIcon(R.drawable.message_left)//小米手机设置图标无效
                .setStyle(new NotificationCompat.BigTextStyle().bigText("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容"))
//                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Orange.ogg")))
//                .setVibrate(new long[]{0,1000,1000,1000})//需要权限 ,静止，振动，静止，振动
//                .setLights(Color.GREEN,1000,1000)
//                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.banana)))
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MIN)//优先级
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.pear_pic))
                .setWhen(System.currentTimeMillis()).setContentIntent(pi).setAutoCancel(true).build();//自动点击消失

    }

    @Override
    public void onClick(View v) {
        manager.notify(1, notification);
    }
}
