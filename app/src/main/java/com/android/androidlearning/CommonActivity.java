package com.android.androidlearning;

import android.Manifest;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.androidlearning.learningcode.animation.ViewAnimationFragment;
import com.android.androidlearning.learningcode.annotation.TestAnnotationFragment;
import com.android.androidlearning.learningcode.glide.TestGlideFragment;
import com.android.androidlearning.learningcode.listview.TestListViewFragment;
import com.android.androidlearning.learningcode.lock.LockTestFragment;
import com.android.androidlearning.learningcode.material.TestSheetBehaviorFragment;
import com.android.androidlearning.learningcode.mvvm.KotlinTest;
import com.android.androidlearning.learningcode.okhttp.TestOKhttpFragment;
import com.android.androidlearning.learningcode.permissions.TestPermissionsFragment;
import com.android.androidlearning.learningcode.proxy.TestDynamicProxyFragment;
import com.android.androidlearning.learningcode.view.TestConstrainLayoutFragment;
import com.android.androidlearning.learningcode.view.TestMyViewFragment;
import com.android.androidlearning.learningcode.viewpager.TestViewPagerFragment;

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

        addButton("时间戳", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public static final int TYPE_HOUR = 0;
    public static final int TYPE_DAY = 1;
    public static final int TYPE_MONTH = 2;
    public static final int TYPE_YEAR = 3;

    public static final int TYPE_TODAY = 1;
    public static final int TYPE_YESTERDAY = 2;
    public static final int TYPE_THIS_WEEK = 3;
    public static final int TYPE_THIS_MONTH = 4;
    public static final int TYPE_EALIER = 5;
    private static final SimpleDateFormat SMMDDTIMEFORMAT = new SimpleDateFormat("MM-dd", sLocale);
    public static String formatCreateTimeDesc(Context context, long time) {
        long delta = Calendar.getInstance().getTimeInMillis() - time;
        // now - [0 秒, 60 秒)
        if (delta < MINUTE) {
            long second = delta / SECOND;
            return context.getString(R.string.resend_timer_time, second <= 0 ? 1 : second);
        }
        // Xm - [1 分钟, 60 分钟) ，如 1m - 59m
        if (delta < HOUR) {
            return context.getString(R.string.time_abbreviation_minute, delta / Time.MINUTE);
        }
        // Xh - [1 小时, 24 小时) ，如 1h - 6h
        if (delta < DAY) {
            return context.getString(R.string.time_abbreviation_hour, delta / Time.HOUR);
        }
        // Xd - [1 天, 7 天) ，如 1d - 6d
        if (delta < SEVEN_DAY) {
            return context.getString(R.string.time_abbreviation_day, delta / Time.DAY);
        }
        // 1w - [7天， 8天)
        if (delta - SEVEN_DAY > 0 && delta - SEVEN_DAY <= DAY) {
            return context.getString(R.string.time_abbreviation_week, 1);
        }
        Calendar createCalendar = Calendar.getInstance();
        createCalendar.setTimeInMillis(time);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        // 在今年内
        if (time >= calendar.getTimeInMillis() + DAY) {
            return SMMDDTIMEFORMAT.format(createCalendar.getTime());
        }
        // 超过今年
        return SCURTIMEFORMAT.format(createCalendar.getTime());
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
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
