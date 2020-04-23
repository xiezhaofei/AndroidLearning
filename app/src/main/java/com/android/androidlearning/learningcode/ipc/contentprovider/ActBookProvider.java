package com.android.androidlearning.learningcode.ipc.contentprovider;

import android.content.ContentProviderClient;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.androidlearning.BaseActivity;
import com.android.androidlearning.utils.ALLog;

/**
 * Created by xiezhaofei on 2019-11-08
 * <p>
 * Describe:
 */
public class ActBookProvider extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));

        Uri uri = Uri.parse("content://com.android.androidlearning.learningcode.ipc.contentprovider.BookProvider");
        boolean relative = uri.isRelative();
        ALLog.d("xzf", "relative:" + relative);
        ContentProviderClient client = getContentResolver().acquireUnstableContentProviderClient(uri);
        ALLog.d("xzf", "client:" + client);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);


    }
}
