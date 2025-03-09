package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.Utils.getCurrentDay;
import static ru.iteco.fmhandroid.Utils.getCurrentMonth;
import static ru.iteco.fmhandroid.Utils.getCurrentYear;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.EspressoIdlingResources;
import ru.iteco.fmhandroid.NewsData;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AddNewsPage;
import ru.iteco.fmhandroid.pages.EditNewsPage;
import ru.iteco.fmhandroid.pages.EmptyPage;
import ru.iteco.fmhandroid.pages.FilterPage;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@DisplayName("Тесты страницы управления новостями")
public class EditNewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private EditNewsPage editNewsPage;
    private AddNewsPage addNewsPage;
    private EmptyPage emptyPage;
    private FilterPage filterPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        TopCustomBar topCustomBar = new TopCustomBar();
        NewsPage newsPage = new NewsPage();
        editNewsPage = new EditNewsPage();
        addNewsPage = new AddNewsPage();
        emptyPage = new EmptyPage();
        filterPage = new FilterPage();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        topCustomBar.openNewsPage();
        newsPage.openEditNewsPage();
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Открытие страницы добавления новости")
    @Description("Проверяем, что кнопка добавления новости работает и открывает нужную страницу.")
    public void openAddNews() {
        editNewsPage.openAddNewsPage();
        addNewsPage.seeAddNewsTitle();
    }

    @Test
    @DisplayName("Удаление новости")
    @Description("Создаем новость, находим её в списке, удаляем и проверяем, что она больше не отображается.")
    public void deleteNews() {
        NewsData news = new NewsData();

        editNewsPage.openAddNewsPage();
        addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);

        editNewsPage.findNewsInControlPage(news.title);
        editNewsPage.deleteNews(news.title);
        editNewsPage.updateNewsList();
        editNewsPage.notFindTitleInControlPage(news.title);
    }

    @Test
    @DisplayName("Обновление списка новостей")
    @Description("Устанавливаем фильтр на будущие даты, проверяем, что список пуст, обновляем страницу и снова проверяем.")
    public void refreshPage() {
        int year = getCurrentYear();
        int month = getCurrentMonth();
        int day = getCurrentDay();

        editNewsPage.openFilterPage();

        filterPage.newsDateStart(year + 1, month, day);
        filterPage.newsDateEnd(year + 1, month, day);
        filterPage.clickFilterButton();

        emptyPage.seeFilterTitle();
        // Здесь в теории можно дергать ручки апи на создание новости
        emptyPage.refreshControlPanelButton();
        emptyPage.seeFilterTitle();
    }

    @Test
    @DisplayName("Редактирования новости")
    @Description("Создаем новость, находим её в списке, меняем статус и проверяем, что статус изменился.")
    public void editNews() {
        NewsData news = new NewsData();

        editNewsPage.openAddNewsPage();
        addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);

        editNewsPage.editNews(news.title);
        addNewsPage.editNewsFields(null, null, null, null, null, null, null, null, false);

        editNewsPage.findNewsInControlPage(news.title);
        editNewsPage.checkNotActiveStatusNews(news.title);
    }
}
