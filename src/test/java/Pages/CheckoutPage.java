package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver driver;

    WebElement firstNameField;

    WebElement lastNameField;

    WebElement zipPostalCodeField;

    WebElement cancelButton;

    WebElement continueButton;

    WebElement checkoutTitle;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstNameField() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement getLastNameField() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement getZipPostalCodeField() {
        return driver.findElement(By.id("postal-code"));
    }

    public WebElement getCancelButton() {
        return driver.findElement(By.id("cancel"));
    }

    public WebElement getContinueButton() {
        return driver.findElement(By.id("continue"));
    }

    public WebElement getCheckoutTitle() {
        return driver.findElement(By.className("title"));
    }

    //----------------------------------------------------

    public void enterFirstName(String firstName) {
        getFirstNameField().clear();
        getFirstNameField().sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        getLastNameField().clear();
        getLastNameField().sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        getZipPostalCodeField().clear();
        getZipPostalCodeField().sendKeys(postalCode);
    }

    public void clickOnCancelButton() {
        getCancelButton().click();
    }

    public void clickOnContinueButton() {
        getContinueButton().click();
    }

    public void enterUserData() {
        enterFirstName("Pera");
        enterLastName("Peric");
        enterPostalCode("12345");
    }

}
