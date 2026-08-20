package learn.qa.test.auth;

import static learn.qa.pages.auth.StartPage.openStartPage;

import learn.qa.test.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Auth")
public class TestAuth extends BaseTest {

  private String login;
  private String password;

  @BeforeEach
  void before() {
    login = "autotest_extra5@rt.ru";
    password = "Autotest1";
  }

  @Test
  @DisplayName("Авторизация пользователя. Положительный сценарий.")
  void testAuth() {
    openStartPage()
        .inputLogin(login)
        .inputPassword(password)
        .clickLogin()
        .desktopOpened();
  }
}