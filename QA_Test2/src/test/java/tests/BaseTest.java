package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.MainPage;
import pages.SEOEvaluationPage;
import utils.PropertyReader;
import utils.TestListener;

@Listeners({AllureTestNg.class, TestListener.class })
public class BaseTest {
    LoginPage loginPage;
    MainPage mainPage;
    SEOEvaluationPage seoEvaluationPage;

    @Step("Открытие браузера.")
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp( ITestContext context) {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        Configuration.baseUrl = PropertyReader.getProperty("prcy.url");;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        loginPage = new LoginPage();
        mainPage = new MainPage();
        seoEvaluationPage= new SEOEvaluationPage();
    }

    @Step("Закрытие браузера.")
    @AfterMethod(alwaysRun = true)
    public void close() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}
