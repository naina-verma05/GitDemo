package org.example.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pageobjects.LandingPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public LandingPage lp;
    public WebDriver inititalizeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\resources\\GlobalData.properties");
        prop.load(fis);

        System.getProperty("browser");
        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : prop.getProperty("browser");

        if (browserName.contains("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if (browserName.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1440,900");
            }

            driver = new ChromeDriver(options);
        }
        else if(browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }


    public String getScrenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File desc = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
        FileUtils.copyFile(src, desc);
        return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
    }

    /******************* Method to fetch data from JSON File *************************/
    public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
        //read JSON to String
        String jsonContent = FileUtils.readFileToString(new File(FilePath));

        //Convert String to HashMap - need Jackson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        //return the list of hashMap
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = inititalizeDriver();
        lp = new LandingPage(driver);
        lp.goToLandingPage();
        return lp;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
