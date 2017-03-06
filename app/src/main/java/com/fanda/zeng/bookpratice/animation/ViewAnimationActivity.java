package com.fanda.zeng.bookpratice.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/3.
 * des 补间动画演示类
 */

public class ViewAnimationActivity extends BaseActivity implements View.OnClickListener {

    Button btn_alpha;
    Button btn_rotate;
    Button btn_scale;
    Button btn_translate;
    Button btn_set;
    Button btn_frame;
    private AlphaAnimation alpha;
    private RotateAnimation rotate;
    private ScaleAnimation scale;
    private TranslateAnimation translate;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        initView();
        initAnimation();
    }

    private void initAnimation() {
        initAlphaAnimation();
        initRotateAnimation();
        initScaleAnimation();
        initTranslateAnimation();
        initAnimationSet();
    }

    private void initAnimationSet() {
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(alpha);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(scale);
        animationSet.addAnimation(translate);
    }

    private void initTranslateAnimation() {
        translate = new TranslateAnimation(0f, 250f, 0f, 250f);
        translate.setDuration(2000);
    }

    private void initScaleAnimation() {
        scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        scale.setInterpolator(new AccelerateDecelerateInterpolator());
        scale.setDuration(2000);
    }

    private void initRotateAnimation() {
//        rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.1f);
//        rotate = new RotateAnimation(0, 90, 0f, 1f);
        rotate.setDuration(2000);
    }

    private void initAlphaAnimation() {
        alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(2000);
//        alpha.setFillAfter(true);
        alpha.setRepeatCount(1);//重复次数，不包括第一次
        alpha.setStartOffset(2000);
        alpha.setRepeatMode(Animation.REVERSE);

    }

    private void initView() {
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_set = (Button) findViewById(R.id.btn_set);
        btn_frame = (Button) findViewById(R.id.btn_frame);

        btn_alpha.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_frame.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alpha:
//                btn_alpha.startAnimation(alpha);
                Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                btn_alpha.startAnimation(alphaAnimation);
                break;

            case R.id.btn_rotate:
//                btn_rotate.startAnimation(rotate);
                Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                btn_rotate.startAnimation(rotateAnimation);
                break;

            case R.id.btn_scale:
                Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
//                btn_scale.startAnimation(scale);
                btn_scale.startAnimation(scaleAnimation);
                break;

            case R.id.btn_translate:
//                btn_translate.startAnimation(translate);
                Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                btn_translate.startAnimation(translateAnimation);
                break;
            case R.id.btn_set:
                btn_set.startAnimation(animationSet);
                break;
            case R.id.btn_frame:
                btn_frame.setBackgroundResource(R.drawable.loading);
                btn_frame.setText("");
                ((AnimationDrawable)btn_frame.getBackground()).start();
                break;

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        btn_frame.setBackgroundResource(R.drawable.loading);
        btn_frame.setText("");
        ((AnimationDrawable)btn_frame.getBackground()).start();
    }
}
