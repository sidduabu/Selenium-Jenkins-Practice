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

public class BankRails_FIPayments extends Utility {
	
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
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
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
            if(!extentReports.getReport().getTestList().isEmpty())
            extentReports.flush();    
        }
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");

	}
	
	
//  ======>>  Account doesnot exist <<==========
	
	@Test(priority =01, testName = "BankRails RejectsDebit_AccDoesNotExists--->> Starts with:-D2006 ")
	public void tc001BRrejectsDebit_AccDoesNotExists() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR001");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getAccountNotExistText(),objError.getAccNumNotExistMsg());
	}
	
	@Test(priority =02, testName = "BankRails RejectsDebit_AccDoesNotExists--->> Starts with:-D2101 ")
	public void tc002BRrejectsDebit_AccDoesNotExists() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR002");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getAccountNotExistText(),objError.getAccNumNotExistMsg());
	}
	
	
//  ======>>  Account not opened <<==========
	
	@Test(priority =03, testName = "BankRails Rejects Debit and Credit_Account not opened-->> Starts with:-B2013 ")
	public void tc003BRrejectsDebitAndCredit_AccNotOpened() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR003");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-03");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonDesc(),objError.getAccountNotopenMsg());
	}
	
	
//  ======>>  Account number is invalid <<==========
	
	@Test(priority =04, testName = "BankRails Rejects Credit_AccNum is invalid-->> Starts with:- C2101")
	public void tc004BRrejectsCredit_AccNumInvalid() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR004");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-04");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}	
	
	
//  ======>>  Account is having insufficient balance <<==========
	
	@Test(priority =05, testName = "BankRailsRejectsDebit_Account is having InsufficientBalance--->> Starts with:-D2007 ")
	public void tc005BRrejectsDebit_InsufficientBalance() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR005");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-05");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndInProcessMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getInsufficientBalance1());
	}	
	
	@Test(priority =06, testName = "BankRailsRejectsPayment_Account is having InsufficientBalance-->>Starts with:-INSB ")
	public void tc006BRrejectsPayment_InsufficientBalance() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR006");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-06");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInsufficientBalance2());
		
	}		
	
	
//  ======>>  Requested amount is less than the minimum allowed <<==========
	
	@Test(priority =07, testName = "BankRails Rejects Debit_BankRailsRejectsDebit_RequestedAmount is less than the minimum allowed-->>Starts with:-D2008 ")
	public void tc007BRrejectsDebit_RequestedAmountLessThanMinimumAllowed() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR007");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-07");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getReqAmountLessThanMinMsg1());
	}	
	
	@Test(priority =8, testName = "BankRails Rejects Credit_BankRailsRejectsCredit_RequestedAmount is less than the minimum allowed-->>Starts with:-222008 ")
	public void tc008BRrejectsCredit_RequestedAmountLessThanMinimumAllowed() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR008");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-08");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getReqAmountLessThanMinMsg2());
		
	}		
	
//  ======>>  Some error with bankcore setup <<==========
	
	@Test(priority= 9, testName = "BankRailsRejectsDebit_Some error with bankcore setup-->>Starts with:-D1001 ")
	public void tc009BRrejectsDebit_SomeErrorWithBankCoreSetup() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR009");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-09");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankCoreSetUpErrMsg());
		
	}	
	
//  ======>>  Unauthorized request sent to bankcore service <<==========
	
	@Test(priority= 10, testName = "BankRailsRejectsDebit_Unauthorized request sent to Bankcore Service-->>Starts with:-D2002 ")
	public void tc010BRrejectsDebit_UnauthorizedRequestSentToBankcoreService() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR010");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-10");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getUnAuthorisedReqSentMsg());
		
	}		
	
