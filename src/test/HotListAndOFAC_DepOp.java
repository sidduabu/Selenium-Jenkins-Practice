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

public class HotListAndOFAC_DepOp extends Utility {

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
	
	
	
//=============================================================================================================================================	
//------------------------->>>  *** Hotlist	Cases----Retail Transfer Division *** <<<-----------------------------------------------------------------------------------------	
//=============================================================================================================================================	

	@Test(testName = "HotList BankAccNum (RetailTransferDivision-RTP)", priority = 1)
	public void tc001BankAccNum_HotList_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
	}

	@Test(testName = "HotList Phone Number (RetailTransferDivision-RTP)", priority = 2)
	public void tc002PhoneNum_HotList_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}

	@Test(testName = "HotList Email (RetailTransferDivision-RTP)", priority = 3)
	public void tc003Email_HotList_DepOp_RTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
	}

	@Test(testName = "HotList BankAccNum (RetailTransferDivision-FedNow)", priority = 4)
	public void tc004BankAccNum_HotList_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
				
	}

	@Test(testName = "HotList Phone Number (RetailTransferDivision-FedNow)", priority = 5)
	public void tc005PhoneNum_HotList_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}
 
	@Test(testName = "HotList Email (RetailTransferDivision-FedNow)", priority = 6)
	public void tc006Email_HotList_DepOp_RTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
	}

	@Test(testName = "HotList BankAccNum (RetailTransferDivision-FedWire)", priority = 7)
	public void tc007BankAccNum_HotList_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
				
	}

	@Test(testName = "HotList Phone Number (RetailTransferDivision-FedWire)", priority = 8)
	public void tc008PhoneNum_HotList_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}

	@Test(testName = "HotList Email (RetailTransferDivision-FedWire)", priority = 9)
	public void tc009Email_HotList_DepOp_RTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-RTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
	}

//=============================================================================================================================================	
//------------------------->>>  *** BusinessTransfer Division *** <<<-----------------------------------------------------------------------------------------	
//=============================================================================================================================================	

	@Test(testName = "HotList BankAccNum (BusinessTransferDivision-RTP)", priority = 10)
	public void tc010BankAccNum_HotList_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
				
	}
	
	@Test(testName = "HotList Phone Number (BusinessTransferDivision-RTP)", priority = 11)
	public void tc011PhoneNum_HotList_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}

	@Test(testName = "HotList Email (BusinessTransferDivision-RTP)", priority = 12)
	public void tc012Email_HotList_DepOp_BTD_RTP() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-RTP");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-RTP-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
	}

	@Test(testName = "HotList BankAccNum (BusinessTransferDivision-FN)", priority = 13)
	public void tc013BankAccNum_HotList_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
				
	}

	@Test(testName = "HotList Phone Number (BusinessTransferDivision-FN)", priority = 14)
	public void tc014PhoneNum_HotList_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}

	@Test(testName = "HotList Email (BusinessTransferDivision-FN)", priority = 15)
	public void tc015Email_HotList_DepOp_BTD_FedNow() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FN");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FN-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
	}

	@Test(testName = "HotList BankAccNum (BusinessTransferDivision-FedWire)", priority = 16)
	public void tc016BankAccNum_HotList_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-001");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistAccountNum());
				
	}

	@Test(testName = "HotList Phone Number (BusinessTransferDivision-FedWire)", priority = 17)
	public void tc017PhoneNum_HotList_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-002");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistPhoneNum());
				
	}

	@Test(testName = "HotList Email (BusinessTransferDivision-FedWire)", priority = 18)
	public void tc018Email_HotList_DepOp_BTD_FedWire() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("HL-BTD-FW");
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails("HL-FW-003");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmit();
		ErrorMessageData riskMSG = TestDataReader.loadRiskCheckAssertMsg();
		ValidationPage.assertingHotlistStatus(riskMSG.getHotlistEmail());
				
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
