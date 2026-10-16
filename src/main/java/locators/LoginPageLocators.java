package locators;

import org.openqa.selenium.By;

public final class LoginPageLocators {
    private LoginPageLocators() {}

//__Заголовок формы "Вход": маркер, что открыта именно эта страница

    public static final By LOGIN_HEADER =
            By.xpath(".//h3[text()='Вход']");

//__Форма_______________________________________________________________________________________________________________

    public static final By EMAIL_FIELD =
            By.xpath(".//input[@type='email']");

    public static final By PASSWORD_FIELD =
            By.xpath(".//input[@type='password']");

    public static final By LOGIN_BUTTON =
            By.xpath(".//button[text()='Войти']");

//__Под формой__________________________________________________________________________________________________________
    // Ссылка "Зарегистрироваться" ведёт на регистрацию
    public static final By REGISTER_LINK =
            By.xpath(".//a[@href='/register']");

    // Ссылка "Восстановить пароль": ведет на страницу восстановления пароля
    public static final By FORGOT_PASSWORD_LINK =
            By.xpath(".//a[@href='/forgot-password']");
}
