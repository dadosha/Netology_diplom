package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    private static final int NEWS_TITLE_TEXT = R.string.news;


    public void seeNewsTitle() {
        onView(withText(NEWS_TITLE_TEXT))
                .check(matches(isDisplayed()));
    }
}
