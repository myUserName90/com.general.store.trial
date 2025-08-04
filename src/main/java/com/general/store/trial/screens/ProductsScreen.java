package com.general.store.trial.screens;

import com.general.store.trial.enums.ScrollDirection;
import com.general.store.trial.model.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductsScreen extends BaseScreen {

    private final By addToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By productPrice = By.id("com.androidsample.generalstore:id/productPrice");
    private final By productTitle = By.id("com.androidsample.generalstore:id/productName");
    private final By cartButton = By.id("com.androidsample.generalstore:id/appbar_btn_cart");

    public List<ProductItem> getAllProductItemsWithScroll() {
        List<ProductItem> allProducts = new ArrayList<>();
        Set<String> seenProductNames = new HashSet<>();
        String lastItemName = "";

        while (true) {
            List<WebElement> productTitles = getElements(productTitle);
            List<WebElement> productPrices = getElements(productPrice);
            List<WebElement> addToCartBtns = getElements(addToCartButton);

            for (int i = 0; i < productTitles.size(); i++) {
                WebElement productTitle = productTitles.get(i);
                WebElement productPrice = productPrices.get(i);
                String name = "";
                String price = "";
                boolean addToCartVisibility = false;

                try {
                    name = productTitle.getText();
                } catch (NoSuchElementException ignored) {
                }

                if (name.isBlank() || !seenProductNames.add(name)) continue;

                try {
                    price = productPrice.getText();
                } catch (NoSuchElementException ignored) {
                }

                addToCartVisibility = isDisplayed(addToCartBtns.get(i));

                allProducts.add(new ProductItem(name, price, addToCartVisibility));
            }

            // End condition check
            List<WebElement> names = getElements(productTitle);
            if (names.isEmpty()) break;

            String currentLast = names.getLast().getText();
            if (lastItemName.equals(currentLast)) break;

            lastItemName = currentLast;
            scroll(ScrollDirection.DOWN);
        }
        scrollToTop();
        return allProducts;
    }

    public void clickAddToCartButtonsAtIndices(List<Integer> indices) {
        Set<Integer> targetIndices = new HashSet<>(indices);
        Set<Integer> clickedIndices = new HashSet<>();              // track which ones we already clicked
        List<WebElement> allTrackedButtons = new ArrayList<>();     // global list of seen buttons

        int lastTrackedCount = -1;

        while (true) {
            List<WebElement> visibleButtons = getElements(addToCartButton);
            // Track only new buttons
            for (WebElement button : visibleButtons) {
                if (!allTrackedButtons.contains(button)) {
                    allTrackedButtons.add(button);
                }
            }
            // Try to click any button from targetIndices that is now visible
            for (int i = 0; i < allTrackedButtons.size(); i++) {
                if (targetIndices.contains(i) && !clickedIndices.contains(i)) {
                    try {
                        WebElement button = allTrackedButtons.get(i);
                        if (button.isDisplayed() && button.isEnabled()) {
                            button.click();
                            clickedIndices.add(i);
                        }
                    } catch (Exception e) {
                        System.out.println("Could not click index " + i + ": " + e.getMessage());
                    }
                }
            }
            // Break if all desired indices have been clicked
            if (clickedIndices.containsAll(targetIndices)) {
                break;
            }
            // No new buttons found after scroll — stop
            if (allTrackedButtons.size() == lastTrackedCount) {
                System.out.println("Reached end of list. Unfound indices: " +
                        targetIndices.stream().filter(i -> !clickedIndices.contains(i)).toList());
                break;
            }
            lastTrackedCount = allTrackedButtons.size();
            scroll(ScrollDirection.DOWN);
        }

        scrollToTop();
    }


    public CartScreen tapCartButton() {
        click(cartButton);
        return new CartScreen();
    }


}
