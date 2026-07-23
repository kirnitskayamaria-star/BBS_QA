package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.TimeUtils;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static user.UserFactory.withBasePermission;

@Epic("Тестирование сервиса PR-CY.")
@Feature("Тестирование страницы SEO анализа.")
public class SEOEvaluationTest extends BaseTest{

    @Story(("Проверка основного функционала SEO анализа."))
    @Test(description = "Тестирование загрузки SEO анализа.")
    public void seoEvaluationTest() {
        loginPage.openPage()
                .login(withBasePermission());
        mainPage.switchToSEOEvaluation();
        seoEvaluationPage.getTitleElement().shouldHave(text("SEO анализ страницы сайта"));
        seoEvaluationPage.getSEOEvaluationResult();
        executeJavaScript("if (!arguments[0]) throw new Error('Баллы за SEO-анализ меньше или равны 60!');",
                seoEvaluationPage.isScoreGreaterThan60()
        );
        seoEvaluationPage.checkProgressCircleColor("rgb(255, 237, 0)");
        seoEvaluationPage.getSeoTestsList().shouldHave(
                CollectionCondition.exactTexts(seoEvaluationPage.getExpectedSeoTestNames()),
                Duration.ofSeconds(10)
        );
        seoEvaluationPage.getKeyWord().shouldHave(text("Новосибирск"));
        seoEvaluationPage.getUrl().shouldHave(text("https://ngs.ru/"));
        seoEvaluationPage.updatePage();
        seoEvaluationPage.getProgressBar().shouldBe(Condition.visible);
        seoEvaluationPage.getProgressBar().shouldBe(Condition.hidden, Duration.ofSeconds(30));
        String expectedCurrentTime = TimeUtils.getCurrentTime();
        String expectedPastTime = TimeUtils.getOneMinuteAgoTime();
        seoEvaluationPage.getLastUpdateTime().shouldHave(
                Condition.or("текущее время обновления",
                        Condition.text(expectedCurrentTime),
                        Condition.text(expectedPastTime)
                )
        );
    }

    @Story("Проверка формы SEO-анализа.")
    @Test(description = "Тестирование настроек формы SEO-анализа по умолчанию.")
    public void seoEvaluationPropertiesTest() {
        loginPage.openPage()
                .login(withBasePermission());
        mainPage.switchToSEOEvaluation();
        seoEvaluationPage.checkSubmitButtonColor("rgba(53, 66, 81, 1)");
        seoEvaluationPage.getSwitchCheckedState(1).shouldBe(Condition.visible);
        seoEvaluationPage.getSwitchCheckedState(2).shouldBe(Condition.visible);
        seoEvaluationPage.getSwitchCheckedState(3).shouldBe(Condition.hidden);
        seoEvaluationPage.getBotDropDown().shouldHave(text("PR-CY Bot"));
    }
}
