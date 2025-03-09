package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPages {
    private static final int PRIVACY_POLICY_LINK = R.id.about_privacy_policy_value_text_view;
    private static final int TERMS_OF_USE_LINK = R.id.about_terms_of_use_value_text_view;
    private static final int PRIVACY_POLICY_VALUE_LINK = R.string.privacy_policy_url;
    private static final int TERMS_OF_USE_VALUE_LINK = R.string.terms_of_use_url;

    public void seePrivacyPolicy() {
        Allure.step("Проверяем отображение ссылки 'Политика конфиденциальности'");
        onView(withId(PRIVACY_POLICY_LINK))
                .check(matches(isDisplayed()));
    }

    public void clickPrivacyPolicyLink() {
        Allure.step("Кликаем по ссылке 'Политика конфиденциальности'");
        onView(withId(PRIVACY_POLICY_LINK))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickTermsOfUse() {
        Allure.step("Кликаем по ссылке 'Условия использования'");
        onView(withId(TERMS_OF_USE_LINK))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public String getPrivacyPolicyValueLink() {
        Allure.step("Получаем URL политики конфиденциальности");
        Context context = ApplicationProvider.getApplicationContext();
        return context.getString(PRIVACY_POLICY_VALUE_LINK);
    }

    public String getTermsOfUseValueLink() {
        Allure.step("Получаем URL условий использования");
        Context context = ApplicationProvider.getApplicationContext();
        return context.getString(TERMS_OF_USE_VALUE_LINK);
    }
}
