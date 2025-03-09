package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class LoginPage {

    private static final int LOGIN_FIELD_HINT = R.string.login;
    private static final int PASSWORD_FIELD_HINT = R.string.password;
    private static final int LOGIN_BUTTON_ID = R.id.enter_button;
    private static final int EMPTY_LOGIN_DATA_TEXT = R.string.empty_login_or_password;
    private static final int INCORRECT_LOGIN_DATA_TEXT = R.string.wrong_login_or_password;
    private static final int ERROR_TEXT = R.string.error;
    private static final int AUTHORIZATION = R.string.authorization;

    public void seeAuthorizationTitle() {
        Allure.step("Проверяем наличие заголовка входа");
        onView(withText(AUTHORIZATION))
                .check(matches(isDisplayed()));
    }

    public void inputLogin(String login) {
        Allure.step("Заполняем поле логина" + login);
        onView(withHint(LOGIN_FIELD_HINT))
                .check(matches(isDisplayed()))
                .perform(typeText(login), closeSoftKeyboard());
    }

    public void inputPassword(String password) {
        Allure.step("Заполняем поле пароля");
        onView(withHint(PASSWORD_FIELD_HINT))
                .check(matches(isDisplayed()))
                .perform(typeText(password), closeSoftKeyboard());
    }

    public void clickLogin() {
        Allure.step("Нажимаем кнопку логина");
        onView(withId(LOGIN_BUTTON_ID))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void login(String login, String password) {
        Allure.step("Логинемся в профиле - " + login);
        inputLogin(login);
        inputPassword(password);
        clickLogin();
    }

    public int getEmptyLoginDataText() {
        Allure.step("Получаем ссылку на текст с ошибкой при пустых данных авторизации");
        return EMPTY_LOGIN_DATA_TEXT;
    }

    public int getIncorrectLoginDataText() {
        Allure.step("Получаем ссылку на текст с ошибкой при неверных данных");
        return INCORRECT_LOGIN_DATA_TEXT;
    }

    public int getErrorText() {
        Allure.step("Получаем ссылку на текст с ошибкой при проблемах с сетью");
        return ERROR_TEXT;
    }
}