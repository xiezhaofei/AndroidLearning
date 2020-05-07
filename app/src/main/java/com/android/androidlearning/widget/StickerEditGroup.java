package com.android.androidlearning.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.androidlearning.R;
import com.android.androidlearning.utils.ViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiezhaofei on 2020/4/26
 * <p>
 * Describe:
 */

public class StickerEditGroup extends FrameLayout implements View.OnTouchListener {
    private static final String TAG = "StickerEditGroup";
    private static final int DRAG = 0;
    private static final int SCALE = 1;
    private int mode;
    private int mOffsetX;
    private int mOffsetY;
    private float mStartXPos;
    private float mStartYPos;
    private float mFocusStickerLeft;
    private float mFocusStickerTop;
    private float mFocusStickerRotation;
    private float mFocusStickerWidth;
    private float mFocusStickerHeight;
    private float lengthOld;
    private float mLastDegree;
    private boolean isEditing = false;
    private LinearLayout mDeleteLayout;
    private ImageView mDeleteImageView;
    private ImageView mEditedViewShadow;
    private TextView mDeleteTextView;
    private List<View> mStickerViews = new ArrayList<>();
    protected FrameLayout mShowViewsContainer;
    protected FrameLayout mStickerLayout;
    private OnStickerDragListener mOnStickerDragListener;
    private View mFocusView;


    private View mScaleView;
    private int mScaleViewWidth;
    private int mScaleViewHeight;
    private boolean isCanDrag = true;
    private long startTime;

    public StickerEditGroup(@NonNull Context context) {
        super(context);
        initViews();
    }

