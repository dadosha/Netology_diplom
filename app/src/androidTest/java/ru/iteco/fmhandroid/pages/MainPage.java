package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class MainPage {
    private static final int ALL_NEWS_BUTTON = R.id.all_news_text_view;

    public void clickAllNews() {
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void seeAllNews() {
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()));
    }
}
