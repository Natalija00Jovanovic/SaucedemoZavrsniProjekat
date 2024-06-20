package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OverviewPage {

    WebDriver driver;

    WebElement overviewTitle;

    WebElement cancelButton;

    WebElement finishButton;

    List<WebElement> itemsInCartList;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getOverviewTitle() {
        return driver.findElement(By.className("title"));
    }

    public WebElement getCancelButton() {
        return driver.findElement(By.id("cancel"));
    }

    public WebElement getFinishButton() {
        return driver.findElement(By.id("finish"));
    }

    public List<WebElement> getItemsInCartList() {
        return driver.findElements(By.className("cart_item"));
    }

    //--------------------------------------------

    public void clickOnCancelButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getCancelButton());
        getCancelButton().click();
    }

    public void clickOnFinishButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getFinishButton());
        getFinishButton().click();
    }

}
