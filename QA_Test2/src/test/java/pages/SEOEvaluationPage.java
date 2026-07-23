package pages;

import com.codeborne.selenide.*;
import enums.SEOTestTypes;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.Arrays;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SEOEvaluationPage {
    private final SelenideElement pageTitle = $x("(//h1)");
    private final SelenideElement urlInput = $("input[name='url']");
    private final SelenideElement keyWordInput = $("input[placeholder='Введите ключевое слово']");
    private final SelenideElement submitButton = $(byText("Проверить"));
    private final SelenideElement scoreBadge = $(".e1923nlf4");
    private final SelenideElement keyWord = $x("(//table/tbody/tr[2]/td[1])[6]");
    private final SelenideElement url = $x("(//table/tbody/tr[2]/td[2])[6]");
    private final SelenideElement progressCircle = $("circle.lgt-progress-circle-path");
    private final SelenideElement updateButton = $(Selectors.byText("Обновить"));
    private final SelenideElement progressBar = $(".e171iw7010");
    private final SelenideElement lastUpdateTime = $(".prcy-qmatmz.exm8y4t0");
    private final SelenideElement botDropDown = $x("//div[contains(concat(' ', normalize-space(@class), ' '), ' lgt-select ')]");
    private final ElementsCollection switches = $$(".lgt-switch-inner");
    private final ElementsCollection seoTestsList = $$(".e5ttlwk7");

    @Step("Получаем название заголовка.")
    public SelenideElement getTitleElement() {
        return pageTitle;
    }

    @Step("Получаем результаты SEO анализа.")
    public void getSEOEvaluationResult() {
        urlInput.setValue("https://ngs.ru");
        keyWordInput.setValue("Новосибирск");
        submitButton.click();
    }

  @Step("Проверяем, что баллы анализа больше 60.")
   public boolean isScoreGreaterThan60() {
     scoreBadge.shouldBe(Condition.visible, Duration.ofSeconds(120));
   String text = scoreBadge.getText();
       if (text == null || text.isEmpty()) return false;
       String onlyDigits = text.replaceAll("[^0-9]", "");
       if (onlyDigits.isEmpty()) return false;
      int score = Integer.parseInt(onlyDigits);
        return score > 60;
    }

    @Step("Проверяем ключевое слово в разделе История.")
    public SelenideElement getKeyWord() {
        return keyWord;
    }

    @Step("Проверяем URL в разделе История.")
    public SelenideElement getUrl() {
        return url;
    }

    @Step("Проверяем значение User Agent по умолчанию.")
    public SelenideElement getBotDropDown() {
        return botDropDown;
    }

    @Step("Проверяем, что цвет кнопки отправки.")
    public SelenideElement checkSubmitButtonColor(String expectedColor) {
        return submitButton.shouldHave(Condition.cssValue("background-color", expectedColor));
    }

    @Step("Получаем индикатор включения переключателя.")
    public SelenideElement getSwitchCheckedState(int number) {
        return switches.get(number - 1).$(".lgt-switch-inner-checked");
    }

    @Step("Проверяем цвет кругового прогресс-бара.")
    public SelenideElement checkProgressCircleColor(String expectedColor) {
        return progressCircle.shouldHave(Condition.cssValue("stroke", expectedColor));
    }

    @Step("Загружаем обновление анализа.")
    public void updatePage() {
        updateButton.click();
    }

    @Step("Получаем ленту прогресса.")
    public SelenideElement getProgressBar() {
        return progressBar;
    }

    @Step("Получаем элемент с временем последнего обновления.")
    public SelenideElement getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Step("Получаем список всех отображаемых SEO-тестов.")
    public ElementsCollection getSeoTestsList() {
        return seoTestsList;
    }

    public String[] getExpectedSeoTestNames() {
        return Arrays.stream(SEOTestTypes.values())
                .map(SEOTestTypes::getName)
                .toArray(String[]::new);
    }
}
