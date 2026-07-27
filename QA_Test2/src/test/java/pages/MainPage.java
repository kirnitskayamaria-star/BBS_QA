package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement pageTitle = $x("(//h3)");
    private final SelenideElement seoEvaluation = $(byText("SEO анализ страницы сайта"));


    @Step("Проверяем заголовок страницы.")
    public MainPage shouldHaveTitle(String expected) {
        pageTitle.shouldHave(text(expected));
        return this;
    }

    @Step("Переходим на страницу SEO Анализа.")
    public void switchToSEOEvaluation() {
        seoEvaluation.click();
    }
}
