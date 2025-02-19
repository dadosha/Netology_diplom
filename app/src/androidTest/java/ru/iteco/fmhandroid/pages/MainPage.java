package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class MainPage {

    private static final int PEOPLE_BUTTON = R.id.authorization_image_button;
    private static final int MAIN_LOGO = R.id.trademark_image_view;
    private static final int LOGOUT_BUTTON = R.string.log_out;

    public void exit() {
        onView(withId(PEOPLE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText(LOGOUT_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void seeMainLogo() {
        onView(withId(MAIN_LOGO))
                .check(matches(isDisplayed()));
    }
}
