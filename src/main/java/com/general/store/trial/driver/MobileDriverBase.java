package com.general.store.trial.driver;


import io.appium.java_client.android.AndroidDriver;

public class MobileDriverBase {

    private static final ThreadLocal<AndroidDriver> driverThread = new ThreadLocal<>();

    private MobileDriverBase() {
    }

    public static void instantiateDriverObject() {
        driverThread.set(MobileDriverFactory.getInstance().getDriverObject());
    }

    public static AndroidDriver getDriver() {
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

