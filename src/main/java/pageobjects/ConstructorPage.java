package pageobjects;

import io.qameta.allure.Step;
import locators.HeaderLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;

import static locators.ConstructorLocators.*;

public class ConstructorPage extends BasePage {

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    // Хелпер: клик по вкладке + ожидание, пока маркер активности переедет на неё
    @Step("Клик по вкладке конструктора")
    public void clickTab(By tab, By activeTab) {
        clickElementWithWaiting(tab);
        waitForVisible(activeTab);
    }
    // Хелпер: есть ли на странице активная вкладка с данным маркером
    @Step("Проверка, что вкладка активна")
    public boolean isTabActive(By activeTab) {
        return !driver.findElements(activeTab).isEmpty();
    }

    @Step("Открыта страница «Конструктор»")
    public ConstructorPage openConstructor () {
        driver.get(Config.baseUrl());
        // Ждём загрузку шапки с навигацией
        waitForVisible(HeaderLocators.CONSTRUCTOR_LINK);
        // На зеркале стартовый экран, а не конструктор: переходим по ссылке
        waitForClickable(HeaderLocators.CONSTRUCTOR_LINK).click();
        // Признак того, что конструктор отрисован: есть активная вкладка
        waitForVisible(ACTIVE_TAB);
        return this;
    }
}
