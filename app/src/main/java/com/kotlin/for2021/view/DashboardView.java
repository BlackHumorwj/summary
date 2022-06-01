package com.kotlin.for2021.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.kotlin.for2021.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * @time 2020/9/9 14:06
 * @desc
 */
public class DashboardView extends View {
    private static final int DEFAULT_COLOR_LOWER = Color.parseColor("#FF756A");
    private static final int DEFAULT_COLOR_MIDDLE = Color.parseColor("#FEC95E");
    private static final int DEFAULT_COLOR_HIGH = Color.parseColor("#46D183");
    private static final int DEAFAULT_COLOR_TITLE = Color.parseColor("#333333");
    private static final int DEFAULT_TEXT_SIZE_DIAL = 10;
    private static final int DEFAULT_STROKE_WIDTH = 21;

    private static final int DEFAULT_RADIUS_DIAL = 0;

    private static final int DEAFAULT_TITLE_SIZE = 16;
    private static final int DEAFAULT_TEXT_SIZE = 13;
    private static final int DEFAULT_VALUE_SIZE = 28;
    private static final int DEFAULT_ANIM_PLAY_TIME = 2000;


    private static final int DEAFAULT_POINT_LINE_MARGIN = 6;

    /**
     * 低区颜色
     */
    private int colorDialLower;
    /**
     * 中间环形颜色
     */
    private int colorDialMiddle;
    /**
     * 高区环形颜色
     */
    private int colorDialHigh;
    private int textSizeDial;
    private int strokeWidthDial;
    public String titleDial = "";
    private int titleDialSize;
    private int titleDialColor;
    private int valueTextSize;
    private int animPlayTime;

    private int radiusDial;
    private int mRealRadius;
    private float currentValue;

