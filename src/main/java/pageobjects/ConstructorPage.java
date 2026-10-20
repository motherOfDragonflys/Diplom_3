package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import java.time.Duration;

import static pageobjects.LoginPage.LOGIN_UI_PATH;

/**
 * Page Object страницы "Конструктор".
 * <p>
 * Локаторы элементов и шаги для работы с вкладками и кнопками конструктора.
 */
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

    //__Кнопка "Войти в аккаунт" в конструкторе
    public static final By LOGIN_BUTTON =
            By.xpath(".//button[normalize-space()='Войти в аккаунт']");

    //__Кнопка "Оформить заказ" в конструкторе
    public static final By ORDER_BUTTON =
            By.xpath(".//button[contains(normalize-space(.), 'Оформить заказ')]");

//__МЕТОДЫ______________________________________________________________________________________________________________

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    // "unused" потому, что имя вкладки только для текста шага Step
    @SuppressWarnings("unused")
    @Step("Кликаем по вкладке \"{name}\" конструктора и ждем активации")
    public void clickTab(String name, By tab, By activeTab) {
        clickElementWithWaiting(tab);
        waitForVisible(activeTab);
    }

    // "unused" потому, что имя вкладки только для текста шага Step
    @SuppressWarnings("unused")
    @Step("Проверяем, что вкладка \"{name}\" активна")
    public boolean isTabActive(String name, By activeTab) {
        return !driver.findElements(activeTab).isEmpty();
    }

    @Step("Открываем страницу \"Конструктор\"")
    public ConstructorPage openConstructor() {
        driver.get(Config.baseUrl());
        // Клик по вкладке конструктора
        waitForClickable(CONSTRUCTOR_LINK).click();
        // Признак того, что конструктор отрисован: есть любая булка
        waitForVisible(FIRST_BUN_CARD);
        return this;
    }

    // Развернутый клик из-за сложностей с навигацией, обычный и js не проходят
    @Step("Нажимаем кнопку \"Войти в аккаунт\"")
    public LoginPage clickLoginButton() {
        // Сколько раз пересылаем клик, если страница не сменилась
        final int clickAttempts = 3;
        // Сколько ждём смены URL после каждого клика, сек
        final int urlChangeTimeout = 5;

        for (int i = 0; i < clickAttempts; i++) {
            waitForClickable(LOGIN_BUTTON).click();
            try {
                new WebDriverWait(driver, Duration.ofSeconds(urlChangeTimeout))
                        .until(ExpectedConditions.urlContains(LOGIN_UI_PATH));
                return new LoginPage(driver);   // перешли? возвращаем страницу входа
            } catch (TimeoutException e) {
                // не перешли: еще обрабатываемся, кликаем снова
            }
        }
        throw new TimeoutException("Клик по \"Войти в аккаунт\" не привёл к форме входа");
    }

    @Step("Проверяем, что на странице конструктора отображается кнопка \"Оформить заказ\"")
    public boolean isOrderButtonVisible() {
        try {
            waitForVisible(ORDER_BUTTON);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
