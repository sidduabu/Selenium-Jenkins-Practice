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

public class AddRecipient_Risk_Delete extends Utility {

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

//=================>> ++++ Risk_Cases (Release and Retry Cases) ++++ <<=======================================================================		

//=================>> FI_MortgageDivision Cases<<=======================		

	@Test(testName = "AddNewRecipientIndividual_FI_RTP_Risk_Release_Approve_AndThen_DeleteActions", priority = 1)
	public void tc001AddNewRecipientIndividual_FI_RTP_Risk_Release_Approve_AndThen_DeleteActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-RTP");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
// ====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
// ====>> Approving with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
		AddRecipientDetails.deletingTheRecipient001("Active");
		AddSenderDetails.logOff();
// ====>> Deleting with another user(4th User)  <<===============			
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);

	}

	@Test(testName = "AddNewRecipientIndividual_FI_RTP_Risk_Release_DeclineActions", priority = 2)
	public void tc002AddNewRecipientIndividual_FI_RTP_Risk_Release_DeclineActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-RTP");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
//====>> Declining with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient2(PhoneNo, "Approval Declined");
	}

	@Test(testName = "AddNewRecipientIndividual_FI_RTP_Risk_RejectAction", priority = 3)
	public void tc003AddNewRecipientIndividual_FI_RTP_Risk_RejectActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-RTP");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Rejecting with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");
	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedNow_Risk_Release_Approve_AndThen_DeleteActions", priority = 4)
	public void tc004AddNewRecipientIndividual_FI_FedNow_Risk_Release_Approve_AndThen_DeleteActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FN");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
//====>> Approving with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
		AddRecipientDetails.deletingTheRecipient001("Active");
		AddSenderDetails.logOff();
//====>> Deleting with another user(4th User)  <<===============			
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);

	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedNow_Risk_Release_DeclineActions", priority = 5)
	public void tc005AddNewRecipientIndividual_FI_FedNow_Risk_Release_DeclineActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FN");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
//====>> Declining with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient2(PhoneNo, "Approval Declined");
	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedNow_Risk_RejectAction", priority = 6)
	public void tc006AddNewRecipientIndividual_FI_FedNow_Risk_RejectActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FN");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Rejecting with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");
	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedWire_Risk_Release_Approve_AndThen_DeleteActions", priority = 7)
	public void tc007AddNewRecipientIndividual_FI_FedWire_Risk_Release_Approve_AndThen_DeleteActions()
			throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FW");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
//====>> Approving with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
		AddRecipientDetails.deletingTheRecipient001("Active");
		AddSenderDetails.logOff();
//====>> Deleting with another user(4th User)  <<===============			
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);

	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedWire_Risk_Release_DeclineActions", priority = 8)
	public void tc008AddNewRecipientIndividual_FI_FedWire_Risk_Release_DeclineActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FW");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Releasing with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.releasingTheRiskRecipient("Awaiting Approval");
		AddSenderDetails.logOff();
//====>> Declining with another user(3rd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient2(PhoneNo, "Approval Declined");
	}

	@Test(testName = "AddNewRecipientIndividual_FI_FedWire_Risk_RejectAction", priority = 9)
	public void tc009AddNewRecipientIndividual_FI_FedWire_Risk_RejectActions() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-MGD-FW");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo = AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn3("Awaiting Risk Review");
		AddSenderDetails.logOff();
//====>> Rejecting with another user(2nd User)  <<===============
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.simpleSearch_FI(PhoneNo);
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");
	}

	
	
	
	
	
//=================>> DepOperation_RetailsTransferDivision Cases<<=======================		

	@Test(testName = "Risk_Release_AddNewRecipient-->> RTP-Network, RetailTransferDivision(Individual)", priority = 10)
	public void tc010AddNewRecipientIndividual_Risk_Release_AndThen_DeleteActions_DepOp_RTP() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.releasingTheRiskRecipient("Active");
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

	@Test(testName = "Risk_Reject_AddNewRecipient-->> RTP-Network, RetailTransferDivision(Individual)", priority = 11)
	public void tc011AddNewRecipientIndividual_Risk_RejectAction_DepOp_RTP() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");

	}

	@Test(testName = "Risk_Release_AddNewRecipient-->> FedNow-Network, RetailTransferDivision(Individual)", priority = 12)
	public void tc012AddNewRecipientIndividual_Risk_Release_AndThen_DeleteActions_DepOp_FedNow() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.releasingTheRiskRecipient("Active");
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

	@Test(testName = "Risk_Reject_AddNewRecipient-->> FedNow-Network, RetailTransferDivision(Individual)", priority = 13)
	public void tc013AddNewRecipientIndividual_Risk_RejectAction_DepOp_FedNow() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");

	}

	@Test(testName = "Risk_Release_AddNewRecipient-->> FedWire-Network, RetailTransferDivision(Individual)", priority = 14)
	public void tc014AddNewRecipientIndividual_Risk_Release_AndThen_DeleteActions_DepOp_FedWire() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.releasingTheRiskRecipient("Active");
		AddRecipientDetails.deletingTheRecipient001("Deleted");


	}

	@Test(testName = "Risk_Reject_AddNewRecipient-->> FedWire-Network, RetailTransferDivision(Individual)", priority = 15)
	public void tc015AddNewRecipientIndividual_Risk_RejectAction_DepOp_FedWire() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RISK");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.awaitingRiskReviewStatus();
		AddRecipientDetails.rejectingTheRiskRecipient("Revoked");

	}

