package tests;

import base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.RegisterPage;
import java.util.Arrays;
import java.util.Collection;
import static utils.TestData.*;

@Feature("Регистрация пользователя: некорректный пароль")
@RunWith(Parameterized.class)
public class RegisterNegativeParameterizedTest extends BaseApiTest {

    private final String password;
    private final String testName;
    private RegisterPage registerPage;

    public RegisterNegativeParameterizedTest(String password, String testName) {
        this.password = password;
        this.testName = testName;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"",      "Ошибка регистрации с паролем из 0 символов"},
                {"a",     "Ошибка регистрации с паролем из 1 символа"},
                {"aBcD",  "Ошибка регистрации с паролем из 4 символов"},
                {"abCde", "Ошибка регистрации с паролем из 5 символов (нижняя граница - 1)"}
        });
    }

    // "Отключаем" создание пользователя у родителя
    @Override
    @Before
    public void createUser() {
        registerPage = new RegisterPage(driver)
                .openRegisterPage()
                .waitForRegisterForm();
    }

    @Test
    @Description("Невозможность регистрации пользователя с паролем от 0 до 5 символов включительно")
    public void registerWithInvalidPassword() {

        Allure.getLifecycle().updateTestCase(t -> t.setName(testName));

        registerPage.setName(userName())
                .setEmail(uniqueEmail())
                .setPassword(password)
                .clickRegisterButton();

        Assert.assertTrue(
                testName + ": ожидаемое сообщение об ошибке не появилось",
                registerPage.isPasswordErrorVisible());
    }
}
