package locators;

import org.openqa.selenium.By;

public final class HeaderLocators {
    private HeaderLocators() {}

    // Ссылка Личный кабинет в шапке
    public static final By HEADER_ACCOUNT_LINK =
            By.cssSelector("a[href='/profile']");

    // Ссылка Конструктор в шапке
    public static final By CONSTRUCTOR_LINK =
            By.cssSelector("nav a[href='/']");

}
