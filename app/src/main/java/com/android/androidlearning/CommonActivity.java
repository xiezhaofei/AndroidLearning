package com.android.androidlearning;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.androidlearning.kotlin.KotlinReflect;
import com.android.androidlearning.learningcode.animation.ViewAnimationFragment;
import com.android.androidlearning.learningcode.annotation.TestAnnotationFragment;
import com.android.androidlearning.learningcode.glide.TestGlideFragment;
import com.android.androidlearning.learningcode.layer.TestLayerDetailFragment;
import com.android.androidlearning.learningcode.listview.TestListViewFragment;
import com.android.androidlearning.learningcode.lock.LockTestFragment;
import com.android.androidlearning.learningcode.material.TestSheetBehaviorFragment;
import com.android.androidlearning.learningcode.mvvm.KotlinTest;
import com.android.androidlearning.learningcode.mvvm.TestViewModelFragment;
import com.android.androidlearning.learningcode.okhttp.TestOKhttpFragment;
import com.android.androidlearning.learningcode.overdraw.TestRecyclerViewOverDrawFragment;
import com.android.androidlearning.learningcode.overdraw.TestRecyclerViewOverDrawFragment2;
import com.android.androidlearning.learningcode.permissions.TestPermissionsFragment;
import com.android.androidlearning.learningcode.proxy.TestDynamicProxyFragment;
import com.android.androidlearning.learningcode.view.TestConstrainLayoutFragment;
import com.android.androidlearning.learningcode.view.TestMyViewFragment;
import com.android.androidlearning.learningcode.viewevent.ScrollTestFragment;
import com.android.androidlearning.learningcode.viewpager.TestViewPagerFragment;
import com.android.androidlearning.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
                Log.d("xzf", String.format("Estimated amount: %s%.2f", "ddd", 0.05));
            }
        });

        addButton("test", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KotlinTest.Companion.getTest();
            }
        });

        addButton("申请权限", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });

        addButton("view model", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestViewModelFragment());
            }
        });

        addButton("test scroll", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new ScrollTestFragment());
            }
        });

        addButton("layer view", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestLayerDetailFragment());
            }
        });

        addButton("打开开发者选项", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.startDevelopmentActivity(CommonActivity.this);
            }
        });

        addButton("开启过度绘制", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.writeDebugHwOverdrawOptions();
            }
        });
        addButton("测试reccyclerview嵌套帧率", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestRecyclerViewOverDrawFragment());
            }
        });
        addButton("测试reccyclerview嵌套帧率2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new TestRecyclerViewOverDrawFragment2());
            }
        });
        addButton("获取手机机型", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xzf", Build.MODEL);
                Log.d("xzf", Build.BOARD);
                Log.d("xzf", Build.HARDWARE);
            }
        });

        addButton("反射往kotlin列表塞入数据", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KotlinReflect.INSTANCE.reflect();
            }
        });

    }


    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * 1000;
    public static final int HOUR = MINUTE * 60;
    private static final long DAY = HOUR * 24;
    private static final long THREE_DAY = DAY * 3;
    private static final long SEVEN_DAY = DAY * 7;
    private static final long YEAR = DAY * 365;


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
    }

    @Override
    public void onBackPressed() {
        if (!removeCurFragment()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            String value = permissions[i];
            int result = grantResults[i];
            Log.d("xzf", "value:" + value + " ; result = " + result);
        }
    }
}
