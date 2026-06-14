package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.AccountOverviewPage;
import pages.LoginPage;
import pages.TransferFundsPage;
import utilities.ExcelUtils;

public class TransferFundsTest extends BaseTest {
	
	@DataProvider(name="transferData")
	public Object[][] getTransferData() throws Exception {
		return ExcelUtils.getLoginData("src/test/resources/TestData.xlsx", "Sheet2");
	}

	@Test(dataProvider = "transferData", dependsOnGroups = "AccountOverviewGroup", groups="TransferFundsGroup")
	public void testTransferFunds(String username, String password) {
		Assert.assertNotNull(BaseTest.capturedAccountId, "Source account is null!");
	    Assert.assertNotNull(BaseTest.newAccountId, "Destination account is null!");
		
		LoginPage loginPage = new LoginPage(driver);
	     loginPage.loginUser(username, password);
	     
	     // Check Current balances before transfer
	     AccountOverviewPage accPage = new AccountOverviewPage(driver);
	     String balStr = accPage.getBalance(1).replace("$", "").replace(",", "").trim();
	     double currentBal = Double.parseDouble(balStr);
	     double transferAmt = 50.00;
	     
	     
	     try {
	    	 if(transferAmt > currentBal) {
	    		 System.out.println("--- Insufficient funds for transfer! Current Balance: $" + currentBal + " ---");
	    		 Assert.fail("Insufficient funds for transfer!");
	    	 }
	     } catch(Exception e) {
	    	 System.out.println("--- Error during balance check: " + e.getMessage() + " ---");
	    	 throw e; // Rethrow to fail the test
	     }
   
        // Transfer
        TransferFundsPage tfPage = new TransferFundsPage(driver);
        tfPage.clickTansferFund();        
        tfPage.transferFunds(String.valueOf(transferAmt), BaseTest.capturedAccountId, BaseTest.newAccountId);

        // Validation
        String actualMsg = tfPage.getSuccessMessage();
        Assert.assertTrue(actualMsg.contains("Complete"), "Fund transfer failed!");
        
        System.out.println("--- Fund Transfer Test Passed for user: " + username + " ---");
        System.out.println("--- Fund Transfer Success for: " + BaseTest.capturedAccountId + " ---");
        
        driver.findElement(By.linkText("Accounts Overview")).click();
        
        String updatedPrimaryBal = accPage.getBalance(1);
        String updatedSecondaryBal = accPage.getBalance(2);
        
        System.out.println("--- Balance after Fund-Transfer  ---");
        System.out.println("Updated Primary Balance: " + updatedPrimaryBal);
        System.out.println("Updated Secondary Balance: " + updatedSecondaryBal);
        
        // Validation after fund transfer
        Assert.assertTrue(updatedPrimaryBal.contains("$"), "Primary balance format invalid!");
	}
}
