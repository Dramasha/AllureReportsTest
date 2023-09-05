import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideWithStepsTest extends BaseTest {
    private static final String REPOSITORY = "Dramasha/AllureReportsTest";
    private static final int Issue = 1;

    @Test
    public void selenideWithStepsTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Откываем главную страницу сайта Github", () -> {
                    open("https://www.github.com");
        });
        step("Ищем репозиторий " + REPOSITORY + " в поисковой строке вверхней правой части сайта", () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY + " в верхней центральной части страницы", () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issues с номером" + Issue + " под названием самого " + Issue, () -> {
            $(withText("#" + Issue)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(Issue);
    }
}