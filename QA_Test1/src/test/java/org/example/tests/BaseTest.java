package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.example.pages.*;
import org.example.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({AllureTestNg.class, TestListener.class })
public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    BasketPage basketPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @Step("Открытие браузера.")
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome")String browser, ITestContext context) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--guest");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        basketPage = new BasketPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @Step("Закрытие браузера.")
    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.quit();
    }
}
