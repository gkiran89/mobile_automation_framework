package co.sample.config;

import io.appium.java_client.AppiumDriver;

public class ContextManager {
    private static ThreadLocal<AppiumDriver> mobDriver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return mobDriver.get();
    }

    public static void setDriver(AppiumDriver driver) {
        mobDriver.set(driver);
    }
}

