package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement SelectCountry;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    List<WebElement> matchingCountries;

    @FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
    WebElement placeOrderButton;

    By firstMatchingCountry = By.xpath("//span[@class='ng-star-inserted']");
    By selectCountryTextbox = By.xpath("//input[@placeholder='Select Country']");

    public OrderConfirmationPage enterCountryDetailsAndPlaceOrder(String country){
        waitForElementToAppear(selectCountryTextbox);
        SelectCountry.click();
        SelectCountry.sendKeys(country);
        waitForElementToAppear(firstMatchingCountry);
        WebElement con = matchingCountries.stream().filter(c->c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
        con.click();
        placeOrderButton.click();

        OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
        return ocp;
    }

}
