package com.android.androidlearning;

import android.view.View;

import com.android.androidlearning.learningcode.animation.ViewAnimationFragment;
import com.android.androidlearning.learningcode.annotation.TestAnnotationFragment;
import com.android.androidlearning.learningcode.listview.TestListViewFragment;
import com.android.androidlearning.learningcode.lock.LockTestFragment;
import com.android.androidlearning.learningcode.okhttp.TestOKhttpFragment;
import com.android.androidlearning.learningcode.proxy.TestDynamicProxyFragment;

/**
 * Created by xiezhaofei on 2020-02-29
 * <p>
 * Describe:
 */
public class CommonActivity extends BaseActivity2 {
    @Override
    protected void initViews() {
        //CommonActivityConfigs.add("listview", TestListViewFragment.class);


        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        addButton("view动画", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new ViewAnimationFragment());
            }
        });
        addButton("动态代理测试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestDynamicProxyFragment());
            }
        });
        addButton("okhttp", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestOKhttpFragment());
            }
        });
        addButton("annotation", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestAnnotationFragment());
            }
        });

        addButton("listview", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestListViewFragment());
            }
        });

        addButton("lock", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new LockTestFragment());
            }
        });
    }
}
