
//    This parent class will hold generic utilities like:
//    Wait for element
//    Switch windows
//    Handle alerts
//    Frames
//    JavaScript utilities
//    Reusable actions
//    Each page object class should inherit this class to access reusable methods without duplicating code.

package org.example.Reusablecomponents;

import org.example.pageobjects.CartOverviewPage;
import org.example.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@routerlink, 'cart')]")
    WebElement cartHeader;

    @FindBy(xpath = "//button[contains(@routerlink, 'myorders')]")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findby){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForElementToAppear(WebElement e){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForElementToDisappear(WebElement e){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOf(e));
    }

    public void clickUsingJS(WebElement e){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",e);
    }

    public void scrollToElement(WebElement e){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",e);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /******************************* Reusable methods
     * @return********************************/

    public CartOverviewPage goToCartPage() throws InterruptedException {
         Thread.sleep(3000);
         cartHeader.click();

        CartOverviewPage cop = new CartOverviewPage(driver);
        return cop;
    }

    public OrderPage goToOrderPage() throws InterruptedException {
        Thread.sleep(3000);
        ordersHeader.click();

        OrderPage op = new OrderPage(driver);
        return op;
    }

}
