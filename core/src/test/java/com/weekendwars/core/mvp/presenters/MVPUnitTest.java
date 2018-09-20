package com.weekendwars.core.mvp.presenters;

import com.weekendwars.core.mvp.activities.AbstractActivity;

import org.junit.Test;
import org.mockito.Mockito;

import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.ConsumerSingleObserver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MVPUnitTest {
    @SuppressWarnings({"unchecked", "ConstantConditions", "CPD-START"})
    @Test
    public void testActivityLifeCycle() {
        final AbstractPresenter presenter = Mockito.spy(AbstractPresenter.class);
        assertNull("Disposable should be null", presenter.getDisposable());

        presenter.attachView(Mockito.spy(AbstractActivity.class));
        assertNotNull("Disposable shouldn't be null", presenter.getDisposable());

        presenter.addDisposable(new ConsumerSingleObserver<>(new Consumer<Object>() {
            @Override
            public void accept(final Object o) {
                // Nothing to do
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(final Throwable throwable) {
                // Nothing to do
            }
        }));

        assertTrue("Presenter should have subscription", presenter.hasSubscriptions());

        presenter.detachView();
        assertFalse("Presenter should not have subscription", presenter.hasSubscriptions());
    }

    @SuppressWarnings({"unchecked", "ConstantConditions", "CPD-END"})
    @Test
    public void testFragmentLifeCycle() {
        final AbstractPresenter presenter = Mockito.spy(AbstractPresenter.class);
        assertNull("Disposable should be null", presenter.getDisposable());

        presenter.attachView(Mockito.spy(AbstractActivity.class));
        assertNotNull("Disposable shouldn't be null", presenter.getDisposable());

        presenter.addDisposable(new ConsumerSingleObserver<>(new Consumer<Object>() {
            @Override
            public void accept(final Object o) {
                // Nothing to do
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(final Throwable throwable) {
                // Nothing to do
            }
        }));

        assertTrue("Presenter should have subscription", presenter.hasSubscriptions());

        presenter.detachView();
        assertFalse("Presenter should not have subscription", presenter.hasSubscriptions());
    }
}