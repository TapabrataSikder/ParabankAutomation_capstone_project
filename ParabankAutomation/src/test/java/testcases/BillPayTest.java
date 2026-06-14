package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AccountOverviewPage;
import pages.BillPayPage;
import pages.LoginPage;
import utilities.ExcelUtils;
import utilities.ScreenshotUtil;

public class BillPayTest extends BaseTest {
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData() throws Exception {
		return ExcelUtils.getLoginData("src/test/resources/TestData.xlsx", "Sheet2");
	}
	
	@Test(dataProvider = "loginData", dependsOnGroups = "AccountOverviewGroup", groups="BillPaymentGroup")
	public void testBillPayment(String username, String password) {
		 LoginPage loginPage = new LoginPage(driver);
	     loginPage.loginUser(username, password);
	     
//	     Balance Check
	     AccountOverviewPage accPage = new AccountOverviewPage(driver);
	     String balStr = accPage.getBalance(1).replace("$", "").replace(",", "").trim();
	     double currentBal = Double.parseDouble(balStr);
	     double paymentAmt = 20.00;
	     
//	     Bill Pay
	     BillPayPage billPayPage = new BillPayPage(driver);
	     billPayPage.navigateToBillPay();	     
	     Assert.assertEquals(billPayPage.getPageTitle(), "Bill Payment Service", "Failed to navigate to Bill Pay page!");
	    
	     try {
	    	 if (currentBal >= paymentAmt) {
		          
		           billPayPage.fillBillPayForm("testPayee", "321 Down Street", "Howrah", "Bengal", 
		                                        "90210", "555-1234", "1234567890", "1234567890", 
		                                        String.valueOf(paymentAmt), BaseTest.capturedAccountId);
		           billPayPage.submitPayment();
		           
		           billPayPage.waitForConfirmation();
		            
		           // Screenshot on Success
		           ScreenshotUtil.takeScreenshot(driver, "BillPay_Success_" + username);
		           System.out.println("--- Bill Payment Successful for: " + username + " ---");
		           
//		           Check Updated Balance
		           driver.findElement(By.linkText("Accounts Overview")).click();
		           String updatedBalStr = accPage.getBalance(1).replace("$", "").replace(",", "").trim();
		           double updatedBal = Double.parseDouble(updatedBalStr);
		           System.out.println("Current Balance: $" + currentBal + "\nPayment Amount: $" + paymentAmt + "\nUpdated Balance: $" + updatedBal);
		           System.out.println("---------------------------");
		           
		           Assert.assertEquals(updatedBal, currentBal - paymentAmt, "Balance did not update correctly after bill payment!");
		        } else {
		            throw new Exception("Insufficient balance. Required: " + paymentAmt + ", Available: " + currentBal);
		        }
	     
	     } catch (Exception e) {
	    	 System.out.println("--- Bill Pay Exception Handled: " + e.getMessage() + " ---");
		     
	    	 // Screenshot on Failure/Exception
		     ScreenshotUtil.takeScreenshot(driver, "BillPay_Failure_" + username);
	     }
	}
}
