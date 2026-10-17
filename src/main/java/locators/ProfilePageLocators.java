package locators;

import org.openqa.selenium.By;

public final class ProfilePageLocators {
    private ProfilePageLocators() {}

//__Описание раздела: маркер открытия страницы профиля
    public static final By PROFILE_INFO_TEXT =
            By.xpath(".//p[contains(text(), 'изменить свои персональные данные')]");

//__Кнопка "Выход" на странице профиля
    public static final By LOGOUT_BUTTON =
            By.xpath(".//button[text()='Выход']");
}
