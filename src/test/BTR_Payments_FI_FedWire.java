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
import dataReader.TestDataReader;
import dataReader.Utility;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;
import pages.Login;

public class BTR_Payments_FI_FedWire extends Utility {

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

//========================================================================================================================================================================================================================
//==================================>> BTR_Pmts with New Recipient (CurrentDate) <<=====================================================================================================================================================================		

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DirectSubmit", priority = 1)
	public void tc001BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_SubmitForApproval", priority = 2)
	public void tc002BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DeclinePmt", priority = 3)
	public void tc003BTR_Pmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "BTR_Pmt_GLaccountToNewFIRecip_CurrentDate_DirectSubmit", priority = 4)
	public void tc004BTR_Pmt_GLaccountToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();

	}

	@Test(testName = "BTR_Pmt_GLaccountToNewFIRecip_CurrentDate_SubmitForApproval", priority = 5)
	public void tc005BTR_Pmt_GLaccountToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmt_GLaccountToNewFIRecip_CurrrentDate_DeclinePmt", priority = 6)
	public void tc006BTR_Pmt_GLaccountToNewFIRecip_CurrrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "BTR_Pmt_DDAaccountToNewFIRecip_CurrentDate_DirectSubmit", priority = 7)
	public void tc007BTR_Pmt_DDAaccountToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_DDAaccountToNewFIRecip_CurrentDate_SubmitForApproval", priority = 8)
	public void tc008BTR_Pmt_DDAaccountToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "BTR_Pmts_DDAaccountToNewFIRecip_CurrentDate_DeclinePmt", priority = 9)
	public void tc009BTR_Pmts_DDAaccountToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "BTR_Pmts_LedgerAccountToNewFIRecip_CurrentDate_DirectSubmit", priority = 10)
	public void tc010BTR_Pmts_LedgerAccountToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmts_LedgerAccountToNewFIRecip_CurrentDate_SubmitForApproval", priority = 11)
	public void tc011BTR_Pmts_LedgerAccountToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "BTR_Pmt_LedgerAccountToNewFIRecip_CurrentDate_DeclinePmt", priority = 12)
	public void tc012BTR_Pmt_LedgerAccountToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

//==================================>> BTR_Pmts with Existing Recipient (CurrentDate) <<=====================================================================================================================================================================		

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DirectSubmit", priority = 13)
	public void tc013BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_SubmitForApproval", priority = 14)
	public void tc014BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DeclinePmt", priority = 15)
	public void tc015BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "BTR_Pmt_GLaccountToExistingFIRecip_CurrentDate_DirectSubmit", priority = 16)
	public void tc016BTR_Pmt_GLaccountToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();

	}

	@Test(testName = "BTR_Pmt_GLaccountToExistingFIRecip_CurrentDate_SubmitForApproval", priority = 17)
	public void tc017BTR_Pmt_GLaccountToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmt_GLaccountToExistingFIRecip_CurrrentDate_DeclinePmt", priority = 18)
	public void tc018BTR_Pmt_GLaccountToExistingFIRecip_CurrrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "BTR_Pmt_DDAaccountToExistingFIRecip_CurrentDate_DirectSubmit", priority = 19)
	public void tc019BTR_Pmt_DDAaccountToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_DDAaccountToExistingFIRecip_CurrentDate_SubmitForApproval", priority = 20)
	public void tc020BTR_Pmt_DDAaccountToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmts_DDAaccountToExistingFIRecip_CurrentDate_DeclinePmt", priority = 21)
	public void tc021BTR_Pmts_DDAaccountToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();

	}

	@Test(testName = "BTR_Pmts_LedgerAccountToExistingFIRecip_CurrentDate_DirectSubmit", priority = 22)
	public void tc022BTR_Pmts_LedgerAccountToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmts_LedgerAccountToExistingFIRecip_CurrentDate_SubmitForApproval", priority = 23)
	public void tc023BTR_Pmts_LedgerAccountToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "BTR_Pmt_LedgerAccountToExistingFIRecip_CurrentDate_DeclinePmt", priority = 24)
	public void tc024BTR_Pmt_LedgerAccountToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Decline with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

//==================================>> BTR_Pmts FutureDate check  <<=====================================================================================================================================================================		

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToNewFIRecip_FutureDateCheck_DirectSubmit", priority = 25)
	public void tc025BTR_Pmt_FundViaFedMasterAccToNewFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);

// Checking API call
//		AddRecipientDetails.enterAmount_CheckAPIcall(objReciDtls);

//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "TC026BTR_Pmt_GLaccountToNewFIRecip_FutureDateCheck_DirectSubmit", priority = 26)
	public void tc026BTR_Pmt_GLaccountToNewFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();

	}

	@Test(testName = "BTR_Pmt_DDAaccountToNewFIRecip_FutureDateCheck_DirectSubmit", priority = 27)
	public void tc027BTR_Pmt_DDAaccountToNewFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmts_LedgerAccountToNewFIRecip_FutureDateCheck_DirectSubmit", priority = 28)
	public void tc028BTR_Pmts_LedgerAccountToNewFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_FutureDateCheck_DirectSubmit", priority = 29)
	public void tc029BTR_Pmt_FundViaFedMasterAccToExistingFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-005");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		// For FI to FI payments....Payment date should not be enable
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmt_GLaccountToExistingFIRecip_FutureDateCheck_DirectSubmit", priority = 30)
	public void tc030BTR_Pmt_GLaccountToExistingFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-006");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		// For FI to FI payments....Payment date should not be enable
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();

	}

	@Test(testName = "BTR_Pmt_DDAaccountToExistingFIRecip_FutureDateCheck_DirectSubmit", priority = 31)
	public void tc031BTR_Pmt_DDAaccountToExistingFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-007");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "BTR_Pmts_LedgerAccountToExistingFIRecip_FutureDateCheck_DirectSubmit", priority = 32)
	public void tc032BTR_Pmts_LedgerAccountToExistingFIRecip_FutureDateCheck_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-008");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
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

		String testCaseStatus = AddSenderDetails.logOff2();
		if (testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD);
			startSuite();               }

	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}

}
