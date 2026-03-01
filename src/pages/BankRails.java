package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataReader.Utility;

public class BankRails extends Utility{



	private static By accountNotExist=By.xpath("//div[@class='error_field ng-star-inserted']//span");
	private static By rejectReasons=By.xpath("//div[@class='form-group'][.//label[text()='Reject Reason']]//strong");
	private static By reasonCodeMsg=By.xpath("//div[@class='form-group'][.//label[text()='Reason Code: ']]//strong");
	private static By rejectReasonDescr=By.xpath("(//div[@class='form-group']//div[@class='ng-star-inserted'])[2]");
	static By instructionID=By.xpath("//div[@class='form-group']/strong[@class='wrap-long-text clickable-span']");


	




	private String AccNumNotExistMsg;
	private String AccountNotopenMsg;
	private String InvalidAccount;
	private String BankcoreServiceTimeoutMsg;
	private String InsufficientBalance1;
	private String InsufficientBalance2;
	private String InvalidAmountInDebitorMsg;
	private String InvalidAmountInCreditorMsg;
	private String DepoAccRejectWarningMsg;
	private String DepoAccRejectWarningMsg2;
	private String SimulatorWaitTimeoutMsg;
	private String ReqAmountLessThanMinMsg1;
	private String ReqAmountLessThanMinMsg2;
	private String BankCoreSetUpErrMsg;
	private String UnAuthorisedReqSentMsg;
	private String BankCoreServiceInternalMsg;
	
	private String DebitorAccCloseMsg;
	private String InsuficientBalance3;
	private String ReqAmountLessThanMinMsg3;
	private String AmountInvalidRejectMsg;
	private String AccWarningCodesMsg;
	private String InvalidInformRecMsg;	
	private String AccHoldWarnMsg;

	
	
	
	
	
	
	
	
	
	public String getDepoAccRejectWarningMsg2() {
		return DepoAccRejectWarningMsg2;
	}
	public void setDepoAccRejectWarningMsg2(String depoAccRejectWarningMsg2) {
		DepoAccRejectWarningMsg2 = depoAccRejectWarningMsg2;
	}
	public String getDebitorAccCloseMsg() {
		return DebitorAccCloseMsg;
	}
	public void setDebitorAccCloseMsg(String debitorAccCloseMsg) {
		DebitorAccCloseMsg = debitorAccCloseMsg;
	}
	public String getInsuficientBalance3() {
		return InsuficientBalance3;
	}
	public void setInsuficientBalance3(String insuficientBalance3) {
		InsuficientBalance3 = insuficientBalance3;
	}
	public String getReqAmountLessThanMinMsg3() {
		return ReqAmountLessThanMinMsg3;
	}
	public void setReqAmountLessThanMinMsg3(String reqAmountLessThanMinMsg3) {
		ReqAmountLessThanMinMsg3 = reqAmountLessThanMinMsg3;
	}
	public String getAmountInvalidRejectMsg() {
		return AmountInvalidRejectMsg;
	}
	public void setAmountInvalidRejectMsg(String amountInvalidRejectMsg) {
		AmountInvalidRejectMsg = amountInvalidRejectMsg;
	}
	public String getAccWarningCodesMsg() {
		return AccWarningCodesMsg;
	}
	public void setAccWarningCodesMsg(String accWarningCodesMsg) {
		AccWarningCodesMsg = accWarningCodesMsg;
	}
	public String getInvalidInformRecMsg() {
		return InvalidInformRecMsg;
	}
	public void setInvalidInformRecMsg(String invalidInformRecMsg) {
		InvalidInformRecMsg = invalidInformRecMsg;
	}
	public String getAccHoldWarnMsg() {
		return AccHoldWarnMsg;
	}
	public void setAccHoldWarnMsg(String accHoldWarnMsg) {
		AccHoldWarnMsg = accHoldWarnMsg;
	}
	public String getAccNumNotExistMsg() {
		return AccNumNotExistMsg;
	}
	public void setAccNumNotExistMsg(String accNumNotExistMsg) {
		AccNumNotExistMsg = accNumNotExistMsg;
	}
	public String getAccountNotopenMsg() {
		return AccountNotopenMsg;
	}
	public void setAccountNotopenMsg2(String accountNotopenMsg2) {
		AccountNotopenMsg = accountNotopenMsg2;
	}
	
	public String getInvalidAccount() {
		return InvalidAccount;
	}
	public void setInvalidAccount(String invalidAccount) {
		InvalidAccount = invalidAccount;
	}
	
