package org.example.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageobjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StandaloneTest {
    public static void main(String[] args) throws InterruptedException {

        String productName = "ZARA COAT 3";
        String country = "India";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://rahulshettyacademy.com/client");

        LandingPage lp = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("nancy.verma0591@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Ndiljuly@2025");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3 ng-star-inserted')]")));

        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'mb-3 ng-star-inserted')]"));

        WebElement prod = elements.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        System.out.println(prod.findElement(By.cssSelector("b")).getText());
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

        WebDriverWait w = new WebDriverWait(driver, 10);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@routerlink, 'cart')]")));

        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[contains(@routerlink, 'cart')]")));
        //driver.findElement(By.xpath("//button[contains(@routerlink, 'cart')]")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        WebElement e = driver.findElement(By.cssSelector(".totalRow button"));

        js.executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();

        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(country);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted']")));

        List<WebElement> matchingCountries = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));

        WebElement con = matchingCountries.stream().filter(c->c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);

        con.click();

        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

        String actualMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();

        Assert.assertTrue(actualMessage.equalsIgnoreCase("Thankyou for the order."));

    }
}
