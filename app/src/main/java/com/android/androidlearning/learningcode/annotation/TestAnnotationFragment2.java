package com.android.androidlearning.learningcode.annotation;

import android.view.View;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;
import com.chenenyu.router.annotation.Route;

@Route(value = "TestAnnotationFragment2")
public class TestAnnotationFragment2 extends BaseFragment2 {
    @Override
    protected void initViews() {
        super.initViews();
        addButton("fragment", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