	public String getBankcoreServiceTimeoutMsg() {
		return BankcoreServiceTimeoutMsg;
	}
	public void setBankcoreServiceTimeoutMsg(String bankcoreServiceTimeoutMsg) {
		BankcoreServiceTimeoutMsg = bankcoreServiceTimeoutMsg;
	}
	
	public String getInsufficientBalance1() {
		return InsufficientBalance1;
	}
	public void setInsufficientBalance1(String insufficientBalance1) {
		InsufficientBalance1 = insufficientBalance1;
	}
	public String getInsufficientBalance2() {
		return InsufficientBalance2;
	}
	public void setInsufficientBalance2(String insufficientBalance2) {
		InsufficientBalance2 = insufficientBalance2;
	}
	
	public String getInvalidAmountInDebitorMsg() {
		return InvalidAmountInDebitorMsg;
	}
	public void setInvalidAmountInDebitorMsg(String invalidAmountInDebitorMsg) {
		InvalidAmountInDebitorMsg = invalidAmountInDebitorMsg;
	}

	public String getInvalidAmountInCreditorMsg() {
		return InvalidAmountInCreditorMsg;
	}
	public void setInvalidAmountInCreditorMsg(String invalidAmountInCreditorMsg) {
		InvalidAmountInCreditorMsg = invalidAmountInCreditorMsg;
	}

	public String getDepoAccRejectWarningMsg() {
		return DepoAccRejectWarningMsg;
	}
	public void setDepoAccRejectWarningMsg(String depoAccRejectWarningMsg) {
		DepoAccRejectWarningMsg = depoAccRejectWarningMsg;
	}
	public String getSimulatorWaitTimeoutMsg() {
		return SimulatorWaitTimeoutMsg;
	}
	public void setSimulatorWaitTimeoutMsg(String simulatorWaitTimeoutMsg) {
		SimulatorWaitTimeoutMsg = simulatorWaitTimeoutMsg;
	}
	public String getReqAmountLessThanMinMsg1() {
		return ReqAmountLessThanMinMsg1;
	}
	public void setReqAmountLessThanMinMsg1(String reqAmountLessThanMinMsg1) {
		ReqAmountLessThanMinMsg1 = reqAmountLessThanMinMsg1;
	}
	public String getReqAmountLessThanMinMsg2() {
		return ReqAmountLessThanMinMsg2;
	}
	public void setReqAmountLessThanMinMsg2(String reqAmountLessThanMinMsg2) {
		ReqAmountLessThanMinMsg2 = reqAmountLessThanMinMsg2;
	}
	public String getBankCoreSetUpErrMsg() {
		return BankCoreSetUpErrMsg;
	}
	public void setBankCoreSetUpErrMsg(String bankCoreSetUpErrMsg) {
		BankCoreSetUpErrMsg = bankCoreSetUpErrMsg;
	}

	public String getUnAuthorisedReqSentMsg() {
		return UnAuthorisedReqSentMsg;
	}
	public void setUnAuthorisedReqSentMsg(String unAuthorisedReqSentMsg) {
		UnAuthorisedReqSentMsg = unAuthorisedReqSentMsg;
	}
	public String getBankCoreServiceInternalMsg() {
		return BankCoreServiceInternalMsg;
	}
	public void setBankCoreServiceInternalMsg(String bankCoreServiceInternalMsg) {
		BankCoreServiceInternalMsg = bankCoreServiceInternalMsg;
	}




	public static String getAccountNotExistText() throws IOException, InterruptedException {
		WebDriverWait wait = new  WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountNotExist));
		return WD.findElement(accountNotExist).getText();
	}


	public static String getRejectReasonsMsg() throws IOException, InterruptedException {
		WebDriverWait wait = new  WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(rejectReasons));
		return WD.findElement(rejectReasons).getText();
	}
	

	public static String getReasonCodeMsg() throws IOException, InterruptedException {
		WebDriverWait wait = new  WebDriverWait(WD, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(instructionID));
		WD.findElement(instructionID).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(reasonCodeMsg));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(reasonCodeMsg));
		return WD.findElement(reasonCodeMsg).getText();
	}
	

	public static String getRejectReasonDesc() throws IOException, InterruptedException {
		WebDriverWait wait = new  WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(instructionID));
// Instruction_ID Click	
		WD.findElement(instructionID).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(rejectReasonDescr));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(rejectReasonDescr));
		return WD.findElement(rejectReasonDescr).getText();
	}

}
