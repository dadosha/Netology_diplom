package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isIn;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashScreenTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private static final List<String> splashscreenTexts = Arrays.asList(
            "Любая помощь важна и нужна",
            "Бережное отношение к пациентам и их близким",
            "Творческий и осознанный подход к жизни до конца",
            "Ответственно и осознанно нести добро людям",
            "Помощь – это создание комфорта для пациентов и их близких",
            "Творческий и осознанный подход к жизни пациента",
            "Добро есть везде и во всех",
            "Ответственная доброта",
            "Создание физического и психологического пространства для завершения жизни",
            "Чем больше мы принимаем добра, тем больше отдаем",
            "Хоспис – это воплощенная гуманность",
            "Хоспис — это призвание и служение человечеству",
            "Хоспис – это компетентная помощь и любовь к пациентам"
    );

    @Test
    public void splashScreenTest() {
        ViewInteraction splashScreenText = onView(withId(R.id.splashscreen_text_view));
        splashScreenText.check(matches(isDisplayed()));
        splashScreenText.check(matches(withText(isIn(splashscreenTexts))));
    }
}
