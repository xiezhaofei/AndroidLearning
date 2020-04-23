package com.android.androidlearning;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiezhaofei on 2020/4/16
 * <p>
 * Describe:
 */
public class ThreadManager {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final Executor NET_THREAD_POOL_EXECUTOR;
    private static final Executor FILE_THREAD_POOL_EXECUTOR;
    private static final Executor THREAD_POOL_EXECUTOR;

    static {
        THREAD_POOL_EXECUTOR = createThreadPoolExecutor();
        NET_THREAD_POOL_EXECUTOR = createThreadPoolExecutor();
        FILE_THREAD_POOL_EXECUTOR = createThreadPoolExecutor();
    }

    private static Executor createThreadPoolExecutor() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>(128, new Comparator<Runnable>() {
                    @Override
                    public int compare(Runnable o1, Runnable o2) {
                        if (o1 instanceof Task && o2 instanceof Task) {
                            if (((Task) o1).priority != ((Task) o2).priority) {
                                return ((Task) o1).priority - ((Task) o2).priority;
                            } else {
                                return (int) (((Task) o1).time - ((Task) o2).time);
                            }
                        }
                        return 0;
                    }
                }),
                new ThreadFactory() {
                    private final AtomicInteger mCount = new AtomicInteger(1);

                    public Thread newThread(Runnable r) {
                        return new Thread(r, "ThreadManager #" + mCount.getAndIncrement());
                    }
                });
    }

    public void executeNet(Runnable command, int priority) {
        if (command == null)
            throw new NullPointerException();
        NET_THREAD_POOL_EXECUTOR.execute(new Task(command, priority, System.currentTimeMillis()));
    }

    public void executeFile(Runnable command, int priority) {
        if (command == null)
            throw new NullPointerException();
        FILE_THREAD_POOL_EXECUTOR.execute(new Task(command, priority, System.currentTimeMillis()));
    }

    public void execute(Runnable command, int priority) {
        if (command == null)
            throw new NullPointerException();
        THREAD_POOL_EXECUTOR.execute(new Task(command, priority, System.currentTimeMillis()));
    }


    private static class Task implements Runnable {
        final Runnable runnable;
        final int priority;
        final long time;

        Task(Runnable runnable, int priority, long time) {
            this.runnable = runnable;
            this.priority = priority;
            this.time = time;
        }

        @Override
        public void run() {
            if (runnable != null) {
                runnable.run();
            }
        }
    }


}
