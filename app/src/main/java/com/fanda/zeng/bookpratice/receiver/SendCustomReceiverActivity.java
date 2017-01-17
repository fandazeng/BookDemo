package com.fanda.zeng.bookpratice.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by zfd on 2017/1/17 0017.
 * 发送自定义广播
 */

public class SendCustomReceiverActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_send_standard;
    private Button btn_send_local;
    private Button btn_offline;

    private LocalBroadcastManager localBroadcastManager;
    private MyReceiver myLocalReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_receiver);

        initView();
    }

    private void initView() {
        btn_send_standard = (Button) findViewById(R.id.btn_send_standard);
        btn_send_local = (Button) findViewById(R.id.btn_send_local);
        btn_offline = (Button) findViewById(R.id.btn_offline);
        btn_send_standard.setOnClickListener(this);
        btn_send_local.setOnClickListener(this);
        btn_offline.setOnClickListener(this);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        myLocalReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fanda.zeng.bookpratice.MY_RECEIVER");
        localBroadcastManager.registerReceiver(myLocalReceiver,intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_standard:
                Intent intent = new Intent(this, MyReceiver.class);
                intent.putExtra("receiver", "我是自定义的广播");
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);
                break;
            case R.id.btn_send_local://发送本地广播
                Intent localIntent = new Intent("com.fanda.zeng.bookpratice.MY_RECEIVER");
                localIntent.putExtra("receiver", "我是本地的广播");
                localBroadcastManager.sendBroadcast(localIntent);

                break;
            case R.id.btn_offline://强制下线
                sendBroadcast(new Intent(ACTION_LOGIN_RECEIVER));
                break;
        }
    }
}
