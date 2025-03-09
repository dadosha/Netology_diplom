package ru.iteco.fmhandroid.matchers;

import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import org.hamcrest.BaseMatcher;

public class FirstMatcher {
    public static Matcher<View> first(final Matcher<View> matcher) {
        return new BaseMatcher<View>() {
            private boolean isFirst = true;

            @Override
            public boolean matches(Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Should return first matching item");
            }
        };
    }
}