//===========================>> Deleting the Recipient <<==============================================================================================================================================================

//Note :- Previously we used SylavanFinancial_621260177 client but Currently we are using VCU_273976369 client
//Why Because:-  For SylvanFinancialClient after creating a NewRecipient for a specific Sender AccountNumber w.r.t SenderName....then we can't able to lookup the NewRecipient as the SenderName is dynamic(Sender Names w.r.t sender account number are dynamic...we may get or we may not get the same name again when we look up)  
	
	@Test(testName = "AddNewRecipient_DuplicateCheck_DeletingRecip_-->>RTP-Network, RetailTransferDivision(Individual)", priority = 16)
	public void tc016AddNewRecipient_DuplicateCheck_DeletingRecip_RTP_Individual_DepOp() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String senderNam = AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
		AddSenderDetails.closeBtn();
//=======>>	Adding the Recipient with same details and checking the duplicate message <<=======	
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		 AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//============>>  Duplicate message check ================================
		AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.closeBtn();
//==========>> Searching the Recipient in Advance_Search and then deleting that recipient <<====		
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderNam);
		AddRecipientDetails.selectExistRecipient_DepOp(objRecipDtls);
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

	@Test(testName = "AddNewRecipient_DuplicateCheck_DeletingRecip_-->> FedNow-Network, RetailTransferDivision(Individual)", priority = 17)
	public void tc017AddNewRecipient_DuplicateCheck_DeletingRecip_FedNowIndividual_DepOp() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String senderNam = AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FN");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
		AddSenderDetails.closeBtn();
//=======>>	Adding the Recipient with same details and checking the duplicate message <<=======	
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//============>>  Duplicate message check ================================
		AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.closeBtn();
//==========>> Searching the Recipient in Advance_Search and then deleting that recipient <<====		
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderNam);
		AddRecipientDetails.selectExistRecipient_DepOp(objRecipDtls);
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

	@Test(testName = "AddNewRecipient_DuplicateCheck_DeletingRecip_-->> FedWire-Network, RetailTransferDivision(Individual)", priority = 18)
	public void tc018AddNewRecipient_DuplicateCheck_DeletingRecip_FedWire_Individual_DepOp() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String senderNam = AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
		AddSenderDetails.closeBtn();
//=======>>	Adding the Recipient with same details and checking the duplicate message <<=======	
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
//============>>  Duplicate message check ================================		
		AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.closeBtn();
//==========>> Searching the Recipient in Advance_Search and then deleting that recipient <<====		
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderNam);
		AddRecipientDetails.selectExistRecipient_DepOp(objRecipDtls);
		AddRecipientDetails.deletingTheRecipient001("Deleted");


	}

	@Test(testName = "AddNewRecipient_DuplicateCheck_DeletingRecip_-->> FedWire-Network, RetailTransferDivision(ForeignIndividual)", priority = 19)
	public void tc019AddNewRecipient_DuplicateCheck_DeletingRecip_FedWire_ForeignIndividual_DepOp() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String senderNam = AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
		AddSenderDetails.closeBtn();
//For Foreign_Individual and Foreign_recipient types---->Even if we are adding_Recipient with same details duplicate message is not displaying
////=======>>	Adding the Recipient with same details and checking the duplicate message <<=======	
//		AddSenderDetails.select_DivRoutingNum2(objSenderDtls);
//		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
//		String senderNam = AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//		AddRecipientDetails.recipDetails(objRecipDtls);
//		AddRecipientDetails.recipBankDetails(objRecipDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objRecipDtls);
//		AddRecipientDetails.addRecipientDuplicateMsg();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.closeBtn();
//==========>> Searching the Recipient in Advance_Search and then deleting that recipient <<====		
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderNam);
		AddRecipientDetails.selectExistRecipient_DepOp(objRecipDtls);
		AddRecipientDetails.deletingTheRecipient001("Deleted");


	}

	@Test(testName = "AddNewRecipient_DuplicateCheck_DeletingRecip_-->> FedWire-Network, RetailTransferDivision(ForeignBusiness)", priority = 20)
	public void tc020AddNewRecipient_DuplicateCheck_DeletingRecip_FedWire_ForeignBusiness_DepOp() throws Exception {
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-Risk-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String senderNam = AddSenderDetails.enterSenderAccountNumber_VCU(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
		AddSenderDetails.closeBtn();
////=======>>	Adding the Recipient with same details and checking the duplicate message <<=======	
//		AddSenderDetails.select_DivRoutingNum2(objSenderDtls);
//		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
//		String senderNam = AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
//		AddRecipientDetails.recipDetails(objRecipDtls);
//		AddRecipientDetails.recipBankDetails(objRecipDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objRecipDtls);
//		AddRecipientDetails.addRecipientDuplicateMsg();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.closeBtn();
////==========>> Searching the Recipient in Advance_Search and then deleting that recipient <<====		
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderNam);
		AddRecipientDetails.selectExistRecipient_DepOp(objRecipDtls);
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
	         startSuite();                }
			
	}

	@AfterClass
	public void endSuite() throws Exception {

		Utility.closeBrowser(WD);
	}

}
