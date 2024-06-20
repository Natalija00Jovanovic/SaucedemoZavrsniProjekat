package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage {

    WebDriver driver;

    WebElement checkoutCompleteTitle;

    WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCheckoutCompleteTitle() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getBackHomeButton() {
        return driver.findElement(By.id("back-to-products"));
    }

    //----------------------------------------------------

    public void clickOnBackHomeButton() {
        getBackHomeButton().click();
    }

}
