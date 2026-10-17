package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import java.time.Duration;

public class ConstructorPage extends HeaderPage {

//__ЛОКАТОРЫ____________________________________________________________________________________________________________

    //__Вкладки конструктора:
    // Вкладка Булки
    public static final By BUNS_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Булки']]");

    // Вкладка Начинки
    public static final By FILLINGS_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Начинки']]");

    // Вкладка Соусы
    public static final By SAUCES_TAB =
            By.xpath("//div[contains(@class,'tab')][.//span[text()='Соусы']]");

    //__Локаторы активной вкладки конструктора:
    // Активная вкладка Булки
    public static final By BUNS_TAB_ACTIVE =
            By.xpath(
            "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Булки']]");

    // Активная вкладка Начинки
    public static final By FILLINGS_TAB_ACTIVE =
            By.xpath(
            "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Начинки']]");

    // Активная вкладка Соусы
    public static final By SAUCES_TAB_ACTIVE =
            By.xpath(
            "//div[contains(@class,'tab') and contains(@class,'tab_type_current')][.//span[text()='Соусы']]");

    // Булка: как маркер загрузки элементов конструктора
    public static final By FIRST_BUN_CARD =
            By.xpath(".//p[contains(text(),'Краторная булка')]");

    //__Кнопка "Оформить заказ" в конструкторе
    public static final By ORDER_BUTTON =
            By.xpath(".//button[normalize-space()='Оформить заказ']");

//__МЕТОДЫ______________________________________________________________________________________________________________

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
        waitForClickable(CONSTRUCTOR_LINK).click();
        // Признак того, что конструктор отрисован: есть любая булка
        waitForVisible(FIRST_BUN_CARD);
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
