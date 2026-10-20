package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object страницы Вход.
 * <p>
 * Локаторы полей и кнопки формы входа, переходы на регистрацию
 * и восстановление пароля, сценарий авторизации.
 */
public class LoginPage extends HeaderPage {

    // UI-путь для страницы входа
    public static final String LOGIN_UI_PATH = "/login";

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Заголовок формы Вход: маркер, что открыта именно эта страница
    public static final By LOGIN_FORM =
            By.xpath(".//h2[text()='Вход']");

    //__Форма входа:
    // Поле ввода email
    public static final By EMAIL_FIELD =
            By.xpath(".//label[text()='Email']/following-sibling::input");

    // Поле ввода пароля
    public static final By PASSWORD_FIELD =
            By.xpath(".//input[@type='password']");

    // Кнопка Войти
    public static final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта форма входа")
    public LoginPage waitForLoginForm() {
        waitForUrlContains(LOGIN_UI_PATH);
        waitForVisible(LOGIN_FORM);
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
    public ConstructorPage clickLoginButton() {
        clickElementWithWaiting(LOGIN_BUTTON);
        return new ConstructorPage(driver);
    }

    @Step("Заполняем форму входа и нажимаем \"Войти\"")
    public ConstructorPage loginAs(String email, String password) {
        return setEmail(email)
                .setPassword(password)
                .clickLoginButton();
    }
}
