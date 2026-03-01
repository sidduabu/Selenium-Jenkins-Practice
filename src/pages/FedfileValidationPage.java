package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.codoid.products.exception.FilloException;
import dataReader.Utility;

public class FedfileValidationPage extends Utility {

	private String Account;
	private String AccountType;
	private String NameOnAccount;
	private String SenderAccountNumber;
	private String DepOpSenderAccNumber;
	private String RecipientType;
	private String RecipientName;
	private String RecipientAddress;
	private String RecipientRoutingNum;
	private String RecipientAccountNum;
	private String Amount;
	private String RemitRefToRecip;
	private String SenderToRecipientInfo;
	private String InfoForRecipientFI_DropDown;
	private String InfoforRecipientFI_TextBox;

	private String AdditionalRemitInfo;
	private String RemitID;
	private String RemitLocnMethod;
	private String RemitLocn;

	private String fwStruOrgIdType;
	private String fwStruOrgIdCode;
	private String fwStruOrgName;
	private String fwStruOrgIdNum;
	private String fwStruOrgAddrsType;
	private String fwStruOrgAddrsLine1;
	private String fwStruOrgAddrsLine2;
	private String fwStruOrgCity;
	private String fwStruOrgState;
	private String fwStruOrgPostalCode;
	private String fwStruOrgCountry;
	private String fwStruOrgPhn;
	private String fwStruOrgEmail;

	private String fwStruBenefIdType;
	private String fwStruBenefIdCode;
	private String fwStruBenefName;
	private String fwStruBenefIdNum;
	private String fwStruBenefAddrsType;
	private String fwStruBenefAddrsLine1;
	private String fwStruBenefAddrsLine2;
	private String fwStruBenefCity;
	private String fwStruBenefState;
	private String fwStruBenefPostalCode;
	private String fwStruBenefCountry;

	private String fwStruDocumType;
	private String fwStruDocumIdNum;
	private String fwStruDocuDate;
	private String fwStruAmtPaid;
	private String fwStruOrgnlAmtPaid;
	private String fwStruDiscAmtPaid;
	private String fwStruAdjAmtPaid;
	private String fwStruAdjReasonCode;
	private String fwAdjustIndic;

	private String channel;
	private String DepOpNewRecipLink;
	private String feeAmt;

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getNameOnAccount() {
		return NameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		NameOnAccount = nameOnAccount;
	}
	public String getSenderAccountNumber() {
		return SenderAccountNumber;
	}

	public void setSenderAccountNumber(String senderAccountNumber) {
		SenderAccountNumber = senderAccountNumber;
	}
	
	public String getDepOpSenderAccNumber() {
		return DepOpSenderAccNumber;
	}

	public void setDepOpSenderAccNumber(String depOpSenderAccNumber) {
		DepOpSenderAccNumber = depOpSenderAccNumber;
	}

	public String getRecipientType() {
		return RecipientType;
	}

	public void setRecipientType(String recipientType) {
		RecipientType = recipientType;
	}

	public String getRecipientName() {
		return RecipientName;
	}

	public void setRecipientName(String recipientName) {
		RecipientName = recipientName;
	}

	public String getRecipientAddress() {
		return RecipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		RecipientAddress = recipientAddress;
	}

	public String getRecipientRoutingNum() {
		return RecipientRoutingNum;
	}

	public void setRecipientRoutingNum(String recipientRoutingNum) {
		RecipientRoutingNum = recipientRoutingNum;
	}

	public String getRecipientAccountNum() {
		return RecipientAccountNum;
	}

	public void setRecipientAccountNum(String recipientAccountNum) {
		RecipientAccountNum = recipientAccountNum;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}


	public String getRemitRefToRecip() {
		return RemitRefToRecip;
	}

	public void setRemitRefToRecip(String remitRefToRecip) {
		RemitRefToRecip = remitRefToRecip;
	}

	public String getSenderToRecipientInfo() {
		return SenderToRecipientInfo;
	}

	public void setSenderToRecipientInfo(String senderToRecipientInfo) {
		SenderToRecipientInfo = senderToRecipientInfo;
	}

	public String getInfoForRecipientFI_DropDown() {
		return InfoForRecipientFI_DropDown;
	}

