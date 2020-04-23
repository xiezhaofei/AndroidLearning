package com.android.androidlearning.learningcode.annotation;

/**
 * Created by xiezhaofei on 2020-03-20
 * <p>
 * Describe:
 */
public class AnnotationTest {
    public AnnotationTest() {
        IceCreamFlavourManager manager = new IceCreamFlavourManager();
        manager.setFlavour(IceCreamFlavourManager.VANILLA);
        int tmp = manager.getFlavour();

    }
}
