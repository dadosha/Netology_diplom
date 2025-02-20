package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    private static final int OUR_MISSION_TEXT = R.id.our_mission_title_text_view;

    public void seeOurMission() {
        onView(withId(OUR_MISSION_TEXT))
                .check(matches(isDisplayed()));
    }
}
