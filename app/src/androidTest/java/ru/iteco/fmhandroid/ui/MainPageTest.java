package ru.iteco.fmhandroid.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;


@RunWith(AndroidJUnit4.class)
@DisplayName("Тесты главной страницы приложения")
public class MainPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private MainPage mainPage;
    private NewsPage newsPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        mainPage = new MainPage();
        newsPage = new NewsPage();
        TopCustomBar topCustomBar = new TopCustomBar();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Переход на страницу новостей через кнопку 'Все новости'")
    @Description("Проверяет, что после нажатия на кнопку 'Все новости' на главной странице, происходит переход на страницу с новостями.")
    public void openNewsClickAllNewsTest() {
        mainPage.clickAllNews();
        newsPage.seeNewsTitle();
    }

    @Test
    @DisplayName("Разворачивание и скрытие списка новостей")
    @Description("Проверяет, что список новостей можно развернуть и свернуть, и что состояние корректно меняется.")
    public void expandAndHideNewsList() {
        mainPage.clickExpandButton();
        mainPage.noSeeAllNews();
        mainPage.clickExpandButton();
        mainPage.seeAllNews();
    }
}
