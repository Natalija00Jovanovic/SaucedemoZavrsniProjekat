package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterPage {

    WebDriver driver;

    WebElement twitterIcon;

    WebElement facebookIcon;

    WebElement linkedInIcon;

    public FooterPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTwitterIcon() {
        return driver.findElement(By.linkText("Twitter"));
    }

    public WebElement getFacebookIcon() {
        return driver.findElement(By.linkText("Facebook"));
    }

    public WebElement getLinkedInIcon() {
        return driver.findElement(By.linkText("LinkedIn"));
    }

    //------------------------------------------------------------

    public void clickOnTwitterIcon() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getTwitterIcon());
        getTwitterIcon().click();
    }

    public void clickOnFacebookIcon() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getFacebookIcon());
        getFacebookIcon().click();
    }

    public void clickOnLinkedInIcon() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getLinkedInIcon());
        getLinkedInIcon().click();
    }

}
