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
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private LoginPage loginPage;
    private TopCustomBar topCustomBar;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        topCustomBar = new TopCustomBar();
        loginPage = new LoginPage();
        if (topCustomBar.isLoginNow()) {
            topCustomBar.exit();
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
    public void testSuccessfulLogin() {
        loginPage.login("login2", "password2");
        topCustomBar.seeMainLogo();
    }

    @Test
    public void testEmptyDataLogin() {
        loginPage.login("", "");;
        checkToastMessage(loginPage.getEmptyLoginDataText());
    }

    @Test
    public void testIncorrectDataLogin() {
        loginPage.login("login2", "asdadavzvzd");
        checkToastMessage(loginPage.getIncorrectLoginDataText());
    }
}
