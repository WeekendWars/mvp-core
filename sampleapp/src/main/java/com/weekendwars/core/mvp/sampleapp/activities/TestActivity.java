package com.weekendwars.core.mvp.sampleapp.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.presenters.TestPresenter;
import com.weekendwars.core.mvp.sampleapp.views.TestView;


public class TestActivity extends AbstractActivity<TestView, TestPresenter> implements TestView {

    @Override
    protected TestView getMvpView() {
        return this;
    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.core_mvp_sampleapp_activity_test;
    }

    @Override
    public void displayRestoredState(final long savedState) {
        Toast.makeText(this, "Saved state: " + savedState, Toast.LENGTH_LONG).show();
    }

    public void goToFragmentsActivity(@NonNull final View view) {
        startActivity(new Intent(this, FragmentTestActivity.class));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ObjectAnimator animator = ObjectAnimator.ofInt(findViewById(R.id.arc_progress),
                "progress", 0, 35);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
        animator.start();
    }
}
