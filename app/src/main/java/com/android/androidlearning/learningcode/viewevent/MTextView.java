package com.android.androidlearning.learningcode.viewevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2020-03-22
 * <p>
 * Describe:
 */
public class MTextView extends AppCompatTextView {
    String flag = "MTextView";

    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
//        ALLog.d(flag, "before dispatchTouchEvent " + getAction(event));
        boolean result = super.dispatchTouchEvent(event);
//        ALLog.d(flag, "after dispatchTouchEvent result : " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //ALLog.d(flag, "before onTouchEvent " + getAction(event));
        boolean result = super.onTouchEvent(event);
        if (onTouchEventResult != null) {
            result = onTouchEventResult;
        }
        ALLog.d(flag, "after onTouchEvent " + getAction(event) + " result : " + result);
        return result;
    }

    private String getAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return "down";
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return "move";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            return "up";
        } else {
            return "cancel";
        }
    }
}
