package learn.qa.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Базовый тест
 */
@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {

  @BeforeAll
  public void beforeAll() {
    closeBrowsers();
    Configuration.browser = "chrome";
    Configuration.browserVersion = "145";
  }

  @AfterAll
  public void afterAll() {
    closeBrowsers();
  }

  private static void closeBrowsers() {
    while (WebDriverRunner.hasWebDriverStarted()) {
      Selenide.closeWebDriver();
    }
  }
}