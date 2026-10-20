package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;

/**
 * Page Object шапки сайта.
 * <p>
 * Общие для всех страниц элементы шапки: ссылки "Личный кабинет"
 * и Конструктор. Унаследована всеми остальными страницами.
 */
public class HeaderPage extends BasePage {

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    // Ссылка Личный кабинет в шапке
    public static final By PROFILE_LINK =
            By.xpath(".//a[@href='/account']");

    // Ссылка Конструктор в шапке
    public static final By CONSTRUCTOR_LINK =
            By.xpath(".//p[text()='Конструктор']");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем форму входа через \"Личный кабинет\"")
    public LoginPage openLoginPageOnHeaderLink() {
        driver.get(Config.baseUrl());
        waitForClickable(PROFILE_LINK).click();
        return new LoginPage(driver);
    }
}
