package com.android.androidlearning.learningcode.viewevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-11-18
 * <p>
 * Describe:
 */
public class MView extends View {

    private String flag = "MView";

    public MView(Context context) {
        super(context);
    }

    public MView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    Boolean onTouchEventResult;

    public void setOnTouchEventResult(boolean result) {
        onTouchEventResult = result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ALLog.d(flag, "before dispatchTouchEvent " + getAction(event));
        boolean result = super.dispatchTouchEvent(event);
        ALLog.d(flag, "after dispatchTouchEvent result : " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ALLog.d(flag, "before onTouchEvent " + getAction(event));
        boolean result = super.onTouchEvent(event);
        if (onTouchEventResult != null) {
            result = onTouchEventResult;
        }
        ALLog.d(flag, "after onTouchEvent result : " + result);
        return result;
    }

    private String getAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return "down";
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return "move";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            return "up";
        }
        return "";
    }
}
