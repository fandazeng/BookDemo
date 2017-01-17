package com.fanda.zeng.bookpratice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zfd on 2017/1/17 0017.
 * 自定义广播
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("receiver"), Toast.LENGTH_SHORT).show();
//        abortBroadcast();//截断广播，不让继续传递
    }
}
