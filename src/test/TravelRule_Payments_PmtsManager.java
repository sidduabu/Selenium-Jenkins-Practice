package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import dataReader.ErrorMessageData;
import dataReader.TestDataReader;
import dataReader.Utility;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;
import pages.Login;
import pages.ValidationPage;

public class TravelRule_Payments_PmtsManager extends Utility {

	static Login objLogin;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-07");
		Login.loginTest(objLogin);
	}

	@BeforeMethod
	public void startTest(Method testMethod) throws Exception {
		String filepath = Paths.get(System.getProperty("user.dir")) + "/TestReport/Report_"
				+ System.getProperty("user.name") + Paths.get(System.getProperty("user.dir")).getFileName()
				+ dateFormat.format(date) + ".html";
		extentSparkReporter = new ExtentSparkReporter(filepath);
		extentReports = new ExtentReports();
		extentSparkReporter.config().thumbnailForBase64(true);
		JsonFormatter json = new JsonFormatter("extent.json");
		extentReports.createDomainFromJsonArchive("extent.json");
		extentReports.attachReporter(json, extentSparkReporter);
		extentReports.keepLastRetryOnly(true);
		while (extentReports.getReport().findTest(testMethod.getName()).isPresent()) {
			extentReports.removeTest(testMethod.getName());
			if (!extentReports.getReport().getTestList().isEmpty())
				extentReports.flush();
		}
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");

	}

//==================================>> Sender Address Fields <<=====================================================================
	
	
	@Test(testName = "TravelRule_POBox_Validation_For_SenderAddress", priority = 1)
	public void tc001TravelRule_POBox_Validation_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with P.O.Box details	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//  Clicking only Recipient Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
//  Error Message Validation --> P.O.Box  		
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Now Clicking the Sender Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_WhitelistedCountry_Validation_For_SenderAddress", priority = 2)
	public void tc002TravelRule_WhitelistedCountry_Validation_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with WhiteListed Country details			
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Clicking only Recipient Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
//  Error Message Validation -->Whitelisted_Country 
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
//  Now Clicking the Sender Address check Box				
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_BlacklistedCountry_Validation_For_SenderAddress", priority = 3)
	public void tc003TravelRule_BlacklistedCountry_Validation_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with Blacklisted Country details			
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Clicking only Recipient Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
//  Error Message Validation -->Blacklisted_Country 
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
//  Now Clicking the Sender Address check Box						
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_WhitelistedCountry_Validation_For_SenderAddress", priority = 4)
	public void tc004TravelRule_POBox_And_WhitelistedCountry_Validation_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with P.O.Box & Whitelisted_Country details			
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Clicking only Recipient Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
//  Error Message Validation-->  P.O.Box & Whitelisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
//  Now Clicking the Sender Address check Box						
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_BlacklistedCountry_Validation_For_SenderAddress", priority = 5)
	public void tc005TravelRule_POBox_And_BlacklistedCountry_Validation_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with P.O.Box & Blacklisted_Country details			
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Clicking only Recipient Address check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
//  Error Message Validation-->  P.O.Box & Blacklisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(), objErrDtls.getBlacklistedCountryErrMsg());
//  Now Clicking the Sender Address check Box						
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_InvalidToValid_For_SenderAddress", priority = 6)
	public void tc006TravelRule_POBox_And_InvalidToValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//	Entering Sender Address with P.O.Box	
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Click both Sender & Recipient Address check Boxes				
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
// Now entering the Valid Sender Address 		
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_Whitelisted_And_InvalidToValid_For_SenderAddress", priority = 7)
	public void tc007TravelRule_Whitelisted_And_InvalidToValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//	Entering Sender Address with WhiteListed Country			
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Click both Sender & Recipient Address check Boxes						
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button				
		AddRecipientDetails.editClick();
// Now entering the Valid Sender Address 		
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_Blacklisted_And_InvalidToValid_For_SenderAddress", priority = 8)
	public void tc008TravelRule_Blacklisted_And_InvalidToValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//	Entering Sender Address with BlackListed Country			
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Click both Sender & Recipient Address check Boxes								
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button						
		AddRecipientDetails.editClick();
// Now entering the Valid Sender Address 		
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_Whitelisted_And_InvalidToValid_For_SenderAddress", priority = 9)
	public void tc009TravelRule_POBox_Whitelisted_And_InvalidToValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with P.O.Box & WhiteListed Country			
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Click both Sender & Recipient Address check Boxes										
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button								
		AddRecipientDetails.editClick();
// Now entering the Valid Sender Address 		
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_Blacklisted_And_InvalidToValid_For_SenderAddress", priority = 10)
	public void tc010TravelRule_POBox_Blacklisted_And_InvalidToValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Entering Sender Address with P.O.Box & WhiteListed Country					
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
//  Click both Sender & Recipient Address check Boxes										
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button										
		AddRecipientDetails.editClick();
