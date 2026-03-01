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

import dataReader.ErrorMessageData;
import dataReader.TestDataReader;
import dataReader.Utility;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;
import pages.BankRails;
import pages.Login;

public class Risk_OFAC_Cases extends Utility{

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
		AddSenderDetails.Clik_Transfers_Link_DepOp();
	}
	
	
	
//================================= CustomerSupport Transfers ========================================================================================================
	
	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
	public void tc001CheckOFACWithNameoftheRecipient_RTP_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-RTD-RTP");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndInProcessMsg();
		ErrorMessageData objError = TestDataReader.loadErrorMsg();
		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
	}
	
	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
	public void tc002CheckOFACWithNameoftheRecipient_FedNow_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-RTD-FN");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndInProcessMsg();
		ErrorMessageData objError = TestDataReader.loadErrorMsg();
		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
	}
	
	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
	public void tc003CheckOFACWithNameoftheRecipient_FedWire_DepOp() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-RTD-FW");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitAndInProcessMsg();
		ErrorMessageData objError = TestDataReader.loadErrorMsg();
		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
	}
	
//	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
//	public void TC004CheckOFACWithNameoftheRecipient_DepOp_BTD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-BTD-RTP");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterFeeAmount();
//		AddRecipientDetails.totalAmountDisplayed();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.submitAndInProcessMsg();
//		ErrorMessageData objError = TestDataReader.loadErrorMsg();
//		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
//	}
//	
//	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
//	public void TC005CheckOFACWithNameoftheRecipient_DepOp_BTD_FN() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-BTD-FN");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterFeeAmount();
//		AddRecipientDetails.totalAmountDisplayed();
//		AddRecipientDetails.continueBtnClick();
//		AddSenderDetails.submitAndInProcessMsg();
//		ErrorMessageData objError = TestDataReader.loadErrorMsg();
//		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
//	}
//	
//	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 0)
//	public void TC006CheckOFACWithNameoftheRecipient_DepOp_BTD_FW() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("OFAC-BTD-FW");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC-01");
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterAddressDetailsManually(objReciDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		AddRecipientDetails.totalAmountDisplayed();
//		AddSenderDetails.paymentQuestionnaire(objSenderDtls);
//		AddRecipientDetails.continueClick();
//		AddSenderDetails.submitAndInProcessMsg();
//		ErrorMessageData objError = TestDataReader.loadErrorMsg();
//		Assert.assertEquals(BankRails.getReasonCodeMsg(),objError.getOFACCheckMsg());		
//	}
//

	
//===================================>> FI - Payment Manager <<=====================================================================================
	
	
	
//	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 1)
//	public void TC001_OFAC_With_RecipientName_FI_MGD_RTP() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI002", getRTPTestDataPath());
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC01", getRTPTestDataPath());
//		AddSenderDetails.Click_Payments_Link_FI();
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls); 
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		String InstructionId = AddSenderDetails.directSubmitMsg();
//		ValidationPage.asserting_PmtStatus_InstruIDClick_RiskCheckStatus();
//		TestDataReader.updateInstructionID("TC001", InstructionId, "RTP_Payments");
//	}
//	
	
//	@Test(testName = "Check OFAC Rule with Name of Recipient", priority = 1)
//    public void TC001_OFAC_With_RecipientName_FI_MGD_FedNow() throws Exception {
//        AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI002", getFedNowTestDataPath());
//        AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC01",getFedNowTestDataPath());
//        AddSenderDetails.Click_Payments_Link_FI();
//        AddSenderDetails.select_DivRoutingNum(objSenderDtls); 
//        AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//        AddSenderDetails.enterSenderDetails(objSenderDtls);
//        AddRecipientDetails.enterRecipientDetails(objReciDtls);
//        AddRecipientDetails.enterAmount(objReciDtls);
//        AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
//        AddRecipientDetails.continueBtnClick();
//        String InstructionId = AddSenderDetails.directSubmitMsg();
//        ValidationPage.asserting_PmtStatus_InstruIDClick_RiskCheckStatus();
//        TestDataReader.updateInstructionID("TC001", InstructionId, "FedNow_Payments");
//    }
	
//	@Test(testName = "Check OFAC Rule with Name of Recipient ", priority = 1)
//	public void TC001_OFAC_With_RecipientName_FI_MGD_FedWire() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FI002", getFedWireTestDataPath());
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("OFAC01",getFedWireTestDataPath());
//		AddSenderDetails.Click_Payments_Link_FI();
//		AddSenderDetails.select_DivRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterAddressDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.continueBtnClick();
//		String InstructionId = AddSenderDetails.directSubmitMsg();
//		ValidationPage.asserting_PmtStatus_InstruIDClick_RiskCheckStatus();
//		TestDataReader.updateInstructionID("TC001", InstructionId, "FedWire_Payments");
//	}

	
	

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
	    AddSenderDetails.dashBoardClick();
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
