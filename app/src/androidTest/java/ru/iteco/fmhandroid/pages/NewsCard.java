package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.recyclerview.widget.RecyclerView;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.matchers.RecyclerViewMatcher;

public class NewsCard {
    private static final int NEWS_LIST = R.id.news_list_recycler_view;
    private static final int NEWS_TITLE = R.id.news_item_title_text_view;
    private static final int NEWS_IMAGE = R.id.category_icon_image_view;
    private static final int NEWS_DATE = R.id.news_item_date_text_view;
    private static final int NEWS_EXPAND_LIST_BUTTON = R.id.view_news_item_image_view;
    private static final int NEWS_DESCRIPTION = R.id.news_item_description_text_view;

    public boolean checkNewsListIsDisplayed() {
        Allure.step("Проверяем наличие отображаемых новостей");
        try {
            onView(withId(NEWS_LIST)).check(matches(isDisplayed()));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public int getRecyclerViewSize() {
        final int[] itemCount = {0};

        onView(withId(NEWS_LIST)).check((view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            itemCount[0] = recyclerView.getAdapter().getItemCount();
        });

        return itemCount[0];
    }

    public String getNewsTitle(int position) {
        Allure.step("Проверяем текст заголовока новости");
        final String[] newsTitle = new String[1];

        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(position, NEWS_TITLE))
                .check((view, noViewFoundException) -> {
                    newsTitle[0] = ((android.widget.TextView) view).getText().toString();
                });

        return newsTitle[0];
    }

    public void newsHasTitle(int randomPosition) {
        Allure.step("Проверяем наличие заголовка у новости");
        onView(withId(NEWS_LIST)).perform(scrollToPosition(randomPosition));

        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(randomPosition, NEWS_TITLE))
                .check(matches(allOf(isDisplayed(), withText(not("")))));
    }

    public void newsHasImage(int randomPosition) {
        Allure.step("Проверяем наличие картинки у новости");
        onView(withId(NEWS_LIST)).perform(scrollToPosition(randomPosition));

        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(randomPosition, NEWS_IMAGE))
                .check(matches(isDisplayed()));
    }

    public void newsHasDate(int randomPosition) {
        Allure.step("Проверяем наличие даты у новости");
        onView(withId(NEWS_LIST)).perform(scrollToPosition(randomPosition));

        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(randomPosition, NEWS_DATE))
                .check(matches(allOf(isDisplayed(), withText(not("")))));
    }

    public void newsHasDescription(int randomPosition) {
        onView(withId(NEWS_LIST)).perform(scrollToPosition(randomPosition));

        Allure.step("Раскрываем описание новости");
        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(randomPosition, NEWS_EXPAND_LIST_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());

        Allure.step("Проверяем наличие описания у новости");
        onView(RecyclerViewMatcher.withRecyclerView(NEWS_LIST)
                .atPositionOnView(randomPosition, NEWS_DESCRIPTION))
                .check(matches(allOf(isDisplayed(), withText(not("")))));
    }
}
