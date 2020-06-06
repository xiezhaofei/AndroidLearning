package com.android.androidlearning.learningcode.okhttp;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpCall implements Runnable, Comparable<HttpCall> {
    public int mPriority;          //0~10
    public long mCreatedTime;
    private String mHost;


    HttpCall(int priority, long ct) {
        mCreatedTime = ct;
        mPriority = priority;
    }

    public void cancel() {

    }

    @Override
    public void run() {
        //网络请求的整个流程收归在这里
        //request header 构建
        //返回结果解析 , 异常处理, 结果校验
        //数据上报

        //todo ..

        //todo

        //in the end
    }

    @Override
    public int compareTo(HttpCall o) {
//        return (int) ((o.mPriority * 100 - o.mCreatedTime) - (mPriority * 100 - mCreatedTime));
        return (o.mPriority - mPriority) * 100 - (int) (o.mCreatedTime - mCreatedTime);
    }

    @Override
    public String toString() {
        return "HttpCall{" +
                "mPriority=" + mPriority +
                ", mCreatedTime=" + (mCreatedTime) +
                ", value=" + (mPriority * 100 - mCreatedTime) +
                '}';
    }
}

