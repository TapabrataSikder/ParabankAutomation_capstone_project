package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	By transferLink = By.linkText("Transfer Funds");
	By amountField = By.id("amount");
	By fromAccountSelect = By.id("fromAccountId");
	By toAccountSelect = By.id("toAccountId");
	By transferButton = By.xpath("//input[@value='Transfer']");
    By successMessage = By.xpath("//h1[text()='Transfer Complete!']");
    
    public TransferFundsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}
    
    public void clickTansferFund() {
    	wait.until(ExpectedConditions.elementToBeClickable(transferLink)).click();
    }
    
    public void transferFunds(String amount, String fromAcc, String toAcc) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(amountField)).sendKeys(amount);
        
        Select fromDropdown = new Select(driver.findElement(fromAccountSelect));
        fromDropdown.selectByVisibleText(fromAcc);
        
        Select toDropdown = new Select(driver.findElement(toAccountSelect));
        toDropdown.selectByVisibleText(toAcc);
        
        driver.findElement(transferButton).click();
    }
    
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
    	
}
