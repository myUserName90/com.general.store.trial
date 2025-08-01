package com.general.store.trial.driver;

import com.general.store.trial.configuration.Configuration;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class MobileDriverFactory {

    private final DesiredCapabilities capabilities;

    public static MobileDriverFactory getInstance() {
        return new MobileDriverFactory();
    }

    private MobileDriverFactory() {
        capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", Configuration.PLATFORM);
        capabilities.setCapability("deviceName", Configuration.DEVICE_NAME);
        capabilities.setCapability("automationName", Configuration.AUTOMATOR2_SERVER);
        capabilities.setCapability("appPackage", Configuration.APP_PACKAGE);
        capabilities.setCapability("appActivity", Configuration.APP_ACTIVITY);
        capabilities.setCapability("newCommandTimeout", Configuration.COMMAND_TIMEOUT);
    }

    public AndroidDriver getDriverObject() {
        try {
            AndroidDriver driver = new AndroidDriver(
                    new URL(Configuration.SERVER_URL), capabilities
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to initialize Appium driver", e);
        }
    }
}
