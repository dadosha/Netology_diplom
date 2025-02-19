package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class Utils {
    public static boolean isElementVisible(int viewId) {
        final boolean[] isVisible = {false};

        onView(withId(viewId))
                .check((view, noViewFoundException) -> isVisible[0] = (noViewFoundException == null));

        return isVisible[0]; // Вернёт true, если элемент найден и видим
    }
}
