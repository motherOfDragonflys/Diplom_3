package base;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;
import org.junit.After;
import org.junit.Before;
import utils.Config;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static utils.ApiEndpoints.*;
import static utils.Config.pageUrl;
import static utils.TestData.*;

/**
 * Базовый класс для тестов с пользователем:
 * - если тесту нужен заранее созданный пользователь, используется @Before createUser как есть;
 * - если пользователь создаётся самим тестом,
 *   тест переопределяет @Before и кладёт созданного пользователя
 *   в поле user: для уборки в @After;
 * - токен тесты не сохраняют, @After добудет его сам
 */
public class BaseApiTest extends BaseTest {

    protected User user;
    protected String accessToken;

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
                .post(pageUrl(REGISTER_ENDPOINT));
        assertEquals("Пользователь не создан через API, код: " + response.statusCode(),
                SC_OK, response.statusCode());
        //Если пользователь создан, сохраняем токен
        accessToken = response.path("accessToken");
    }

    @After
    @Step("Удаляем тестового пользователя через API")
    public void deleteUser (){

        String token = accessToken;

        // Для тестов с "отключенным" Before ловим токен
        if (token == null && user != null) {
            // Входим с данными пользователя, ловим токен
            Response loginResponse = loginAPI(
                    user.getEmail(),
                    user.getPassword(),
                    user.getName());
            if (loginResponse.statusCode() == SC_OK) {
                token = loginResponse.path("accessToken");
            }
        }

        // Удаление по токену
        if (token != null) {
            RestAssured.given()
                    .header("Authorization", token)
                    .when()
                    .delete(Config.baseUrl() + DELETE_ENDPOINT);
        }
    }

    @Step("Входим через API")
    public Response loginAPI(String email, String password, String name) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new User(email, password, name))
                .when()
                .post(pageUrl(LOGIN_ENDPOINT));
    }
}
