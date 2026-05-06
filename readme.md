# Чат-бот с погодой и курсом валют

Десктопное приложение на JavaFX, которое позволяет получить текущую погоду в любом городе и курс валюты к рублю через текстовые команды.

## Функциональность

- `list` – показать список доступных команд
- `weather <город>` – получить погоду (температура, описание, влажность)
- `rate <валюта>` – получить курс указанной валюты к рублю (например, `rate USD`)
- `quit` – очистить чат

## Технологии

- Java 8+
- JavaFX (графический интерфейс)
- HttpURLConnection (HTTP-запросы)
- Jackson (ObjectMapper) – парсинг JSON
- API:
  - [wttr.in](https://wttr.in) – погода
  - [exchangerate-api.com](https://exchangerate-api.com) – курсы валют

## Скриншот

*Добавь сюда скриншот окна приложения*

## Как запустить

### Вариант 1: через IDE (IntelliJ IDEA, Eclipse)

1. Склонируй репозиторий:  
   `git clone https://github.com/Umarrina/твой-репозиторий.git`
2. Открой проект в IDE как Maven/Gradle или просто как Java-проект.
3. Убедись, что библиотека Jackson подключена (если нет – скачай `jackson-databind` и добавь в classpath).
4. Запусти класс `ru.kpfu.itis.group400.amirova.ChatApplication`.

### Вариант 2: через командную строку (если настроен JavaFX)

```bash
javac -cp ".;jackson-databind-2.15.0.jar" ru/kpfu/itis/group400/amirova/*.java ru/kpfu/itis/group400/amirova/server/*.java ru/kpfu/itis/group400/amirova/view/*.java
java -cp ".;jackson-databind-2.15.0.jar" ru.kpfu.itis.group400.amirova.ChatApplication
```

## Примеры использования

```
> list
Доступные команды:
- list - показать команды
- weather [город] - погода
- rate [валюта] - курс валюты
- quit - очистить чат

> weather Казань
Погода в Kazan: 12.5°C, Partly cloudy, влажность 67%

> rate USD
Курс USD к RUB: 92.45 рублей
```

## Структура проекта

```
src/
├── ru/kpfu/itis/group400/amirova/
│   ├── ChatApplication.java       # Точка входа JavaFX
│   ├── view/
│   │   └── ChatView.java          # GUI (AnchorPane, TextArea)
│   └── server/
│       └── ChatBot.java           # Логика обработки команд, HTTP-запросы
```
