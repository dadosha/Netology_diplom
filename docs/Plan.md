### Профили для тестирования

| Логин  | Пароль    | Права админа |
|--------|-----------|--------------|
| login2 | password2 | Есть         |
| login4 | password4 | Нет          |

## Перечень проверяемых страниц
1. Экран запуска
2. Страница авторизации
3. Главная страница
4. Страница "О приложении"
5. Страница новостей
6. Фильтр новостей
7. Страница миссии 
8. Страница Панели управления
9. Фильтр новостей Панели управления
10. Страница создания новости
11. Страница редактирования новости

## Перечень автоматизируемых сценариев
Для кейсов добавил ID и по ним перечислю:
Точно сделаю: 2-15, 25-53, 56

Хочу сделать, но не уверен, что смогу:
1. 1 — не пробовал захватывать экран при запуске приложения, но интересно постараться
2. 16–24 — можно сделать, но при проверке приложения были ситуации, когда создавал новости, и они   сразу исчезали, или появлялись новости, которые я не создавал; вероятнее всего, это связано с тем, что общий доступ есть у всех.   Не знаю, как это повлияет на выполняемые кейсы.
3. 58 — не знаю, можно ли перед тестами отключать сеть на устройстве; нужно почитать и попробовать
4. 54–55 — интересно попробовать

## Используемое устройство
1. Планшет - версия Android 10 - API 29
2. TECNO POVA 4 - версия Android 12 - API 31
3. Телевизор - версия AndroidTV 12 - API 32

## Перечень используемых инструментов с обоснованием выбора.

1. Java - Основной язык программирования для написания тестов
2. Gradle - для управления зависимостями и автоматизации сборки проекта
3. JUnit 4 - используется для UI-тестов с Espresso
4. Espresso - фреймворк для UI-тестирования
5. Espresso Intents - тестирование интентов
6. Espresso Idling Resource - работа с асинхронными операциями
7. AndroidX Test Ext JUnit - расширение JUnit для Android-тестов
8. AndroidX Test Runner - запуск и управление тестами
9. AndroidX Test Rules - управление жизненным циклом тестов
10. Allure - для создания отчетов о тестировании

## Перечень необходимых разрешений, данных и доступов.

1. Разрешение на автоматизацию
2. Пользователь с админскими правами

## Перечень и описание возможных рисков при автоматизации.

1. Обновления пприложения 
2. Собственные недостатки в знаниях и опыте
3. Изменения API Android
4. Проблемы с тестовой средой 
5. Человеческие(личные проблемы или неожиданные обстоятельства)

## Перечень необходимых специалистов для автоматизации.

1. Автоматизатор тестировщик

## Интервальная оценка с учётом рисков в часах.

|Этап| Время (ч) |
|-----|---|
|Подготовка тестового окружения| 3 |
|Написание тестов| 12 |
|Запуск и отладка| 8 |
|Анализ и документация| 4 |
|Итого:| 27 часов |