package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codoid.products.exception.FilloException;

import dataReader.Utility;

public class XBWireFieldValidationPage extends Utility{



	private static By ErrorMsgs = By.xpath("//div[@formarrayname='transactions']//lib-alac-ops-ui-field-error-display//small");
	private static By BICErrorMsg = By.xpath("//div[@class='error_field ng-star-inserted']//span");
	private static By newErrorMsgs = By.xpath("//span[@style='display: inline-block;']");
	private static By senderToRecErrorMsg = By.xpath("//div[@style='display: inline-block;']");






	//XBWire FieldValidation Error Msg
	private String CountryEmptyMsg;  
	private String CurrencyEmptyMsg;  
	private String ContactNameEmptyMsg;  
	private String InvalidContactNameMsg;  
	private String AccountNoNotEmptyMsg;  
	private String InvalidAccountNoMsg;  
	private String AccountNoLessThanMinMsg;  
	private String BankRoutingNotEmptyMsg;  
	private String InvalidRoutingMsg;  
	private String PhoneNoNotEmptyMsg;  
	private String InvalidPhoneNoMsg;  
	private String PhoneNoLessThanMinMsg; 
	private String InvalidBICMsg;
	private String BICNotEmptyMsg;  
	private String BICLessThanMinMsg;  
	private String TaxIdNotEmptyMsg;  
	private String InvalidTaxIdMsg;  
	private String TaxIdLessThanMinMsg;  
	private String NationalityEmptyMsg;  
	private String PurposeOfPayNotEmptyMsg;  
	private String PassportNoNotEmptyMsg;  
	private String InvalidPassportNoMsg;  
	private String PassportNoLessThanMinMsg;  
	private String IFSCEmptyMsg;  
	private String InvalidIFSCMsg;  
	private String PurposeOfPaymntCodeEmptyMsg;  
	private String IBANEmptyMSg;  
	private String InvalidIBANMsg;  
	private String IBANMinLessThanMsg;  
	private String BankBranchAddressEmptyMsg;  
	private String RecAccountMethodsEmptyMsg;  
	private String InvalidRecipientNameMsg;
	private String InvalidAddressLineMsg;
	private String InvalidTownMsg;
	private String InvalidStateMsg;
	private String InvalidZipMsg;
	private String InvalidEndTOEndMsg;
	private String InvalidSenderRecMsg;

	public String getInvalidRecipientNameMsg() {
		return InvalidRecipientNameMsg;
	}

	public void setInvalidRecipientNameMsg(String invalidRecipientNameMsg) {
		this.InvalidRecipientNameMsg = invalidRecipientNameMsg;
	}

	public String getInvalidAddressLineMsg() {
		return InvalidAddressLineMsg;
	}

	public void setInvalidAddressLineMsg(String invalidAddressLineMsg) {
		this.InvalidAddressLineMsg = invalidAddressLineMsg;
	}

	public String getInvalidTownMsg() {
		return InvalidTownMsg;
	}

	public void setInvalidTownMsg(String invalidTownMsg) {
		this.InvalidTownMsg = invalidTownMsg;
	}

	public String getInvalidStateMsg() {
		return InvalidStateMsg;
	}

	public void setInvalidStateMsg(String invalidStateMsg) {
		this.InvalidStateMsg = invalidStateMsg;
	}

	public String getInvalidZipMsg() {
		return InvalidZipMsg;
	}

	public void setInvalidZipMsg(String invalidZipMsg) {
		this.InvalidZipMsg = invalidZipMsg;
	}

	public String getInvalidEndTOEndMsg() {
		return InvalidEndTOEndMsg;
	}

	public void setInvalidEndTOEndMsg(String invalidEndTOEndMsg) {
		this.InvalidEndTOEndMsg = invalidEndTOEndMsg;
	}

	public String getInvalidSenderRecMsg() {
		return InvalidSenderRecMsg;
	}

	public void setInvalidSenderRecMsg(String invalidSenderRecMsg) {
		this.InvalidSenderRecMsg = invalidSenderRecMsg;
	}

