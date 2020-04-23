package com.android.androidlearning.learningcode.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.androidlearning.learningcode.ipc.aidl.Book;
import com.android.androidlearning.learningcode.ipc.aidl.IBookManager;
import com.android.androidlearning.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezhaofei on 2020-01-02
 * <p>
 * Describe:
 */
public class RemoteService extends Service {
    private List<Book> mList = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("xzf", "onBind");
        return stub;
    }

    IBookManager.Stub stub = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.d("xzf", "getBookList size:" + mList.size());
            return mList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mList.add(book);
            Log.d("xzf", "addBook,size:" + mList.size());
        }
    };

    public RemoteService() {
        super();
    }

    @Override
    public void onCreate() {
        Log.d("xzf", "onCreate ; cur thread:" + Thread.currentThread().getName() + " ; cur process : " + AppUtils.getCurProcessName(this));
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("xzf", "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("xzf", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("xzf", "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("xzf", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("xzf", "onRebind");
        super.onRebind(intent);
    }
}
