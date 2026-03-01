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
import dataReader.ErrorMessageData;
import dataReader.TestDataReader;
import dataReader.Utility;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;
import pages.Login;
import pages.ValidationPage;

public class LimitsChecks_FI extends Utility {

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
            if(! extentReports.getReport().getTestList().isEmpty())
            extentReports.flush();
        }
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");

	}

	
//===========================================================================================================================================
//--------------------------------->>> *** Mortgage Division *** <<<------------------------------------------------------------------------------------	
//===========================================================================================================================================
		
	
////	@Test(testName = "Minimum payout per transaction(Mortgage Division RTP)", priority = 1)
////	public void tc001RtpMinPayoutPerTxn() throws Exception {
////		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
////		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
////		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
////		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-001");
////		AddRecipientDetails.enterRecipientDetails(objReciDtls);
////		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
////		AddRecipientDetails.enterAmount(objReciDtls);
////		AddRecipientDetails.continueBtnClick();
////		AddSenderDetails.directSubmit();
////		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
////		ValidationPage.assertingAmlChecks(amlAssertMsg.getMinPayoutPerTxnReason());
////	}
//
//	@Test(testName = "Maximum payout per transaction(Mortgage Division RTP)", priority = 2)
//	public void tc002RtpMaxPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-002");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
//	}
//
//	@Test(testName = "Maximum payout per day(Mortgage Division RTP)", priority = 3)
//	public void tc003RtpMaxPayoutPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-003");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
//				
//	}
//
//	@Test(testName = "Maximum payout with in 24hrs(Mortgage Division RTP)", priority = 4)
//	public void tc004RtpMaxPayoutWithIn24hrs() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-004");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
//	}
//	
//	@Test(testName = "Maximum payout per week(Mortgage Division RTP)", priority = 5)
//	public void tc005RtpMaxPayoutPerWeek() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-005");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerWeekReason());
//	}
//
//	@Test(testName = "Maximum payout per month(Mortgage Division RTP)", priority = 6)
//	public void tc006RtpMaxPayoutPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-RTP-006");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
//	}
//
//	
//
////	@Test(testName = "Minimum payout per transaction(Mortgage Division FedNow)", priority = 7)
////	public void tc007FedNowMinPayoutPerTxn() throws Exception {
////		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
////		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
////		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
////		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-001");
////		AddRecipientDetails.enterRecipientDetails(objReciDtls);
////		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
////		AddRecipientDetails.enterAmount(objReciDtls);
////		AddRecipientDetails.continueBtnClick();
////		AddSenderDetails.directSubmit();
////		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
////		ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
////	}
//
//	@Test(testName = "Maximum payout per transaction(Mortgage Division FedNow)", priority = 8)
//	public void tc008FedNowMaxPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-002");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
//		}
//
//	@Test(testName = "Maximum payout per day(Mortgage Division FedNow)", priority = 9)
//	public void tc009FedNowMaxPayoutPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-003");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason());
//				
//	}
//	
//	@Test(testName = "Maximum payout with in 24hrs(Mortgage Division FedNow)", priority = 10)
//	public void tc010FedNowMaxPayoutWithIn24hrs() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-004");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
//	}
//
//	@Test(testName = "Maximum payout per week(Mortgage Division FedNow)", priority = 11)
//	public void tc011FedNowMaxPayoutPerWeek() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-005");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
//	}
//
//	@Test(testName = "Maximum payout per month(Mortgage Division FedNow)", priority = 12)
//	public void tc012FedNowMaxPayoutPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FN-006");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
//	}
//	
////	@Test(testName = "Minimum payout per transaction(Mortgage Division FedWire)", priority = 13)
////	public void tc013FedWireMinPayoutPerTxn() throws Exception {
////		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
////		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
////		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
////		AddSenderDetails.enterSenderDetails(objSenderDtls);
////		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-001");
////		AddRecipientDetails.enterRecipientDetails(objReciDtls);
////		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
////		AddRecipientDetails.enterAmount(objReciDtls);
////		AddRecipientDetails.continueBtnClick();
////		AddSenderDetails.directSubmit();
////		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
////		ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
////	}
//
//	@Test(testName = "Maximum payout per transaction(Mortgage Division FedWire)", priority = 14)
//	public void tc014FedWireMaxPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-002");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerTxnReason());
//	}
//
//	@Test(testName = "Maximum payout per day(Mortgage Division FedWire)", priority = 15)
//	public void tc015FedWireMaxPayoutPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-003");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason());
//				
//	}
//
//	@Test(testName = "Maximum payout with in 24hrs(Mortgage Division FedWire)", priority = 16)
//	public void tc016FedWireMaxPayoutWithIn24hrs() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-004");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason());
//	}
//	
//	@Test(testName = "Maximum payout per week(Mortgage Division FedWire)", priority = 17)
//	public void tc017FedWireMaxPayoutPerWeek() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-005");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerWeekReason());
//	}
//
//	@Test(testName = "Maximum payout per month(Mortgage Division FedWire)", priority = 18)
//	public void tc018FedWireMaxPayoutPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-MGD-FW-006");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason());
//	}
//
//	
	
