package org.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //  Локатор формы регистрации
    private static final By registrationForm = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Регистрация']]");

    // Поля регистрации
    private static final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private static final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Локатор ошибки некорректного пароля
    private static final By passwordErrorLabel = By.xpath(".//p[contains(@class, 'input__error') and text()='Некорректный пароль']");

    // Кнопка Зарегистрироваться
    private static final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Кнопка Войти
    private static final By signInButton = By.xpath(".//*[text()='Войти']");

    //  Локатор формы Вход
    private static final By loginForm = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Вход']]");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Дождаться, пока страница загрузится
    public void waitForRegistrationPageToLoad() {
        wait.until(visibilityOfElementLocated(registrationForm));
    }

    // Ввести данные в поле Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // Ввести данные в поле Email
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    // Ввести данные в поле Пароль
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Нажать на кнопку Зарегистрироваться
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // Нажать на кнопку Войти
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    // Зарегистрироваться шагом
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    // Проверить, успешна ли регистрация
    public boolean checkRegistrationIsSuccessful() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
        return element.isDisplayed();
    }

    // Проверить, отображается ли ошибка "Некорректный пароль", если пароль короткий
    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorLabel)).isDisplayed();
    }

}