    public StickerEditGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public StickerEditGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setStickerDragListener(OnStickerDragListener listener) {
        this.mOnStickerDragListener = listener;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void addSticker(final View sticker) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        if (isDifferentSizeWithVideo()) {
            if (mOffsetX <= 0 || mOffsetY <= 0) {
                int[] location = new int[2];
                mShowViewsContainer.getLocationInWindow(location);
                int[] location2 = new int[2];
                mStickerLayout.getLocationInWindow(location2);
                int offSetX = location[0] - location2[0];
                int offsetY = location[1] - location2[1];
                mOffsetX = offSetX;
                mOffsetY = offsetY;
            }
            mShowViewsContainer.addView(sticker, params);
        } else {
            mStickerLayout.addView(sticker, params);
        }
        mStickerViews.add(sticker);
        sticker.postDelayed(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams stickerLayoutParams = (FrameLayout.LayoutParams) sticker.getLayoutParams();
                if (stickerLayoutParams.gravity == Gravity.CENTER) {
                    stickerLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
                    stickerLayoutParams.leftMargin = sticker.getLeft();
                    stickerLayoutParams.topMargin = sticker.getTop();
                    sticker.setLayoutParams(stickerLayoutParams);
                }
            }
        }, 500);
    }

    private void initViews() {
        mDeleteLayout = new LinearLayout(getContext());
        mDeleteLayout.setOrientation(LinearLayout.HORIZONTAL);
        mDeleteLayout.setGravity(Gravity.CENTER);
        mDeleteLayout.setBackgroundColor(Color.parseColor("#ff6633"));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(getContext(), 54));
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        addView(mDeleteLayout, layoutParams);

        mDeleteImageView = new ImageView(this.getContext());
        mDeleteImageView.setImageResource(R.drawable.delete_sticker_icon);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewUtils.dip2px(getContext(), 25),
                ViewUtils.dip2px(getContext(), 30.5f));
        params.gravity = Gravity.CENTER_VERTICAL;
        mDeleteLayout.addView(mDeleteImageView, params);

        mDeleteTextView = new TextView(getContext());
        mDeleteTextView.setTextColor(Color.parseColor("#FFFFFF"));
        LinearLayout.LayoutParams deleteLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deleteLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        mDeleteTextView.setTextSize(16);
        mDeleteLayout.addView(mDeleteTextView, deleteLayoutParams);

        mDeleteLayout.setVisibility(View.INVISIBLE);

        mStickerLayout = new FrameLayout(getContext());
        addView(mStickerLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        mEditedViewShadow = new ImageView(getContext());
        mStickerLayout.addView(mEditedViewShadow);
        mEditedViewShadow.setVisibility(GONE);

        mStickerLayout.setOnTouchListener(this);
    }


    private void onEdit(View view) {
        if (isEditing || !isDifferentSizeWithVideo() || view == mEditedViewShadow) {
            return;
        }
        isEditing = true;
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        if (params.gravity == Gravity.CENTER) {
            params.width = view.getWidth();
            params.height = view.getHeight();
            params.gravity = Gravity.TOP | Gravity.LEFT;
            params.leftMargin = view.getLeft();
            params.topMargin = view.getTop();
            view.setLayoutParams(params);
        }

        LayoutParams editedViewShadowParams = (FrameLayout.LayoutParams) mEditedViewShadow.getLayoutParams();
        editedViewShadowParams.width = view.getWidth();
        editedViewShadowParams.height = view.getHeight();
        editedViewShadowParams.gravity = Gravity.TOP | Gravity.LEFT;
        editedViewShadowParams.leftMargin = view.getLeft() + mOffsetX;
        editedViewShadowParams.topMargin = view.getTop() + mOffsetY;
        Bitmap bitmap = null;
        if (view instanceof ImageView) {
//            bitmap = ((ImageView) view).getBitmap();
            bitmap = ViewUtils.viewToBitmap(view);
        }
        mEditedViewShadow.setImageBitmap(bitmap);
        view.setVisibility(INVISIBLE);
        mEditedViewShadow.setPivotX(view.getPivotX());
        mEditedViewShadow.setPivotY(view.getPivotY());
        mEditedViewShadow.setTranslationX(view.getTranslationX());
        mEditedViewShadow.setTranslationY(view.getTranslationY());
        mEditedViewShadow.setRotation(view.getRotation());
        mEditedViewShadow.setVisibility(VISIBLE);
        mEditedViewShadow.setLayoutParams(editedViewShadowParams);
        mEditedViewShadow.setImageAlpha(255);
    }

    private void onIdle(View view) {
        if (!isEditing || !isDifferentSizeWithVideo() || view == mEditedViewShadow) {
            return;
        }
        isEditing = false;
        mEditedViewShadow.setVisibility(GONE);
        view.setVisibility(VISIBLE);
    }


    private boolean myTouch(MotionEvent event) {
        if (event == null) {
            return false;
        }

        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction() & event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis();
                View focusView = findFocusView(event);
                if (focusView != null) {
                    mFocusView = focusView;
                    mode = DRAG;
                    mStartXPos = x;
                    mStartYPos = y;
                    mFocusStickerLeft = mFocusView.getLeft();
                    mFocusStickerTop = mFocusView.getTop();
                    mFocusStickerWidth = mFocusView.getWidth();
                    mFocusStickerHeight = mFocusView.getHeight();
                    mFocusStickerRotation = mFocusView.getRotation();
                    onEdit(mFocusView);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                View scaleView = findAppropriateView(event);
                if (scaleView != null) {
                    mScaleView = scaleView;
                    if (mScaleView == mFocusView || mFocusView != null) {
                        onIdle(mFocusView);
                        mFocusView = null;
                    }
                    lengthOld = calculation(event);
                    mLastDegree = getDegree(event);
                    mScaleViewWidth = mScaleView.getWidth();
                    mScaleViewHeight = mScaleView.getHeight();
                    FrameLayout.LayoutParams scaleParams = (LayoutParams) mScaleView.getLayoutParams();
                    if (scaleParams.gravity == Gravity.CENTER) {
                        scaleParams.gravity = Gravity.LEFT | Gravity.TOP;
                        scaleParams.topMargin = mScaleView.getTop();
                        scaleParams.leftMargin = mScaleView.getLeft();
                        mScaleView.setLayoutParams(scaleParams);
                    }
                    mode = SCALE;
                    onEdit(mScaleView);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG && mFocusView != null && isStickerMove(mStartXPos, mStartYPos, x, y)) {
                    if (mOnStickerDragListener != null) {
                        mOnStickerDragListener.onStickerDragStart();
                    }
                    FrameLayout.LayoutParams params = ((FrameLayout.LayoutParams) mFocusView.getLayoutParams());
                    params.leftMargin = (int) (mFocusStickerLeft + x - mStartXPos);
                    params.topMargin = (int) (mFocusStickerTop + y - mStartYPos);
                    params.gravity = Gravity.LEFT | Gravity.TOP;
                    //??
                    params.width = mFocusView.getWidth();
                    params.height = mFocusView.getHeight();

                    if (mOffsetY + params.topMargin + mFocusView.getHeight() / 2 > mDeleteLayout.getTop()) {
                        mEditedViewShadow.setImageAlpha((int) (0.5f * 255));
                        mDeleteLayout.setVisibility(View.VISIBLE);
                        mDeleteTextView.setText("松手即可删除");
                        mDeleteImageView.setImageResource(R.drawable.delete_sticker_open_icon);
                    } else {
                        mEditedViewShadow.setImageAlpha(255);
                        mDeleteLayout.setVisibility(View.VISIBLE);
                        mDeleteTextView.setText("拖到此处删除");
                        mDeleteImageView.setImageResource(R.drawable.delete_sticker_icon);
                    }
                    mFocusView.requestLayout();
                } else if (mode == SCALE && event.getPointerCount() == 2 && mScaleView != null) {
                    if (mOnStickerDragListener != null) {
                        mOnStickerDragListener.onStickerDragStart();
                    }
                    mEditedViewShadow.setImageAlpha(255);
                    RotationInfo info = calculateRotationInfo(mScaleView, event);
                    if (info != null) {
                        mScaleView.setRotation(mScaleView.getRotation() + info.degreeOffset / 2);
                    }
                    float scale = calculation(event) / lengthOld;
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mScaleView.getLayoutParams();
                    params.gravity = Gravity.LEFT | Gravity.TOP;

                    int oldWidth = mScaleView.getWidth();
                    int oldHeight = mScaleView.getHeight();

                    float ratio = mScaleViewHeight * 1f / mScaleViewWidth;
                    params.width = (int) (mScaleViewWidth * scale) > getWidth() ? getWidth() : mScaleViewWidth * scale < 0.16f * getWidth() ? (int) (0.16f * getWidth()) : (int) (mScaleViewWidth * scale);
                    params.height = (int) (params.width * ratio);


                    params.leftMargin = params.leftMargin - (params.width - oldWidth) / 2;
                    params.topMargin = params.topMargin - (params.height - oldHeight) / 2;

                    mScaleView.requestLayout();
                    if (mDeleteLayout.getVisibility() != VISIBLE) {
                        mDeleteLayout.setVisibility(View.VISIBLE);
                        mDeleteTextView.setText("拖到此处删除");
                        mDeleteImageView.setImageResource(R.drawable.delete_sticker_icon);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mDeleteLayout.setVisibility(INVISIBLE);
                if (mScaleView == null && mFocusView == null) {
                    if (mOnStickerDragListener != null) {
                        mOnStickerDragListener.onStickerDragEnd();
                    }
                    break;
                }
                if (mFocusView != null) {
                    lengthOld = 1;
                    if (isDifferentSizeWithVideo() && isOutside(mFocusView, mShowViewsContainer) || isOutside(mFocusView, mStickerLayout)) {
                        if (isOutside(mFocusView, mShowViewsContainer)) {
                            ((LayoutParams) mFocusView.getLayoutParams()).topMargin = (int) (mFocusStickerTop + mFocusStickerHeight / 2 - mFocusView.getHeight() / 2);
                            ((LayoutParams) mFocusView.getLayoutParams()).leftMargin = (int) (mFocusStickerLeft + mFocusStickerWidth / 2 - mFocusView.getWidth() / 2);
                            mFocusView.setRotation(mFocusStickerRotation);
                        }
                    }
                    mFocusView.requestLayout();
                    onIdle(mFocusView);
                } else {
                    if (isDifferentSizeWithVideo() && isOutside(mScaleView, mShowViewsContainer) || isOutside(mScaleView, mStickerLayout)) {
                        if (isOutside(mScaleView, mShowViewsContainer)) {
                            ((LayoutParams) mScaleView.getLayoutParams()).topMargin = (int) (mFocusStickerTop + mFocusStickerHeight / 2 - mScaleView.getHeight() / 2);
                            ((LayoutParams) mScaleView.getLayoutParams()).leftMargin = (int) (mFocusStickerLeft + mFocusStickerWidth / 2 - mScaleView.getWidth() / 2);
                            mScaleView.setRotation(mFocusStickerRotation);
                        }
                    }
                    mScaleView.requestLayout();
                    onIdle(mScaleView);
                }
                mScaleView = null;
                mFocusView = null;
                if (mOnStickerDragListener != null) {
                    mOnStickerDragListener.onStickerDragEnd();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDeleteLayout.setVisibility(INVISIBLE);
                if (mScaleView == null && mFocusView == null) {
                    if (mOnStickerDragListener != null) {
                        mOnStickerDragListener.onStickerDragEnd();
                    }
                    break;
                }
                lengthOld = 1;
                if (mode == DRAG && mFocusView != null) {
                    FrameLayout.LayoutParams params = ((FrameLayout.LayoutParams) mFocusView.getLayoutParams());
                    if (mOffsetY + params.topMargin + mFocusView.getHeight() / 2 > mDeleteLayout.getTop()) {
                        if (isDifferentSizeWithVideo() && mShowViewsContainer.indexOfChild(mFocusView) >= 0) {
                            mShowViewsContainer.removeView(mFocusView);
                            mStickerViews.remove(mFocusView);

                        } else if (!isDifferentSizeWithVideo() && mStickerLayout.indexOfChild(mFocusView) >= 0) {
                            mStickerLayout.removeView(mFocusView);
                            mStickerViews.remove(mFocusView);
                        }
                        onIdle(mFocusView);
                        mFocusView = null;
                        mScaleView = null;
                        if (mOnStickerDragListener != null) {
                            mOnStickerDragListener.onStickerDragEnd();
                        }
                    } else {
                        if (isDifferentSizeWithVideo() && isOutside(mFocusView, mShowViewsContainer) || isOutside(mFocusView, mStickerLayout)) {
                            if (isOutside(mFocusView, mShowViewsContainer)) {
                                ((LayoutParams) mFocusView.getLayoutParams()).topMargin = (int) (mFocusStickerTop + mFocusStickerHeight / 2 - mFocusView.getHeight() / 2);
                                ((LayoutParams) mFocusView.getLayoutParams()).leftMargin = (int) (mFocusStickerLeft + mFocusStickerWidth / 2 - mFocusView.getWidth() / 2);
                                mFocusView.setRotation(mFocusStickerRotation);
                            }
                        }
                        if (mStickerLayout.indexOfChild(mFocusView) >= 0 &&
                                mStickerLayout.indexOfChild(mFocusView) < mStickerViews.size() - 1) {
                            mStickerLayout.removeView(mFocusView);
                            mStickerViews.remove(mFocusView);
                            mStickerLayout.addView(mFocusView);
                            mStickerViews.add(mFocusView);
                        } else if (mShowViewsContainer != null && mShowViewsContainer.indexOfChild(mFocusView) >= 0
                                && mShowViewsContainer.indexOfChild(mFocusView) < mStickerViews.size() - 1) {
                            mShowViewsContainer.removeView(mFocusView);
                            mStickerViews.remove(mFocusView);
                            mShowViewsContainer.addView(mFocusView);
                            mStickerViews.add(mFocusView);
                        }
//                        if (mFocusView instanceof PicassoStickerView) {
//                            ((PicassoStickerView) mFocusView).getPicassoViewShadow().getLayoutParams().width = params.width;
//                            ((PicassoStickerView) mFocusView).getPicassoViewShadow().getLayoutParams().height = params.height;
//                            ((PicassoStickerView) mFocusView).getPicassoViewShadow().requestLayout();
//                        }
                        if (mFocusView != null) {
                            mFocusView.requestLayout();
                            onIdle(mFocusView);
                        }
                        Map<String, Object> valLabMap = new HashMap<>();
                        Map<String, Object> customMap = new HashMap<>();
                        int viewGroupWidth = getWidth();
                        int viewGroupHeight = getHeight();
                        if (isDifferentSizeWithVideo()) {
                            viewGroupWidth = mShowViewsContainer.getWidth();
                            viewGroupHeight = mShowViewsContainer.getHeight();
                        }
                        String centerX = String.valueOf((mFocusView.getWidth() * 1f / 2 + mFocusView.getLeft()) / viewGroupWidth);
                        String centerY = String.valueOf((mFocusView.getHeight() * 1f / 2 + mFocusView.getTop()) / viewGroupHeight);
                        customMap.put("position_id", centerX + "," + centerY);
                        valLabMap.put("custom", customMap);

                        mScaleView = null;
                        mFocusView = null;
                    }
                } else {
                    if (isDifferentSizeWithVideo() && isOutside(mScaleView, mShowViewsContainer) || isOutside(mScaleView, mStickerLayout)) {
                        if (isOutside(mScaleView, mShowViewsContainer)) {
                            ((LayoutParams) mScaleView.getLayoutParams()).topMargin = (int) (mFocusStickerTop + mFocusStickerHeight / 2 - mScaleView.getHeight() / 2);
                            ((LayoutParams) mScaleView.getLayoutParams()).leftMargin = (int) (mFocusStickerLeft + mFocusStickerWidth / 2 - mScaleView.getWidth() / 2);
                            mScaleView.setRotation(mFocusStickerRotation);
                        }
                    }

                    FrameLayout.LayoutParams params = ((FrameLayout.LayoutParams) mScaleView.getLayoutParams());
                    if (mStickerLayout.indexOfChild(mScaleView) >= 0 &&
                            mStickerLayout.indexOfChild(mScaleView) < mStickerViews.size() - 1) {
                        mStickerLayout.removeView(mScaleView);
                        mStickerViews.remove(mScaleView);
                        mStickerLayout.addView(mScaleView);
                        mStickerViews.add(mScaleView);
                    } else if (mShowViewsContainer.indexOfChild(mScaleView) >= 0 && mShowViewsContainer.indexOfChild(mScaleView) < mStickerViews.size() - 1) {
                        mShowViewsContainer.removeView(mScaleView);
                        mStickerViews.remove(mScaleView);
                        mShowViewsContainer.addView(mScaleView);
                        mStickerViews.add(mScaleView);
                    }
                    mScaleView.requestLayout();
                    onIdle(mScaleView);
                    mScaleView = null;
                    mFocusView = null;
                }

                if (mOnStickerDragListener != null) {
                    mOnStickerDragListener.onStickerDragEnd();
                }
                break;
        }
        return true;
    }

    public boolean isOutside(View child, ViewGroup parent) {
        if (((LayoutParams) child.getLayoutParams()).topMargin < -child.getHeight() / 2
                || ((LayoutParams) child.getLayoutParams()).topMargin > parent.getHeight() - child.getHeight() / 2
                || ((LayoutParams) child.getLayoutParams()).leftMargin < -child.getWidth() / 2
                || ((LayoutParams) child.getLayoutParams()).leftMargin > parent.getWidth() - child.getWidth() / 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event == null || !isCanDrag) {
            return false;
        }
        boolean result = myTouch(event);
        if (mScaleView != null || mFocusView != null) {
            View view = mScaleView != null ? mScaleView : mFocusView;
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            LayoutParams editedViewShadowParams = (FrameLayout.LayoutParams) mEditedViewShadow.getLayoutParams();
            editedViewShadowParams.width = view.getWidth();
            editedViewShadowParams.height = view.getHeight();
            if (params.leftMargin == 0 && params.topMargin == 0) {
                editedViewShadowParams.gravity = Gravity.CENTER;
            } else {
                editedViewShadowParams.gravity = Gravity.TOP | Gravity.LEFT;
                editedViewShadowParams.leftMargin = params.leftMargin + mOffsetX;
                editedViewShadowParams.topMargin = params.topMargin + mOffsetY;
            }
            mEditedViewShadow.setPivotX(view.getPivotX());
            mEditedViewShadow.setPivotY(view.getPivotY());
            mEditedViewShadow.setRotation(view.getRotation());
            mEditedViewShadow.requestLayout();
        }
        return result;
    }

    private boolean isDifferentSizeWithVideo() {
        return mShowViewsContainer != null;
    }

    public void setShowViewsContainer(FrameLayout container) {
        mShowViewsContainer = container;
    }

    private float calculation(MotionEvent event) {
        float x1 = event.getX();
        float x2 = event.getX(1);
        float y1 = event.getY();
        float y2 = event.getY(1);
        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    private RotationInfo calculateRotationInfo(View view, MotionEvent event) {

        //line 1
        float x1 = event.getX();
        float x2 = event.getX(1);
        float y1 = event.getY();
        float y2 = event.getY(1);

        RotationInfo info = new RotationInfo();
        info.pivotX = (x1 + x2) / 2;
        info.pivotY = (y1 + y2) / 2;

        float curDegree = getDegree(event);
        info.degreeOffset = curDegree - mLastDegree;
        if (curDegree - mLastDegree > 90) {
            info.degreeOffset = info.degreeOffset - 180;
        } else if (curDegree - mLastDegree < -90) {
            info.degreeOffset = info.degreeOffset + 180;
        }

        mLastDegree = getDegree(event);

        return info;
    }

    private float getDegree(MotionEvent event) {
        float x1 = event.getX();
        float x2 = event.getX(1);
        float y1 = event.getY();
        float y2 = event.getY(1);
        float a;
        a = (x1 - x2) == 0 ? Float.MAX_VALUE / 2 : (y1 - y2) / (x1 - x2);
        return (float) Math.toDegrees(Math.atan(a));
    }


    public boolean hasStickerInfo() {
        return mStickerViews.size() > 0;
    }

    public List<View> getStickerViews() {
        return mStickerViews;
    }

    public interface OnStickerDragListener {
        public void onStickerDragStart();

        public void onStickerDragEnd();
    }


    public void addSticker(View sticker, double x, double y, double width, double height, float rotation) {
        Log.d(TAG, String.format("x = %f , y = %f , width = %f , height = %f , rotation = %f", x, y, width, height, rotation));
        int viewGroupWidth;
        int viewGroupHeight;
        if (isDifferentSizeWithVideo()) {
            viewGroupWidth = mShowViewsContainer.getWidth();
            viewGroupHeight = mShowViewsContainer.getHeight();
            if (mOffsetX <= 0 || mOffsetY <= 0) {
                int[] location = new int[2];
                mShowViewsContainer.getLocationInWindow(location);
                int[] location2 = new int[2];
                mStickerLayout.getLocationInWindow(location2);
                int offSetX = location[0] - location2[0];
                int offsetY = location[1] - location2[1];
                mOffsetX = offSetX;
                mOffsetY = offsetY;
            }
        } else {
            viewGroupWidth = mStickerLayout.getWidth();
            viewGroupHeight = mStickerLayout.getHeight();
        }
        FrameLayout.LayoutParams stickerLayout = new FrameLayout.LayoutParams((int) (width * viewGroupWidth),
                (int) (height * viewGroupHeight));
        stickerLayout.gravity = Gravity.LEFT | Gravity.TOP;
        stickerLayout.setMargins((int) (x * viewGroupWidth), (int) (y * viewGroupHeight), 0, 0);
        sticker.setRotation(rotation);
        if (isDifferentSizeWithVideo()) {
            mShowViewsContainer.addView(sticker, stickerLayout);
        } else {
            mStickerLayout.addView(sticker, stickerLayout);
        }
        mStickerViews.add(sticker);
    }

    public int getViewGroupWidth() {
        if (isDifferentSizeWithVideo()) {
            return mShowViewsContainer.getWidth();
        } else {
            return mStickerLayout.getWidth();
        }
    }


    public int getViewGroupHeight() {
        if (isDifferentSizeWithVideo()) {
            return mShowViewsContainer.getHeight();
        } else {
            return mStickerLayout.getHeight();
        }
    }


    private static class RotationInfo {
        float pivotX;
        float pivotY;
        float degreeOffset;
    }


    private View findAppropriateView(MotionEvent event) {
        float x1 = event.getX();
        float y1 = event.getY();
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        float centerX = (Math.max(x1, x2) - Math.min(x1, x2)) / 2 + Math.min(x1, x2);
        float centerY = (Math.max(y1, y2) - Math.min(y1, y2)) / 2 + Math.min(y1, y2);
        for (int i = mStickerViews.size() - 1; i >= 0; i--) {
            View stickerView = mStickerViews.get(i);
            int left, top;
            if (isDifferentSizeWithVideo()) {
                left = stickerView.getLeft() + mOffsetX;
                top = stickerView.getTop() + mOffsetY;
            } else {
                left = stickerView.getLeft();
                top = stickerView.getTop();
            }
            int width = stickerView.getWidth();
            int height = stickerView.getHeight();
            float[] src = {
                    0, 0,
                    0, height,
                    width, 0,
                    width, height
            };
            float[] dst = new float[8];
            Matrix m = new Matrix();
            m.setRotate(stickerView.getRotation(), width / 2, height / 2);
            m.mapPoints(dst, src);
            for (int j = 0; j < dst.length; j++) {
                if (j % 2 == 0) {
                    dst[j] += left;
                } else {
                    dst[j] += top;
                }
            }
            PointF p1 = new PointF(dst[0], dst[1]);
            PointF p2 = new PointF(dst[2], dst[3]);
            PointF p3 = new PointF(dst[4], dst[5]);
            PointF p4 = new PointF(dst[6], dst[7]);
            PointF p = new PointF(centerX, centerY);
            if (isInsideRect(p1, p2, p3, p4, p)) {
                return stickerView;
            }
        }
        return null;
    }


    private View findFocusView(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        for (int i = mStickerViews.size() - 1; i >= 0; i--) {
            View stickerView = mStickerViews.get(i);
            int left, top;
            if (isDifferentSizeWithVideo()) {
                left = stickerView.getLeft() + mOffsetX;
                top = stickerView.getTop() + mOffsetY;
            } else {
                left = stickerView.getLeft();
                top = stickerView.getTop();
            }
            int width = stickerView.getWidth();
            int height = stickerView.getHeight();
            float rotation = stickerView.getRotation();
            float[] src = {
                    0, 0,
                    0, height,
                    width, 0,
                    width, height
            };
            float[] dst = new float[8];
            Matrix m = new Matrix();
            m.setRotate(rotation, width / 2, height / 2);
            m.mapPoints(dst, src);
            for (int j = 0; j < dst.length; j++) {
                if (j % 2 == 0) {
                    dst[j] += left;
                } else {
                    dst[j] += top;
                }
            }
            PointF p1 = new PointF(dst[0], dst[1]);
            PointF p2 = new PointF(dst[2], dst[3]);
            PointF p3 = new PointF(dst[4], dst[5]);
            PointF p4 = new PointF(dst[6], dst[7]);
            PointF p = new PointF(x, y);
            if (isInsideRect(p1, p2, p3, p4, p)) {
                return stickerView;
            }
        }
        return null;
    }

    private boolean isEditView(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int _3DP = ViewUtils.dip2px(getContext(), 3);
//        if (view instanceof PicassoStickerView) {
//            int left, top;
//            ImageView editView = ((PicassoStickerView) view).getEditView();
//            if (isDifferentSizeWithVideo()) {
//                left = view.getLeft() + mOffsetX + editView.getLeft() + _3DP;
//                top = view.getTop() + mOffsetY + editView.getTop() + _3DP;
//            } else {
//                left = view.getLeft() + editView.getLeft() + _3DP;
//                top = view.getTop() + editView.getTop() + _3DP;
//            }
//            int width = editView.getWidth() + _3DP;
//            int height = editView.getHeight() + _3DP;
//            float[] src = {
//                    left, top,
//                    left, height + top,
//                    width + left, top,
//                    width + left, height + top
//            };
//
//            float[] dst = new float[8];
//            Matrix m = new Matrix();
//            m.setRotate(view.getRotation(), left + view.getWidth() / 2 - editView.getLeft(), top + view.getHeight() / 2 - editView.getTop());
//            m.mapPoints(dst, src);
//            PointF p1 = new PointF(dst[0], dst[1]);
//            PointF p2 = new PointF(dst[2], dst[3]);
//            PointF p3 = new PointF(dst[4], dst[5]);
//            PointF p4 = new PointF(dst[6], dst[7]);
//            PointF p = new PointF(x, y);
//            if (isInsideRect(p1, p2, p3, p4, p)) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
        return false;
    }


    private boolean isInsideRect(PointF p1, PointF p2, PointF p3, PointF p4, PointF p) {
        float angle1 = getAngle(p1, p2, p);
        float angle5 = getAngle(p1, p3, p);
        float angle2 = getAngle(p2, p, p1);
        float angle3 = getAngle(p2, p, p4);
        float angle4 = getAngle(p3, p, p1);
        float angle6 = getAngle(p3, p, p4);
        float angle7 = getAngle(p4, p, p2);
        float angle8 = getAngle(p4, p, p3);
        if (angle1 < 90 && angle2 < 90 & angle3 < 90 && angle4 < 90 && angle5 < 90 && angle6 < 90 && angle7 < 90 && angle8 < 90) {
            return true;
        } else {
            return false;
        }
    }

    private float getAngle(PointF cen, PointF first, PointF second) {
        float dx1, dx2, dx3, dy1, dy2, dy3;
        float angle;
        dx1 = first.x - cen.x;
        dy1 = first.y - cen.y;
        dx2 = second.x - cen.x;
        dy2 = second.y - cen.y;
        dx3 = first.x - second.x;
        dy3 = first.y - second.y;
        float a = (float) Math.sqrt(dx3 * dx3 + dy3 * dy3);
        float b = (float) Math.sqrt(dx1 * dx1 + dy1 * dy1);
        float c = (float) Math.sqrt(dx2 * dx2 + dy2 * dy2);
        angle = (float) Math.toDegrees((float) Math.acos((b * b + c * c - a * a) / (2 * b * c)));
        return angle;
    }

    public void setIsCanDrag(boolean isCanDrag) {
        this.isCanDrag = isCanDrag;
    }

    private boolean isStickerMove(float originX, float originY, float targetX, float targetY) {
        float distance = (float) Math.sqrt((originX - targetX) * (originX - targetX) + (originY - targetY) * (originY - targetY));
        return distance > 5;
    }
}
