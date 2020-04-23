package com.android.androidlearning.learningcode.annotation;

import android.view.View;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;

/**
 * Created by xiezhaofei on 2020-03-21
 * <p>
 * Describe:
 */
@HelloAnnotation(say = "do it")
public class TestAnnotationFragment extends BaseFragment2 {
    @Override
    protected void initViews() {
        super.initViews();
        addButton("解析注解", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
                HelloAnnotation annotation = TestAnnotationFragment.class.getAnnotation(HelloAnnotation.class);
                trace(annotation.say());
            }
        });
    }
}
