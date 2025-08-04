package com.general.store.trial;

import com.general.store.trial.driver.MobileDriverBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.general.store.trial.driver.MobileDriverBase.closeDriverObjects;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        closeDriverObjects();
        MobileDriverBase.instantiateDriverObject();
    }

    @AfterMethod
    public void tearDown() {
        closeDriverObjects();
    }


}
