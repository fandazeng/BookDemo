package com.fanda.zeng.bookpratice.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 曾凡达 on 2017/3/9.
 * des 自定义View
 */

public class CustomView extends View {
    private Paint mPaint;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBaseDraw(canvas);//基础绘画
//        setPathDraw(canvas);//路径
//        setPathOp(canvas);//路径组合
//        setOtherOperation(canvas);//旋转，位移，裁剪，锁定与恢复操作


    }

    /**
     * des 旋转，位移，裁剪，锁定与恢复操作
     *
     * @author 曾凡达
     * created at 2017/3/13 15:40
     */
    private void setOtherOperation(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(80);
        canvas.drawText("位移前的文本", 100, 100, mPaint);
//        canvas.translate(100, 100);
//        canvas.rotate(45f);
        canvas.save();
        RectF rectF = new RectF(200, 200, getRight(), getBottom());
        canvas.clipRect(rectF);
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.GREEN);
        canvas.drawText("位移后的文本", 500, 800, mPaint);
        canvas.restore();
        canvas.drawText("外面", 50, 800, mPaint);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    /**
     * des 路径组合
     *
     * @author 曾凡达
     * created at 2017/3/13 15:02
     */
    private void setPathOp(Canvas canvas) {

//        Path.Op有如下几种参数
//        Path.Op.DIFFERENCE：减去Path2后Path1剩下的部分
//        Path.Op.INTERSECT：保留Path1与Path2共同的部分
//        Path.Op.REVERSE_DIFFERENCE：减去Path1后Path2剩下的部分
//        Path.Op.UNION：保留全部Path1和Path2
//        Path.Op.XOR：包含Path1与Path2但不包括两者相交的部分

        //设置填充风格，方便观察效果
        mPaint.setStyle(Paint.Style.FILL);
        //构建path
        Path path = new Path();
        path.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 150, 100, Path.Direction.CW);
        path.op(path2, Path.Op.UNION);
        //Path.Op.UNION
        canvas.drawPath(path, mPaint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 400, 100, Path.Direction.CW);
        path2.addCircle(300, 400, 100, Path.Direction.CW);
        path.op(path2, Path.Op.REVERSE_DIFFERENCE);
        //Path.Op.REVERSE_DIFFERENCE
        canvas.drawPath(path, mPaint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 650, 100, Path.Direction.CW);
        path2.addCircle(300, 650, 100, Path.Direction.CW);
        path.op(path2, Path.Op.INTERSECT);
        //Path.Op.INTERSECT
        canvas.drawPath(path, mPaint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 900, 100, Path.Direction.CW);
        path2.addCircle(300, 900, 100, Path.Direction.CW);
        path.op(path2, Path.Op.DIFFERENCE);
        //Path.Op.DIFFERENCE
        canvas.drawPath(path, mPaint);

        //清除路径
        path.reset();
        path2.reset();
        path.addCircle(150, 1150, 100, Path.Direction.CW);
        path2.addCircle(300, 1150, 100, Path.Direction.CW);
        path.op(path2, Path.Op.XOR);
        //Path.Op.XOR
        canvas.drawPath(path, mPaint);
    }

    /**
     * des 设置路径绘画
     *
     * @author 曾凡达
     * created at 2017/3/13 13:58
     */
    private void setPathDraw(Canvas canvas) {
        setDrawPaint();
//        Path path = new Path();
//        path.moveTo(300, 300);
//        path.lineTo(100, 500);
//        path.lineTo(500, 500);
//        path.close();
//        canvas.drawPath(path, mPaint);

        //sweepAngle：圆弧扫过的角度，顺时针方向，单位为度 （0~360）,水平向右为0度
        //false代表弧线起点与上个绘制的最后一个点连接，true代表不连接
//        Path arcPath = new Path();
//        RectF rectF = new RectF(500, 600, 700, 800);
//        arcPath.arcTo(rectF, 0, 90, true);
//        canvas.drawPath(arcPath, mPaint);
//
//        RectF rectF2 = new RectF(500, 800, 700, 1000);
//        arcPath.arcTo(rectF2, 0, 180, true);
//        canvas.drawPath(arcPath, mPaint);
//
//        RectF rectF3 = new RectF(500, 1100, 700, 1300);
//        arcPath.arcTo(rectF3, 270, 180, false);
//        canvas.drawPath(arcPath, mPaint);

        //构建path
//        Path addPath = new Path();
//        //添加弧形到path
//        RectF arc = new RectF(100, 100, 300, 300);
//        addPath.addArc(arc, 0, 270);
//        //添加圆形到path
//        addPath.addCircle(200, 500, 100, Path.Direction.CCW);
//        //添加矩形到path
//        addPath.addRect(100, 700, 300, 800, Path.Direction.CW);
//        //添加椭圆到path
//        RectF oval = new RectF(100, 900, 300, 1000);
//        addPath.addOval(oval, Path.Direction.CCW);
//        //绘制path
//        canvas.drawPath(addPath, mPaint);
    }

    private void setDrawPaint() {
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
    }

    /**
     * des 基础绘画方式
     *
     * @author 曾凡达
     * created at 2017/3/13 13:58
     */
    private void setBaseDraw(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(60);
        canvas.drawColor(Color.GREEN);
        canvas.drawText("hello customView", 100, 300, mPaint);

        //-------->绘制白色矩形
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.reset();

        //-------->绘制直线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawLine(150, 720, 200, 720, mPaint);
        mPaint.reset();

        //-------->绘制带边框的矩形
        mPaint.setStrokeWidth(10);
        mPaint.setARGB(150, 90, 255, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF1 = new RectF(30, 60, 350, 350);
        canvas.drawRect(rectF1, mPaint);
        mPaint.reset();

        //绘制圆角矩形
        mPaint.setColor(Color.BLACK);
        RectF rectF = new RectF(100, 1000, 400, 1300);
        canvas.drawRoundRect(rectF, 20, 20, mPaint);
        mPaint.reset();

        //-------->绘制实心圆
        mPaint.setStrokeWidth(14);
        mPaint.setColor(Color.BLUE);//设置颜色
        mPaint.setAntiAlias(true);//去锯齿
        canvas.drawCircle(670, 300, 70, mPaint);//绘制普通圆
        mPaint.setStyle(Paint.Style.STROKE);//设置空心Style
        mPaint.setStrokeWidth(20);//设置空心边框的宽度
        canvas.drawCircle(900, 300, 70, mPaint);//绘制空心圆
        mPaint.reset();

        //-------->绘制椭圆
        mPaint.setColor(Color.YELLOW);
        RectF rectF2 = new RectF(200, 430, 600, 600);
        canvas.drawOval(rectF2, mPaint);
        mPaint.reset();

        //-------->绘制文字
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(60);
        mPaint.setUnderlineText(true);
        canvas.drawText("Hello Android", 150, 720, mPaint);
        mPaint.reset();

        //绘制圆弧
        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        RectF rectF3 = new RectF(500, 800, 700, 1000);
        canvas.drawArc(rectF3, 0f, 270f, false, mPaint);
        mPaint.reset();

        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        RectF rectF4 = new RectF(500, 1100, 700, 1300);
        canvas.drawArc(rectF4, 0f, 270f, true, mPaint);
        mPaint.reset();

        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        RectF rectF5 = new RectF(500, 1400, 700, 1600);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        canvas.drawArc(rectF5, 0f, 270f, false, mPaint);
        mPaint.reset();

        mPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        RectF rectF6 = new RectF(500, 1700, 700, 1900);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        canvas.drawArc(rectF6, 0f, 270f, true, mPaint);
        mPaint.reset();

    }


}
