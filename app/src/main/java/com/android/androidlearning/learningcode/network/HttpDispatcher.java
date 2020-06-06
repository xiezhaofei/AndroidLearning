package com.android.androidlearning.learningcode.network;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.Util;


class HttpDispatcher {
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private ExecutorService executorService;
    private Runnable idleCallback;
    private OkHttpClient httpClient = new OkHttpClient();

    /**
     * Ready async calls in the order they'll be run.
     */
    private final PriorityQueue<HttpCall> readyAsyncCalls = new PriorityQueue<>();

    /**
     * Running asynchronous calls. Includes canceled calls that haven't finished yet.
     */
    private final Deque<HttpCall> runningAsyncCalls = new ArrayDeque<>();

    /**
     * Running synchronous calls. Includes canceled calls that haven't finished yet.
     */

    private HttpDispatcher() {
    }

    private static class HttpDispatcherInner {
        static HttpDispatcher dispatcher = new HttpDispatcher();
    }

    public static HttpDispatcher getInstance() {
        return HttpDispatcherInner.dispatcher;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public synchronized void setMaxRequests(int maxRequests) {
        if (maxRequests < 1) {
            throw new IllegalArgumentException("max < 1: " + maxRequests);
        }
        this.maxRequests = maxRequests;
        promoteCalls();
    }

    private void promoteCalls() {
        if (runningAsyncCalls.size() >= maxRequests) return; // Already running max capacity.
        if (readyAsyncCalls.isEmpty()) return; // No ready calls to promote.

        for (Iterator<HttpCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            HttpCall call = i.next();

            if (runningCallsForHost(call) < maxRequestsPerHost) {
                i.remove();
                runningAsyncCalls.add(call);
                executorService().execute(call);
            }

            if (runningAsyncCalls.size() >= maxRequests) return; // Reached max capacity.
        }
    }

    public synchronized int getMaxRequests() {
        return maxRequests;
    }

    public synchronized void setMaxRequestsPerHost(int maxRequestsPerHost) {
        if (maxRequestsPerHost < 1) {
            throw new IllegalArgumentException("max < 1: " + maxRequestsPerHost);
        }
        this.maxRequestsPerHost = maxRequestsPerHost;
        promoteCalls();
    }

    public synchronized int getMaxRequestsPerHost() {
        return maxRequestsPerHost;
    }

    public synchronized void setIdleCallback(Runnable idleCallback) {
        this.idleCallback = idleCallback;
    }

    public synchronized void enqueue(HttpCall call) {
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
            runningAsyncCalls.add(call);
            executorService().execute(call);
        } else {
            readyAsyncCalls.add(call);
        }
    }

    void finished(HttpCall call) {
        finished(runningAsyncCalls, call, true);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        int runningCallsCount;
        Runnable idleCallback;
        synchronized (this) {
            if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
            if (promoteCalls) promoteCalls();
            runningCallsCount = runningCallsCount();
            idleCallback = this.idleCallback;
        }

        if (runningCallsCount == 0 && idleCallback != null) {
            idleCallback.run();
        }
    }

    public synchronized int runningCallsCount() {
        return runningAsyncCalls.size();
    }

    private int runningCallsForHost(HttpCall call) {
        int result = 0;
        for (HttpCall c : runningAsyncCalls) {
            if (c.getHost().equals(call.getHost())) result++;
        }
        return result;
    }

    synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("PDD Dispatcher", false));
        }
        return executorService;
    }

    public synchronized int queuedCallsCount() {
        return readyAsyncCalls.size();
    }

    public synchronized void cancelAll() {
        for (HttpCall call : readyAsyncCalls) {
            call.cancel();
        }

        for (HttpCall call : runningAsyncCalls) {
            call.cancel();
        }
    }

}
