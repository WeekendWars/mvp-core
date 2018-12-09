package com.weekendwars.core.mvp.flows;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.weekendwars.core.mvp.pages.TestActivityPageObject;
import com.weekendwars.core.mvp.sampleapp.activities.TestActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SuppressWarnings("PMD.JUnitTestsShouldIncludeAssert")
public class TestActivityFlows {

    @Rule
    public final ActivityTestRule<TestActivity> mActivityRule =
            new ActivityTestRule<>(TestActivity.class);

    @Test
    public void testFlow() {
        new TestActivityPageObject(mActivityRule).touchButton();
    }
}
