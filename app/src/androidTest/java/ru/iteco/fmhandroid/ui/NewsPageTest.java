package ru.iteco.fmhandroid.ui;

import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.Utils.generateRandomUUID;
import static ru.iteco.fmhandroid.Utils.getCurrentDay;
import static ru.iteco.fmhandroid.Utils.getCurrentHour;
import static ru.iteco.fmhandroid.Utils.getCurrentMinute;
import static ru.iteco.fmhandroid.Utils.getCurrentMonth;
import static ru.iteco.fmhandroid.Utils.getCurrentYear;
import static ru.iteco.fmhandroid.Utils.randomCategory;

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
import ru.iteco.fmhandroid.pages.AddNewsPage;
import ru.iteco.fmhandroid.pages.EditNewsPage;
import ru.iteco.fmhandroid.pages.EmptyPage;
import ru.iteco.fmhandroid.pages.FilterPage;
import ru.iteco.fmhandroid.pages.LoginPage;
import ru.iteco.fmhandroid.pages.NewsCard;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.pages.TopCustomBar;

@RunWith(AndroidJUnit4.class)
@DisplayName("Тесты страницы новостей")
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private SplashScreenIdlingResource splashIdlingResource;
    private NewsCard newsCard;
    private TopCustomBar topCustomBar;
    private NewsPage newsPage;
    private EditNewsPage editNewsPage;
    private FilterPage filterPage;
    private EmptyPage emptyPage;
    private AddNewsPage addNewsPage;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            splashIdlingResource = new SplashScreenIdlingResource(activity, R.id.splashscreen_text_view);
            IdlingRegistry.getInstance().register(splashIdlingResource);
        });
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        newsCard = new NewsCard();
        topCustomBar = new TopCustomBar();
        newsPage = new NewsPage();
        editNewsPage = new EditNewsPage();
        filterPage = new FilterPage();
        emptyPage = new EmptyPage();
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
    @DisplayName("Проверка наличия заголовка в карточке новости")
    @Description("Открываем страницу новостей, выбираем случайную карточку и проверяем, что у неё есть заголовок.")
    public void newsCardHaveTitle() {
        if (!newsCard.checkNewsListIsDisplayed()) {
            return;
        };
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasTitle(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия изображения в карточке новости")
    @Description("Открываем страницу новостей, выбираем случайную карточку и проверяем, что у неё есть изображение.")
    public void newsCardHaveImage() {
        if (!newsCard.checkNewsListIsDisplayed()) {
            return;
        };
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasImage(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия даты в карточке новости")
    @Description("Открываем страницу новостей, выбираем случайную карточку и проверяем, что у неё указана дата.")
    public void newsCardHaveDate() {
        if (!newsCard.checkNewsListIsDisplayed()) {
            return;
        };
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasDate(randomPosition);
    }

    @Test
    @DisplayName("Проверка наличия описания в карточке новости")
    @Description("Открываем страницу новостей, выбираем случайную карточку и проверяем, что у неё есть описание.")
    public void newsCardHaveDescription() {
        if (!newsCard.checkNewsListIsDisplayed()) {
            return;
        };
        int randomPosition = new Random().nextInt(newsCard.getRecyclerViewSize());
        newsCard.newsHasDescription(randomPosition);
    }

    @Test
    @DisplayName("Открытие страницы редактирования новостей")
    @Description("Открываем страницу новостей, затем переходим в режим редактирования.")
    public void openEditNews() {
        newsPage.openEditNewsPage();
        editNewsPage.seeEditNewsTitle();
    }

    @Test
    @DisplayName("Открытие страницы фильтрации новостей")
    @Description("Открываем страницу новостей, затем открываем фильтры.")
    public void openFilterNews() {
        newsPage.openFilterPage();
        filterPage.seeFilterTitle();
    }

    @Test
    @DisplayName("Обновление страницы новостей")
    @Description("Фильтруем новости по будущей дате, проверяем, что список пуст, затем обновляем страницу.")
    public void refreshPage() {
        int year = getCurrentYear();
        int month = getCurrentMonth();
        int day = getCurrentDay();

        newsPage.openFilterPage();

        filterPage.newsDateStart(year + 1, month, day);
        filterPage.newsDateEnd(year + 1, month, day);
        filterPage.clickFilterButton();

        emptyPage.seeFilterTitle();
        emptyPage.refreshNewsButton();
    }

    @Test
    @DisplayName("Сортировка новостей по убыванию даты")
    @Description("Создаём несколько новостей, проверяем, что первая новость перемещается в конец списка после сортировки.")
    public void sortNews() {
        if (!newsCard.checkNewsListIsDisplayed()) {
            throw new AssertionError("Список новостей не отображается");
        }

        while (newsCard.getRecyclerViewSize() < 2) {
            newsPage.openEditNewsPage();
            editNewsPage.openAddNewsPage();
            addNewsPage.createNews(randomCategory(), generateRandomUUID(), getCurrentYear(), getCurrentMonth(),
                    getCurrentDay(), getCurrentHour(), getCurrentMinute(), generateRandomUUID());
            topCustomBar.openNewsPage();
        }

        String firstNewsTitle = newsCard.getNewsTitle(0);
        newsPage.clickSortPage();
        int lastPosition = newsCard.getRecyclerViewSize() - 1;
        String lastNewsTitle  = newsCard.getNewsTitle(lastPosition);
        assertEquals("Новость не переместилась в конец списка", firstNewsTitle, lastNewsTitle);
    }
}
