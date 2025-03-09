package ru.iteco.fmhandroid.pages;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;

import static ru.iteco.fmhandroid.Utils.getSystemPositiveButtonText;
import static ru.iteco.fmhandroid.matchers.FirstMatcher.first;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class EditNewsPage {

    private static final int EDIT_NEWS_TITLE = R.string.news_control_panel;
    private static final int ADD_NEWS_BUTTON = R.id.add_news_image_view;
    private static final int NEWS_TITLE_CONTROL_PANEL = R.id.news_item_title_text_view;
    private static final int CREATE_DATE_NEWS = R.id.news_item_create_date_text_view;
    private static final int PUBLISHED_DATE_NEWS = R.id.news_item_publication_date_text_view;
    private static final int EXPAND_LIST_NEWS = R.id.view_news_item_image_view;
    private static final int DESCRIPTION_NEWS = R.id.news_item_description_text_view;
    private static final int STATUS_NEWS = R.id.news_item_published_text_view;
    private static final int ACTIVE_NEWS = R.string.news_control_panel_active;
    private static final int NO_ACTIVE_NEWS = R.string.news_control_panel_not_active;
    private static final int CARD_NEWS = R.id.news_item_material_card_view;
    private static final int DELETE_NEWS_BUTTON = R.id.delete_news_item_image_view;
    private static final int NEWS_LIST_UPDATE = R.id.news_control_panel_swipe_to_refresh;
    private static final int FILTER_BUTTON = R.id.filter_news_material_button;
    private static final int LIST_NEWS = R.id.news_list_recycler_view;
    private static final int EDIT_NEWS_BUTTON = R.id.edit_news_item_image_view;

    public void seeEditNewsTitle() {
        Allure.step("Проверяем наличие заголовка страницы панели управления новостями");
        onView(withText(EDIT_NEWS_TITLE))
                .check(matches(isDisplayed()));
    }

    public void openAddNewsPage() {
        Allure.step("Открываем страницу создания новостей");
        onView(withId(ADD_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void findNewsInControlPage(String titleText) {
        Allure.step("Находим новость по заголовку - " + titleText);
        onView(withId(LIST_NEWS))
                .perform(scrollTo(hasDescendant(withText(titleText))))
                .check(matches(isDisplayed()));
    }

    public void notFindTitleInControlPage(String titleText) {
        Allure.step("Проверяем отсутствие новости по заголовку - " + titleText);
        onView(withId(LIST_NEWS))
                .check(matches(not(hasDescendant(withText(titleText)))));
    }

    public void checkCreateDate(String titleText, String createDate) {
        Allure.step("Проверяем дату создания новости");
        onView(first(allOf(
                withId(CREATE_DATE_NEWS),
                hasSibling(withText(titleText))
        ))).check(matches(withText(createDate)));
    }

    public void checkPublishedDate(String titleText, String createDate) {
        Allure.step("Проверяем дату публикации новости");
        onView(allOf(
                withId(PUBLISHED_DATE_NEWS),
                isDescendantOfA(
                        allOf(
                                withId(CARD_NEWS),
                                hasDescendant(withText(titleText))
                        )
                )
        )).check(matches(withText(createDate)));
    }

    public void checkPublishedDescription(String titleText, String newsDescription) {
        Allure.step("Проверяем описание новости");
        onView(allOf(
                withId(EXPAND_LIST_NEWS),
                isDescendantOfA(
                        allOf(
                                withId(CARD_NEWS),
                                hasDescendant(withText(titleText))
                        )
                )
        )).perform(click());

        onView(allOf(
                withId(DESCRIPTION_NEWS),
                isDescendantOfA(
                        allOf(
                                withId(CARD_NEWS),
                                hasDescendant(withText(titleText))
                        )
                ),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
        )).check(matches(withText(newsDescription)));
    }

    public void checkActiveStatusNews(String titleText) {
        Allure.step("Проверяем активность новости");
        onView(first(allOf(
                withId(STATUS_NEWS),
                isDescendantOfA(allOf(
                        withId(CARD_NEWS),
                        hasDescendant(withText(titleText))
                ))
        ))).check(matches(withText(ACTIVE_NEWS)));
    }

    public void checkNotActiveStatusNews(String titleText) {
        Allure.step("Проверяем неактивности новости");
        onView(first(allOf(
                withId(STATUS_NEWS),
                isDescendantOfA(allOf(
                        withId(CARD_NEWS),
                        hasDescendant(withText(titleText))
                ))
        ))).check(matches(withText(NO_ACTIVE_NEWS)));
    }

    public void deleteNews(String titleText) {
        Allure.step("Удаляем новость с заголовком - " + titleText);
        onView(allOf(
                withId(DELETE_NEWS_BUTTON),
                isDescendantOfA(
                        allOf(
                                withId(CARD_NEWS),
                                hasDescendant(withText(titleText))
                        )
                )
        )).check(matches(isDisplayed()))
                .perform(click());

        onView(withText(getSystemPositiveButtonText()))
                .perform(click());
    }

    public void editNews(String titleText) {
        findNewsInControlPage(titleText);
        Allure.step("Открываем страницу редактирования новости для - " + titleText);
        onView(allOf(
                withId(EDIT_NEWS_BUTTON),
                isDescendantOfA(
                        allOf(
                                withId(CARD_NEWS),
                                hasDescendant(withText(titleText))
                        )
                )
        )).check(matches(isDisplayed()))
                .perform(click());
    }

    public void updateNewsList() {
        Allure.step("Обновляем список новостей");
        onView(withId(NEWS_LIST_UPDATE)).perform(swipeDown());
    }

    public void openFilterPage() {
        Allure.step("Открываем страницу фильтров для созданных новостей");
        onView(withId(FILTER_BUTTON)).perform(click());
    }
}
