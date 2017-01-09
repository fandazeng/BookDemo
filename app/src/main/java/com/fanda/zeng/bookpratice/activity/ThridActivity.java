package com.fanda.zeng.bookpratice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.util.ActivityCollector;

/**
 * Created by 曾凡达 on 2017/1/4.
 * des
 */

public class ThridActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ThridActivity";

    private Button btn_second;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();

        if (savedInstanceState != null) {
            Log.i(TAG, savedInstanceState.getString("save_data"));
        }

        Log.i(TAG, "Tasd id is"+ getTaskId());
    }

    private void initView() {
        btn_second = (Button) findViewById(R.id.btn_second);
        btn_second.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second:
                ActivityCollector.finishAll();
                Process.killProcess(Process.myPid());
                break;
        }
    }

    /**
     * 返回到主界面
     */
    private void returnToMainActivity() {
        Intent intent = getIntent();
        intent.putExtra("return", "return data");
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        returnToMainActivity();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("save_data","save_data");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
