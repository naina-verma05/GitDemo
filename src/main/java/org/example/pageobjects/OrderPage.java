package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
    List<WebElement> ordersList;

    public Boolean verifyOrderDetails(String productName){
        Boolean match = ordersList.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
        System.out.println("The Product : "+productName+" is available in Orders Page");
        return match;
    }

}
