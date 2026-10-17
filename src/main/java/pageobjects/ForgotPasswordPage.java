package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Заголовок "Восстановление пароля": маркер открытия формы восстановления
    public static final By FORGOT_PASSWORD_HEADER =
            By.xpath(".//h3[text()='Восстановление пароля']");

    //__Ссылка "Войти" под формой
    public static final By LOGIN_LINK_IN_FORGOT_PAGE =
            By.xpath(".//a[@href='/login']");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта форма восстановления пароля")
    public ForgotPasswordPage waitForForgotPasswordForm() {
        waitForVisible(FORGOT_PASSWORD_HEADER);
        return this;
    }

    @Step("Нажимаем на ссылку \"Войти\" под формой восстановления")
    public LoginPage clickLoginLink() {
        clickElementWithWaiting(LOGIN_LINK_IN_FORGOT_PAGE);
        return new LoginPage(driver);
    }
}
