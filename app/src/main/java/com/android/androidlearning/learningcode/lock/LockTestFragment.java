package com.android.androidlearning.learningcode.lock;

import android.view.View;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;
import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2020/4/17
 * <p>
 * Describe:
 */
public class LockTestFragment extends BaseFragment2 {
    private final Object lock = new Object();

    @Override
    protected void initViews() {
        super.initViews();
        addButton("synchronized 1", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        synchronized (lock) {
                            ALLog.d("xzf", "enter lock 1");
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ALLog.d("xzf", "lock waked up 4");
                        }
                    }
                }).start();
            }
        });
        addButton("synchronized 2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (lock) {
                            ALLog.d("xzf", "enter lock 2");
                            lock.notifyAll();
                            ALLog.d("xzf", "lock notifyAll 3");
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ALLog.d("xzf", "lock waked up 5");
                        }
                    }
                }).start();


            }
        });
        addButton("synchronized 3", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (lock) {
                            lock.notifyAll();
                        }
                    }
                }).start();
            }
        });
    }
}
