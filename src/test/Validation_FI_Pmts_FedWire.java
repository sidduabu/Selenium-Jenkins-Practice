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
import pages.ValidationPage;
import pages.Login;

public class Validation_FI_Pmts_FedWire extends Utility {
	

	public static  ExtentSparkReporter extentSparkReporter;
	 public static  ExtentReports extentReports;
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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
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
		while(extentReports.getReport().findTest(testMethod.getName()).isPresent()) {
            extentReports.removeTest(testMethod.getName());
            if(! extentReports.getReport().getTestList().isEmpty())
            extentReports.flush();  }
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");
	}
	
	
	@Test(priority = 1, testName = "EmptyDDA_AccNum")
	public static void tc001EmptyDDA_AccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 2, testName = "EmptyLedgerAccNum")
	public static void tc002EmptyLedgerAccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 3, testName = "EmptyLedgerAccName")
	public static void tc003EmptyLedgerAccName_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptySenderAccName());
	}

	@Test(priority = 4, testName = "InvalidDDA_AccNum")
	public static void tc004InvalidDDA_AccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 5, testName = "InvalidLedgerAccNum")
	public static void tc005InvalidLedgerAccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 6, testName = "EmptyRecipTyp")
	public static void tc006EmptyRecipTyp_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FW-006");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());
	}

	@Test(priority = 7, testName = "EmptyRecipName")
	public static void tc007EmptyRecipName_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-007");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam());
	}

	@Test(priority = 8, testName = "EmptyRecipAddressLine")
	public static void tc008EmptyRecipAddrsLine_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-008");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.emptyAddressLine();	}

	@Test(priority = 9, testName = "EmptyRecipTown")
	public static void tc009EmptyRecipTown_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipTown());
	}

	@Test(priority = 10, testName = "EmptyRecipState")
	public static void tc010EmptyRecipState_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipState());
	}

	@Test(priority = 11, testName = "EmptyRecipCountry")
	public static void tc011EmptyRecipCountry_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipCntry());
	}

	@Test(priority = 12, testName = "EmptyRecipZipCode")
	public static void tc012EmptyRecipZipCode_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipZipCod());
	}

	@Test(priority = 13, testName = "EmptyRecipRoutNum")
	public static void tc013EmptyRecipRoutNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-013");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipRoutNum());
	}

	@Test(priority = 14, testName = "EmptyRecipAccNum")
	public static void tc014EmptyRecipAccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 15, testName = "EmptyRecipAmt")
	public static void tc015EmptyRecipAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAmt());
	}

	@Test(priority = 16, testName = "InvdRecipNam")
	public static void tc016InvdRecipNam_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipNam());
	}

	@Test(priority = 17, testName = "InvdRecipZipCode")
	public static void tc017InvdRecipZipCode_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		As we are entering the amount so the API call will happens so we are getting another error message
//		next month @Anil going to fix this so up to next month we will run like this 		
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 18, testName = "InvdRecipEmail")
	public static void tc018InvdRecipEmail_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-018");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//	As we are entering the amount so the API call will happens so we are getting another error message
//	next month @Anil going to fix this so up to next month we will run like this 	
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipEmail());
	}

	@Test(priority = 19, testName = "InvdRecipPhnNum")
	public static void tc019InvdRecipPhnNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipPhn());
	}

	@Test(priority = 20, testName = "InvdRecipRoutingNum")
	public static void tc020InvdRecipRoutingNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-020");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipRoutNum());
	}

	@Test(priority = 21, testName = "InvdRecipAccNum")
	public static void tc021InvdRecipAccNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-021");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 22, testName = "InvdRecipAmt")
	public static void tc022InvdRecipAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-022");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdAmt());
	}

	@Test(priority = 23, testName = "AmtMaxExcd")
	public static void tc023AmtMaxExcd_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-023");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd5());
	}

	
	
//===================FedWire Structured Remittance (Both Orginiator & Beneficiary)=====================================================================================================================================================================================================================
// Here both Orginiator & Beneficiary are covered in same test case. ie:- from test_case-29 to test_case-42) 	
	
	@Test (priority = 24, testName="FW_EmtyRefToRecip")
	public static void tc024FW_EmtyRefToRecip_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fWEmtyRefToRecip();
	}
	
	@Test (priority = 25, testName="Empty_FWRemitt_InfoForRecip_VD_FedWire_Pmts")
	public static void tc025Empty_FWRemitt_InfoForRecip_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-025");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.emptyFWRemitInfoForRecipFI();
	}
	
	@Test (priority = 26, testName="Fw_EmptyInfoForRecipFI_Pmts_FedWire_DropDown")
	public static void tc026FW_EmptyInfoForRecipFIDropDown_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyInfoRcpFI()); 
	}
	
	@Test (priority = 27, testName="FW_InvdRefToRecip")
	public static void tc027FW_InvdRefToRecip_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-027");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwInvdRefToRecip()); 
	}
	
	@Test (priority = 28, testName="FW_InvdSendrToRecipInfo")
	public static void tc028FW_InvdSendrToRecipInfo_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-028");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fwInvdSendrToRecipInfo();
	}
	
	@Test (priority = 29, testName="FW_OrgEmtyIdTyp")
	public static void tc029FW_OrgEmtyIdTyp_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-029");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgEmtyIdTyp());
	}
	
	@Test (priority = 30, testName="FW_OrgEmtyIdCode")
	public static void tc030FW_OrgEmtyIdCode_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-030");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyIdCod());
	}
	
	@Test (priority = 31, testName="FW_OrgEmtyNam")
	public static void tc031FW_OrgEmtyNam_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-031");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyNam()); 
	}
	
	@Test (priority = 32, testName="FW_OrgEmtyIdNum")
	public static void tc032FW_OrgEmtyIdNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-032");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyIdNum()); 
	}
	
