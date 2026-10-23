package org.pageobjects;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void startBrowser() {
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
    }

    @Test
    @Description("Check clicking on Sauces tab makes it active")
    public void testSwitchingToSaucesTabIsSuccessful() {
        mainPage.clickSauceTabHeader();
        assertTrue("Sauces tab is not active", mainPage.isSauceTabActive());
    }

    @Test
    @Description("Check clicking on Filling tab makes it active")
    public void testSwitchingToFillingTabIsSuccessful() {
        mainPage.clickFillingTabHeader();
        assertTrue("Filling tab is not active", mainPage.isFillingTabActive());
    }

    @Test
    @Description("Check switching to Buns after another tab")
    public void testSwitchingToBunsTabIsSuccessful() {
        mainPage.clickSauceTabHeader();
        assertTrue(mainPage.isSauceTabActive());
        mainPage.clickBunTabHeader();
        assertTrue("Bun tab is not active", mainPage.isBunTabActive());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}