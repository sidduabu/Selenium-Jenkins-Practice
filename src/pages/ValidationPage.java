package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dataReader.ErrorMessageData;
import dataReader.TestDataReader;
import dataReader.Utility;

public class ValidationPage extends Utility {

	private static By errorFieldMsg1 = By.xpath("//div[contains(@class, 'error_field')]//div[@class='ng-star-inserted']");
	private static By errorFieldMsg_SenderToRecipInfo = By.xpath("//div[contains(@class, 'error_field')]//small");

	private static By eyeVisibility = By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']");
	private static By finalPage_PayToDropDown = By.xpath("//*[@elementid='paymentPartyRecipientDetails']//a[@data-target='#recipientInfo']");
	private static By finalPage_AccountDetails = By.xpath("//div[@class='expand_dropdown_details']//div[@class='d-flex']");
	private static By finalPage_RecipRoutingNum = By.xpath("//div[@id='recipientInfo']//div[@class='d-flex']");
	private static By finalPage_RecipPhoneNum = By.xpath("//div[@id='recipientInfo']//div[@class='form-group ng-star-inserted']//strong");
	private static By recipientRoutingNumSearchBtn = By.xpath("//*[contains(@id, '_routing_number')]//span[contains(@class, '-blue ng-star-inserted')]");
	private static By bankRoutingTextBox = By.xpath("//input[contains(@id, 'rtn_search_input')]");
	private static By bankRoutingSearchBtn = By.xpath("//a[contains(@id, 'search_banklist')]//*[@class='fa fa-search rb']");
	private static By NoRecordMsgRoutingNum = By.xpath("//div[@id='routing_results_list']//span[text()=' No records found for given search criteria.']");

	private static By rejectStatusInFinalPage = By.xpath("//span[@class='detail_item']//*[@class='c_status']");
	private static By rejectReasonBlockAtFinalPage = By.xpath("//div[@class='col-sm-8 ng-star-inserted']//div[@class='ng-star-inserted']");
//	private static By rejectReasonCodeBlockAtFinalPage = By.xpath("//div[@class='col-sm-4 ng-star-inserted']//div[@class='ng-star-inserted']");
	private static By instructionID = By.xpath("//div[@class='form-group']//strong[@class='wrap-long-text clickable-span']");
	private static By awaitingRiskReviewStatus = By.xpath("//span[@class='detail_item text_green ng-star-inserted']");
 	private static By reasonCodeAtTransactionManagerPage = By.xpath("//div[@class='col-sm-12 ng-star-inserted']//strong");	
 	
 	
// //=======>> TMS Final Page Validation <<==========
// 	 private static By transferMethod_AtTitle = By.xpath("//div[@class='title_glance_details header-ellipsis ng-star-inserted']//span[@class='detail_item pl-15']");
// 	 private static By transferType_AtTitle = By.xpath("//div[@class='title_glance_details header-ellipsis ng-star-inserted']//app-transaction-type-display[@class='ng-star-inserted']");
// 	 private static By transferStatus_AtTitle = By.xpath("//div[@class='title_glance_details header-ellipsis ng-star-inserted']//span[contains(@class, 'c_status')]");
// 	 private static By transferConfirmNum_AtTitle = By.xpath("//div[@class='title_glance_details header-ellipsis ng-star-inserted']//span[@class='ng-star-inserted']");
//
//// 	 private static By transferValueDateAtCenter = By.xpath("//div[@class='flex_column details_glance2 text-center']//strong[@class='d-block mb_10']");
// 	 private static By transferMoneyAtCenter = By.xpath("//div[@class='flex_column details_glance2 text-center']//strong[@class='mt_10 d-block']");
// //=====> Common for Sender And Recipient ======
// 	 private static By transferNameAccNumAccType = By.xpath("//div[@class='flex_column details_glance3 overlay_dropdown_details']//strong[@style='word-break: break-word;']");

 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
	public static void continueClick_And_AssertingSingleErrorFieldMsg(String expectedError) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
//		JavascriptExecutor js = (JavascriptExecutor) WD;
//		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		String errMsg = WD.findElements(errorFieldMsg1).get(0).getText();
		Assert.assertEquals(errMsg, expectedError);
//		Assert.assertEquals(errMsg, "Amount cannot exceed 1,000,000.00 USD");

	}

	public static void continueClick_And_AssertingTwoErrorFieldMsgs(String expectedError) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		Thread.sleep(2000);
		String errMsg1 = WD.findElements(errorFieldMsg1).get(0).getText();
		String errMsg2 = WD.findElements(errorFieldMsg1).get(1).getText();
		Assert.assertEquals(errMsg1, expectedError);
		Assert.assertEquals(errMsg2, expectedError);
	}

	public static void assertingErrorFieldMsg_For_UploadRelatedDoc(String expectedError) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		WD.findElements(AddRecipientDetails.reltDocTyp).get(0).sendKeys(Keys.TAB, Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		Thread.sleep(2000);
		String errMsg = WD.findElements(errorFieldMsg1).get(0).getText();
		Assert.assertEquals(errMsg, expectedError);
	}

	public static void assertingSingleErrorFieldMsg_WithOutContinueClick(String expectedError) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		String errMsg = WD.findElements(errorFieldMsg1).get(0).getText();
		Assert.assertEquals(errMsg, expectedError);
	}
	
	public static void assertingTwoErrorFieldMsgs_WithOutContinueClick(String expectedError1,String expectedError2) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		String errMsg1 = WD.findElements(errorFieldMsg1).get(0).getText();
		String errMsg2 = WD.findElements(errorFieldMsg1).get(1).getText();
		Assert.assertEquals(errMsg1, expectedError1);
		Assert.assertEquals(errMsg2, expectedError2);
	}

