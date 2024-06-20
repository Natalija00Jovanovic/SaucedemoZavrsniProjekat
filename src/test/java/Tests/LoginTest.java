package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void logInWithInvalidUsername() {
        loginPage.enterUsernameInUsernameField("standarduser");
        loginPage.enterPasswordInPasswordField("secret_sauce");
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        String errorMessageText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage().getText(), errorMessageText);
    }

    @Test
    public void logInWithInvalidPassword() {
        loginPage.enterUsernameInUsernameField("standard_user");
        loginPage.enterPasswordInPasswordField("secretsauce");
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        String errorMessageText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPage.getErrorMessage().getText(), errorMessageText);
    }

    @Test
    public void logInWithValidCredentials() {
        loginPage.enterUsernameInUsernameField("standard_user");
        loginPage.enterPasswordInPasswordField("secret_sauce");
        Assert.assertTrue(loginPage.getLoginButton().isDisplayed());
        loginPage.clickOnLoginButton();
        Assert.assertTrue(headerPage.getCartIcon().isDisplayed());
    }

    @AfterMethod
    public void removeCookies() {  // za ovaj test ne moze da stoji resetAppState() zato sto ne postoji taj element na stranici
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
