package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	By user = By.name("username");
	By pass = By.name("password");
	By submit = By.xpath("//input[@value='Log In']");
	
	
	// Constructor 
	public LoginPage(WebDriver driver) {
		this.driver=driver; // current class member variable 
		// as soon as the LoginPage object created, wait is ready to be used
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	
	public void loginUser(String uname, String pwd) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(user)).sendKeys(uname);
		driver.findElement(pass).sendKeys(pwd);
		driver.findElement(submit).click();

	}
	
	public boolean isLoginErrorDisplayed() {
	    return driver.findElements(By.className("error")).size() > 0;
	}

}