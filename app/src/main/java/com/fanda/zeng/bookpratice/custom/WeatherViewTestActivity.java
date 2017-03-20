package com.fanda.zeng.bookpratice.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/15.
 * des 天气待机控件测试界面
 */

public class WeatherViewTestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_waiting_test);
    }
}
