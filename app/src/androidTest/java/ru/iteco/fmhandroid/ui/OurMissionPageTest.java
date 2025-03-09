package ru.iteco.fmhandroid.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.OurMissionPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@RunWith(AndroidJUnit4.class)
@DisplayName("Тесты страницы миссии")
public class OurMissionPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private OurMissionPage ourMissionPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        ourMissionPage = new OurMissionPage();
        TopCustomBar topCustomBar = new TopCustomBar();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        topCustomBar.openOurMissionPage();
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Проверка наличия заголовка в карточке миссии")
    @Description("Выбирает случайную карточку миссии и проверяет, что у неё есть заголовок.")
    public void missionCardHaveTitle() {
        int randomPosition = new Random().nextInt(ourMissionPage.getRecyclerViewSize());
        ourMissionPage.missionHasTitle(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия описания в карточке новости")
    @Description("Выбирает случайную карточку новости и проверяет, что у неё есть описание.")
    public void missionCardHaveDescription() {
        int randomPosition = new Random().nextInt(ourMissionPage.getRecyclerViewSize());
        ourMissionPage.missionHasDescription(randomPosition);
    }
}
