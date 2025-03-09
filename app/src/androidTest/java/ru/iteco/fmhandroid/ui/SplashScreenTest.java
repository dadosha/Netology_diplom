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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;


@RunWith(AndroidJUnit4.class)
@DisplayName("Тест экрана запуска приложения")
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
    @DisplayName("Проверка отображения экрана запуска")
    @Description("Проверяем, что экран загрузки отображается и содержит один из ожидаемых текстов.")
    public void splashScreenTest() {
        Allure.step("Находим поле текстов на экране заставки");
        ViewInteraction splashScreenText = onView(withId(R.id.splashscreen_text_view));
        splashScreenText.check(matches(isDisplayed()));
        Allure.step("Проверяем, что текст в поле один из заложенных");
        splashScreenText.check(matches(withText(isIn(splashscreenTexts))));
    }
}
