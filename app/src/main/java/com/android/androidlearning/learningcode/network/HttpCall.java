package com.android.androidlearning.learningcode.network;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

class HttpCall implements Runnable, Comparable<HttpCall> {
    private int mPriority; //0-10
    private long mCreatedTime;
    private String mHost;
    private HttpHandler mHttpHandler;


    HttpCall(HttpHandler mHttpHandler) {
        this.mHttpHandler = mHttpHandler;
        mCreatedTime = System.currentTimeMillis();
    }

    HttpCall(HttpHandler mHttpHandler, int priority) {
        this.mHttpHandler = mHttpHandler;
        mCreatedTime = System.currentTimeMillis();
        mPriority = priority;
    }

    String getHost() {
        if (mHost == null) {
            try {
                mHost = new URL(mHttpHandler.url()).getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return mHost;
    }

    public void cancel() {
    }

    @Override
    public void run() {

        Request.Builder builder = new Request.Builder()
                .url(mHttpHandler.url());

        builder.method(mHttpHandler.method(), mHttpHandler.buildBody());

        if (mHttpHandler.header() != null) {
            Set<Map.Entry<String, String>> set = mHttpHandler.header().entrySet();
            for (Map.Entry<String, String> entry : set) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.build();

        Call call = HttpDispatcher.getInstance().getHttpClient().newCall(request);

        try {
            Response response = call.execute();

            mHttpHandler.onResponse(mHttpHandler.url(), response);

        } catch (IOException e) {
            e.printStackTrace();

            mHttpHandler.onFailure(mHttpHandler.url(), e);
        }


        //in the end
        HttpDispatcher.getInstance().finished(this);
    }

    @Override
    public int compareTo(HttpCall o) {
        return (o.mPriority - mPriority) * 100 - (int) (o.mCreatedTime - mCreatedTime);
    }
}
