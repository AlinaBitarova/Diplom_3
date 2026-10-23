package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //  Локатор формы Вход
    private static final By loginForm = By.xpath("//div[@class='Auth_login__3hAey' and .//h2[text()='Вход']]");

    // Поля входа
    private static final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Кнопка Войти
    private static final By loginButton = By.xpath(".//*[text()='Войти']");

    // Кнопка Зарегистрироваться
    private static final By registerButton = By.cssSelector("a[href='/register']");

    // Кнопка Восстановить пароль
    private static final By restoreThePasswordButton = By.cssSelector("a[href='/forgot-password']");

    // Окно Восстановить пароль
    private static final By restoreThePasswordWindow = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Восстановление пароля']]");

    // Кнопка Войти окна Всстановить пароль
    private static final By signInAtRestoreThePasswordWindowButton = By.xpath(".//*[text()='Войти']");

    //  Локатор главной страницы
    private static final By mainPage = By.id("root");

    // Кнопка Личный кабинет
    private static final By myAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    // Локаторы личного кабинета
    private final By profileFormContainer = By.xpath(".//div[contains(@class, 'Profile_profile')] | .//form");
    private final By profileName = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By profileEmail = By.xpath(".//label[text()='Логин']/following-sibling::input");

    // Кнопка Выйти
    private final By logOutButton = By.xpath(".//button[contains(text(), 'Выход')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Дождаться, пока страница входа загрузится
    public void waitForLoginPageToLoad() {
        wait.until(visibilityOfElementLocated(loginForm));
    }

    // Ввести данные в поле Email
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    // Ввести данные в поле Пароль
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Нажать на кнопку Войти
    public void clickLogInButton() {
        driver.findElement(loginButton).click();
    }

    // Нажать на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // Нажать на кнопку Восстановить пароль
    public void clickRestoreThePasswordButton() {
        driver.findElement(restoreThePasswordButton).click();
    }

    // Дождаться, пока окно Восстановить пароль загрузится
    public void waitForRestoreThePasswordWindowToLoad() {
        wait.until(visibilityOfElementLocated(restoreThePasswordWindow));
    }

    // Нажать на кнопку Войти в окне Восстановить пароль
    public void clickSignInAtRestoreThePasswordWindowButton() {
        driver.findElement(signInAtRestoreThePasswordWindowButton).click();
    }

    // Войти шагом
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogInButton();
    }

    // Дождаться, пока главная страница загрузится
    public void waitForMainPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage));
    }

    // Дождаться, пока страница профиля загрузится
    public void waitForProfilePageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileFormContainer));
    }

    // Получить данные из поля "Имя" в профиле
    public String getProfileName() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(profileFormContainer));
        return container.findElement(profileName).getAttribute("value");
    }

    // Получить данные из поля "Email" в профиле
    public String getProfileEmail() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(profileFormContainer));
        return container.findElement(profileEmail).getAttribute("value");
    }

    // Проверить, успешен ли вход
    public boolean checkLoginIsSuccessful() {
        waitForMainPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(myAccountButton)).click();
        waitForProfilePageToLoad();
        String actualName = getProfileName();
        String actualEmail = getProfileEmail();
        return actualName != null && !actualName.trim().isEmpty() &&
                actualEmail != null && !actualEmail.trim().isEmpty();
    }

    // Выйти из личного кабинета
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
    }

}
