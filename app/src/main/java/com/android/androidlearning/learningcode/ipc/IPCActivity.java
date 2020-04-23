package com.android.androidlearning.learningcode.ipc;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.android.androidlearning.BaseActivity2;
import com.android.androidlearning.learningcode.ipc.aidl.Book;
import com.android.androidlearning.learningcode.ipc.aidl.IBookManager;
import com.android.androidlearning.learningcode.service.RemoteService;

import java.util.List;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class IPCActivity extends BaseActivity2 {

    private IBookManager mBookManager;
    private IPCActivity mActivity;
    private int mCount = 0;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("xzf", "onReceive");
            Intent intent1 = new Intent(getApplicationContext(), RemoteService.class);
            bindService(intent1, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.d("xzf", "onServiceDisconnected");
                }
            }, BIND_AUTO_CREATE);
        }
    };

    @Override
    protected void initViews() {
        mActivity = this;
        addButton("启动service", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread.currentThread().setName("hello");
                Log.d("xzf", "thread:" + Thread.currentThread().getName());
                Intent intent = new Intent(getApplicationContext(), RemoteService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.d("xzf", "onServiceConnected");
                        mBookManager = IBookManager.Stub.asInterface(service);

                        mActivity.addButton("增加一本书", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    mBookManager.addBook(new Book("android " + mCount++));
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        mActivity.addButton("返回所有书", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    List<Book> list = mBookManager.getBookList();
                                    Log.d("xzf", "size:" + list.size());
                                    for (Book book : list) {
                                        Log.d("xzf", "name:" + book.name);
                                    }
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d("xzf", "onServiceDisconnected");
                    }
                }, BIND_AUTO_CREATE);
            }
        });


        addButton("创建新页面", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(IPCActivity2.class);
            }
        });
//        Intent intent = new Intent();
//        intent.setAction("myaction");
        registerReceiver(receiver, new IntentFilter("myaction"));

        addButton("发广播", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("myaction");
                sendBroadcast(intent);
            }
        });
    }
}
