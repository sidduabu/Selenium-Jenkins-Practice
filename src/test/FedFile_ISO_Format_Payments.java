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

public class FedFile_ISO_Format_Payments extends Utility {
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
// testCase 01 to 08 :- BTR_Pmts...FI to FI  [ie:- FundViaFedMasterAcc, GL_Acc, DDA_Acc, LedgerAcc --->> DirectSubmit & SubmitForApproaval.]
// testCase 09 to 14 :- CTR_Pmts...FI/Cust to Cust with No_Remitt_Info [ie:-GL_Acc, DDA_Acc, LedgerAcc--->> DirectSubmit & SubmitForApproaval.]
// testCase 15 to 26 :- CTP_Pmts...FI/Cust to Cust With Additional_Remitt_Info [ie:-GL_Acc, DDA_Acc, LedgerAcc--->> StructuralRemitt & ExternalRemitt------->> DirectSubmit & SubmitForApproaval.]                                            
// testCase 27 to 28 :- tch_Funding --->> DirectSubmit & SubmitForApproaval.
		

//========================================>> BTR Payments (FI to FI) <<=============================================================================================	

	@Test(testName = "FedFile_ISO_BTR_Pmt_FunViaFedMasterAcc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 1)
	public void tc001FedFile_ISO_BTR_Pmt_FunViaFedMasterAcc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE04");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		 Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-06");
//		 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_FunViaFedMasterAcc_To_FIrecip_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 2)
	public void tc002FedFile_ISO_BTR_Pmt_FunViaFedMasterAcc_To_FIrecip_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE04");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-06");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_GL_Acc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 3)
	public void tc003FedFile_ISO_BTR_Pmt_GL_Acc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE05");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-07");
		// Validating the Uploaded transaction details in UI
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_GL_Acc_To_FIrecip_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 4)
	public void tc004FedFile_ISO_BTR_Pmt_GL_Acc_To_FIrecip_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE05");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-07");
//		 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("BTR-MGD-001");
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "tc011FedFile_ISO_BTR_Pmt_DDA_Acc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 5)
	public void tc005FedFile_ISO_BTR_Pmt_DDA_Acc_To_FIrecip_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE06");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//	    Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//	    Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-04");
//	    Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_Upload_DDA_Acc_NoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 6)
	public void tc006FedFile_ISO_BTR_Pmt_Upload_DDA_Acc_NoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE06");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-04");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_Upload_LedgerAcc_NoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 7)
	public void tc007FedFile_ISO_BTR_Pmt_Upload_LedgerAcc_NoAdditnlRemittInfo_DirectSubmit_FI_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE07");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-05");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_BTR_Pmt_Upload_LedgerAcc_NoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 8)
	public void tc008FedFile_ISO_BTR_Pmt_Upload_LedgerAcc_NoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE07");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-05");
//		 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

//=========================>> CTR Payments :- FI/Customer to Customer (With Out Additional_Remittance_Info) <<===================================================================================

	@Test(testName = "FedFile_ISO_CTR_Pmt_GL_AccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 9)
	public void tc009FedFile_ISO_CTR_Pmt_GL_AccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE08");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-08");
//				 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_CTR_Pmt_GL_AccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 10)
	public void tc010FedFile_ISO_CTR_Pmt_GL_AccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE08");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-08");
//				 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_CTR_Pmt_DDAaccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 11)
	public void tc011FedFile_ISO_CTR_Pmt_DDAaccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE03");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//			Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-03");
//			Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_CTR_Pmt_DDAaccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 12)
	public void tc012FedFile_ISO_CTR_Pmt_DDAaccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE03");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//			Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-03");
//			Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	
//	@Test(testName = "FedFile_ISO_CTR_Pmt_LedgerAccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 13)
//	public void tc013FedFile_ISO_CTR_Pmt_LedgerAaccToIndividPmt_WithNoAdditnlRemittInfo_DirectSubmit_FI_Pmts()throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE03");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////			Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-03");
////			Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmitMsg();
//	}
//
//	@Test(testName = "FedFile_ISO_CTR_Pmt_LedgerAccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 14)
//	public void tc014FedFile_ISO_CTR_Pmt_LedgerAccToIndividPmt_WithNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE03");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////			Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-03");
////			Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.submitForApproval();
//		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
//		AddSenderDetails.logOff();
//		// Approving with another user
//		objLogin = TestDataReader.loadLogin("Login-04");
//		Login.loginTest(objLogin);
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		AddSenderDetails.approvingPayment();
//	}
//
	
	
	
//=========================>> CTP Payments (FI to Customer With  Remittance) <<===================================================================================

