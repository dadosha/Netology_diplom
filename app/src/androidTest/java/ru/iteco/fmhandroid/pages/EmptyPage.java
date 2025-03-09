package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class EmptyPage {
    private static final int NOTHING_TEXT = R.string.empty_claim_list_text;
    private static final int REFRESH_CONTROL_PANEL_BUTTON = R.id.control_panel_news_retry_material_button;
    private static final int REFRESH_NEWS_BUTTON = R.id.news_retry_material_button;


    public void seeFilterTitle() {
        Allure.step("Проверяем наличие текста об отсутствие новостей");
        onView(withText(NOTHING_TEXT))
                .check(matches(isDisplayed()));
    }

    public void refreshControlPanelButton() {
        Allure.step("Нажимаем кнопку перезагрузки новостей ");
        onView(withId(REFRESH_CONTROL_PANEL_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void refreshNewsButton() {
        Allure.step("Нажимаем кнопку перезагрузки новостей");
        onView(withId(REFRESH_NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
