package com.fanda.zeng.bookpratice.database;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by zfd on 2017/1/18 0018.
 * SharePreference 存储与读取练习
 */

public class SharePreferencePraticeActivity extends BaseActivity {

    private EditText et_file_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_pratice);
        et_file_content = (EditText) findViewById(R.id.et_file_content);

        SharedPreferences sharedPreferences = getSharedPreferences("preference", MODE_PRIVATE);
        et_file_content.setText(sharedPreferences.getString("name", "") + sharedPreferences.getInt("age", 0) + sharedPreferences.getBoolean("flag", false));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    /**
     * 保存数据到文件
     */
    private void save() {
        SharedPreferences preferences = getSharedPreferences("preference", MODE_PRIVATE);
        preferences.edit().putString("name", "zengfdanda").putInt("age", 23).putBoolean("flag", true).apply();
    }
}
