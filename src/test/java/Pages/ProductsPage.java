package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    WebDriver driver;

    List<WebElement> inventoryItems;

    List<WebElement> addToCartButtonsList;

    List<WebElement> removeButtonsList;

    WebElement backpackItem;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getInventoryItems() {
        return driver.findElements(By.className("inventory_item"));
    }

    public List<WebElement> getAddToCartButtonsList() {
        return driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
    }

    public List<WebElement> getRemoveButtonsList() {
        return driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"));
    }

    public WebElement getBackpackItem() {
        return driver.findElement(By.id("item_4_title_link"));
    }

    //--------------------------------------------------

    public void addItemsToCart(int a) {
        if(a > getAddToCartButtonsList().size()) {
            System.out.println("Not enough items on the page, enter another number of items.");
        } else {
            for(int i = a; i > 0; i--) {
                getAddToCartButtonsList().get(i).click();
            }
        }
    }

    public void removeItemsFromCart(int a) {
        if(a > getRemoveButtonsList().size()) {
            System.out.println("There aren't that many items in the cart.");
        } else {
            for(int i = (a - 1); i >= 0; i--) {
                getRemoveButtonsList().get(i).click();
            }
        }
    }

    public void clickOnBackpackItem() {
        getBackpackItem().click();
    }

}
