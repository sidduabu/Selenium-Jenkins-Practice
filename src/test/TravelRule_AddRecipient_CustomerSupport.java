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

public class TravelRule_AddRecipient_CustomerSupport extends Utility {

	
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
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
		while(extentReports.getReport().findTest(testMethod.getName()).isPresent())
		{
			extentReports.removeTest(testMethod.getName());
			if(! extentReports.getReport().getTestList().isEmpty())
				extentReports.flush();
		}
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");

	}
	
	
	
	@Test(testName = "TravelRule_POBox_Validation_For_RecipientAddress_CustSupport_AddRecip", priority = 1)
	public void tc001TravelRule_POBox_Validation_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-001");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> P.O.Box  	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_WhiteListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip", priority = 2)
	public void tc002TravelRule_WhiteListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-002");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with Whitelisted_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> Whitelisted_Country  	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_BlackListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip", priority = 3)
	public void tc003TravelRule_BlackListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with BlackListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> BlackListed_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_POBox_WhiteListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip", priority = 4)
	public void tc004TravelRule_POBox_WhiteListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box & Whitelisted_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> P.O.Box & Whitelisted_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_POBox_BlackListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip", priority = 5)
	public void tc005TravelRule_POBox_BlackListedCountry_Validation_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box & BlackListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> P.O.Box & BlackListed_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getBlacklistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	
	
	@Test(testName = "TravelRule_POBox_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip", priority = 6)
	public void tc006TravelRule_POBox_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-001");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//  Error Message Validation --> P.O.Box  	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Clicking the Recipient Address check Box	
     	AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
        AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-000");
// Now entering the Valid Recipient Address 		
	    AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_Whitelisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip", priority = 7)
	public void tc007TravelRule_Whitelisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-002");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with WhiteListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Clicking the Recipient Address check Box	
     	AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
        AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-000");
// Now entering the Valid Recipient Address 		
	    AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_Blacklisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip", priority = 8)
	public void tc008TravelRule_Blacklisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with BlackListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Clicking the Recipient Address check Box	
     	AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
        AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-000");
// Now entering the Valid Recipient Address 		
	    AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_POBox_Whitelisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip", priority = 9)
	public void tc009TravelRule_POBox_Whitelisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-004");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box & WhiteListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Clicking the Recipient Address check Box	
     	AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
        AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-000");
// Now entering the Valid Recipient Address 		
	    AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}

	
	@Test(testName = "TravelRule_POBox_Blacklisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip", priority = 10)
	public void tc010TravelRule_POBox_Blacklisted_And_InvalidToValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-005");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Recipient Address with P.O.Box & BlackListed_Country details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Clicking the Recipient Address check Box	
     	AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
		AddRecipientDetails.editClick();
        AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-000");
// Now entering the Valid Recipient Address 		
	    AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	
	@Test(testName = "TravelRule_POBox_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip", priority = 11)
	public void tc011TravelRule_POBox_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-000");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Valid Recipient Address details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
 // Click Edit Button		
     	AddRecipientDetails.editClick();
		AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-001");
//	Entering Recipient Address with P.O.Box details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
//  Error Message Validation --> P.O.Box  	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getPoBoxErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_WhiteListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip", priority = 2)
	public void tc012TravelRule_WhiteListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-002");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Valid Recipient Address details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
     	AddRecipientDetails.editClick();
//	Entering Recipient Address with Whitelisted_Country details	
		AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-002");
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
//  Error Message Validation --> Whitelisted_Country  	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getWhitelistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_BlackListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip", priority = 3)
	public void tc013TravelRule_BlackListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Valid Recipient Address details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
     	AddRecipientDetails.editClick();
//	Entering Recipient Address with BlackListed_Country details	
		AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
//  Error Message Validation --> BlackListed_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getBlacklistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_P.O.Box & WhiteListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip", priority = 4)
	public void tc014TravelRule_POBox_WhiteListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Valid Recipient Address details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
     	AddRecipientDetails.editClick();
//	Entering Recipient Address with P.O.Box & Whitelisted_Country details	
		AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-004");
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
//  Error Message Validation --> P.O.Box & Whitelisted_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getWhitelistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "TravelRule_POBox_BlackListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip", priority = 5)
	public void tc015TravelRule_POBox_BlackListedCountry_And_ValidToInValid_For_RecipientAddress_CustSupport_AddRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TRVL-MGD-RC-000");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("TRVL-RC-003");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//	Entering Valid Recipient Address details	
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
// Click Edit Button		
     	AddRecipientDetails.editClick();
//	Entering Recipient Address with P.O.Box & BlackListed_Country details	
		AddRecipientDetails objRecipDtls2 = TestDataReader.loadRecipientDetails("TRVL-RC-005");
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls2);
//  Error Message Validation --> P.O.Box & BlackListed_Country 	
        AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingTwoErrorFieldMsgs_WithOutContinueClick(objErrDtls.getPoBoxErrMsg(),objErrDtls.getBlacklistedCountryErrMsg());
// Now Clicking the Recipient Address check Box	
		AddRecipientDetails.travelRuleCheckBoxClick("No", "Yes");
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
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
		
		String testCaseStatus = AddSenderDetails.closeBtn2();
		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD); 
			startSuite();               }

	}

	@AfterClass
	public void endSuite() throws Exception {
		
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}
	
}
