package org.example.pageobjects;

import org.example.Reusablecomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {
    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'mb-3 ng-star-inserted')]")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;



    By productsBy = By.xpath("//div[contains(@class, 'mb-3 ng-star-inserted')]");
    By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");
    By cartButton = By.xpath("//button[contains(@routerlink, 'cart')]");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        System.out.println(prod.findElement(By.cssSelector("b")).getText());

        return prod;
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCartButton).click();
        waitForElementToAppear(toastMessage);
        //waitForElementToDisappear(spinner);
        waitForElementToAppear(cartButton);
    }
}