//	@Test(testName = "FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts", priority = 15)
//	public void tc015FedFile_ISO_GL_AccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmitMsg();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts", priority = 16)
//	public void tc016FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.submitForApproval();
//		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
//		AddSenderDetails.logOff();
//		// Approving with another user
//		objLogin = TestDataReader.loadLogin("Login-04");
//		Login.loginTest(objLogin);
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		AddSenderDetails.approvingPayment();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts", priority = 17)
//	public void tc017FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
//		// Fedfile_Data)
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmitMsg();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts", priority = 18)
//	public void tc018FedFile_ISO_CTP_Pmt_GL_AccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.submitForApproval();
//		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
//		AddSenderDetails.logOff();
//		// Approving with another user
//		objLogin = TestDataReader.loadLogin("Login-04");
//		Login.loginTest(objLogin);
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		AddSenderDetails.approvingPayment();
//	}
	
	
	@Test(testName = "FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts", priority = 19)
	public void tc019FedFile_ISO_DDAaccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts", priority = 20)
	public void tc020FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	@Test(testName = "FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts", priority = 21)
	public void tc021FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
		// Fedfile_Data)
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts", priority = 22)
	public void tc022FedFile_ISO_CTP_Pmt_DDAaccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		// Uploading the FedFile
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
//		Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
		// Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
		AddSenderDetails.approvingPayment();
	}

	
//	@Test(testName = "FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts", priority = 23)
//	public void tc023FedFile_ISO_LedgerAccToIndividPmt_WithStructRemitt_DirectSubmit_FI_Pmts() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmitMsg();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts", priority = 24)
//	public void tc024FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithStructRemitt_SubmitForApproval_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE01");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-02");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_StructuralRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.submitForApproval();
//		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
//		AddSenderDetails.logOff();
//		// Approving with another user
//		objLogin = TestDataReader.loadLogin("Login-04");
//		Login.loginTest(objLogin);
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		AddSenderDetails.approvingPayment();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts", priority = 25)
//	public void tc025FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithExternalRemitt_DirectSubmit_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//		// Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same
//		// Fedfile_Data)
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.directSubmitMsg();
//	}
//
//	@Test(testName = "FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts", priority = 26)
//	public void tc026FedFile_ISO_CTP_Pmt_LedgerAccToIndividPmt_WithExternalRemitt_SubmitForApproval_FI_Pmts()
//			throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE02");
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		// Uploading the FedFile
//		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
////		Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
//		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-01");
////		Validating the 	Uploaded transaction details in UI  
//		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
//		FedfileValidationPage.fedFile_ExternalRemit_FieldDetails_Validation(fedFiledata);
//		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
//		AddSenderDetails.submitForApproval();
//		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
//		AddSenderDetails.logOff();
//		// Approving with another user
//		objLogin = TestDataReader.loadLogin("Login-04");
//		Login.loginTest(objLogin);
//		AddSenderDetails.selectPaymentsManager();
//		AddSenderDetails.Clik_Payments_Link_FI();
//		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
//		AddSenderDetails.searchConfirmationNo(ConfirmationNo);
//		AddSenderDetails.approvingPayment();
//	}
	
//===============================================================================================================================================	

	@Test(testName = "FedFile_ISO_tch_fundingaccount_UploadNoAdditnlRemittInfo_DirectSubmit_FI_Pmts", priority = 27)
	public void tc027FedFile_ISO_tch_fundingaccount_UploadNoAdditnlRemittInfo_DirectSubmit_FI_Pmts() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE09");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-09");
//   Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();

	}

	@Test(testName = "FedFile_ISO_tch_fundingaccount_UploadNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts", priority = 28)
	public void tc028FedFile_ISO_tch_fundingaccount_UploadNoAdditnlRemittInfo_SubmitForApproval_FI_Pmts()
			throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("FILE09");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//   Uploading the FedFile		
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
//   Loading/Fetching the Transaction details from Sheet (In Sheet we Stored Same Fedfile_Data)	
		FedfileValidationPage fedFiledata = TestDataReader.loadfedFileData("FI-09");
//	 Validating the 	Uploaded transaction details in UI  
		FedfileValidationPage.fedFile_SenderAndRecipient_FieldDetails_Validation(fedFiledata);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.submitForApproval();
		String ConfirmationNo = AddSenderDetails.pendingApprovalMsg();
		AddSenderDetails.logOff();
//   Approving with another user
		objLogin = TestDataReader.loadLogin("Login-04");
		Login.loginTest(objLogin);
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
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
