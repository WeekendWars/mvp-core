package core.mvp.sampleapp.views;

import core.mvp.views.AbstractView;

public interface TestView extends AbstractView {
    void displayRestoredState(long savedState);
}
