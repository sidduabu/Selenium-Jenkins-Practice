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

public class Validation_DepOp_Transfers_FedNow extends Utility {
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-06");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
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
	}


	@Test(priority = 1, testName = "EmptyAccNum")
	public static void tc001EmptyAccNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-001");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 2, testName = "InvalidAccNum")
	public static void tc002InvalidAccNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 3, testName = "EmptyChannel")
	public static void tc003EmptyChannel_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-003");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyChannel());
	}

	@Test(priority = 4, testName = "EmptyRecipTyp")
	public static void tc004EmptyRecipTyp_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-004");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());
	}

	@Test(priority = 5, testName = "EmptyRecipName")
	public static void tc005EmptyRecipName_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-005");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam());
	}

	@Test(priority = 6, testName = "EmptyRecipAddressLine")
	public static void tc006EmptyRecipAddrsLine_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-006");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.emptyAddressLine();
	}

	@Test(priority = 7, testName = "EmptyRecipTown")
	public static void tc007EmptyRecipTown_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-007");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipTown());
	}

	@Test(priority = 8, testName = "EmptyRecipState")
	public static void tc008EmptyRecipState_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-008");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipState());
	}

	@Test(priority = 9, testName = "EmptyRecipCountry")
	public static void tc009EmptyRecipCountry_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipCntry());
	}

	@Test(priority = 10, testName = "EmptyRecipZipCode")
	public static void tc010EmptyRecipZipCode_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipZipCod());
	}

	@Test(priority = 11, testName = "EmptyRecipRoutNum")
	public static void tc011EmptyRecipRoutNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipRoutNum());
	}

	@Test(priority = 12, testName = "EmptyRecipAccNum")
	public static void tc012EmptyRecipAccNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 13, testName = "EmptyRecipAmt")
	public static void tc013EmptyRecipAmt_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-013");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAmt());
	}

	@Test(priority = 14, testName = "EmptyEndToEnd")
	public static void tc014EmptyEndToEnd_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyEndToEnd());
	}

	@Test(priority = 15, testName = "InvdRecipNam")
	public static void tc015InvdRecipNam_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipNam());
	}

	@Test(priority = 16, testName = "InvdRecipZipCode")
	public static void tc016InvdRecipZipCode_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//	As we are entering the amount so the API call will happens so we are getting another error message
//	next month @Anil going to fix this so up to next month we will run like this 				
//		AddRecipientDetails.enterAmount(objReciDtls);
//		// AddRecipientDetails.totalAmountDisplayed();
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
//		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 17, testName = "InvdRecipEmail")
	public static void tc017InvdRecipEmail_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//	As we are entering the amount so the API call will happens so we are getting another error message
//	next month @Anil going to fix this so up to next month we will run like this 				
//		AddRecipientDetails.enterAmount(objReciDtls);
//		// AddRecipientDetails.totalAmountDisplayed();
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
//		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipEmail());
	}

	@Test(priority = 18, testName = "InvdRecipPhnNum")
	public static void tc018InvdRecipPhnNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-018");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipPhn());
	}

	@Test(priority = 19, testName = "InvdRecipRoutingNum")
	public static void tc019InvdRecipRoutingNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipRoutNum());
	}

	@Test(priority = 20, testName = "InvdRecipAccNum")
	public static void tc020InvdRecipAccNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-020");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 21, testName = "InvdRecipAmt")
	public static void tc021InvdRecipAmt_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-021");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdAmt());
	}

	@Test(priority = 22, testName = "InvdRecipEndToEnd")
	public static void tc022InvdRecipEndToEnd_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-022");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdEndToEnd());
	}

	@Test(priority = 23, testName = "AmtMaxExcd")
	public static void tc023AmtMaxExcd_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-023");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd6());
	}

//===================>>  Remittance <<=======================================================================================================================================	

	@Test(priority = 24, testName = "RtpEmtyDocTyp")
	public static void tc024RtpEmtyDocTyp_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyDocTyp());
	}

	@Test(priority = 25, testName = "RtpEmtyDoc")
	public static void tc025RtpEmtyDoc_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-025");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyDoc());
	}

	@Test(priority = 26, testName = "RtpEmtyDate")
	public static void tc026RtpEmtyDate_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.rtpEmtyDate();
	}

