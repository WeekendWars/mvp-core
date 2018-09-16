package com.weekendwars.core.mvp.sampleapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weekendwars.core.mvp.sampleapp.R;

public class FragmentTestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_mvp_sampleapp_activity_fragment_test);
    }
}
