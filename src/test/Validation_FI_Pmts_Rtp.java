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

public class Validation_FI_Pmts_Rtp extends Utility {

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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
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
	public static void tc001EmptyDDA_AccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-001");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 2, testName = "EmptyLedgerAccNum")
	public static void tc002EmptyLedgerAccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 3, testName = "EmptyLedgerAccName")
	public static void tc003EmptyLedgerAccName_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-003");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptySenderAccName());
	}

	@Test(priority = 4, testName = "InvalidDDA_AccNum")
	public static void tc004InvalidDDA_AccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-004");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 5, testName = "InvalidLedgerAccNum")
	public static void tc005InvalidLedgerAccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-005");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 6, testName = "EmptyRecipTyp")
	public static void tc006EmptyRecipTyp_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-006");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());
	}

	@Test(priority = 7, testName = "EmptyRecipName")
	public static void tc007EmptyRecipName_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-007");
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
	public static void tc008EmptyRecipAddrsLine_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-008");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.emptyAddressLine();
	}

	@Test(priority = 9, testName = "EmptyRecipTown")
	public static void tc009EmptyRecipTown_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-009");
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
	public static void tc010EmptyRecipState_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-010");
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
	public static void tc011EmptyRecipCountry_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-011");
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
	public static void tc12EmptyRecipZipCode_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-012");
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
	public static void tc013EmptyRecipRoutNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-013");
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
	public static void tc014EmptyRecipAccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-014");
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
	public static void tc015EmptyRecipAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-015");
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
	public static void tc016EmptyEndToEnd_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-016");
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
	public static void tc017InvdRecipNam_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-017");
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
	public static void tc018InvdRecipZipCode_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-018");
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
	public static void tc019InvdRecipEmail_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-019");
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
	public static void tc020InvdRecipPhnNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-020");
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
	public static void tc021InvdRecipRoutingNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-021");
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
	public static void tc022InvdRecipAccNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-022");
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
	public static void tc023InvdRecipAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-023");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdAmt());
	}

	@Test(priority = 24, testName="InvdRecipEndToEnd")
	public static void tc024InvdRecipEndToEnd_VD_RTP_Pmts() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-024");
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
	public static void tc025AmtMaxExcd_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-025");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd1());
	}

//=====================>> Remittance <<=======================================================================================================================	

	@Test(priority = 26, testName = "RtpEmtyDocTyp")
	public static void tc026RtpEmtyDocTyp_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-026");
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

	@Test(priority = 27, testName = "RtpEmtyDoc")
	public static void tc027RtpEmtyDoc_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-027");
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

	@Test(priority = 28, testName = "RtpEmtyDate")
	public static void tc028RtpEmtyDate_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-028");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.rtpEmtyDate();
	}

	@Test(priority = 29, testName = "RtpEmtyInvoiceAmt")
	public static void tc029RtpEmtyInvoiceAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-029");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("invoice", "", objErrDtls.getRtpEmtyInvcAmt());
	}

	@Test(priority = 30, testName = "RtpEmtyDiscountAmt")
	public static void tc030RtpEmtyDiscountAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-030");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.rtpInvcDiscAmt("discount", "", objErrDtls.getRtpEmtyDiscAmt());
	}

	@Test(priority = 31, testName = "RtpEmtyLocationMethod")
	public static void tc031RtpEmtyLocationMethod_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-031");
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

	@Test(priority = 32, testName = "RtpInvlaidInvoiceAmount")
	public static void tc032RtpInvalidInvoiceAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-032");
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

	@Test(priority = 33, testName = "RtpInvlaidDiscAmount")
	public static void tc033RtpInvlaidDiscAmt_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-033");
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

	@Test(priority = 34, testName = "RtpInvlaidRemittId")
	public static void tc034RtpInvlaidRemittId_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-034");
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
	public static void tc035RtpEmptyDocType_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-035");
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

	@Test(priority = 36, testName = "RtpInvdFileType")
	public static void tc036RtpInvdFileType_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-036");
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

	@Test(priority = 37, testName = "RtpMaxFileSize")
	public static void tc037RtpMaxFileSize_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-037");
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

	
//================ Missing/Extra Cases (Added on 31-January-2025) =================================================================
	
	@Test(priority = 38, testName = "InvdRecipZipCode00000")
	public static void tc038InvdRecipZipCode00000_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-038");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}
	
	@Test(priority = 39, testName = "InvalidBankNameSearchForRoutingNum")
	public static void tc039InvalidBankNameSearchForRoutingNum_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-039");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ValidationPage.AssertingInvalidBankNameSearchForRoutingNum();
	}
	
	@Test(priority = 40, testName = "Entering_Point009amount_AndAssertingItsRoundingValue")
	public static void tc040Entering_Point009amount_AndAssertingItsRoundingValue_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-040");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ValidationPage.assertingRoundFigureValues("0.01");
	}
	
	@Test(priority = 41, testName = "Entering_34.562 amount_AndAssertingItsRoundingValue")
	public static void tc041Entering_34Point562amount_AndAssertingItsRoundingValue_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-041");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ValidationPage.assertingRoundFigureValues("34.56");
	}
	
	@Test(priority = 42, testName = "Entering_34.567amount_AndAssertingItsRoundingValue")
	public static void tc042Entering_34Point567amount_AndAssertingItsRoundingValue_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-042");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		ValidationPage.assertingRoundFigureValues("34.57");
	}
	
	@Test(priority = 43, testName = "Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue")
	public static void tc043Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue_VD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-043");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
// Validating the entered amount				
		ValidationPage.assertingRoundFigureValues("12345678912345678.00");
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd1());
	}
	
	@Test(priority = 44, testName = "RTP_Remittance_Invalid_#Document")
	public static void tc044EnterInvalidDataIn_Rtp_Remittance_DocumentVD_RTP_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-MGD-RTP-044");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
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