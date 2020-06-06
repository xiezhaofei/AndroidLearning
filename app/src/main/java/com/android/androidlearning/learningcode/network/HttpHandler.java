package com.android.androidlearning.learningcode.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.Response;

public interface HttpHandler {
    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";

    abstract String method();

    abstract String url();

    abstract Map<String, String> header();

    abstract RequestBody buildBody();

    abstract void onFailure(String url, IOException e);

    abstract void onResponse(String url, Response response) throws IOException;

}