	public void setInfoForRecipientFI_DropDown(String infoForRecipientFI_DropDown) {
		InfoForRecipientFI_DropDown = infoForRecipientFI_DropDown;
	}

	public String getInfoforRecipientFI_TextBox() {
		return InfoforRecipientFI_TextBox;
	}

	public void setInfoforRecipientFI_TextBox(String infoforRecipientFI_TextBox) {
		InfoforRecipientFI_TextBox = infoforRecipientFI_TextBox;
	}

	public String getAdditionalRemitInfo() {
		return AdditionalRemitInfo;
	}

	public void setAdditionalRemitInfo(String additionalRemitInfo) {
		AdditionalRemitInfo = additionalRemitInfo;
	}

	public String getRemitID() {
		return RemitID;
	}

	public void setRemitID(String remitID) {
		RemitID = remitID;
	}

	public String getRemitLocnMethod() {
		return RemitLocnMethod;
	}

	public void setRemitLocnMethod(String remitLocnMethod) {
		RemitLocnMethod = remitLocnMethod;
	}

	public String getRemitLocn() {
		return RemitLocn;
	}

	public void setRemitLocn(String remitLocn) {
		RemitLocn = remitLocn;
	}

	public String getFwStruOrgIdType() {
		return fwStruOrgIdType;
	}

	public void setFwStruOrgIdType(String fwStruOrgIdType) {
		this.fwStruOrgIdType = fwStruOrgIdType;
	}

	public String getFwStruOrgIdCode() {
		return fwStruOrgIdCode;
	}

	public void setFwStruOrgIdCode(String fwStruOrgIdCode) {
		this.fwStruOrgIdCode = fwStruOrgIdCode;
	}

	public String getFwStruOrgName() {
		return fwStruOrgName;
	}

	public void setFwStruOrgName(String fwStruOrgName) {
		this.fwStruOrgName = fwStruOrgName;
	}

	public String getFwStruOrgIdNum() {
		return fwStruOrgIdNum;
	}

	public void setFwStruOrgIdNum(String fwStruOrgIdNum) {
		this.fwStruOrgIdNum = fwStruOrgIdNum;
	}

	public String getFwStruOrgAddrsType() {
		return fwStruOrgAddrsType;
	}

	public void setFwStruOrgAddrsType(String fwStruOrgAddrsType) {
		this.fwStruOrgAddrsType = fwStruOrgAddrsType;
	}

	public String getFwStruOrgAddrsLine1() {
		return fwStruOrgAddrsLine1;
	}

	public void setFwStruOrgAddrsLine1(String fwStruOrgAddrsLine1) {
		this.fwStruOrgAddrsLine1 = fwStruOrgAddrsLine1;
	}

	public String getFwStruOrgAddrsLine2() {
		return fwStruOrgAddrsLine2;
	}

	public void setFwStruOrgAddrsLine2(String fwStruOrgAddrsLine2) {
		this.fwStruOrgAddrsLine2 = fwStruOrgAddrsLine2;
	}

	public String getFwStruOrgCity() {
		return fwStruOrgCity;
	}

	public void setFwStruOrgCity(String fwStruOrgCity) {
		this.fwStruOrgCity = fwStruOrgCity;
	}

	public String getFwStruOrgState() {
		return fwStruOrgState;
	}

	public void setFwStruOrgState(String fwStruOrgState) {
		this.fwStruOrgState = fwStruOrgState;
	}

	public String getFwStruOrgPostalCode() {
		return fwStruOrgPostalCode;
	}

	public void setFwStruOrgPostalCode(String fwStruOrgPostalCode) {
		this.fwStruOrgPostalCode = fwStruOrgPostalCode;
	}

	public String getFwStruOrgCountry() {
		return fwStruOrgCountry;
	}

	public void setFwStruOrgCountry(String fwStruOrgCountry) {
		this.fwStruOrgCountry = fwStruOrgCountry;
	}

	public String getFwStruOrgPhn() {
		return fwStruOrgPhn;
	}

	public void setFwStruOrgPhn(String fwStruOrgPhn) {
		this.fwStruOrgPhn = fwStruOrgPhn;
	}

	public String getFwStruOrgEmail() {
		return fwStruOrgEmail;
	}

