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

    private final By productCard = By.xpath("./android.widget.RelativeLayout/android.widget.LinearLayout");
    private final By priceCard = By.xpath("./android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]");
    private final By addToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By productPrice = By.id("com.androidsample.generalstore:id/productPrice");
    private final By productTitle = By.id("com.androidsample.generalstore:id/productName");

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

                addToCartVisibility = addToCartBtns.get(i).isDisplayed();

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

        return allProducts;
    }

}
