package ru.iteco.fmhandroid.ui;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

@RunWith(AndroidJUnit4.class)
@DisplayName("Тест названия пакета приложения")
public class PackageNameTest {

    @Test
    @DisplayName("Проверка корректного названия пакета")
    @Description("Получаем `appContext` и проверяем, что название пакета соответствует `ru.iteco.fmhandroid`.")
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ru.iteco.fmhandroid", appContext.getPackageName());
    }
}