package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private AdditionalData additionalData;
    private User user;

    @Before
    public void startBrowser() {
        additionalData = new AdditionalData();
        user = additionalData.getRandomUser();
        additionalData.createUser(user);

        String browser = System.getProperty("browser", "chrome"); // "chrome" — значение по умолчанию

        if (browser.equalsIgnoreCase("chrome")) {
            startBrowserChrome();
        } else if (browser.equalsIgnoreCase("yandex")) {
            startBrowserYandex();
        }
    }

    public void startBrowserChrome() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().clearDriverCache();
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        initialSetUp();
    }

    public void startBrowserYandex() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        initialSetUp();
    }

    public void initialSetUp() {
        driver.manage().window().maximize();
        driver.get("https://qa-stellarburgers.education-services.ru");
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
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
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (user != null) {
            additionalData.deleteUser(user);
        }
    }

}