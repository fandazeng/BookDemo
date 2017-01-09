package com.fanda.zeng.bookpratice.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;

/**
 * Created by 曾凡达 on 2017/1/9.
 * des UI练习第一个界面
 */

public class UIFirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_ui_alert;
    private Button btn_ui_progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_first);
        initView();
    }

    private void initView() {
        btn_ui_alert = (Button) findViewById(R.id.btn_ui_alert);
        btn_ui_progress = (Button) findViewById(R.id.btn_ui_progress);
        btn_ui_alert.setOnClickListener(this);
        btn_ui_progress.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ui_alert:
                showAlertDilog();
                break;
            case R.id.btn_ui_progress:
                showProgresstDilog();
                break;
            default:
                break;
        }
    }

    /**
     * 显示进度条
     */
     private void showProgresstDilog() {
         ProgressDialog progressDialog = new ProgressDialog(this);
         progressDialog.setTitle("This is a ProgressDialog");
         progressDialog.setMessage("Loading...");
         progressDialog.setCancelable(false);
         progressDialog.show();
    }

    /**
     * 显示警告框
     */
    private void showAlertDilog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is a Dialog")
                .setMessage("Something important")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UIFirstActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UIFirstActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        }).create().show();

    }
    
    
}
