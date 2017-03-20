package com.fanda.zeng.bookpratice.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;

import com.fanda.zeng.bookpratice.R;
import com.fanda.zeng.bookpratice.animation.Point;

/**
 * Created by 曾凡达 on 2017/3/15.
 * des 天气待机控件
 */

public class WeatherWaitingView extends View {

    private float sun_radius;
    private float sun_line_width;
    private int sun_bg_center_color;
    private int sun_bg_edge_color;
    private int sun_bg_src;
    private int sun_line_count;
    private float sun_line_distance;
    private float degree;//旋转色度

    private Context context;
    private Paint circlePaint;
    private Paint shadowPaint;//阴影
    private Paint linePaint;//发光的线
    private RadialGradient radialGradient;

    private Point point;
//    private int centerWidth;
//    private int centerHeight;


    public WeatherWaitingView(Context context) {
        this(context, null);
    }

    public WeatherWaitingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherWaitingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        getUserSettingValues(attrs);
        initViews();
    }

    /**
     * des 初始化资源
     *
     * @author 曾凡达
     * created at 2017/3/15 22:35
     */
    private void initViews() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(30);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(sun_bg_edge_color);
//        bitmapPaint.setFilterBitmap(true);//加快显示速度，本设置项依赖于dither和xfermode的设置
//        bitmapPaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        circlePaint.setAntiAlias(true);
        shadowPaint.setAntiAlias(true);
    }

    /**
     * des 获取用户在xml中设置的属性值
     *
     * @author 曾凡达
     * created at 2017/3/15 22:08
     */
    private void getUserSettingValues(AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.weather_waiting);
        sun_radius = array.getDimension(R.styleable.weather_waiting_sun_radius, 240);
        sun_line_width = array.getDimension(R.styleable.weather_waiting_sun_line_width, 80);
        sun_bg_center_color = array.getColor(R.styleable.weather_waiting_sun_bg_from_color, 0xffff7e00);
        sun_bg_edge_color = array.getColor(R.styleable.weather_waiting_sun_bg_to_color, 0xffffda10);
        sun_bg_src = array.getResourceId(R.styleable.weather_waiting_sun_bg_src, R.mipmap.bg_weather);
        sun_line_count = array.getInteger(R.styleable.weather_waiting_sun_line_count, 8);
        sun_line_distance = array.getDimension(R.styleable.weather_waiting_sun_line_distance, 70);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidthOrHeight(widthMeasureSpec), measureWidthOrHeight(heightMeasureSpec));
    }

    /**
     * des 测试宽和高
     *
     * @author 曾凡达
     * created at 2017/3/15 22:41
     */
    private int measureWidthOrHeight(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                //最大值模式 当控件的layout_Width或layout_height属性指定为wrap_content时
                size = Math.min(Integer.MAX_VALUE, size);
                break;
            default:
                size = Integer.MAX_VALUE;
                break;
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (point == null) {
            point = new Point(getScreenWidth(context) / 2, getScreenHeight(context) / 3);
            drawBg(canvas);
            drawCircle(canvas);
            setSunAnimation();
        } else {
            drawBg(canvas);
            drawCircle(canvas);
            if (point.getY() == getScreenHeight(context) / 3) {
                drawLine(canvas);
            }
        }
        canvas.rotate(degree);
    }

    /**
     * des 设置太阳的动画
     *
     * @author 曾凡达
     * created at 2017/3/16 14:14
     */
    private void setSunAnimation() {
        //弹跳动画
        ObjectAnimator obAnimation = ObjectAnimator.ofInt(this, "centerHeight", 0, getScreenHeight(context) / 3);
        obAnimation.setInterpolator(new BounceInterpolator());
        obAnimation.setDuration(1000);

        //线伸长动画
        ObjectAnimator lineAnimation = ObjectAnimator.ofFloat(this, "sun_line_width", 0, sun_line_width);
        lineAnimation.setDuration(1000);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(this, "degree", 0, 360);
        rotateAnimation.setDuration(50000);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);//无限次数

        //组合
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotateAnimation).after(lineAnimation).after(obAnimation);
        animatorSet.start();

    }

    /**
     * des 画太阳发光的线
     *
     * @author 曾凡达
     * created at 2017/3/16 11:20
     */
    private void drawLine(Canvas canvas) {
        float startX = point.getX() + sun_radius + sun_line_distance;
        float endX = point.getX() + sun_radius + sun_line_distance + sun_line_width;
        for (int i = 0; i < sun_line_count; i++) {
            int angle = 360 / sun_line_count;
            canvas.drawLine(startX, point.getY(), endX, point.getY(), linePaint);
            canvas.rotate(angle, point.getX(), point.getY());
        }

    }

    /**
     * des 画背景
     *
     * @author 曾凡达
     * created at 2017/3/16 11:20
     */
    private void drawBg(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sun_bg_src);
        Matrix matric = new Matrix();
        matric.postScale(1.6f, 1.6f);
        canvas.drawBitmap(bitmap, matric, null);

    }

    /**
     * des 画中间的圆
     *
     * @author 曾凡达
     * created at 2017/3/16 9:42
     */
    private void drawCircle(Canvas canvas) {
        radialGradient = new RadialGradient(point.getX(), point.getY(), sun_radius, sun_bg_center_color, sun_bg_edge_color, Shader.TileMode.REPEAT);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//如果不关闭硬件加速，setShadowLayer无效
        shadowPaint.setColor(Color.argb(102, 255, 150, 0));
//        shadowPaint.setShadowLayer(10, 20, 40, Color.argb(90, 255, 150, 0));
        MaskFilter maskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
        canvas.drawCircle(point.getX(), point.getY(), sun_radius, shadowPaint);
        circlePaint.setShader(radialGradient);
        circlePaint.setMaskFilter(maskFilter);
        canvas.drawCircle(point.getX(), point.getY(), sun_radius, circlePaint);

    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    public float getCenterHeight() {
        return point.getY();
    }

    public void setCenterHeight(int centerHeight) {
        point.setY(centerHeight);
        invalidate();
        Log.d("height", centerHeight + "");
    }

    public float getSun_line_width() {
        return sun_line_width;
    }

    public void setSun_line_width(float sun_line_width) {
        this.sun_line_width = sun_line_width;
        invalidate();
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
        Log.d("degree", degree + "");
        invalidate();
    }
}
