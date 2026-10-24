package org.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Кнопка Личный кабинет
    private static final By MY_ACCOUNT_BUTTON = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    // Кнопка Войти в аккаунт
    private static final By SIGN_IN_INTO_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");

    // Разделы конструктора
    private static final By BUN_TAB_HEADER = By.xpath(".//span[text()='Булки']");
    private static final By SAUCE_TAB_HEADER = By.xpath(".//span[text()='Соусы']");
    private static final By FILLING_TAB_HEADER = By.xpath(".//span[text()='Начинки']");

    // Локаторы родительских контейнеров вкладок (чтобы проверить, что они активны)
    private static final By BUN_TAB_CONTAINER = By.xpath(".//span[text()='Булки']/parent::div");
    private static final By SAUCE_TAB_CONTAINER = By.xpath(".//span[text()='Соусы']/parent::div");
    private static final By FILLING_TAB_CONTAINER = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Click on My Account button")
    public void clickMyAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_BUTTON)).click();
    }

    @Step("Click on Sign in Into Account button")
    public void clickSignInIntoAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_INTO_ACCOUNT_BUTTON)).click();
    }

    @Step("Click on Bun tab")
    public void clickBunTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(BUN_TAB_HEADER)).click();
    }

    @Step("Click on Sauce tab")
    public void clickSauceTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(SAUCE_TAB_HEADER)).click();
    }

    @Step("Click on Filling tab")
    public void clickFillingTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(FILLING_TAB_HEADER)).click();
    }

    @Step("Check that Bun tab is active")
    public boolean isBunTabActive() {
        return wait.until(ExpectedConditions.attributeContains(BUN_TAB_CONTAINER, "class", "current"));
    }

    @Step("Check that Sauce tab is active")
    public boolean isSauceTabActive() {
        return wait.until(ExpectedConditions.attributeContains(SAUCE_TAB_CONTAINER, "class", "current"));
    }

    @Step("Check that Filling tab is active")
    public boolean isFillingTabActive() {
        return wait.until(ExpectedConditions.attributeContains(FILLING_TAB_CONTAINER, "class", "current"));
    }
}
