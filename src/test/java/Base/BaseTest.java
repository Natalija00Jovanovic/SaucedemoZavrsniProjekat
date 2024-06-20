package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;

    public WebDriverWait wait;

    public LoginPage loginPage;

    public HeaderPage headerPage;

    public ProductsPage productsPage;

    public ItemPage itemPage;

    public YourCartPage yourCartPage;

    public CheckoutPage checkoutPage;

    public OverviewPage overviewPage;

    public CheckoutCompletePage checkoutCompletePage;

    public FooterPage footerPage;

    public String mainWindow;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        headerPage = new HeaderPage(driver);
        productsPage = new ProductsPage(driver);
        itemPage = new ItemPage(driver);
        yourCartPage = new YourCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        footerPage = new FooterPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    public void validLogIn() {
        loginPage.enterUsernameInUsernameField("standard_user");
        loginPage.enterPasswordInPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();
    }

    public boolean isNotEqual(String a, String b) {
        if(a != b) {
            return true;
        } else {
            return false;
        }
    }

    public void switchToNewWindow() {
        closeAllWindows();
        for(String window: driver.getWindowHandles()) {
            if(!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void closeAllWindows() {
        for(String window: driver.getWindowHandles()) {
            if(window.equals(mainWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
            break;
        }
    }

    public void resetAppState() {
        headerPage.clickOnHamburgerMenu();
        headerPage.clickOnResetAppState();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
