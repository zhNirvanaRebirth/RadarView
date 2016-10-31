package com.onektower.radarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/10/31.
 * 自定义雷达控件
 * 涉及类容：
 * 1、正多边形的相关计算（http://ipsm.hner.cn/czpd/kczy/shang/sx/3/12/rj-kebiao/1/jasl2.htm）
 */
public class RadarView extends View{
    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
