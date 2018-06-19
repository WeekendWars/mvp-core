package core.mvp.weekendwars.android.com;

import org.junit.Test;
import org.mockito.Mockito;

import core.mvp.activities.AbstractActivity;
import core.mvp.presenters.AbstractPresenter;
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

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    public void testLifeCycle() {
        final AbstractPresenter presenter = Mockito.spy(AbstractPresenter.class);
        assertNull(presenter.getDisposable());

        presenter.attachView(Mockito.spy(AbstractActivity.class));
        assertNotNull(presenter.getDisposable());

        presenter.addDisposable(new ConsumerSingleObserver<>(new Consumer<Object>() {
            @Override
            public void accept(final Object o) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(final Throwable throwable) throws Exception {

            }
        }));

        assertTrue(presenter.hasSubscriptions());

        presenter.detachView();
        assertFalse(presenter.hasSubscriptions());
    }
}