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

public class Validation_DepOp_AddRecipient_RTP extends Utility {

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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
	}

	@BeforeMethod
	public void startTest(Method testMethod) throws Exception {
		String filepath=Paths.get(System.getProperty("user.dir")) + "/TestReport/Report_" + System.getProperty("user.name")
		+ Paths.get(System.getProperty("user.dir")).getFileName() + dateFormat.format(date) + ".html";
		extentSparkReporter  = new ExtentSparkReporter(filepath);
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



	@Test(priority = 1, testName = "EmptyDivision")
	public static void tc01_EmptyDivision_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-001");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyDivision());
	}

	@Test(priority = 2, testName = "Empty TransferType")
	public static void tc02EmptyTransferType_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-002");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyTransferType());
	}

	@Test(priority = 3, testName = "Empty Payment Method")
	public static void tc03EmptyPaymentMethod_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-003");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-000");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyTransferMethod());
	}

	@Test(priority = 4, testName = "Empty Recipient Type")
	public static void tc04EmptyRecipientType_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-004");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());
	}

	@Test(priority = 5, testName = "InvalidNickname")
	public static void tc05InvalidNickname_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-005");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidNickname());
	}

	@Test(priority = 6, testName = "EmptyRecipientName")
	public static void tc06EmptyRecipientName_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-006");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipientName());
	}

	@Test(priority = 7, testName = "InvalidRecipientName")
	public static void tc07InvalidRecipientName_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-007");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidRecipientName());
	}

	@Test(priority = 8, testName = "EmptyAddressLine")
	public static void tc08EmptyAddressLine_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-008");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ValidationPage.emptyAddressLine();
		}

	@Test(priority = 9, testName = "InvalidAddressLine")
	public static void tc09InvalidAddressLine_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-009");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg4(objErrDtls.getInvalidAddressLine());
	}

	@Test(priority = 10, testName = "EmptyTownName")
	public static void tc010EmptyTownName_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-010");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg4(objErrDtls.getEmptyRecipTown());
	}

	@Test(priority = 11, testName = "InvalidTownName")
	public static void tc011InvalidTownName_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-011");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getInvdRecipTown());
	}

	@Test(priority = 12, testName = "EmptyState")
	public static void tc012EmptyState_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-012");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getEmptyRecipState());
	}

	@Test(priority = 13, testName = "EmptyCountry")
	public static void tc013EmptyCountry_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-013");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getEmptyRecipCntry());
	}

	@Test(priority = 14, testName = "EmptyZip")
	public static void tc014EmptyZip_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-014");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getEmptyRecipZipCod());
	}

	@Test(priority = 15, testName = "InvalidZip")
	public static void tc015InvalidZip_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-015");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingSingleErrorFieldMsg_WithOutContinueClick(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 16, testName = "InvalidEmail")
	public static void tc016InvalidEmail_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-016");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidEmail());
	}

	@Test(priority = 17, testName = "InvalidPhone")
	public static void tc017InvalidPhone_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-017");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidPhone());
	}

	@Test(priority = 18, testName = "EmptyRoutingNum")
	public static void tc018EmptyRoutingNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-018");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipRoutNum());
	}

	@Test(priority = 19, testName = "InvalidRoutingNum")
	public static void tc019InvalidRoutingNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-019");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipRoutNum());
	}
	

	@Test(priority = 20, testName = "EmptyAccountNum")
	public static void tc020EmptyAccountNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-020");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 21, testName = "InvalidAccountNum")
	public static void tc021InvalidAccountNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-000");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-RC-RTD-RTP-021");
		AddRecipientDetails.recipDetails2(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 22, testName = "EmptySenderAccountNumber")
	public static void tc022_EmptySenderAccNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-022");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 23, testName = "InvalidSenderAccountNumber")
	public static void tc023_InvalidSenderAccNum_VD_AddRecip_CustSupport_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-RC-RTD-RTP-023");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls); 
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
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
