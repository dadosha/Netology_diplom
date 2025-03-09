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
import ru.iteco.fmhandroid.NewsData;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.SplashScreenIdlingResource;
import ru.iteco.fmhandroid.pages.AddNewsPage;
import ru.iteco.fmhandroid.pages.EditNewsPage;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NewsCard;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;


@RunWith(AndroidJUnit4.class)
@DisplayName("Карточки новостей")
public class NewsCardTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private NewsCard newsCard;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        newsCard = new NewsCard();
        TopCustomBar topCustomBar = new TopCustomBar();
        NewsPage newsPage = new NewsPage();
        EditNewsPage editNewsPage = new EditNewsPage();
        AddNewsPage addNewsPage = new AddNewsPage();
        if (!topCustomBar.isLoginNow()) {
            LoginPage loginPage = new LoginPage();
            loginPage.login("login2", "password2");
        }
        if (!newsCard.checkNewsListIsDisplayed()) {
            topCustomBar.openNewsPage();
            newsPage.openEditNewsPage();
            editNewsPage.openAddNewsPage();

            NewsData news = new NewsData();
            addNewsPage.createNews(news.category, news.title, news.year, news.month, news.day, news.hour, news.minutes, news.description);
            topCustomBar.openMainPage();
        };
    }

    @After
    public void tearDown() {
        if (splashIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(splashIdlingResource);
        }
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    @DisplayName("Проверка наличия заголовка в карточке новости")
    @Description("Выбирает случайную карточку новости и проверяет, что у неё есть заголовок.")
    public void newsCardHaveTitle() {
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasTitle(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия изображения в карточке новости")
    @Description("Выбирает случайную карточку новости и проверяет, что у неё есть изображение.")
    public void newsCardHaveImage() {
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasImage(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия даты в карточке новости")
    @Description("Выбирает случайную карточку новости и проверяет, что у неё есть дата публикации.")
    public void newsCardHaveDate() {
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasDate(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия описания в карточке новости")
    @Description("Выбирает случайную карточку новости и проверяет, что у неё есть описание.")
    public void newsCardHaveDescription() {
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasDescription(randomPosition);
    }
}
