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

public class AddRecipients_DepOp extends Utility {

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
		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
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

//============================================================================================================================================================		
// *** Note:- Here in this class we are adding the New_Recipient in every test case.....and in the same case we are deleting that New_Recipient ***	
	
//=========================>> Retails Transfer Division =======================================================================================================	
	
	@Test(testName = "Adding New Recipient-->> RTP-Network, RetailTransferDivision(Individual)", priority = 1)
	public void tc001AddNewRecipientAndThenDeletingRTPIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedNow-Network, RetailTransferDivision(Individual)", priority = 2)
	public void tc002AddNewRecipientAndThenDeletingFedNowIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FN");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, RetailTransferDivision(Individual)", priority = 3)
	public void tc003AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> RTP-Network, RetailTransferDivision(Business)", priority = 4)
	public void tc004AddNewRecipientAndThenDeletingRTPIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedNow-Network, RetailTransferDivision(Business)", priority = 5)
	public void tc005AddNewRecipientAndThenDeletingFedNowIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-FN");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, RetailTransferDivision(Business)", priority = 6)
	public void tc006AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, RetailTransferDivision(ForeignIndividual)", priority = 7)
	public void tc007AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, RetailTransferDivision(ForeignBusiness)", priority = 8)
	public void tc008AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	
	
	
	
//======================================>> BusinessTransfer Division <<======================================================================================
	
	
	@Test(testName = "Adding New Recipient-->> RTP-Network, BusinessTransferDivision(Individual)", priority = 9)
	public void tc009AddNewRecipientAndThenDeletingRTPIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}
	
	@Test(testName = "Adding New Recipient-->> FedNow-Network, BusinessTransferDivision(Individual)", priority = 10)
	public void tc010AddNewRecipientAndThenDeletingFedNowIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FN");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, BusinessTransferDivision(Individual)", priority = 11)
	public void tc011AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> RTP-Network, BusinessTransferDivision(Business)", priority = 12)
	public void tc012AddNewRecipientAndThenDeletingRTPIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedNow-Network, BusinessTransferDivision(Business)", priority = 13)
	public void tc013AddNewRecipientAndThenDeletingFedNowIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-FN");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, BusinessTransferDivision(Business)", priority = 14)
	public void tc014AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.recipBankDetails(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, BusinessTransferDivision(ForeignIndividual)", priority = 15)
	public void tc015AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_IND-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Adding New Recipient-->> FedWire-Network, BusinessTransferDivision(ForeignBusiness)", priority = 16)
	public void tc016AddNewRecipientAndThenDeletingFedWireIndividual_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objRecipDtls = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-FW");
		AddRecipientDetails.recipDetails(objRecipDtls);
		AddRecipientDetails.fwBIScodeIBAN(objRecipDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objRecipDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}
	
	
	
	@Test(testName = "Adding New Recipient and Delete-->> XBWire --> Argentina", priority = 17)
	public void tc017AddNewRecipientAndThenDeleting_XBWire_Argentina() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-XB1");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.addXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
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
		
		String testCaseStatus = AddSenderDetails.closeBtn2();
		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD); 
			startSuite();               }
			
	}

	@AfterClass
	public void endSuite() throws Exception {
		
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}

}
