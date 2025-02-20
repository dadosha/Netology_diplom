package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class LoginPage {

    private static final int LOGIN_FIELD_HINT = R.string.login;
    private static final int PASSWORD_FIELD_HINT = R.string.password;
    private static final int LOGIN_BUTTON_ID = R.id.enter_button;
    private static final int EMPTY_LOGIN_DATA_TEXT = R.string.empty_login_or_password;
    private static final int INCORRECT_LOGIN_DATA_TEXT = R.string.wrong_login_or_password;
    private static final int ERROR_TEXT = R.string.error;

    public void login(String login, String password) {
        onView(withHint(LOGIN_FIELD_HINT))
                .check(matches(isDisplayed()))
                .perform(typeText(login), closeSoftKeyboard());
        onView(withHint(PASSWORD_FIELD_HINT))
                .check(matches(isDisplayed()))
                .perform(typeText(password), closeSoftKeyboard());
        onView(withId(LOGIN_BUTTON_ID))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public int getEmptyLoginDataText() {
        return EMPTY_LOGIN_DATA_TEXT;
    }

    public int getIncorrectLoginDataText() {
        return INCORRECT_LOGIN_DATA_TEXT;
    }

    public int getErrorText() {
        return ERROR_TEXT;
    }
}