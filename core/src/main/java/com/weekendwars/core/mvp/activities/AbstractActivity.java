package com.weekendwars.core.mvp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.core.mvp.views.AbstractView;

public abstract class AbstractActivity<V extends AbstractView, P extends AbstractPresenter<V>>
        extends AppCompatActivity implements AbstractView {

    private static final String STATE_KEY = "state-key";
    private P mPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        mPresenter = createPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(getMvpView());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    protected abstract V getMvpView();

    protected abstract P createPresenter();

    protected abstract int getLayoutResourceId();

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(STATE_KEY, mPresenter.getState());
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPresenter.restoreState(savedInstanceState.getBundle(STATE_KEY));
    }
}