// Now entering the Valid Sender Address 		
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_ValidToInValid_For_SenderAddress", priority = 11)
	public void tc011TravelRule_POBox_And_ValidToInValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Enter Valid Sender Address	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
		AddRecipientDetails.travelRuleCheckBoxClick("NO", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
		AddRecipientDetails.editClick();
//  Enter Invalid Address --> Address with P.O.Box 	
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-001");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
// Error Message Validation --> P.O.Box 
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Clicking the Sender Address Check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_WhiteListed_And_ValidToInValid_For_SenderAddress", priority = 12)
	public void tc012TravelRule_WhiteListed_And_ValidToInValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Enter Valid Sender Address	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
		AddRecipientDetails.travelRuleCheckBoxClick("NO", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
		AddRecipientDetails.editClick();
//	Enter Invalid Address --> Address with WhiteListed_Country
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-002");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
// Error Message Validation --> Whitelisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
// Clicking the Sender Address Check Box				
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_BlackListed_And_ValidToInValid_For_SenderAddress", priority = 13)
	public void tc013TravelRule_BlackListed_And_ValidToInValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Enter Valid Sender Address	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
		AddRecipientDetails.travelRuleCheckBoxClick("NO", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
		AddRecipientDetails.editClick();
//	Enter Invalid Address --> Address with BlackListed_Country
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-003");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
// Error Message Validation --> Blacklisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
// Clicking the Sender Address Check Box				
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_WhiteListedCountry_ValidToInValid_For_SenderAddress", priority = 14)
	public void tc014TravelRule_POBox_And_WhiteListedCountry_ValidToInValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Enter Valid Sender Address	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
		AddRecipientDetails.travelRuleCheckBoxClick("NO", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
		AddRecipientDetails.editClick();
//	Enter Invalid Address --> Address with P.O.Box & WhiteListed_Country
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-004");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
// Error Message Validation --> P.O.Box & Whitelisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
// Clicking the Sender Address Check Box		
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TravelRule_POBox_And_BlackListedCountry_ValidToInValid_For_SenderAddress", priority = 15)
	public void tc015TravelRule_POBox_And_BlackListedCountry_ValidToInValid_For_SenderAddress_FW_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//	Enter Valid Sender Address	
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		
		AddRecipientDetails.travelRuleCheckBoxClick("NO", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
		AddRecipientDetails.editClick();
//	Enter Invalid Address --> Address with P.O.Box & BlackListed_Country
		AddSenderDetails objSenderDtls2 = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-005");
		AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls2);
// Error Message Validation --> P.O.Box & Blacklisted_Country
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getBlacklistedCountryErrMsg());
// Clicking the Sender Address Check Box				
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

//=======================>> Recipient Address Fields <<=====================================================================	

	@Test(testName = "TravelRule_POBox_Validation_For_RecipientAddress", priority = 16)
			public void tc016TravelRule_POBox_Validation_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box details	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Clicking only Sender Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
//  Error Message Validation --> P.O.Box  		
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Now Clicking the Recipient Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_WhitelistedCountry_Validation_For_RecipientAddress", priority = 17)
			public void tc017TravelRule_WhitelistedCountry_Validation_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-002");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with WhiteListed Country details			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Clicking only Sender Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
//  Error Message Validation -->Whitelisted_Country 
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
//  Now Clicking the Recipient Address check Box				
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_BlacklistedCountry_Validation_For_RecipientAddress", priority = 18)
			public void tc018TravelRule_BlacklistedCountry_Validation_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-003");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with Blacklisted Country details			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Clicking only Sender Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
//  Error Message Validation -->Blacklisted_Country 
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
//  Now Clicking the Recipient Address check Box						
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_WhitelistedCountry_Validation_For_RecipientAddress", priority = 19)
			public void tc019TravelRule_POBox_And_WhitelistedCountry_Validation_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-004");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box & Whitelisted_Country details			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Clicking only Sender Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
//  Error Message Validation-->  P.O.Box & Whitelisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
//  Now Clicking the Recipient Address check Box						
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_BlacklistedCountry_Validation_For_RecipientAddress", priority = 20)
			public void tc020TravelRule_POBox_And_BlacklistedCountry_Validation_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-005");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box & Blacklisted_Country details			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Clicking only Sender Address check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
//  Error Message Validation-->  P.O.Box & Blacklisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(), objErrDtls.getBlacklistedCountryErrMsg());
//  Now Clicking the Recipient Address check Box						
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_InvalidToValid_For_RecipientAddress", priority = 21)
			public void tc021TravelRule_POBox_And_InvalidToValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Click both Sender & Recipient Address check Boxes				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
				AddRecipientDetails.editClick();
// Now entering the Valid Recipient Address 		
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-000");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_Whitelisted_And_InvalidToValid_For_RecipientAddress", priority =22)
			public void tc022TravelRule_Whitelisted_And_InvalidToValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-002");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with WhiteListed Country			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Click both Sender & Recipient Address check Boxes						
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button				
				AddRecipientDetails.editClick();
// Now entering the Valid Recipient Address 		
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-000");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_Blacklisted_And_InvalidToValid_For_RecipientAddress", priority =23)
			public void tc023TravelRule_Blacklisted_And_InvalidToValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-003");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with BlackListed Country			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Click both Sender & Recipient Address check Boxes								
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button						
				AddRecipientDetails.editClick();
// Now entering the Valid Recipient Address 		
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-000");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_Whitelisted_And_InvalidToValid_For_RecipientAddress", priority =24)
			public void tc024TravelRule_POBox_Whitelisted_And_InvalidToValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-004");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box & WhiteListed Country			
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Click both Sender & Recipient Address check Boxes										
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button								
				AddRecipientDetails.editClick();
