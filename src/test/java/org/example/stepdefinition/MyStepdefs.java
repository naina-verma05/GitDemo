package org.example.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TestComponents.BaseTest;
import org.example.pageobjects.*;
import org.testng.Assert;

import java.io.IOException;

public class MyStepdefs extends BaseTest {
    
    public LandingPage landingPage;
    public ProductCataloguePage productCatalogue;
    public OrderConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Home Page")
    public void i_Landed_On_Ecommerce_HomePage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("I Logged in with {string} and {string}")
    public void iLoggedInWithAnd(String email, String password) {
        productCatalogue = lp.loginToApplication(email, password);
    }

    @When("^I add product (.+) to the cart$")
    public void iAddProductToTheCart(String product) {
        productCatalogue.addProductToCart(product);
    }

    @And("I checkout {string} and submit the order")
    public void iCheckoutAndSubmitTheOrder(String product) throws InterruptedException {
        CartOverviewPage cartPage = productCatalogue.goToCartPage();
        Assert.assertTrue(cartPage.verifyCartItems(product));
        CheckoutPage cp = cartPage.proceedToCheckoutPage();
        confirmationPage = cp.enterCountryDetailsAndPlaceOrder("india");
    }

    @Then("I verify the {string} message is displayed on the screen.")
    public void iVerifyTheMessageIsDisplayedOnTheScreen(String message) {
        Boolean result = confirmationPage.verifyOrderConfirmationMessage("Thankyou for the order.");
        Assert.assertTrue(result);
    }


    @Then("I verify the error message.")
    public void iVerifyTheErrorMessage() {
        Assert.assertEquals("*Enter Valid Email", landingPage.getErrorMessage());
    }
}
