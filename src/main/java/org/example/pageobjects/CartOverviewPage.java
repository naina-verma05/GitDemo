package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartOverviewPage extends AbstractComponent {

    WebDriver driver;
    public CartOverviewPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".cartSection h3")
    List<WebElement> cartItems;

    @FindBy(xpath="//li[@class='totalRow']/button")
    WebElement checkoutButton;

    public List<WebElement> getCartItemLists(){
        return cartItems;
    }

    public Boolean verifyCartItems(String productName){
        Boolean match = getCartItemLists().stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
        System.out.println("The Product : "+productName+" added to Cart is available in Checkout Page");
       return match;
    }

    public CheckoutPage proceedToCheckoutPage(){
        scrollToElement(checkoutButton);
        scrollToBottom();
        checkoutButton.click();

        CheckoutPage cp = new CheckoutPage(driver);
        return cp;
    }
}
