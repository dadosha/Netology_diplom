package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.Utils.checkToastMessage;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AboutPages;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.OurMissionPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@LargeTest
@RunWith(AndroidJUnit4.class)
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
    public void exitProfile() {
        topCustomBar.exit();
    }

    @Test
    public void openNewsPage() {
        topCustomBar.openNewsPage();
        newsPage.seeNewsTitle();
    }

    @Test
    public void openAboutPage() {
        topCustomBar.openAboutPage();
        aboutPages.seePrivacyPolicy();
    }

    @Test
    public void openMainPage() {
        topCustomBar.openNewsPage();
        topCustomBar.openMainPage();
        mainPage.seeAllNews();
    }

    @Test
    public void openOurMissionPage() {
        topCustomBar.openOurMissionPage();
        ourMissionPage.seeOurMission();
    }

    @Test
    public void clickBackButtonStartMainPage() {
        topCustomBar.openAboutPage();
        topCustomBar.clickBackButton();
        mainPage.seeAllNews();
    }

    @Test
    public void clickBackButtonStartNewsPage() {
        topCustomBar.openOurMissionPage();
        topCustomBar.openAboutPage();
        topCustomBar.clickBackButton();
        ourMissionPage.seeOurMission();
    }
}
