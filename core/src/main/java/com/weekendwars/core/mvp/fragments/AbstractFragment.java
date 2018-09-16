package com.weekendwars.core.mvp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.core.mvp.views.AbstractView;

public abstract class AbstractFragment<V extends AbstractView, P extends AbstractPresenter<V>>
        extends android.support.v4.app.Fragment implements AbstractView {

    private static final String STATE_KEY = "state-key" + AbstractFragment.class.getCanonicalName();
    private P mPresenter;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.attachView(getMvpView());
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(STATE_KEY, mPresenter.getState());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_KEY)) {
            mPresenter.restoreState(savedInstanceState.getBundle(STATE_KEY));
        }
    }

    protected abstract V getMvpView();

    protected abstract P createPresenter();

    protected abstract int getLayoutResourceId();

    protected final P getPresenter() {
        return mPresenter;
    }
}
