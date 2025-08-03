package com.general.store.trial.screens;

import com.general.store.trial.enums.ScrollDirection;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.*;

import static com.general.store.trial.driver.MobileDriverBase.getDriver;
import static com.general.store.trial.enums.ScrollDirection.DOWN;
import static com.general.store.trial.helper.WaitHelper.getWait;

@Log4j2
public abstract class BaseScreen {


    protected WebElement getElement(By locator) {
        log.info("Getting element: {}", locator);
        return getDriver().findElement(locator);
    }

    protected List<WebElement> getElements(By locator) {
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

    protected boolean isDisplayed(WebElement element) {
        try {
            log.info("Checking if element is displayed: {}", element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void scroll(ScrollDirection direction) {
        Dimension size = getDriver().manage().window().getSize();
        int x = size.width / 2;

        int startY, endY;
        if (direction == ScrollDirection.UP) {
            startY = (int) (size.height * 0.2);
            endY = (int) (size.height * 0.8);
        } else {
            startY = (int) (size.height * 0.8);
            endY = (int) (size.height * 0.2);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getDriver().perform(Collections.singletonList(swipe));
    }

    protected void scrollToTop() {
        String previousPageSource = "";
        int unchangedCount = 0;

        while (unchangedCount < 2) {
            String currentPageSource = getDriver().getPageSource();

            assert currentPageSource != null;
            if (currentPageSource.equals(previousPageSource)) {
                unchangedCount++;
            } else {
                unchangedCount = 0;
            }

            previousPageSource = currentPageSource;
            scroll(ScrollDirection.UP);
        }
    }


    protected List<String> collectElementsTextWithScroll(By locator) {
        List<String> collected = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        String lastItem = "";

        while (true) {
            List<WebElement> elements = getElements(locator);
            for (WebElement element : elements) {
                String text = element.getText();
                if (seen.add(text)) {
                    collected.add(text);
                }
            }

            String currentLast = elements.getLast().getText();
            if (lastItem.equals(currentLast)) break;

            lastItem = currentLast;
            scroll(DOWN);
        }
        scrollToTop();

        return collected;
    }

    protected int countAllVisibleElementsWithScroll(By locator) {
        List<String> seenPositions = new ArrayList<>();
        String lastLocation = "";

        while (true) {
            List<WebElement> elements = getElements(locator);

            for (WebElement e : elements) {
                seenPositions.add(e.getLocation().toString());
            }

            String currentLast = elements.getLast().getLocation().toString();
            if (currentLast.equals(lastLocation)) break;

            lastLocation = currentLast;
            scroll(DOWN);
        }
        return seenPositions.size();
    }
}
