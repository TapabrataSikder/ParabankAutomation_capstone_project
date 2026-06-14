package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.RegistrationPage;
import utilities.ExcelUtils;

public class RegistrationTest extends BaseTest {
	
	@DataProvider(name="regData")
	public Object[][] getdata() throws Exception{
		return ExcelUtils.getTestData("src/test/resources/TestData.xlsx", "Sheet1");
	}
	
	@Test(dataProvider = "regData")
	public void testRegistration(String fname, String lname, String adr, String cty, String st, String zp, String phn, String ssn, String user, String pwd, String cnfpwd) {
	    RegistrationPage regPage = new RegistrationPage(driver);
	    regPage.register(fname, lname, adr, cty, st, zp, phn, ssn, user, pwd, cnfpwd);
	    
//	    Validation
	    String act_msg = regPage.confirmRegister();
	    String exp_msg = "Welcome " + user;
	    
	    Assert.assertEquals(act_msg, exp_msg, "Registration Failed!");
	    System.out.println("--- Registration Test Passed for: ---" + user);
	  
	}
}