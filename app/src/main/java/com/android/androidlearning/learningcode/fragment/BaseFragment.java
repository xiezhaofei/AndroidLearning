package com.android.androidlearning.learningcode.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by xiezhaofei on 2020-01-08
 * <p>
 * Describe:
 */
public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initValues(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return mView;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initValues(Bundle savedInstanceState);

    protected <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

    protected void trace(String msg) {
//        Log.d(TAG, msg);
        System.out.println(TAG + " " + msg);
    }


}
