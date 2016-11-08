package com.onektower.radarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/10/31.
 * 自定义雷达控件
 * 涉及类容：
 * 1、正多边形的相关计算（http://ipsm.hner.cn/czpd/kczy/shang/sx/3/12/rj-kebiao/1/jasl2.htm）
 * 1.1、边数为奇数
 * 1.2、边数为偶数
 */
public class RadarView extends View {
    //多边形默认边数
    private static final int defaultEdgeNum = 6;
    //默认层数
    private static final int defaultLayerNum = 6;
    //多边形默认半径
    private final float defaultRadius;
    //多边形默认线宽
    private final float defaultLineWidth;
    //多边形默认颜色
    private final int defaultLineColor;
    //多边形边数
    private int edgeNum;
    //多边形最小半径
    private float radius;
    //多边形线宽
    private float lineWidth;
    //层数
    private int layerNum;
    //颜色
    private int lineColor;
    //控件的宽
    private int width;
    //控件的高
    private int height;
    //画笔
    private Paint paint;
    //多边形内角
    private float angle;
    //边距
    private float dis;
    //是否是奇数多边形
    private boolean isOdd;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ThemeRadarViewStyle);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultRadius = dp2px(200);
        defaultLineWidth = 2;
        defaultLineColor = Color.BLACK;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarViewStyle, defStyleAttr, 0);
        edgeNum = typedArray.getInt(R.styleable.RadarViewStyle_edge_num, defaultEdgeNum);
        radius = typedArray.getDimension(R.styleable.RadarViewStyle_radius, defaultRadius);
        lineWidth = typedArray.getDimension(R.styleable.RadarViewStyle_line_width, defaultLineWidth);
        lineColor = typedArray.getColor(R.styleable.RadarViewStyle_line_color, defaultLineColor);
        layerNum = typedArray.getInt(R.styleable.RadarViewStyle_layer_num, defaultLayerNum);
        typedArray.recycle();

        angle = (float) ((Math.PI * 2) / edgeNum);
        isOdd = edgeNum % 2 == 0 ? false : true;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 这里是确定控件的大小的方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) radius;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) radius;
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {//表示用户设定了大小
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    /**
     * 这里可以得到控件的最终大小
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        dis = width > height ? height / 2 / layerNum - lineWidth : width / 2 / layerNum - lineWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);
        Path path = new Path();
        float currentRadius = dis;
        for (int i = 0; i < layerNum; i++) {
            for (int j = 0; j < edgeNum; j++) {
                float cos = (float) Math.cos(angle * j);
                float sin = (float) Math.sin(angle * j);
                float positionX = isOdd ? currentRadius * sin : currentRadius * cos;
                float positionY = isOdd ? currentRadius * cos : currentRadius * sin;
                if (j == 0)
                    path.moveTo(positionX, positionY);
                else {
                    path.lineTo(positionX, positionY);
                }
            }
            currentRadius += dis;
            path.close();
        }
        for (int i = 0; i < edgeNum; i++){
            float realRadius = dis * layerNum;
            float cos = (float) Math.cos(angle * i);
            float sin = (float) Math.sin(angle * i);
            float positionX = isOdd ? realRadius * sin : realRadius * cos;
            float positionY = isOdd ? realRadius * cos : realRadius * sin;
            path.moveTo(0, 0);
            path.lineTo(positionX, positionY);
        }
        canvas.drawPath(path, paint);
    }

    private float dp2px(float dp) {
        float density = getResources().getDisplayMetrics().density;
        return density * dp + 0.5f;
    }

    private float sp2px(float sp) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return scaledDensity * sp;
    }
}
