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

public class BoundaryValueAnalysis_FI_RTP extends Utility {

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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
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
		while (extentReports.getReport().findTest(testMethod.getName()).isPresent()) {
			extentReports.removeTest(testMethod.getName());
			if (!extentReports.getReport().getTestList().isEmpty())
				extentReports.flush();
		}
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");

//		 Redirect System.out to capture logs
		logStream = new ByteArrayOutputStream();
		originalOut = System.out;
		System.setOut(new PrintStream(logStream, true, StandardCharsets.UTF_8));

	}

// =======================>> Sender Account Number- DDA <<==================================================================================================	

	@Test(testName = "DDA_AccNum--> less than 4 digits", priority = 01)
	public static void tc001DDA_AccNum_LessThan_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "DDA_AccNum--> With_Exact_FourDigits", priority = 2)
	public void tc002DDA_AccNum_With_Exact_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "DDA_AccNum--> Exact_ThirtyFourDigits", priority = 3)
	public void tc003DDA_AccNum_With_Exact_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "DDA_AccNum--> GreaterThan_ThirtyFourDigits", priority = 4)
	public static void tc004DDA_AccNum_GreaterThan_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

// ==========================>> Sender Account Number- Ledger <<============================================================================================	

	@Test(testName = "Ledger_AccNum--> less than 4 digits", priority = 05)
	public static void tc005Ledger_AccNum_LessThan_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "Ledger_AccNum--> With_Exact_FourDigits", priority = 6)
	public void tc006Ledger_AccNum_With_Exact_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "Ledger_AccNum--> Exact_ThirtyFourDigits", priority = 7)
	public void tc007Ledger_AccNum_With_Exact_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "ledger_AccNum--> GreaterThan_ThirtyFourDigits", priority = 8)
	public static void tc008Ledger_AccNum_GreaterThan_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

// =============================>> Recipient Account Number <<=========================================================================	

	@Test(testName = "RecipAccNum--> Less than 4-digits", priority = 9)
	public static void tc009RecipAccNum_LessThan_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "RecipAccNum--> Exact 4-digits", priority = 10)
	public static void tc010RecipAccNum_Exact_4Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "RecipAccNum--> Exact 34-digits", priority = 11)
	public static void tc011RecipAccNum_Exact_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "RecipAccNum--> Greater than 34-digits", priority = 12)
	public static void tc012RecipAccNum_GreaterThan_34Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

// =============================>> Recipient Account Name <<=========================================================================	

	@Test(testName = "RecipientName--> Less than One-Character", priority = 13)
	public static void tc013RecipientName_LessThanOneCharacter_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-013");
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

	@Test(testName = "RecipientName--> Exact One-Character", priority = 14)
	public static void tc014RecipientName_ExactOneCharacter_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

	@Test(testName = "RecipientName--> Exact 60-Characters", priority = 15)
	public static void tc015RecipientName_Exact60Characters_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

	@Test(testName = "RecipientName--> Greater than 60-Characters", priority = 16)
	public static void tc016RecipientName_GreaterThan60Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

//=============================Routing Number =================================================

	@Test(testName = "RoutingNumber_LessThan 9-Digits", priority = 17)
	public static void tc017RoutingNumber_LessThan_9Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinRoutNumLengthErorrMsg());

	}

	@Test(testName = "RoutingNumber_Exact 9-Digits", priority = 18)
	public static void tc018RoutingNumber_Exact_9Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());
	}

	@Test(testName = "RoutingNumber_Greater 9-Digits", priority = 19)
	public static void tc019RoutingNumber_GreaterThan_9Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());
	}

////============================= Amount Field =================================================
//
//	@Test(testName = "Amount Less Than 0.01 ie :- 0.00 ", priority = 20)
//	public static void tc020Amount_LessThan_Point01_BVA_FI_MGD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-020");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg1(objErrDtls.getInvdAmt());
//	}
//
//	@Test(testName = "Amount Exact 0.01", priority = 21)
//	public static void tc021Amount_ExactPoint01_BVA_FI_MGD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-021");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertionBVA_Amount(objReciDtls.getPmtAmt());
//	}
//
//	@Test(testName = "Amount Exact 5000 ", priority = 22)
//	public static void tc022Amount_ExactLimit_5000_BVA_FI_MGD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-022");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertionBVA_Amount(objReciDtls.getPmtAmt());
//	}
//
//	@Test(testName = "Amount Greater Than 5000 --> ie 5000.01", priority = 23)
//	public static void tc023Amount_GreaterThanLimit_5000_BVA_FI_MGD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-023");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRelatedDocumentDetails(objReciDtls.getDocType(), objReciDtls.getDocPath());
////		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg1(objErrDtls.getMaxAmountError_RTP());
//	}

//============================= Recipient Phone Number =================================================

	@Test(testName = "PhoneNumber Less than 10Digits", priority = 24)
	public static void tc024PhoneNumber_LessThan_10Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinPhoneNumLengthErrorMsg());

	}

	@Test(testName = "PhoneNumber_Exact 10-Digits", priority = 25)
	public static void tc025PhoneNumber_Exact_10Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_PhoneNumber(objReciDtls.getRecipientPhone());
	}

	@Test(testName = "PhoneNumber_Greater Than 10-Digits", priority = 26)
	public static void tc026PhoneNumber_GreaterThan_10Digits_BVA_FI_MGD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-RTP-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-RTP-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
	     startSuite();  }

	}

	@AfterClass
	public void endSuite() throws Exception {

		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}

}