	public void setFwStruOrgEmail(String fwStruOrgEmail) {
		this.fwStruOrgEmail = fwStruOrgEmail;
	}

	public String getFwStruBenefIdType() {
		return fwStruBenefIdType;
	}

	public void setFwStruBenefIdType(String fwStruBenefIdType) {
		this.fwStruBenefIdType = fwStruBenefIdType;
	}

	public String getFwStruBenefIdCode() {
		return fwStruBenefIdCode;
	}

	public void setFwStruBenefIdCode(String fwStruBenefIdCode) {
		this.fwStruBenefIdCode = fwStruBenefIdCode;
	}

	public String getFwStruBenefCountry() {
		return fwStruBenefCountry;
	}

	public void setFwStruBenefCountry(String fwStruBenefCountry) {
		this.fwStruBenefCountry = fwStruBenefCountry;
	}

	public String getFwStruBenefName() {
		return fwStruBenefName;
	}

	public void setFwStruBenefName(String fwStruBenefName) {
		this.fwStruBenefName = fwStruBenefName;
	}

	public String getFwStruBenefIdNum() {
		return fwStruBenefIdNum;
	}

	public void setFwStruBenefIdNum(String fwStruBenefIdNum) {
		this.fwStruBenefIdNum = fwStruBenefIdNum;
	}

	public String getFwStruBenefAddrsType() {
		return fwStruBenefAddrsType;
	}

	public void setFwStruBenefAddrsType(String fwStruBenefAddrsType) {
		this.fwStruBenefAddrsType = fwStruBenefAddrsType;
	}

	public String getFwStruBenefAddrsLine1() {
		return fwStruBenefAddrsLine1;
	}

	public void setFwStruBenefAddrsLine1(String fwStruBenefAddrsLine1) {
		this.fwStruBenefAddrsLine1 = fwStruBenefAddrsLine1;
	}

	public String getFwStruBenefAddrsLine2() {
		return fwStruBenefAddrsLine2;
	}

	public void setFwStruBenefAddrsLine2(String fwStruBenefAddrsLine2) {
		this.fwStruBenefAddrsLine2 = fwStruBenefAddrsLine2;
	}

	public String getFwStruBenefCity() {
		return fwStruBenefCity;
	}

	public void setFwStruBenefCity(String fwStruBenefCity) {
		this.fwStruBenefCity = fwStruBenefCity;
	}

	public String getFwStruBenefState() {
		return fwStruBenefState;
	}

	public void setFwStruBenefState(String fwStruBenefState) {
		this.fwStruBenefState = fwStruBenefState;
	}

	public String getFwStruBenefPostalCode() {
		return fwStruBenefPostalCode;
	}

	public void setFwStruBenefPostalCode(String fwStruBenefPostalCode) {
		this.fwStruBenefPostalCode = fwStruBenefPostalCode;
	}

	public String getFwStruDocumType() {
		return fwStruDocumType;
	}

	public void setFwStruDocumType(String fwStruDocumType) {
		this.fwStruDocumType = fwStruDocumType;
	}

	public String getFwStruDocumIdNum() {
		return fwStruDocumIdNum;
	}

	public void setFwStruDocumIdNum(String fwStruDocumIdNum) {
		this.fwStruDocumIdNum = fwStruDocumIdNum;
	}

	public String getFwStruDocuDate() {
		return fwStruDocuDate;
	}

	public void setFwStruDocuDate(String fwStruDocuDate) {
		this.fwStruDocuDate = fwStruDocuDate;
	}

	public String getFwStruAmtPaid() {
		return fwStruAmtPaid;
	}

	public void setFwStruAmtPaid(String fwStruAmtPaid) {
		this.fwStruAmtPaid = fwStruAmtPaid;
	}

	public String getFwStruOrgnlAmtPaid() {
		return fwStruOrgnlAmtPaid;
	}

	public void setFwStruOrgnlAmtPaid(String fwStruOrgnlAmtPaid) {
		this.fwStruOrgnlAmtPaid = fwStruOrgnlAmtPaid;
	}

	public String getFwStruDiscAmtPaid() {
		return fwStruDiscAmtPaid;
	}

