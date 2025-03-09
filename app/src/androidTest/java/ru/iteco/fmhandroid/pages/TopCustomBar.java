package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class TopCustomBar {
    private static final int PEOPLE_BUTTON = R.id.authorization_image_button;
    private static final int LIST_PAGE_BUTTON = R.id.main_menu_image_button;
    private static final int MAIN_LOGO = R.id.trademark_image_view;
    private static final int LOGOUT_BUTTON = R.string.log_out;
    private static final int MAIN_BUTTON = R.string.main;
    private static final int NEWS_BUTTON = R.string.news;
    private static final int ABOUT_BUTTON = R.string.about;
    private static final int OUR_MISSION_BUTTON = R.id.our_mission_image_button;
    private static final int BACK_BUTTON = R.id.about_back_image_button;

    public void exit() {
        Allure.step("Открываем выпадающий список");
        onView(withId(PEOPLE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
        Allure.step("Нажимаем кнопку выхода из системы");
        onView(withText(LOGOUT_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openMainPage() {
        Allure.step("Открываем выпадающий список");
        onView(withId(LIST_PAGE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
        Allure.step("Нажимаем кнопку открытия главной страницы");
        onView(withText(MAIN_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Step("Открытие страницы новостей")
    public void openNewsPage() {
        Allure.step("Открываем выпадающий список");
        onView(withId(LIST_PAGE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
        Allure.step("Нажимаем кнопку открытия страницы всех новостей");
        onView(withText(NEWS_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openAboutPage() {
        Allure.step("Открываем выпадающий список");
        onView(withId(LIST_PAGE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
        Allure.step("Нажимаем кнопку открытия страницы о приложении");
        onView(withText(ABOUT_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openOurMissionPage() {
        Allure.step("Открытие страницы миссии приложения");
        onView(withId(OUR_MISSION_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickBackButton() {
        Allure.step("Возврат на страницу назад со страницы 'О приложении'");
        onView(withId(BACK_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void seeMainLogo() {
        Allure.step("Проверка наличия лого приложения");
        onView(withId(MAIN_LOGO))
                .check(matches(isDisplayed()));
    }

    public boolean isLoginNow() {
        try {
            Allure.step("Проверяем залогинены ли сейчас");
            onView(withId(MAIN_LOGO)).check(matches(isDisplayed()));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}
