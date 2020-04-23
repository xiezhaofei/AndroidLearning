package com.android.androidlearning.learningcode.okhttp;

import android.view.View;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiezhaofei on 2020-03-16
 * <p>
 * Describe:
 */
public class TestOKhttpFragment extends BaseFragment2 {
    OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected void initViews() {
        super.initViews();

        addButton("同步get", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //同步get请求
                        Request request = new Request.Builder()
                                .url("https://www.baidu.com")
                                .get()
                                .build();
                        Call call = httpClient.newCall(request);
                        try {
                            Response response = call.execute();
                            trace(response.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        addButton("异步get", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .get()
                        .build();
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        trace(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        trace(response.toString());
                    }
                });
            }
        });

        addButton("同步post", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //同步post请求

                        FormBody responseBody = new FormBody.Builder()
                                .add("name", "zhangsan")
                                .add("pwd", "111111111")
                                .build();


                        Request request = new Request.Builder()
                                .url("https://www.baidu.com")
                                .post(responseBody)
                                .build();


                        Call call = httpClient.newCall(request);
                        try {
                            Response response = call.execute();
                            trace(response.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


    }
}
