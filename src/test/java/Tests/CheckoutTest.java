package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void userCanClickOnCancelOptionOnCheckoutYourInformationPage() {
        productsPage.addItemsToCart(3);
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getCheckoutButton().isDisplayed());
        yourCartPage.clickOnCheckout();
        String expectedText1 = "Checkout: Your Information";
        Assert.assertEquals(checkoutPage.getCheckoutTitle().getText(), expectedText1);
        Assert.assertTrue(checkoutPage.getCancelButton().isDisplayed());
        checkoutPage.clickOnCancelButton();
        String expectedURL = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanClickOnCancelOptionOnCheckoutOverviewPage() {
        productsPage.addItemsToCart(3);
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getCheckoutButton().isDisplayed());
        yourCartPage.clickOnCheckout();
        String expectedText1 = "Checkout: Your Information";
        checkoutPage.enterUserData();
        Assert.assertTrue(checkoutPage.getContinueButton().isDisplayed());
        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(overviewPage.getCancelButton().isDisplayed());
        overviewPage.clickOnCancelButton();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @AfterMethod
    public void removeCookies() {
        resetAppState();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
