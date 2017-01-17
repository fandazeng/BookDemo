package com.fanda.zeng.bookpratice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zfd on 2017/1/17 0017.
 *  开机启动广播
 */

public class BootCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机了，我可以启动了",Toast.LENGTH_LONG).show();
    }
}
