package wknt.WekanTests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class LoginTests extends TestBase {

    @Test
    public void loginWithExistingUserTest() throws Exception {
        String username = "LoginTest";
        String password = "test123";
        type(By.id("at-field-username_and_email"), username);
        type(By.id("at-field-password"), password);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        click(button);
        wait.until(ExpectedConditions.presenceOfElementLocated(boardVisible));
        assertTrue(driver.findElement(boardVisible).isDisplayed());
    }

    @Test
    public void loginWithNonExistingUserTest() throws Exception {
        String username = "NonUser";
        String password = "test123";
        By errorMessage = By.xpath("//*/text()[normalize-space(.)='Login forbidden']/parent::*");
        type(By.id("at-field-username_and_email"), username);
        type(By.id("at-field-password"), password);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        click(button);
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        assertTrue(driver.findElement(errorMessage).isDisplayed());
    }

}
