package learn.qa.ui.test.auth;

import static learn.qa.ui.pages.auth.StartPage.openStartPage;

import learn.qa.ui.test.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Auth")
public class TestAuth extends BaseTest {

  private String login;
  private String password;

  @BeforeEach
  void before() {
    login = "login";
    password = "password";
  }

  @Test
  @DisplayName("Авторизация пользователя. Положительный сценарий")
  void testAuth() {
    openStartPage()
        .inputLogin(login)
        .inputPassword(password)
        .clickLogin()
        .desktopOpened();
  }
}