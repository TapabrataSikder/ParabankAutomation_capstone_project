package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	By totalBalance = By.xpath("//td[contains(normalize-space(), 'Total')]/following-sibling::td[1]");

	public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public String getAccountId(int index) { // index-row value for any no.of accounts
    	By accountId = By.xpath("//table[@id='accountTable']//tr[" + index + "]/td[1]/a");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountId)).getText();
    }

    public String getBalance(int index) {
    	By balance = By.xpath("//table[@id='accountTable']//tr[" + index + "]/td[2]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(balance)).getText();
    }

    public String getTotalBalance() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalBalance)).getText();
    }
}
