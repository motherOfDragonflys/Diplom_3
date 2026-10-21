package tests;

import base.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobjects.ConstructorPage;
import pageobjects.ForgotPasswordPage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

import static org.junit.Assert.assertTrue;

/**
 * Логика тестов:
 * перед каждым тестом через API создается пользователь и после теста удаляется (BaseApiTest),
 * вход проверяем по четырем точкам входа в форму логина;
 * после успешного входа открывается конструктор,
 * где кнопка "Войти в аккаунт" становится "Оформить заказ" — это и есть маркер входа
 */
@Feature("Вход пользователя")
public class LoginTest extends BaseApiTest {

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    @Description("Кнопка \"Войти в аккаунт\" на странице конструктора ведет на форму входа")
    public void loginViaEnterAccountButton() {
        ConstructorPage constructor = new ConstructorPage(driver)
                .openConstructor()
                .clickLoginButton()
                .waitForLoginForm()
                .loginAs(user.getEmail(), user.getPassword());

        assertTrue("После входа по кнопке \"Войти в аккаунт\" не открылся конструктор",
                constructor.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    @Description("Кнопка \"Личный кабинет\" в шапке ведет на форму входа")
    public void loginViaHeaderAccountButton() {
        ConstructorPage constructor = new LoginPage(driver)
                .openLoginPageOnHeaderLink()
                .waitForLoginForm()
                .loginAs(user.getEmail(), user.getPassword());

        assertTrue("После входа через \"Личный кабинет\" не открылся конструктор",
                constructor.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Кнопка \"Войти\" под формой регистрации ведет на форму входа")
    public void loginViaRegisterFormLink() {
        ConstructorPage constructor = new RegisterPage(driver)
                .openRegisterPage()
                .waitForRegisterForm()
                .clickLoginLink()
                .waitForLoginForm()
                .loginAs(user.getEmail(), user.getPassword());

        assertTrue("После входа из формы регистрации не открылся конструктор",
                constructor.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Кнопка \"Войти\" под формой восстановления пароля ведет на форму входа")
    public void loginViaForgotPasswordForm() {
        ConstructorPage constructor = new ForgotPasswordPage(driver)
                .openForgotPasswordPage()
                .waitForForgotPasswordForm()
                .clickLoginLink()
                .waitForLoginForm()
                .loginAs(user.getEmail(), user.getPassword());

        assertTrue("После входа из формы восстановления пароля не открылся конструктор",
                constructor.isOrderButtonVisible());
    }
}
