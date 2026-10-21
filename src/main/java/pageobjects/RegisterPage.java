package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.Config;

/**
 * Page Object страницы "Регистрация".
 * <p>
 * Локаторы полей и кнопки формы регистрации, проверка ошибки
 * валидации пароля, переход на страницу входа.
 */
public class RegisterPage extends HeaderPage {

    // UI-путь для страницы регистрации
    public static final String REGISTER_UI_PATH = "/register";

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Заголовок формы "Регистрация": маркер, что открыта именно эта страница
    public static final By REGISTER_HEADER =
            By.xpath(".//h2[text()='Регистрация']");

    //__Форма регистрации:
    // Поле ввода имени пользователя
    public static final By REGISTER_NAME_INPUT =
            By.xpath(".//label[text()='Имя']/following-sibling::input");

    // Поле ввода email
    public static final By REGISTER_EMAIL_INPUT =
            By.xpath(".//label[text()='Email']/following-sibling::input");

    // Поле ввода пароля
    public static final By REGISTER_PASSWORD_INPUT =
            By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // Кнопка "Зарегистрироваться"
    public static final By REGISTER_BUTTON =
            By.xpath(".//button[text()='Зарегистрироваться']");

    //__Под формой:
    // Маркер появления сообщения об ошибке
    public static final By REGISTER_ERROR_MESSAGE =
            By.xpath(".//p[contains(@class, 'input__error')]");

    // Ссылка "Войти" под формой
    public static final By LOGIN_LINK =
            By.xpath(".//a[text()='Войти']");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта форма регистрации")
    public RegisterPage waitForRegisterForm() {
        waitForUrlContains(REGISTER_UI_PATH);
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
    public LoginPage clickRegisterButton() {
        clickElementWithWaiting(REGISTER_BUTTON);
        // При успехе, возвращаем LoginPage,
        // при ошибке валидации остаёмся на форме регистрации
        return new LoginPage(driver);
    }

    @Step("Проверяем появление ошибки валидации пароля")
    public boolean isPasswordErrorVisible() {
        try {
            waitForVisible(REGISTER_ERROR_MESSAGE);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажимаем на ссылку \"Войти\" под формой регистрации")
    public LoginPage clickLoginLink() {
        clickElementWithWaiting(LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Открываем страницу регистрации")
    public RegisterPage openRegisterPage() {
        driver.get(Config.pageUrl(REGISTER_UI_PATH));
        return this;
    }
}
