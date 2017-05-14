package com.fanda.zeng.bookpratice.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 曾凡达 on 2017/3/23.
 * des
 */

public class DatabaseActivity extends BaseActivity implements View.OnClickListener {

    private Button createDatabase;
    private MyDatabaseHelper databaseHelper;
    private static ExecutorService executorService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        databaseHelper = new MyDatabaseHelper(this, "BookStroe.db", null, 4);
        createDatabase = (Button) findViewById(R.id.btn_create_database);
        createDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_database:
                databaseHelper.getWritableDatabase();
                executorService = Executors.newFixedThreadPool(10);
//                executorService.submit()
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObj_showapi_res_body = null;
                try {
                    jsonObj_showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
                    JSONObject jsonObj_pagebean = jsonObj_showapi_res_body.getJSONObject("pagebean");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
