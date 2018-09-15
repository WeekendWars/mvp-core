package com.weekendwars.core.mvp.sampleapp.presenters;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.core.mvp.sampleapp.views.TestView;

public class TestPresenter extends AbstractPresenter<TestView> {

    private static final String STATE = "state";
    private long mState;

    @Override
    public void attachView(@NonNull final TestView view) {
        super.attachView(view);
        view.displayRestoredState(mState);
    }

    @Override
    public void restoreState(@NonNull final Bundle savedState) {
        mState = savedState.getLong(STATE);
    }

    @NonNull
    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putLong(STATE, SystemClock.currentThreadTimeMillis());

        return state;
    }
}
