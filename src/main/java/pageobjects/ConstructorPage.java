package pageobjects;

import io.qameta.allure.Step;
import locators.HeaderLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import java.time.Duration;
import static locators.ConstructorLocators.*;

public class ConstructorPage extends HeaderPage {

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по вкладке \"{tab}\" конструктора с ожиданием")
    public void clickTab(By tab, By activeTab) {
        clickElementWithWaiting(tab);
        waitForVisible(activeTab);
    }

    @Step("Проверка, что вкладка активна")
    public boolean isTabActive(By activeTab) {
        return !driver.findElements(activeTab).isEmpty();
    }

    @Step("Открыта страница \"Конструктор\"")
    public ConstructorPage openConstructor () {
        driver.get(Config.baseUrl());
        // На зеркале стартовый экран, а не конструктор: переходим по ссылке
        waitForClickable(HeaderLocators.CONSTRUCTOR_LINK).click();
        // Признак того, что конструктор отрисован: есть любая активная вкладка
        waitForVisible(ACTIVE_TAB);
        return this;
    }

    // Развернутый клик из-за сложностей с навигацией, обычный и js не проходят
    @Step("Нажимаем кнопку \"Оформить заказ\"")
    public LoginPage clickOrderButton() {
        waitForConstructorLoaded();
        for (int i = 0; i < 3; i++) {
            waitForClickable(ORDER_BUTTON).click();
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.urlContains("/login"));
                return new LoginPage(driver);   // перешли — возвращаем страницу входа
            } catch (TimeoutException e) {
                // не перешли: обработчик ещё не навешан, кликаем снова
            }
        }
        throw new TimeoutException("Клик по \"Оформить заказ\" не привёл к форме входа");
    }

    @Step("Проверяем, что конструктор загружен")
    public ConstructorPage waitForConstructorLoaded() {
        waitForVisible(BUNS_TAB);
        waitForVisible(SAUCES_TAB);
        waitForVisible(FILLINGS_TAB);
        waitForVisible(FIRST_BUN_CARD);
        return this;
    }
}
