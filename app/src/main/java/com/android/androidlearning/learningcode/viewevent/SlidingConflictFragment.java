package com.android.androidlearning.learningcode.viewevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.androidlearning.R;

/**
 * Created by xiezhaofei on 2019-11-18
 * <p>
 * Describe:
 */
public class SlidingConflictFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fra_sliding_conflict, container, false);
        final MScrollView scrollView = view.findViewById(R.id.scrollView1);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                View sv = view.findViewById(R.id.scrollView2);
                scrollView.setPosition(sv.getTop(), sv.getLeft(), sv.getWidth(), sv.getHeight());
            }
        });
        return view;
    }
}