//===========================================================================================================================================
//--------------------------------->>> *** AutoLoan Division *** <<<------------------------------------------------------------------------------------	
//===========================================================================================================================================
	
	
//	@Test(testName = "Minimum payout per transaction(AutoLoan Division RTP)", priority = 19)
//	public void tc019RtpMinPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-001");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason(),
//				amlAssertMsg.getMinPayoutPerTxnReasonCode());
//	}

	@Test(testName = "Maximum payout per transaction(AutoLoan Division RTP)", priority = 20)
	public void tc020RtpMaxPayoutPerTxn() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason(), amlAssertMsg.getMaxPayoutPerTxnReasonCode());
	}

	@Test(testName = "Maximum payout per day(AutoLoan Division RTP)", priority = 21)
	public void tc021RtpMaxPayoutPerDay() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason(), amlAssertMsg.getMaxPayoutPerDayReasonCode());
				
	}
	
	@Test(testName = "Maximum payout with in 24hrs(AutoLoan Division RTP)", priority = 22)
	public void tc022RtpMaxPayoutWithIn24hrs() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutWithIn24hrsReason(),  amlAssertMsg.getMaxPayoutWithIn24hrsReasonCode());
	}
	

	@Test(testName = "Maximum payout per week(AutoLoan Division RTP)", priority = 23)
	public void tc023RtpMaxPayoutPerWeek() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerWeekReason(), amlAssertMsg.getMaxPayoutPerWeekReasonCode());
	}
	
	@Test(testName = "Maximum payout per month(AutoLoan Division RTP)", priority = 24)
	public void tc024RtpMaxPayoutPerMonth() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks( amlAssertMsg.getMaxPayoutPerMonthReason(),  amlAssertMsg.getMaxPayoutPerMonthReasonCode());
	}

	

//	@Test(testName = "Minimum payout per transaction(AutoLoan Division FedNow)", priority = 25)
//	public void tc025FedNowMinPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-001");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
//	}

	@Test(testName = "Maximum payout per transaction(AutoLoan Division FedNow)", priority = 26)
	public void tc026FedNowMaxPayoutPerTxn() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason(),  amlAssertMsg.getMaxPayoutPerTxnReasonCode());
	}

	@Test(testName = "Maximum payout per day(AutoLoan Division FedNow)", priority = 27)
	public void tc027FedNowMaxPayoutPerDay() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason(), amlAssertMsg.getMaxPayoutPerDayReasonCode());
				
	}
	
	@Test(testName = "Maximum payout with in 24hrs(AutoLoan Division FedNow)", priority = 28)
	public void tc028FedNowMaxPayoutWithIn24hrs() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutWithIn24hrsReason(), amlAssertMsg.getMaxPayoutWithIn24hrsReasonCode());
	}

	@Test(testName = "Maximum payout per week(AutoLoan Division FedNow)", priority = 29)
	public void tc029FedNowMaxPayoutPerWeek() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerWeekReason(), amlAssertMsg.getMaxPayoutPerWeekReasonCode());
	}

	@Test(testName = "Maximum payout per month(AutoLoan Division FedNow)", priority = 30)
	public void tc030FedNowMaxPayoutPerMonth() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerMonthReason(), amlAssertMsg.getMaxPayoutPerMonthReasonCode());
	}
	
