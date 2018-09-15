# mvp-core

This is a simple and small library for setting up a basic MVP structure. It also includes <a href="https://square.github.io/retrofit/">Retrofit</a> and <a href="https://github.com/ReactiveX/RxJava"> RxJava </a>

## Features
* Basic MVP structure for `Activity` and `Fragment`
* State saving/restoring for `Presenter`

## Includes
* com.android.support:appcompat-v7:27.1.1
* com.squareup.retrofit2:retrofit:2.4.0
* com.squareup.retrofit2:adapter-rxjava2:2.4.0

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

```java
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

```java
    @NonNull
    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putLong(STATE, SystemClock.currentThreadTimeMillis());

        return state;
    }
```

## View's detachment

When the `Activity`'s no longer in the foreground notifies the `Presenter` the view's been detached. So it disposes any queued `Disposable` for avoiding listening events when the view's no longer visible.

## Handle Disposables

When the view's been attached the `Presenter` creates a new instance of `CompositeDisposable` which will be disposed on view's detachment. For adding new disposables being handled by view's lifecycle you should call the `AbstractPresenter.addDisposable()` method like showed below:

```java
    addDisposable(YourModel.INSTANCE.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Consumer<YourDTO>() {
                    @Override
                    public void accept(final YourDTO data) throws Exception {
                        getView().render(data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull final Throwable t) {
                        handleError(t);
                    }
                }));
```
## Implementation

Add the dependency to your **build.gradle** like showed below:

`implementation('com.github.weekendwars:core-mvp:1.0.0')`

* Your `Activity` should extend `AbstractActivity<T, V>` and implement it's view `T` like showed below:

```java
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
```java
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
