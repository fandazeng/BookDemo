package com.fanda.zeng.bookpratice.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;

/**
 * Created by 曾凡达 on 2017/4/11.
 * des
 */

public class WindowActivity extends BaseActivity implements View.OnTouchListener {

    private Button mButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;
    private TextView tv_hello_world;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        tv_hello_world = (TextView) findViewById(R.id.tv_hello_world);
        tv_hello_world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWindowView();
            }
        });
    }

    private void addWindowView() {
        mButton = new Button(this);
        mButton.setText("window");

        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        mButton.setOnTouchListener(this);

        mWindowManager.addView(mButton, mLayoutParams);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Toast.makeText(this, "View添加成功", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWindowManager.removeView(mButton);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mLayoutParams.x = (int) event.getX();
                mLayoutParams.y = (int) event.getY();
                mWindowManager.updateViewLayout(mButton,mLayoutParams);
                break;

            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return false;
    }
}