    private Paint arcPaint;
    private RectF mRect;
    private Paint pointerPaint;
    private Paint.FontMetrics fontMetrics;
    private Paint titlePaint;
    private Paint textPaint;
    private Path pointerPath;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DashboardView);
        colorDialLower = attributes.getColor(R.styleable.DashboardView_color_dial_lower, DEFAULT_COLOR_LOWER);
        colorDialMiddle = attributes.getColor(R.styleable.DashboardView_color_dial_middle, DEFAULT_COLOR_MIDDLE);
        colorDialHigh = attributes.getColor(R.styleable.DashboardView_color_dial_high, DEFAULT_COLOR_HIGH);
        textSizeDial = (int) attributes.getDimension(R.styleable.DashboardView_text_size_dial, sp2px(DEFAULT_TEXT_SIZE_DIAL));
        strokeWidthDial = (int) attributes.getDimension(R.styleable.DashboardView_stroke_width_dial, dp2px(DEFAULT_STROKE_WIDTH));
        radiusDial = (int) attributes.getDimension(R.styleable.DashboardView_radius_circle_dial, dp2px(DEFAULT_RADIUS_DIAL));
        titleDial = attributes.getString(R.styleable.DashboardView_text_title_dial);
        titleDialSize = (int) attributes.getDimension(R.styleable.DashboardView_text_title_size, dp2px(DEAFAULT_TITLE_SIZE));
        titleDialColor = attributes.getColor(R.styleable.DashboardView_text_title_color, DEAFAULT_COLOR_TITLE);
        valueTextSize = (int) attributes.getDimension(R.styleable.DashboardView_text_size_value, dp2px(DEFAULT_VALUE_SIZE));
        animPlayTime = attributes.getInt(R.styleable.DashboardView_animator_play_time, DEFAULT_ANIM_PLAY_TIME);

        attributes.recycle();
    }

    private void initPaint() {
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(strokeWidthDial);

        pointerPaint = new Paint();
        pointerPaint.setAntiAlias(true);
        pointerPaint.setTextSize(textSizeDial);
        pointerPaint.setTextAlign(Paint.Align.CENTER);
        fontMetrics = pointerPaint.getFontMetrics();

        titlePaint = new Paint();
        titlePaint.setAntiAlias(true);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setFakeBoldText(true);


        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(dp2px(DEAFAULT_TEXT_SIZE));
        textPaint.setColor(Color.parseColor("#999999"));


        pointerPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth, mHeight;
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = getPaddingLeft() + radiusDial * 2 + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(mWidth, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + radiusDial * 2 + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(mHeight, heightSize);
            }
        }

        setMeasuredDimension(mWidth, mHeight);

        radiusDial = Math.min((getMeasuredWidth() - getPaddingLeft() - getPaddingRight()),
                (getMeasuredHeight() - getPaddingTop() - getPaddingBottom())) / 2;
        mRealRadius = radiusDial - strokeWidthDial / 2;
        mRect = new RectF(-mRealRadius, -mRealRadius, mRealRadius, mRealRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas);
        drawPointerLine(canvas);
        drawTitleDial(canvas);
        drawPointer(canvas);
    }

    private void drawArc(Canvas canvas) {
        canvas.translate(getPaddingLeft() + radiusDial, getPaddingTop() + radiusDial);
        arcPaint.setColor(colorDialLower);
        canvas.drawArc(mRect, 165, 63, false, arcPaint);
        arcPaint.setColor(colorDialMiddle);
        canvas.drawArc(mRect, 228, 84, false, arcPaint);
        arcPaint.setColor(colorDialHigh);
        canvas.drawArc(mRect, 312, 63, false, arcPaint);
    }

    private void drawPointerLine(Canvas canvas) {
        canvas.rotate(165);
        for (int i = 0; i < 21; i++) {     //一共需要绘制101个表针

            //            if (i <= 6) {
            //                pointerPaint.setColor(colorDialLower);
            //            } else if (i <= 14) {
            //                pointerPaint.setColor(colorDialMiddle);
            //            } else {
            //                pointerPaint.setColor(colorDialHigh);
            //            }
            pointerPaint.setColor(ContextCompat.getColor(getContext(), R.color.black));

            if (i % 2 == 0) {     //长表针
                pointerPaint.setStrokeWidth(2);
                canvas.drawLine(radiusDial - strokeWidthDial - DEAFAULT_POINT_LINE_MARGIN, 0, radiusDial - strokeWidthDial - dp2px(8), 0, pointerPaint);

                if (i == 0 || i == 6 || i == 14 || i == 20) {
                    //刻度文案
                    drawPointerText(canvas, i);
                }

            } else {    //短表针
                pointerPaint.setStrokeWidth(1);
                canvas.drawLine(radiusDial - strokeWidthDial - DEAFAULT_POINT_LINE_MARGIN, 0, radiusDial - strokeWidthDial - dp2px(5), 0, pointerPaint);
            }
            canvas.rotate(10.5f);
        }
    }

    private void drawPointerText(Canvas canvas, int i) {

        canvas.save();


        int currentCenterX = (int) (radiusDial - strokeWidthDial - dp2px(21) - pointerPaint.measureText(String.valueOf(i)) / 2);
        canvas.translate(currentCenterX, 0);
        canvas.rotate(360 - 165 - 10.5f * i);        //坐标系总旋转角度为360度

        int textBaseLine = (int) (0 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);

        //设置刻度
        canvas.drawText(String.valueOf(i * 5), 0, textBaseLine, pointerPaint);

        canvas.restore();
    }

    public void drawTitleDial(Canvas canvas) {
        titlePaint.setColor(titleDialColor);
        titlePaint.setTextSize(titleDialSize);
        canvas.rotate(-25f);       //恢复坐标系为起始中心位置
        // canvas.drawText(titleDial, 0, -radiusDial / 3, titlePaint);

        //        if (currentValue <= 30) {
        //            titlePaint.setColor(colorDialLower);
        //        } else if (currentValue <= 70) {
        //            titlePaint.setColor(colorDialMiddle);
        //        } else {
        //            titlePaint.setColor(colorDialHigh);
        //        }
        titlePaint.setTextSize(valueTextSize);
        // canvas.drawText(currentValue + "%", 0, radiusDial * 2/3, titlePaint);

        //标题
        canvas.drawText(titleDial, 0, radiusDial * 4 / 9, textPaint);

        //百分比
        canvas.drawText(currentValue + "%", 0, radiusDial * 7 / 9, titlePaint);

    }

    private void drawPointer(Canvas canvas) {

        titlePaint.setColor(Color.parseColor("#C4C4C4"));

        int currentDegree = (int) (currentValue * 2.1f + 165);
        canvas.rotate(currentDegree);

        //指针起点
        pointerPath.moveTo(radiusDial - strokeWidthDial - dp2px(25), 0);
        pointerPath.lineTo(0, -dp2px(5));
        pointerPath.lineTo(-10, 0);
        pointerPath.lineTo(0, dp2px(5));
        //形成闭环
        pointerPath.close();
        canvas.drawPath(pointerPath, titlePaint);
    }

    public void setCompleteDegree(float degree) {

        ValueAnimator animator = ValueAnimator.ofFloat(0, degree);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) (Math.round((float) animation.getAnimatedValue() * 100)) / 100;
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(animPlayTime);
        animator.start();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }
}
