package Tests;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SocialNetworksLinksTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        mainWindow = driver.getWindowHandle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void userCanClickOnTwitterIcon() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", footerPage.getTwitterIcon());
        Assert.assertTrue(footerPage.getTwitterIcon().isDisplayed());
        footerPage.clickOnTwitterIcon();
        switchToNewWindow();
        String expectedURL = "https://x.com/saucelabs";
        Thread.sleep(5000);  // mora ovde wait zato sto se URL menja pre nego sto bude finalna verzija, gore u expectedURL
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanClickOnFacebookIcon() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", footerPage.getFacebookIcon());
        Assert.assertTrue(footerPage.getFacebookIcon().isDisplayed());
        footerPage.clickOnFacebookIcon();
        switchToNewWindow();
        String expectedURL = "https://www.facebook.com/saucelabs";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanClickOnLinkedInIcon() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", footerPage.getLinkedInIcon());
        Assert.assertTrue(footerPage.getLinkedInIcon().isDisplayed());
        footerPage.clickOnLinkedInIcon();
        switchToNewWindow();
        String expectedURL = "https://www.linkedin.com/company/sauce-labs/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @AfterMethod
    public void removeCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
