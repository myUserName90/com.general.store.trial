package com.general.store.trial.driver;


import io.appium.java_client.AppiumDriver;

public class MobileDriverBase {

    private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    private MobileDriverBase() {
    }

    public static void instantiateDriverObject() {
        driverThread.set(MobileDriverFactory.getInstance().getDriverObject());
    }

    public static AppiumDriver getDriver() {
        return driverThread.get();
    }

    public static void closeDriverObjects() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        } catch (Exception ignore) {
        } finally {
            driverThread.remove();
        }
    }
}

