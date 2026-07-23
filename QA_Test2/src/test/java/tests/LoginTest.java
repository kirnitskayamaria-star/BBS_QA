package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.text;
import static user.UserFactory.withBasePermission;

@Epic("Тестирование сервиса PR-CY.")
@Feature("Тестирование страницы авторизации.")
public class LoginTest extends BaseTest {
    @Story("Авторизация по e-mail.")
    @Test(description = "Тестрование авторизации по e-mail.")
    public void projectIsOpen() {
        loginPage.openPage()
                .login(withBasePermission());
        mainPage.getTitleElement().shouldHave(text("Обновления сервиса"));
    }
}
