package org.example.Tests;

import org.example.TestComponents.BaseTest;
import org.example.TestComponents.Retry;
import org.example.pageobjects.CartOverviewPage;
import org.example.pageobjects.ProductCataloguePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"errorHandling"}, retryAnalyzer = Retry.class)
    public void loginEmailErrorValidation(){
        ProductCataloguePage pcp =  lp.loginToApplication("nancy.verma0591gmail.com", "Ndiljuly@225");
        //Assert.assertEquals("*Enter Valid Email", lp.getErrorMessage());
        Assert.assertEquals("*Enter Valid Email", lp.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        String country = "India";

        ProductCataloguePage pcp =  lp.loginToApplication("naina.verma0591@gmail.com", "Ndiljuly@2025");

        pcp.addProductToCart(productName);
        CartOverviewPage cop = pcp.goToCartPage();
        Assert.assertFalse(cop.verifyCartItems("ZARA COAT 33"));
    }
}
