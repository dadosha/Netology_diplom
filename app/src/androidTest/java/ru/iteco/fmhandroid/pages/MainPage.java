package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    private static final int ALL_NEWS_BUTTON = R.id.all_news_text_view;
    private static final int EXPAND_BUTTON = R.id.expand_material_button;

    public void clickAllNews() {
        Allure.step("Кликаем на страницу всех новостей");
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickExpandButton() {
        Allure.step("Кликаем на кнопку раскрытия/скрытия списка новостей не главной");
        onView((withId(EXPAND_BUTTON)))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void seeAllNews() {
        Allure.step("Проверяем наличие кнопки всех новостей");
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(isDisplayed()));
    }

    public void noSeeAllNews() {
        Allure.step("Проверяем отсутствие кнопки всех новостей");
        onView(withId(ALL_NEWS_BUTTON))
                .check(matches(not(isDisplayed())));
    }
}
