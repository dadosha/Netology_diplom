package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import ru.iteco.fmhandroid.matchers.ToastMatcher;

public class Utils {
    public static void checkToastMessage(int message) {
        onView(withText(message)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
}
