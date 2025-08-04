package com.general.store.trial.tests;

import com.general.store.trial.BaseTest;
import com.general.store.trial.model.ProductItem;
import com.general.store.trial.screens.CartScreen;
import com.general.store.trial.screens.MainScreen;
import com.general.store.trial.screens.ProductsScreen;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CartTests extends BaseTest {

    @Test
    public void cartTest() {
        int position1 = 0, position3 = 2, position6 = 5;
        ProductsScreen productsScreen = new MainScreen()
                .selectCountry("Armenia")
                .enterName("My Name")
                .selectFemaleGender()
                .tapLetsShop();

        List<ProductItem> productItems = productsScreen.getAllProductItemsWithScroll();
        String firstProductName = productItems.getFirst().getName();
        double firstProductPrice = Double.parseDouble(productItems.getFirst().getPrice().replaceAll("[^\\d.]", ""));

        String thirdProductName = productItems.get(position3).getName();
        double thirdProductPrice = Double.parseDouble(productItems.get(position3).getPrice().replaceAll("[^\\d.]", ""));

        String sixthProductName = productItems.get(position6).getName();
        double sixthProductPrice = Double.parseDouble(productItems.get(position6).getPrice().replaceAll("[^\\d.]", ""));

        double totalExpectedPrice = firstProductPrice + thirdProductPrice + sixthProductPrice;

        productsScreen.clickAddToCartButtonsAtIndices(List.of(position1, position3, position6));
        CartScreen cartScreen = productsScreen.tapCartButton();

        List<String> productNames = cartScreen.getProductNames();
        double totalPrice = cartScreen.getTotalPrice();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productNames.size(), 3, "Product count mismatch");
        softAssert.assertTrue(productNames.contains(firstProductName), "Product name mismatch");
        softAssert.assertTrue(productNames.contains(thirdProductName), "Product name mismatch");
        softAssert.assertTrue(productNames.contains(sixthProductName), "Product name mismatch");
        softAssert.assertEquals(totalPrice, totalExpectedPrice, "Total price mismatch");
        softAssert.assertAll();

    }
}