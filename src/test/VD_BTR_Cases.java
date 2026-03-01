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
import pages.ValidationPage;

public class VD_BTR_Cases extends Utility{
	
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
		objLogin = TestDataReader.loadLogin("Login-05");
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
		while (extentReports.getReport().findTest(testMethod.getName()).isPresent()) {
			extentReports.removeTest(testMethod.getName());
			if (!extentReports.getReport().getTestList().isEmpty())
				extentReports.flush();
		}
		extentTest = extentReports.createTest(testMethod.getName()).assignCategory(this.getClass().getSimpleName());
		extentTest.log(Status.INFO, "Test Run started");
	}

	
//===================================>> RecipientType_DropDown   <<========================================================================================================================================================

// Info:- If we select the FundViaFedMaster_Account as sender account then only FI option should be enable in the Recipient_Type DropDown
	@Test(testName = "VD_FundViaFedMasterToFI_Recip_RecipTypeDropDownCheck", priority = 1)
	public void tc001VD_FundViaFedMasterToFI_Recip_RecipTypeDropDownCheck() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.RecipientType_Validation_FI();
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
	}
	
	
//===================================>> Invalid UniqueReference Mandatory Field Validation  <<========================================================================================================================================================
	
	@Test(testName = "VD_Invalid_UniqueReferenceField_FundViaFedMasterToFI_Recip", priority = 02)
	public void tc002VD_Invalid_UniqueReferenceField_FundViaFedMasterToFIrecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.enterinvalidUniqueReference_And_Assertion();
	}
	
	@Test(testName = "VD_Invalid_UniqueReferenceField_GL_Account to FI Recip", priority = 03)
	public void tc003VD_Invalid_UniqueReferenceField_GL_Account_To_FI_Recip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.enterinvalidUniqueReference_And_Assertion();
	}
	
	@Test(testName = "VD_Invalid_UniqueReferenceField_DDA_Account to FI Recip", priority = 04)
	public void tc004VD_Invalid_UniqueReferenceField_DDA_Account_To_FI_Recip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.enterinvalidUniqueReference_And_Assertion();
	}
	
	@Test(testName = "VD_Invalid_UniqueReferenceField_Ledger_Account to FI Recip", priority = 05)
	public void tc005VD_Invalid_UniqueReferenceField_Ledger_Account_To_FI_Recip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.enterinvalidUniqueReference_And_Assertion();
	}
	
	
	
//========================================>> InfoForRecipFI_DropDownOptionsValidation    <<=====================================================================================================================================================	
//As per the new update (ie:- From 62nd Release) If we select Recipient_Type as FI then we should show only two options in the Info_For_Recipient_FI DropDown (Two Options are 	PHOB(Phone) & TELB(Telecom)
	
	@Test(testName = "VD_FundViaFedMasterToFIrecip_InfoForRecipFI_DropDownOptionsValidation", priority = 06)
	public void tc006VD_FundViaFedMasterToFIrecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-001");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//	Here We are validating Info_For_recipient_FI_DropDown Options (Apart from Select Option) 	
		ValidationPage.info_For_RecipFI_DropDownOptions_Validation("TwoOptions");
		
	}
	
	@Test(testName = "VD_GL_Acc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation", priority = 07)
	public void tc007VD_GL_Acc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-002");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//	Here We are validating Info_For_recipient_FI_DropDown Options (Apart from Select Option) 	
		ValidationPage.info_For_RecipFI_DropDownOptions_Validation("TwoOptions");
		
	}
	
	@Test(testName = "VD_DDA_Acc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation", priority = 8)
	public void tc008VD_DDA_Acc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-003");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//	Here We are validating Info_For_recipient_FI_DropDown Options (Apart from Select Option) 	
		ValidationPage.info_For_RecipFI_DropDownOptions_Validation("TwoOptions");
		
	}
	
	@Test(testName = "VD_LedgerAcc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation", priority = 9)
	public void tc009VD_LedgerAcc_ToFIrecip_InfoForRecipFI_DropDownOptionsValidation() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("BTR-MGD-004");
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails_FI(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//	Here We are validating Info_For_recipient_FI_DropDown Options (Apart from Select Option) 	
		ValidationPage.info_For_RecipFI_DropDownOptions_Validation("TwoOptions");
		
	}
	

	
//=========================================================================================================================================================================================================================	
	
//	@Test(testName = "", priority = 33)
//	public void tc033CheckingAPICall_FItoIndividualPmt() throws Exception {
//		objLogin = TestDataReader.loadLogin("Login-01");
//		Login.loginTest(objLogin);
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("TC004");
//		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("TC004");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterAddressDetails(objReciDtls);
//		AddRecipientDetails.enterAmount_CheckAPIcall(objReciDtls);
////		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
////		AddRecipientDetails.continueBtnClick();
////		AddSenderDetails.directSubmitMsg();
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
		if (testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
			Utility.closeBrowser(WD);
			startSuite();               }

	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}

}
