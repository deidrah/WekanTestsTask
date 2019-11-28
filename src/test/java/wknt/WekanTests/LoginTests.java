package wknt.WekanTests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;


public class LoginTests extends TestBase {

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
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*/text()[normalize-space(.)='Login forbidden']/parent::*")));
        assertTrue(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Login forbidden']/parent::*")).isDisplayed());
    }

}