//	@Test(priority = 27, testName = "RtpEmtyInvoiceAmt")
//	public static void tc027RtpEmtyInvoiceAmt_VD_FedNow_Transfers() throws Exception {
//		commonMethod("VD-Pmts-RTD-FN-027");
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.rtpInvcDiscAmt("invoice", "", objErrDtls.getRtpEmtyInvcAmt());
//	}
//
//	@Test(priority = 28, testName = "RtpEmtyDiscountAmt")
//	public static void tc028RtpEmtyDiscountAmt_VD_FedNow_Transfers() throws Exception {
//		commonMethod("VD-Pmts-RTD-FN-028");
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.rtpInvcDiscAmt("discount", "", objErrDtls.getRtpEmtyDiscAmt());
//	}

	@Test(priority = 29, testName = "RtpEmtyLocationMethod")
	public static void tc029RtpEmtyLocationMethod_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-029");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyRemitLocMetd());
	}

	@Test(priority = 30, testName = "RtpInvlaidInvoiceAmount")
	public static void tc030RtpInvalidInvoiceAmt_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-030");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("invoice", "!@#$%", objErrDtls.getRtpInvdInvcAmt());
	}

	@Test(priority = 31, testName = "RtpInvlaidDiscAmount")
	public static void tc031RtpInvlaidDiscAmt_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-031");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("discount", "!@#$%", objErrDtls.getRtpInvdDiscAmt());
	}

	@Test(priority = 32, testName = "RtpInvlaidRemittId")
	public static void tc032RtpInvlaidRemittId_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-032");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpInvdRemitId());
	}

//=======================>> Upload Document <<======================================================================================================================================================	

	@Test(priority = 33, testName = "RtpEmptyDocType")
	public static void tc033RtpEmptyDocType_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-033");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getEmptyDocTyp());
	}

	@Test(priority = 34, testName = "RtpInvdFileType")
	public static void tc034RtpInvdFileType_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-034");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getInvdUploadDoc());
	}

	@Test(priority = 35, testName = "RtpMaxFileSize")
	public static void tc035RtpMaxFileSize_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-035");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		// AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
	}

//================ Missing/Extra Cases (Date:- 31-January-2025) =================================================================

	@Test(priority = 36, testName = "InvdRecipZipCode00000")
	public static void tc036InvdRecipZipCode00000_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-036");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 37, testName = "InvalidBankNameSearchForRoutingNum")
	public static void tc037InvalidBankNameSearchForRoutingNum_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-037");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		ValidationPage.AssertingInvalidBankNameSearchForRoutingNum();
	}

	@Test(priority = 38, testName = "Entering_Point009amount_AndAssertingItsRoundingValue")
	public static void tc038Entering_Point009amount_AndAssertingItsRoundingValue_VD_FedNow_Transfers()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-038");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		ValidationPage.assertingRoundFigureValues("0.01");
	}

	@Test(priority = 39, testName = "Entering_34.562 amount_AndAssertingItsRoundingValue")
	public static void tc039Entering_34Point562amount_AndAssertingItsRoundingValue_VD_FedNow_Transfers()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-039");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.56");
	}

	@Test(priority = 40, testName = "Entering_34.567amount_AndAssertingItsRoundingValue")
	public static void tc040Entering_34Point567amount_AndAssertingItsRoundingValue_VD_FedNow_Transfers()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-040");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.57");
	}

	@Test(priority = 41, testName = "Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue")
	public static void tc041Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue_VD_FedNow_Transfers()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-041");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
// Validating the entered amount		
		ValidationPage.assertingRoundFigureValues("12345678912345678.00");
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd6());
	}

	@Test(priority = 42, testName = "FedNow_Remittance_Invalid_#Document")
	public static void tc042EnterInvalidDataIn_FedNow_Remittance_Document_VD_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FN-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FN-042");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpinvalidRemitDoc());
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
		if (testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD);
			startSuite();    }
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}

}
