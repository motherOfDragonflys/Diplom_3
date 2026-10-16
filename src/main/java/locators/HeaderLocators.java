package locators;

import org.openqa.selenium.By;

public final class HeaderLocators {
    private HeaderLocators() {}

    // Ссылка Личный кабинет в шапке
    public static final By HEADER_ACCOUNT_LINK =
            By.xpath(".//a[@href='/profile']");

    // Имя пользователя в шапке: текст ссылки профиля вместо Личного кабинета
    public static final By HEADER_USER_NAME =
            By.xpath(".//a[@href='/profile']//p[not(text()='Личный кабинет')]");

    // Ссылка "Конструктор" в шапке
    public static final By CONSTRUCTOR_LINK =
            By.xpath(".//p[text()='Конструктор']");

}
