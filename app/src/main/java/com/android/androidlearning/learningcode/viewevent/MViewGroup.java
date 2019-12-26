package com.android.androidlearning.learningcode.viewevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-11-18
 * <p>
 * Describe:
 */
public class MViewGroup extends FrameLayout {

    private String flag = "MViewGroup";

    public MViewGroup(@NonNull Context context) {
        super(context);
    }

    public MViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @TargetApi(21)
    public MViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Boolean onTouchEventResult;

    public void setOnTouchEventResult(boolean result) {
        onTouchEventResult = result;
    }

    Boolean onInterceptResult;

    public void setOnInterceptResult(boolean result) {
        onInterceptResult = result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ALLog.d(flag, "before dispatchTouchEvent ev type:" + getAction(ev));
        boolean result = super.dispatchTouchEvent(ev);
        ALLog.d(flag, "after dispatchTouchEvent result : " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ALLog.d(flag, "before onInterceptTouchEvent " + getAction(ev));
        boolean result = super.onInterceptTouchEvent(ev);
        if (onInterceptResult != null) {
            result = onInterceptResult;
        }
        ALLog.d(flag, "after onInterceptTouchEvent result : " + result);
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