// Now entering the Valid Recipient Address 		
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-000");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_Blacklisted_And_InvalidToValid_For_RecipientAddress", priority = 25)
			public void tc025TravelRule_POBox_Blacklisted_And_InvalidToValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-005");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Entering Recipient Address with P.O.Box & WhiteListed Country					
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
//  Click both Sender & Recipient Address check Boxes										
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button										
				AddRecipientDetails.editClick();
// Now entering the Valid Recipient Address 		
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-000");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_ValidToInValid_For_RecipientAddress", priority = 26)
			public void tc026TravelRule_POBox_And_ValidToInValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Enter Valid Recipient Address	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
// Clicking only Sender Address check box				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
				AddRecipientDetails.editClick();
//  Enter Invalid Recipient Address --> Address with P.O.Box 	
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-001");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
// Error Message Validation --> P.O.Box 
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Clicking the Sender Address Check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_WhiteListed_And_ValidToInValid_For_RecipientAddress", priority = 27)
			public void tc027TravelRule_WhiteListed_And_ValidToInValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Enter Valid Recipient Address	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
// Clicking only Sender Address check box				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
				AddRecipientDetails.editClick();
//	Enter Invalid Recipient Address --> Address with WhiteListed_Country
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-002");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
// Error Message Validation --> Whitelisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
// Clicking the Recipient Address Check Box				
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_BlackListed_And_ValidToInValid_For_RecipientAddress", priority = 28)
			public void tc028TravelRule_BlackListed_And_ValidToInValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Enter Valid Recipient Address	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
// Clicking only Sender Address check box				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
				AddRecipientDetails.editClick();
//	Enter Invalid Recipient Address --> Address with BlackListed_Country
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-003");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
// Error Message Validation --> Blacklisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
// Clicking the Recipient Address Check Box				
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_WhiteListedCountry_ValidToInValid_For_RecipientAddress", priority = 29)
			public void tc029TravelRule_POBox_And_WhiteListedCountry_ValidToInValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Enter Valid Recipient Address	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				
// Clicking only Sender Address check box				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
				AddRecipientDetails.editClick();
//	Enter Invalid Recipient Address --> Address with P.O.Box & WhiteListed_Country
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-004");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
// Error Message Validation --> P.O.Box & Whitelisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
// Clicking the Recipient Address Check Box		
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}

			@Test(testName = "TravelRule_POBox_And_BlackListedCountry_ValidToInValid_For_RecipientAddress", priority = 30)
			public void tc030TravelRule_POBox_And_BlackListedCountry_ValidToInValid_For_RecipientAddress_FW_Pmts() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-PMTS-000");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TRVL-000");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Payments_Link_FI();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
				AddSenderDetails.enterSenderAddressDetailsInAllIndividualFields(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	Enter Valid Recipient Address	
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
// Clicking only Sender Address check box				
				AddRecipientDetails.travelRuleCheckBoxClick("Yes", "No");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
//	Edit Button Click	
				AddRecipientDetails.editClick();
//	Enter Invalid Recipient Address --> Address with P.O.Box & BlackListed_Country
				AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("TRVL-005");
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
// Error Message Validation --> P.O.Box & Blacklisted_Country
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
				ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getBlacklistedCountryErrMsg());
// Clicking the Recipient Address Check Box				
				AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
				AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
				AddSenderDetails.directSubmitMsg();
			}
	
	
	@AfterMethod
	public void endTest(ITestResult testResult) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, testResult.getMethod().getMethodName() + " Failed");
			extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
		}
		extentTest.log(Status.INFO, testResult.getMethod().getMethodName() + " Test Completed");
		extentTest.log(Status.INFO, "Screen Attatched",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.takeScreenShot()).build());
		extentReports.flush();

//		String testCaseStatus = AddSenderDetails.closeBtn2();
//		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
//	         startSuite(); 
//	         Utility.closeBrowser(WD);       }
	}

	@AfterClass
	public void endSuite() throws Exception {
//		AddSenderDetails.logOff();
//		Utility.closeBrowser(WD);
	}

}
