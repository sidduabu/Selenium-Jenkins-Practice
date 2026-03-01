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
import pages.FedfileValidationPage;
import pages.Login;

public class Fedfile_ISO_Format_Transfers extends Utility {
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

		objLogin = TestDataReader.loadLogin("Login-03");
		Login.loginTest(objLogin);
	}
	
	
//Info :- 
// testCase 01 to 06:- Sender_To_Individual_Recip        [Structural_Remitt, External_Remitt, No_Additinal_Remitt_info --->> DirectSubmit & SubmitForApproval]
// testCase 07 to 12:- Sender_To_ForeignIndividual_Recip [Structural_Remitt, External_Remitt, No_Additinal_Remitt_info --->> DirectSubmit & SubmitForApproval]

	
//===============================================>> Deposit Operation - Transfers Cases <<===========================================================================================================================================
//===============================================================================================================================================	

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithStructuralRemitt_DirectSubmit_Depo_Transfer", priority = 01)
	public void tc001FedFile_ISO_SenderToIndivdRecipWithStructuralRemitt_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE01");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP02");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithStructuralRemitt_SubmitForApproval_Depo_Transfer", priority = 02)
	public void tc002FedFile_ISO_SenderToIndivdRecipWithStructuralRemitt_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE01");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP02");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithExternalRemittFor_DirectSubmit_Depo_Transfer", priority = 3)
	public void tc003FedFile_ISO_SenderToIndivdRecipWithExternalRemittFor_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE02");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP01");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithExternalRemitt_SubmitForApproval_Depo_Transfer", priority = 4)
	public void tc004FedFile_ISO_SenderToIndivdRecipWithExternalRemitt_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE02");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP01");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithNoAdditnlRemittInfo_DirectSubmit_Depo_Transfer", priority = 5)
	public void tc005FedFile_ISO_SenderToIndivdRecipWithNoAdditnlRemittInfo_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP03");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_SenderToIndivdRecipWithNoAdditnlRemittInfo_SubmitForApproval_Depo_Transfer", priority = 06)
	public void tc006FedFile_ISO_SenderToIndivdRecipWithNoAdditnlRemittInfo_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP03");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_SenderToForeignIndivdRecipWithStructuralRemitt_DirectSubmit_Depo_Transfer", priority = 7)
	public void tc007FedFile_ISO_SenderToForeignIndivdRecipWithStructuralRemitt_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE01");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP02");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO__SenderToForeignIndivdRecipWithStructuralRemitt_SubmitForApproval_Depo_Transfer", priority = 8)
	public void tc008FedFile_ISO__SenderToForeignIndivdRecipWithStructuralRemitt_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE01");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP02");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO__SenderToForeignIndivdRecipWithExternalRemittFor_DirectSubmit_Depo_Transfer", priority = 9)
	public void tc009FedFile_ISO__SenderToForeignIndivdRecipWithExternalRemittFor_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE02");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP01");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO__SenderToForeignIndivdRecipWithExternalRemitt_SubmitForApproval_Depo_Transfer", priority = 10)
	public void tc010FedFile_ISO__SenderToForeignIndivdRecipWithExternalRemitt_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE02");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP01");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO__SenderToForeignIndivdRecipWithNoAdditnlRemittInfo_DirectSubmit_Depo_Transfer", priority = 11)
	public void tc011FedFile_ISO__SenderToForeignIndivdRecipWithNoAdditnlRemittInfo_DirectSubmit_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP03");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO__SenderToForeignIndivdRecipWithNoAdditnlRemittInfo_SubmitForApproval_Depo_Transfer", priority = 12)
	public void tc012FedFile_ISO__SenderToForeignIndivdRecipWithNoAdditnlRemittInfo_SubmitForApproval_Depo_Transfer()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DFILE03");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("DP03");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
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

//		String testCaseStatus = AddSenderDetails.logOff2();
//		if(testCaseStatus.equalsIgnoreCase("InFinalCatch")) {
//			Utility.closeBrowser(WD);
//	        startSuite();              }
	}

	@AfterClass
	public void endSuite() throws Exception {
//		Utility.closeBrowser(WD);
	}
}
