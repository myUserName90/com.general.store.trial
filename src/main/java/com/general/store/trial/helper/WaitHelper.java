package com.general.store.trial.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static com.general.store.trial.driver.MobileDriverBase.getDriver;

public class WaitHelper {
    private static final int TIMEOUT = 30;
    private static final int SHORT_TIMEOUT = 20;
    private static final int LONG_TIMEOUT = 60;
    private static WebDriverWait wait;

    private WaitHelper() {
    }

    public static WaitHelper getWait() {
        return getCustomWait(TIMEOUT);
    }

    public static WaitHelper getLongWait() {
        return getCustomWait(LONG_TIMEOUT);
    }

    public static WaitHelper getShortWait() {
        return getCustomWait(SHORT_TIMEOUT);
    }

    private static WaitHelper getCustomWait(int timeOut) {
        WaitHelper waitHandler = new WaitHelper();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(50));
        return waitHandler;
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            //ignore
        }
    }

    public boolean elementVisible(By element) {
        return wait.until(driver -> driver.findElement(element).isDisplayed());
    }

    public boolean elementClickable(By element) {
        return wait.until(driver -> Objects.equals(driver.findElement(element).getAttribute("clickable"), "true"));
    }

}


