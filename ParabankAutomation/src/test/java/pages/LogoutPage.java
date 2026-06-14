package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {
	private WebDriver driver;
    private WebDriverWait wait;
    
    By logoutLink = By.xpath("//a[text()='Log Out']");
    
    public LogoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
    public void clickLogout() {
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        } catch (Exception e) {
        	System.out.println("Logout link not found. Assuming already logged out.");
        }
    	
    }
}
