package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OptionMenuTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        validLogIn();
    }

    @Test
    public void optionMenuAppears() {
        Assert.assertTrue(headerPage.getHamburgerMenu().isDisplayed());
        headerPage.clickOnHamburgerMenu();
        Assert.assertTrue(headerPage.getMenuList().isDisplayed());
        headerPage.clickOnXButton();  // moram da zatvorim meni da bi AfterMethod mogla da se izvrsi, kao i ostali testovi kad ih pustim sve zajedno
    }

    @Test
    public void optionMenuCollapses() throws InterruptedException {
        Assert.assertTrue(headerPage.getHamburgerMenu().isDisplayed());
        headerPage.clickOnHamburgerMenu();
        Assert.assertTrue(headerPage.getMenuList().isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(headerPage.getXButton()));
        Assert.assertTrue(headerPage.getXButton().isDisplayed());
        headerPage.clickOnXButton();
        Thread.sleep(3000);  // ne uspeva sa implicit wait, pa ostavljam Thread.sleep
        Assert.assertFalse(headerPage.getXButton().isDisplayed());
    }

    @Test
    public void allItemsFromProductsPage() {
        String urlBeforeAllItemsClick = driver.getCurrentUrl();
        headerPage.clickOnHamburgerMenu();
        headerPage.clickOnAllItems();
        Assert.assertEquals(driver.getCurrentUrl(), urlBeforeAllItemsClick);
    }

    @Test
    public void allItemsOptionFromCartPage() {
        headerPage.clickOnCartIcon();
        String expectedURL1 = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL1);
        headerPage.clickOnHamburgerMenu();
        headerPage.clickOnAllItems();
        String expectedURL2 = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL2);
    }

    @Test
    public void userCanLogOut() {
        headerPage.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(headerPage.getLogoutOption()));
        Assert.assertTrue(headerPage.getLogoutOption().isDisplayed());
        headerPage.clickOnLogout();
        String expectedURL = "https://www.saucedemo.com/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanOpenAboutPage() {
        headerPage.clickOnHamburgerMenu();
        wait.until(ExpectedConditions.visibilityOf(headerPage.getAboutOption()));
        Assert.assertTrue(headerPage.getAboutOption().isDisplayed());
        headerPage.clickOnAbout();
        String expectedURL = "https://saucelabs.com/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    @Test
    public void userCanResetAppState() throws InterruptedException {
        productsPage.addItemsToCart(3);
        Assert.assertTrue(headerPage.getCartBadge().isDisplayed());
        headerPage.clickOnHamburgerMenu();
        headerPage.clickOnResetAppState();
        Thread.sleep(3000);
        Assert.assertTrue(headerPage.getCartIcon().getText().isBlank());
    }

    @AfterMethod
    public void removeCookies() {  // za ovaj test ne moze da stoji resetAppState() zato sto ne moze da pristupi hamburgerMenu dugmetu, posto je meni otvoren
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}
