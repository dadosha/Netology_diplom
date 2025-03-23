package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

import static ru.iteco.fmhandroid.Utils.formatDate;
import static ru.iteco.fmhandroid.Utils.getSystemPositiveButtonText;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class AddNewsPage {
    private static final int ADD_NEWS_TITLE = R.string.creating;
    private static final int CATEGORY_FIELD = R.id.news_item_category_text_input_layout;
    private static final int TITLE_FIELD = R.id.news_item_title_text_input_layout;
    private static final int DATE_FIELD = R.id.news_item_create_date_text_input_layout;
    private static final int TIME_FIELD = R.id.news_item_publish_time_text_input_layout;
    private static final int DESCRIPTION_FIELD = R.id.news_item_description_text_input_layout;
    private static final int CATEGORY_LIST_DESCRIPTION = R.string.news_item_category;
    private static final int TITLE_TEXT_FIELD = R.id.news_item_title_text_input_edit_text;
    private static final int DATE_TEXT_FIELD = R.id.news_item_publish_date_text_input_edit_text;
    private static final int TIME_TEXT_FIELD = R.id.news_item_publish_time_text_input_edit_text;
    private static final int DESCRIPTION_TEXT_FIELD = R.id.news_item_description_text_input_edit_text;
    private static final int SAVE_BUTTON = R.id.save_button;
    private static final int CANCEL_BUTTON = R.id.cancel_button;
    private static final int ERROR_IMAGE = R.id.text_input_end_icon;
    private static final int ERROR_TEXT = R.string.empty_fields;
    private static final int STATUS_SWITCHER = R.id.switcher;
    private static final int ACTIVE_STATUS = R.string.news_item_active;
    private static final int NOT_ACTIVE_STATUS = R.string.news_item_not_active;


    public void seeAddNewsTitle() {
        Allure.step("Проверяем наличие заголовка создания новости");
        onView(withText(ADD_NEWS_TITLE))
                .check(matches(isDisplayed()));
    }

    public void openCategory() {
        Allure.step("Открываем список категорий");
        onView(withHint(CATEGORY_LIST_DESCRIPTION))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void chooseCategory(String categoryName) {
        Allure.step("Выбираем категорию из списка - " + categoryName);
        onView(isRoot()).perform(closeSoftKeyboard());
        onView(withText(categoryName))
                .inRoot(RootMatchers.isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void replaceNewsTitle(String titleText) {
        Allure.step("Заменяем текст в поле заголовка новостей - " + titleText);
        onView(withId(TITLE_TEXT_FIELD))
                .check(matches(isDisplayed()))
                .perform(replaceText(titleText), closeSoftKeyboard());
    }

    public void replaceNewsDescription(String descriptionText) {
        Allure.step("Добавляем текст в поле описания новости - " + descriptionText);
        onView(withId(DESCRIPTION_TEXT_FIELD))
                .check(matches(isDisplayed()))
                .perform(replaceText(descriptionText), closeSoftKeyboard());
    }

    public void newsDate(int year, int month, int day) {
        Allure.step("Выбираем дату публикации новости - " + formatDate(day, month, year));
        onView(withId(DATE_TEXT_FIELD))
                .perform(click());

        onView(withClassName(equalTo(DatePicker.class.getName())))
                .perform(setDate(year, month, day));

        onView(withText(getSystemPositiveButtonText()))
                .perform(click());
    }

    public void newsTime(int hour, int minute) {
        Allure.step("Выбираем время публикации новости - " + hour + ":" + minute);
        onView(withId(TIME_TEXT_FIELD))
                .perform(click());

        onView(withClassName(equalTo(TimePicker.class.getName())))
                .perform(setTime(hour, minute));

        onView(withText(getSystemPositiveButtonText()))
                .perform(click());
    }

    private boolean getSwitchStatus() {
        Allure.step("Проверяем текущий статус переключателя");
        try {
            onView(withText(ACTIVE_STATUS))
                    .check(matches(isDisplayed()));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public void setNewsStatus(boolean status) {
        Allure.step("Устанавливаем статус новости: " + (status ? "Активна" : "Не активна"));
        if (!(getSwitchStatus() == status)) {
            Allure.step("Меняем статус новости");
            onView(withId(STATUS_SWITCHER))
                    .check(matches(isDisplayed()))
                    .perform(click());
        } else {
            Allure.step("Статус новости совпадает с необходимым, ничего не делаем");
        }
    }

    public void saveNews() {
        Allure.step("Нажимаем кнопку сохранения новости");
        onView(withId(SAVE_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void closeAddNewsPage() {
        Allure.step("Закрываем страницу создания новости");
        onView(withId(CANCEL_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(android.R.id.button1)) // "OK", "Да", "Aceptar"
                .inRoot(isDialog())
                .perform(click());
    }

    private void checkErrorIconDisplayed(int fieldId) {
        onView(allOf(
                withId(ERROR_IMAGE),
                isDescendantOfA(withId(fieldId))
        )).check(matches(isDisplayed()));
    }

    public void categoryHaveErrorIcon() {
        Allure.step("Проверяем наличие значка ошибки для поля категории");
        checkErrorIconDisplayed(CATEGORY_FIELD);
    }

    public void titleHaveErrorIcon() {
        Allure.step("Проверяем наличие значка ошибки для поля заголовка");
        checkErrorIconDisplayed(TITLE_FIELD);
    }

    public void dateHaveErrorIcon() {
        Allure.step("Проверяем наличие значка ошибки для поля даты");
        checkErrorIconDisplayed(DATE_FIELD);
    }

    public void timeHaveErrorIcon() {
        Allure.step("Проверяем наличие значка ошибки для поля времени");
        checkErrorIconDisplayed(TIME_FIELD);
    }

    public void descriptionHaveErrorIcon() {
        Allure.step("Проверяем наличие значка ошибки для поля описания новости");
        checkErrorIconDisplayed(DESCRIPTION_FIELD);
    }

    public int getErrorText() {
        Allure.step("Проверяем появление тост сообщения с текстом - " + ERROR_TEXT);
        return ERROR_TEXT;
    }

    public void createNews(String categoryName, String newsTitle, int year, int month, int day, int hour, int minutes, String newsDescription) {
        Allure.step("Создаем новость по введенным данным");
        openCategory();
        chooseCategory(categoryName);
        replaceNewsTitle(newsTitle);
        newsDate(year, month, day);
        newsTime(hour, minutes);
        replaceNewsDescription(newsDescription);
        saveNews();
    }

    public void editNewsFields(String categoryName, String newsTitle, Integer year, Integer month, Integer day,
                               Integer hour, Integer minutes, String newsDescription, Boolean status) {
        Allure.step("Отредактировать новость по введенным данным");

        if (categoryName != null) {
            openCategory();
            chooseCategory(categoryName);
        }

        if (newsTitle != null) {
            replaceNewsTitle(newsTitle);
        }

        if (year != null && month != null && day != null) {
            newsDate(year, month, day);
        }

        if (hour != null && minutes != null) {
            newsTime(hour, minutes);
        }

        if (newsDescription != null) {
            replaceNewsDescription(newsDescription);
        }

        if (status != null) {
            setNewsStatus(status);
        }

        saveNews();
    }

}
