package com.fanda.zeng.bookpratice.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanda.zeng.bookpratice.receiver.LoginActivity;
import com.fanda.zeng.bookpratice.util.ActivityCollector;

/**
 * Created by 曾凡达 on 2017/1/5.
 * des 基类
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public static final String ACTION_LOGIN_RECEIVER = "com.fanda.zeng.bookpratice.LOGIN_RECEIVER";


    private IntentFilter loginFilter;
    private OfflineReceiver offlineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName());

        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loginFilter = new IntentFilter(ACTION_LOGIN_RECEIVER);
        offlineReceiver = new OfflineReceiver();
        registerReceiver(offlineReceiver, loginFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver != null) {
            unregisterReceiver(offlineReceiver);
            offlineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class OfflineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("强制下线通知").setMessage("已在其他设备上登录").setCancelable(false)
                    .setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCollector.finishAll();
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    });
            builder.create().show();
        }
    }

}
