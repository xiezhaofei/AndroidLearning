package com.android.androidlearning.learningcode.tmp;

import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.android.androidlearning.BaseActivity2;
import com.android.androidlearning.learningcode.fragment.BitmapRecycleFragment;
import com.android.androidlearning.learningcode.fragment.FirstPageFragment;
import com.android.androidlearning.learningcode.viewevent.EventDeliveryFragment;

/**
 * Created by xiezhaofei on 2019-12-04
 * <p>
 * Describe:
 */
public class TmpActivity extends BaseActivity2 {
    @Override
    protected void initViews() {
        addButton("图片", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new EventDeliveryFragment());
            }
        });
        addButton("gradview", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new FirstPageFragment());
            }
        });
        addButton("bitmap回收问题", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(new BitmapRecycleFragment());
            }
        });

        addButton("子线程弹出toast", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(TmpActivity.this, "子线程弹出toast", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(TmpActivity.this, "子线程弹出toast", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!removeCurFragment()) {
            super.onBackPressed();
        }

    }


}
