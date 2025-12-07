package org.example.cucmber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/org/example/cucmber",
                    glue = "org.example.stepdefinition",
                    monochrome = true,
                    tags = "@Error",
                    plugin = {"html:target/cucumber.html"}
                )
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
