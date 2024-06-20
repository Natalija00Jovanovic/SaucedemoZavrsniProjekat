package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemPage {

    WebDriver driver;

    WebElement backButton;

    WebElement addToCartButton;

    WebElement removeButton;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBackButton() {
        return driver.findElement(By.id("back-to-products"));
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.id("add-to-cart"));
    }

    public WebElement getRemoveButton() {
        return driver.findElement(By.id("remove"));
    }

    //---------------------------------------

    public void clickOnBackButton() {
        getBackButton().click();
    }

    public void clickOnAddToCartButton() {
        getAddToCartButton().click();
    }

    public void clickOnRemoveButton() {
        getRemoveButton().click();
    }

}
