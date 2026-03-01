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

public class Dupaco_DepOp_Transfers_And_AddRecipient extends Utility {
	
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

	
// Info:- From testCase 01 to 06 (Transfers with NewRecipient---> CurrentDate)
//	      From testCase 07 to 12 (Transfers with NewRecipient---> FutureDate)
//        From testCase 13 to 18 (Transfers with ExistingRecipient---> CurrentDate)
//        From testCase 19 to 24 (Transfers with ExistingRecipient---> FutureDate)
//        From testCase 25 to 28 (Adding NewRecipient---> Individual, Business, ForeignIndividual, ForeignBusiness types)
	
	
		
//===================>> Transfers With NewRecipient (Current Date) <<=========================================================================================		

		@Test(testName = "DupacoTransfers_To_NewIndividualRecipient_CurrentDate_DirectSubmit", priority = 1)
		public void tc001DupacoTransfers_To_NewIndividualRecipient_CurrentDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.directSubmitMsg();
		}
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_SubmitFor Approval_RecipientType_Individual", priority = 2)
		public void tc002DupacoTransfers_To_NewIndividualRecipient_CurrentDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingPayment();
		}
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_Declinetransfer_RecipientType_Individual", priority = 3)
		public void tc003DupacoTransfers_To_NewIndividualRecipient_CurrentDate_CurrentDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}
		
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DirectSubmit_RecipientType_ForeignIndividual", priority = 4)
		public void tc004DupacoTransfers_To_NewForeignIndividualRecipient_CurrentDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.directSubmitMsg();
		}
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_SubmitFor Approval_RecipientType_ForeignIndividual", priority = 5  )
		public void tc005DupacoTransfers_To_NewForeignIndividualRecipient_CurrentDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingPayment();		}
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DeclinePayment_RecipientType_ForeignIndividual", priority = 6)
		public void tc006DupacoTransfers_To_NewForeignIndividualRecipient_CurrentDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}
		
//===================>> Transfers With NewRecipient (Future Date) <<=========================================================================================		
		
		@Test(testName = "DupacoTransfers_To_NewIndividualRecipient_FutureDate_DirectSubmit", priority = 7)
		public void tc007DupacoTransfers_To_NewIndividualRecipient_FutureDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}
		
		
		@Test(testName = "DupacoTransfers_To_NewIndividualRecipient_FutureDate_SubmitforApproval", priority = 8)
		public void tc008DupacoTransfers_To_NewIndividualRecipient_FutureDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();		}
		
		@Test(testName = "DupacoTransfers_To_NewIndividualRecipient_FutureDate_DeclineTransfer", priority=9)
		public void tc009DupacoTransfers_To_NewIndividualRecipient_FutureDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}
		
		
		
		@Test(testName = "DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_DirectSubmit", priority=10)
		public void tc010DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}
		
		
		@Test(testName = "DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_SubmitforApproval", priority =11)
		public void tc011DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();		}
		
		@Test(testName = "DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_DeclineTransfer", priority = 12)
		public void tc012DupacoTransfers_To_NewForeignIndividualRecipient_FutureDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-002");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.enterRecipientDetails_ForeignIndividual(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}
				
		
//==========================>> Transfers With Existing Recipient (Current Date) <<==========================================================		
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DirectSubmit_RecipientType_Individual", priority = 13)
		public void tc013DupacoTransfers_To_ExistingIndividualRecipient_CurrentDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.directSubmitMsg();
		}
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_SubmitFor Approval_RecipientType_Individual", priority = 14)
		public void tc014DupacoTransfers_To_ExistingIndividualRecipient_CurrentDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingPayment();		}
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DeclineTransfer_RecipientType_Individual", priority = 15)
		public void tc015DupacoTransfers_To_ExistingIndividualRecipient_CurrentDate_DeclineTarnsfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}

		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DirectSubmit_RecipientType_ForeignIndividual", priority = 16)
		public void tc016DupacoTransfers_To_ExistingForeignIndividualRecipient_CurrentDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.directSubmitMsg();
		}
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_SubmitFor Approval_RecipientType_ForeignIndividual", priority = 17)
		public void tc017DupacoTransfers_To_ExistingForeignIndividualRecipient_CurrentDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingPayment();		}
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DeclinePayment_RecipientType_ForeignIndividual", priority = 18)
		public void tc018DupacoTransfers_To_ExistingForeignIndividualRecipient_CurrentDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}

		
