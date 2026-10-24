package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Check clicking on Sauces tab makes it active")
    public void testSwitchingToSaucesTabIsSuccessful() {
        driver.get("https://qa-stellarburgers.education-services.ru");
        mainPage.clickSauceTabHeader();
        assertTrue("Sauces tab is not active", mainPage.isSauceTabActive());
    }

    @Test
    @Description("Check clicking on Filling tab makes it active")
    public void testSwitchingToFillingTabIsSuccessful() {
        driver.get("https://qa-stellarburgers.education-services.ru");
        mainPage.clickFillingTabHeader();
        assertTrue("Filling tab is not active", mainPage.isFillingTabActive());
    }

    @Test
    @Description("Check switching to Buns after another tab")
    public void testSwitchingToBunsTabIsSuccessful() {
        driver.get("https://qa-stellarburgers.education-services.ru");
        mainPage.clickSauceTabHeader();
        assertTrue(mainPage.isSauceTabActive());
        mainPage.clickBunTabHeader();
        assertTrue("Bun tab is not active", mainPage.isBunTabActive());
    }
}