package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderPage {

    WebDriver driver;

    WebElement cartIcon;

    WebElement cartBadge;

    WebElement hamburgerMenu;

    WebElement xButton;

    WebElement menuList;

    WebElement allItemsOption;

    WebElement aboutOption;

    WebElement logoutOption;

    WebElement resetAppStateOption;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCartIcon() {
        return driver.findElement(By.className("shopping_cart_link"));
    }

    public WebElement getCartBadge() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public WebElement getHamburgerMenu() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getXButton() {
        return driver.findElement(By.id("react-burger-cross-btn"));
    }

    public WebElement getMenuList() {
        return driver.findElement(By.className("bm-menu"));
    }

    public WebElement getAllItemsOption() {
        return driver.findElement(By.id("inventory_sidebar_link"));
    }

    public WebElement getAboutOption() {
        return driver.findElement(By.id("about_sidebar_link"));
    }

    public WebElement getLogoutOption() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement getResetAppStateOption() {
        return driver.findElement(By.id("reset_sidebar_link"));
    }

    //----------------------------------------------------------------

    public void clickOnCartIcon() {
        getCartIcon().click();
    }

    public void clickOnHamburgerMenu() {
        getHamburgerMenu().click();
    }

    public void clickOnXButton() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getXButton());
    }

    public void clickOnAllItems() {
        getAllItemsOption().click();
    }

    public void clickOnAbout() {
        getAboutOption().click();
    }

    public void clickOnLogout() {
        getLogoutOption().click();
    }

    public void clickOnResetAppState() {
        getResetAppStateOption().click();
    }

}
