package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static locators.ForgotPasswordPageLocators.*;

public class ForgotPasswordPage extends BasePage {

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
