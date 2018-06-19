package core.mvp.weekendwars.android.com.sampleapp.presenters;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import core.mvp.weekendwars.android.com.presenters.AbstractPresenter;
import core.mvp.weekendwars.android.com.sampleapp.views.TestView;

public class TestPresenter extends AbstractPresenter<TestView> {

    private long mState;

    @Override
    public void attachView(@NonNull final TestView view) {
        super.attachView(view);
        view.displayRestoredState(mState);
    }

    @Override
    public void restoreState(@NonNull final Bundle savedState) {
        mState = savedState.getLong("state");
    }

    @NonNull
    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putLong("state", SystemClock.currentThreadTimeMillis());

        return state;
    }
}