package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest extends BaseTest {

    private User user;

    @Before
    public void initialSetUp() {
        user = additionalData.getRandomUser();
        additionalData.createUser(user);
        driver.get("https://qa-stellarburgers.education-services.ru");
    }

    @Test
    @Description("Check login from main page (Sign In Into Account Button)")
    public void testCanLoginFromMainPageWithSignInButton() {
        mainPage.clickSignInIntoAccountButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.checkLoginIsSuccessful());
    }

    @Test
    @Description("Check login from main page (My Account Button)")
    public void testCanLoginFromMainPageWithMyAccountButton() {
        mainPage.clickMyAccountButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.checkLoginIsSuccessful());
    }

    @Test
    @Description("Check login from registration page (Sign In Button)")
    public void testCanLoginFromRegistrationPageWithSignInButton() {
        mainPage.clickSignInIntoAccountButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickRegisterButton();
        registrationPage.waitForRegistrationPageToLoad();
        registrationPage.clickSignInButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.checkLoginIsSuccessful());
    }

    @Test
    @Description("Check login from restore the password page (Sign In Button)")
    public void testCanLoginFromRestoreThePasswordPageWithSignInButton() {
        mainPage.clickSignInIntoAccountButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.clickRestoreThePasswordButton();
        loginPage.waitForRestoreThePasswordWindowToLoad();
        loginPage.clickSignInAtRestoreThePasswordWindowButton();
        loginPage.waitForLoginPageToLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.checkLoginIsSuccessful());
    }

    @After
    public void cleanData() {
        if (user != null) {
            additionalData.deleteUser(user);
        }
    }
}