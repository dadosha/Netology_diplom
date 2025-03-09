package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.equalTo;
import static ru.iteco.fmhandroid.Utils.formatDate;
import static ru.iteco.fmhandroid.Utils.getSystemPositiveButtonText;

import android.widget.DatePicker;

import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class FilterPage {
    private static final int FILTER_TITLE = R.id.filter_news_title_text_view;
    private static final int FILTER_BUTTON = R.id.filter_button;
    private static final int CANCEL_BUTTON = R.id.cancel_button;
    private static final int CATEGORY_LIST_DESCRIPTION = R.string.news_item_category;
    private static final int DATE_START_TEXT_FIELD = R.id.news_item_publish_date_start_text_input_edit_text;
    private static final int DATE_END_TEXT_FIELD = R.id.news_item_publish_date_end_text_input_edit_text;
    private static final int ACTIVE_CHECKBOX = R.id.filter_news_active_material_check_box;
    private static final int INACTIVE_CHECKBOX = R.id.filter_news_inactive_material_check_box;
    private static final int WRONG_DATE_PERIOD_TEXT = R.string.wrong_news_date_period;


    public void seeFilterTitle() {
        Allure.step("Проверяем наличие заголовка фильтров");
        onView(withId(FILTER_TITLE))
                .check(matches(isDisplayed()));
    }

    public void cancelFilter() {
        Allure.step("Кликаем на страницу закрытия фильтров");
        onView(withId(CANCEL_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void openCategory() {
        Allure.step("Открываем выпадающий список категорий");
        onView(withHint(CATEGORY_LIST_DESCRIPTION))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void chooseCategory(String categoryName) {
        Allure.step("Выбираем категорию - " + categoryName);
        onView(isRoot()).perform(closeSoftKeyboard());
        onView(withText(categoryName))
                .inRoot(RootMatchers.isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void newsDateStart(int year, int month, int day) {
        Allure.step("Выбираем начальную дату для фильтра - " + formatDate(day, month, year));
        onView(withId(DATE_START_TEXT_FIELD))
                .perform(click());

        onView(withClassName(equalTo(DatePicker.class.getName())))
                .perform(setDate(year, month, day));

        onView(withText(getSystemPositiveButtonText()))
                .perform(click());
    }

    public void newsDateEnd(int year, int month, int day) {
        Allure.step("Выбираем конечную дату для фильтра - " + formatDate(day, month, year));
        onView(withId(DATE_END_TEXT_FIELD))
                .perform(click());

        onView(withClassName(equalTo(DatePicker.class.getName())))
                .perform(setDate(year, month, day));

        onView(withText(getSystemPositiveButtonText()))
                .perform(click());
    }

    public void clickFilterButton() {
        Allure.step("Применяем настроенный фильтр");
        onView(withId(FILTER_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void seeWrongDatePeriodMessage() {
        Allure.step("Проверяем наличие сообщения о неверно выбранном периоде дат");
        onView(withText(WRONG_DATE_PERIOD_TEXT))
                .check(matches(isDisplayed()));
    }

    public void chooseActiveCheckBox() {
        Allure.step("Выбираем чекбокс активных новостей");
        onView(withId(ACTIVE_CHECKBOX)).perform(click());
    }

    public void chooseInActiveCheckBox() {
        Allure.step("Выбираем чекбокс неактивных новостей");
        onView(withId(INACTIVE_CHECKBOX)).perform(click());
    }

    public void fillFilterForm(String category, Integer startYear, Integer startMonth, Integer startDay, Integer endYear, Integer endMonth, Integer endDay, Boolean isActive, Boolean isInactive) {
        Allure.step("Заполняем форму фильтрации новостей");

        if (category != null) {
            openCategory();
            chooseCategory(category);
        }

        if (startYear != null && startMonth != null && startDay != null) {
            newsDateStart(startYear, startMonth, startDay);
        }

        if (endYear != null && endMonth != null && endDay != null) {
            newsDateEnd(endYear, endMonth, endDay);
        }

        if (isActive != null && isActive) {
            chooseActiveCheckBox();
        }

        if (isInactive != null && isInactive) {
            chooseInActiveCheckBox();
        }

        clickFilterButton();
    }

}
