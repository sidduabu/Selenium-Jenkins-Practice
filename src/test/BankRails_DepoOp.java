package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
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
import pages.BankRails;
import pages.Login;

public class BankRails_DepoOp extends Utility {
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
		String filepath=Paths.get(System.getProperty("user.dir")) + "/TestReport/Report_" + System.getProperty("user.name")
		+ Paths.get(System.getProperty("user.dir")).getFileName() + dateFormat.format(date) + ".html";
		extentSparkReporter  = new ExtentSparkReporter(filepath);
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


	@Test(testName = "Reject Debit,as AccountNo does not exist starts with D2006", priority = 1)
	public void TC001RejectsDebitAsAcctNoNotExistStartsWithD2006_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getAccountNotExistText(),objError.getAccNumNotExistMsg());
	}

	@Test(testName = "Reject Debit,as AccountNo does not exist starts with D2101", priority = 2)
	public void TC002RejectsDebitAsAcctNoNotExistStartsWithD2101_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD02");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getAccountNotExistText(),objError.getAccNumNotExistMsg());
	}


	@Test(testName = "Reject Debit Credit As AccNot Open starts with B2013", priority = 3)
	public void TC003RejectsDebitCreditAsAccNotOpenStartsWithB2013_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),objError.getDebitorAccCloseMsg());
	}


	@Test(testName = "Rejects the Credit as the account number is invalid starts with C2101", priority = 4)
	public void TC004RejectsCreditAsAccNoInvalidStartsWithC2101_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD02");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}


	@Test(testName = "Rejects the Debit as Funds are Insufficient starts with D2007", priority = 5)
	public void TC005RejectsDebitAsInsufficientFundsStartsD2007_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD05");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndInProcessMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getInsuficientBalance3());
	}


	@Test(testName = "Rejects the Payment as the account number is invalid with 1010000017173", priority = 6)
	public void TC006RejectsPymntAsInsufficientFundsStartsINSB_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD06");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.closeBtn();
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError=TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInsufficientBalance2());
	}


	@Test(testName = "Rejects the Debit as the requested amount is less than minimum allowed Starts D2008", priority = 7)
	public void TC007RejectsDebitAsReqAmountLessThanMinStartsD2008_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD07");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),objError.getReqAmountLessThanMinMsg3());
	}


	@Test(testName = "Rejects the Credit as the requested amount is less than minimum allowed Starts 222008", priority = 8)
	public void TC008RejectsCreditAsReqAmountLessThanMinStarts222008_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD08");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD04");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getReqAmountLessThanMinMsg2());
	}


	@Test(testName = "Rejects the Debit as there is some error with bankcore setup Starts D1001", priority = 9)
	public void TC009RejectsDebitAsErrorAtBankCoreSetupStartsD1001_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD09");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankCoreSetUpErrMsg());
	}


	@Test(testName = "Rejects the Debit as unauthorized request sent to bankcore service Starts D2002", priority = 10)
	public void TC010RejectsDebitAsUnauthorizedReqSentToBankcoreStartsD2002_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD10");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getUnAuthorisedReqSentMsg());
	}



	@Test(testName = "Bank Rails Rejects the Debit as the bankcore service is offline or timeout Starts D2004", priority = 11)
	public void TC011RejectsDebitAsBankcoreServiceOfflineorTimeoutStartsD2004_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD11");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankcoreServiceTimeoutMsg());
	}


	@Test(testName = "Rejects the Debit as the bankcore service responsed with internal server error starts D2005", priority = 12)
	public void TC012RejectsDebitAsBankcoreServiceWithInternalServerErrorStartsD2005_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD12");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankCoreServiceInternalMsg());
	}


	@Test(testName = "Bank Rails Rejects the Debit as the amount is invalid Starts with D2102", priority = 13)
	public void TC013RejectsDebitAsAmountInvalidStartsD2102_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD13");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),objError.getAmountInvalidRejectMsg());
	}


	@Test(testName = "Bank Rails Rejects the Credit as the amount is invalid Starts with 222102", priority = 14)
	public void TC014RejectsCreditAsAmountInvalidStarts222102_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD14");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD05");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAmountInCreditorMsg());
	}


	@Test(testName = "Enquiry will fail with warning codes AccountHold Reject Starts 8888", priority = 15)
	public void TC015EnquiryFailWithWarningCodesAsAccHoldRejectStarts8888_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD15");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),objError.getAccWarningCodesMsg());
	}


	@Test(testName = "Simulator Fails to Connect Enrichment Service Starts 9990", priority = 16)
	public void TC016SimulatorFailsToConnectEnrichmentServiceStarts9990_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD16");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD06");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getSimulatorWaitTimeoutMsg());
	}


	@Test(testName = "Bankrails reject credit and debit enact saying invalid amount Starts A", priority = 17)
	public void TC017RejectsCreditDebitEnactInvalidAmountStartsA_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD17");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAmountInCreditorMsg());
	}


	@Test(testName = "Enquiry will fail with warning codes. Enact will fail Starts HOLD", priority = 18)
	public void TC018EnquiryFailWithWarningCodesStartsHOLD_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD18");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),objError.getAccHoldWarnMsg());
	}


	@Test(testName = "Enquiry will fail with warning codes. Enact will fail Starts HOLD", priority = 19)
	public void TC019EnquiryFailWithWarningCodesStartsHOLD_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD19");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),"Unable to fetch the quote details. 2012:Account Hold Warning");
	}


	@Test(testName = "BankRails Rejects Credit_bankcore service responsed with internal server error-->> Starts with:- C2005", priority = 20)
	public void TC020RejectsCredit_BankcoreServiceResponsedwithInternalServer_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD07");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}


	@Test(testName = "BankRails Rejects Credit_bankcore service is offline or timeout error occured before receiving response Starts with:- C2004", priority = 20)
	public void TC021RejectsCredit_BankCoreService_Offline_TimeoutBeforeReceivingResponse_StartsC2004_Depo() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD08");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}


	@Test(testName = "BankRails Rejects Credit_as unauthorized request sent to bankcore service-->> Starts with:- C2002", priority = 22)
	public void TC022RejectsCredit_asUnauthorizedRequestSentToBankCoreService_StartsC2002_Depo() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD09");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}


	@Test(testName = "BankRails Rejects Credit_as there is some error with bankcore setup-->> Starts with:- C1001", priority = 23)
	public void TC023RejectsCredit_asThereisSomeErrorwithBankcoreSetup_StartsC1001_Depo() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("BRD10");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueClick();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}


	@Test(testName = "Rejects both Debit & Credit as the account not open Starts B2013", priority = 24)
	public void TC024RejectsDebit_CreditAccountNotOpenStartsB2013_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD20");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),"Unable to fetch the quote details. 2013:Account not open");
	}

	@Test(testName = "Rejects Debit as the requested amount is less than minimum allowed Starts D2008", priority = 25)
	public void TC025RejectsDebit_RequestedAmountLessThanMinimumAllowedStartsD2008_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("BRD21");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		Assert.assertEquals(AddSenderDetails.getBankRailsErrorMsg(),"Unable to fetch the quote details. 2008:requested ammount is less than minimum allowed");
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