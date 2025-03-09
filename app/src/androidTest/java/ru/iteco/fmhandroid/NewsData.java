package ru.iteco.fmhandroid;

import static ru.iteco.fmhandroid.Utils.*;

public class NewsData {
    public String category;
    public String title;
    public String description;
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minutes;

    public NewsData() {
        this.category = randomCategory();
        this.title = generateRandomUUID();
        this.description = generateRandomUUID();
        this.year = getCurrentYear();
        this.month = getCurrentMonth();
        this.day = getCurrentDay();
        this.hour = getCurrentHour();
        this.minutes = getCurrentMinute();
    }
}