package com.general.store.trial.tests;

import com.general.store.trial.BaseTest;
import com.general.store.trial.model.ProductItem;
import com.general.store.trial.screens.MainScreen;
import com.general.store.trial.screens.ProductsScreen;
import org.testng.annotations.Test;

import java.util.List;

public class CartTests extends BaseTest {

    @Test
    public void cartTest() {
        ProductsScreen productsScreen = new MainScreen()
                .selectCountry("Armenia")
                .enterName("My Name")
                .selectFemaleGender()
                .tapLetsShop();

        List<ProductItem> productItems = productsScreen.getAllProductItemsWithScroll();
        String firstProductName = productItems.getFirst().getName();
        double firstProductPrice = Double.parseDouble(productItems.getFirst().getPrice());

        String thirdProductName = productItems.get(2).getName();
        double thirdProductPrice = Double.parseDouble(productItems.get(2).getPrice());

        String sixthProductName = productItems.get(5).getName();
        double sixthProductPrice = Double.parseDouble(productItems.get(2).getPrice());

        double totalPrice = firstProductPrice + thirdProductPrice + sixthProductPrice;

        System.out.println("Total price: " + totalPrice);
    }
}