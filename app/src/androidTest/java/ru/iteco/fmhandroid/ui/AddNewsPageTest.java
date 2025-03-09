package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.Utils.checkToastMessage;
import static ru.iteco.fmhandroid.Utils.formatDate;

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
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@DisplayName("Тесты страницы создания новости")
public class AddNewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private TopCustomBar topCustomBar;
    private NewsPage newsPage;
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
        editNewsPage = new EditNewsPage();
        addNewsPage = new AddNewsPage();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        topCustomBar.openNewsPage();
        newsPage.openEditNewsPage();
        editNewsPage.openAddNewsPage();
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Добавление новой новости через панель управления")
    @Description("Проверяем, что новость успешно создаётся и отображается в панели управления новостями.")
    public void addNews() {
        NewsData news = new NewsData();

        addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);

        editNewsPage.findNewsInControlPage(news.title);
        editNewsPage.checkPublishedDate(news.title, formatDate(news.day, news.month, news.year));
        editNewsPage.checkPublishedDescription(news.title, news.description);
        editNewsPage.checkActiveStatusNews(news.title);
        editNewsPage.checkCreateDate(news.title, formatDate(news.day, news.month, news.year));
    }

    @Test
    @DisplayName("Добавление новости и проверка в ленте")
    @Description("Добавляем новость и проверяем, что она отображается в списке новостей.")
    public void addNewsInNewsPage() {
        NewsData news = new NewsData();

        addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);

        topCustomBar.openNewsPage();

        newsPage.updateNewsList();
        newsPage.findNewsInNewsPage(news.title);
    }

    @Test
    @DisplayName("Закрытие страницы добавления новости")
    @Description("Проверяем, что страница добавления новости успешно закрывается.")
    public void closeAddNewsPage() {
        addNewsPage.closeAddNewsPage();
        editNewsPage.seeEditNewsTitle();
    }

    @Test
    @DisplayName("Ошибка при сохранении пустой новости")
    @Description("Проверяем, что при попытке сохранить пустую новость появляется ошибка.")
    public void errorImageForFields() {
        addNewsPage.saveNews();
        checkToastMessage(addNewsPage.getErrorText());
        addNewsPage.categoryHaveErrorIcon();
        addNewsPage.titleHaveErrorIcon();
        addNewsPage.dateHaveErrorIcon();
        addNewsPage.timeHaveErrorIcon();
        addNewsPage.descriptionHaveErrorIcon();
    }
}
