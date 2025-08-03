package com.general.store.trial.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartScreen extends BaseScreen {

    private final By totalPrice = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private final By productName = By.id("com.androidsample.generalstore:id/productName");


    public double getTotalPrice() {
        return Double.parseDouble(getElement(totalPrice).getText().replaceAll("[^\\d.]", ""));
    }

    public List<String> getProductNames() {
        return getElements(productName).stream().map(WebElement::getText).toList();
    }


}