//===============================>> ** AML & HotList **<<=====================================================================================================================================================================================	

	public static void assertingAmlChecks(String expectedRejectReasonBlock , String expectedRejectReasonCodeBlock) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(rejectStatusInFinalPage));
// fetch the Confirmation Number
		System.out.println("Conf " + WD.findElement(AddSenderDetails.confirmNo).getText());
// Asserting Payment Status ---> ie:- Rejected
		Assert.assertEquals(WD.findElement(rejectStatusInFinalPage).getText(), "Rejected");
// Reject Reason 
		List<WebElement> actualRejectReason_multipleLines =  WD.findElements(rejectReasonBlockAtFinalPage);
	        List<String> actualRejectReasonList = new ArrayList<>();
	        for (WebElement actualRejectReason_SingleLine : actualRejectReason_multipleLines) {
	        	String actualRejectReason_SingleLineMsg = actualRejectReason_SingleLine.getText();
	        	if (!actualRejectReason_SingleLineMsg.trim().isEmpty()) {
	                actualRejectReasonList.add(actualRejectReason_SingleLineMsg.trim());    }
			} 
		ValidationPage.validateMessages(expectedRejectReasonBlock, actualRejectReasonList);

// From 61.4 Release:- Only one Reject_Reason_Code is displaying (Previously we used get like this :- If we get x-number of RejectReasons then we will get the same number of RejectReasonCodes also. 
// But As per the recent update we will get only one RejectReasonCode irrespective of number of RejectReasons ---> Got confirmed from Anil)		

