package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import static locators.ProfilePageLocators.*;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем, что открыта страница профиля")
    public ProfilePage waitForProfileForm() {
        waitForVisible(PROFILE_INFO_TEXT);
        return this;
    }

    @Step("Нажимаем кнопку \"Выход\"")
    public HeaderPage clickLogoutButton() {
        clickElementWithWaiting(LOGOUT_BUTTON);
        // После выхода стенд перебрасывает на главную (конструктор), сессия закрыта
        return new HeaderPage(driver);
    }
}
