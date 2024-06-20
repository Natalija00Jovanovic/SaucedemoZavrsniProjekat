package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddingAndRemovingItemsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void userCanAddItemsToCartFromProductsPage() throws InterruptedException {
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
        Thread.sleep(3000);  // bez wait-a, items se odmah dodaju u korpu i javlja se cartBadge, pa asertacija bude false i test pada
        productsPage.addItemsToCart(3);
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
    }

    @Test
    public void itemNumberOnCartBadgeUpdatesWhenAddingItems() {
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
        productsPage.addItemsToCart(1);
        String firstNumber = headerPage.getCartBadge().getText();
        productsPage.addItemsToCart(1);
        String secondNumber = headerPage.getCartBadge().getText();
        isNotEqual(firstNumber, secondNumber);
    }

    @Test
    public void userCanRemoveItemsFromCartFromProductsPage() throws InterruptedException {
        productsPage.addItemsToCart(3);
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
        productsPage.removeItemsFromCart(3);
        Thread.sleep(3000);
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
    }

    @Test
    public void itemNumberOnCartBadgeUpdatesWhenRemovingItems() {
        productsPage.addItemsToCart(3);
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
        String firstNumber = headerPage.getCartBadge().getText();
        productsPage.removeItemsFromCart(1);
        String secondNumber = headerPage.getCartBadge().getText();
        isNotEqual(firstNumber, secondNumber);
    }

    @Test
    public void userCanAddItemToCartFromItemPage() {
        productsPage.clickOnBackpackItem();
        String expectedURL = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
    }

    @Test
    public void userCanRemoveItemFromCartFromItemPage() {
        productsPage.clickOnBackpackItem();
        String expectedURL = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
        Assert.assertTrue(itemPage.getRemoveButton().isDisplayed());
        itemPage.clickOnRemoveButton();
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
    }

    @AfterMethod
    public void removeCookies() {
        resetAppState();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