// Reject Reason Code
//		String actualRejectReasonCodeBlock = WD.findElement(rejectReasonCodeBlockAtFinalPage).getText();
//        ValidationPage.validateMessages(expectedRejectReasonCodeBlock, actualRejectReasonCodeBlock);
	}
	
	
	public static void validateMessages(String expectedBlock, List<String> actualRejectReasonList) {
		// Convert to sets of trimmed strings
		        Set<String> expectedSet = new HashSet<>(splitAndTrim(expectedBlock));
		        Set<String> actualSet = new HashSet<>(actualRejectReasonList);
		        if (!expectedSet.equals(actualSet)) {
		// Checking if there are any missing messages or not (ExceptedMsgs - ActualMsgs)
		        	Set<String> missing = new HashSet<>(expectedSet);
		            missing.removeAll(actualSet);
		// Checking if there are any Unexpected messages or not (ActualMsgs - ExceptedMsgs)
		            Set<String> unexpected = new HashSet<>(actualSet);
		            unexpected.removeAll(expectedSet);
		// Building the String to print all the Missing/Unexpected messages
		            StringBuilder errorMessage = new StringBuilder();
		            errorMessage.append("Message validation failed!\n");
		            if (!missing.isEmpty()) {
		                errorMessage.append("Missing messages:\n");
		                for (String msg : missing) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		            if (!unexpected.isEmpty()) {
		                errorMessage.append("Unexpected messages:\n");
		                for (String msg : unexpected) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		//  Fail the test
		            Assert.fail(errorMessage.toString());
		        } else {
		            System.out.println("Messages match!");
		        }
		    }

			
		// Helper method to split and trim
		    public static List<String> splitAndTrim(String block) {
		        String[] lines = block.split("\\r?\\n");
		        List<String> trimmedLines = new ArrayList<>();
		        for (String line : lines) {
		            if (!line.trim().isEmpty()) {
		                trimmedLines.add(line.trim());
		            }
		        }
		        return trimmedLines;
		    }

	
	public static void assertingHotlistStatus(String expectedError) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.confirmNo));

//		fetch the Confirmation Number
		System.out.println("Conf " + WD.findElement(AddSenderDetails.confirmNo).getText());

//		 Payment first status ---> InProcess
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//strong[text()='In Process']")));
		String pmtFirstStatusActual = WD.findElement(By.xpath("//span[@class='detail_item']//strong[@class='c_status']")).getText();
		Assert.assertEquals(pmtFirstStatusActual, "In Process");
// Instruction_ID click  
		wait.until(ExpectedConditions.visibilityOfElementLocated(instructionID));
		WD.findElement(instructionID).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(awaitingRiskReviewStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(reasonCodeAtTransactionManagerPage));
//	 Payment second status ---> Awaiting Risk Review
		String pmtSecondStatusActual = WD.findElement(awaitingRiskReviewStatus).getText();
		Assert.assertEquals(pmtSecondStatusActual, "Awaiting Risk Review");
//	 Reason Code:- CDTR_NEG_DDA - Creditor account found in Hotlist
		String actualResonCode = WD.findElement(reasonCodeAtTransactionManagerPage).getText();
//		Assert.assertEquals(actualResonCode.contains("CDTR_NEG_DDA - Creditor account found in Hotlist"), true);
		Assert.assertEquals(actualResonCode, expectedError);
	}

//================================ ** RTP Mandatory_Filed_Validation_Cases ** =======================================================================================================================================================

	public static void emptyAddressLine() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.recipAddressLine));
		WD.findElement(AddRecipientDetails.recipAddressLine).clear();
		WD.findElement(AddRecipientDetails.recipAddressLine).sendKeys(Keys.SPACE, Keys.TAB);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipAddrsLine());
	}

	public static void rtpEmtyDate() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.RemitDate));
		WD.findElement(AddRecipientDetails.endToEnd).sendKeys(Keys.TAB);
		WD.findElement(AddRecipientDetails.RemitDoct).sendKeys(Keys.TAB);
		WD.findElement(By.xpath("//i[@class='fa fa-times']")).click();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyDocDat());
	}

	public static void rtpInvcDiscAmt(String element, String data, String expectedError) throws Exception {
		WebElement amountField = null;
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.RemitInvcAmt));
		if (element.equalsIgnoreCase("invoice")) {
			amountField = WD.findElement(AddRecipientDetails.RemitInvcAmt);
		} else if (element.equalsIgnoreCase("discount")) {
			amountField = WD.findElement(AddRecipientDetails.RemitDiscAmt);
		}

		((JavascriptExecutor) WD).executeScript("arguments[0].value='';", amountField);
		amountField.sendKeys(Keys.SPACE, Keys.BACK_SPACE, data);
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		Thread.sleep(2000);
		Assert.assertEquals(WD.findElements(errorFieldMsg1).get(0).getText(), expectedError);
	}

	public static void AssertingInvalidBankNameSearchForRoutingNum() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipientRoutingNumSearchBtn));
		WD.findElement(AddRecipientDetails.recipRoutNo).clear();
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].click();", WD.findElement(recipientRoutingNumSearchBtn));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bankRoutingTextBox));
		WD.findElement(bankRoutingTextBox).clear();
		WD.findElement(bankRoutingTextBox).sendKeys(Keys.CLEAR, "!@#$%^&", Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(bankRoutingSearchBtn));
		WD.findElement(bankRoutingSearchBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NoRecordMsgRoutingNum));
		Assert.assertEquals(WD.findElement(NoRecordMsgRoutingNum).getText(),
				"No records found for given search criteria.");
		WD.findElements(AddSenderDetails.closeBtn).get(1).click();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	}

	public static void assertingRoundFigureValues(String expectedAmt) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.recipAmt));
