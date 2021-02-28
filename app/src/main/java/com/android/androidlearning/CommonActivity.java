package com.android.androidlearning;

import android.util.Log;
import android.view.View;

import com.android.androidlearning.learningcode.animation.ViewAnimationFragment;
import com.android.androidlearning.learningcode.annotation.TestAnnotationFragment;
import com.android.androidlearning.learningcode.glide.TestGlideFragment;
import com.android.androidlearning.learningcode.listview.TestListViewFragment;
import com.android.androidlearning.learningcode.lock.LockTestFragment;
import com.android.androidlearning.learningcode.material.TestSheetBehaviorFragment;
import com.android.androidlearning.learningcode.okhttp.TestOKhttpFragment;
import com.android.androidlearning.learningcode.permissions.TestPermissionsFragment;
import com.android.androidlearning.learningcode.proxy.TestDynamicProxyFragment;
import com.android.androidlearning.learningcode.view.TestConstrainLayoutFragment;
import com.android.androidlearning.learningcode.view.TestMyViewFragment;
import com.android.androidlearning.learningcode.viewpager.TestViewPagerFragment;

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

        addButton("glide", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestGlideFragment());
            }
        });
        addButton("constrainlayout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestConstrainLayoutFragment());
            }
        });

        addButton("viewpager+fragment", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestViewPagerFragment());
            }
        });

        addButton("权限管理", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestPermissionsFragment());
            }
        });
        addButton("自定义view", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFragment(new TestMyViewFragment());
            }
        });
        addButton("测试sheet", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startFragment(new TestSheetBehaviorFragment());
            }
        });

        addButton("sheet",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAct(TestSheetActivity.class);
                    }
                });

        addButton("字符串格式", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xzf",String.format("Estimated amount: %s%.2f","ddd",0.05));
            }
        });

    }
}
