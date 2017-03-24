package com.fanda.zeng.bookpratice.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/6.
 * des  属性动画演示类
 */

public class PropertyAnimActivity extends BaseActivity {

    private TextView tv_property_anim;
    private TextView tv_scroller_anim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        initValueAnimator();
//        initObjectAnimator();
        initPointAnimator();
        initViewPropertyAnimator();
    }

    private void initViewPropertyAnimator() {
        tv_property_anim = (TextView) findViewById(R.id.tv_property_anim);
        tv_scroller_anim = (TextView) findViewById(R.id.tv_scroller_anim);
        float y = tv_property_anim.getTranslationY();
        tv_property_anim.animate().translationY(-500).translationY(y).alpha(0f).alpha(1).rotation(360).rotation(90).setDuration(5000).start();

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_scroller_anim.scrollTo((int) (100 * animation.getAnimatedFraction()), 0);
                Log.d("scroller", (int) (100 * animation.getAnimatedFraction()) +"");
                tv_scroller_anim.invalidate();
            }
        });
        valueAnimator.start();
    }

    private void initPointAnimator() {
        Point startPonit = new Point(0, 100);
        Point endPoint = new Point(50, -300);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PonitEvaluator(), startPonit, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                Log.d("point", "X坐标为：" + point.getX() + "------Y坐标为：" + point.getY());
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    private void initObjectAnimator() {
        tv_property_anim = (TextView) findViewById(R.id.tv_property_anim);
        float y = tv_property_anim.getTranslationY();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(tv_property_anim, "alpha", 1f, 0f, 1f);//渐变
        ObjectAnimator rotaionAnimator = ObjectAnimator.ofFloat(tv_property_anim, "rotation", 0f, 360f, 90f);//旋转
        ObjectAnimator fadeInOutAnimator = ObjectAnimator.ofFloat(tv_property_anim, "translationY", y, -500, y);//位移
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(tv_property_anim, "scaleY", 1f, 3f);//缩放

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alphaAnimator).with(rotaionAnimator).after(fadeInOutAnimator);
//        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        objectAnimator.setRepeatCount(1);
        animatorSet.setDuration(5000);
//        animatorSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(PropertyAnimActivity.this, "动画完结了！", Toast.LENGTH_SHORT).show();
            }
        });
        animatorSet.start();
    }

    private void initValueAnimator() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f,5f,0f);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100, 50);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.d("property", "当前的值为：" + (float) animation.getAnimatedValue());
                Log.d("property", "当前的值为：" + animation.getAnimatedValue());
            }
        });
        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(1000);
        valueAnimator.start();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
