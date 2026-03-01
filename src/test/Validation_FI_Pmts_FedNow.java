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
import pages.Login;
import pages.ValidationPage;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;

public class Validation_FI_Pmts_FedNow extends Utility {

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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
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
		while(extentReports.getReport().findTest(testMethod.getName()).isPresent())
        {
            extentReports.removeTest(testMethod.getName());
            if(! extentReports.getReport().getTestList().isEmpty())
            extentReports.flush();
        }
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");
	}
	

	@Test(priority = 1, testName = "EmptyDDA_AccNum")
	public static void tc001EmptyDDA_AccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-001");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 2, testName = "EmptyLedgerAccNum")
	public static void tc002EmptyLedgerAccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 3, testName = "EmptyLedgerAccName")
	public static void tc003EmptyLedgerAccName_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-003");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptySenderAccName());
	}

	@Test(priority = 4, testName = "InvalidDDA_AccNum")
	public static void tc004InvalidDDA_AccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-004");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 5, testName = "InvalidLedgerAccNum")
	public static void tc005InvalidLedgerAccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-005");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 6, testName = "EmptyRecipTyp")
	public static void tc006EmptyRecipTyp_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-006");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());
	}

	@Test(priority = 7, testName = "EmptyRecipName")
	public static void tc007EmptyRecipName_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-007");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam());
	}

	@Test(priority = 8, testName = "EmptyRecipAddressLine")
	public static void tc008EmptyRecipAddrsLine_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-008");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.emptyAddressLine();	}

	@Test(priority = 9, testName = "EmptyRecipTown")
	public static void tc009EmptyRecipTown_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipTown());
	}

	@Test(priority = 10, testName = "EmptyRecipState")
	public static void tc010EmptyRecipState_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipState());
	}

	@Test(priority = 11, testName = "EmptyRecipCountry")
	public static void tc011EmptyRecipCountry_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipCntry());
	}

	@Test(priority = 12, testName = "EmptyRecipZipCode")
	public static void tc012EmptyRecipZipCode_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipZipCod());
	}

	@Test(priority = 13, testName = "EmptyRecipRoutNum")
	public static void tc013EmptyRecipRoutNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-013");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipRoutNum());
	}

	@Test(priority = 14, testName = "EmptyRecipAccNum")
	public static void tc014EmptyRecipAccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 15, testName = "EmptyRecipAmt")
	public static void tc015EmptyRecipAmt_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAmt());
	}

	@Test(priority = 16, testName = "EmptyEndToEnd")
	public static void tc016EmptyEndToEnd_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyEndToEnd());
	}

	@Test(priority = 17, testName = "InvdRecipNam")
	public static void tc017InvdRecipNam_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipNam());
	}

	@Test(priority = 18, testName = "InvdRecipZipCode")
	public static void tc018InvdRecipZipCode_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-018");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 19, testName = "InvdRecipEmail")
	public static void tc019InvdRecipEmail_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipEmail());
	}

	@Test(priority = 20, testName = "InvdRecipPhnNum")
	public static void tc020InvdRecipPhnNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-020");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipPhn());
	}

	@Test(priority = 21, testName = "InvdRecipRoutingNum")
	public static void tc021InvdRecipRoutingNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-021");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipRoutNum());
	}

	@Test(priority = 22, testName = "InvdRecipAccNum")
	public static void tc022InvdRecipAccNum_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-022");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 23, testName = "InvdRecipAmt")
	public static void tc023InvdRecipAmt_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-023");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdAmt());
	}

	@Test(priority = 24, testName="InvdRecipEndToEnd")
	public static void tc024InvdRecipEndToEnd_VD_FedNow_Pmts() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdEndToEnd());
		
	}
	@Test(priority = 25, testName = "AmtMaxExcd")
	public static void tc025AmtMaxExcd_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-025");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd4());
	}

	@Test(priority = 26, testName = "FedNowEmtyDocTyp")
	public static void tc026FedNowEmtyDocTyp_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyDocTyp());
	}

	@Test(priority = 27, testName = "FedNowEmtyDoc")
	public static void tc027FedNowEmtyDoc_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-027");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyDoc());
	}

	@Test(priority = 28, testName = "FedNowEmtyDate")
	public static void tc028FedNowEmtyDate_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-028");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.rtpEmtyDate();
	}

