package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {

    private static final int NEWS_TITLE_TEXT = R.string.news;
    private static final int FILTER_BUTTON = R.id.filter_news_material_button;
    private static final int SORT_BUTTON = R.id.sort_news_material_button;
    private static final int EDIT_NEWS_BUTTON = R.id.edit_news_material_button;
    private static final int NEWS_LIST_UPDATE = R.id.news_list_swipe_refresh;


    public void seeNewsTitle() {
        Allure.step("Проверяем наличие заголовка страницы новостей");
        onView(withText(NEWS_TITLE_TEXT))
                .check(matches(isDisplayed()));
    }

    public void openFilterPage() {
        Allure.step("Открытие страницы фильтров для новостей");
        onView(withId(FILTER_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickSortPage() {
        Allure.step("Нажимаем кнопку сортировки новостей");
        onView(withId(SORT_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openEditNewsPage() {
        Allure.step("Открываем страницу управления новостями");
        onView(withId(EDIT_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void updateNewsList() {
        Allure.step("Обновление списка новостей");
        onView(withId(NEWS_LIST_UPDATE)).perform(swipeDown());
    }

    public void findNewsInNewsPage(String titleText) {
        Allure.step("Найти новость по заголовку - " + titleText);
        onView(withText(titleText))
                .check(matches(isDisplayed()));
    }
}
