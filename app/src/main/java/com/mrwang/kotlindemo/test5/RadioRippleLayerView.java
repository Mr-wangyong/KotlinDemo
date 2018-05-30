package com.mrwang.kotlindemo.test5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author chengwangyong
 * @date 2018/5/16
 */
public class RadioRippleLayerView extends View {

    private Paint mPaint;

    public RadioRippleLayerView(Context context) {
        this(context, null);
    }

    public RadioRippleLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioRippleLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RadioRippleLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getWidth() / 2;
        canvas.save();
        canvas.drawCircle(radius, radius, radius, mPaint);
        canvas.restore();
    }
}
