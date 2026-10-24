package org.pageobjects;

import io.qameta.allure.Step;
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
    private static final By LOGIN_FORM = By.xpath("//div[@class='Auth_login__3hAey' and .//h2[text()='Вход']]");

    // Поля входа
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/following-sibling::input");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Кнопка Войти
    private static final By LOGIN_BUTTON = By.xpath(".//*[text()='Войти']");

    // Кнопка Зарегистрироваться
    private static final By REGISTER_BUTTON = By.cssSelector("a[href='/register']");

    // Кнопка Восстановить пароль
    private static final By RESTORE_THE_PASSWORD_BUTTON = By.cssSelector("a[href='/forgot-password']");

    // Окно Восстановить пароль
    private static final By RESTORE_THE_PASSWORD_WINDOW = By.xpath("//div[contains(@class, 'Auth_login') and .//h2[text()='Восстановление пароля']]");

    // Кнопка Войти окна Всстановить пароль
    private static final By SIGN_IN_AT_RESTORE_THE_PASSWORD_WINDOW_BUTTON = By.xpath(".//*[text()='Войти']");

    //  Локатор главной страницы
    private static final By MAIN_PAGE = By.id("root");

    // Кнопка Личный кабинет
    private static final By MY_ACCOUNT_BUTTON = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    // Локаторы личного кабинета
    private final By PROFILE_FORM_CONTAINER = By.xpath(".//div[contains(@class, 'Profile_profile')] | .//form");
    private final By PROFILE_NAME = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By PROFILE_EMAIL = By.xpath(".//label[text()='Логин']/following-sibling::input");

    // Кнопка Выйти
    private final By LOGOUT_BUTTON = By.xpath(".//button[contains(text(), 'Выход')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Wait for Login page to load")
    public void waitForLoginPageToLoad() {
        wait.until(visibilityOfElementLocated(LOGIN_FORM));
    }

    @Step("Fill Email field")
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Fill Password field")
    public void setPassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Click on Log in button")
    public void clickLogInButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Click on Register button")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Click on Restore The Password button")
    public void clickRestoreThePasswordButton() {
        driver.findElement(RESTORE_THE_PASSWORD_BUTTON).click();
    }

    @Step("Wait for Restore The Password Window to load")
    public void waitForRestoreThePasswordWindowToLoad() {
        wait.until(visibilityOfElementLocated(RESTORE_THE_PASSWORD_WINDOW));
    }

    @Step("Click on Sign in button at Restore The Password Window")
    public void clickSignInAtRestoreThePasswordWindowButton() {
        driver.findElement(SIGN_IN_AT_RESTORE_THE_PASSWORD_WINDOW_BUTTON).click();
    }

    @Step("Log in in one step")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogInButton();
    }

    @Step("Wait for the Main page to load")
    public void waitForMainPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_PAGE));
    }

    @Step("Wait for the Profile page to load")
    public void waitForProfilePageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_FORM_CONTAINER));
    }

    @Step("Get data from Name field in Profile")
    public String getProfileName() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_FORM_CONTAINER));
        return container.findElement(PROFILE_NAME).getAttribute("value");
    }

    @Step("Get data from Email field in Profile")
    public String getProfileEmail() {
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(PROFILE_FORM_CONTAINER));
        return container.findElement(PROFILE_EMAIL).getAttribute("value");
    }

    @Step("Check if logging in is successful")
    public boolean checkLoginIsSuccessful() {
        waitForMainPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_BUTTON)).click();
        waitForProfilePageToLoad();
        String actualName = getProfileName();
        String actualEmail = getProfileEmail();
        return actualName != null && !actualName.trim().isEmpty() &&
                actualEmail != null && !actualEmail.trim().isEmpty();
    }

    @Step("Log out")
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_BUTTON)).click();
    }

}
