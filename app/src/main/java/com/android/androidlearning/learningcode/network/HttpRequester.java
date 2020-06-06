package com.android.androidlearning.learningcode.network;


public class HttpRequester {

    public static void deliverRequest(HttpHandler httpHandler) {
        deliverRequest(httpHandler, 0);
    }

    public static void deliverRequestMain(HttpHandler httpHandler) {
        deliverRequest(httpHandler, 10);
    }

    public static void deliverRequest(HttpHandler httpHandler, int priority) {
        HttpDispatcher.getInstance().enqueue(new HttpCall(httpHandler, priority));
    }
}
