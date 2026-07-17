package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    public static final String DATA_TEST_PATTERN = "//*[@data-test='%s']";
    public static final String REMOVE_BUTTON = "//button[@data-test='remove-%s']";
    public static final By PAGE_TITLE = By.xpath("//*[@class='title']");
    WebDriver driver;
    WebDriverWait wait;
    public NavigationPanel navigationPanel;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.navigationPanel = new NavigationPanel(driver);
    }

    @Step("Открываем страницу авторизации.")
    public BasePage open(String pagePath) {
        driver.get(BASE_URL + pagePath);
        return this;
    }
}
