package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import static user.UserFactory.withBasePermission;

@Epic("Тестирование сервиса PR-CY.")
@Feature("Тестирование страницы SEO анализа.")
public class SEOEvaluationTest extends BaseTest{
    private static final String TARGET_URL = "https://ngs.ru/";
    private static final String TARGET_KEYWORD = "Новосибирск";
    private static final String EXPECTED_PAGE_TITLE = "SEO анализ страницы сайта";

    @Story(("Проверка основного функционала SEO анализа."))
    @Test(description = "Тестирование загрузки SEO анализа.")
    public void seoEvaluationTest() {
        loginPage.openPage()
                .login(withBasePermission());
        mainPage.switchToSEOEvaluation();
        seoEvaluationPage.shouldHaveTite(EXPECTED_PAGE_TITLE);
        seoEvaluationPage.startSEOEvaluation(TARGET_URL, TARGET_KEYWORD);
        seoEvaluationPage.shouldHaveScoreGreaterThan60();
        seoEvaluationPage.checkProgressCircleColorBasedOnScore();
        seoEvaluationPage.shouldHaveSeoTestsList();
        seoEvaluationPage.shouldHaveKeyWord(TARGET_KEYWORD);
        seoEvaluationPage.shouldHaveUrl(TARGET_URL);
        seoEvaluationPage.updatePage();
        seoEvaluationPage.waitForProgressBarToDisappear();
        seoEvaluationPage.shouldHaveRecentUpdateTime();
    }

    @Story("Проверка формы SEO-анализа.")
    @Test(description = "Тестирование настроек формы SEO-анализа по умолчанию.")
    public void seoEvaluationPropertiesTest() {
        loginPage.openPage()
                .login(withBasePermission());
        mainPage.switchToSEOEvaluation();
        seoEvaluationPage.shouldHaveDefaultSubmitButtonColor();
        seoEvaluationPage.shouldBeSwitchChecked(1);
        seoEvaluationPage.shouldBeSwitchChecked(2);
        seoEvaluationPage.shouldBeSwitchNotChecked(3);
        seoEvaluationPage.shouldHaveDefaultBotDropDown();
    }
}
