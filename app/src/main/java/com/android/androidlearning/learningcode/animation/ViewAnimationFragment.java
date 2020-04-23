package com.android.androidlearning.learningcode.animation;

import android.animation.ObjectAnimator;
import android.view.View;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;

/**
 * Created by xiezhaofei on 2020-02-29
 * <p>
 * Describe:
 */
public class ViewAnimationFragment extends BaseFragment2 {
    @Override
    protected void initViews() {
        super.initViews();
        addButton("示例的view", null);
        addButton("缩放view", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = getButton("示例的view");
                trace("view width:" + view.getWidth());
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0, 0.25f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();

                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        trace("view width:" + view.getWidth());
                    }
                }, 2000);
            }
        });
    }
}
