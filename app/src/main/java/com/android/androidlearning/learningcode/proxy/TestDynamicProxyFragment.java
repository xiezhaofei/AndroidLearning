package com.android.androidlearning.learningcode.proxy;

import android.view.View;
import android.widget.AbsListView;

import com.android.androidlearning.learningcode.fragment.BaseFragment2;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by xiezhaofei on 2020-03-15
 * <p>
 * Describe:
 */
public class TestDynamicProxyFragment extends BaseFragment2 {

    @Override
    protected void initViews() {
        super.initViews();
        addButton("test", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sell sell = new SellImpl();

                DynamicProxy dynamicProxy = new DynamicProxy(sell);

                Sell seeProxy = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicProxy));

                seeProxy.sell();

                try {
                    Field mRecyclerField = AbsListView.class.getField("mRecycler");


                    mRecyclerField.setAccessible(true);

                    trace(mRecyclerField.toGenericString());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
