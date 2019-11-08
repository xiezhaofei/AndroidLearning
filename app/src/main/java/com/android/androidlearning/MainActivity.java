package com.android.androidlearning;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fView(R.id.btn_ipc);
        fView(R.id.btn_contentprovider);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ipc) {

        } else if (id == R.id.btn_contentprovider) {

        }
    }

    private void fView(int id) {
        findViewById(id).setOnClickListener(this);
    }
}
