package com.android.androidlearning.learningcode.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by xiezhaofei on 2020-01-14
 * <p>
 * Describe:
 */
public class BitmapRecycleFragment extends BaseFragment2 {

    private Activity mActivity;

    @Override
    protected void initViews() {
        super.initViews();
        addButton("创建bitmap", new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        for (int i = 0; i < 1000; i++) {
//                            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aa/test.png");
////                            bitmap.recycle();
//                            BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aa/test.png");
//                            BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aa/test.png");
//                            bitmap = null;
////                            Runtime.getRuntime().gc();
//
//                        }
                        for (int i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            trace("+++++++++++:" + i);
                        }
                        if (mActivity == null) {
                            trace("================");
                        } else {
                            trace("----------------");
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mActivity, "弹出toast", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();


            }
        });
    }

    @Override
    protected void back() {
        super.back();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        trace("onDetach " + (getContext() == null));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        trace("onAttach " + (getContext() == null));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trace("onDestroy " + (getContext() == null));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        trace("onDestroy " + (getContext() == null));
    }

}
