package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ItemPageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        validLogIn();
    }

    @Test
    public void userCanOpenItemPage() {
        Assert.assertTrue(productsPage.getBackpackItem().isDisplayed());
        productsPage.clickOnBackpackItem();
        String expectedURL = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanUseBackToProductsButton() {
        Assert.assertTrue(productsPage.getBackpackItem().isDisplayed());
        productsPage.clickOnBackpackItem();
        String expectedURL1 = "https://www.saucedemo.com/inventory-item.html?id=4";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL1);
        Assert.assertTrue(itemPage.getBackButton().isDisplayed());
        itemPage.clickOnBackButton();
        String expectedURL2 = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL2);
    }

    @AfterMethod
    public void removeCookies() {
        resetAppState();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
