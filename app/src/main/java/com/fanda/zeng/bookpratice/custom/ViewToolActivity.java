package com.fanda.zeng.bookpratice.custom;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.Scroller;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.activity.BaseActivity;

/**
 * Created by 曾凡达 on 2017/3/8.
 * des 系统提供的相关工具
 * Configuration
 * ViewConfiguration
 * GestureDetector
 * VelocityTracker
 * Scroller
 * ViewDragHelper
 */

public class ViewToolActivity extends BaseActivity {

    private GestureDetector gestureDetector;
    private VelocityTracker velocityTracker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tool);
//        initConfiguration();
//        initViewConfiguration();
//        initGesture();
//        initScroller();
    }

    /**
     * des 滑动
     *  public void scrollBy(int x, int y) {
        scrollTo(mScrollX + x, mScrollY + y);
        }
     scrollTo( )和scrollBy( )移动的只是View的内容，而且View的背景是不移动的
     * @author 曾凡达
     * created at 2017/3/8 15:45
     */
    private void initScroller() {
//        Button btn_scroller = (Button) findViewById(R.id.btn_scroller);
//        btn_scroller.scrollTo(0,250);
//        btn_scroller.invalidate();
    }

    /**
     * des 用于跟踪触摸屏事件（比如，Flinging及其他Gestures手势事件等）的速率
     *
     * @author 曾凡达
     * created at 2017/3/8 15:29
     */
    private void startTracker(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.addMovement(event);
        }
        velocityTracker.computeCurrentVelocity(1000);
        float x = velocityTracker.getXVelocity();//获取在1秒内X方向所滑动像素值
        float y = velocityTracker.getYVelocity();//获取在1秒内Y方向所滑动像素值
        Log.d("velocityTracker", "x方向速度为：" + x + "y方向速度为：" + y);
    }

    /**
     * des 一个手势处理的工具
     *
     * @author 曾凡达
     * created at 2017/3/8 15:12
     */
    private void initGesture() {
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {//触摸屏幕时均会调用该方法
                Log.d("gestureDetector", "手势中的onDown方法");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {//手指在屏幕上按下,且未移动和松开时调用该方法
                Log.d("gestureDetector", "手势中的onShowPress方法");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {//轻击屏幕时调用该方法
                Log.d("gestureDetector", "手势中的onSingleTapUp方法");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {//手指在屏幕上滚动时会调用该方法
                Log.d("gestureDetector", "手势中的onScroll方法");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {//手指长按屏幕时均会调用该方法
                Log.d("gestureDetector", "手势中的onLongPress方法");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {//手指在屏幕上滑动时会调用该方法
                Log.d("gestureDetector", "手势中的onFling方法");
                return false;
            }
        });

    }

    /**
     * des 提供了一些自定义控件用到的标准常量，比如尺寸大小，滑动距离，敏感度等等
     *
     * @author 曾凡达
     * created at 2017/3/8 15:00
     */
    private void initViewConfiguration() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
        //该值表示系统所能识别出的被认为是滑动的最小距离
        int touchSlop = viewConfiguration.getScaledTouchSlop();
        //获取Fling速度的最小值和最大值
        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        //判断是否有物理按键
        boolean isHasPermanentMenuKey = viewConfiguration.hasPermanentMenuKey();
        //双击间隔时间.在该时间内是双击，否则是单击
        int doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout();
        //按住状态转变为长按状态需要的时间
        int longPressTimeout = ViewConfiguration.getLongPressTimeout();
        //重复按键的时间
        int keyRepeatTimeout = ViewConfiguration.getKeyRepeatTimeout();
        Log.d("viewConfiguration", "被认为是滑动的最小距离" + touchSlop + "----" + minimumVelocity + "----" + maximumVelocity + "----" + isHasPermanentMenuKey + "----" + doubleTapTimeout + "----" + longPressTimeout + "----" + keyRepeatTimeout);
    }

    /**
     * des 用来描述设备的配置信息。比如用户的配置信息：locale和scaling等等。
     * 比如设备的相关信息：输入模式，屏幕大小， 屏幕方向等等
     *
     * @author 曾凡达
     * created at 2017/3/8 14:58
     */
    private void initConfiguration() {
        Configuration configuration = getResources().getConfiguration();
        int countryCode = configuration.mcc;//获取国家码
        int networkCode = configuration.mnc;//获取网络码
        if (Configuration.ORIENTATION_PORTRAIT == configuration.orientation) {//判断横竖屏
            Log.d("configuration", countryCode + "----" + networkCode + "----");
        }

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        startTracker(event);
//        return super.onTouchEvent(event);
//        return gestureDetector.onTouchEvent(event);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (velocityTracker != null) {//回收资源
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }
}




















