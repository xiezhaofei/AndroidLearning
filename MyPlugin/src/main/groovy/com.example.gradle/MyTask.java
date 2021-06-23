package com.example.gradle;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

public class MyTask extends DefaultTask {
    @Override
    public Task doFirst(Action<? super Task> action) {
        System.out.println("hello world");
        return super.doFirst(action);
    }
}
