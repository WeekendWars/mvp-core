package com.weekendwars.core.mvp.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.weekendwars.core.mvp.views.AbstractView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbstractPresenter<V extends AbstractView> {

    private CompositeDisposable mDisposable;
    private V mView;

    /**
     * Called on activity's lifecycle onResume(). It means the activity's displaying and there's
     * a view attached to it.
     *
     * @param view the view being attached
     */
    public void attachView(@NonNull final V view) {
        mDisposable = new CompositeDisposable();
        mView = view;
    }

    @Nullable
    protected V getView() {
        return mView;
    }

    /**
     * Called when the activty's been paused or destroyed. It means the activity's not displaying
     * anymore and there's no view attached.
     * <p>
     * We unsubscribe any subscription for avoiding the view being called when it's not visible
     */
    public void detachView() {
        if (hasSubscriptions()) {
            mDisposable.dispose();
            mDisposable.clear();
        }
    }

    /**
     * @return whether the presenter's queued subscriptions or not
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public boolean hasSubscriptions() {
        return mDisposable.size() > 0;
    }

    /**
     * Called when the activity's restoring it's state. We should restore presenter's state here
     *
     * @param savedState the previously saved state
     */
    public abstract void restoreState(@NonNull Bundle savedState);

    /**
     * Adds a {@link Disposable disposable} to the {@link CompositeDisposable compositeDisposable}
     * for being queued and executed
     *
     * @param disposable the disposable being queued
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public void addDisposable(@NonNull final Disposable disposable) {
        mDisposable.add(disposable);
    }

    /**
     * @return the compositeDisposable
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public CompositeDisposable getDisposable() {
        return mDisposable;
    }

    /**
     * Used on the activity's saveInstanceState() for saving the presenter's state. We should
     * save here any presenter's state we want to keep after Activity's destruction.
     *
     * @return the bundle containing the presenter's state
     */
    @NonNull
    public abstract Bundle getState();
}
