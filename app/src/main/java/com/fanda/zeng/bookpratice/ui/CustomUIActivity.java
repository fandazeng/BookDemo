package com.fanda.zeng.bookpratice.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;
import com.fanda.zeng.bookpratice.custom.TitleLinearLayout;

/**
 * Created by zfd on 2017/1/16 0016.
 * 自定义标题布局
 */

public class CustomUIActivity extends BaseActivity {

    private TitleLinearLayout ly_top_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ly_top_title = (TitleLinearLayout) findViewById(R.id.ly_top_title);
        ly_top_title.setTitle("自定义标题");
    }
}
