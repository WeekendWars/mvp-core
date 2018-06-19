package core.mvp.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import core.mvp.views.AbstractView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbstractPresenter<V extends AbstractView> {

    private CompositeDisposable mDisposable;
    private V mView;

    public void attachView(@NonNull final V view) {
        mDisposable = new CompositeDisposable();
        mView = view;
    }

    @Nullable
    protected V getView() {
        return mView;
    }

    public void detachView() {
        if (hasSubscriptions()) {
            mDisposable.dispose();
            mDisposable.clear();
        }
    }

    @VisibleForTesting
    public boolean hasSubscriptions() {
        return mDisposable.size() > 0;
    }

    public abstract void restoreState(@NonNull Bundle savedState);

    @VisibleForTesting
    public void addDisposable(@NonNull final Disposable disposable) {
        mDisposable.add(disposable);
    }

    @VisibleForTesting
    public CompositeDisposable getDisposable() {
        return mDisposable;
    }

    @NonNull
    public abstract Bundle getState();
}
