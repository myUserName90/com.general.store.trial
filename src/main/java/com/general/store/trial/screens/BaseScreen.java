package com.general.store.trial.screens;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.general.store.trial.driver.MobileDriverBase.getDriver;
import static com.general.store.trial.helper.WaitHelper.getWait;

@Log4j2
public abstract class BaseScreen {


    private WebElement getElement(By locator) {
        log.info("Getting element: {}", locator);
        return getDriver().findElement(locator);
    }

    private List<WebElement> getElements(By locator) {
        log.info("Getting elements: {}", locator);
        return getDriver().findElements(locator);
    }

    protected void clickElementByText(String text, By locator) {
        List<WebElement> elements = getElements(locator);
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                element.click();
                break;
            }
        }
    }

    protected void click(By locator) {
        getWait().elementVisible(locator);
        log.info("Clicking element: {}", locator);
        getElement(locator).click();
    }

    protected String getText(By locator) {
        log.info("Getting text from element: {}", locator);
        return getElement(locator).getText();
    }

    protected void sendKeys(By locator, String input) {
        getWait().elementVisible(locator);
        log.info("Sending input: {} to element: {}", input, locator);
        getElement(locator).sendKeys(input);
    }

    protected boolean isDisplayed(By locator) {
        try {
            log.info("Checking if element is displayed: {}", locator);
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
