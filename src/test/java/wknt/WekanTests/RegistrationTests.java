package wknt.WekanTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegistrationTests extends TestBase {

    @Test
    public void registerNewUserTest() throws Exception {
        String username = "Test%s";
        String email = "Test%s@gmail.com";
        String password = "test123";
        String formattedUsername = String.format(username, RandomStringUtils.randomAlphabetic(4));
        String formattedEmail = String.format(email, RandomStringUtils.randomAlphabetic(4));
        click(By.id("at-signUp"));
        type(By.xpath("//input[@id='at-field-username']"), formattedUsername);
        type(By.xpath("//input[@id='at-field-email']"), formattedEmail);
        type(By.xpath("//input[@id='at-field-password']"), password);
        click(By.xpath("//form[@id='at-pwd-form']/button"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("js-add-board")));
        assertTrue(driver.findElement(By.className("js-add-board")).isDisplayed());
    }

    @Test
    public void registerExistingUserTest() throws Exception {
        String username = "LoginTest";
        String email = "logintest@wp.pl";
        String password = "test123";
        click(By.id("at-signUp"));
        type(By.id("at-field-username"), username);
        type(By.id("at-field-email"), email);
        type(By.id("at-field-password"), password);
        click(By.id("at-btn"));
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*/text()[normalize-space(.)='Username already exists.']/parent::*")));
        assertTrue(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Username already exists.']/parent::*")).isDisplayed());
    }

}
