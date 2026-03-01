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

public class RelatedDoc_MaxiSize_ValidationCases_Payments extends Utility {
	
	
	static Login objLogin;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();

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
		
		objLogin = TestDataReader.loadLogin("Login-01");
		Login.loginTest(objLogin);
		AddSenderDetails.selectCustomerSupport();
	}
	
//======================================>> RTP Payments <<===========================================================================================================================================================================================	
	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment First Page _RTP", priority = 1)
	public void tc001UploadRelatedDocument__PNG_FileType_AtPaymentFirstPage_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
	}
	

	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page _RTP", priority = 2)
	public void tc002UploadRelatedDocument__PNG_FileType_AtPaymentFinalPage_SigleLogin_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());

	}


	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page_SecondLogin_RTP", priority = 3)
	public void tc003UploadRelatedDocument_PNG_FileType_AtPaymentFinalPage_SecondLogin_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	

//======================================>> FedNow Payments <<===========================================================================================================================================================================================	
	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment First Page _FedNow", priority = 1)
	public void tc004UploadRelatedDocument__PNG_FileType_AtPaymentFirstPage_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
	}
	

	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page FedNow", priority = 2)
	public void tc005UploadRelatedDocument__PNG_FileType_AtPaymentFinalPage_SigleLogin_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());

	}


	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page SecondLogin FedNow", priority = 3)
	public void tc006UploadRelatedDocument_PNG_FileType_AtPaymentFinalPageSecondLogin_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	
//======================================>> FedWire Payments <<===========================================================================================================================================================================================	
	
	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment First Page FedWire", priority = 1)
	public void tc007UploadRelatedDocument__PNG_FileType_AtPaymentFirstPage_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.travelRuleCheckBoxClick("Yes", "Yes");
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
	}
	

	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page FedWire", priority = 2)
	public void tc008UploadRelatedDocument__PNG_FileType_AtPaymentFinalPage_SigleLogin_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());

	}


	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page_SecondLogin_FedWire", priority = 3)
	public void tc009UploadRelatedDocument_PNG_FileType_AtPaymentFinalPage_SecondLogin_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MAX-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
		//Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//	Validating the Maximum Size error 	
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.assertingErrorFieldMsg_For_UploadRelatedDoc(objErrDtls.getMaxDocSize());
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
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
	         startSuite(); 
	         Utility.closeBrowser(WD);       }
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}


}
