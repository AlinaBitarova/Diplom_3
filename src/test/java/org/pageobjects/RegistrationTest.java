package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest{

    private User user;

    public void goToRegistrationPage() {
        driver.manage().window().maximize();
        driver.get("https://qa-stellarburgers.education-services.ru");
        mainPage = new MainPage(driver);
        mainPage.clickSignInIntoAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.waitForLoginPageToLoad();
        loginPage.clickRegisterButton();
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Description("Check registration with valid data is successful")
    public void testCanRegisterWithValidData() {
        user = additionalData.getRandomUser();
        goToRegistrationPage();
        registrationPage.waitForRegistrationPageToLoad();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registrationPage.checkRegistrationIsSuccessful());
    }

    @Test
    @Description("Check registration with invalid password throws error")
    public void testCantRegisterWithInvalidPassword() {
        user = additionalData.getRandomUser();
        user.setPassword("123");
        goToRegistrationPage();
        registrationPage.waitForRegistrationPageToLoad();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registrationPage.isPasswordErrorDisplayed());
    }

    @After
    public void cleanData() {
        if (user != null) {
            additionalData.deleteUser(user);
        }
    }
}