//	@Test(testName = "Minimum payout per transaction(AutoLoan Division FedWire)", priority = 31)
//	public void tc031FedWireMinPayoutPerTxn() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-001");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
//		ValidationPage.assertingAmlChecks( amlAssertMsg.getMinPayoutPerTxnReason());
//	}

	@Test(testName = "Maximum payout per transaction(AutoLoan Division FedWire)", priority = 32)
	public void tc032FedWireMaxPayoutPerTxn() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerTxnReason(), amlAssertMsg.getMaxPayoutPerTxnReasonCode());
	}

	@Test(testName = "Maximum payout per day(AutoLoan Division FedWire)", priority = 33)
	public void tc033FedWireMaxPayoutPerDay() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerDayReason(), amlAssertMsg.getMaxPayoutPerDayReasonCode());
				
	}

	@Test(testName = "Maximum payout with in 24hrs(AutoLoan Division FedWire)", priority = 34)
	public void tc034FedWireMaxPayoutWithIn24hrs() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-004");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutWithIn24hrsReason(), amlAssertMsg.getMaxPayoutWithIn24hrsReasonCode());
	}
	
	
	@Test(testName = "Maximum payout per week(AutoLoan Division FedWire)", priority = 35)
	public void tc035FedWireMaxPayoutPerWeek() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-005");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerWeekReason(), amlAssertMsg.getMaxPayoutPerWeekReasonCode());
	}

	@Test(testName = "Maximum payout per month(AutoLoan Division FedWire)", priority = 36)
	public void tc036FedWireMaxPayoutPerMonth() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-ALD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-006");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData amlAssertMsg = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingAmlChecks(amlAssertMsg.getMaxPayoutPerMonthReason(), amlAssertMsg.getMaxPayoutPerMonthReasonCode());
	}
	
	
	
	
	
//	
////============================================================================================================================================================================================================================	
//
////============================================================================================================================================================================================================================	
//	
//// Note :- Limit Rules 	(ie:- Number of payouts per day/ week/ month) are configured for Mortgage Division 
//
//	@Test(testName = "MaxNumberOfDebitsPerDay", priority = 37 , invocationCount = 4)
//	public void tc037RtpMaxNumberOfDebitsPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_DAY:Maximum no of debits in a day");
//	}
//	
//	@Test(testName = "MaxNumberOfDebitsPerWeek", priority = 38, invocationCount = 4)
//	public void tc038RtpMaxNumberOfDebitsPerWeek() throws Exception {
//	AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//	AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//	AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//	AddSenderDetails.enterSenderDetails(objSenderDtls);
//	AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//	AddRecipientDetails.enterRecipientDetails(objReciDtls);
//	AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//	AddRecipientDetails.enterAmount(objReciDtls);
//	AddRecipientDetails.continueBtnClick();
//	AddSenderDetails.directSubmit();
//	ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_WEEK:Maximum no of debits in a week");
//}
//	
//	@Test(testName = "MaxNumberOfDebitsPerMonth", priority = 39, invocationCount = 4)
//	public void tc039RtpMaxNumberOfDebitsPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-RTP");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_MONTH:Maximum no of debits in a month");
//
//	}
//	
//	
//	@Test(testName = "MaxNumberOfDebitsPerDay", priority = 40, invocationCount = 4)
//	public void tc040FedNowMaxNumberOfDebitsPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_DAY:Maximum no of debits in a day");
//	
//		
//	}
//	
//	@Test(testName = "MaxNumberOfDebitsPer Week", priority = 41, invocationCount = 4)
//	public void tc041FedNowMaxNumberOfDebitsPerWeek() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_WEEK:Maximum no of debits in a week");
//	}
//	
//	@Test(testName = "MaxNumberOfDebitsPer Month", priority = 42, invocationCount = 4)
//	public void tc042FedNowMaxNumberOfDebitsPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FN");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_MONTH:Maximum no of debits in a month");
//	}
//	
//	
//	@Test(testName = "Max Number Of Debits Per Day", priority = 43, invocationCount = 4)
//	public void tc043FedWireMaxNumberOfDebitsPerDay() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_DAY:Maximum no of debits in a day");
//	}
//	
//	@Test(testName = "Max Number Of Debits Per Week", priority = 44, invocationCount = 4)
//	public void tc044FedWireMaxNumberOfDebitsPerWeek() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_WEEK:Maximum no of debits in a week");
//	}
//	
//	@Test(testName = "Max Number Of Debits Per Month", priority = 45, invocationCount = 4)
//	public void tc045FedWireMaxNumberOfDebitsPerMonth() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("LC-MGD-FW");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("LC-007");
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.directSubmit();
//		ValidationPage.assertingAmlChecks("MAX_NO_OF_DEBITS_PER_MONTH:Maximum no of debits in a month");
//	}
//	
//	
//	

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
	         Utility.closeBrowser(WD);       }
	}

	@AfterClass
	public void endSuite() throws Exception {
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}

}
