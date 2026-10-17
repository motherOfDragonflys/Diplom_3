package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.ConstructorPage;
import pageobjects.HeaderPage;
import pageobjects.LoginPage;
import utils.Config;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static utils.TestData.*;

/**
 * Логика тестов:
 * перед каждым тестом через API создается пользователь и после теста удаляется,
 * в каждом тесте происходит полный путь до входа пользователя,
 * изоляция тестов: у каждого теста свой пользователь и своя стартовая страница.
 * В Before и After не уношу логику в отдельный класс, т.к. пользователь через апи создается только в данном наборе тестов
 */
@Feature("Вход пользователя")
public class LoginTest extends BaseTest {

    private static User user;
    private static String accessToken;

    @Before
    @Step("Создаем тестового пользователя через API")
    public void createUser() {
        user = new User(
                uniqueEmail(),
                validPassword(),
                userName());

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Config.baseUrl() + "/api/auth/register");
        assertEquals("Пользователь не создан через API, код: " + response.statusCode(),
                SC_OK, response.statusCode());
        //Если пользователь создан, сохраняем токен
        accessToken = response.path("accessToken");
    }

    @After
    @Step("Удаляем тестового пользователя через API")
    public void deleteUser (){
        if (accessToken != null) {
            RestAssured.given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(Config.baseUrl() + "/api/auth/user");
        }
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
                .loginAs(user.getEmail(), user.getPassword());

        assertEquals(
                "После входа по кнопке \"Оформить заказ\", в шапке не имя пользователя",
                userName(),
                new HeaderPage(driver).getHeaderUserName());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("ссылка в шапке страницы ведет на форму входа")
    public void loginViaHeaderAccountButton() {
        new HeaderPage(driver)
                .clickAccountLink()
                .waitForLoginForm()
                .loginAs(user.getEmail(), user.getPassword());

        assertEquals(
                "После входа по кнопке \"Личный кабинет\", в шапке не имя пользователя",
                userName(),
                new HeaderPage(driver).getHeaderUserName());
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
                .loginAs(user.getEmail(), user.getPassword());

        assertEquals(
                "После входа по кнопке \"Войти\" под формой регистрации, в шапке не имя пользователя",
                userName(),
                new HeaderPage(driver).getHeaderUserName());
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
                .loginAs(user.getEmail(), user.getPassword());

        assertEquals(
                "После входа по кнопке \"Войти\" под формой восстановления пароля, в шапке не имя пользователя",
                userName(),
                new HeaderPage(driver).getHeaderUserName());
    }
}
