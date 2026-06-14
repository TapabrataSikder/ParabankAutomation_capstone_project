package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utilities.ExcelUtils;

public class NegativeLoginTest extends BaseTest {

    @DataProvider(name="invalidLoginData")
    public Object[][] getInvalidData() throws Exception {
        // Create a new sheet 'Sheet3' in your Excel for negative test data
        return ExcelUtils.getLoginData("src/test/resources/TestData.xlsx", "Sheet3");
    }

    @Test(dataProvider = "invalidLoginData")
    public void testNegativeLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(username, password);

        // Assert that the user is NOT logged in (look for error message or stay on login page)
        String currentUrl = driver.getCurrentUrl();
//        Assert.assertFalse(currentUrl.contains("overview"), "Login should have failed, but user was logged in!");
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Error message was not displayed!");
        System.out.println("--- Negative Login Test Passed (User not logged in) ---");
    }
}