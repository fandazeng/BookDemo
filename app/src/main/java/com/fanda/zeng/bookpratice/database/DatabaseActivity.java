package com.fanda.zeng.bookpratice.database;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/23.
 * des
 */

public class DatabaseActivity extends BaseActivity implements View.OnClickListener {

    private Button createDatabase;
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        databaseHelper = new MyDatabaseHelper(this, "BookStroe.db", null, 1);
        createDatabase = (Button) findViewById(R.id.btn_create_database);
        createDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_database:
                databaseHelper.getWritableDatabase();
                break;
        }
    }
}