	public void setFwStruDiscAmtPaid(String fwStruDiscAmtPaid) {
		this.fwStruDiscAmtPaid = fwStruDiscAmtPaid;
	}

	public String getFwStruAdjAmtPaid() {
		return fwStruAdjAmtPaid;
	}

	public void setFwStruAdjAmtPaid(String fwStruAdjAmtPaid) {
		this.fwStruAdjAmtPaid = fwStruAdjAmtPaid;
	}

	public String getFwStruAdjReasonCode() {
		return fwStruAdjReasonCode;
	}

	public void setFwStruAdjReasonCode(String fwStruAdjReasonCode) {
		this.fwStruAdjReasonCode = fwStruAdjReasonCode;
	}

	public String getFwAdjustIndic() {
		return fwAdjustIndic;
	}

	public void setFwAdjustIndic(String fwAdjustIndic) {
		this.fwAdjustIndic = fwAdjustIndic;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDepOpNewRecipLink() {
		return DepOpNewRecipLink;
	}

	public void setDepOpNewRecipLink(String depOpNewRecipLink) {
		DepOpNewRecipLink = depOpNewRecipLink;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public static void fedFile_SenderAndRecipient_FieldDetails_Validation(FedfileValidationPage fedFiledata) throws IOException, FilloException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//span[@class='info_small routing_no_bankname placeholder_ellipsis routing_no_display ng-star-inserted']")));
        Thread.sleep(4000);
		if (!fedFiledata.getAccount().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.selectLedger));
			Assert.assertEquals(WD.findElement(AddSenderDetails.selectLedger).getText(), fedFiledata.getAccount());		}
		
		if (!fedFiledata.getAccountType().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.selectLedger));
			Assert.assertEquals(WD.findElement(AddSenderDetails.senderAccType).getText(), fedFiledata.getAccountType());	}
		
		if (!fedFiledata.getSenderAccountNumber().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.selectLedger));
			Assert.assertEquals(WD.findElement(AddSenderDetails.senderAccNo).getDomProperty("value"),
					fedFiledata.getSenderAccountNumber());		}

		if (!fedFiledata.getChannel().equalsIgnoreCase("")) {
			Assert.assertEquals(WD.findElement(AddSenderDetails.ChannelType).getText(), fedFiledata.getChannel());
			Assert.assertEquals(WD.findElement(AddSenderDetails.DepoNewRecLink).getText(),
					fedFiledata.getDepOpNewRecipLink());
//			Assert.assertEquals(WD.findElement(AddRecipientDetails.recipFeeAmt).getDomProperty("value"),
//					fedFiledata.getFeeAmt());
			Assert.assertEquals(WD.findElement(AddSenderDetails.DepOpSenderAccNo).getDomProperty("value"),
					fedFiledata.getDepOpSenderAccNumber());
		}
		
		if(!fedFiledata.getNameOnAccount().equalsIgnoreCase("")) {
		Assert.assertEquals(WD.findElement(By.id("pay_from_name_on_acc")).getDomProperty("value"),
				fedFiledata.getNameOnAccount());}
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipType).getText(), fedFiledata.getRecipientType());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipName).getDomProperty("value"),
				fedFiledata.getRecipientName());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipAddress).getDomProperty("value"),
				fedFiledata.getRecipientAddress());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipRoutNo).getDomProperty("value"),
				fedFiledata.getRecipientRoutingNum());
		if(!fedFiledata.getRecipientAccountNum().equalsIgnoreCase("")) {
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipAccNo).getDomProperty("value"),
				fedFiledata.getRecipientAccountNum());}
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipAmt).getDomProperty("value"),
				fedFiledata.getAmount());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittRefToRecip).getDomProperty("value"),
				fedFiledata.getRemitRefToRecip());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittSenderToRecipInfo).getDomProperty("value"),
				fedFiledata.getSenderToRecipientInfo());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittInfoForRecFIDropdwn).getText(),
				fedFiledata.getInfoForRecipientFI_DropDown());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittInfoForRecFI).getDomProperty("value"),
				fedFiledata.getInfoforRecipientFI_TextBox());
		
	}

	public static void fedFile_ExternalRemit_FieldDetails_Validation(FedfileValidationPage fedFiledata) throws IOException, FilloException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwExtRemitId));
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittAddRemitInfo).getText(),
				fedFiledata.getAdditionalRemitInfo());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwExtRemitId).getDomProperty("value"),
				fedFiledata.getRemitID());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwExtRemitLocMethd).getText(),
				fedFiledata.getRemitLocnMethod());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwExtRemitLoc).getDomProperty("value"),
				fedFiledata.getRemitLocn());
	}

	public static void fedFile_StructuralRemit_FieldDetails_Validation(FedfileValidationPage fedFiledata) throws IOException, FilloException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwStruOrgIdType));
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwRemittAddRemitInfo).getText(),
				fedFiledata.getAdditionalRemitInfo());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgIdType).getText(),
				fedFiledata.getFwStruOrgIdType());
		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgIdCode).get(0).getText(),
				fedFiledata.getFwStruOrgIdCode());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgName).getDomProperty("value"),
				fedFiledata.getFwStruOrgName());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgIdNum).getDomProperty("value"),
				fedFiledata.getFwStruOrgIdNum());
