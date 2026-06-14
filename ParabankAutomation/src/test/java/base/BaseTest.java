package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import utilities.ConfigReader;


@Listeners(utilities.TestListener.class)
public class BaseTest {
	public static WebDriver driver;  // to ensure that all test classes share same browser and prevent multiple browser launch
	public static String capturedAccountId;
	public static String newAccountId;
	
	@BeforeMethod
	public void setup() throws Exception {

		driver = new ChromeDriver();				
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = ConfigReader.getProperty("appurl");
        driver.get(url);
	}
	
	public static WebDriver getDriver() {
        return driver;
    }
	
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}