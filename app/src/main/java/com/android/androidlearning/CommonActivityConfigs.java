package com.android.androidlearning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezhaofei on 2020-03-22
 * <p>
 * Describe:
 */
public class CommonActivityConfigs {
    private static List<String> introduces = new ArrayList<>();
    private static List<Class> classes = new ArrayList<>();
    public static void add(String introduce,Class clazz){
        introduces.add(introduce);
        classes.add(clazz);
    }
}