	public String getInvalidBICMsg() {
		return InvalidBICMsg;
	}
	public void setInvalidBICMsg(String invalidBICMsg) {
		InvalidBICMsg = invalidBICMsg;
	}
	public String getCountryEmptyMsg() {
		return CountryEmptyMsg;
	}
	public void setCountryEmptyMsg(String countryEmptyMsg) {
		CountryEmptyMsg = countryEmptyMsg;
	}
	public String getCurrencyEmptyMsg() {
		return CurrencyEmptyMsg;
	}
	public void setCurrencyEmptyMsg(String currencyEmptyMsg) {
		CurrencyEmptyMsg = currencyEmptyMsg;
	}
	public String getContactNameEmptyMsg() {
		return ContactNameEmptyMsg;
	}
	public void setContactNameEmptyMsg(String contactNameEmptyMsg) {
		ContactNameEmptyMsg = contactNameEmptyMsg;
	}
	public String getInvalidContactNameMsg() {
		return InvalidContactNameMsg;
	}
	public void setInvalidContactNameMsg(String invalidContactNameMsg) {
		InvalidContactNameMsg = invalidContactNameMsg;
	}
	public String getAccountNoNotEmptyMsg() {
		return AccountNoNotEmptyMsg;
	}
	public void setAccountNoNotEmptyMsg(String accountNoNotEmptyMsg) {
		AccountNoNotEmptyMsg = accountNoNotEmptyMsg;
	}
	public String getInvalidAccountNoMsg() {
		return InvalidAccountNoMsg;
	}
	public void setInvalidAccountNoMsg(String invalidAccountNoMsg) {
		InvalidAccountNoMsg = invalidAccountNoMsg;
	}
	public String getAccountNoLessThanMinMsg() {
		return AccountNoLessThanMinMsg;
	}
	public void setAccountNoLessThanMinMsg(String accountNoLessThanMinMsg) {
		AccountNoLessThanMinMsg = accountNoLessThanMinMsg;
	}
	public String getBankRoutingNotEmptyMsg() {
		return BankRoutingNotEmptyMsg;
	}
	public void setBankRoutingNotEmptyMsg(String bankRoutingNotEmptyMsg) {
		BankRoutingNotEmptyMsg = bankRoutingNotEmptyMsg;
	}
	public String getInvalidRoutingMsg() {
		return InvalidRoutingMsg;
	}
	public void setInvalidRoutingMsg(String invalidRoutingMsg) {
		InvalidRoutingMsg = invalidRoutingMsg;
	}
	public String getPhoneNoNotEmptyMsg() {
		return PhoneNoNotEmptyMsg;
	}
	public void setPhoneNoNotEmptyMsg(String phoneNoNotEmptyMsg) {
		PhoneNoNotEmptyMsg = phoneNoNotEmptyMsg;
	}
	public String getInvalidPhoneNoMsg() {
		return InvalidPhoneNoMsg;
	}
	public void setInvalidPhoneNoMsg(String invalidPhoneNoMsg) {
		InvalidPhoneNoMsg = invalidPhoneNoMsg;
	}
	public String getPhoneNoLessThanMinMsg() {
		return PhoneNoLessThanMinMsg;
	}
	public void setPhoneNoLessThanMinMsg(String phoneNoLessThanMinMsg) {
		PhoneNoLessThanMinMsg = phoneNoLessThanMinMsg;
	}
	public String getBICNotEmptyMsg() {
		return BICNotEmptyMsg;
	}
	public void setBICNotEmptyMsg(String bICNotEmptyMsg) {
		BICNotEmptyMsg = bICNotEmptyMsg;
	}
	public String getBICLessThanMinMsg() {
		return BICLessThanMinMsg;
	}
	public void setBICLessThanMinMsg(String bICLessThanMinMsg) {
		BICLessThanMinMsg = bICLessThanMinMsg;
	}
	public String getTaxIdNotEmptyMsg() {
		return TaxIdNotEmptyMsg;
	}
	public void setTaxIdNotEmptyMsg(String taxIdNotEmptyMsg) {
		TaxIdNotEmptyMsg = taxIdNotEmptyMsg;
	}
	public String getInvalidTaxIdMsg() {
		return InvalidTaxIdMsg;
	}
	public void setInvalidTaxIdMsg(String invalidTaxIdMsg) {
		InvalidTaxIdMsg = invalidTaxIdMsg;
	}
	public String getTaxIdLessThanMinMsg() {
		return TaxIdLessThanMinMsg;
	}
	public void setTaxIdLessThanMinMsg(String taxIdLessThanMinMsg) {
		TaxIdLessThanMinMsg = taxIdLessThanMinMsg;
	}
	public String getNationalityEmptyMsg() {
		return NationalityEmptyMsg;
	}
	public void setNationalityEmptyMsg(String nationalityEmptyMsg) {
		NationalityEmptyMsg = nationalityEmptyMsg;
	}
	public String getPurposeOfPayNotEmptyMsg() {
		return PurposeOfPayNotEmptyMsg;
	}
	public void setPurposeOfPayNotEmptyMsg(String purposeOfPayNotEmptyMsg) {
		PurposeOfPayNotEmptyMsg = purposeOfPayNotEmptyMsg;
	}
	public String getPassportNoNotEmptyMsg() {
		return PassportNoNotEmptyMsg;
	}
	public void setPassportNoNotEmptyMsg(String passportNoNotEmptyMsg) {
		PassportNoNotEmptyMsg = passportNoNotEmptyMsg;
	}
	public String getInvalidPassportNoMsg() {
		return InvalidPassportNoMsg;
	}
	public void setInvalidPassportNoMsg(String invalidPassportNoMsg) {
		InvalidPassportNoMsg = invalidPassportNoMsg;
	}
	public String getPassportNoLessThanMinMsg() {
		return PassportNoLessThanMinMsg;
	}
	public void setPassportNoLessThanMinMsg(String passportNoLessThanMinMsg) {
		PassportNoLessThanMinMsg = passportNoLessThanMinMsg;
	}
	public String getIFSCEmptyMsg() {
		return IFSCEmptyMsg;
	}
	public void setIFSCEmptyMsg(String iFSCEmptyMsg) {
		IFSCEmptyMsg = iFSCEmptyMsg;
	}
	public String getInvalidIFSCMsg() {
		return InvalidIFSCMsg;
	}
	public void setInvalidIFSCMsg(String invalidIFSCMsg) {
		InvalidIFSCMsg = invalidIFSCMsg;
	}
	public String getPurposeOfPaymntCodeEmptyMsg() {
		return PurposeOfPaymntCodeEmptyMsg;
	}
	public void setPurposeOfPaymntCodeEmptyMsg(String purposeOfPaymntCodeEmptyMsg) {
		PurposeOfPaymntCodeEmptyMsg = purposeOfPaymntCodeEmptyMsg;
	}
	public String getIBANEmptyMSg() {
		return IBANEmptyMSg;
	}
	public void setIBANEmptyMSg(String iBANEmptyMSg) {
		IBANEmptyMSg = iBANEmptyMSg;
	}
	public String getInvalidIBANMsg() {
		return InvalidIBANMsg;
	}
	public void setInvalidIBANMsg(String invalidIBANMsg) {
		InvalidIBANMsg = invalidIBANMsg;
	}
	public String getIBANMinLessThanMsg() {
		return IBANMinLessThanMsg;
	}
	public void setIBANMinLessThanMsg(String iBANMinLessThanMsg) {
		IBANMinLessThanMsg = iBANMinLessThanMsg;
	}
	public String getBankBranchAddressEmptyMsg() {
		return BankBranchAddressEmptyMsg;
	}
	public void setBankBranchAddressEmptyMsg(String bankBranchAddressEmptyMsg) {
		BankBranchAddressEmptyMsg = bankBranchAddressEmptyMsg;
	}
	public String getRecAccountMethodsEmptyMsg() {
		return RecAccountMethodsEmptyMsg;
	}
	public void setRecAccountMethodsEmptyMsg(String recAccountMethodsEmptyMsg) {
		RecAccountMethodsEmptyMsg = recAccountMethodsEmptyMsg;
	}