//	Asserting the Actual Amount and Expected Amount	
		Assert.assertEquals(WD.findElement(AddRecipientDetails.recipAmt).getDomProperty("value"), expectedAmt);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(By.xpath("//div[@class='block_style_body']//label[text()='Amount:']")));
	}

//================================ ** Fed Wire Mandatory_Filed_Validation_Cases ** =======================================================================================================================================================

	public static void fw_Assertion() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg1));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(errorFieldMsg1));
		Thread.sleep(2000);
		String errMsg1 = WD.findElements(errorFieldMsg1).get(0).getText();
		String errMsg2 = WD.findElements(errorFieldMsg1).get(1).getText();
		String errMsg3 = WD.findElements(errorFieldMsg1).get(2).getText();
		Assert.assertEquals(errMsg1, objErrDtls.getEmptyTransferType());
		Assert.assertEquals(errMsg2, objErrDtls.getEmptyPaymentMethod());
		Assert.assertEquals(errMsg3, objErrDtls.getEmptyRecipType());
	}

	public static void fWEmtyRefToRecip() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwRemittRefToRecip));
		WD.findElement(AddRecipientDetails.fwRemittRefToRecip).sendKeys(Keys.SPACE);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRefToRecip());
	}

	public static void emptyFWRemitInfoForRecipFI() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwRemittRefToRecip));
		WD.findElement(AddRecipientDetails.fwRemittInfoForRecFI).clear();
		WD.findElement(AddRecipientDetails.fwRemittInfoForRecFI).sendKeys(Keys.SPACE, Keys.BACK_SPACE);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyInfoRcpFI());
	}

	public static void fwInvdSendrToRecipInfo() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwRemittSenderToRecipInfo));
		WD.findElement(AddRecipientDetails.fwRemittSenderToRecipInfo).clear();
		WD.findElement(AddRecipientDetails.fwRemittSenderToRecipInfo).sendKeys("!@#$%^&*(!@#$%");
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwInvdSendrToRecipInfo());
	}

	public static void fwDocEmtyDocDat() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwStruDocuDate));
		WD.findElement(AddRecipientDetails.fwStruDocumIdNum).sendKeys(Keys.TAB);
		WD.findElement(By.xpath("//span[@class=\"info_small clear_date ng-star-inserted\"]")).click();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocEmtyDocDat());
	}

	public static void enterSenderToRecipInfo_And_ValidateErrorMsg(String expectedError) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwRemittSenderToRecipInfo));
		WD.findElement(AddRecipientDetails.fwRemittSenderToRecipInfo).sendKeys("Information1");
		
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorFieldMsg_SenderToRecipInfo));
		String errMsg = WD.findElement(errorFieldMsg_SenderToRecipInfo).getText();
		Assert.assertEquals(errMsg, expectedError);
	}
	
	
	
//============================================== *** Boundary Value Analysis ***=========================================================================================		

	public static void fetchSenderAccountDetails(String expectedSenderDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(eyeVisibility));
		WD.findElements(eyeVisibility).get(0).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(finalPage_AccountDetails));
		String actualAccDetails = WD.findElements(finalPage_AccountDetails).get(0).getText();
