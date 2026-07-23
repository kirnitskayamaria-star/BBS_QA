package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement pageTitle = $x("(//h3)");
    private final SelenideElement seoEvaluation = $(byText("SEO анализ страницы сайта"));


    @Step("Получаем заголовок страницы.")
    public SelenideElement getTitleElement() {
        return pageTitle;
    }

    @Step("Переходим на страницу SEO Анализа.")
    public void switchToSEOEvaluation() {
        seoEvaluation.click();
    }

}
