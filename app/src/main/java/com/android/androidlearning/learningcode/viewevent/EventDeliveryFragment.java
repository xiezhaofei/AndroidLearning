package com.android.androidlearning.learningcode.viewevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.androidlearning.R;

/**
 * Created by xiezhaofei on 2019-11-18
 * <p>
 * Describe:
 */
public class EventDeliveryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.fra_event_delivery, container, false);

        MViewGroup viewGroup = layout.findViewById(R.id.viewgroup);
        viewGroup.setFlag("MViewGroup");

        MView view = ((MView) layout.findViewById(R.id.view));
        view.setOnTouchEventResult(true);
//        viewGroup.setOnInterceptResult(true);


        MView view1 = layout.findViewById(R.id.view1);
        view1.setFlag("MView1");
//        view1.setOnTouchEventResult(true);

        MView view2 = layout.findViewById(R.id.view2);
        view2.setFlag("MView2");

        return layout;
    }


}
