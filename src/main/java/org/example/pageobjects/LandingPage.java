package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(how = How.ID, using = "userEmail")
    WebElement uEmail;

    @FindBy(id="userPassword")
    WebElement uPassword;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(xpath="//div[@class='invalid-feedback']/div")
    WebElement errorMessage;

    public void goToLandingPage(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCataloguePage loginToApplication(String email, String pass){
        uEmail.sendKeys(email);
        uPassword.sendKeys(pass);
        loginButton.click();

        ProductCataloguePage pcp = new ProductCataloguePage(driver);
        return pcp;
    }

    public String getErrorMessage(){
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();

    }
}
