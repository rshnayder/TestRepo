package com.example.utils;

import java.nio.charset.StandardCharsets;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

public class Allurelogger {

    public static void logRequest(String requestDetails) {
        Allure.addAttachment("Request Payload", "application/json",
                             requestDetails, StandardCharsets.UTF_8.name());
    }

    /**
     * Додає відповідь у звіт Allure.
     *
     * @param response Відповідь `Response` з API.
     */
    public static void logResponse(Response response) {
        Allure.addAttachment("Response Payload", "application/json",
                             response.asString(), StandardCharsets.UTF_8.name());
    }
}
