package com.general.store.trial.driver;

import com.general.store.trial.configuration.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URI;
import java.time.Duration;

public class MobileDriverFactory {

    public static MobileDriverFactory getInstance() {
        return new MobileDriverFactory();
    }

    public AppiumDriver getDriverObject() {
        return switch (Configuration.PLATFORM.toUpperCase()) {
            case "ANDROID" -> getAndroidDriver();
            case "IOS" -> getIOSDriver();
            default -> throw new IllegalStateException("Unsupported platform: " + Configuration.PLATFORM);
        };
    }

    private AndroidDriver getAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp("C:\\Users\\Narek Melikyan\\IdeaProjects\\testProjects\\com.general.store.trial\\src\\main\\resources\\apk\\General-Store.apk")
                .setPlatformName(Configuration.PLATFORM)
                .setDeviceName(Configuration.DEVICE_NAME)
                .setAutomationName(Configuration.AUTOMATOR2_SERVER)
                .setAppPackage(Configuration.APP_PACKAGE)
                .setAppActivity(Configuration.APP_ACTIVITY)
                .setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(Configuration.COMMAND_TIMEOUT)));
        try {
            AndroidDriver driver = new AndroidDriver(
                    URI.create(Configuration.SERVER_URL).toURL(), options
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    private IOSDriver getIOSDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName(Configuration.DEVICE_NAME)
                .setAutomationName(Configuration.AUTOMATOR2_SERVER)
                .setBundleId(Configuration.APP_ACTIVITY)
                .setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(Configuration.COMMAND_TIMEOUT)));
        try {
            IOSDriver driver = new IOSDriver(
                    URI.create(Configuration.SERVER_URL).toURL(), options
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }
}
