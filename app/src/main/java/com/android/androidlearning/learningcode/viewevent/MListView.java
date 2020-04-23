package com.android.androidlearning.learningcode.viewevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2020-03-17
 * <p>
 * Describe:
 */
public class MListView extends ListView {
    private String flag = "MListView";

    public MListView(Context context) {
        super(context);
    }

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isGrapedMoveEvent || ev.getAction() != MotionEvent.ACTION_MOVE) {
//            ALLog.d(flag, "dispatchTouchEvent:" + ev.getAction());
        }
        boolean result = super.dispatchTouchEvent(ev);
        if (!isGrapedMoveEvent || ev.getAction() != MotionEvent.ACTION_MOVE) {
//            ALLog.d(flag, "dispatchTouchEvent result:" + result);
        }
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        if (!isGrapedMoveEvent || ev.getAction() != MotionEvent.ACTION_MOVE) {
            ALLog.d(flag, "onInterceptTouchEvent result:" + result);
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        if (!isGrapedMoveEvent || ev.getAction() != MotionEvent.ACTION_MOVE) {
            ALLog.d(flag, "onTouchEvent result:" + result);
        }
        return result;
    }

    private boolean isGrapedMoveEvent;

    public void grapMoveEvent() {
        isGrapedMoveEvent = true;
    }
}
