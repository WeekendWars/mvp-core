package com.weekendwars.core.mvp.pages;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import com.weekendwars.core.mvp.validators.FragmentTestActivityValidator;

/* default */ class FragmentTestActivityPageObject extends PageObject {

    /* default */ FragmentTestActivityPageObject(@NonNull final ActivityTestRule rule) {
        super(new FragmentTestActivityValidator(rule));
    }
}
