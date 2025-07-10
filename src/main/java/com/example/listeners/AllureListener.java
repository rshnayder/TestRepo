package com.example.listeners;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

public class AllureListener extends AllureTestNg {

    private static final Logger logger = LoggerFactory.getLogger(AllureListener.class);
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("[TEST STARTED] - %s", testName);
        Allure.step("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("[TEST PASSED] - %s", testName);
        Allure.step("Test passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        logger.error("[TEST FAILED] - %s", testName);
        logger.error("Reason: %s", throwable.getMessage());


        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        Allure.addAttachment("Stacktrace", sw.toString());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("[TEST SKIPPED] - %s", testName);
        Allure.step("Test skipped: " + testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("[ALL TESTS FINISHED] - %s", context.getName());
    }
}