package com.fanda.zeng.bookpratice.transition;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by David on 2017/5/12.
 * 场景动画演示类
 */

public class SceneTransition extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_scene);
    }
}
