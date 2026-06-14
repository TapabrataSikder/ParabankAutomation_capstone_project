package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import org.testng.Assert;
import pages.LoginPage;
import utilities.ExcelUtils;

public class LoginTest extends BaseTest{
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData() throws Exception{
		return ExcelUtils.getLoginData("src/test/resources/TestData.xlsx", "Sheet2");
	}
	
	@Test(dataProvider = "loginData", groups="LoginGroup")
	public void validLogin(String username, String password) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginUser(username, password);
		
//		Validation
		String exp_title = "ParaBank | Accounts Overview";
		String act_title = driver.getTitle();
		Assert.assertEquals(act_title, exp_title, "Login Failed!");
		System.out.println("--- Login Test Passed for: " + username + " ---");
	}
	

}
