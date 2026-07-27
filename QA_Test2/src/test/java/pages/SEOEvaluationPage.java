package pages;

import com.codeborne.selenide.*;
import enums.SEOTestTypes;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import utils.TimeUtils;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SEOEvaluationPage {
    private final SelenideElement pageTitle = $x("(//h1)");
    private final SelenideElement urlInput = $("input[name='url']");
    private final SelenideElement keyWordInput = $("input[placeholder='Введите ключевое слово']");
    private final SelenideElement submitButton = $(byText("Проверить"));
    private final SelenideElement scoreBadge = $(com.codeborne.selenide.Selectors.withText("балл"));
    private final SelenideElement keyWord = $x("(//table/tbody/tr[2]/td[1])[6]");
    private final SelenideElement url = $x("(//table/tbody/tr[2]/td[2])[6]");
    private final SelenideElement progressCircle = $("circle.lgt-progress-circle-path");
    private final SelenideElement updateButton = $(Selectors.byText("Обновить"));
    private final SelenideElement progressBar = $(".e171iw7010");
    private final SelenideElement lastUpdateTime = $(".prcy-qmatmz.exm8y4t0");
    private final SelenideElement botDropDown = $x("//div[contains(concat(' ', normalize-space(@class), ' '), ' lgt-select ')]");
    private final ElementsCollection switches = $$(".lgt-switch-inner");
    private final ElementsCollection seoTestsList = $$(".e5ttlwk7");
    private static final String DEFAULT_SUBMIT_BUTTON_COLOR = "rgba(53, 66, 81, 1)";
    private static final String DEFAULT_BOT_NAME = "PR-CY Bot";

    @Step("Проверяем заголовок страницы.")
    public SEOEvaluationPage shouldHaveTite(String expected) {
        pageTitle.shouldHave(text(expected));
        return this;
    }

    @Step("Запускаем SEO анализ для URL: {url} с ключевым словом: {keyword}")
    public void startSEOEvaluation(String url, String keyword) {
        urlInput.setValue(url);
        keyWordInput.setValue(keyword);
        submitButton.click();
    }

    @Step("Проверяем, что баллы анализа больше 60.")
    public SEOEvaluationPage shouldHaveScoreGreaterThan60() {
        WebElementCondition scoreGreaterThan60 = new WebElementCondition("баллы больше 60") {
            @Override
            public CheckResult check(Driver driver, WebElement element) {
                String text = element.getText();
                if (text == null || text.isEmpty()) {
                    return CheckResult.rejected("текст отсутствует", text);
                }

                String onlyDigits = text.replaceAll("[^0-9]", "");
                if (onlyDigits.isEmpty()) {
                    return CheckResult.rejected("в тексте нет цифр", text);
                }

                int actualScore = Integer.parseInt(onlyDigits);

                return actualScore > 60 ?
                        CheckResult.accepted() :
                        CheckResult.rejected("фактические баллы: " + actualScore, text);
            }
        };
        scoreBadge.shouldHave(scoreGreaterThan60, Duration.ofSeconds(120));
        return this;
    }


    @Step("Проверяем ключевое слово в разделе История.")
    public SEOEvaluationPage shouldHaveKeyWord(String expected) {
        keyWord.shouldHave(text(expected));
        return this;
    }

    @Step("Проверяем URL в разделе История.")
    public SEOEvaluationPage shouldHaveUrl(String expected) {
        url.shouldHave(text(expected));
        return this;
    }

    @Step("Проверяем значение User Agent по умолчанию.")
    public SEOEvaluationPage shouldHaveDefaultBotDropDown() {
        botDropDown.shouldHave(text(DEFAULT_BOT_NAME));
        return this;
    }

    @Step("Проверяем цвет кнопки отправки по умолчанию.")
    public SEOEvaluationPage shouldHaveDefaultSubmitButtonColor() {
        submitButton.shouldHave(Condition.cssValue("background-color", DEFAULT_SUBMIT_BUTTON_COLOR));
        return this;
    }

    @Step("Проверяем, что переключатель №{number} включен.")
    public SEOEvaluationPage shouldBeSwitchChecked(int number) {
        switches.get(number - 1).$(".lgt-switch-inner-checked").shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем, что переключатель №{number} выключен.")
    public SEOEvaluationPage shouldBeSwitchNotChecked(int number) {
        switches.get(number - 1).$(".lgt-switch-inner-checked").shouldBe(Condition.hidden);
        return this;
    }

    @Step("Проверяем, что цвет кругового прогресс-бара соответствует успешному анализу.")
    public SEOEvaluationPage checkProgressCircleColorBasedOnScore() {
        progressCircle.shouldHave(
                Condition.or("успешный цвет индикатора",
                        Condition.cssValue("stroke", "rgb(49, 105, 240)"), // Синий
                        Condition.cssValue("stroke", "rgb(82, 196, 26)")   // Зеленый
                )
        );
        return this;
    }

    @Step("Загружаем обновление анализа.")
    public void updatePage() {
        updateButton.click();
    }

    @Step("Ожидаем появление и последующее скрытие прогресс-бара.")
    public SEOEvaluationPage waitForProgressBarToDisappear() {
        progressBar.shouldBe(Condition.visible);
        progressBar.shouldBe(Condition.hidden, Duration.ofSeconds(30));
        return this;
    }

    @Step("Проверяем, что время последнего обновления актуально.")
    public SEOEvaluationPage shouldHaveRecentUpdateTime() {
        String expectedCurrentTime = TimeUtils.getCurrentTime();
        String expectedPastTime = TimeUtils.getOneMinuteAgoTime();

        lastUpdateTime.shouldHave(
                Condition.or("актуальное время обновления",
                        Condition.text(expectedCurrentTime),
                        Condition.text(expectedPastTime)
                )
        );
        return this;
    }

    @Step("Получаем список всех отображаемых SEO-тестов и проверяем их названия.")
    public SEOEvaluationPage shouldHaveSeoTestsList() {
        String[] expectedTestNames = Stream.of(SEOTestTypes.values())
                .map(SEOTestTypes::getName)
                .toArray(String[]::new);
        seoTestsList.shouldHave(
                CollectionCondition.exactTexts(expectedTestNames),
                Duration.ofSeconds(10)
        );
        return this;
    }
}
