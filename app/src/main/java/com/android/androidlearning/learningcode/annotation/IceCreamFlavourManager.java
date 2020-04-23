package com.android.androidlearning.learningcode.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;

/**
 * Created by xiezhaofei on 2020-03-20
 * <p>
 * Describe:
 */
public class IceCreamFlavourManager {

    private int flavour;

    public static final int VANILLA = 0;
    public static final int CHOCOLATE = 1;
    public static final int STRAWBERRY = 2;

    @Documented
    @IntDef({VANILLA, CHOCOLATE, STRAWBERRY})
    public @interface Flavour {
    }

    @Flavour
    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(@Flavour int flavour) {
        this.flavour = flavour;
    }


}
