package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BillPayPage {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
    By billPayLink = By.linkText("Bill Pay");
    By pageTitle = By.xpath("//h1[text()='Bill Payment Service']");
    By payeeName = By.name("payee.name");
    By payeeAddress = By.name("payee.address.street");
    By payeeCity = By.name("payee.address.city");
    By payeeState = By.name("payee.address.state");
    By payeeZipCode = By.name("payee.address.zipCode");
    By payeePhone = By.name("payee.phoneNumber");
    By payeeAccount = By.name("payee.accountNumber");
    By verifyPayeeAccount = By.name("verifyAccount");
    By amount = By.name("amount");
    By fromAccount = By.name("fromAccountId");
    By sendPaymentBtn = By.xpath("//input[@value='Send Payment']");
    By successMessage = By.xpath("//h1[text()='Bill Payment Complete']");
    
    
    public BillPayPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToBillPay() {
		wait.until(ExpectedConditions.elementToBeClickable(billPayLink)).click();
	}
    
    public String getPageTitle() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
		return driver.findElement(pageTitle).getText();
	}
	
	public void fillBillPayForm(String name, String address, String city, String state, String zipCode, String phone, String account, String verifyAccount, String amountValue, String fromAccountId) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(payeeName));
		driver.findElement(payeeName).sendKeys(name);
		driver.findElement(payeeAddress).sendKeys(address);
		driver.findElement(payeeCity).sendKeys(city);
		driver.findElement(payeeState).sendKeys(state);
		driver.findElement(payeeZipCode).sendKeys(zipCode);
		driver.findElement(payeePhone).sendKeys(phone);
		driver.findElement(payeeAccount).sendKeys(account);
		driver.findElement(verifyPayeeAccount).sendKeys(verifyAccount);
		driver.findElement(amount).sendKeys(amountValue);
		
		Select fromAccountDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount)));
		fromAccountDropdown.selectByVisibleText(fromAccountId);
	}
	
	public void submitPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(sendPaymentBtn)).click();
	}

	public void waitForConfirmation() {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
	}
}
