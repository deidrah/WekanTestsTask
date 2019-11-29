package wknt.WekanTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


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
        wait.until(ExpectedConditions.elementToBeClickable(button));
        click(button);
        wait.until(ExpectedConditions.presenceOfElementLocated(boardVisible));
        assertTrue(driver.findElement(boardVisible).isDisplayed());
    }

    @Test
    public void registerExistingUserTest() throws Exception {
        String username = "LoginTest";
        String email = "logintest@wp.pl";
        String password = "test123";
        By errorMessage = By.xpath("//*/text()[normalize-space(.)='Username already exists.']/parent::*");
        click(By.id("at-signUp"));
        type(By.id("at-field-username"), username);
        type(By.id("at-field-email"), email);
        type(By.id("at-field-password"), password);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        click(button);
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        assertTrue(driver.findElement(errorMessage).isDisplayed());
    }

}
