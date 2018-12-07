package com.weekendwars.core.mvp.sampleapp.activities;

import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.presenters.TestPresenter;
import com.weekendwars.core.mvp.sampleapp.views.TestView;

public class TestActivity2 extends AbstractActivity<TestView, TestPresenter> implements TestView {

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
        return R.layout.core_mvp_sampleapp_activity_test_two;
    }

    @Override
    public void displayRestoredState(long savedState) {

    }
}
