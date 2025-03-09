package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.Utils.getCurrentDay;
import static ru.iteco.fmhandroid.Utils.getCurrentMonth;
import static ru.iteco.fmhandroid.Utils.getCurrentYear;

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
import ru.iteco.fmhandroid.NewsData;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AddNewsPage;
import ru.iteco.fmhandroid.pages.EditNewsPage;
import ru.iteco.fmhandroid.pages.FilterPage;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;


@RunWith(AndroidJUnit4.class)
@DisplayName("Тесты страницы фильтров новостей")
public class NewsFilterPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private TopCustomBar topCustomBar;
    private NewsPage newsPage;
    private FilterPage filterPage;
    private EditNewsPage editNewsPage;
    private AddNewsPage addNewsPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        topCustomBar = new TopCustomBar();
        newsPage = new NewsPage();
        filterPage = new FilterPage();
        editNewsPage = new EditNewsPage();
        addNewsPage = new AddNewsPage();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        topCustomBar.openNewsPage();
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Отмена настроек фильтра новостей")
    @Description("Открывает страницу фильтрации новостей, затем нажимает 'Отмена' и проверяет, что остаемся на странице новостей.")
    public void cancelFilterSettings() {
        newsPage.openFilterPage();
        filterPage.cancelFilter();
        newsPage.seeNewsTitle();
    }

    @Test
    @DisplayName("Ошибка при неверном периоде дат в фильтре")
    @Description("Устанавливает в фильтре некорректный временной диапазон и проверяет, что появляется сообщение об ошибке.")
    public void wrongPeriodDateMessage() {
        int year = getCurrentYear();
        int month = getCurrentMonth();
        int day = getCurrentDay();

        newsPage.openFilterPage();

        filterPage.fillFilterForm(null, year, month, day, null, null, null, null, null);

        filterPage.seeWrongDatePeriodMessage();
    }

    @Test
    @DisplayName("Фильтрация новостей по категории и дате")
    @Description("Создает новость, затем фильтрует список по её категории и дате, проверяет, что она найдена.")
    public void settingNewsFilter() {
        NewsData news = new NewsData();

        newsPage.openEditNewsPage();
        editNewsPage.openAddNewsPage();

        addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);

        topCustomBar.openNewsPage();
        newsPage.openFilterPage();

        filterPage.fillFilterForm(news.category, news.year, news.month, news.day, news.year, news.month, news.day, null, null);

        newsPage.findNewsInNewsPage(news.title);
    }
}
