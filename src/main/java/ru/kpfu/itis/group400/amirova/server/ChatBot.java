package ru.kpfu.itis.group400.amirova.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChatBot {

    private static final String pathWeather = "https://wttr.in/%s?format=j1";
    private static final String pathRate = "https://api.exchangerate-api.com/v4/latest/%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    /*
необходимо реализовать чат-бота на javafx (можно без сокетов), в котором буду реализованы команды:
- list, которая выводит список доступных команд
- weather, которая выводит погоду в указанном городе (как в первой контрольной)
- exchange (rate), которая выводит курс указанной валюты к рублю (использовать api для получения курса валют)
- quit, которая выводит на главную страницу
     */

    public String processCommand(String input) {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0];
        String message = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "list":
                return outputAvailableCommand();
            case "weather":
                return outputWeather(message);
            case "rate":
                return outputRate(message);
            case "quit":
                return "Чат очищен";
            default:
                return "Неверная команда";

        }
    }

    private String outputRate(String currency) {

        if (currency.equals("")) {
            return "Введите валюту в виде: rate Необходимая_валюта";
        }

        currency = currency.toUpperCase();

        try {
            String urlString = String.format(pathRate, currency);

            String jsonResponse = makeHttpRequest(urlString);
            JsonNode root = objectMapper.readTree(jsonResponse);

            double rateToRUB = root.path("rates").path("RUB").asDouble();

            return String.format("Курс %s к RUB: %.2f рублей", currency, rateToRUB);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String outputAvailableCommand() {
        return "Доступные команды:\n" +
                "- list - показать команды\n" +
                "- weather [город] - погода\n" +
                "- rate [валюта] - курс валюты\n" +
                "- quit - очистить чат";
    }

    private String outputWeather(String message) {

        if (message.equals("")) {
            return "Введите город в виде: weather Необходимый_город";
        }
        try {
            String urlString = String.format(pathWeather, message);
            String jsonResponse = makeHttpRequest(urlString);
            JsonNode root = objectMapper.readTree(jsonResponse);

            String area = root.path("nearest_area").get(0).path("areaName").get(0).path("value").asText();

            double tempC = root.path("current_condition").get(0).path("temp_C").asDouble();
            String description = root.path("current_condition").get(0).path("weatherDesc").get(0).path("value").asText();
            double humidity = root.path("current_condition").get(0).path("humidity").asDouble();

            return String.format("Погода в %s: %.1f°C, %s, влажность %.0f%%",
                    area, tempC, description, humidity);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String makeHttpRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }


}