//		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgAddrsType).get(0).getText(),
//				fedFiledata.getFwStruOrgAddrsType());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgAddrsLine1).getDomProperty("value"),
				fedFiledata.getFwStruOrgAddrsLine1());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgAddrsLine2).getDomProperty("value"),
				fedFiledata.getFwStruOrgAddrsLine2());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgCity).getDomProperty("value"),
				fedFiledata.getFwStruOrgCity());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgState).getDomProperty("value"),
				fedFiledata.getFwStruOrgState());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgPostalCode).getDomProperty("value"),
				fedFiledata.getFwStruOrgPostalCode());
		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgCountry).get(0).getText(),
				fedFiledata.getFwStruOrgCountry());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgPhn).getDomProperty("value"),
				fedFiledata.getFwStruOrgPhn());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgEmail).getDomProperty("value"),
				fedFiledata.getFwStruOrgEmail());

		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefIdType).getText(),
				fedFiledata.getFwStruBenefIdType());
		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgIdCode).get(1).getText(),
				fedFiledata.getFwStruBenefIdCode());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefName).getDomProperty("value"),
				fedFiledata.getFwStruBenefName());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefIdNum).getDomProperty("value"),
				fedFiledata.getFwStruBenefIdNum());
//		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgAddrsType).get(1).getText(),
//				fedFiledata.getFwStruBenefAddrsType());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefAddrsLine1).getDomProperty("value"),
				fedFiledata.getFwStruBenefAddrsLine1());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefAddrsLine2).getDomProperty("value"),
				fedFiledata.getFwStruBenefAddrsLine2());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefCity).getDomProperty("value"),
				fedFiledata.getFwStruBenefCity());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefState).getDomProperty("value"),
				fedFiledata.getFwStruBenefState());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruBenefPostalCode).getDomProperty("value"),
				fedFiledata.getFwStruBenefPostalCode());
		Assert.assertEquals(WD.findElements(AddRecipientDetails.fwStruOrgCountry).get(1).getText(),
				fedFiledata.getFwStruBenefCountry());

		Assert.assertEquals(WD.findElements(AddRecipientDetails.reltDocTyp).get(1).getText(),
				fedFiledata.getFwStruDocumType());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruDocumIdNum).getDomProperty("value"),
				fedFiledata.getFwStruDocumIdNum());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruDocuDate).getDomProperty("value"),
				fedFiledata.getFwStruDocuDate());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruAmtPaid).getDomProperty("value"),
				fedFiledata.getFwStruAmtPaid());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruOrgnlAmtPaid).getDomProperty("value"),
				fedFiledata.getFwStruOrgnlAmtPaid());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruDiscAmtPaid).getDomProperty("value"),
				fedFiledata.getFwStruDiscAmtPaid());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStruAdjAmtPaid).getDomProperty("value"),
				fedFiledata.getFwStruAdjAmtPaid());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwStructAdjustReason).getDomProperty("value"),
				fedFiledata.getFwStruAdjReasonCode());
		Assert.assertEquals(WD.findElement(AddRecipientDetails.fwAdjustIndic).getText(),
				fedFiledata.getFwAdjustIndic());
	}

}
