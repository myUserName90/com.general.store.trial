package com.general.store.trial;

import com.general.store.trial.driver.MobileDriverBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.general.store.trial.driver.MobileDriverBase.getDriver;

public class BaseTest {
    @BeforeTest
    public void setup() {
        MobileDriverBase.instantiateDriverObject();
    }

    @AfterTest
    public void tearDown() {
        MobileDriverBase.closeDriverObjects();
    }
}
