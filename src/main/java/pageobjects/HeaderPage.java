package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static locators.HeaderLocators.*;

public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем на ссылку \"Личный кабинет\" в шапке")
    public LoginPage clickAccountLink() {
        clickElementWithWaiting(HEADER_ACCOUNT_LINK);
        // Незалогиненному пользователю стенд показывает форму входа
        return new LoginPage(driver);
    }

    @Step("Ожидаем имя пользователя в шапке вместо ссылки \"Личный кабинет\"")
    public HeaderPage waitForUserInHeader() {
         waitForVisible(HEADER_USER_NAME);
         return this;
    }

    @Step("Получаем имя пользователя в шапке")
    public String getHeaderUserName() {
        return waitForVisible(HEADER_USER_NAME).getText();
    }

}
