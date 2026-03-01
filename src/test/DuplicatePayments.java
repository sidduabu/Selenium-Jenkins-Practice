package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class DuplicatePayments extends Utility {
	public static  ExtentSparkReporter extentSparkReporter;
	public static  ExtentReports extentReports;
	public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
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
		AddSenderDetails.selectCustomerSupport();
	}



	@Test(testName = "Check Duplicate with Same Amount but Recipient Details & Sender Details Different - Reject & Warning Msg", priority = 1)
	public void TC001CheckDuplicateWithSameAmountOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
        //Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeAllPages();
        //Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details but Recipient Details & Amount Different - Reject & Warning Msg", priority = 2)
	public void TC002CheckDuplicateWithSameSenderAccDetailsOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Recipient Details but Sender Details & Amount Different - Reject & Warning Msg", priority = 3)
	public void TC003CheckDuplicateWithSameRecipientDetailsOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Receiver Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Receiver Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Amount but Recipient Details Different - Reject & Warning Msg", priority = 4)
	public void TC004CheckDuplicateWithSameSenderDetailsAndAmountOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Recipient Details but Amount Different - Reject & Warning Msg", priority = 5)
	public void TC005CheckDuplicateWithSameSenderDetailsAndRecipientDetailsOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Receiver Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Receiver Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with Same Recipient Details & Amount but Sender Details Different - Reject & Warning Msg", priority = 6)
	public void TC006CheckDuplicateWithSameRecipientDetailsAndAmountOnly_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("RTP02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with All Same Recipient Details, Amount and Sender Details - Reject & Warning Msg", priority = 7)
	public void TC007CheckDuplicateWithAllSameDetails_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with All Same Recipient Details, Amount and Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with All Same Recipient Details, Amount and Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("RTP01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Amount but Recipient Details & Sender Details Different - Reject & Warning Msg", priority = 8)
	public void TC008CheckDuplicateWithSameAmountOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details but Recipient Details & Amount Different - Reject & Warning Msg", priority = 9)
	public void TC009CheckDuplicateWithSameSenderAccDetailsOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Recipient Details but Sender Details & Amount Different - Reject & Warning Msg", priority = 10)
	public void TC010CheckDuplicateWithSameRecipientDetailsOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Amount but Recipient Details Different - Reject & Warning Msg", priority = 11)
	public void TC011CheckDuplicateWithSameSenderDetailsAndAmountOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Recipient Details but Amount Different - Reject & Warning Msg", priority = 12)
	public void TC012CheckDuplicateWithSameSenderDetailsAndRecipientDetailsOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with Same Recipient Details & Amount but Sender Details Different - Reject & Warning Msg", priority = 13)
	public void TC013CheckDuplicateWithSameRecipientDetailsAndAmountOnly_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with All Same Recipient Details, Amount and Sender Details - Reject & Warning Msg", priority = 14)
	public void TC014CheckDuplicateWithAllSameDetails_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
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
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with All Same Recipient Details, Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with All Same Recipient Details, Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedNow01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Amount but Recipient Details & Sender Details Different - Reject & Warning Msg", priority = 15)
	public void TC015CheckDuplicateWithSameAmountOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details but Recipient Details & Amount Different - Reject & Warning Msg", priority = 16)
	public void TC016CheckDuplicateWithSameSenderAccDetailsOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Recipient Details but Sender Details & Amount Different - Reject & Warning Msg", priority = 17)
	public void TC017CheckDuplicateWithSameRecipientDetailsOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Amount but Recipient Details Different - Reject & Warning Msg", priority = 18)
	public void TC018CheckDuplicateWithSameSenderDetailsAndAmountOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP04");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}


	@Test(testName = "Check Duplicate with Same Sender Details & Recipient Details but Amount Different - Reject & Warning Msg", priority = 19)
	public void TC019CheckDuplicateWithSameSenderDetailsAndRecipientDetailsOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP02");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Sender Details & Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Sender Details & Recipient Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP03");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with Same Recipient Details & Amount but Sender Details Different - Reject & Warning Msg", priority = 20)
	public void TC020CheckDuplicateWithSameRecipientDetailsAndAmountOnly_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with Same Recipient Details & Amount
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire02");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
	}



	@Test(testName = "Check Duplicate with All Same Recipient Details, Amount and Sender Details - Reject & Warning Msg", priority = 21)
	public void TC021CheckDuplicateWithAllSameDetails_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		List<String> list=AddRecipientDetails.storeFields();
		AddSenderDetails.closeBtn();
		//Check Duplicate with All Same Recipient Details, Amount and Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertRejectMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);;
		AddSenderDetails.closeAllPages();
		//Check Duplicate with All Same Recipient Details, Amount and Sender Details
		objSenderDtls=TestDataReader.loadSenderDetails("FedWire01");
		objReciDtls=TestDataReader.loadRecipientDetails("DUP01");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.AssertWarningMsg();
		AddRecipientDetails.AssertAmount(objReciDtls);
		AddSenderDetails.AssertSenderDetails(objSenderDtls);
		AddRecipientDetails.AssertReceiverDetails(objReciDtls);
		AddRecipientDetails.AssertStatus(list);
		AddSenderDetails.closeBtn();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddRecipientDetails.checkSubmitBtnVisible(); 
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
		AddSenderDetails.closeBtn();
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
}