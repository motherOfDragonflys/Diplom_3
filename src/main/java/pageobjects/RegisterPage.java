package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends HeaderPage {

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Заголовок формы "Регистрация": маркер, что открыта именно эта страница
    public static final By REGISTER_HEADER =
            By.xpath(".//h3[text()='Регистрация']");

    //__Форма регистрации:
    // Поле ввода имени пользователя
    public static final By REGISTER_NAME_INPUT =
            By.xpath(".//input[@name='name']");

    // Поле ввода email
    public static final By REGISTER_EMAIL_INPUT =
            By.xpath(".//input[@type='email']");

    // Поле ввода пароля
    public static final By REGISTER_PASSWORD_INPUT =
            By.xpath(".//input[@type='password']");

    // Кнопка "Зарегистрироваться"
    public static final By REGISTER_BUTTON =
            By.xpath(".//button[text()='Зарегистрироваться']");

    //__Под формой:
    // Маркер появления сообщения об ошибке
    public static final By REGISTER_ERROR_MESSAGE =
            By.xpath(".//form[@name='register']//p[contains(@class, 'text_type_main-default')]");

    // Ссылка "Войти" под формой
    public static final By LOGIN_LINK =
            By.xpath(".//a[text()='Войти']");

//__МЕТОДЫ______________________________________________________________________________________________________________

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
