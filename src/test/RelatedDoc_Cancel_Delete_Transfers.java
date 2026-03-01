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

public class RelatedDoc_Cancel_Delete_Transfers extends Utility {
	
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
	
//=================================================================================================================================================================================================================================================	
//==========================>> RTP Cases <<=========================================================================================================================================================================================================	
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_RTP_Transfers", priority = 1)
	public void tc001UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//  Cancel & Delete actions at the Payment First Page     
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_RTP_Transfers", priority = 2)
	public void tc002UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//  Cancel & Delete actions at the Payment First Page after Edit button click    
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_RTP_Transfers", priority = 3)
	public void tc003UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//  Cancel & Delete actions at the FinalPage--->first login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_RTP_Transfers", priority = 4)
	public void tc004UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//  Cancel & Delete actions at the FinalPage--->Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_RTP_Transfers", priority = 5)
	public void tc005UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
//  Cancel & Delete actions at the FinalPage--->first login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_RTP_Transfers", priority = 6)
	public void tc006UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//  Cancel & Delete actions at the FinalPage---> Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_RTP_Transfers", priority = 07)
	public void tc007UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_RTP_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-RTP");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
//  Cancel & Delete actions at the FinalPage---> Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}


	
//=================================================================================================================================================================================================================================================	
//==========================>> FedNow Cases <<=========================================================================================================================================================================================================	

	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_FedNow_Transfers", priority = 1)
	public void tc001UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
//  Cancel & Delete actions at the Payment First Page     
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_FedNow_Transfers", priority = 2)
	public void tc002UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//  Cancel & Delete actions at the Payment First Page after Edit button click    
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_FedNow_Transfers", priority = 3)
	public void tc003UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//  Cancel & Delete actions at the FinalPage--->first login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_FedNow_Transfers", priority = 4)
	public void tc004UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
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
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//  Cancel & Delete actions at the FinalPage--->Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
	}
	
	
	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_FedNow_Transfers", priority = 5)
	public void tc005UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
//  Cancel & Delete actions at the FinalPage--->first login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_FedNow_Transfers", priority = 6)
	public void tc006UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//  Cancel & Delete actions at the FinalPage---> Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}

	@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_FedNow_Transfers", priority = 07)
	public void tc007UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_FedNow_Transfers() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FN");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
//  Cancel & Delete actions at the FinalPage---> Second login      
		AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
		AddSenderDetails.approvingPayment();
//	Validating Deleted Doc at the final page after approving	
	    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

	}

		
	
//=================================================================================================================================================================================================================================================	
//==========================>> FedWire Cases <<=========================================================================================================================================================================================================	
			
			@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_FedWire_Transfers", priority = 1)
			public void tc001UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
//			Uploading the Related Doc at the Payment First Page	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		//  Cancel & Delete actions at the Payment First Page     
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.directSubmitMsg();
//			Validating Deleted Doc at the final page after approving	
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
			}
			
			@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_FedWire_Transfers", priority = 2)
			public void tc002UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtPaymentFirstPage_AfterEditClick_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
//			Uploading the Related Doc at the Payment First Page	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
	          	AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		//  Cancel & Delete actions at the Payment First Page after Edit button click    
				AddRecipientDetails.editBtnClick();
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDoc();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.directSubmitMsg();
//			Validating Deleted Doc at the final page after approving	
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
			}

			@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_FedWire_Transfers", priority = 3)
			public void tc003UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtFirstLoginFinalPage_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
//			Uploading the Related Doc at the Payment First Page	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.submitForApproval();
				String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
		//  Cancel & Delete actions at the FinalPage--->first login      
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
				AddSenderDetails.logOff();	
//			Approving with another user
				objLogin = TestDataReader.loadLogin("Login-02");
				Login.loginTest(objLogin);
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.searchConfirmationNo(ConfirmationNo);
				AddSenderDetails.approvingPayment();
//			Validating Deleted Doc at the final page after approving	
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
			}
			
			@Test(testName = "UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_FedWire_Transfers", priority = 4)
			public void tc004UploadedRelatedDocument_AtPaymentFirstPage_CancelDeletingDocAtSecondLoginFinalPage_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
//			Uploading the Related Doc at the Payment First Page	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.submitForApproval();
				String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
				AddSenderDetails.logOff();	
//			Approving with another user
				objLogin = TestDataReader.loadLogin("Login-02");
				Login.loginTest(objLogin);
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		//  Cancel & Delete actions at the FinalPage--->Second login      
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
				AddSenderDetails.approvingPayment();
//			Validating Deleted Doc at the final page after approving	
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
			}
			
			
			@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_FedWire_Transfers", priority = 5)
			public void tc005UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtFirstLogin_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.submitForApproval();
				String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//			Uploading the Related Doc at the FinalPage---> First login	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		//  Cancel & Delete actions at the FinalPage--->first login      
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
		        AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
				AddSenderDetails.logOff();	
//			Approving with another user
				objLogin = TestDataReader.loadLogin("Login-02");
				Login.loginTest(objLogin);
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.searchConfirmationNo(ConfirmationNo);
				AddSenderDetails.approvingPayment();
//			Validating Deleted Doc at the final page after approving	
			    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

			}

			@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_FedWire_Transfers", priority = 6)
			public void tc006UploadedRelatedDocument_AtPaymentFinalPageFirstLogin_CancelDeletingDocAtSecondLogin_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.submitForApproval();
				String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//			Uploading the Related Doc at the FinalPage---> First login	
				AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
				AddSenderDetails.logOff();	
//			Approving with another user
				objLogin = TestDataReader.loadLogin("Login-02");
				Login.loginTest(objLogin);
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		//  Cancel & Delete actions at the FinalPage---> Second login      
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
			    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
				AddSenderDetails.approvingPayment();
//			Validating Deleted Doc at the final page after approving	
			    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();

			}

			@Test(testName = "UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_FedWire_Transfers", priority = 07)
			public void tc007UploadedRelatedDocument_AtPaymentFinalPageSecondLogin_CancelDeletingDocAtSecondLogin_FedWire_Transfers() throws Exception {
				AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-RTD-FW");
				AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-001");
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		        AddRecipientDetails.totalAmountDisplayed();
				AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
				AddSenderDetails.submitForApproval();
				String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
				AddSenderDetails.logOff();	
//	Approving with another user
				objLogin = TestDataReader.loadLogin("Login-02");
				Login.loginTest(objLogin);
				AddSenderDetails.selectPaymentsManager();
				AddSenderDetails.Clik_Transfers_Link_DepOp();
				AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
				AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
			    AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
			    AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
//  Cancel & Delete actions at the FinalPage---> Second login      
				AddRecipientDetails.cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage();
			    AddRecipientDetails.Validate_DeletedRelatedDocumentAtFinalPage();
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
