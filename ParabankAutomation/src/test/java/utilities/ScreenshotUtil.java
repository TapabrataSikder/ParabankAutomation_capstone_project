package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files; // Use this instead of FileUtils
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String testName) {
       	// Time stamp for the ss
    	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    	String directoryPath = "./screenshots/";
        File directory = new File(directoryPath);
    	
//        Create the folder if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
//      Create a unique filename for the screenshot
        File destFile = new File(directoryPath + testName + "_" + timestamp + ".png");
       
        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        try {
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}