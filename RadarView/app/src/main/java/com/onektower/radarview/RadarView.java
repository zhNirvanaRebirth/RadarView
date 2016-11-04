package com.onektower.radarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
    //多边形默认半径
    private final float defaultRadius;
    //多边形默认边距
    private final float defaultEdgeDis;
    //多边形默认线宽
    private final float defaultLineWidth;
    //多边形边数
    private int edgeNum;
    //多边形最小半径
    private float radius;
    //多边形边距
    private float edgeDis;
    //多边形线宽
    private float lineWidth;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ThemeRadarViewStyle);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultRadius = dp2px(100);
        defaultEdgeDis = dp2px(100);
        defaultLineWidth = dp2px(5);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadarViewStyle, defStyleAttr, 0);
        edgeNum = typedArray.getInt(R.styleable.RadarViewStyle_edge_num, defaultEdgeNum);
        radius = typedArray.getDimension(R.styleable.RadarViewStyle_radius, defaultRadius);
        edgeDis = typedArray.getDimension(R.styleable.RadarViewStyle_edge_dis, defaultEdgeDis);
        lineWidth = typedArray.getDimension(R.styleable.RadarViewStyle_line_width, defaultLineWidth);
        typedArray.recycle();
    }

    /**
     * 这里是确定控件的大小的方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 这里可以得到控件的最终大小
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
