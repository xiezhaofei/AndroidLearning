package com.android.androidlearning.learningcode.ipc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.android.androidlearning.BaseActivity2;
import com.android.androidlearning.learningcode.service.RemoteService;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class IPCActivity2 extends BaseActivity2 {

    @Override
    protected void initViews() {
        addButton("bindService", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xzf", "IPCActivity2 click");
                Intent intent = new Intent(getApplicationContext(), RemoteService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.d("xzf", "IPCActivity2 onServiceConnected");
                        final ServiceConnection connection = this;
                        addButton("unbind server", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                unbindService(connection);
                            }
                        });

                    }


                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d("xzf", "IPCActivity2 onServiceDisconnected");
                    }
                }, BIND_AUTO_CREATE);
            }
        });
    }
}
