package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isIn;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.matchers.RecyclerViewMatcher;

public class OurMissionPage {
    private static final int OUR_MISSION_TEXT = R.id.our_mission_title_text_view;
    private static final int MISSION_EXPAND_BUTTON = R.id.our_mission_item_open_card_image_button;
    private static final int MISSION_TITLE = R.id.our_mission_item_title_text_view;
    private static final int MISSION_DESCRIPTION = R.id.our_mission_item_description_text_view;
    private static final int MISSION_LIST = R.id.our_mission_item_list_recycler_view;

    private static final List<String> missionTitles = Arrays.asList(
            "«Хоспис для меня - это то, каким должен быть мир.»",
            "Хоспис в своем истинном понимании - это творчество",
            "“В хосписе не работают плохие люди” В.В. Миллионщикова",
            "«Хоспис – это философия, из которой следует сложнейшая наука медицинской помощи умирающим и искусство ухода, в котором сочетается компетентность и любовь» С. Сандерс",
            "Служение человеку с теплом, любовью и заботой",
            "\"Хоспис продлевает жизнь, дает надежду, утешение и поддержку.\"",
            "\"Двигатель хосписа - милосердие плюс профессионализм\"\nА.В. Гнездилов, д.м.н., один из пионеров хосписного движения.",
            "Важен каждый!"
    );

    List<String> missionDescriptions = Arrays.asList(
            "Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер",
            "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.",
            "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.",
            "“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \n" +
                    "Би Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"",
            "\"Если пациента нельзя вылечить, это не значит, что для него ничего нельзя сделать. То, что кажется мелочью, пустяком в жизни здорового человека - для пациента имеет огромный смысл.\"",
            "\" Хоспис - это мои новые друзья. Полная перезагрузка жизненных ценностей. В хосписе нет страха и одиночества.\"\n" +
                    "Евгения Белоусова, дочь пациентки Ольги Васильевны",
            "\"Делай добро... А добро заразительно. По-моему, все люди милосердны. Нужно просто говорить с ними об этом, суметь разбудить в них чувство сострадания, заложенное от рождения\" - В.В. Миллионщикова",
            "\"Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных\""
    );


    public void seeOurMission() {
        Allure.step("Проверяем наличие заголовка страницы целей");
        onView(withId(OUR_MISSION_TEXT))
                .check(matches(isDisplayed()));
    }

    public int getRecyclerViewSize() {
        final int[] itemCount = {0};

        onView(withId(MISSION_LIST)).check((view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            itemCount[0] = recyclerView.getAdapter().getItemCount();
        });

        return itemCount[0];
    }

    public void missionHasTitle(int randomPosition) {
        Allure.step("Проверяем наличие заголовка у миссии");
        onView(withId(MISSION_LIST)).perform(scrollToPosition(randomPosition));

        onView(RecyclerViewMatcher.withRecyclerView(MISSION_LIST)
                .atPositionOnView(randomPosition, MISSION_TITLE))
                .check(matches(withText(isIn(missionTitles))));
    }

    public void missionHasDescription(int randomPosition) {
        onView(withId(MISSION_LIST)).perform(scrollToPosition(randomPosition));

        Allure.step("Раскрываем описание миссии");
        onView(RecyclerViewMatcher.withRecyclerView(MISSION_LIST)
                .atPositionOnView(randomPosition, MISSION_EXPAND_BUTTON))
                .check(matches(isDisplayed()))
                .perform(click());

        Allure.step("Проверяем наличие описания у миссии");
        onView(RecyclerViewMatcher.withRecyclerView(MISSION_LIST)
                .atPositionOnView(randomPosition, MISSION_DESCRIPTION))
                .check(matches(withText(isIn(missionDescriptions))));
    }
}
