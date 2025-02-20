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

import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AboutPages;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

public class AboutScreenTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private TopCustomBar topCustomBar;
    private AboutPages aboutPages;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        topCustomBar = new TopCustomBar();
        aboutPages = new AboutPages();
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
    public void clickPrivacyPolicy() {
        topCustomBar.openAboutPage();
        Intents.init();
        aboutPages.clickPrivacyPolicyLink();
        Intents.intended(hasData(aboutPages.getPrivacyPolicyValueLink()));
        Intents.intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }

    @Test
    public void clickTermsOfUse() {
        topCustomBar.openAboutPage();
        Intents.init();
        aboutPages.clickTermsOfUse();
        Intents.intended(hasData(aboutPages.getTermsOfUseValueLink()));
        Intents.intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }
}