//	
//	@Test(priority = 29, testName = "FedNowEmtyInvoiceAmt") 
//	public static void tc029FedNowEmtyInvoiceAmt_VD_FedNow_Pmts() throws Exception {
//		commonMethod("VD-Pmts-MGD-FN-029");
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.rtpInvcDiscAmt("invoice", "", objErrDtls.getFedNowEmtyInvcAmt());
//	}

//	@Test(priority = 30, testName = "FedNowEmtyDiscountAmt")
//	public static void tc030FedNowEmtyDiscountAmt_VD_FedNow_Pmts() throws Exception {
//		commonMethod("VD-Pmts-MGD-FN-030");
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.rtpInvcDiscAmt("discount", "", objErrDtls.getRtpEmtyDiscAmt());
//	}

	@Test(priority = 31, testName = "FedNowEmtyLocationMethod")
	public static void tc031FedNowEmtyLocationMethod_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-031");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpEmtyRemitLocMetd());
	}

	@Test(priority = 32, testName = "FedNowInvlaidInvoiceAmount")
	public static void tc032FedNowInvalidInvoiceAmt_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-032");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("invoice", "!@#$%", objErrDtls.getRtpInvdInvcAmt());
	}

	@Test(priority = 33, testName = "FedNowInvlaidDiscAmount")
	public static void tc033FedNowInvlaidDiscAmt_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-033");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("discount", "!@#$%", objErrDtls.getRtpInvdDiscAmt());
	}

	@Test(priority = 34, testName = "FedNowInvlaidRemittId")
	public static void tc034FedNowInvlaidRemittId_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-034");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getRtpInvdRemitId());
	}

	
//=======================>> Upload Document <<======================================================================================================================================================	
	
	@Test(priority = 35, testName = "EmptyDocType(Dropdown not selected)-->UploadDocument")
	public static void tc035FedNowEmptyDocType_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-035");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getEmptyDocTyp());
	}

	@Test(priority = 36, testName = "FedNowInvdFileType")
	public static void tc036FedNowInvdFileType_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-036");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getInvdUploadDoc());
	}

	@Test(priority = 37, testName = "FedNowMaxFileSize")
	public static void tc037FedNowMaxFileSize_VD_FedNow_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-037");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
	}
	
	
//================ Missing/Extra Cases (Date:- 31-January-2025) =================================================================
	
		@Test(priority = 38, testName = "InvdRecipZipCode00000")
		public static void tc038InvdRecipZipCode00000_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-038");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
			ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
			ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
		}
		
		@Test(priority = 39, testName = "InvalidBankNameSearchForRoutingNum")
		public static void tc039InvalidBankNameSearchForRoutingNuu_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-039");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
			ValidationPage.AssertingInvalidBankNameSearchForRoutingNum();
		}
		
		@Test(priority = 40, testName = "Entering_Point009amount_AndAssertingItsRoundingValue")
		public static void tc040Entering_Point009amount_AndAssertingItsRoundingValue_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-040");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			ValidationPage.assertingRoundFigureValues("0.01");
		}
		
		@Test(priority = 41, testName = "Entering_34.562 amount_AndAssertingItsRoundingValue")
		public static void tc041Entering_34Point562amount_AndAssertingItsRoundingValue_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-041");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			ValidationPage.assertingRoundFigureValues("34.56");
		}
		
		@Test(priority = 42, testName = "Entering_34.567amount_AndAssertingItsRoundingValue")
		public static void tc042Entering_34Point567amount_AndAssertingItsRoundingValue_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-042");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			ValidationPage.assertingRoundFigureValues("34.57");
		}
		
		@Test(priority = 43, testName = "Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue")
		public static void tc043Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-043");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount002(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
// Validating the entered amount					
			ValidationPage.assertingRoundFigureValues("12345678912345678.00");
			ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
			ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd4());
		}
		
		@Test(priority = 44, testName = "FedNow_Remittance_Invalid_#Document")
		public static void tc044EnterInvalidDataIn_FedNow_Remittance_Document_VD_FedNow_Pmts() throws Exception {
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-FN-000");
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-FN-044");
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD);
	         startSuite();     }	
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
}