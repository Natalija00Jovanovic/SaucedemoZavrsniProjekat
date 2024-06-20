package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YourCartPage {

    WebDriver driver;

    WebElement yourCartTitle;

    WebElement continueShoppingButton;

    WebElement checkoutButton;

    List<WebElement> removeButtonsList;

    List<WebElement> itemsInCartList;

    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getYourCartTitle() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getContinueShoppingButton() {
        return driver.findElement(By.id("continue-shopping"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.id("checkout"));
    }

    public List<WebElement> getRemoveButtonsList() {
        return driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));
    }

    public List<WebElement> getItemsInCartList() {
        return driver.findElements(By.className("inventory_item_name"));
    }

    //-------------------------------------------

    public void clickOnContinueShopping() {
        getContinueShoppingButton().click();
    }

    public void clickOnCheckout() {
        getCheckoutButton().click();
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

    public void clickOnItemInCart() {
        for(int i = 0; i < 1; i++) {
            getItemsInCartList().get(i).click();
        }
    }



}
