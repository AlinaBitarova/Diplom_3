package org.pageobjects;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;
    protected AdditionalData additionalData;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    @Before
    public void startBrowser() {
        additionalData = new AdditionalData();

        String browser = System.getProperty("browser", "chrome"); // "chrome" — значение по умолчанию

        if (browser.equalsIgnoreCase("chrome")) {
            startBrowserChrome();
        } else if (browser.equalsIgnoreCase("yandex")) {
            startBrowserYandex();
        }

        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    private void startBrowserChrome() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().clearDriverCache();
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    private void startBrowserYandex() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