// >>>In 62.2 Release...DevTeam removed AddressType_field..!! 	
//	@Test (priority = 33, testName="FW_OrgEmtyAddrsTyp")
//	public static void tc033FW_OrgEmtyAddrsTyp_VD_FedWire_Pmts() throws Exception {
//		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
//		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-033");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
//		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyAddrsTyp()); 
//	}
	
	@Test (priority = 34, testName="FW_OrgEmtyAddrsLine")
	public static void tc034FW_OrgEmtyAddrsLine_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-034");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgEmtyAddrsLin1()); 
	}
	
	@Test (priority = 35, testName="FW_OrgEmtyState")
	public static void tc035FW_OrgEmtyState_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-035");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyState()); 
	}
	@Test (priority = 36, testName="FW_OrgEmtyPostalCode")
	public static void tc036FW_OrgEmtyPostalCode_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-036");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyPostl()); 
	}
	
	@Test (priority = 37, testName="FW_OrgEmtyCountry")
	public static void tc037FW_OrgEmtyCountry_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-037");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyCtry()); 
	}
	
	@Test (priority = 38, testName="FW_OrgInvdCity")
	public static void tc038FW_OrgInvdCity_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-038");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdCity()); 
	}
	@Test (priority = 39, testName="FW_OrgInvdState")
	public static void tc039FW_OrgInvdState_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-039");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdState()); 
	}
	@Test (priority = 40, testName="FW_OrgInvdPostal")
	public static void tc040FW_OrgInvdPostal_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-040");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdPostl()); 
	}
	@Test (priority = 41, testName="FW_OrgInvdPhone")
	public static void tc041FW_OrgInvdPhone_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-041");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgInvdPhn()); 
	}
	@Test (priority = 42, testName="FW_OrgInvdEmail")
	public static void tc042FW_OrgInvdEmail_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-042");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgInvdEml()); 
	}
	
	
	
//===================FedWire Structured Remittance (Primary Remittance Document)=====================================================================================================================================================================================================================
	
	@Test (priority = 43, testName="FW_DocEmtyDocTyp")
	public static void tc043FW_DocEmtyDocTyp_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-043");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocEmtyDocTyp()); 
	}
	
	@Test (priority = 44, testName="FW_DocEmtyDocIdNum")
	public static void tc044FW_DocEmtyDocIdNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-044");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocEmtyDocIdNum()); 
	}
	
	@Test (priority = 45, testName="FW_DocEmtyDocDat")
	public static void tc045FW_DocEmtyDocDat_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-045");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fwDocEmtyDocDat();
	}
	@Test (priority = 46 , testName="FW_DocInvdActlAmt")
	public static void tc046FW_DocInvdActlAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-046");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdActlAmt()); 
	}
	@Test (priority = 47 , testName="FW_DocInvdOrgnlAmt")
	public static void tc047FW_DocInvdOrgnlAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-047");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdOrgnlAmt()); 
	}
	@Test (priority =48 , testName="FW_DocInvdDiscAmt")
	public static void tc048FW_DocInvdDiscAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-048");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdDiscAmt()); 
	}
	@Test (priority =49 , testName="FW_DocInvdAdjstAmt")
	public static void tc049FW_DocInvdAdjstAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-049");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdAdjstAmt()); 
	}
	@Test (priority = 50 , testName="FW_DocExcdActlAmt")
	public static void tc050FW_DocExcdActlAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-050");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdActlAmt()); 
	}
	@Test (priority = 51 , testName="FW_DocExcdOrgnlAmt")
	public static void tc051FW_DocExcdOrgnlAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-051");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdOrgnlAmt()); 
	}
	@Test (priority = 52 , testName="FW_DocExcdDiscAmt")
	public static void tc052FW_DocExcdDiscAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-052");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdDiscAmt()); 
	}
	@Test (priority =53 , testName="FW_DocExcdAdjstAmt")
	public static void tc053FW_DocExcdAdjstAmt_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-053");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdAdjstAmt()); 
	}
	
	@Test (priority =54 , testName="Empty_FW_Remit_AdjResnCode_VD_FedWire_Pmts")
	public static void tc054Empty_FW_Remit_AdjResnCode_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-054");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmptyDocAdjResnCode()); 
	}
	@Test (priority =55 , testName="FW_DocAdjInditr")
	public static void tc055FW_DocAdjInditr_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-055");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmptyDocAdjIndictr()); 
	}
	
