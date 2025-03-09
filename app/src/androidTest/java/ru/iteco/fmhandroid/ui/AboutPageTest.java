package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AboutPages;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;


@RunWith(AllureAndroidJUnit4.class)
@DisplayName("Тесты старницы 'О приложении'")
public class AboutPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private AboutPages aboutPages;

    @Before
    public void setUp() {
        Allure.step("Подготовка теста");
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        TopCustomBar topCustomBar = new TopCustomBar();
        aboutPages = new AboutPages();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        topCustomBar.openAboutPage();
        Allure.step("Начинаем отслеживание вызовов браузера");
        Intents.init();
    }

    @After
    public void tearDown() {
        Allure.step("Прекращаем отслеживание вызовов браузера");
        Intents.release();
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Проверка открытия политики конфиденциальности")
    @Description("Этот тест проверяет, что при клике на 'Политика конфиденциальности' открывается нужная веб-страница")
    public void clickPrivacyPolicy() {
        aboutPages.clickPrivacyPolicyLink();
        checkOpenURL(aboutPages.getPrivacyPolicyValueLink());
    }

    @Test
    @DisplayName("Проверка открытия условий использования")
    @Description("Этот тест проверяет, что при клике на 'Условия использования' открывается нужная веб-страница")
    public void clickTermsOfUse() {
        aboutPages.clickTermsOfUse();
        checkOpenURL(aboutPages.getTermsOfUseValueLink());
    }

    @Step("Проверяем, что открылся браузер с нужным URL")
    public void checkOpenURL(String url) {
        Allure.step("Проверяем, что открывается ссылка на " + url);
        Intents.intended(hasData(url));
        Allure.step("Проверяем, что отправлен Intent на открытие браузера");
        Intents.intended(hasAction(Intent.ACTION_VIEW));
    }
}