//		System.out.println("Sender details :-" +actualAccDetails);
//		 Extracting the Account Number from Sender Details
		actualAccDetails = actualAccDetails.replaceAll("[^0-9]", "");
		System.out.println("Sender details we gave:- " + expectedSenderDetails);
		System.out.println("Sender details it took:- " + actualAccDetails);
		System.out.println("Account number length which we gave:- " + expectedSenderDetails.length());
		System.out.println("Account number length which it accepted:- "+ actualAccDetails.length());

		if (expectedSenderDetails.length() == 35) {
			expectedSenderDetails = expectedSenderDetails.substring(0, 34);
		}
		Assert.assertEquals(actualAccDetails, expectedSenderDetails);
	}

	
	
	public static void fetchCreditorAccountDetails(String expectedRecipDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(eyeVisibility));
		WD.findElements(eyeVisibility).get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(finalPage_AccountDetails));
		String actualAccDetails = WD.findElements(finalPage_AccountDetails).get(1).getText();
//		System.out.println("Recipient details :- " + actualAccDetails);
		System.out.println("Recipient details We gave:- " + expectedRecipDetails);
		System.out.println("Length we gave :-" + expectedRecipDetails.length());

//this if_block is only for Acc_num & Routing_Num validation (It allows only numerical values)		
		if (expectedRecipDetails.matches("\\d+")) {
//	Extracting the Account Number from Recipient Details
			actualAccDetails = actualAccDetails.replaceAll("[^0-9]", "");
			System.out.println("Recipient details it took:- " + actualAccDetails);
			System.out.println("Length it took :- " + actualAccDetails.length());
//	For Account Number greater than 34 digits
			if (expectedRecipDetails.length() == 35) {
				expectedRecipDetails = expectedRecipDetails.substring(0, 34);
			}
//	For Routing Number greater than 9 digits
			if (expectedRecipDetails.length() == 10) {
				expectedRecipDetails = expectedRecipDetails.substring(0, 9);
			}
			Assert.assertEquals(actualAccDetails, expectedRecipDetails);

		} else {           // this else_block is for Recipient_Name validation (It will allow alphabets & alphaNumerics)
//	 Extracting the Recipient Name from Recipient Details
			actualAccDetails = actualAccDetails.substring(0, actualAccDetails.indexOf(","));
			System.out.println("Recipient details it took:- " + actualAccDetails);
			System.out.println("Length it took :- " + actualAccDetails.length());
			if (expectedRecipDetails.length() == 61) {
				expectedRecipDetails = expectedRecipDetails.substring(0, 60);
			}
			if (expectedRecipDetails.length() == 141) {
				expectedRecipDetails = expectedRecipDetails.substring(0, 140);
			}
			Assert.assertEquals(actualAccDetails, expectedRecipDetails);

		}
//	    Assert.assertEquals(actualAccDetails , expectedRecipDetails);

	}
	
	
  public static void assertionBVA_RoutingNumber(String exceptedRoutingNum) {
	  WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(eyeVisibility));
		WD.findElement(finalPage_PayToDropDown).click();
		WD.findElements(eyeVisibility).get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(finalPage_RecipRoutingNum));
		String actualRoutingNum = WD.findElements(finalPage_RecipRoutingNum).get(0).getText();
		System.out.println("Routing Number we gave:- "+ exceptedRoutingNum);
		System.out.println("Routing Number it took:- "+ actualRoutingNum);
		
		if (exceptedRoutingNum.length() == 10) {
			exceptedRoutingNum = exceptedRoutingNum.substring(0, 9);
		}
		Assert.assertEquals(actualRoutingNum , exceptedRoutingNum);
		
  }
  
  public static void assertionBVA_PhoneNumber(String exceptedPhoneNum) {
	  WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(eyeVisibility));
		WD.findElement(finalPage_PayToDropDown).click();
		WD.findElements(eyeVisibility).get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(finalPage_RecipPhoneNum));
		String actualPhoneNum = WD.findElements(finalPage_RecipPhoneNum).get(1).getText();
		
