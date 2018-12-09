package com.weekendwars.core.mvp.pages;

import android.support.annotation.NonNull;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.validators.TestActivityValidator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class TestActivityPageObject extends PageObject {

    private final ActivityTestRule mRule;

    public TestActivityPageObject(@NonNull final ActivityTestRule rule) {
        super(new TestActivityValidator(rule));
        mRule = rule;
    }

    public FragmentTestActivityPageObject touchButton() {
        onView(withId(R.id.button)).perform(ViewActions.click());

        return new FragmentTestActivityPageObject(mRule);
    }
}