//===================>> Transfers With Existing Recipient (Future Date) <<=========================================================================================		

		
		
		
		@Test(testName = "DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_DirectSubmit", priority = 19)
		public void tc019DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}
		
		
		@Test(testName = "DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_SubmitforApproval", priority = 20)
		public void tc020DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
			//Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();		}
		
		@Test(testName = "DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_DeclineTarnsfer", priority = 21)
		public void tc021DupacoTransfers_To_ExistingIndividualRecipient_FutureDate_DeclineTarnsfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientIndividual_Dupaco_DepOp("Automation_Individual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}

		
		
		@Test(testName = "DupacoTransfers_To_ExistingForeignIndividualRecipient_FutureDate_DirectSubmit", priority = 22)
		public void tc022DupacoTransfers_To_ExistingForeignIndividualRecipient_FutureDate_DirectSubmit() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.futureDatePymtSubmitMsg();
		}
		
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_SubmitFor Approval_RecipientType_ForeignIndividual", priority = 23)
		public void tc023DupacoTransfers_To_ExistingForeignIndividualRecipient_FutureDate_SubmitforApproval() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Approving with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.approvingFuturePymt();		}
		
		@Test(testName = "Dupaco_Transfers_DepOp_RTD_DeclinePayment_RecipientType_ForeignIndividual", priority = 24)
		public void tc024DupacoTransfers_To_ExistingForeignIndividualRecipient_FutureDate_DeclineTransfer() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DUPC-PMT-RTD-001");
			AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("DUPC-PMT-RTD-001");
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork_DupacoClient(objSenderDtls);
			AddSenderDetails.enterDepoSenderDetails(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_Pmts();
			AddSenderDetails.enterChannelDetails_RecipientType(objSenderDtls);
			AddRecipientDetails.selectingExistingRecipientForeignIndividual_Dupaco_DepOp("Automation_ForeignIndividual");
			AddRecipientDetails.enterAmount(objReciDtls);
			AddRecipientDetails.enterFeeAmount();
			AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
			AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
			AddRecipientDetails.totalAmountDisplayed();
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddSenderDetails.submitForApproval();
			String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
			AddSenderDetails.logOff();
//			Declining the payment with another user
			objLogin = TestDataReader.loadLogin("Login-02");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Transfers_Link_DepOp();
			AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
			AddSenderDetails.searchConfirmationNo(ConfirmationNo);
			AddSenderDetails.declinePayment();		}
		
		
	
	
//=========================================================================================================================================================================================================	
//---------------------------------->> DepOp - Add Recipient (Dupaco_Client-FedWire) << ---------------------------------------------------------------------------------------------------------------------------------------------------	
//=========================================================================================================================================================================================================	
				
	
	
		@Test(testName = "Dupaco_AddNewRecipient_RTD_DepOp_Individual", priority = 25)
		public void tc025Dupaco_AddingNewIndividualRecipient_MGD_DepOp() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Recipient_Link_DepOp();
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPCO-RC-RTD");
			AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
			AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
			AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_AddRecipient();
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPCO-AddRC-RTD-001");
			AddRecipientDetails.recipDetails(objReciDtls);
			AddRecipientDetails.recipBankDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddRecipientDetails.submitBtn2();
			AddRecipientDetails.successApprovalMsg();
			AddRecipientDetails.deletingTheRecipient001("Deleted");

		}
		
		@Test(testName = "Dupaco_AddNewRecipient_RTD_DepOp_Business", priority = 26)
		public void tc026Dupaco_AddingNewBusinessRecipient_MGD_DepOp() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Recipient_Link_DepOp();
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPCO-RC-RTD");
			AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
			AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
			AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_AddRecipient();
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPCO-AddRC-RTD-002");
			AddRecipientDetails.recipDetails(objReciDtls);
			AddRecipientDetails.recipBankDetails(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddRecipientDetails.submitBtn2();
			AddRecipientDetails.successApprovalMsg();
			AddRecipientDetails.deletingTheRecipient001("Deleted");

		}
		
		@Test(testName = "Dupaco_AddNewRecipient_RTD_DepOp_ForeignIndividual", priority = 27)
		public void tc027Dupaco_AddingNewForeignIndividualRecipient_MGD_DepOp() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Recipient_Link_DepOp();
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPCO-RC-RTD");
			AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
			AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
			AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_AddRecipient();
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPCO-AddRC-RTD-003");
			AddRecipientDetails.recipDetails(objReciDtls);
			AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddRecipientDetails.submitBtn2();
			AddRecipientDetails.successApprovalMsg();
			AddRecipientDetails.deletingTheRecipient001("Deleted");

		}

		@Test(testName = "Dupaco_AddNewRecipient_RTD_DepOp_ForeignBusiness", priority = 28)
		public void tc028Dupaco_AddingNewForeignBusinessRecipient_MGD_DepOp() throws Exception {
			objLogin = TestDataReader.loadLogin("Login-01");
			Login.loginTest(objLogin);
			AddSenderDetails.selectCustomerSupport();
			AddSenderDetails.Clik_Recipient_Link_DepOp();
			AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DPCO-RC-RTD");
			AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
			AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
			AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
			AddSenderDetails.dupaco_SelectingSenderAccType_DepOp_AddRecipient();
			AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("DPCO-AddRC-RTD-004");
			AddRecipientDetails.recipDetails(objReciDtls);
			AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
			AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
			AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
			AddRecipientDetails.submitBtn2();
			AddRecipientDetails.successApprovalMsg();
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
	        startSuite();              }

	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}

}
