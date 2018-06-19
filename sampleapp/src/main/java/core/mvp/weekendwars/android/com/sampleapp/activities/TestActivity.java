package core.mvp.weekendwars.android.com.sampleapp.activities;

import android.widget.Toast;

import core.mvp.weekendwars.android.com.activities.AbstractActivity;
import core.mvp.weekendwars.android.com.sampleapp.R;
import core.mvp.weekendwars.android.com.sampleapp.presenters.TestPresenter;
import core.mvp.weekendwars.android.com.sampleapp.views.TestView;

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
        return R.layout.sampleapp_activity_test;
    }

    @Override
    public void displayRestoredState(final long savedState) {
        Toast.makeText(this, "Saved state: " + savedState, Toast.LENGTH_LONG).show();
    }
}
