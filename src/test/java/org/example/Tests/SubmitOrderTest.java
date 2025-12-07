package org.example.Tests;

import org.apache.commons.io.FileUtils;
import org.example.TestComponents.BaseTest;
import org.example.pageobjects.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";
    String country = "India";

    @Test(dataProvider = "getData", groups={"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
        ProductCataloguePage pcp =  lp.loginToApplication(input.get("email"), input.get("pass"));
        pcp.addProductToCart(input.get("product"));
        CartOverviewPage cop = pcp.goToCartPage();
        Assert.assertTrue(cop.verifyCartItems(input.get("product")));
        CheckoutPage cp = cop.proceedToCheckoutPage();
        OrderConfirmationPage ocp = cp.enterCountryDetailsAndPlaceOrder(country);
        Boolean result = ocp.verifyOrderConfirmationMessage("Thankyou for the order.");
        Assert.assertTrue(result);
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() throws InterruptedException {
        ProductCataloguePage pcp =  lp.loginToApplication("nancy.verma0591@gmail.com", "Ndiljuly@2025");
        OrderPage op = lp.goToOrderPage();
        Assert.assertTrue(op.verifyOrderDetails(productName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {

        /****************** Using HashMap ******************/
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "nancy.verma0591@gmail.com");
//        map1.put("pass", "Ndiljuly@2025");
//        map1.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map2 = new HashMap<String, String>();
//        map2.put("email", "naina.verma0591@gmail.com");
//        map2.put("pass", "Ndiljuly@2025");
//        map2.put("product", "ADIDAS ORIGINAL");

        List<HashMap<String, String>> list = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\org\\example\\data\\PurchaseOrder.json");
        return new Object[][]{{list.get(0)}, {list.get(1)}};
    }

}
