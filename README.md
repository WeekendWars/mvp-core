# mvp-core

This is a simple and small library for setting up a basic MVP structure


## Features
* Basic MVP structure for `Activity` and `Fragment`
* State saving/restoring for `Presenter`

## Library information
* Total methods in core-release.aar: 58 (0,09% used)
* Total fields in core-release.aar:  6 (0,01% used)
* Total classes in core-release.aar:  11 (0,02% used)
* Methods remaining in core-release.aar: 65477
* Fields remaining in core-release.aar:  65529
* Classes remaining in core-release.aar:  65524

## View's attachment

Your `Activity` will notify the `Presenter` when it's been attached so the `Presenter` knows when to start doing whatever it
needs to do. It will receive a reference to the `Activity`'s view on the `attachView(V)` method like showed below:

```
    @Override
    public void attachView(@NonNull final TestView view) {
        super.attachView(view);
        view.displayRestoredState(mState);
    }
```

The `Presenter` will save the view's reference which you can access whenever you want by calling `getView()` **You must not call
this method before the view's attachment, like on the presenter's constructor**. It will also create a new instance of the `CompositeDisposable`.


## State restoring

If your `Activity` has been destroyed it will call it's `onRestoreInstanceState()` and notify the `Presenter` there's a state
needed to be restored.

You should save your `Presenter`'s state by overriding the `getBundle()` method and saving it's current state like showed below:

```
    @NonNull
    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putLong(STATE, SystemClock.currentThreadTimeMillis());

        return state;
    }
```


## Implementation
* Your `Activity` should extend `AbstractActivity<T, V>` and implement it's view `T` like showed below:

```
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
}
```

* Your presenter should extend `AbstractPresenter<V>` like showed below:
```
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
```
