package com.android.androidlearning.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Unit;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class ALLog {
    public static void i(String tag, String msg) {
    }

    public static void d(String tag, String msg) {
        System.out.println("ALLog => " + tag + ": " + msg);
    }

    public static void e(String tag, String msg) {

    }

    public static void d(@Nullable String tag, @NotNull Unit foo) {

    }
}