//===================FedWire External Remittance=====================================================================================================================================================================================================================
	
	@Test (priority = 56 , testName="FW_EmtyRemitId")
	public static void tc056FW_EmtyRemitId_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-056");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitId()); 
	}
	@Test (priority = 57 , testName="FW_EmtyRemitLocMthd")
	public static void tc057FW_EmtyRemitLocMthd_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-057");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitLocMthd());
	}
	@Test (priority = 58 , testName="FW_EmtyRemitLoc")
	public static void tc058FW_EmtyRemitLoc_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-058");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitLoc());
	}
	
	@Test (priority =59 , testName="FW_InvalidRemitId")
	public static void tc059FW_InvalidRemitId_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-059");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwInvdRemitId()); 
	}
	
	
	
//================ Missing/Extra Cases (Date:- 31-January-2025) =================================================================
	
	
	@Test(priority = 60, testName = "InvdRecipZipCode00000")
	public static void tc060InvdRecipZipCode00000_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-060");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}
	
	@Test(priority = 61, testName = "InvalidBankNameSearchForRoutingNum")
	public static void tc061InvalidBankNameSearchForRoutingNum_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-061");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.AssertingInvalidBankNameSearchForRoutingNum();
	}
	
	@Test(priority = 62, testName = "Entering_Point009amount_AndAssertingItsRoundingValue")
	public static void tc062Entering_Point009amount_AndAssertingItsRoundingValue_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-062");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("0.01");
	}
	
	@Test(priority = 63, testName = "Entering_34.562 amount_AndAssertingItsRoundingValue")
	public static void tc063Entering_34Point562amount_AndAssertingItsRoundingValue_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-063");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.56");
	}
	
	@Test(priority = 64, testName = "Entering_34.567amount_AndAssertingItsRoundingValue")
	public static void tc064Entering_34Point567amount_AndAssertingItsRoundingValue_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-064");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.57");
	}
	
	@Test(priority = 65, testName = "Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue")
	public static void tc065Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-065");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
// Validating the entered amount		
		ValidationPage.assertingRoundFigureValues("12345678912345678.00");
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd5());
	}
	
	@Test(priority = 66, testName = "Entering invalid data in UniqueReference")
	public static void tc066EnteringInvalidDataInUniqueRef_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-066");
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.enterinvalidUniqueReference_And_Assertion();
	}
	
	@Test(priority = 67, testName = "RecipientTypeDropDownCheck")
	public static void tc067RecipientTypeDropDownCheck_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI-FW-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-067");
		AddRecipientDetails.RecipientType_Validation_FI();
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
	}
	
	@Test(testName = "EnteringSenderToRecipInfo_AndSelectingStructuredRemit_VD_FedWire_Pmts", priority = 68)
	public void tc68EnteringSenderToRecipInfo_AndSelectingStructuredRemit_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-068");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.enterSenderToRecipInfo_And_ValidateErrorMsg(objErrDtls.getFwSenderToRecipInfo());
	}
	
	
	@Test(testName = "EnteringSenderToRecipInfo_AndSelectingExternalRemit_VD_FedWire_Pmts", priority = 69)
	public void tc69EnteringSenderToRecipInfo_AndSelectingExternalRemit_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FI-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-069");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.enterSenderToRecipInfo_And_ValidateErrorMsg(objErrDtls.getFwSenderToRecipInfo());
	}
	
//=============================================================================================================================================================================================	
	
		@Test(testName = "VD_GL_Acc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation", priority = 70)
		public void tc070VD_GL_Acc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-GL");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-069");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		Here We are validating Sender_To_Recipient_Info_DropDown Options (Apart from Select Option) 	
			ValidationPage.info_For_RecipFI_DropDownOptions_Validation("ThreeOptions");
			
		}
		
		@Test(testName = "VD_DDA_Acc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation", priority = 71)
		public void tc071VD_DDA_Acc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-DDA");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-069");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		Here We are validating Sender_To_Recipient_Info_DropDown Options (Apart from Select Option) 	
			ValidationPage.info_For_RecipFI_DropDownOptions_Validation("ThreeOptions");
			
		}
		
		@Test(testName = "VD_LedgerAcc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation", priority = 72)
		public void tc072VD_LedgerAcc_To_IndiviRecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-Ledger");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FW-069");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		Here We are validating Sender_To_Recipient_Info_DropDown Options (Apart from Select Option) 	
			ValidationPage.info_For_RecipFI_DropDownOptions_Validation("ThreeOptions");
			
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
	        startSuite();              }	
		}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}

}