	public static String getErrorMsg() throws InterruptedException  {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ErrorMsgs)); // Wait for all elements
		List<WebElement> elements = WD.findElements(ErrorMsgs); // Get all matching elements
		for (WebElement element : elements) {
			String text = element.getText();
			if (!text.trim().isEmpty()) { // Check if the text is not empty
				JavascriptExecutor js = (JavascriptExecutor) WD;
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
				Thread.sleep(500);
				return text;
			}
		}
		return "No error message found";
	}


	public static List<String> getAllErrorMsg() throws InterruptedException, IOException, FilloException  {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(newErrorMsgs));
		List<WebElement> elements = WD.findElements(newErrorMsgs);
		List<String> actualErrorMessages = new ArrayList<>(); // Store collected error messages
		for (WebElement element : elements) {
			actualErrorMessages.add(element.getText().trim()); // Store trimmed error message
		}
		return actualErrorMessages;
	}


	public static String getBicErrorMsg()  {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(BICErrorMsg));
		return  WD.findElement(BICErrorMsg).getText();
	}


	public static void enterInvalidAddressFields() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.presenceOfElementLocated(AddRecipientDetails.recipAddressLine));
		wait.until(ExpectedConditions.presenceOfElementLocated(AddRecipientDetails.recipZipcode));
		WD.findElement(AddRecipientDetails.recipAddressLine).clear();
		WD.findElement(AddRecipientDetails.recipTownName).clear();
		WD.findElement(AddRecipientDetails.recipState_XB).clear();
		WD.findElement(AddRecipientDetails.recipZipcode).clear();
		WD.findElement(By.id("xb-e2e-ref")).clear();
		WD.findElement(AddRecipientDetails.recipAddressLine).sendKeys("@@",Keys.TAB);
		WD.findElement(AddRecipientDetails.recipTownName).sendKeys("@@",Keys.TAB);
		WD.findElement(AddRecipientDetails.recipState_XB).sendKeys("@@",Keys.TAB); 
		WD.findElement(AddRecipientDetails.recipZipcode).sendKeys("@@",Keys.TAB);
		WD.findElement(By.id("xb-e2e-ref")).sendKeys("@@",Keys.TAB);
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(AddRecipientDetails.selectCountry));
	}
	

	public static String assertInvalidSenderToRec() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("xb_comment")));
		WD.findElement(By.id("xb_comment")).clear();
		WD.findElement(By.id("xb_comment")).sendKeys("@@",Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(senderToRecErrorMsg));
		return  WD.findElement(senderToRecErrorMsg).getText();
	}


}
