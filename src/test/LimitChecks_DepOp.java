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

public class LimitChecks_DepOp extends Utility{
	
	
	
	static Login objLogin;
	public static  ExtentSparkReporter extentSparkReporter;
	  public static  ExtentReports extentReports;
	  public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
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

	
//===========================================================================================================================================
//--------------------------------->>> *** RetailTransfer Division *** <<<------------------------------------------------------------------------------------	
//===========================================================================================================================================
			
		
	@Test(testName = "Minimum payout per transaction(RetailTransfer Division RTP)", priority = 1)
	public void tc001RtpMinPayoutPerTxn_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-RTP-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(RetailTransfer Division RTP)", priority = 2)
	public void tc002RtpMaxPayoutPerTxn_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-RTP-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per day(RetailTransfer Division RTP)", priority = 3)
	public void tc003RtpMaxPayoutPerDay_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
				
	}

	@Test(testName = "Maximum payout with in 24hrs(RetailTransfer Division RTP)", priority = 4)
	public void tc004RtpMaxPayoutWithIn24hrs_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-RTP-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}
	
	@Test(testName = "Maximum payout per week(RetailTransfer Division RTP)", priority = 5)
	public void tc005RtpMaxPayoutPerWeek_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-RTP-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerWeekReason());
	}

	@Test(testName = "Maximum payout per month(RetailTransfer Division RTP)", priority = 6)
	public void tc006RtpMaxPayoutPerMonth_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-RTP-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
	}

	

	@Test(testName = "Minimum payout per transaction(RetailTransfer Division FedNow)", priority = 7)
	public void tc007FedNowMinPayoutPerTxn_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FN-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(RetailTransfer Division FedNow)", priority = 8)
	public void tc008FedNowMaxPayoutPerTxn_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FN-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
		}

	@Test(testName = "Maximum payout per day(RetailTransfer Division FedNow)", priority = 9)
	public void tc009FedNowMaxPayoutPerDay_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
				
	}
	
	@Test(testName = "Maximum payout with in 24hrs(RetailTransfer Division FedNow)", priority = 10)
	public void tc010FedNowMaxPayoutWithIn24hrs_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FN-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}

	@Test(testName = "Maximum payout per week(RetailTransfer Division FedNow)", priority = 11)
	public void tc011FedNowMaxPayoutPerWeek_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FN-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
	}

	@Test(testName = "Maximum payout per month(RetailTransfer Division FedNow)", priority = 12)
	public void tc012FedNowMaxPayoutPerMonth_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FN-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
	}
	
	@Test(testName = "Minimum payout per transaction(RetailTransfer Division FedWire)", priority = 13)
	public void tc013FedWireMinPayoutPerTxn_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(RetailTransfer Division FedWire)", priority = 14)
	public void tc014FedWireMaxPayoutPerTxn_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per day(RetailTransfer Division FedWire)", priority = 15)
	public void tc015FedWireMaxPayoutPerDay_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
				
	}

	@Test(testName = "Maximum payout with in 24hrs(RetailTransfer Division FedWire)", priority = 16)
	public void tc016FedWireMaxPayoutWithIn24hrs_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}
	
	@Test(testName = "Maximum payout per week(RetailTransfer Division FedWire)", priority = 17)
	public void tc017FedWireMaxPayoutPerWeek_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
	}

	@Test(testName = "Maximum payout per month(RetailTransfer Division FedWire)", priority = 18)
	public void tc018FedWireMaxPayoutPerMonth_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-RTD-FW-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
	}

		
		
//===========================================================================================================================================
//--------------------------------->>> *** BusinessTransfer Division *** <<<------------------------------------------------------------------------------------	
//===========================================================================================================================================
		
		
	@Test(testName = "Minimum payout per transaction(BusinessTransfer RTP)", priority = 19)
	public void tc019RtpMinPayoutPerTxn_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(BusinessTransfer RTP)", priority = 20)
	public void tc020RtpMaxPayoutPerTxn_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per day(BusinessTransfer RTP)", priority = 21)
	public void tc021RtpMaxPayoutPerDay_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
				
	}
	
	@Test(testName = "Maximum payout with in 24hrs(BusinessTransfer RTP)", priority = 22)
	public void tc022RtpMaxPayoutWithIn24hrs_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}
	

	@Test(testName = "Maximum payout per week(BusinessTransfer RTP)", priority = 23)
	public void tc023RtpMaxPayoutPerWeek_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
	}
	
	@Test(testName = "Maximum payout per month(BusinessTransfer RTP)", priority = 24)
	public void tc024RtpMaxPayoutPerMonth_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-RTP-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
	}

	
	
	@Test(testName = "Minimum payout per transaction(BusinessTransfer FedNow)", priority = 25)
	public void tc025FedNowMinPayoutPerTxn_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(BusinessTransfer FedNow)", priority = 26)
	public void tc026FedNowMaxPayoutPerTxn_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per day(BusinessTransfer FedNow)", priority = 27)
	public void tc027FedNowMaxPayoutPerDay_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
				
	}
	
	@Test(testName = "Maximum payout with in 24hrs(BusinessTransfer FedNow)", priority = 28)
	public void tc028FedNowMaxPayoutWithIn24hrs_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}

	@Test(testName = "Maximum payout per week(BusinessTransfer FedNow)", priority = 29)
	public void tc029FedNowMaxPayoutPerWeek_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
	}

	@Test(testName = "Maximum payout per month(BusinessTransfer FedNow)", priority = 30)
	public void tc030FedNowMaxPayoutPerMonth_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FN-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.endToEnd(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
	}
	
	@Test(testName = "Minimum payout per transaction(BusinessTransfer FedWire)", priority = 31)
	public void tc031FedWireMinPayoutPerTxn_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per transaction(BusinessTransfer FedWire)", priority = 32)
	public void tc032FedWireMaxPayoutPerTxn_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
	}

	@Test(testName = "Maximum payout per day(BusinessTransfer FedWire)", priority = 33)
	public void tc033FedWireMaxPayoutPerDay_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
				
	}

	@Test(testName = "Maximum payout with in 24hrs(BusinessTransfer FedWire)", priority = 34)
	public void tc034FedWireMaxPayoutWithIn24hrs_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
	}
	
	
	@Test(testName = "Maximum payout per week(BusinessTransfer FedWire)", priority = 35)
	public void tc035FedWireMaxPayoutPerWeek_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
	}

	@Test(testName = "Maximum payout per month(BusinessTransfer FedWire)", priority = 36)
	public void tc036FedWireMaxPayoutPerMonth_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-BTD-FW-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.currentBalanceDisplay_DepOp();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		//ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		//ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
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
		        startSuite();  
		        Utility.closeBrowser(WD);     }
		}

		@AfterClass
		public void endSuite() throws Exception {
			AddSenderDetails.logOff();
			Utility.closeBrowser(WD);
		}
}