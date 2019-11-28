package wknt.WekanTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTests {
    WebDriverWait wait;
    WebDriver driver;

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    @BeforeEach
    public void testSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8,30));

        driver.navigate().to("https://wekan.coded.pl/");
    }


    @Test
    public void loginWithExistingUserTest() throws Exception {
        String username = "LoginTest";
        String password = "test123";
        type(By.id("at-field-username_and_email"), username);
        type(By.id("at-field-password"), password);
        click(By.id("at-btn"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-add-board")));
        assertTrue(driver.findElement(By.className("js-add-board")).isDisplayed());
    }

    @Test
    public void loginWithNonExistingUserTest() throws Exception {
        String username = "NonUser";
        String password = "test123";
        type(By.id("at-field-username_and_email"), username);
        type(By.id("at-field-password"), password);
        click(By.id("at-btn"));
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*/text()[normalize-space(.)='Login forbidden']/parent::*")));
        System.out.println(driver.getPageSource());
        assertTrue(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Login forbidden']/parent::*")).isDisplayed());
    }


    @AfterEach
    public void closeDriver() throws IOException {
        driver.close();
        driver.quit();
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
