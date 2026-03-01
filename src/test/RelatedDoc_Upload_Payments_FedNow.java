package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class RelatedDoc_Upload_Payments_FedNow extends Utility {
	
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
	
// Info - FedNow_Payments Cases
//  testCase 01 to 21 :- Uploading Each File Individually 
//	                    (ie:- PNG, JPG, JPEG, PDF, BMP, MP3, WAV Files) ---> { a) AtPmtFirstPage    b) AtPmtFinalPage_1stLogin   c) AtPmtFinalPage_2ndLogin }
//	testCase 22 to 24 :- UploadingAllFileFormats_In_One_Case            ---> { a) AtPmtFirstPage    b) AtPmtFinalPage_1stLogin   c) AtPmtFinalPage_2ndLogin }
	

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
		AddSenderDetails.selectPaymentsManager();
	}
	
	
	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment First Page _FedNow_Payments", priority = 1)
	public void tc001UploadRelatedDocument__PNG_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page _FedNow_Payments", priority = 2)
	public void tc002UploadRelatedDocument__PNG_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document _PNG_FileType at Payment Final Page_SecondLogin_FedNow_Payments", priority = 3)
	public void tc003UploadRelatedDocument_PNG_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	
	
	@Test(testName = "Upload Related Document JPG_FileType at Payment First Page _FedNow_Payments", priority = 4)
	public void tc004UploadRelatedDocument_JPG_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPG");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document JPG_FileType at Payment Final Page _FedNow_Payments", priority = 5)
	public void tc005UploadRelatedDocument_JPG_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document JPG_FileType at Payment Final Page_SecondLogin _FedNow_Payments", priority = 6)
	public void tc006UploadRelatedDocument_JPG_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}

	
	@Test(testName = "Upload Related Document JPEG_FileType at Payment First Page _FedNow_Payments", priority = 7)
	public void tc007UploadRelatedDocument_JPEG_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPEG");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document JPEG_FileType at Payment Final Page _FedNow_Payments", priority = 8)
	public void tc008UploadRelatedDocument_JPEG_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPEG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document JPEG_FileType at Payment Final Page_SecondLogin _FedNow_Payments", priority = 9)
	public void tc009UploadRelatedDocument_JPEG_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-JPEG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}

	
	
	@Test(testName = "Upload Related Document PDF_FileType at Payment First Page _FedNow_Payments", priority = 10)
	public void tc010UploadRelatedDocument_PDF_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PDF");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document PDF_FileType at Payment Final Page _FedNow_Payments", priority = 11)
	public void tc011UploadRelatedDocument_PDF_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PDF");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document PDF_FileType at Payment Final Page_SecondLogin _FedNow_Payments", priority = 12)
	public void tc012UploadRelatedDocument_PDF_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PDF");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}

	
	
	@Test(testName = "Upload Related Document BMP_FileType at Payment First Page _FedNow_Payments", priority = 13)
	public void tc013UploadRelatedDocument_BMP_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-BMP");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document BMP_FileType at Payment Final Page _FedNow_Payments", priority = 14)
	public void tc014UploadRelatedDocument_BMP_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-BMP");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document BMP_FileType at Payment Final Page_SecondLogin _FedNow_Payments", priority = 15)
	public void tc015UploadRelatedDocument_BMP_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-BMP");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}


	
	
	@Test(testName = "Upload Related Document WAV_FileType at Payment First Page _FedNow_Payments", priority = 16)
	public void tc016UploadRelatedDocument_WAV_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-WAV");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document WAV_FileType at Payment Final Page _FedNow_Payments", priority = 17)
	public void tc017UploadRelatedDocument_WAV_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-WAV");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}


	
	@Test(testName = "Upload Related Document WAV_FileType at Payment Final Page_SecondLogin_FedNow_Payments", priority = 18)
	public void tc018UploadRelatedDocument_WAV_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-WAV");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}

	
	@Test(testName = "Upload Related Document MP3_FileType at Payment First Page _FedNow_Payments", priority = 19)
	public void tc019UploadRelatedDocument_MP3_FileType_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MP3");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFirstPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}
	

	@Test(testName = "Upload Related Document MP3_FileType at Payment Final Page _FedNow_Payments", priority = 20)
	public void tc020UploadRelatedDocument_MP3_FileType_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MP3");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);

	}

	
	@Test(testName = "Upload Related Document MP3_FileType at Payment Final Page_SecondLogin_FedNow_Payments", priority = 21)
	public void tc021UploadRelatedDocument_MP3_FileType_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-MP3");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//	Uploading the Related Doc at the FinalPage---> Second login	
		AddRecipientDetails.uploadRelatedDocFile_AndSelectDocType_AtFinalPage(objReciDtls.getDocType(), objReciDtls.getDocPath());
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForSingleFile(objReciDtls);
	}

	
	
//==============================================> All Files Format Types in One Case <<=======================================================	
	
	@Test(testName = "Upload Related Document All_FileFormatTypes at Payment First Page _FedNow_Payments", priority = 22)
	public void tc022UploadRelatedDocument_All_FileFormatTypes_AtPaymentFirstPage_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
//	Uploading the Related Doc at the Payment First Page ---> ** All File Format types **
		List<String> relatedDocPath =	AddRecipientDetails.uploadingMultipleRelatedDocuments_AndSelectingDocType("FirstPage", "SevenFiles");
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
	}
	

	@Test(testName = "Upload Related Document All_FileFormatTypes at Payment Final Page _FedNow_Payments", priority = 23)
	public void tc023UploadRelatedDocument_All_FileFormatTypes_AtPaymentFinalPage_SigleLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo=AddSenderDetails.pendingApprovalMsg();
//	Uploading the Related Doc at the FinalPage---> First login	---> ** All File Format types **
		List<String> relatedDocPath =	AddRecipientDetails.uploadingMultipleRelatedDocuments_AndSelectingDocType("FinalPage", "SevenFiles");
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
		AddSenderDetails.logOff();	
//	Approving with another user
		objLogin = TestDataReader.loadLogin("Login-02");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		Validating Uploaded Doc at the final page before approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
        AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
	}


	@Test(testName = "Upload Related Document All_FileFormatTypes at Payment Final Page _SecondLogin_FedNow_Payments", priority = 24)
	public void tc024UploadRelatedDocument_All_FileFormatTypes_AtPaymentFinalPage_SecondLogin_FedNow_Payments() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("UPDOC-FN-PMTS");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("UPDOC-PNG");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
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
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
// Uploading the Related Doc at the FinalPage---> Second login	---> ** All File Format types **
		List<String> relatedDocPath =	AddRecipientDetails.uploadingMultipleRelatedDocuments_AndSelectingDocType("FinalPage", "SevenFiles");
//	Validating Uploaded Doc at the final page before approving	
		AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
		AddSenderDetails.approvingPayment();
//	Validating Uploaded Doc at the final page after approving	
		AddRecipientDetails.validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(relatedDocPath);
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
//		Utility.closeBrowser(WD);
	}

	

}
