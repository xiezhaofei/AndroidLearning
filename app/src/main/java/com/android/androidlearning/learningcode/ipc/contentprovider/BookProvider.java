package com.android.androidlearning.learningcode.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-11-08
 * <p>
 * Describe:
 */
public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";

    @Override
    public boolean onCreate() {
        ALLog.d(TAG, "onCreate thread:" + Thread.currentThread().getName());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        ALLog.d(TAG, "query thread:" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        ALLog.d(TAG, "getType thread:" + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        ALLog.d(TAG, "insert thread:" + Thread.currentThread().getName());
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        ALLog.d(TAG, "delete thread:" + Thread.currentThread().getName());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        ALLog.d(TAG, "update thread:" + Thread.currentThread().getName());
        return 0;
    }
}
