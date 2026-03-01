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

public class AddRecipients_FI extends Utility {
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


	@Test(testName = "Add New Recipient with RTP Network(Individual)", priority = 1)
	public void TC001AddNewRecipientRTPIndividual() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC009");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC005");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	
	@Test(testName = "Add New Recipient with FedNow Network(Individual)", priority = 2)
	public void TC002AddNewRecipientFedNowIndividual() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC006");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "Add New Recipient with FedWire Network(Individual)", priority = 3)
	public void TC003AddNewRecipientFedWireIndividual() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC007");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "Add New Recipient with RTP Network(Business)", priority = 4)
	public void TC004AddNewRecipientRTPBusiness() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC009");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC008");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	
	@Test(testName = "Add New Recipient with FedNow Network(Business)", priority = 5)
	public void TC005AddNewRecipientFedNowBusiness() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC009");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "Add New Recipient with FedWire Network(Business)", priority = 6)
	public void TC006AddNewRecipientFedWireBusiness() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC010");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	
	//Suspend, Delete and Revoke Cases
	
	@Test(testName = "Suspend Exisiting Recipient", priority = 7)
	public void TC007SuspendExistingRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC010");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.suspendRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNum);
		AddRecipientDetails.SuspendSuccessMsg();
	}
	
	@Test(testName = "Revoke Exisiting Recipient", priority = 8)
	public void TC008RevokeExistingRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC009");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.revokeRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNum);
		AddRecipientDetails.RevokeSuccessMsg();
	}
	
	@Test(testName = "Delete Exisiting Recipient", priority = 9)
	public void TC009DeleteExistingRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC008");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.deleteRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNum);
		AddRecipientDetails.approvingDelete();
	}
	

	@Test(testName = "Decline Suspended State Recipient", priority = 10)
	public void TC010DeclineSuspendStateRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC007");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.suspendRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient(PhoneNum);
		AddRecipientDetails.declineSuspend();
	}
	
	@Test(testName = "Decline Revoke State Recipient", priority = 11)
	public void TC011DeclineRevokeStateRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC006");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.revokeRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient(PhoneNum);
		AddRecipientDetails.declineRevoke();
	}
	
	@Test(testName = "Decline Delete state Recipient", priority = 12)
	public void TC012DeclineDeleteStateRecipient() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC005");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		
		AddRecipientDetails.selectExistRecipient(objReciDtls);
		AddRecipientDetails.deleteRecipient();
		String PhoneNum=AddRecipientDetails.getPhoneNo();
		AddSenderDetails.logOff();
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitDecliningRecipient(PhoneNum);
		AddRecipientDetails.declineDelete();
	}
	
	@Test(testName = "Add New Recipient with All Networks(Individual)", priority = 13)
	public void TC013AddNewRecipientWithRTP_FedNow_FedWire_Individual() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC007");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.selectPaymentNetworks();
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "Add New Recipient with All Networks(Business)", priority = 14)
	public void TC014AddNewRecipientWithRTP_FedNow_FedWire_Business() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC010");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		String PhoneNo=AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.selectPaymentNetworks();
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
		AddRecipientDetails.submitBtn();
		AddSenderDetails.logOff();	
		//Approve with another user
		objLogin = TestDataReader.loadLogin("OPAY_002");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddRecipientDetails.submitApprovingRecipient(PhoneNo);
		AddRecipientDetails.successApprovalMsg();
	}
	
	@Test(testName = "Add New Recipient FedWire(FI)", priority = 15)
	public void TC015AddNewRecipientFedWire_FI() throws Exception {
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC012");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Click_Recipient_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddRecipientDetails.enterNewRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_No_travelRuleMethod();
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

