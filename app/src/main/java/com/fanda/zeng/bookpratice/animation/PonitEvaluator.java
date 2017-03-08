package com.fanda.zeng.bookpratice.animation;

import android.animation.TypeEvaluator;

/**
 * Created by 曾凡达 on 2017/3/7.
 * des 对象取值程序
 */

public class PonitEvaluator implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
        return new Point(x, y);
    }

}
