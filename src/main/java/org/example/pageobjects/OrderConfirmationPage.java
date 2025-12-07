package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends AbstractComponent {
    WebDriver driver;
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement actualConfirfmationMessage;

    public Boolean verifyOrderConfirmationMessage(String s){
        scrollToElement(actualConfirfmationMessage);
        Boolean match = actualConfirfmationMessage.getText().equalsIgnoreCase(s);
        return match;
    }
}
