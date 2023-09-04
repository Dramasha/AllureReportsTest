import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;

public class SelenideWithListenerTest extends BaseTest {
    @Test
    public void selenideWithListenerTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://www.github.com");
        $(".search-input").click();
        $("#query-builder-test").setValue("Dramasha/AllureReportsTest").submit();
        $(linkText("Dramasha/AllureReportsTest")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);
    }
}