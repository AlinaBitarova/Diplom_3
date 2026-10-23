package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Кнопка Личный кабинет
    private static final By myAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    // Кнопка Войти в аккаунт
    private static final By signInIntoAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    // Разделы конструктора
    private static final By bunTabHeader = By.xpath(".//span[text()='Булки']");
    private static final By sauceTabHeader = By.xpath(".//span[text()='Соусы']");
    private static final By fillingTabHeader = By.xpath(".//span[text()='Начинки']");

    // Локаторы родительских контейнеров вкладок (чтобы проверить, что они активны)
    private static final By bunTabContainer = By.xpath(".//span[text()='Булки']/parent::div");
    private static final By sauceTabContainer = By.xpath(".//span[text()='Соусы']/parent::div");
    private static final By fillingTabContainer = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Нажать на кнопку Личный кабинет
    public void clickMyAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountButton)).click();
    }

    // Нажать на кнопку Войти в аккаунт
    public void clickSignInIntoAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInIntoAccountButton)).click();
    }

    // Нажать на раздел Булки
    public void clickBunTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(bunTabHeader)).click();
    }

    // Нажать на раздел Соусы
    public void clickSauceTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceTabHeader)).click();
    }

    // Нажать на раздел Начинки
    public void clickFillingTabHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingTabHeader)).click();
    }

    // Проверить, что вкладка Булки активна
    public boolean isBunTabActive() {
        return wait.until(ExpectedConditions.attributeContains(bunTabContainer, "class", "current"));
    }

    // Проверить, что вкладка Соусы активна
    public boolean isSauceTabActive() {
        return wait.until(ExpectedConditions.attributeContains(sauceTabContainer, "class", "current"));
    }

    // Проверить, что вкладка Начинки активна
    public boolean isFillingTabActive() {
        return wait.until(ExpectedConditions.attributeContains(fillingTabContainer, "class", "current"));
    }
}
