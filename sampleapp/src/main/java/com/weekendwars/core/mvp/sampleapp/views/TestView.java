package com.weekendwars.core.mvp.sampleapp.views;

import com.weekendwars.core.mvp.views.AbstractView;

public interface TestView extends AbstractView {
    void displayRestoredState(long savedState);
}
