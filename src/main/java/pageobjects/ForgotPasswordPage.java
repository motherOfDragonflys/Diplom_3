package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;

/**
 * Page Object страницы "Восстановление пароля".
 * <p>
 * Маркер открытия формы и ссылка перехода на страницу входа.
 */
public class ForgotPasswordPage extends HeaderPage {

    // UI-путь для страницы восстановления пароля
    public static final String FORGOT_PASSWORD_UI_PATH = "/forgot-password";

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Заголовок "Восстановление пароля": маркер открытия формы восстановления
    public static final By FORGOT_PASSWORD_FORM =
            By.xpath(".//h2[text()='Восстановление пароля']");

    //__Ссылка "Войти" под формой
    public static final By LOGIN_LINK =
            By.xpath(".//a[@href='/login']");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта форма восстановления пароля")
    public ForgotPasswordPage waitForForgotPasswordForm() {
        waitForUrlContains(FORGOT_PASSWORD_UI_PATH);
        waitForVisible(FORGOT_PASSWORD_FORM);
        return this;
    }

    @Step("Нажимаем на ссылку \"Войти\" под формой восстановления")
    public LoginPage clickLoginLink() {
        clickElementWithWaiting(LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Открываем страницу восстановления пароля")
    public ForgotPasswordPage openForgotPasswordPage() {
        driver.get(Config.pageUrl(FORGOT_PASSWORD_UI_PATH));
        return this;
    }
}
