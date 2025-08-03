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

    public void clickAddToCartButtonAtIndex(int index) {
        List<WebElement> addToCartButtons;
        int lastSize = 0;

        while (true) {
            addToCartButtons = getElements(addToCartButton);

            if (index < addToCartButtons.size()) {
                break; // The target index is now visible
            }

            // No more buttons are loading after scroll
            if (addToCartButtons.size() == lastSize) {
                throw new IndexOutOfBoundsException("Add to Cart button at index " + index + " is not reachable.");
            }

            lastSize = addToCartButtons.size();
            scroll(ScrollDirection.DOWN);
        }

        WebElement buttonToClick = addToCartButtons.get(index);

        if (buttonToClick.isDisplayed()) {
            buttonToClick.click();
        } else {
            throw new IllegalStateException("Add to Cart button at index " + index + " is not visible.");
        }

        scrollToTop();
    }

    public CartScreen tapCartButton() {
        click(cartButton);
        return new CartScreen();
    }


}
