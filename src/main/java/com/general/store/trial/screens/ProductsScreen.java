package com.general.store.trial.screens;

import org.openqa.selenium.By;

public class ProductsScreen extends BaseScreen {

    private final By productWidget = By.className("android.widget.LinearLayout");
    private final By addToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By productPrice = By.id("com.androidsample.generalstore:id/productPrice");
    private final By productTitle = By.id("com.androidsample.generalstore:id/productName");

}
