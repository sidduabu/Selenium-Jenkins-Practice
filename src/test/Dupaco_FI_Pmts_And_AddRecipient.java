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

public class Dupaco_FI_Pmts_And_AddRecipient extends Utility {

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


// Info:- From testCase 01 to 15 (Payments with NewRecipient---> CurrentDate)
//	      From testCase 16 to 21 (Payments with NewRecipient---> FutureDate )
//	      From testCase 22 to 36 (Payments with ExistingRecipient---> CurrentDate)
//        From testCase 36 to 42 (Payments with ExistingRecipient---> FutureDate )
//        From testCase 42 to 48 (FI to FI Payments for FutureDate Check ---> With both New & Existing Recipient)
//	      From testCase 49 to 51 (Adding NewRecipient---> FI, Individual, Business types)
	

	
//========================>> Payments With New Recipient (Current Date Payments) <<===============================	

	@Test(testName = "DupacoPmt_FundViaFedMasterAccountToFI_DirectSubmit_CurrentDate(NewRecipient-FI)", priority = 1)
	public void tc001DupacoPmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount to FI_MGD_SubmitForApproval_CurrentDate(NewRecipient-FI)", priority = 2)
	public void tc002DupacoPmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount_FI_MGD_DeclinePmt_CurrentDate(NewRecipient-FI)", priority = 3)
	public void tc003DupacoPmt_FundViaFedMasterAccToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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
		// Declining the payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();	
		}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_DirectSubmit_CurrentDate(NewRecipient-FI)", priority = 4)
	public void tc004DupacoPmt_DDAaccToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_Submit For Approval_CurrentDate(NewRecipient-FI)", priority = 5)
	public void tc005DupacoPmt_DDAaccToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_Decline Payment_CurrentDate(NewRecipient-FI)", priority = 6)
	public void tc006DupacoPmt_DDAaccToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();	
		}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_DirectSubmit_CurrentDate(NewRecipient-Individual)", priority = 7)
	public void tc007DupacoPmt_DDAaccToNewIndividualRecip_CurrentDate_DirectSubmit() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_Submit For Approval_CurrentDate(NewRecipient-Individual)", priority = 8)
	public void tc008DupacoPmt_DDAaccToNewIndividualRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_DDA_Acc_MGD_Decline Payment_CurrentDate(NewRecipient_Individual)", priority = 9)
	public void tc009DupacoPmt_DDAaccToNewIndividualRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
		}

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_DirectSubmit_CurrentDate(NewRecipient_FI)", priority = 10)
	public void tc010DupacoPmt_LedgerAccToNewFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_SubmitForApproval_CurrentDate(NewRecipient_FI)", priority = 11)
	public void tc011DupacoPmt_LedgerAccToNewFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_DeclinePmt_CurrentDate(NewRecipient_FI)", priority = 12)
	public void tc012DupacoPmt_LedgerAccToNewFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
		}

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_DirectSubmit_CurrentDate(NewRecipient_FI)", priority = 13)
	public void tc013DupacoPmt_LedgerAccToNewIndividualRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_SubmitForApproval_CurrentDate(NewRecipient_FI)", priority = 14)
	public void tc014DupacoPmt_LedgerAccToNewIndividualRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
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

	@Test(testName = "DupacoPmt_LedgerAcc_MGD_DeclinePmt_CurrentDate(NewRecipient_Individual)", priority = 15)
	public void tc015DupacoPmt_LedgerAccToNewIndividualRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
		}

	
	
//========================>> Payments With New Recipient(Future Date Payments ) <<==================================================================================================				

		@Test(testName = "DupacoPmt_DDA_Acc_MGD_DirectSubmit_FutureDate(NewRecipient-Individual)", priority = 16)
		public void tc016DupacoPmt_DDAaccToNewIndividualRecip_FutureDate_DirectSubmit() throws Exception {
			Utility.open_TMSSite();
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_FI();
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}

		@Test(testName = "DupacoPmt_DDA_Acc_MGD_Submit For Approval_FutureDate(NewRecipient-Individual)", priority = 17)
		public void tc017DupacoPmt_DDAaccToNewIndividualRecip_FutureDate_SubmitForApproval() throws Exception {
			Utility.open_TMSSite();
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_FI();
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			// Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();
		}

		@Test(testName = "DupacoPmt_DDA_Acc_MGD_Decline Payment_FutureDate(NewRecipient_Individual)", priority = 18)
		public void tc018DupacoPmt_DDAaccToNewIndividualRecip_FutureDate_DeclinePmt() throws Exception {
			Utility.open_TMSSite();
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_FI();
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining Payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();
			}	
		
		@Test(testName = "DupacoPmt_LedgerAcc_MGD_DirectSubmit_FutureDate(NewRecipient_FI)", priority = 19)
		public void tc019DupacoPmt_LedgerAccToNewIndividualRecip_FutureDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}

		@Test(testName = "DupacoPmt_LedgerAcc_MGD_SubmitForApproval_FutureDate(NewRecipient_FI)", priority = 20)
		public void tc020DupacoPmt_LedgerAccToNewIndividualRecip_FutureDate_SubmitForApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//					Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();		}

		@Test(testName = "DupacoPmt_LedgerAcc_MGD_DeclinePmt_FutureDate(NewRecipient_Individual)", priority = 21)
		public void tc021DupacoPmt_LedgerAccToNewIndividualRecip_FutureDate_DeclinePmt() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterSenderDetails(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining Payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectPaymentsManager();
			AddSenderDetails.Clik_Payments_Link_FI();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();
		}
	
	
	
