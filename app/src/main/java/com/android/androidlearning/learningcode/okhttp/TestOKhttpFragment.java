package com.android.androidlearning.learningcode.okhttp;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;
import com.android.androidlearning.learningcode.network.HttpHandler;
import com.android.androidlearning.learningcode.network.HttpRequester;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xiezhaofei on 2020-03-16
 * <p>
 * Describe:
 */
public class TestOKhttpFragment extends BaseFragment2 {
    OkHttpClient httpClient = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.N)
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
                Headers headers = call.request().headers();
                headers.newBuilder().add("", "").build();
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
                call.cancel();
            }
        });

        addButton("同步post", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //同步post请求

                        HttpRequester.deliverRequest(new HttpHandler() {
                            @Override
                            public String method() {
                                return HttpHandler.POST;
                            }

                            @Override
                            public String url() {
                                return "https://www.baidu.com";
                            }

                            @Override
                            public Map<String, String> header() {
                                return null;
                            }

                            @Override
                            public RequestBody buildBody() {
                                FormBody responseBody = new FormBody.Builder()
                                        .add("name", "zhangsan")
                                        .add("pwd", "111111111")
                                        .build();
                                return responseBody;
                            }

                            @Override
                            public void onFailure(String url, IOException e) {
                                trace(e.toString());
                            }

                            @Override
                            public void onResponse(String url, Response response) throws IOException {
                                trace(response.toString());
                            }
                        });


//                        Request request = new Request.Builder()
//                                .url()
//                                .post(responseBody)
//                                .build();
//
//
//                        Call call = httpClient.newCall(request);
//
//                        try {
//                            Response response = call.execute();
//                            trace(response.toString());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }


                    }
                }).start();
            }
        });

        addButton("测试优先级队列", new View.OnClickListener() {
            private final PriorityQueue<HttpCall> readyAsyncCalls = new PriorityQueue<>();

            @Override
            public void onClick(View v) {
                long ct = 0;
                readyAsyncCalls.add(new HttpCall(1, ct + 1500));
                readyAsyncCalls.add(new HttpCall(30, ct + 1500));
                readyAsyncCalls.add(new HttpCall(40, ct + 1500));

                readyAsyncCalls.add(new HttpCall(1, ct));
                readyAsyncCalls.add(new HttpCall(30, ct));
                readyAsyncCalls.add(new HttpCall(40, ct));
                readyAsyncCalls.add(new HttpCall(20, ct));
                readyAsyncCalls.add(new HttpCall(90, ct));
                readyAsyncCalls.add(new HttpCall(50, ct));


                readyAsyncCalls.add(new HttpCall(20, ct + 1500));
                readyAsyncCalls.add(new HttpCall(90, ct + 1500));
                readyAsyncCalls.add(new HttpCall(50, ct + 1500));

                readyAsyncCalls.add(new HttpCall(1, ct - 1500));
                readyAsyncCalls.add(new HttpCall(30, ct - 1500));
                readyAsyncCalls.add(new HttpCall(40, ct - 1500));
                readyAsyncCalls.add(new HttpCall(20, ct - 1500));
                readyAsyncCalls.add(new HttpCall(90, ct - 1500));
                readyAsyncCalls.add(new HttpCall(50, ct - 1500));

                for (HttpCall call : readyAsyncCalls) {
                    System.out.println(call.toString());
                }
                System.out.println("HttpCall =====================");
                while (!readyAsyncCalls.isEmpty()) {
                    HttpCall call = readyAsyncCalls.poll();
                    System.out.println(call.toString());
                }

            }
        });


    }
}
