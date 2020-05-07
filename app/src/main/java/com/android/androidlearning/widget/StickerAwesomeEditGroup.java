package com.android.androidlearning.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by xiezhaofei on 2019/8/9
 * <p>
 * Describe:
 */
public class StickerAwesomeEditGroup extends StickerEditGroup {
    private static final String TAG = "StickerAwesomeEditGroup";
    public static final int STATUS_EDIT = 0;
    public static final int STATUS_PREVIEW = 1;
    private int mStatus;
    private int mStandardWidth;
    private int mStandardHeight;
    private HashMap<View, LocationInfo> mViewModelMapping = new HashMap();
    private float[] mMatrix = new float[]{
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f
    };

    public StickerAwesomeEditGroup(@NonNull Context context) {
        super(context);
    }

    public StickerAwesomeEditGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StickerAwesomeEditGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStandardSize(int width, int height) {
        mStandardWidth = width;
        mStandardHeight = height;
    }

    public void changeStatus(int status) {
        mStatus = status;
        boolean isClickEnable = mStatus == STATUS_EDIT;
//        for (View view : getStickerViews()) {
//            if (view instanceof PicassoStickerView) {
//                ((PicassoStickerView) view).setClickable(isClickEnable);
//            }
//        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mStatus == STATUS_PREVIEW) {
            return false;
        }
        return super.onTouch(v, event);
    }

    public void noteViewsRelativePosition(float[] matrix) {
        mMatrix = matrix;
        final float transx = matrix[Matrix.MTRANS_X];
        final float transy = matrix[Matrix.MTRANS_Y];

        final float scalex = matrix[Matrix.MSCALE_X];
        float skewy = matrix[Matrix.MSKEW_Y];
        final float rScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);

        Log.d(TAG, String.format("transX = %f ; transY = %f ; scale = %f", transx, transy, rScale));

        float showPicWidth = mStandardWidth * rScale;
        float showPicHeight = mStandardHeight * rScale;

        Log.d(TAG, String.format("showPicWidth = %f ; showPicHeight = %f", showPicWidth, showPicHeight));

        for (View view : getStickerViews()) {
            LocationInfo info = mViewModelMapping.get(view);
            if (info == null) {
                info = new LocationInfo();
                mViewModelMapping.put(view, info);
            }


//            info.width = view.getWidth() / showPicWidth * view.getScaleX();
//            info.height = view.getHeight() / showPicHeight * view.getScaleY();
//            info.leftMargin = (view.getLeft() - transx + view.getTranslationX()) / showPicWidth;
//            info.topMargin = (view.getTop() - transy + view.getTranslationY()) / showPicHeight;
//            info.rotation = view.getRotation();
//

            float centerX = view.getLeft() + view.getWidth() * 1f / 2 + view.getTranslationX();
            float centerY = view.getTop() + view.getHeight() * 1f / 2 + view.getTranslationY();

            float a0 = matrix[0];
            float a1 = matrix[1];
            float a2 = matrix[2];
            float a3 = matrix[3];
            float a4 = matrix[4];
            float a5 = matrix[5];

            float y = ((a3 * centerX - a0 * centerY) - (a3 * a2 - a0 * a5)) / (a3 * a1 - a0 * a4);
            float x = ((a4 * centerX - a1 * centerY) - (a2 * a4 - a1 * a5)) / (a0 * a4 - a3 * a1);


            info.leftMargin = (x - view.getWidth() * view.getScaleX() / rScale / 2) / mStandardWidth;
            info.topMargin = (y - view.getHeight() * view.getScaleY() / rScale / 2) / mStandardHeight;
            info.rotation = view.getRotation();
            info.preRotation = (float) getMatrixRotation(matrix);

            info.width = view.getWidth() * view.getScaleX() / rScale / mStandardWidth;
            info.height = view.getHeight() * view.getScaleY() / rScale / mStandardHeight;

            Log.d(TAG, "view width is " + view.getWidth() + " height is " + view.getHeight() + " left is " + view.getLeft() + " top is " + view.getTop() + " translationX is " + view.getTranslationX() + " translationY is " + view.getTranslationY());
            Log.d(TAG, info.toString());
        }
    }

    public void refreshChildrenLayout(float[] matrix) {
        mMatrix = matrix;
        final float transx = matrix[Matrix.MTRANS_X];
        final float transy = matrix[Matrix.MTRANS_Y];

        final float scalex = matrix[Matrix.MSCALE_X];
        float skewy = matrix[Matrix.MSKEW_Y];
        final float rScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);

        final float showPicWidth = mStandardWidth * rScale;
        final float showPicHeight = mStandardHeight * rScale;

        double rotation = getMatrixRotation(matrix);

        Log.d(TAG, String.format("transX = %f ; transY = %f ; scale = %f", transx, transy, rScale));
        Log.d(TAG, String.format("showPicWidth = %f ; showPicHeight = %f", showPicWidth, showPicHeight));
        for (final View view : getStickerViews()) {
            if (view != null) {

                final LocationInfo info = mViewModelMapping.get(view);
                if (info == null) {
                    continue;
                }
                view.setTranslationX(0);
                view.setTranslationY(0);
                view.setScaleX(1f);
                view.setScaleY(1f);

//                if (view instanceof PicassoStickerView) {
//                    PicassoStickerView picassoStickerView = (PicassoStickerView) view;
//                    LayoutParams layoutParams = (LayoutParams) picassoStickerView.getPicassoViewShadow().getLayoutParams();
//                    layoutParams.width = (int) (info.width * showPicWidth);
//                    layoutParams.height = (int) (info.height * showPicHeight);
//                    picassoStickerView.setScaleX(1f);
//                    picassoStickerView.setScaleY(1f);
//                    picassoStickerView.requestLayout();
//                }

                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                layoutParams.width = (int) (info.width * showPicWidth);
                layoutParams.height = (int) (info.height * showPicHeight);

                float x = (info.leftMargin + info.width / 2) * mStandardWidth;
                float y = (info.topMargin + info.height / 2) * mStandardHeight;

                view.setRotation((float) ((info.rotation + rotation - info.preRotation) % 360));


                layoutParams.leftMargin = (int) (x * matrix[Matrix.MSCALE_X] + y * matrix[Matrix.MSKEW_X] + matrix[Matrix.MTRANS_X]) - layoutParams.width / 2;
                layoutParams.topMargin = (int) (x * matrix[Matrix.MSKEW_Y] + y * matrix[Matrix.MSCALE_Y] + matrix[Matrix.MTRANS_Y]) - layoutParams.height / 2;

                view.requestLayout();
            }
        }
    }


    public void postMatrix(float[] matrix) {
        mMatrix = matrix;
        final float scalex = matrix[Matrix.MSCALE_X];
        float skewy = matrix[Matrix.MSKEW_Y];
        final float rScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);

        //rScale = rScale / mScale;
        float showPicWidth = mStandardWidth * rScale;

        double rotation = getMatrixRotation(matrix);

        for (final View sticker : getStickerViews()) {
            if (sticker != null) {

                LocationInfo info = mViewModelMapping.get(sticker);
                if (info == null || sticker.getWidth() == 0 || sticker.getHeight() == 0) {
                    continue;
                }
                //scale
                float widthScale = info.width * showPicWidth / sticker.getWidth();
                sticker.setScaleX(widthScale);
                sticker.setScaleY(widthScale);

                //rotation
                sticker.setRotation((float) ((info.rotation + rotation - info.preRotation) % 360));

                //translation
                float centerX = (info.leftMargin + info.width / 2) * mStandardWidth;
                float centerY = (info.topMargin + info.height / 2) * mStandardHeight;
                float newCenterX = centerX * matrix[Matrix.MSCALE_X] + centerY * matrix[Matrix.MSKEW_X] + matrix[Matrix.MTRANS_X];
                float newCenterY = centerX * matrix[Matrix.MSKEW_Y] + centerY * matrix[Matrix.MSCALE_Y] + matrix[Matrix.MTRANS_Y];

                float originViewCenterX = sticker.getWidth() * 1f / 2 + sticker.getLeft();
                float originViewCenterY = sticker.getHeight() * 1f / 2 + sticker.getTop();
                sticker.setTranslationX(newCenterX - originViewCenterX);
                sticker.setTranslationY(newCenterY - originViewCenterY);
            }
        }
    }


    public void addSticker(final View view, float leftMargin, float topMargin, float width, float height, float rotation) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.width = width;
        locationInfo.height = height;
        locationInfo.leftMargin = leftMargin;
        locationInfo.topMargin = topMargin;
        locationInfo.rotation = rotation;
        mViewModelMapping.put(view, locationInfo);

        final float scalex = mMatrix[Matrix.MSCALE_X];
        float skewy = mMatrix[Matrix.MSKEW_Y];
        final float rScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);

        final float showPicWidth = mStandardWidth * rScale;
        final float showPicHeight = mStandardHeight * rScale;

        float x = (locationInfo.leftMargin + locationInfo.width / 2) * mStandardWidth;
        float y = (locationInfo.topMargin + locationInfo.height / 2) * mStandardHeight;

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.d(TAG, "view w = " + view.getWidth() + " ; h = " + view.getHeight());
                refreshChildrenLayout(mMatrix);
            }
        });

        super.addSticker(view, (x * mMatrix[Matrix.MSCALE_X] + y * mMatrix[Matrix.MSKEW_X] + mMatrix[Matrix.MTRANS_X] - locationInfo.width * showPicWidth / 2) / getViewGroupWidth(),
                (x * mMatrix[Matrix.MSKEW_Y] + y * mMatrix[Matrix.MSCALE_Y] + mMatrix[Matrix.MTRANS_Y] - locationInfo.height * showPicHeight / 2) / getViewGroupHeight(),
                (locationInfo.width * showPicWidth) / getViewGroupWidth(), (locationInfo.height * showPicHeight) / getViewGroupHeight(), (float) (rotation + getMatrixRotation(mMatrix)));

    }

    public void resetSticker() {
        mViewModelMapping.clear();
        for (View view : getStickerViews()) {
            if (mStickerLayout != null) {
                mStickerLayout.removeView(view);
            }

            if (mShowViewsContainer != null) {
                mShowViewsContainer.removeView(view);
            }
        }
        getStickerViews().clear();
    }


    private double getMatrixRotation(float[] matrix) {
        //(1,0)
        float x = matrix[Matrix.MSCALE_X];
        float y = matrix[Matrix.MSKEW_Y];
        double cos = x / Math.sqrt(x * x + y * y);
        if (cos > 1) {
            cos = 1;
        }
        if (cos < -1) {
            cos = -1;
        }
        double angle = Math.acos(cos) / 2 / Math.PI * 360;
        //第三象限 第四象限
        if (y < 0) {
            angle = 360 - angle;
        }
        return angle;
    }

    private class LocationInfo {
        public float width;
        public float height;
        public float leftMargin;
        public float topMargin;
        public float rotation;
        public float preRotation;

        @Override
        public String toString() {
            return "LocationInfo{" +
                    "width=" + width +
                    ", height=" + height +
                    ", leftMargin=" + leftMargin +
                    ", topMargin=" + topMargin +
                    ", rotation=" + rotation +
                    '}';
        }
    }


    @Override
    public boolean isOutside(View child, ViewGroup parent) {
        if (((LayoutParams) child.getLayoutParams()).topMargin < (-child.getHeight() / 2 + (mOnCropRectListener != null ? mOnCropRectListener.getCropRect().top : 0))
                || ((LayoutParams) child.getLayoutParams()).topMargin > (mOnCropRectListener != null ? mOnCropRectListener.getCropRect().bottom : parent.getHeight()) - child.getHeight() / 2
                || ((LayoutParams) child.getLayoutParams()).leftMargin < (mOnCropRectListener != null ? mOnCropRectListener.getCropRect().left : 0) - child.getWidth() / 2
                || ((LayoutParams) child.getLayoutParams()).leftMargin > (mOnCropRectListener != null ? mOnCropRectListener.getCropRect().right : parent.getWidth()) - child.getWidth() / 2) {
            return true;
        }
        return false;
    }

    public interface OnCropRectChangeListener {
        RectF getCropRect();
    }

    private OnCropRectChangeListener mOnCropRectListener;

    public void setOnCropRectListener(OnCropRectChangeListener cropRectListener) {
        mOnCropRectListener = cropRectListener;
    }
}
