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

public class BoundaryValueAnalysis_FI_FedWire extends Utility {

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
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-008");
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
	
// Info	
//	testCase 01 to 04  --> Sender_AccountNumber_DDA    (Minimum & Maximum Length)
//  testCase 05 to 08  --> Sender_AccountNumber_Ledger (Minimum & Maximum Length)
//  testCase 09 to 12  --> Sender_Name                 (Minimum & Maximum Length)
//  testCase 13 to 16  --> Recipient AccountNumber     (Minimum & Maximum Length)
//  testCase 17 to 20  --> Recipient Name              (Minimum & Maximum Length)
//  testCase 21 to 23  --> Recipient Routing Number    (Minimum & Maximum Length)
//  testCase 24 to 26  --> Recipient Phone Number      (Minimum & Maximum Length)
//  testCase 27 to	   --> Recipient Amount            (Minimum & Maximum Length)
//  testCase 27 to	   --> Recipient Amount            (Minimum & Maximum Value)


	
	
	
	
//======================================>> ** FI-Pmts-MGD- FedWire Network **<<==============================================================================================================================

// =======================>> Sender Account Number- DDA <<==================================================================================================	

	@Test(testName = "DDA_AccNum--> less than 4_Digits", priority = 01)
	public static void tc001DDA_AccNum_LessThan_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "DDA_AccNum--> With_Exact_04_Digits", priority = 2)
	public void tc002DDA_AccNum_With_Exact_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "DDA_AccNum--> Exact_ThirtyFourDigits", priority = 3)
	public void tc003DDA_AccNum_With_Exact_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "DDA_AccNum--> GreaterThan_34_Digits", priority = 4)
	public static void tc004DDA_AccNum_GreaterThan_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

// ==========================>> Sender Account Number- Ledger <<============================================================================================	

	@Test(testName = "Ledger_AccNum--> less than 4 digits", priority = 05)
	public static void tc005Ledger_AccNum_LessThan_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "Ledger_AccNum--> With_Exact_FourDigits", priority = 6)
	public void tc006Ledger_AccNum_With_Exact_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddSenderDetails.enterCostCenterData();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "Ledger_AccNum--> Exact_34_Digits", priority = 7)
	public void tc007Ledger_AccNum_With_Exact_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddSenderDetails.enterCostCenterData();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}

	@Test(testName = "ledger_AccNum--> GreaterThan_34_Digits", priority = 8)
	public static void tc008Ledger_AccNum_GreaterThan_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddSenderDetails.enterCostCenterData();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
	}
	
	
//// ==========================>> Sender Account Number- Ledger <<============================================================================================	
//
//	@Test(testName = "Ledger_AccNum--> less than 4 digits", priority = 9)
//	public static void tc009SenderName_LessThan_0_Digits_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-009");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
//	}
//
//	@Test(testName = "Ledger_AccNum--> With_Exact_FourDigits", priority = 10)
//	public void tc010Ledger_AccNum_With_Exact_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-010");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddSenderDetails.enterCostCenterData();
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmit();
//		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
//	}
//
//	@Test(testName = "Ledger_AccNum--> Exact_34_Digits", priority = 11)
//	public void tc011Ledger_AccNum_With_Exact_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-011");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddSenderDetails.enterCostCenterData();
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmit();
//		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
//	}
//
//	public static void tc012Ledger_AccNum_GreaterThan_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-012");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddSenderDetails.enterCostCenterData();
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmit();
//		ValidationPage.fetchSenderAccountDetails(objSenderDtls.getSenderAccNumber());
//	}
	

// =============================>> Recipient Account Number <<=========================================================================	

	@Test(testName = "RecipAccNum--> Less than 4-digits", priority = 13)
	public static void tc013RecipAccNum_LessThan_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinAccNumLengthErorrMsg());
	}

	@Test(testName = "RecipAccNum--> Exact 4-digits", priority = 14)
	public static void tc014RecipAccNum_Exact_04_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "RecipAccNum--> Exact 34-digits", priority = 15)
	public static void tc015RecipAccNum_Exact_34_Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

	@Test(testName = "RecipAccNum--> Greater than 34-digits", priority = 16)
	public static void tc016RecipAccNum_GreaterThan34Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientAccNum());
	}

// =============================>> Recipient Name <<=========================================================================	

	@Test(testName = "RecipientName--> Less than One-Character", priority = 17)
	public static void tc017RecipientName_LessThanOneCharacter_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-013");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam());
	}

	@Test(testName = "RecipientName--> Exact One-Character", priority = 18)
	public static void tc018RecipientName_ExactOneCharacter_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

	@Test(testName = "RecipientName--> Exact 140-digits", priority = 19)
	public static void tc019RecipientName_Exact_140Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

	@Test(testName = "RecipientName--> Greater than 140-digits", priority = 20)
	public static void tc020RecipientName_GreaterThan_140Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.fetchCreditorAccountDetails(objReciDtls.getRecipientName());
	}

// =============================>> Recipient Routing Number <<=========================================================================	

	@Test(testName = "RoutingNumber_LessThan 9-Digits", priority = 21)
	public static void tc021RoutingNumber_LessThan_9Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinRoutNumLengthErorrMsg());
	}

	@Test(testName = "RoutingNumber_Exact 9-Digits", priority = 22)
	public static void tc022RoutingNumber_Exact_9Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());
	}

	@Test(testName = "RoutingNumber_Greater 9-Digits", priority = 23)
	public static void tc023RoutingNumber_GreaterThan_9Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_RoutingNumber(objReciDtls.getRecipientRoutNum());
	}


	
//============================= Recipient Phone Number =================================================	

	@Test(testName = "PhoneNumber Less than 10Digits", priority = 24)
	public static void tc024PhoneNumber_LessThan_10Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getMinPhoneNumLengthErrorMsg());
	}

	@Test(testName = "PhoneNumber_Exact 10-Digits", priority = 25)
	public static void tc025PhoneNumber_Exact_10Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_PhoneNumber(objReciDtls.getRecipientPhone());
	}

	@Test(testName = "PhoneNumber_Greater Than 10-Digits", priority = 26)
	public static void tc026PhoneNumber_GreaterThan_10Digits_BVA_FI_MGD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ValidationPage.assertionBVA_PhoneNumber(objReciDtls.getRecipientPhone());
	}

	
////============================= Amount Field =================================================
//
//	@Test(testName = "Amount Less Than", priority = 27)
//	public static void tc027Amount_LessThan_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-020");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg1(objErrDtls.getInvdAmt());
//	}
//
//	@Test(testName = "Amount Exact", priority = 28)
//	public static void tc028Amount_Exact_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-021");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertionBVA_Amount(objReciDtls.getPmtAmt());
//	}
//
//	@Test(testName = "Amount Exact ", priority = 29)
//	public static void tc029Amount_Exact_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-022");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertionBVA_Amount(objReciDtls.getPmtAmt());
//	}
//
//	@Test(testName = "Amount Greater Than", priority = 30)
//	public static void tc030Amount_GreaterThan_BVA_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BVA-Pmts-MGD-FW-GL");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("BVA-Pmts-MGD-FW-023");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
//		ValidationPage.assertErrorFieldMsg1(objErrDtls.getMaxAmountError_Payments_FedWire());
//	}

	
	
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
			startSuite();  }

	}

	@AfterClass
	public void endSuite() throws Exception {
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);	}

}
