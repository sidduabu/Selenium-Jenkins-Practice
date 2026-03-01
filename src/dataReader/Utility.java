package dataReader;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Utility {
	private static final String TMSTESTDATA = "Docs/TMSTestData.xls";
	private static final String BROWSER = "Chrome";
	private static final String TMSURL = "https://aqums.billerpayments.com/umsui/#/login?partnerKey=ALACRITI&app_id=121";

	protected static WebDriver WD = null;
	static int count = 1;

	public static void openBrowser() {
        if (BROWSER.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            WD = new FirefoxDriver(options);
        } 
        else if (BROWSER.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
         //   options.addArguments("--headless");
             options.addArguments("--window-size=1920,1080");
 			options.setExperimentalOption("prefs", Map.of("profile.default_content_setting_values.notifications", 2));
			options.addArguments("--disable-extensions");
            WD = new ChromeDriver(options);
        }
        else if (BROWSER.equalsIgnoreCase("IE")) {
            WD = new InternetExplorerDriver();
        } 
        else if (BROWSER.equalsIgnoreCase("Safari")) {

            WD = new SafariDriver();
        } 
        else {
            System.out.println("Invalid Browser name is given in Config File.Kindly update");
        }
    }

	public static void open_TMSSite() {

		WD.navigate().to(TMSURL);
	}

	public static String getTMSTestDataPath() {
		return TMSTESTDATA;
	}

	public static void closeBrowser(WebDriver WD) throws IOException, InterruptedException {
		WD.close();
	}

	public static String takeScreenShot() throws IOException {
		File myImg = ((TakesScreenshot) WD).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(myImg);
		String Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		return Base64StringofScreenshot;
	}

}