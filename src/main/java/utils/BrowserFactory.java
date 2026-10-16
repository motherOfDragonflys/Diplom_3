package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Фабрика WebDriver-драйверов.
 * <p>
 * Браузер выбирается системным свойством {@code browser}:
 * <pre>
 *   mvn clean test                  # Chrome (по умолчанию)
 *   mvn clean test -Dbrowser=yandex # Яндекс Браузер
 * </pre>
 * В IDEA: Run → Edit Configurations → VM options → {@code -Dbrowser=yandex}.
 * <p>
 * Для Яндекс Браузера требуется chromedriver соответствующей версии
 * в {@code src/main/resources/chromedriver-yandex.exe} (браузер — Chromium-подобный,
 * путь к нему задаётся константой {@link #YANDEX_BINARY}).
 */
public class BrowserFactory {

    private static final String CHROME = "chrome";
    private static final String YANDEX = "yandex";

    private static final String YANDEX_BINARY =
            "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
    private static final String YANDEX_DRIVER_PATH =
            "src/main/resources/chromedriver-yandex.exe";

    public static WebDriver create() {
        String browser = System.getProperty("browser", CHROME).toLowerCase();

        switch (browser) {
            case CHROME:
                return createChrome();
            case YANDEX:
                return createYandex();
            default:
                throw new IllegalArgumentException(
                        "Неизвестный браузер: " + browser + ". Доступно: chrome, yandex");
        }
    }

    private static WebDriver createChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized",
                "--no-first-run",
                "--no-default-browser-check",
                "--disable-infobars",
                "--headless",
                "--window-size=1920,1080"
        );
        return new ChromeDriver(options);
    }

    private static WebDriver createYandex() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BINARY);
        options.addArguments(
                "--start-maximized",
                "--no-first-run",
                "--no-default-browser-check",
                "--disable-infobars",
                "--headless",
                "--window-size=1920,1080"
        );
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
        return new ChromeDriver(options);
    }
}
