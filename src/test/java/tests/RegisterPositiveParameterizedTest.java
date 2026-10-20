package tests;

import base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import model.User;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;
import java.util.Arrays;
import java.util.Collection;
import static utils.TestData.*;

@Feature("Регистрация пользователя: успешная регистрация")
@RunWith(Parameterized.class)
public class RegisterPositiveParameterizedTest extends BaseApiTest {

    private final String password;
    private final String testName;
    private RegisterPage registerPage;

    public RegisterPositiveParameterizedTest(String password, String testName) {
        this.password = password;
        this.testName = testName;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Password12345", "Успешная регистрация с паролем длиннее 6 символов"},
                {"ABcdEF",        "Успешная регистрация с паролем 6 символов (нижняя граница)"},
                {"ABcdEFa",       "Успешная регистрация с паролем 7 символов (нижняя граница + 1)"}
        });
    }

    // "Отключаем" создание пользователя у родителя, After не трогаем
    @Override
    @Before
    public void createUser() {
        registerPage = new RegisterPage(driver)
                .openRegisterPage()
                .waitForRegisterForm();
    }

    @Test
    @Description("Успешная регистрация пользователя с паролем от 6 символов включительно")
    public void registerWithPassword() {

        Allure.getLifecycle().updateTestCase(t -> t.setName(testName));

        String email = uniqueEmail();
        String name = userName();

        user = new User(email, password, name);

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        new LoginPage(driver).waitForLoginForm();

        Response loginResponse = loginAPI(email, password, name);
        Assert.assertEquals(
              testName + ": пользователь создан, API-логин с зарегистрированными данными",
              HttpStatus.SC_OK,
              loginResponse.statusCode());
    }
}
