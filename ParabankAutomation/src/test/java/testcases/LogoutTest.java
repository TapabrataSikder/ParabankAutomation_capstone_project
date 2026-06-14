package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LogoutPage;

public class LogoutTest extends BaseTest {
	
	@Test(dependsOnGroups = {"BillPaymentGroup", "TransferFundsGroup"}, groups = "LogoutGroup")
	public void testLogout() {
		
		LogoutPage logoutPage = new LogoutPage(driver);
		logoutPage.clickLogout();
		
		// Validation: Ensure we are back on the login page (or main page)
		boolean isLoginButtonVisible = !driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty();
        
		Assert.assertTrue(isLoginButtonVisible, "Logout failed: Login button not found on page!");
	    System.out.println("--- Logout Successful ---");
	}

}
