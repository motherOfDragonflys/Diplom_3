package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import java.time.Duration;
import static locators.HeaderLocators.HEADER_ACCOUNT_LINK;
import static locators.LoginPageLocators.*;

public class LoginPage extends HeaderPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем форму входа через \"Личный кабинет\"")
    public LoginPage openLoginPage() {
        driver.get(Config.baseUrl());
        //Приходим на форму Вход
        waitForClickable(HEADER_ACCOUNT_LINK).click();
        return waitForLoginForm();
    }

    @Step("Проверяем, что открыта форма входа")
    public LoginPage waitForLoginForm() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/login"));
        waitForVisible(LOGIN_HEADER);
        return this;
    }

    @Step("Вводим email: {email}")
    public LoginPage setEmail(String email) {
        inputTextInField(EMAIL_FIELD, email);
        return this;
    }

    @Step("Вводим пароль")
    public LoginPage setPassword(String password) {
        inputTextInField(PASSWORD_FIELD, password);
        return this;
    }

    @Step("Нажимаем кнопку \"Войти\"")
    public HeaderPage clickLoginButton() {
        clickElementWithWaiting(LOGIN_BUTTON);
        return new HeaderPage(driver);
    }

    @Step("Нажимаем на ссылку \"Зарегистрироваться\" под формой входа")
    public RegisterPage clickRegisterLink() {
        clickElementWithWaiting(REGISTER_LINK);
        return new RegisterPage(driver);
    }

    @Step("Нажимаем на ссылку \"Восстановить пароль\" под формой входа")
    public ForgotPasswordPage clickForgotPasswordLink() {
        clickElementWithWaiting(FORGOT_PASSWORD_LINK);
        return new ForgotPasswordPage(driver);
    }

    @Step("Заполняем форму входа и нажимаем \"Войти\"")
    public HeaderPage loginAs(String email, String password) {
        return setEmail(email)
                .setPassword(password)
                .clickLoginButton()
                .waitForUserInHeader();
    }
}
