package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EndToEndCheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void userCanCheckoutWithItemsInCart() {
        productsPage.addItemsToCart(3);
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getCheckoutButton().isDisplayed());
        yourCartPage.clickOnCheckout();
        String expectedText1 = "Checkout: Your Information";
        Assert.assertEquals(checkoutPage.getCheckoutTitle().getText(), expectedText1);
        checkoutPage.enterUserData();
        Assert.assertTrue(checkoutPage.getContinueButton().isDisplayed());
        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(overviewPage.getItemsInCartList().get(0).isDisplayed());
        Assert.assertTrue(overviewPage.getFinishButton().isDisplayed());
        overviewPage.clickOnFinishButton();
        String expectedText2 = "Checkout: Complete!";
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteTitle().getText(), expectedText2);
        Assert.assertTrue(checkoutCompletePage.getBackHomeButton().isDisplayed());
    }

    @Test
    public void userCanCheckoutWithNoItemsInCart() {
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getCheckoutButton().isDisplayed());
        yourCartPage.clickOnCheckout();
        String expectedText1 = "Checkout: Your Information";
        Assert.assertEquals(checkoutPage.getCheckoutTitle().getText(), expectedText1);
        checkoutPage.enterUserData();
        Assert.assertTrue(checkoutPage.getContinueButton().isDisplayed());
        checkoutPage.clickOnContinueButton();
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
        Assert.assertTrue(overviewPage.getFinishButton().isDisplayed());
        overviewPage.clickOnFinishButton();
        String expectedText2 = "Checkout: Complete!";
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteTitle().getText(), expectedText2);
        Assert.assertTrue(checkoutCompletePage.getBackHomeButton().isDisplayed());
    }

    @AfterMethod
    public void removeCookies() {
        resetAppState();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
