package com.android.androidlearning.learningcode.viewevent;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-11-18
 * <p>
 * Describe:
 */
public class MScrollView extends ScrollView {
    public MScrollView(Context context) {
        super(context);
    }

    public MScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private String flag = "MScrollView";

    public void setFlag(String flag) {
        this.flag = flag;
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


    private int top, left, width, height;

    public void setPosition(int top, int left, int width, int height) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
        ALLog.d("xzf", "top = " + top + " left = " + left + " width = " + width + " height = " + height);
    }

    private boolean isInnerRect(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x > left && x < left + width && y > top && y < top + height) {
            return true;
        }
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ALLog.d(flag, "before onInterceptTouchEvent " + getAction(ev));
        boolean result = super.onInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isInnerRect(ev)) {
                onInterceptResult = true;
            } else {
                onInterceptResult = false;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {

        }


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
