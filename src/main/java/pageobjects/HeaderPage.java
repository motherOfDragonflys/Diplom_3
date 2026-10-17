package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends BasePage {

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    // Ссылка Личный кабинет в шапке
    public static final By HEADER_ACCOUNT_LINK =
            By.xpath(".//a[@href='/profile']");

    // Имя пользователя в шапке: текст ссылки профиля вместо Личного кабинета
    public static final By HEADER_USER_NAME =
            By.xpath(".//a[@href='/profile']//p[not(text()='Личный кабинет')]");

    // Ссылка "Конструктор" в шапке
    public static final By CONSTRUCTOR_LINK =
            By.xpath(".//p[text()='Конструктор']");

//__МЕТОДЫ______________________________________________________________________________________________________________

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
