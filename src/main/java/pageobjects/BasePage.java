package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Базовый класс всех страниц.
 * <p>
 * Содержит WebDriver, ожидания и общие операции.
 * Дочерние страницы получают драйвер через конструктор
 * и работают со своими локаторами через унаследованные методы.
 */
public abstract class BasePage {

    protected static final long DEFAULT_TIMEOUT = 20;

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

//__Ожидания видимости, кликабельности, невидимости_____________________________________________________________________

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

//__Общие операции______________________________________________________________________________________________________

    protected void clickElementWithWaiting(By locator) {
        WebElement element = waitForClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void inputTextInField(By locator, String text) {
        WebElement field = waitForVisible(locator);
        field.clear();
        field.sendKeys(text);
    }
}
