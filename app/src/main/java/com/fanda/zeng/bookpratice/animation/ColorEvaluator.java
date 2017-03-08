package com.fanda.zeng.bookpratice.animation;

import android.animation.TypeEvaluator;

/**
 * Created by 曾凡达 on 2017/3/8.
 * des 颜色渐变的取值程序
 */

public class ColorEvaluator implements TypeEvaluator<String> {

    private int currentRed = -1;
    private int currentGreen = -1;
    private int currentBlue = -1;

    @Override
    public String evaluate(float fraction, String startValue, String endValue) {
        int startRed = Integer.parseInt(startValue.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startValue.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startValue.substring(5, 7), 16);

        int endRed = Integer.parseInt(endValue.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endValue.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endValue.substring(5, 7), 16);

        if (currentRed == -1) {
            currentRed = startRed;
        }
        if (currentGreen == -1) {
            currentGreen = startGreen;
        }
        if (currentBlue == -1) {
            currentBlue = startBlue;
        }

        //计算初始颜色和结束颜色之间的差值
        int redDiff = Math.abs(startRed - endRed);
        int greenDiff = Math.abs(startGreen - endGreen);
        int blueDiff = Math.abs(startBlue - endBlue);
        int colorDiff = redDiff + greenDiff + blueDiff;

        if (currentRed != endRed) {
            currentRed = getCurrentColor(startRed, endRed, colorDiff, 0, fraction);
        } else if (currentGreen != endGreen) {
            currentGreen = getCurrentColor(startGreen, endGreen, colorDiff, redDiff, fraction);
        } else if (currentBlue != endBlue) {
            currentBlue = getCurrentColor(startBlue, endBlue, colorDiff, redDiff + greenDiff, fraction);
        }

        //将计算出的当前颜色值组装返回
        String currentColor = "#" + getHexString(currentRed) + getHexString(currentGreen) + getHexString(currentBlue);
        return currentColor;
    }

    /**
     * des 根据fraction 值来计算当前的颜色
     *
     * @author 曾凡达
     * created at 2017/3/8 10:26
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff, int offset, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
