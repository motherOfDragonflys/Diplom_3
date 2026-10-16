package locators;

import org.openqa.selenium.By;

public final class RegisterPageLocators {
    private RegisterPageLocators() {}

//__Заголовок формы "Регистрация": маркер, что открыта именно эта страница
    public static final By REGISTER_HEADER =
            By.xpath(".//h3[text()='Регистрация']");

//__Форма_______________________________________________________________________________________________________________
    public static final By REGISTER_NAME_INPUT =
            By.xpath(".//input[@name='name']");

    public static final By REGISTER_EMAIL_INPUT =
            By.xpath(".//input[@type='email']");

    public static final By REGISTER_PASSWORD_INPUT =
            By.xpath(".//input[@type='password']");

    public static final By REGISTER_BUTTON =
            By.xpath(".//button[text()='Зарегистрироваться']");

//__Под формой__________________________________________________________________________________________________________
    // Маркер появления сообщения об ошибке
    public static final By REGISTER_ERROR_MESSAGE =
            By.xpath(".//form[@name='register']//p[contains(@class, 'text_type_main-default')]");

    // Ссылка "Войти" под формой
    public static final By LOGIN_LINK =
            By.xpath(".//a[text()='Войти']");
}
