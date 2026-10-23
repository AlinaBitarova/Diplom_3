package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;

public class RegistrationTest {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private AdditionalData additionalData;
    private User user;

    @Before
    public void startBrowser() {
        additionalData = new AdditionalData();

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
        goToRegistrationPage();

    }

    public void startBrowserYandex() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        goToRegistrationPage();
    }

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
        registrationPage.waitForRegistrationPageToLoad();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registrationPage.checkRegistrationIsSuccessful());
    }

    @Test
    @Description("Check registration with invalid password throws error")
    public void testCantRegisterWithInvalidPassword() {
        user = additionalData.getRandomUser();
        user.setPassword("123");
        registrationPage.waitForRegistrationPageToLoad();
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registrationPage.isPasswordErrorDisplayed());
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