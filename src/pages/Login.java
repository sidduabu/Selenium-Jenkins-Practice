package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dataReader.Utility;

public class Login extends Utility {
	
	private static By TMS_userName= By.id("username");
	private static By TMS_password = By.id("password");
	private static By TMS_loginSubmitButton = By.xpath("//button[contains(text(), 'LOG IN ')]");
	

	private String password;
	private String loginId;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public static void loginTest(Login objLogin) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains("Login"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(TMS_userName));
		WD.findElement(TMS_userName).clear();
		WD.findElement(TMS_userName).sendKeys(objLogin.getLoginId());
		WD.findElement(TMS_password).clear();
		WD.findElement(TMS_password).sendKeys(objLogin.getPassword());
		while(WD.findElement(By.xpath("//button")).getDomAttribute("disabled") != null);
		WD.findElement(TMS_loginSubmitButton).click();
		WD.findElement(TMS_password).sendKeys(Keys.ENTER);
	}

}