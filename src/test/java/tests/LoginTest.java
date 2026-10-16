package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pageobjects.ConstructorPage;
import pageobjects.HeaderPage;
import pageobjects.LoginPage;

import static utils.Config.baseUrl;
import static utils.TestData.*;

/**
 * Логика тестов:
 * в Before создается пользователь 1(!) раз на все тесты и разлогинивается,
 * в каждом тесте происходит полный путь до формы Вход и логин пользователя,
 * изоляция тестов: каждый тест открывает свою стартовую страницу сам,
 * состояние сессии между тестами не очищается
 */
@Feature("Вход пользователя")
public class LoginTest extends BaseTest {

    private static String email;
    private static String password;
    private static boolean userCreated = false;

    @Before
    @Step("Создаем тестового пользователя и выходим из аккаунта")
    public void registerAndLogoutUser() {

        if (userCreated) {
            return; // учетка уже создана
        }

        email = uniqueEmail();
        password = validPassword();

        new LoginPage(driver)
                .openLoginPage()
                .clickRegisterLink()
                .waitForRegisterForm()
                .setName(userName())
                .setEmail(email)
                .setPassword(password)
                .clickRegisterButton()
                .waitForUserInHeader()
                .logout();  // вернулись в незалогиненное состояние
        userCreated = true;

        driver.get(baseUrl());
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        driver.manage().deleteAllCookies();
        driver.get(baseUrl());

    }

    @Test
    @DisplayName("Вход через кнопку \"Оформить заказ\"")
    @Description("В данной реализации \"вход по кнопке «Войти в аккаунт» на главной\" " +
            "происходит через кнопку \"Оформить заказ\" для незалогиненного пользователя")
    public void loginViaOrderButton() {
        new ConstructorPage(driver)
                .openConstructor()
                .clickOrderButton()
                .waitForLoginForm()
                .loginAs(email, password);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("ссылка в шапке страницы ведет на форму входа")
    public void loginViaHeaderAccountButton() {
        new HeaderPage(driver)
                .clickAccountLink()
                .waitForLoginForm()
                .loginAs(email, password);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("ссылка \"Войти\" под формой регистрации")
    public void loginViaRegisterFormLink() {
        new LoginPage(driver)
                .openLoginPage()
                .clickRegisterLink()
                .waitForRegisterForm()
                .clickLoginLink()
                .waitForLoginForm()
                .loginAs(email, password);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("ссылка \"Войти\" под формой восстановления пароля")
    public void loginViaForgotPasswordForm() {
        new LoginPage(driver)
                .openLoginPage()
                .clickForgotPasswordLink()
                .waitForForgotPasswordForm()
                .clickLoginLink()
                .waitForLoginForm()
                .loginAs(email, password);
    }
}
