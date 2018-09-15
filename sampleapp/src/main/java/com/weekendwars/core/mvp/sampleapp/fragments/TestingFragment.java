package com.weekendwars.core.mvp.sampleapp.fragments;

import android.widget.Toast;

import com.weekendwars.core.mvp.fragments.AbstractFragment;
import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.presenters.TestPresenter;
import com.weekendwars.core.mvp.sampleapp.views.TestView;

public class TestingFragment extends AbstractFragment<TestView, TestPresenter>
        implements TestView {

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
        return R.layout.core_mvp_sampleapp_fragment_test;
    }

    @Override
    public void displayRestoredState(final long savedState) {
        Toast.makeText(getContext(), String.valueOf(savedState), Toast.LENGTH_SHORT).show();
    }
}
