package org.pageobjects;

import io.qameta.allure.Step;
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
    private static final By REGISTRATION_FORM = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Регистрация']]");

    // Поля регистрации
    private static final By NAME_FIELD = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Локатор ошибки некорректного пароля
    private static final By PASSWORD_ERROR_LABEL = By.xpath(".//p[contains(@class, 'input__error') and text()='Некорректный пароль']");

    // Кнопка Зарегистрироваться
    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");

    // Кнопка Войти
    private static final By SIGN_IN_BUTTON = By.xpath(".//*[text()='Войти']");

    //  Локатор формы Вход
    private static final By LOGIN_FORM = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Вход']]");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Wait for Registration page to load")
    public void waitForRegistrationPageToLoad() {
        wait.until(visibilityOfElementLocated(REGISTRATION_FORM));
    }

    @Step("Fill Name field")
    public void setName(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Fill Email field")
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Fill Password field")
    public void setPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Click on Register button")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Click on Sign in button")
    public void clickSignInButton() {
        driver.findElement(SIGN_IN_BUTTON).click();
    }

    @Step("Register in one step")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Check if registration is successful")
    public boolean checkRegistrationIsSuccessful() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FORM));
        return element.isDisplayed();
    }

    @Step("Check if error message is displayed when password is too short")
    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR_LABEL)).isDisplayed();
    }

}
