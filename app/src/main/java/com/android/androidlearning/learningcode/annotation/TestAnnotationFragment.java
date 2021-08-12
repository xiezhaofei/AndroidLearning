package com.android.androidlearning.learningcode.annotation;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;
import com.example.autofragment.NewFragment;

/**
 * Created by xiezhaofei on 2020-03-21
 * <p>
 * Describe:
 */
@NewFragment(text = "测试 TestAnnotationFragment")
@HelloAnnotation(say = "do it")
public class TestAnnotationFragment extends BaseFragment2 {
    @Override
    protected void initViews() {
        super.initViews();
        addButton("解析注解", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
            }
        });
    }


    private class AnnotationProcessor {
        public String getAnnotationString(Fragment fragment) {
            HelloAnnotation annotation = fragment.getClass().getAnnotation(HelloAnnotation.class);
            if (annotation != null) {
                return annotation.say();
            }
            return null;
        }
    }
}


