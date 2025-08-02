package com.general.store.trial.screens;

import org.openqa.selenium.By;

import static com.general.store.trial.driver.MobileDriverBase.getDriver;

public class MainScreen extends BaseScreen {
    private final By countryDropdown = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private final By countryOptions = By.className("android.widget.TextView");
    private final By nameInput = By.className("android.widget.EditText");
    private final By femaleRadioBtn = By.id("com.androidsample.generalstore:id/radioFemale");
    private final By letsShopButton = By.id("com.androidsample.generalstore:id/btnLetsShop");

    public MainScreen selectCountry(String country) {
        click(countryDropdown);
        clickElementByText(country, countryOptions);
        return this;
    }

    public MainScreen enterName(String name) {
        sendKeys(nameInput, name);
        return this;
    }

    public ProductsScreen tapLetsShop() {
        click(letsShopButton);
        return new ProductsScreen();
    }

    public MainScreen selectFemaleGender() {
        click(femaleRadioBtn);
        return this;
    }
}
