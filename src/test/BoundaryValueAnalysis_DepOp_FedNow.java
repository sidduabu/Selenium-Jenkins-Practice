package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
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

public class BoundaryValueAnalysis_DepOp_FedNow extends Utility {

	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	static ByteArrayOutputStream logStream;
	static PrintStream originalOut;

	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-05");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);

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

//	 Redirect System.out to capture logs
		logStream = new ByteArrayOutputStream();
		originalOut = System.out;
		System.setOut(new PrintStream(logStream, true, StandardCharsets.UTF_8));

	}

//=============>> Sender Account Number <<================	

	@Test(testName = "Sender_AccNum--> less than 4 digits", priority = 1)
	public static void tc001SenderAccNum_LessThan_04Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-000");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "Sender_AccNum--> With_Exact_04Digits", priority = 2)
	public void tc002SenderAccNum_With_Exact_04Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-002");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());

	}

	@Test(testName = "Sender_AccNum--> Exact_34Digits", priority = 3)
	public void tc003SenderAccNum_With_Exact_34Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "Sender_AccNum--> GreaterThan_34Digits", priority = 4)
	public static void tc004SenderAccNum_GreaterThan_34Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());

	}

//=============>> Recipient Account Number <<================		

	@Test(testName = "Recipient_AccNum--> Less than 4 digits", priority = 5)
	public void tc005Recipient_AccNum_With_LessThan_04Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-005");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "Recipient_AccNum--> Exact 4 digits", priority = 6)
	public void tc006Recipient_AccNum_With_Exact_04Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-006");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "Recipient_AccNum--> Exact 34 digits", priority = 7)
	public void tc007Recipient_AccNum_With_Exact_34Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-007");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "Recipient_AccNum--> More than 34 digits", priority = 8)
	public void tc008Recipient_AccNum_With_MoreThan_34Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-008");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

//=============================>> Recipient Name <<=========================================================================	

	@Test(testName = "Recipient_Name--> Less than One digit", priority = 9)
	public void tc009RecipientName_LessThan_OneDigit_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-009");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam());
	}

	@Test(testName = "Recipient_Name--> Exact One digit", priority = 10)
	public void tc010RecipientName_Exact_OneDigit_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-010");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());

	}

	@Test(testName = "Recipient_Name--> Exact  60 digit", priority = 11)
	public void tc011RecipientName_Exact_60Digit_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-011");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());

	}

	@Test(testName = "Recipient_Name--> greater than 60 digit", priority = 12)
	public void tc012RecipientName_GreaterThan_60Digit_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-012");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());

	}

//============================= Routing Number =================================================

	@Test(testName = "RoutingNumber_LessThan 9-Digits", priority = 13)
	public void tc013RoutingNumber_LessThan_9Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-013");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinRoutNumLengthErorrMsg());
	}

	@Test(testName = "RoutingNumber_Exact 9-Digits", priority = 14)
	public void tc014RoutingNumber_Exact_9Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-014");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());

	}

	@Test(testName = "RoutingNumber_Greater 9-Digits", priority = 15)
	public void tc015RoutingNumber_GreaterThan_9Digits_BVA_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-015");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());

	}

//// ============================= Amount Field =================================================
//
//	@Test(testName = "Amount Less Than", priority = 16)
//	public static void tc016Amount_LessThan_() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-016");
//		AddSenderDetails.Clik_Transfers_Link_DepOp();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
////		AddRecipientDetails.currentBalanceDisplay_DepOp();
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg1(objErrDtls.getInvdAmt());
//	}
//
//	@Test(testName = "Amount Exact", priority = 17)
//	public static void tc017Amount_Exact() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-017");
//		AddSenderDetails.Clik_Transfers_Link_DepOp();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
////		AddRecipientDetails.currentBalanceDisplay_DepOp();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertionBVA_Amount(objReciDtls.getPmtAmt());
//
//	}
//
//	@Test(testName = "Amount Exact ", priority = 18)
//	public static void tc018Amount_Exact() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-018");
//		AddSenderDetails.Clik_Transfers_Link_DepOp();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
//		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		AddRecipientDetails.currentBalanceDisplay_DepOp();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getPmtAmt());
//	}
//
//	@Test(testName = "Amount Greater Than", priority = 19)
//	public static void tc019Amount_GreaterThan() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-019");
//		AddSenderDetails.Clik_Transfers_Link_DepOp();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
//		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		AddRecipientDetails.currentBalanceDisplay_DepOp();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getPmtAmt());
//	}

//============================= Recipient Phone Number =================================================

	@Test(testName = "PhoneNumber_LessThan 10-Digits", priority = 20)
	public void tc020PhoneNumber_LessThan_10Digits_BVA_DepOp_RTD_FN() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-020");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinPhoneNumLengthErrorMsg());
	}

	@Test(testName = "PhoneNumber_Exact 10-Digits", priority = 21)
	public void tc021PhoneNumber_Exact_10Digits_BVA_DepOp_RTD_FN() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-021");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_PhoneNumber(objReciDtls.getRecipientPhone());

	}

	@Test(testName = "PhoneNumber_Greater Than 10-Digits", priority = 22)
	public void tc022PhoneNumber_GreaterThan_10Digits_BVA_DepOp_RTD_FN() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-RTD-FN-022");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_PhoneNumber(objReciDtls.getRecipientPhone());

	}


	
	@AfterMethod
	public void endTest(ITestResult testResult) throws Exception {
//		Code for printing the output/SOP's in reports
		System.setOut(originalOut); // Restore original System.out
		String consoleLogs = logStream.toString(StandardCharsets.UTF_8).trim(); // Capture Console Logs & Format for
																				// ExtentReports
		String formattedLogs = consoleLogs.replace("\n", "<br>"); // Preserve line breaks in HTML format
		extentTest.info("Captured Console Logs:<br>" + formattedLogs); // Attach formatted logs to ExtentReports

		if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, testResult.getMethod().getMethodName() + " Failed");
			extentTest.log(Status.INFO, testResult.getThrowable().getMessage());
		}
		extentTest.log(Status.INFO, testResult.getMethod().getMethodName() + " Test Completed");
		extentTest.log(Status.INFO, "Screen Attatched",
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.takeScreenShot()).build());
		extentReports.flush();

		String testCaseStatus = AddSenderDetails.closeBtn2();
		if (testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			startSuite();
		}
	}

	@AfterClass
	public void endSuite() throws Exception {
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);	
		}
}
