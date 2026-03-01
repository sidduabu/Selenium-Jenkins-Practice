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

public class AddRecipient_EditFlows_DepOp extends Utility {
	
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
		objLogin = TestDataReader.loadLogin("Login-01");
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

	
	@Test(testName = "Editing Recipient-->> RTP-Network, RetailTransferDivision(Individual to Business)", priority = 1)
	public void tc001Edit_Recipient_DepOp_RTD_RTP_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
//		 AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}
	
	@Test(testName = "Editing Recipient-->> FedNow-Network, RetailTransferDivision(Individual to Business)", priority = 2)
	public void tc002Edit_Recipient_DepOp_RTD_FN_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, RetailTransferDivision(Individual to Business)", priority = 3)
	public void tc003Edit_Recipient_DepOp_RTD_FW_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
		
	}
	
	
	

	@Test(testName = "Editing Recipient-->> RTP-Network, RetailTransferDivision(Business to Individual)", priority = 4)
	public void tc004Edit_Recipient_DepOp_RTD_RTP_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
		
	}
	
	@Test(testName = "Editing Recipient-->> FedNow-Network, RetailTransferDivision(Business to Individual)", priority = 5)
	public void tc005Edit_Recipient_DepOp_RTD_FN_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, RetailTransferDivision(Business to Individual)", priority = 6)
	public void tc006Edit_Recipient_DepOp_RTD_FW_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

		
	}
	
	
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, RetailTransferDivision(ForeignIndividual to ForeignBusiness)", priority = 7)
	public void tc007Edit_Recipient_DepOp_RTD_FedWire_ForeignIndividual_to_ForeignBusiness() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-FR_IND-FW");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");
	}

	@Test(testName = "Editing Recipient-->> FedWire-Network, RetailTransferDivision(ForeignBusiness to ForeignIndividual)", priority = 8)
	public void tc008Edit_Recipient_DepOp_RTD_FedWire_ForeignBusiness_to_ForeignIndividual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-FW");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-FR_IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

//=====================================>> Business Transfer division <<===============================================================================================================	
	
	@Test(testName = "Editing Recipient-->> RTP-Network, BusinessTransferDivision(Individual to Business)", priority = 9)
	public void tc009Edit_Recipient_DepOp_BTD_RTP_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedNow-Network, BusinessTransferDivision(Individual to Business)", priority = 10)
	public void tc010Edit_Recipient_DepOp_BTD_FN_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, BusinessTransferDivision(Individual to Business)", priority = 11)
	public void tc011Edit_Recipient_DepOp_BTD_FW_Individual_to_Business() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-IND-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	
	

	@Test(testName = "Editing Recipient-->> RTP-Network, BusinessTransferDivision(Business to Individual)", priority = 12)
	public void tc012Edit_Recipient_DepOp_BTD_RTP_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-RTP");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedNow-Network, BusinessTransferDivision(Business to Individual)", priority = 13)
	public void tc013Edit_Recipient_DepOp_BTD_FN_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FN");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber_DepOp004(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, BusinessTransferDivision(Business to Individual)", priority = 14)
	public void tc014Edit_Recipient_DepOp_BTD_FW_Business_to_Individual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-BUS-RTP");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.recipBankDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		 Checking the duplicate recipient 		
		//AddRecipientDetails.addRecipientDuplicateMsg();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.recipBankDetails(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	
	
	@Test(testName = "Editing Recipient-->> FedWire-Network, BusinessTransferDivision(ForeignIndividual to ForeignBusiness)", priority = 15)
	public void tc015Edit_Recipient_DepOp_BTD_FedWire_ForeignIndividual_to_ForeignBusiness() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-FR_IND-FW");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}

	@Test(testName = "Editing Recipient-->> FedWire-Network, BusinessTransferDivision(ForeignBusiness to ForeignIndividual)", priority = 16)
	public void tc016Edit_Recipient_DepOp_BTD_FedWire_ForeignBusiness_to_ForeignIndividual() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-FW");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("ADRC-FR_BUS-FW");
		AddRecipientDetails.recipDetails(objReciDtls);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		AddRecipientDetails objReciDtls2 = TestDataReader.loadRecipientDetails("ADRC-FR_IND-2");
		AddRecipientDetails.recipDetails(objReciDtls2);
		AddRecipientDetails.fwBIScodeIBAN(objReciDtls2);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls2);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.submitBtn2();
		AddRecipientDetails.successApprovalMsg();
//==>>	Deleting the New_Recipient  <<===
		AddRecipientDetails.deletingTheRecipient001("Deleted");

	}
	
	
	
//==============================================  XB _Cases ======================================================================================================
	
	@Test(testName = "Editing->>Retail Transfer Argentina Recip To Business Transfer Chile Recip)", priority = 17)
	public void tc017Edit_XBWireRetailTrans_ArgentinaRecip_To_BusinTrans_ChileRecip() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("ADRC-RTD-XB2");
		AddSenderDetails.select_ClientsRoutingNum2(objSenderDtls);
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB047");
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.addXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
			}
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editBtnClick();
		objSenderDtls = TestDataReader.loadSenderDetails("ADRC-BTD-XB3");
		objReciDtls=TestDataReader.loadXBWireDetails("XB044");
		AddSenderDetails.addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(objSenderDtls);
		AddSenderDetails.enterSenderAccountNumber(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.addXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterChileDetails(objReciDtls);
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
	        startSuite();     }

	}

	@AfterClass
	public void endSuite() throws Exception {
		
		AddSenderDetails.logOff();
		Utility.closeBrowser(WD);
	}


}
