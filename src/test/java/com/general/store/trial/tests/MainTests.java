package com.general.store.trial.tests;

import com.general.store.trial.BaseTest;
import com.general.store.trial.screens.MainScreen;
import org.testng.annotations.Test;

public class MainTests extends BaseTest {

    @Test
    public void mainTest() {
        new MainScreen()
                .selectCountry("Armenia")
                .enterName("John Doe")
                .selectFemaleGender()
                .tapLetsShop();
    }
}
