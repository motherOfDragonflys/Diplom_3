package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static locators.RegisterPageLocators.*;

public class RegisterPage extends HeaderPage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта форма регистрации")
    public RegisterPage waitForRegisterForm() {
        waitForVisible(REGISTER_HEADER);
        return this;
    }

    @Step("Вводим имя: {name}")
    public RegisterPage setName(String name) {
        inputTextInField(REGISTER_NAME_INPUT, name);
        return this;
    }

    @Step("Вводим email: {email}")
    public RegisterPage setEmail(String email) {
        inputTextInField(REGISTER_EMAIL_INPUT, email);
        return this;
    }

    @Step("Вводим пароль")
    public RegisterPage setPassword(String password) {
        inputTextInField(REGISTER_PASSWORD_INPUT, password);
        return this;
    }

    @Step("Нажимаем кнопку \"Зарегистрироваться\"")
    public HeaderPage clickRegisterButton() {
        clickElementWithWaiting(REGISTER_BUTTON);
        // Успешная регистрация автологинит и перебрасывает на главную
        return new HeaderPage(driver);
    }

    @Step("Проверяем, что появилась ошибка")
    public void waitForError() {
        // Факт ошибки, без анализа текста
        waitForVisible(REGISTER_ERROR_MESSAGE);
    }

    @Step("Нажимаем на ссылку \"Войти\" под формой регистрации")
    public LoginPage clickLoginLink() {
        clickElementWithWaiting(LOGIN_LINK);
        return new LoginPage(driver);
    }

}
