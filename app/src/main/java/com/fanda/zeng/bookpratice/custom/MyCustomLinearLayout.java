package com.fanda.zeng.bookpratice.custom;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 曾凡达 on 2017/3/8.
 * des ViewDragHelper 演示
 */

public class MyCustomLinearLayout extends LinearLayout {

    private ViewDragHelper viewDragHelper;

    public MyCustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewDragHelper();
    }

    private void initViewDragHelper() {
        if (viewDragHelper == null) {
            viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    return true;
                }

                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    int fixedLeft;
                    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                    View parent = (View) child.getParent();
                    int leftBound = parent.getPaddingLeft() + lp.leftMargin;
                    int rightBound = parent.getWidth() - child.getWidth() - parent.getPaddingRight() - lp.rightMargin;

                    if (left < leftBound) {
                        fixedLeft = leftBound;
                    } else if (left > rightBound) {
                        fixedLeft = rightBound;
                    } else {
                        fixedLeft = left;
                    }
                    return fixedLeft;
                }

                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    int fixedTop;
                    View parent = (View) child.getParent();
                    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                    int topBound = getPaddingTop() + lp.topMargin;
                    int bottomBound = getHeight() - child.getHeight() - parent.getPaddingBottom() - lp.bottomMargin;
                    if (top < topBound) {
                        fixedTop = topBound;
                    } else if (top > bottomBound) {
                        fixedTop = bottomBound;
                    } else {
                        fixedTop = top;
                    }
                    return fixedTop;
                }

                @Override
                public void onViewDragStateChanged(int state) {
                    switch (state) {
                        case ViewDragHelper.STATE_DRAGGING://拖动的时候
                            Log.d("viewDragHelper", "拖动");
                            break;
                        case ViewDragHelper.STATE_IDLE://空闲的时候
                            Log.d("viewDragHelper", "空闲");
                            break;
                        case ViewDragHelper.STATE_SETTLING://按住的时候
                            Log.d("viewDragHelper", "按住");
                            break;
                    }
                }

                @Override
                public void onViewCaptured(View capturedChild, int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                    Log.d("viewDragHelper", "捕获View");
                }

                @Override
                public void onViewReleased(View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    Log.d("viewDragHelper", "释放View");
                }
            });
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
