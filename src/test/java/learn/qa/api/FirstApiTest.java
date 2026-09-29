package learn.qa.api;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Авторизация")
public class FirstApiTest {

  @Test
  @DisplayName("Авторизация пользователя")
  void authShouldBeSuccess() {
    final String body = new JSONObject()
        .put("mail", "mail")
        .put("password", "password")
        .toString();
    Response res = step("API. Авторизация", () -> given()
        .filter(new AllureRestAssured())
        .baseUri("<backend-url>")
        .body(body)
        .contentType(ContentType.JSON)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .post("/auth/login"));
    String accessToken = res.jsonPath().getString("accessToken");
    String refreshToken = res.jsonPath().getString("refreshToken");
    String mail = res.jsonPath().getString("mail");

    assertThat(accessToken)
        .as("Проверка наличия access токена при авторизации")
        .isNotNull()
        .hasSizeGreaterThan(0);

    assertThat(refreshToken)
        .as("Проверка наличия refresh токена при авторизации")
        .isNotNull()
        .hasSizeGreaterThan(0);

    assertThat(mail)
        .as("Проверка валидности email")
        .isNotNull()
        .isEqualTo("mail");
  }

  @DisplayName("auth/me содержит mail")
  @Test
  void authMeShouldContainsMail() {
    final String token = getToken();
    final String mail = given()
        .filter(new AllureRestAssured())
        .baseUri("<backend-url>")
        .contentType(ContentType.JSON)
        .auth().oauth2(token)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .get("/auth/me")
        .jsonPath()
        .getString("user.mail");

    assertThat(mail)
        .as("Проверка наличия mail в ответе")
        .isNotNull()
        .isEqualTo("mail");
  }

  @Step("API. Авторизация")
  private String getToken() {
    final String body = new JSONObject()
        .put("mail", "mail")
        .put("password", "password")
        .toString();
    return given()
        .filter(new AllureRestAssured())
        .baseUri("<backend-url>")
        .body(body)
        .contentType(ContentType.JSON)
        .expect()
        .statusCode(200)
        .when()
        .post("/auth/login")
        .jsonPath()
        .getString("accessToken");
  }
}