//	Removing special characters & space in the phone number and then removing the first digit in the phone number 	
		actualPhoneNum = actualPhoneNum.replaceAll("[^0-9]", "").substring(1);
		System.out.println("Phone Number we gave:- "+ exceptedPhoneNum);
		System.out.println("Phone Number it took:- "+ actualPhoneNum);
		
		if (exceptedPhoneNum.length() == 11) {
			exceptedPhoneNum = exceptedPhoneNum.substring(0, 10);
		}
		Assert.assertEquals(actualPhoneNum , exceptedPhoneNum);
		
  }
  
  public static void assertionBVA_Amount(String exceptedPhoneNum) {
	  WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(80));
		wait.until(ExpectedConditions.presenceOfElementLocated(eyeVisibility));
		WD.findElement(finalPage_PayToDropDown).click();
		WD.findElements(eyeVisibility).get(1).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(finalPage_RecipPhoneNum));
		String actualPhoneNum = WD.findElements(finalPage_RecipPhoneNum).get(1).getText();
		
//	Removing special characters & space in the phone number and then removing the first digit in the phone number 	
		actualPhoneNum = actualPhoneNum.replaceAll("[^0-9]", "").substring(1);
		System.out.println("Phone Number we gave:- "+ exceptedPhoneNum);
		System.out.println("Phone Number it took:- "+ actualPhoneNum);
		
		if (exceptedPhoneNum.length() == 11) {
			exceptedPhoneNum = exceptedPhoneNum.substring(0, 10);
		}
		Assert.assertEquals(actualPhoneNum , exceptedPhoneNum);
		
  }

	
	
	

