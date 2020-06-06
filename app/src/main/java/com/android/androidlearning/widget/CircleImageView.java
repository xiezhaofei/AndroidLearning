package com.android.androidlearning.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {
    private Paint mPaint;

    public CircleImageView(Context context) {
        super(context);
        initPaint();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, Math.min(canvas.getWidth() / 2, canvas.getHeight() / 2), mPaint);
    }
}
