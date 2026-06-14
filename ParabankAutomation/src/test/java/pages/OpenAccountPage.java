package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenAccountPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	By openAccountLink = By.linkText("Open New Account");
	By accountTypeSelect = By.id("type");
	
	By newAccountId = By.id("newAccountId");
	
	public OpenAccountPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}
	
	public void clickOpenAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(openAccountLink)).click();
	}
	
	public void openNewAccount(String accountType) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeSelect)).sendKeys(accountType);
		
		// handling HTML <select> dropdown
		Select fromDropdown = new Select(driver.findElement(accountTypeSelect));
		fromDropdown.selectByVisibleText(accountType);
		
		driver.findElement(By.xpath("//input[@value='Open New Account']")).click();
	}
	
	public String getNewAccountId() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountId)).getText();
	}
}
