package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUP() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Enter “tomsmith” username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Enter “SuperSecretPassword!” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify the text “Secure Area”
        String expectedText = "Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='example']//h2"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("", expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Enter “tomsmith1” username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        // Enter “SuperSecretPassword” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        //Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify the error message “Your username is invalid!”
        String expectedMessage = "Your username is invalid!\n" +
                "×";
        WebElement actualMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals("Message not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter “tomsmith” username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Enter “SuperSecretPassword” password
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");
        // Click on ‘LOGIN’ button
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        // Verify the error message “Your password is invalid!”
        String expectedMessage = "Your password is invalid!\n" +
                "×";
        WebElement actualMessageElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals("Message not displayed", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
