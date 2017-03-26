package com.fanda.zeng.bookpratice.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;

/**
 * Created by 曾凡达 on 2017/3/26.
 * des 桌面小部件
 */

public class MyAppWidgetProvider extends AppWidgetProvider {

    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.fanda.zeng.bookpratice.action.CLICK";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, intent.getAction());

        // 这里判断是自己的action，做自己的事情，比如小工具被点击了要干啥，这里是做一个动画效果
        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.banana);
                    AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0 ; i<37 ;i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.iv_widget_icon, rotateBitmap(context, bitmap, degree));
                        Intent intentClick = new Intent(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv_widget_icon, pendingIntent);
                        widgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //创建的时候会调用一次
        Log.d(TAG, "onupdate");
        int counter = appWidgetIds.length;
        Log.d(TAG, "counter = " + counter);

        for (int appWidgetId : appWidgetIds) {
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Log.d(TAG, "appWidgetId = " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        // "窗口小部件"点击事件发送的Intent广播
        Intent intentClick = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_widget_icon, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    private Bitmap rotateBitmap(Context context, Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return tmpBitmap;
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        //第一次添加桌面小部件时调用，只调用一次
        Log.d(TAG, "onEnabled");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        //每删除一次桌面小部件就调用一次
        Log.d(TAG, "onDeleted");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        //删除最后一个该类型桌面小部件就调用一次
        Log.d(TAG, "onDisabled");
    }
}