//=======================================*** BTR Payments ***==================================================================================================================================================================================================================================
//==========================>> Unique Reference <<======================

	public static void enterinvalidUniqueReference_And_Assertion() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.UniqueReference));
		WD.findElement(AddRecipientDetails.UniqueReference).sendKeys("d8bb32d9-1acc-4555-a2ee-f721125c36");
		continueClick_And_AssertingSingleErrorFieldMsg("Invalid Unique Reference. ");
	}
	
	
	
	public static void info_For_RecipFI_DropDownOptions_Validation(String numberExpectedOptions) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.fwRemittInfoForRecFIDropdwn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(AddRecipientDetails.fwRemittRefToRecip));

		while(WD.findElement(AddRecipientDetails.fwRemittInfoForRecFIDropdwn).getDomAttribute("aria-expanded").equals("false")) {
		WD.findElement(AddRecipientDetails.fwRemittInfoForRecFIDropdwn).click(); }
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fw_info_type-panel")));
        
		List<String> expected_Options = new ArrayList<>();
		expected_Options.add("PHOB (Phone)");
		expected_Options.add("TELB (Telecom)");
		if(numberExpectedOptions.equals("ThreeOptions")){
			expected_Options.add("HOLD (Hold)"); 		}
		
		List<String> actual_Options = new ArrayList<>();
		List<WebElement> allElements = WD.findElements(By.xpath("//div[@id='fw_info_type-panel']//span[@class='mdc-list-item__primary-text']"));
		for (WebElement singleElement : allElements) {
			String option = singleElement.getText();
			actual_Options.add(option);
		}
		actual_Options.remove("Select");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_info_type-panel']//span[text()='PHOB (Phone) ']"))).click();

		validateDropDownOptions(expected_Options, actual_Options);
		
	}

	
	public static void validateDropDownOptions(List<String> expectedOptions, List<String> actualOptions) {
		// Convert to sets of trimmed strings
		        Set<String> expectedOptionsSet = new HashSet<>(expectedOptions);
		        Set<String> actualOptionsSet = new HashSet<>(actualOptions);
		        if (!expectedOptionsSet.equals(actualOptionsSet)) {
		// Checking if there are any missing Options or not (ExceptedOptions - ActualOptions = Missing Options)
		        	Set<String> missing = new HashSet<>(expectedOptionsSet);
		            missing.removeAll(actualOptionsSet);
		// Checking if there are any Unexpected messages or not (ActualMsgs - ExceptedMsgs = UnExpected Options)
		            Set<String> unexpected = new HashSet<>(actualOptionsSet);
		            unexpected.removeAll(expectedOptionsSet);
		// Building the String to print all the Missing/Unexpected messages
		            StringBuilder errorMessage = new StringBuilder();
		            errorMessage.append("DropDown_Options validation failed!\n");
		            if (!missing.isEmpty()) {
		                errorMessage.append("Missing Options:\n");
		                for (String msg : missing) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		            if (!unexpected.isEmpty()) {
		                errorMessage.append("Unexpected Options:\n");
		                for (String msg : unexpected) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		//  Fail the test
		            Assert.fail(errorMessage.toString());
		        } else {
		            System.out.println("Actual DropDown Options are matching with the Expected DropDown Options ..!");
		        }
		    }

	
	
	
//==========================================================================================================================================================================================================================================	
	
	
	
//	public static void tms_FinalPageValidation_TransactionInformation(AddRecipientDetails objRecipDetails) {
//		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("basicDetails")));
//	   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='basicDetails']//label")));
//// List for splitting the Web Elements 		
//	   List<WebElement> allTransferInformationLabels = WD.findElements(By.xpath("//div[@id='basicDetails']//label"));
//	   int indexNum = 0 ;
//	   for (WebElement individualTransferInfoLabel : allTransferInformationLabels) {
//		   String currentTransferInfoLabel = individualTransferInfoLabel.getText();
//		   String currentTransferInfoData = WD.findElements(By.xpath("//div[@id='basicDetails']//strong")).get(indexNum).getText();
//		   System.out.println(currentTransferInfoLabel + ":- " + currentTransferInfoData);
//
//		   if(currentTransferInfoLabel.contains("Division")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getDivision());
//		   } 
//		   else if(currentTransferInfoLabel.contains("Transfer Method")) {
//			   tms_FinalPageValidation_TransferMethod(objRecipDetails, currentTransferInfoData);
//		   }
//		   else if(currentTransferInfoLabel.contains("Channel")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getChannel());
//		   }
//		   else if(currentTransferInfoLabel.contains("Transaction Type")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransactionType());
//		   }
//		   else if(currentTransferInfoLabel.equalsIgnoreCase("Amount:")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTranferAmount());
//		   }
//		   else if(currentTransferInfoLabel.equalsIgnoreCase("Fee:")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransferFee());
//		   }
//		   else if(currentTransferInfoLabel.equalsIgnoreCase("Total Amount:")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransferTotalAmount());
//		   }
//		   else if(currentTransferInfoLabel.contains("Scheduled Date:")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransferDate());
//		   }
////		   else if(currentTransferInfoLabel.contentEquals("Value Date:")) {
////			   Assert.assertEquals(currentTransferInfoData, "");
////		   }
//		   else if(currentTransferInfoLabel.contains("Client Reference")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransferReference());
//		   }
//		   else if(currentTransferInfoLabel.contains("Confirmation Number")) {
//			   Assert.assertEquals(currentTransferInfoData, objRecipDetails.getTransferConfirmNum());
//		   }
//		   
//		indexNum++ ;
//	  }
//	}
//	
//	
//	public static void tms_FinalPageValidation_TransferMethod(Validation_Page objTarnsferDtls, String actualTransferMethod) {
//		  System.out.println("In validatingTransferMethod:- "+objTarnsferDtls.getTransferSpeed());
//		  int flag = 0;
//		   if("Instant".equalsIgnoreCase(objTarnsferDtls.getTransferSpeed())) {
//			   if(objTarnsferDtls.getTestCaseDescription().contains("Internal to Internal")) {
//				   Assert.assertEquals(actualTransferMethod, "OnUs");
//				   flag++ ;
//			   }else {
//				   Assert.assertTrue("RTP-FedNow".contains(actualTransferMethod), "Assert failed--> TransferMethod doesn't contains RTP-FedNow");
//				   flag++ ;
//			    }
//		   }else if("Within The Day".equalsIgnoreCase(objTarnsferDtls.getTransferSpeed())) {
//			   Assert.assertTrue("Same Day ACH-WEB".contains(actualTransferMethod), "Assert failed--> TransferMethod doesn't contains Same Day ACH-WEB");
//			   flag++ ;
//
//		   }else if("1-2 Business Days".equalsIgnoreCase(objTarnsferDtls.getTransferSpeed())) {
//			   Assert.assertTrue("ACH-WEB".contains(actualTransferMethod), "Assert failed--> TransferMethod doesn't contains ACH-WEB");
//			   flag++ ;
//
//		   }else if("Wire".equalsIgnoreCase(objTarnsferDtls.getTransferSpeed())) {
//			   Assert.assertEquals(actualTransferMethod, "Fedwire");
//			   flag++ ;
//		   }
//		   if(flag==0) {
//			   Assert.fail("Transfer Method/Speed Assertion didn't happen");
//		   }
//	  }
//		
//	
//	public static void tms_FinalPageValidation_TransferInfoAtTMSTitle(Validation_Page objTarnsferDtls) {
//		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(transferConfirmNum_AtTitle));
//		   tms_FinalPageValidation_TransferMethod(objTarnsferDtls, WD.findElement(transferMethod_AtTitle).getText());
//		   Assert.assertEquals(WD.findElement(transferType_AtTitle).getText(), objTarnsferDtls.getTransactionType());
//		   Assert.assertEquals(WD.findElement(transferStatus_AtTitle).getText(), objTarnsferDtls.getTransferFinalStatus());
//		   Assert.assertEquals(WD.findElement(transferConfirmNum_AtTitle).getText().substring(21), objTarnsferDtls.getTransferConfirmNum());
//	}
//
//	
//	
//	public static void tms_FinalPageValidation_MoneyFlowForOrginatorTransaction(Validation_Page objTarnsferDtls) {
//		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flex_column details_glance2 text-center']")));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(transferMoneyAtCenter));
//////Need To modify this Value Date ...as there is difference between ScheduledDate & Value Date 		
////		Assert.assertEquals(WD.findElement(transferValueDateAtCenter).getText().replace("(Value Date)", "").trim(), objTarnsferDtls.getTransferDate());
//		Assert.assertEquals(WD.findElement(transferMoneyAtCenter).getText().replace("(Amount)", "").trim(), objTarnsferDtls.getTranferAmount());
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='money-flow-left']")));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//i[@class='fa fa-caret-right'])[1]")));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='dollar-flow-left']")));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//i[@class='fa fa-caret-right'])[2]")));
//	}
//	
//	
//	public static void tms_FinalPageValidation_SenderAndRecipDetails(Validation_Page objTarnsferDtls) {
//		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='flex_column details_glance3 overlay_dropdown_details']")));
//        String[] transferFromAccDetailsSplit = objTarnsferDtls.getTransferFrom().toLowerCase().split(" ");
//        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(0).getText().toLowerCase().contains(transferFromAccDetailsSplit[0]), "Assert failed at Sender Name");
//        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(0).getText().toLowerCase().contains(transferFromAccDetailsSplit[1]), "Assert failed at Sender Acc Num");
//        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(0).getText().toLowerCase().contains(transferFromAccDetailsSplit[transferFromAccDetailsSplit.length-1]), "Assert failed at Sender Acc Type");
//        String[] transferToAccDetailsSplit = objTarnsferDtls.getTransferTo().toLowerCase().split(" ");
//// Recipient Name is different ---> In dropDown it starts with "Demo" and in LookUp it starts with "Mary"       
////        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(1).getText().toLowerCase().contains(transferToSplit[0]), "Assert failed at Recipient Name");
////        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(1).getText().toLowerCase().contains(transferToAccDetailsSplit[1]), "Assert failed at Recipient Acc Type");
//        Assert.assertTrue(WD.findElements(transferNameAccNumAccType).get(1).getText().toLowerCase().contains(transferToAccDetailsSplit[transferToAccDetailsSplit.length-1]), "Assert failed at Recipient Acc Num");
//
//        
//	}
//	

	
	
	
	
	
}