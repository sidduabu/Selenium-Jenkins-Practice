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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import dataReader.TestDataReader;
import dataReader.Utility;
import pages.Login;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;

public class FIPayments_Approvals extends Utility {
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

	@Test(testName = "RTP Payment for a New Recipient_Positive", priority = 1)
	public void TC001RTPwithNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "FedNow Payment for a New Recipient_Positive", priority = 2)
	public void TC002FedNowWithNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Schd FedWire Future Date Payment", priority = 3)
	public void TC003SchdlFedWireFutureDateWithNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "FedWire Current Date Payment", priority = 4)
	public void TC004FedWireCurrentDatePmtWithNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "RTP Payment for a Exist Recipient_Positive", priority = 5)
	public void TC005RTPwithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedNow Payment for a Exist Recipient_Positive", priority = 6)
	public void TC006FedNowwithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "FedWire Current Date Payment Exist Recipient_Positive", priority = 7)
	public void TC007FedWireCurrentDatePmtWithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Schd FedWire Future Date Payment Exist Recipient_Positive", priority = 8)
	public void TC008SchdlFedWireFutureDatePmtWithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC008");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}


	//Decline Payments

	@Test(testName = "Decline RTP Payment for a New Recipient", priority = 9)
	public void TC009DeclineRTPPymtNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedNow Payment for a New Recipient", priority = 10)
	public void TC010DeclineFedNowPymtNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedWire Current Date Payment for a New Recipient", priority = 11)
	public void TC011DeclineFedWireCurrentDatePymtNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedWire Future Date Payment for a New Recipient", priority = 12)
	public void TC012DeclineFedWireFutureDatePymtNewRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}



	@Test(testName = "Decline RTP Payment for a Exist Recipient_Positive", priority = 13)
	public void TC013DeclineRTPwithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Decline FedNow Payment for a Exist Recipient_Positive", priority = 14)
	public void TC014DeclineFedNowwithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedWire Current Date Payment Exist Recipient_Positive", priority = 15)
	public void TC015DeclineFedWireCurrentDatePmtWithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Decline FedWire Future Date Payment Exist Recipient_Positive", priority = 16)
	public void TC016DeclineFedWireFutureDatePmtWithExistRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC008");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Decline with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	/*-----   Partially Approval Cases   -------*/



	@Test(testName = "RTP Payment for a New Recipient Partially Approved", priority = 17)
	public void TC017NewRecipientPartiallyApprovedFI_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC022");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "FedNow Payment for a New Recipient Partially Approved", priority = 18)
	public void TC018NewRecipientPartiallyApprovedFI_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC023");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Schd FedWire Future Date Payment Partially Approved", priority = 19)
	public void TC019FutureDateNewRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}

	@Test(testName = "FedWire New Recipient Current Date Payment Partially Approved", priority = 20)
	public void TC020CurrentDateNewRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}



	@Test(testName = "RTP Payment for a Exist Recipient Partially Approved", priority = 21)
	public void TC021ExistRecipientPartiallyApprovedFI_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC025");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedNow Payment for a Exist Recipient Partially Approved", priority = 22)
	public void TC022ExistRecipientPartiallyApprovedFI_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC026");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "FedWire Current Date Payment Exist Recipient Partially Approved", priority = 23)
	public void TC023CurrentDateExistRecipientPartiallyApprovedFI_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Schd FedWire Future Date Payment Exist Recipient Partially Approved", priority = 24)
	public void TC024FutureDateExistRecipientPartiallyApprovedFI_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingFuturePymt();
	}


	/*----Decline Cases----*/


	@Test(testName = "RTP Payment for a New Recipient Partially Approved", priority = 25)
	public void TC025DeclineNewRecipientPartiallyApprovedFI_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC022");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "FedNow Payment for a New Recipient Partially Approved", priority = 26)
	public void TC026DeclineNewRecipientPartiallyApprovedFI_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC023");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "Schd FedWire Future Date Payment Partially Approved", priority = 27)
	public void TC027DeclineFutureDateNewRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "FedWire Current Date Payment Partially Approved", priority = 28)
	public void TC028DeclineCurrentDateNewRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC024");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}



	@Test(testName = "RTP Payment for a Exist Recipient Partially Approved", priority = 29)
	public void TC029DeclineExistRecipientPartiallyApprovedFI_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC025");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	@Test(testName = "FedNow Payment for a Exist Recipient Partially Approved", priority = 30)
	public void TC030DeclineExistRecipientPartiallyApprovedFI_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC026");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "FedWire Current Date Payment Exist Recipient Partially Approved", priority = 31)
	public void TC031DeclineCurrentDateExistRecipientPartiallyApprovedFI_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "Schd FedWire Future Date Payment Exist Recipient Partially Approved", priority = 32)
	public void TC032DeclineFutureDateExistRecipientPartiallyApprovedFI_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterFIVCUSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	/*----   New Recipient Type FI     -----*/

	@Test(testName = "FI Recipient Type FedWire Payment Partially Approved", priority = 33)
	public void TC033FIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC028");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Decline FI Recipient Type FedWire Payment Partially Approved", priority = 34)
	public void TC034DeclineFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC028");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "DDA Acc with FI Recipient Type FedWire Payment Partially Approved", priority = 35)
	public void TC035DDAWithFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC029");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Decline DDA Account with FI Recipient Type FedWire Payment Partially Approved", priority = 36)
	public void TC036DeclineDDAWithFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC029");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.enterUniqueReference();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}

	/*------ Exist FI Recipient Payment --*/

	@Test(testName = "Existing FI Recipient Type FedWire Payment Partially Approved", priority = 37)
	public void TC037ExistFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC030");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Exist Decline FI Recipient Type FedWire Payment Partially Approved", priority = 38)
	public void TC038ExistedDeclineFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC031");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}


	@Test(testName = "DDA Acc with ExistFI Recipient Type FedWire Payment Partially Approved", priority = 39)
	public void TC039DDAWithExistedFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC031");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Decline Existed DDA Account with FI Recipient Type FedWire Payment Partially Approved", priority = 40)
	public void TC040DeclineDDAWithExistedFIRecipientPartiallyApproved_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC031");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enter100000Amount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Partially Approved
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.partialApprovePayment();
		AddSenderDetails.logOff();
		//Approving with 3rd User
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.declinePayment();
	}



	/*--  OnUs Cases with VCU as Debitor and Creditor---*/

	@Test(testName = "OnUs RTP Payment for a New Recipient_Positive", priority = 41)
	public void TC041_OnUsRTPwithNewRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs FedNow Payment for a New Recipient_Positive", priority = 42)
	public void TC042_OnUsFedNowWithNewRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS05");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS02");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs FedWire Current Date Payment", priority = 43)
	public void TC043_OnUsFedWireCurrentDatePmtWithNewRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS06");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs RTP Payment for a Exist Recipient_Positive", priority = 44)
	public void TC044_OnUsRTPwithExistRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}

	@Test(testName = "OnUs FedNow Payment for a Exist Recipient_Positive", priority = 45)
	public void TC045_OnUsFedNowwithExistRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS02");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS02");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs FedWire Current Date Payment Exist Recipient_Positive", priority = 46)
	public void TC046_OnUsFedWireCurrentDatePmtWithExistRecipient_VCU() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		AddSenderDetails.checkONUS();
	}


	/*------------Edit Payment in Pending Approval flow--------------*/

	@Test(testName = "Edit Mortgage To Other Acc DDA and Future Date FedWire New Recipient_Payment", priority = 47)
	public void TC047EditMortgageToDDAFutureToCurrentDate_NewRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCurrentDate();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Edit Mortgage To DDA Current Date FedWire With a New Recipient_Payment", priority = 48)
	public void TC048EditMortgageToDDACurrentToFutureDate_NewRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingFuturePymt();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Edit Mortgage to DDA Current Date FedWire With a Exist Recipients_Payment", priority = 49)
	public void TC049EditMortgageToDDACurrentToFutureDate_ExistRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC043");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingFuturePymt();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Mortgage to DDA and Future Date FedWire Exist Recipient_Payment", priority = 50)
	public void TC050EditMortgageToDDAFutureToCurrentDate_ExistRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC043");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterCurrentDate();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Other Account DDA to Mortgage and New Recipient to Exist Recipient", priority = 51)
	public void TC051EditOtherAccountDDAToMortgage_NewRecipToExistRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		String senderName=AddSenderDetails.getDDASenderName();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC044");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object>list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}


	@Test(testName = "Edit Other Account Ledger to Mortgage and Exist Recipient to New Recipient", priority = 52)
	public void TC052EditOtherAccountLedgerToMortgage_ExistRecipToNewRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC032");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}


	@Test(testName = "Edit Funds via Fed to Mortgage and New FI Type Recipient to Exist FI Type Recipient", priority = 53)
	public void TC053EditFundsviaFedToMortgage_NewFIRecipToExistFIRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC045");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Mortgage To Funds via Fed and Exist FI Type Recipient to New FI Type Recipient", priority = 54)
	public void TC054EditMortgageToFundsviaFed_ExistFIRecipToNewFIRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC035");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsEditedFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Edit Other Account DDA to Mortgage and New Recipient to Exist Recipient", priority = 55)
	public void TC055EditOtherAccountDDAToOtherAccDDA_NewRecipToExistRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		String senderName=AddSenderDetails.getDDASenderName();
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC046");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		Thread.sleep(2000);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object>list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}


	@Test(testName = "Edit Other Account DDA to Mortgage and New Recipient to Exist Recipient", priority = 56)
	public void TC056EditOtherAccountDDAToOtherAccLedger_NewRecipToExistRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		String senderName=AddSenderDetails.getDDASenderName();
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC047");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object>list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Other Account Ledger to Mortgage and Exist Recipient to New Recipient", priority = 57)
	public void TC057EditOtherAccountLedgerToOtherAccDDA_ExistRecipToNewRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC032");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Edit Other Account Ledger to Mortgage and Exist Recipient to New Recipient", priority = 58)
	public void TC058EditOtherAccountLedgerToOtherAccLedger_ExistRecipToNewRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC032");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC021");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}


	@Test(testName = "Edit Other Account DDA to Mortgage and New Recipient to Exist Recipient", priority = 59)
	public void TC059EditOtherAccountDDAToFundsviaFed_NewRecipToExistRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		String senderName=AddSenderDetails.getDDASenderName();
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC034");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object>list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsEditedFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}


	@Test(testName = "Edit Other Account Ledger to Mortgage and Exist Recipient to New Recipient", priority = 60)
	public void TC060EditOtherAccountLedgerToFundsviaFed_ExistRecipToNewRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC038");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsEditedFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
	}

	@Test(testName = "Edit Funds via Fed to Mortgage and New FI Type Recipient to Exist FI Type Recipient", priority = 61)
	public void TC061EditFundsviaFedToOtherAccDDA_NewFIRecipToExistFIRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC049");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		String senderName=AddSenderDetails.getDDASenderName();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDDADetails(objSenderDtls,senderName);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Funds via Fed to Mortgage and New FI Type Recipient to Exist FI Type Recipient", priority = 62)
	public void TC062EditFundsviaFedToOtherAccLedger_NewFIRecipToExistFIRecipInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsFI(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertNewReceiverDetails(objReciDtls);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC050");
		objReciDtls=TestDataReader.loadRecipientDetails("TC015");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Edit Mortgage to DDA and Future Date FedWire Exist Recipient_Payment", priority = 63)
	public void TC063EditMortgageToOtherAccLedger_ExistRecipsInPendingApproval_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		List<Object> detailsList=AddRecipientDetails.getDateAndAmount();
		List<Object> list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetails(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		objSenderDtls=TestDataReader.loadSenderDetails("TC047");
		objReciDtls=TestDataReader.loadRecipientDetails("TC014");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddRecipientDetails.editClick_PendingApproval();
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddSenderDetails.selectNewOrExistingRecipFIPayments(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		detailsList=AddRecipientDetails.getDateAndAmount();
		list=AddRecipientDetails.getSelectedExistRecipDetails();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='Payment has been updated and is pending for approval.']")));
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_003");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
		//Assert Entered fields are showing same or not
		AddSenderDetails.assertMortgageSenderDetailsLedger(objSenderDtls);
		AddRecipientDetails.assertDateAndAmount(detailsList);
		AddRecipientDetails.clickDownArrows();
		AddRecipientDetails.assertExistReceiverDetails(objReciDtls, list);
	}

	@Test(testName = "Save Recipient in Pending Approval Page", priority = 64)
	public void TC064SaveRecipientInPendingApprovalPage_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		String phoneNo=AddRecipientDetails.enterRandomPhoneNum();
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
		AddRecipientDetails.saveThisRecipientClick();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.checkPaymentMethod(objSenderDtls);
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(phoneNo);
		AddRecipientDetails.successApprovalMsg();
	}

	@Test(testName = "Save Recipient in Pending Approval Page", priority = 65)
	public void TC065SaveRecipientInPendingApprovalPage_RTP() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		String phoneNo=AddRecipientDetails.enterRandomPhoneNum();
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
		AddRecipientDetails.saveThisRecipientClick();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.checkPaymentMethod(objSenderDtls);
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(phoneNo);
		AddRecipientDetails.successApprovalMsg();
	}


	@Test(testName = "Save Recipient in Pending Approval Page", priority = 66)
	public void TC066SaveRecipientInPendingApprovalPage_FedNow() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		String phoneNo=AddRecipientDetails.enterRandomPhoneNum();
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
		AddRecipientDetails.saveThisRecipientClick();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.checkPaymentMethod(objSenderDtls);
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(phoneNo);
		AddRecipientDetails.successApprovalMsg();
	}


	@Test(testName = "Payment From Funds Via Fed and Save FI Recipient In Pending Approval Page", priority = 67)
	public void TC067SaveFIRecipientInPendingApprovalPage_FedWire() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC033");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
		AddRecipientDetails.saveThisRecipientClick();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.checkPaymentMethod(objSenderDtls);
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(objReciDtls.getRecipientName());
		AddRecipientDetails.successApprovalMsg();
	}

	@Test(testName = "RTP Payment with all Networks Exist Recipient_Positive", priority = 68)
	public void TC068RTPwithAllNetworksExistRecipient_SingleApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC040");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo); 
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "Fednow Payment with all Networks Exist Recipient_Positive", priority = 69)
	public void TC069FednowwithAllNetworksExistRecipient_SingleApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC041");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		//Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}


	@Test(testName = "Fedwire Payment with all Networks Exist Recipient_Positive", priority = 70)
	public void TC070FedwirewithAllNetworksExistRecipient_SingleApproval() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC042");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//		Approving with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
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