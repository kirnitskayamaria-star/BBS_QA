package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import user.User;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement buttonEnter = $x("//span[@role='button'][text()='Вход']");
    private final SelenideElement buttonPasswordEnter = $(byText("Вход по паролю"));
    private final SelenideElement buttonPasswordEnterNew = $(byText("Войти по паролю"));
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement buttonSubmit = $(byText("Войти"));

    @Step("Открываем главную страницу сайта.")
    public LoginPage openPage() {
        open("/");
        return this;
    }

    @Step("Логинимся под кредами пользователя.")
    public LoginPage login(User user) {
        buttonEnter.shouldBe(Condition.visible, Condition.enabled).click();
        buttonPasswordEnter.shouldBe(Condition.visible).click();
        buttonPasswordEnterNew.shouldBe(Condition.visible).click();
        fillEmailField(user.getEmail());
        fillPasswordField(user.getPassword());
        Selenide.sleep(1500);
        buttonSubmit.click();
        return this;
    }

    @Step("Заполняем поле ввода email.")
    public void fillEmailField(String email) {
        emailInput.setValue(email);
    }

    @Step("Заполняем поле ввода пароля.")
    public void fillPasswordField(String password) {
        passwordInput.setValue(password);
    }
}
