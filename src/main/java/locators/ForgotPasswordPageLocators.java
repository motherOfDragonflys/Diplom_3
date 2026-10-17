package locators;

import org.openqa.selenium.By;

public final class ForgotPasswordPageLocators {
    private ForgotPasswordPageLocators() {}

//__Заголовок "Восстановление пароля": маркер открытия формы восстановления
    public static final By FORGOT_PASSWORD_HEADER =
            By.xpath(".//h3[text()='Восстановление пароля']");

//__Ссылка "Войти" под формой
    public static final By LOGIN_LINK_IN_FORGOT_PAGE =
            By.xpath(".//a[@href='/login']");
}
