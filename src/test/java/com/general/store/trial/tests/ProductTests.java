package com.general.store.trial.tests;

import com.general.store.trial.BaseTest;
import com.general.store.trial.model.ProductItem;
import com.general.store.trial.screens.MainScreen;
import com.general.store.trial.screens.ProductsScreen;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductTests extends BaseTest {

    @Test
    public void testProductList() {
        ProductsScreen productsScreen = new MainScreen()
                .selectCountry("Armenia")
                .enterName("My Name")
                .selectFemaleGender()
                .tapLetsShop();
        List<ProductItem> allProducts = productsScreen.getAllProductItemsWithScroll();

        List<String> expectedNames = List.of(
                "Air Jordan 4 Retro", "Air Jordan 1 Mid SE", "Nike Blazer Mid '77",
                "Converse All Star", "Air Jordan 9 Retro", "Jordan 6 Rings",
                "Jordan Lift Off", "Le Bron Soldier 11", "PG 3", "Nike SFB Jungle",
                "Nike SFB Gen 2"
        );
        List<String> expectedPrices = List.of(
                "$160.97", "$135.0", "$110.0", "$55.0", "$170.97",
                "$165.0", "$115.0", "$130.0", "$110.0", "$116.97", "$150.0"
        );

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(allProducts.size(), 11, "Add to cart button count mismatch");
        for (int i = 0; i < allProducts.size(); i++) {
            ProductItem product = allProducts.get(i);
            softAssert.assertTrue(product.isAddToCartButtonVisible(), "Add to cart button missing");
            softAssert.assertEquals(product.getName(), expectedNames.get(i), "Product name mismatch");
            softAssert.assertEquals(product.getPrice(), expectedPrices.get(i), "Product price mismatch");
        }
        softAssert.assertAll();
    }
}
