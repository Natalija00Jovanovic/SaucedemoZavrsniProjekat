package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void cartIconLeadsFromProductsPageToCartPage() {
        Assert.assertTrue(headerPage.getCartIcon().isDisplayed());
        headerPage.clickOnCartIcon();
        String expectedURL = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void cartIconLeadsFromItemPageToCartPage()  {
        productsPage.clickOnBackpackItem();
        Assert.assertTrue(headerPage.getCartIcon().isDisplayed());
        headerPage.clickOnCartIcon();
        String expectedURL = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanRemoveItemsFromCartFromCartPage() {
        productsPage.addItemsToCart(3);
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
        String firstNumber = headerPage.getCartBadge().getText();
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getYourCartTitle().isDisplayed());
        yourCartPage.removeItemsFromCart(1);
        String secondNumber = headerPage.getCartBadge().getText();
        isNotEqual(firstNumber, secondNumber);
    }

    @Test
    public void clickOnItemNameFromCartPageLeadsToItemPage() {
        productsPage.addItemsToCart(3);
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getYourCartTitle().isDisplayed());
        yourCartPage.clickOnItemInCart();
        Assert.assertTrue(itemPage.getBackButton().isDisplayed());
    }

    @Test
    public void continueShoppingOptionLeadsToProductsPage() {
        productsPage.addItemsToCart(3);
        headerPage.clickOnCartIcon();
        Assert.assertTrue(yourCartPage.getYourCartTitle().isDisplayed());
        Assert.assertTrue(yourCartPage.getContinueShoppingButton().isDisplayed());
        yourCartPage.clickOnContinueShopping();
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
