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
import ru.iteco.fmhandroid.pages.AboutPages;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.OurMissionPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;


@RunWith(AndroidJUnit4.class)
@DisplayName("Тесты верхнего меню приложения")
public class NavigationAndTopBarTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private TopCustomBar topCustomBar;
    private NewsPage newsPage;
    private AboutPages aboutPages;
    private MainPage mainPage;
    private OurMissionPage ourMissionPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        topCustomBar = new TopCustomBar();
        newsPage = new NewsPage();
        aboutPages = new AboutPages();
        mainPage = new MainPage();
        ourMissionPage = new OurMissionPage();
        loginPage = new LoginPage();
        if (!topCustomBar.isLoginNow()) {
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
    @DisplayName("Выход из профиля")
    @Description("Проверяет, что пользователь может выйти из профиля через верхнее меню.")
    public void exitProfile() {
        topCustomBar.exit();
        loginPage.seeAuthorizationTitle();
    }

    @Test
    @DisplayName("Открытие страницы новостей")
    @Description("Проверяет, что после нажатия на 'Новости' в верхнем меню происходит переход на страницу новостей.")
    public void openNewsPage() {
        topCustomBar.openNewsPage();
        newsPage.seeNewsTitle();
    }

    @Test
    @DisplayName("Открытие страницы 'О приложении'")
    @Description("Проверяет, что после нажатия на 'О приложении' в верхнем меню происходит переход на соответствующую страницу.")
    public void openAboutPage() {
        topCustomBar.openAboutPage();
        aboutPages.seePrivacyPolicy();
    }

    @Test
    @DisplayName("Открытие главной страницы из страницы новостей")
    @Description("Проверяет, что можно вернуться на главную страницу, находясь на странице новостей.")
    public void openMainPage() {
        topCustomBar.openNewsPage();
        topCustomBar.openMainPage();
        mainPage.seeAllNews();
    }

    @Test
    @DisplayName("Открытие страницы 'Наша миссия'")
    @Description("Проверяет, что можно открыть страницу 'Наша миссия' через верхнее меню.")
    public void openOurMissionPage() {
        topCustomBar.openOurMissionPage();
        ourMissionPage.seeOurMission();
    }

    @Test
    @DisplayName("Кнопка 'Назад' возвращает на главную страницу")
    @Description("Проверяет, что после открытия страницы 'О приложении' и нажатия 'Назад', происходит возврат на главную страницу.")
    public void clickBackButtonStartMainPage() {
        topCustomBar.openAboutPage();
        topCustomBar.clickBackButton();
        mainPage.seeAllNews();
    }

    @Test
    @DisplayName("Кнопка 'Назад' возвращает на страницу 'Наша миссия'")
    @Description("Проверяет, что после открытия 'Наша миссия' и 'О приложении', нажатие 'Назад' возвращает на 'Наша миссия'.")
    public void clickBackButtonStartNewsPage() {
        topCustomBar.openOurMissionPage();
        topCustomBar.openAboutPage();
        topCustomBar.clickBackButton();
        ourMissionPage.seeOurMission();
    }
}
