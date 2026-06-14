package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	private By regLink = By.linkText("Register");
	private By firstName = By.id("customer.firstName");
	private By lastName = By.id("customer.lastName");
	private By address = By.id("customer.address.street");
	private By city = By.id("customer.address.city");
	private By state = By.id("customer.address.state");
	private By zip = By.id("customer.address.zipCode");
	private By phone = By.id("customer.phoneNumber");
	private By ssnField = By.id("customer.ssn");
	private By username = By.id("customer.username");
	private By password = By.id("customer.password");
	private By cnfpassword = By.id("repeatedPassword");
	private By registerButton = By.xpath("//input[@value='Register']");
	
	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void register(String fname, String lname, String adr, String cty, String st, String zp, String phn, String ssnNum, String user, String pwd, String cnfpwd) {
		wait.until(ExpectedConditions.elementToBeClickable(regLink)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
		driver.findElement(lastName).sendKeys(lname);
		driver.findElement(address).sendKeys(adr);
		driver.findElement(city).sendKeys(cty);
		driver.findElement(state).sendKeys(st);
		driver.findElement(zip).sendKeys(zp);
		driver.findElement(phone).sendKeys(phn);
		driver.findElement(ssnField).sendKeys(ssnNum);
		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(cnfpassword).sendKeys(cnfpwd);
		
		driver.findElement(registerButton).click();
		
	}
	
	public String confirmRegister() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='title']"))).getText();
	}
	
}