//  ======>>  Bankcore service is offline or timeout <<==========
	
	@Test(priority= 11, testName = "BankRailsRejectsDebit_Bankcore service is offline or timeout-->>Starts with:-D2004 ")
	public void tc011BRrejectsDebit_BankcoreServiceOfflineOrTimeout() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR011");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-11");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankcoreServiceTimeoutMsg());
	}	
	
//  ======>>  Bankcore service responsed with internal server error <<==========
	
	@Test(priority= 12, testName = "BankRailsRejectsDebit_Bankcore service responsed with internal server error-->>Starts with:-D2005 ")
	public void tc012BRrejectsDebit_BankcoreServiceResponsedWithInternalServerError() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR012");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-12");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getBankCoreServiceInternalMsg());
	}		
	
//  ======>>  Invalid Amount <<==========
	
	@Test(priority= 13, testName = "BankRailsRejectsDebit_Invalid Amount-->>Starts with:-D2102 ")
	public void tc013BRrejectsDebit_InvalidAmount() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR013");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-13");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAmountInDebitorMsg());
		
	}		
	
	@Test(priority= 14, testName = "BankRailsRejectsCredit_Invalid Amount-->>Starts with:-222102 ")
	public void tc014BRrejectsCredit_InvalidAmount() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR014");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-14");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAmountInCreditorMsg());
		
	}		
		
//  ======>>       Account Hold Reject       <<==========
	
	@Test(priority= 15, testName = "BankRailsRejectsDebit_Account Hold Reject-->>Starts with:-8888 ")
	public void tc015BRrejectsDebit_AccountHoldReject() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR015");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-15");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getDepoAccRejectWarningMsg2());
	}		
	
//  ======>>  Simulator waits for millisecond  <<==========
	@Test(priority= 16, testName = "Simulator waits for milliseconds -->>Starts with:- 9990632723 ")
	public void tc016SimulatorWaitsforMilliseconds() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR016");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-16");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getSimulatorWaitTimeoutMsg());
		
	}			
	
	
	@Test(priority= 17, testName = "Enquiry will fail with warning codes. Enact will fail Starts HOLD")
	public void tc017EnquirywillfailwithwarningcodesEnactwillFailStarts_HOLD() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR017");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-16");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),"2012:Account Hold Warning");
	}	
	

	@Test(priority= 18, testName = "Bankrails reject credit and debit enact saying invalid amount Starts A")
	public void tc018RejectsCreditDebitEnactInvalidAmountStartsA_DepoOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR018");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-16");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAmountInCreditorMsg());
	}
	
	@Test(priority =19, testName = "BankRails Rejects Credit_bankcore service responsed with internal server error-->> Starts with:- C2005")
	public void tc019BRrejectsCredit_BankcoreServiceResponsedwithInternalServer_StartsC2005_FI() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR004");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-17");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}	
	
	
	@Test(priority =20, testName = "BankRails Rejects Credit_bankcore service is offline or timeout error occured before receiving response Starts with:- C2004")
	public void tc020RejectsCredit_BankCoreService_Offline_TimeoutBeforeReceivingResponse_StartsC2004_FI() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR004");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-18");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}	
	
	
	@Test(priority =21, testName = "BankRails Rejects Credit_as unauthorized request sent to bankcore service-->> Starts with:- C2002")
	public void tc021BRrejectsCredit_asUnauthorizedRequestSentToBankCoreService_StartsC2002_FI() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR004");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-19");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
	}	
	
	
	@Test(priority =22, testName = "BankRails Rejects Credit_as there is some error with bankcore setup-->> Starts with:- C1001")
	public void tc022BRrejectsCredit_asThereisSomeErrorwithBankcoreSetup_StartsC1001_FI() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BR004");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BR-20");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndRejectedMsg();
		BankRails objError = TestDataReader.loadBankRailsDetails();
		Assert.assertEquals(BankRails.getRejectReasonsMsg(),objError.getInvalidAccount());
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
			startSuite();              }
	}

	@AfterClass
	public void endSuite() throws Exception {
		
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}

}
