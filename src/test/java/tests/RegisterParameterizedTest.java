package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.HeaderPage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;
import java.util.Arrays;
import java.util.Collection;
import static utils.TestData.*;

@Feature("Регистрация пользователя: проверка пароля")
@RunWith(Parameterized.class)
public class RegisterParameterizedTest extends BaseTest {

    private final String password;
    private final boolean shouldFail;
    private final String testName;
    private RegisterPage registerPage;

    public RegisterParameterizedTest(String password, boolean shouldFail, String testName) {
        this.password = password;
        this.shouldFail = shouldFail;
        this.testName = testName;
    }

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Password12345", false, "Успешная регистрация с паролем длиннее 6 символов"},
                {"ABcdEF",        false, "Успешная регистрация с паролем 6 символов (нижняя граница)"},
                {"ABcdEFa",       false, "Успешная регистрация с паролем 7 символов (нижняя граница + 1)"},
                {"",              true,  "Ошибка регистрации с пустым паролем"},
                {"a",             true,  "Ошибка регистрации с паролем из 1 символа"},
                {"abCde",         true,  "Ошибка регистрации с паролем из 5 символов (нижняя граница - 1)"}
        });
    }

    @Before
    // >> "Личный кабинет" в шапке
    // >> "Зарегистрироваться" под формой входа
    // проверяем видимость формы регистрации
    public void openRegisterPage() {
        registerPage = new LoginPage(driver)
                .openLoginPage()
                .clickRegisterLink()
                .waitForRegisterForm();
    }

    @Test
    public void registerWithPassword() {

        Allure.getLifecycle().updateTestCase(t -> t.setName(testName));

        registerPage.setName(userName());
        registerPage.setEmail(uniqueEmail());
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        //Если помечено маркером false (негативные кейсы)
        if (shouldFail) {
            registerPage.waitForError();
        } else {
            //Если позитивные, в шапке отображается имя пользователя
            new HeaderPage(driver).waitForUserInHeader();
        }
    }
}
