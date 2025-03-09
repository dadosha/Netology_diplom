package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.res.Resources;

import androidx.test.core.app.ApplicationProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import ru.iteco.fmhandroid.matchers.ToastMatcher;

public class Utils {
    private static final List<String> categoryList = Arrays.asList(
            "Объявление",
            "День рождения",
            "Зарплата",
            "Профсоюз",
            "Праздник",
            "Массаж",
            "Благодарность",
            "Нужна помощь"
    );

    public static void checkToastMessage(int message) {
        onView(withText(message)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getSystemPositiveButtonText() {
        Context context = ApplicationProvider.getApplicationContext();
        Resources resources = context.getResources();
        return resources.getString(android.R.string.ok);
    }

    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public static int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    public static int getCurrentDay() {
        return LocalDate.now().getDayOfMonth();
    }

    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static String formatDate(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

    public static String randomCategory() {
        return categoryList.get(new Random().nextInt(categoryList.size()));
    }
}
