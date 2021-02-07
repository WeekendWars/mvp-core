package com.weekendwars.core.mvp.validators;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import com.weekendwars.core.mvp.sampleapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class TestActivityValidator implements Validator {

    private final ActivityTestRule mActivityRule;

    public TestActivityValidator(@NonNull final ActivityTestRule rule) {
        this.mActivityRule = rule;
    }

    @Override
    public void validate() {
        onView(withId(R.id.button)).inRoot(withDecorView(not(is(mActivityRule
                .getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}