//===============================>> Payments with Existing Recipient <<=====================================================================================================
//==================================================================================================================================================================================

//========================>> Current Date Payments <<===============================	
		
	@Test(testName = "DupacoPmt_FundViaFedMasterAccount_FI_MGD_DirectSubmit_CurrentDate(ExistingRecipient-FI)", priority = 22)
	public void tc022DupacoPmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount To FI_MGD_SubmitForApproval_CurrentDate(ExistingRecipient-FI)", priority = 23)
	public void tc023DupacoPmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount to FI_MGD_DeclinePmt__CurrentDate(ExistingRecipient-FI)", priority = 24)
	public void tc024DupacoPmt_FundViaFedMasterAccToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
// Declining the payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_to FI MGD_DirectSubmit_CurrentDate(ExistingRecipient-FI)", priority = 25)
	public void tc025DupacoPmt_DDAaccToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_to FI MGD_Submit For Approval_CurrentDate(ExistingRecipient-FI)", priority = 26)
	public void tc026DupacoPmt_DDAaccToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_DDA_Acc_FI to FI MGD_Decline Payment_CurrentDate(ExistingRecipient-FI)", priority = 27)
	public void tc027DupacoPmt_DDAaccToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_FI to Individual MGD_DirectSubmit_CurrentDate(ExistingRecipient-Individual)", priority = 28)
	public void tc028DupacoPmt_DDAaccToExistingIndividualRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_FI to Individual MGD_Submit For Approval_CurrentDate(ExistingRecipient-Individual)", priority = 29)
	public void tc029DupacoPmt_DDAaccToExistingIndividualRecip_CurrentDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_DDA_Acc_FI to Individual MGD_Decline Payment_CurrentDate(ExistingRecipient)", priority = 30)
	public void tc030DupacoPmt_DDAaccToExistingIndividualRecip_CurrentDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_To FI MGD_DirectSubmit_CurrentDate(ExistingRecipient_FI)", priority = 31)
	public void tc031DupacoPmt_LedgerAccToExistingFIRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_To FI MGD_SubmitForApproval__CurrentDate(ExistingRecipient_FI)", priority = 32)
	public void tc032DupacoPmt_LedgerAccToExistingFIRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
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
		AddSenderDetails.approvingPayment();	}

	@Test(testName = "DupacoPmt_LedgerAcc_To FI MGD_DeclinePmt_CurrentDate(ExistingRecipient_FI)", priority = 33)
	public void tc033DupacoPmt_LedgerAccToExistingFIRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_to Individual  MGD_DirectSubmit_CurrentDate(ExistingRecipient_Individual)", priority = 34)
	public void tc034DupacoPmt_LedgerAccToExistingIndividualRecip_CurrentDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_to Individual  MGD_SubmitForApproval_CurrentDate(ExistingRecipient_Individual)", priority = 35)
	public void tc035DupacoPmt_LedgerAccToExistingIndividualRecip_CurrentDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
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

	@Test(testName = "DupacoPmt_LedgerAcc_to Individual  MGD_DeclinePmt_CurrentDate(ExistingRecipient_Individual)", priority = 36)
	public void tc036DupacoPmt_LedgerAccToExistingIndividualRecip_CurrentDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		}

	
