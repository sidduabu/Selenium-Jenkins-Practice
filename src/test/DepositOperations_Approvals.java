package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class DepositOperations_Approvals extends Utility {
	public static  ExtentSparkReporter extentSparkReporter;
	public static  ExtentReports extentReports;
	public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
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

	@Test(testName = "New Recipient_Retail Transfer with RTP", priority = 1)
	public void TC001NewRecipientRetailTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "New Recipient_Retail Transfer with FedNow", priority = 2)
	public void TC002NewRecipientRetailTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Future Date FedWire with New Recipient_Retail Transfer", priority = 3)
	public void TC003FutureDatewithNewRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "Current Date FedWire With a New Recipient_Retail Transfer", priority = 4)
	public void TC004CurrentDatewithNewRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "RTP With New Recipient_Business Transfer", priority = 5)
	public void TC005NewRecipientBusinessTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedNow With New Recipient_Business Transfer", priority = 6)
	public void TC006NewRecipientBusinessTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "New Recipient_Business Transfer_FedWire", priority = 7)
	public void TC007FutureDatePymntwithNewRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "Current Date with FedWire New Recipient_Business Transfer", priority = 8)
	public void TC008CurrentDateNewRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}



	@Test(testName = "Decline RTP With New Recipient_Retail Transfer", priority = 9)
	public void TC009DeclineNewRecipientRetailTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP015");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedNow With New Recipient_Retail Transfer", priority = 10)
	public void TC010DeclineNewRecipientRetailTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP016");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedWire with New Recipient_Retail Transfer", priority = 11)
	public void TC011DeclineFutureDateNewRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP017");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire With a New Recipient_Retail Transfer", priority = 12)
	public void TC012DeclineCurrentDateForNewRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP017");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline RTP With New Recipient_Business Transfer", priority = 13)
	public void TC013DeclineNewRecipientBusinessTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP018");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedNow New Recipient_Business Transfer", priority =14)
	public void TC014DeclineNewRecipientBusinessTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP019");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedWire New Recipient_Business Transfer", priority = 15)
	public void TC015DeclineFutureDateNewRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire New Recipient_Business Transfer", priority = 16)
	public void TC016DeclineCurrentDateNewRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	//	Existing Recipient


	@Test(testName = "Existing Recipient Retail Transfer Current Date with FedWire", priority = 17)
	public void TC017ExistRecipientRetailTransferCurrentDate_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Existing Recipient Retail Transfer Future Date with FedWire", priority = 18)
	public void TC018ExistRecipientRetailTransferFutureDate_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP022");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}


	@Test(testName = "Existing Recipient Business Transfer Current Date with FedWire", priority = 19)
	public void TC019ExistRecipientBusinessTransferCurrentDate_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP023");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Existing Recipient Business Transfer Future Date with FedWire", priority = 20)
	public void TC020ExistRecipientBusinessTransferFutureDate_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}


	@Test(testName = "Decline FedWire with Exist Recipient_Retail Transfer", priority = 21)
	public void TC021DeclineFutureDateExistRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP025");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire With a Exist Recipient_Retail Transfer", priority = 22)
	public void TC022DeclineCurrentDateExistRecipientRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP026");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire Exist Recipient_Business Transfer", priority = 23)
	public void TC023DeclineFutureDateExistRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire Exist Recipient_Business Transfer", priority = 24)
	public void TC024DeclineCurrentDateRecipientBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP028");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	/*---    Dual Approval Cases  ----*/

	@Test(testName = "New Recipient_Retail Transfer with RTP by Dual Approval", priority = 25)
	public void TC025NewRecipientRetailTransferDualApproval_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "New Recipient_Retail Transfer with FedNow Dual Approval", priority = 26)
	public void TC026NewRecipientRetailTransferDualApproval_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Future Date FedWire with New Recipient_Retail Transfer Dual Approval", priority = 27)
	public void TC027FutureDateNewRecipientRetailTransferDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "Current Date FedWire With a New Recipient_Retail Transfer Dual Approval", priority = 28)
	public void TC028CurrentDateNewRecipientRetailTransferDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}



	@Test(testName = "RTP With New Recipient_Business Transfer Dual Approval", priority = 29)
	public void TC029NewRecipientBusinessTransferDualApproval_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedNow With New Recipient_Business Transfer Dual Approval", priority = 30)
	public void TC030NewRecipientBusinessTransferDualApproval_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "New Recipient_Business Transfer_FedWire Dual Approval", priority = 31)
	public void TC031FutureDateNewRecipientBusinessTransferDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "Current Date with FedWire New Recipient_Business Transfer Dual Approval", priority = 32)
	public void TC032CurrentDateNewRecipientBusinessTransferDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}



	/*--- Transfer Declined By 3rd User ------*/



	@Test(testName = "Decline New Recipient_Retail Transfer with RTP by Partially Approved", priority = 33)
	public void TC033DeclineNewRecipientPartiallyApprovedRetailTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline New Recipient_Retail Transfer with FedNow Partially Approved", priority = 34)
	public void TC034DeclineNewRecipientPartiallyApprovedRetailTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline Future Date FedWire with New Recipient_Retail Transfer Partially Approved", priority = 35)
	public void TC035DeclineFutureDateNewRecipientPartiallyApprovedRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline Current Date FedWire With a New Recipient_Retail Transfer Partially Approved", priority = 36)
	public void TC036DeclineCurrentDateNewRecipientPartiallyApprovedRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}



	@Test(testName = "Decline RTP With New Recipient_Business Transfer Partially Approved", priority = 37)
	public void TC037DeclineNewRecipientPartiallyApprovedBusinessTransfer_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "FedNow With New Recipient_Business Transfer Partially Approved", priority = 38)
	public void TC038DeclineNewRecipientPartiallyApprovedBusinessTransfer_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline New Recipient_Business Transfer_FedWire Partially Approved", priority = 39)
	public void TC039DeclineFutureDateNewRecipientPartiallyApprovedBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline Current Date with FedWire New Recipient_Business Transfer Partially Approved", priority = 40)
	public void TC040DeclineCurrentDateNewRecipientPartiallyApprovedBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	/*---- Existing Recipient Dual Approval  -----*/


	@Test(testName = "Existing Recipient Retail Transfer Current Date with FedWire Dual Approval", priority = 41)
	public void TC041ExistRecipientRetailTransferCurrentDateDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Existing Recipient Retail Transfer Future Date with FedWire Dual Approval", priority = 42)
	public void TC042ExistRecipientRetailTransferFutureDateDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP022");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}


	@Test(testName = "Existing Recipient Business Transfer Current Date with FedWire DualApproval", priority = 43)
	public void TC043ExistRecipientBusinessTransferCurrentDateDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP023");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Existing Recipient Business Transfer Future Date with FedWire", priority = 44)
	public void TC044ExistRecipientBusinessTransferFutureDateDualApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	/*--- Decline Partially Approved Transfer ----*/


	@Test(testName = "Decline Existing Recipient Retail Transfer Current Date with FedWire Partially Approved", priority = 45)
	public void TC045DeclineExistRecipientCurrentDatePartiallyApprovedRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline Existing Recipient Retail Transfer Future Date with FedWire Partially Approved", priority = 46)
	public void TC046DeclineExistRecipientFutureDatePartiallyApprovedRetailTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP022");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline Existing Recipient Business Transfer Current Date with FedWire Partially Approved", priority = 47)
	public void TC047DeclineExistRecipientCurrentDatePartiallyApprovedBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP023");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline Existing Recipient Business Transfer Future Date with FedWire Partially Approved", priority = 48)
	public void TC048DeclineExistRecipientFutureDatePartiallyApprovedBusinessTransfer_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Payment Questionnaire Check For Branch Channel RetailTransfer_FedWire", priority = 49)
	public void TC049PymntQuestionnaireAmountAbove100000ForBranch_RetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP029");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.paymentQuestionnairExtraFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partial Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with final user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}




	/*--- OnUs Transfers with VCU Client   ------*/

	@Test(testName = "New Recipient_Retail Transfer with RTP", priority = 50)
	public void TC050_OnUsRTPNewRecipientRetailTransfer_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}

	@Test(testName = "New Recipient_Retail Transfer with FedNow", priority = 51)
	public void TC051_OnUsFedNowNewRecipientRetailTransfer_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST02");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS02");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "Current Date FedWire With a New Recipient_Retail Transfer", priority = 52)
	public void TC052_OnUsFedWireCurrentDatewithNewRecipientRetailTransfer_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "Existing Recipient Retail Transfer Current Date with FedWire", priority = 53)
	public void TC053_OnUsFedWireExistRecipientRetailTransferCurrentDate_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}

	/*------------Edit Transfer in Pending Approval flow--------------*/

	@Test(testName = "Future Date to Current Date FedWire With a New Recipients_Retail Transfer", priority = 54)
	public void TC054EditSender_FutureToCurrentDateRetailTransfer_NewRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("DP030");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCurrentDate();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Current Date to Future Date FedWire With a New Recipients_Retail Transfer", priority = 55)
	public void TC055EditSender_CurrentToFutureDateRetailTransfer_NewRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("DP030");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Current Date To Future Date FedWire With a Exist Recipients_Retail Transfer", priority = 56)
	public void TC056EditSender_CurrentToFutureDateRetailTransfer_ExistRecips_InPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		//Edit Another Sender and Recipient details
		objSenderDtls=TestDataReader.loadSenderDetails("DP034");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();	
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Future Date to Current Date FedWire With a Exist Recipients_Retail Transfer", priority = 57)
	public void TC057EditSender_FutureToCurrentDateRetailTransfer_ExistRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		//Edit Another Sender and Recipient details
		objSenderDtls=TestDataReader.loadSenderDetails("DP034");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterCurrentDate();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		list=AddRecipientDetails.getSelectedExistRecipDetails();
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Fedwire New Recip to Exist Recip_Retail Transfer", priority = 58)
	public void TC058EditNewRecipToExistRecip_RetailTransferInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP032");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC016");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("DP031");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Exist To New Recipient Fedwire _Retail Transfer", priority = 59)
	public void TC059EditExistRecipToNewRecip_RetailTransferInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		//Edit Another Sender and Recipient details
		objSenderDtls=TestDataReader.loadSenderDetails("DP033");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		senderName=AddSenderDetails.getSenderName(objSenderDtls);
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();	
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferNewReceiverDetails(objReciDtls);
		
	}
	
	
	@Test(testName = "Edit Payment Questionnaire only in Pending Approval", priority = 60)
	public void TC060Edit_PaymentQuestionnaireInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		//Edit Another Sender and Recipient details
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.senderDetailsLoadingCheck();
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.editPaymentQuestionnaire(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.assertTransferSenderDetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertTransferExistReceiverDetails(objReciDtls, list);
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
		AddSenderDetails.logOff();
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
}