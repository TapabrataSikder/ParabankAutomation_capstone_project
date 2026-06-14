package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.OpenAccountPage;
import utilities.ExcelUtils;

public class OpenAccountTest extends BaseTest {
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData() throws Exception {
		return ExcelUtils.getLoginData("src/test/resources/TestData.xlsx", "Sheet2");
	}
	
	@Test(dataProvider = "loginData", dependsOnGroups = "LoginGroup", groups="OpenAccountGroup")
	public void testOpenNewAccount(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(username, password);
		
		OpenAccountPage openAccPage = new OpenAccountPage(driver);
		
		openAccPage.clickOpenAccount();
		openAccPage.openNewAccount("SAVINGS");
		
//		Account Details
		BaseTest.newAccountId = openAccPage.getNewAccountId();
		
//		Output the details
		System.out.println("--- Open Account Test Passed for: ---" + username);
		System.out.println("New Account ID: " + BaseTest.newAccountId);
		
		// Validation
		String exp_title = "ParaBank | Open Account";
		String act_title = driver.getTitle();
		Assert.assertEquals(act_title, exp_title, "Open Account Page not displayed!");		
	}

}