//========================>> Future Date Payments ( Payments With Existing Recipient) <<==================================================================================================				
	
	@Test(testName = "DupacoPmt_DDA_Acc to Individual MGD_DirectSubmit_FutureDate(ExistingRecipient-Individual)", priority = 37)
	public void tc037DupacoPmt_DDAaccToExistingIndividualRecip_FutureDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "DupacoPmt_DDA_Acc_to Individual MGD_Submit For Approval_FutureDate(ExistingRecipient-Individual)", priority = 38)
	public void tc038DupacoPmt_DDAaccToExistingIndividualRecip_FutureDate_SubmitForApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
		}

	@Test(testName = "DupacoPmt_DDA_Acc_to Individual MGD_Decline Payment_FutureDate(ExistingRecipient)", priority = 39)
	public void tc039DupacoPmt_DDAaccToExistingIndividualRecip_FutureDate_DeclinePmt() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}
	
	
	@Test(testName = "DupacoPmt_LedgerAcc_to Individual MGD_DirectSubmit_FutureDate(ExistingRecipient_Individual)", priority = 40)
	public void tc040DupacoPmt_LedgerAccToExistingIndividualRecip_FutureDate_DirectSubmit() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_to Individual MGD_SubmitForApproval_FutureDate(ExistingRecipient_Individual)", priority = 41)
	public void tc041DupacoPmt_LedgerAccToExistingIndividualRecip_FutureDate_SubmitForApproval() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//				Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();	
		}

	
	@Test(testName = "DupacoPmt_LedgerAcc_to Individual MGD_DeclinePmt_FutureDate(ExistingRecipient_Individual)", priority = 42)
	public void tc042DupacoPmt_LedgerAccToExistingIndividualRecip_FutureDate_DeclinePmt() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments( "Automation_Individual");
		AddSenderDetails.selectAccType_enterAccNum_AccName(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//		Declining Payment with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}	
	
	
	
	
//========================>> Future Date Payments Check for FI to FI(Both New & Existing Recipient <<===============================	

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount_To FI _MGD_DirectSubmit_(NewRecipient-FI)", priority = 43)
	public void tc043DupacoPmt_FundViaFedMasterAccToNewFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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
	}

	@Test(testName = "DupacoPmt_DDA_Acc__To FI MGD_DirectSubmit_(NewRecipient-FI)", priority = 44)
	public void tc044DupacoPmt_DDAaccToNewFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
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

	}

	@Test(testName = "DupacoPmt_LedgerAcc__To FI MGD_DirectSubmit_(NewRecipient_FI)", priority = 45)
	public void tc045DupacoPmt_LedgerAccToNewFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
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
	}

	@Test(testName = "DupacoPmt_FundViaFedMasterAccount_To FI _MGD_DirectSubmit_(ExistingRecipient-FI)", priority = 46)
	public void tc046DupacoPmt_FundViaFedMasterAccToExistingFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-FFM");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();

	}

	@Test(testName = "DupacoPmt_DDA_Acc__To FI MGD_DirectSubmit_(ExistingRecipient-FI)", priority = 47)
	public void tc047DupacoPmt_DDAaccToExistingFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-DDA");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.dupaco_SelectingSenderAccType_FI();
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	}

	@Test(testName = "DupacoPmt_LedgerAcc_To FI MGD_DirectSubmit_(ExistingRecipient_FI)", priority = 48)
	public void tc048DupacoPmt_LedgerAccToExistingFIRecip_FutureDateCheck() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DUPC-PMT-MGD-LDG");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DUPC-PMT-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectingExistingRecipient_FIpayments("AutomationTesting_FI");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.enterUniqueReference();
//  For FI to FI payments....Payment date should not be enable		
		AddRecipientDetails.pmtDateCheck_FItoFI(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	}

	
	
// =========================================================================================================================================================================================================
// ---------------------------------->> FI - Add Recipient (Dupaco_Client-FedWire) << ---------------------------------------------------------------------------------------------------------------------------------------------------
// =========================================================================================================================================================================================================

	@Test(testName = "Dupaco_AddNewRecipient_MGD_Individual", priority = 49)
	public void tc049Dupaco_AddNewRecipient_Individual_And_Deleting() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPC-RC-MGD");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPC-RC-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");		
	}

	@Test(testName = "Dupaco_AddNewRecipient_MGD_Business", priority = 50)
	public void tc050Dupaco_AddNewRecipient_Business_And_Deleting() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPC-RC-MGD");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPC-RC-MGD-002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");		

	}

	@Test(testName = "Dupaco_AddNewRecipient_MGD_FI", priority = 51)
	public void tc051Dupaco_AddNewRecipient_FI_And_Deleting() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPC-RC-MGD");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPC-RC-MGD-003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddRecipientDetails.enterNewRecipientDetails_FIRecipientType(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");		
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
		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD);
	        startSuite();             }

	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
}
