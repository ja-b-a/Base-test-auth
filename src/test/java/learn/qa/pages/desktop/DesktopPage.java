package learn.qa.pages.desktop;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import learn.qa.pages.BasePage;

/**
 * Рабочий стол
 */
public class DesktopPage extends BasePage {

  private final SelenideElement desktopHeader =
      $x("//div[@data-class = 'Page_headerContent']//span[text() = 'Рабочий стол']")
          .as("Заголовок рабочего стола");

  @Step("Проверяем, что рабочий стол открыт")
  public DesktopPage desktopOpened() {
    desktopHeader.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC));
    return this;
  }
}