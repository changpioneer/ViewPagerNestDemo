package com.pioneer.viewpagernest.viewpagerdemo.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 黑色、灰色的圆点
 */
public class SimpleItemView extends View {

    private Paint mPaint;
    private boolean selected;
    private RectF rectF;

    public SimpleItemView(Context context) {
        super(context);
        init();
    }

    public SimpleItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (selected) {
            mPaint.setColor(Color.BLACK);
        } else {
            mPaint.setColor(Color.GRAY);
        }

        rectF.left = getWidth() / 2 - getHeight() / 2;
        rectF.right = getHeight() / 2 + getWidth() / 2;
        rectF.top = 0;
        rectF.bottom = getHeight();
        canvas.drawRoundRect(rectF, getHeight() / 2, getHeight() / 2, mPaint);
    }
}
