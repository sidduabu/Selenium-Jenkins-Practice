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
import pages.ValidationPage;
import pages.Login;

public class Validation_DepOp_Transfers_FedWire extends Utility {

	public static  ExtentSparkReporter extentSparkReporter;
	 public static  ExtentReports extentReports;
	 public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		open_TMSSite();
		objLogin = TestDataReader.loadLogin("Login-06");
		Login.loginTest(objLogin);
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DepOp-Pmts-FW");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
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

		
	@Test(priority = 1, testName="EmptyAccNum")
	public static void tc001EmptyAccNum_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-001");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());    
	}
	
	@Test(priority = 2, testName="InvalidAccNum")
	public static void tc002InvalidAccNum_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-002");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
        ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());    
	}
	
	@Test(priority = 3, testName="EmptyChannel")
	public static void tc003EmptyChannel_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-003");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyChannel());    
	}

	@Test(priority = 4, testName="EmptyRecipTyp")
	public static void tc004EmptyRecipTyp_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-004");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipType());    
	}

	@Test(priority = 5, testName="EmptyRecipName")
	public static void tc005EmptyRecipName_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-005");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipNam()); 
	}

	@Test(priority = 6, testName="EmptyRecipAddressLine")
	public static void tc006EmptyRecipAddrsLine_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-006");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.emptyAddressLine();	
		}
	
	@Test(priority = 7, testName="EmptyRecipTown")
	public static void tc007EmptyRecipTown_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-007");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
        ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipTown()); 
	}

	@Test(priority = 8, testName="EmptyRecipState")
	public static void tc008EmptyRecipState_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-008");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
        ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipState()); 
	}
	@Test(priority = 9, testName="EmptyRecipCountry")
	public static void tc009EmptyRecipCountry_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-009");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
        ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipCntry()); 
	}

	@Test(priority = 10, testName="EmptyRecipZipCode")
	public static void tc010EmptyRecipZipCode_VD_FedWire_Transfers() throws Exception{
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-010");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
        ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipZipCod()); 
	}
	
	@Test(priority = 11, testName = "EmptyRecipRoutNum")
	public static void tc011EmptyRecipRoutNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-011");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyRecipRoutNum());
	}

	@Test(priority = 12, testName = "EmptyRecipAccNum")
	public static void tc012EmptyRecipAccNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-012");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAccNum());
	}

	@Test(priority = 13, testName = "EmptyRecipAmt")
	public static void tc013EmptyRecipAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-013");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getEmptyAmt());
	}

	@Test(priority = 14, testName = "InvdRecipNam")
	public static void tc014InvdRecipNam_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-014");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipNam());
	}

	@Test(priority = 15, testName = "InvdRecipZipCode")
	public static void tc015InvdRecipZipCode_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-015");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}

	@Test(priority = 16, testName = "InvdRecipEmail")
	public static void tc016InvdRecipEmail_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-016");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipEmail());
	}

	@Test(priority = 17, testName = "InvdRecipPhnNum")
	public static void tc017InvdRecipPhnNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-017");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipPhn());
	}

	@Test(priority = 18, testName = "InvdRecipRoutingNum")
	public static void tc018InvdRecipRoutingNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-018");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdRecipRoutNum());
	}

	@Test(priority = 19, testName = "InvdRecipAccNum")
	public static void tc019InvdRecipAccNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-019");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvalidAccNum());
	}

	@Test(priority = 20, testName = "InvdRecipAmt")
	public static void tc020InvdRecipAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-020");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdAmt());
	}
	
	@Test(priority = 21, testName = "AmtMaxExcd")
	public static void tc021AmtMaxExcd_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-021");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd4());
	}
	
	
	@Test (priority = 22, testName="FW_EmtyRefToRecip")
	public static void tc022FW_EmtyRefToRecip_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-022");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fWEmtyRefToRecip();
	}
	
	@Test (priority = 23, testName="Fw_EmptyInfoRcpFI")
	public static void tc023FW_EmptyInfoRcpFI_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-023");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.emptyFWRemitInfoForRecipFI();
	}
	
	@Test (priority = 24, testName="Fw_EmptyInfoRcpFI_DropDown")
	public static void tc024FW_EmptyInfoRcpFI_DropDown_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-024");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyInfoRcpFI()); 
	}
	
	@Test (priority = 25, testName="FW_InvdRefToRecip")
	public static void tc025FW_InvdRefToRecip_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-025");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwInvdRefToRecip()); 
	}
	
	@Test (priority = 26, testName="FW_InvdSendrToRecipInfo")
	public static void tc026FW_InvdSendrToRecipInfo_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-026");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fwInvdSendrToRecipInfo();
	}
	
	@Test (priority = 27, testName="FW_OrgEmtyIdTyp")
	public static void tc027FW_OrgEmtyIdTyp_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-027");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgEmtyIdTyp());
	}
	
	@Test (priority = 28, testName="FW_OrgEmtyIdCode")
	public static void tc028FW_OrgEmtyIdCode_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-028");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyIdCod());
	}
	
	@Test (priority = 29, testName="FW_OrgEmtyNam")
	public static void tc029FW_OrgEmtyNam_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-029");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyNam()); 
	}
	
	@Test (priority = 30, testName="FW_OrgEmtyIdNum")
	public static void tc030FW_OrgEmtyIdNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-030");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyIdNum()); 
	}
	
// >>>In 62.2 Release...DevTeam removed AddressType_field..!! 	
//	@Test (priority = 31, testName="FW_OrgEmtyAddrsTyp")
//	public static void tc031FW_OrgEmtyAddrsTyp_VD_FedWire_Transfers() throws Exception {
//		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
//		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-031");
//		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
//		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
//		AddRecipientDetails.enterRecipientDetails(objReciDtls);
//		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
//		AddRecipientDetails.enterAmount(objReciDtls);
//		AddRecipientDetails.enterFeeAmount();
//		AddRecipientDetails.totalAmountDisplayed(); 
//		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
//		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
//		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyAddrsTyp()); 
//	}

	@Test (priority = 32, testName="FW_OrgEmtyAddrsLine")
	public static void tc032FW_OrgEmtyAddrsLine_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-032");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgEmtyAddrsLin1()); 
	}
	
	@Test (priority = 33, testName="FW_OrgEmtyState")
	public static void tc033FW_OrgEmtyState_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-033");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyState()); 
	}
	@Test (priority = 34, testName="FW_OrgEmtyPostalCode")
	public static void tc034FW_OrgEmtyPostalCode_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-034");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyPostl()); 
	}
	
	@Test (priority = 35, testName="FW_OrgEmtyCountry")
	public static void tc035FW_OrgEmtyCountry_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-035");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgEmtyCtry()); 
	}
	
	@Test (priority = 36, testName="FW_OrgInvdCity")
	public static void tc036FW_OrgInvdCity_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-036");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdCity()); 
	}
	@Test (priority = 37, testName="FW_OrgInvdState")
	public static void tc037FW_OrgInvdState_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-037");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdState()); 
	}
	@Test (priority = 38, testName="FW_OrgInvdPostal")
	public static void tc038FW_OrgInvdPostal_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-038");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingTwoErrorFieldMsgs(objErrDtls.getFwOrgInvdPostl()); 
	}
	@Test (priority = 39, testName="FW_OrgInvdPhone")
	public static void tc039FW_OrgInvdPhone_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-039");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgInvdPhn()); 
	}
	@Test (priority = 40, testName="FW_OrgInvdEmail")
	public static void tc040FW_OrgInvdEmail_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-040");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwOrgInvdEml()); 
	}
	
	@Test (priority = 41, testName="FW_DocEmtyDocTyp")
	public static void tc041FW_DocEmtyDocTyp_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-041");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocEmtyDocTyp()); 
	}
	
	@Test (priority = 42, testName="FW_DocEmtyDocIdNum")
	public static void tc042FW_DocEmtyDocIdNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-042");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocEmtyDocIdNum()); 
	}
	
	@Test (priority = 43, testName="FW_DocEmtyDocDat")
	public static void tc043FW_DocEmtyDocDat_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-043");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ValidationPage.fwDocEmtyDocDat();
	}
	@Test (priority =44 , testName="FW_DocInvdActlAmt")
	public static void tc044FW_DocInvdActlAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-044");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdActlAmt()); 
	}
	@Test (priority = 45, testName="FW_DocInvdOrgnlAmt")
	public static void tc045FW_DocInvdOrgnlAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-045");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdOrgnlAmt()); 
	}
	@Test (priority =46 , testName="FW_DocInvdDiscAmt")
	public static void tc046FW_DocInvdDiscAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-046");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdDiscAmt()); 
	}
	@Test (priority =47 , testName="FW_DocInvdAdjstAmt")
	public static void tc047FW_DocInvdAdjstAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-047");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocInvdAdjstAmt()); 
	}
	@Test (priority = 48, testName="FW_DocExcdActlAmt")
	public static void tc048FW_DocExcdActlAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-048");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdActlAmt()); 
	}
	@Test (priority =49 , testName="FW_DocExcdOrgnlAmt")
	public static void tc049FW_DocExcdOrgnlAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-049");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdOrgnlAmt()); 
	}
	@Test (priority =50 , testName="FW_DocExcdDiscAmt")
	public static void tc050FW_DocExcdDiscAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-050");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdDiscAmt()); 
	}
	@Test (priority =51 , testName="FW_DocExcdAdjstAmt")
	public static void tc051FW_DocExcdAdjstAmt_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-051");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwDocExcdAdjstAmt()); 
	}
	
	@Test (priority =52 , testName="FW_Empty_AdjReason_VD_FedWire_Transfers")
	public static void tc052FW_Empty_AdjReason_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-052");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmptyDocAdjResnCode()); 
	}
	@Test (priority =53 , testName="FW_DocAdjInditr")
	public static void tc053FW_DocAdjInditr_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-053");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmptyDocAdjIndictr()); 
	}
	
	@Test (priority =54 , testName="FW_EmtyRemitId")
	public static void tc054FW_EmtyRemitId_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-054");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitId()); 
	}
	@Test (priority =55 , testName="FW_EmtyRemitLocMthd")
	public static void tc055FW_EmtyRemitLocMthd_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-055");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitLocMthd());
	}
	@Test (priority =56 , testName="FW_EmtyRemitLoc")
	public static void tc056FW_EmtyRemitLoc_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-056");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		 
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwEmtyRemitLoc());
	}
	
	@Test (priority =57 , testName="FW_InvalidRemitId")
	public static void tc057FW_InvalidRemitId_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-057");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFeeAmount();
		AddRecipientDetails.totalAmountDisplayed(); 
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getFwInvdRemitId()); 
	}
	

//================ Missing/Extra Cases (Date:- 31-January-2025) =================================================================

	
	@Test(priority = 58, testName = "InvdRecipZipCode00000")
	public static void tc058InvdRecipZipCode00000_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-058");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getInvdZipCode());
	}
	
	@Test(priority = 59, testName = "InvalidBankNameSearchForRoutingNum")
	public static void tc059InvalidBankNameSearchForRoutingNum_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-059");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.AssertingInvalidBankNameSearchForRoutingNum();
	}
	
	@Test(priority = 60, testName = "Entering_Point009amount_AndAssertingItsRoundingValue")
	public static void tc060Entering_Point009amount_AndAssertingItsRoundingValue_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-060");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("0.01");
	}
	
	@Test(priority = 61, testName = "Entering_34.562 amount_AndAssertingItsRoundingValue")
	public static void tc061Entering_34Point562amount_AndAssertingItsRoundingValue_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-061");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.56");
	}
	
	@Test(priority = 62, testName = "Entering_34.567amount_AndAssertingItsRoundingValue")
	public static void tc062VD_Entering_34Point567amount_AndAssertingItsRoundingValue_VD_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-062");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		ValidationPage.assertingRoundFigureValues("34.57");
	}
	
	@Test(priority = 63, testName = "Entering_GreaterThan17DigitsInAmountField_AndAssertingTheValue")
	public static void tc063VD_Entering_GreaterThan18DigitsInAmountField_FedWire_Transfers() throws Exception {
		AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-063");
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount002(objReciDtls);
// Validating the entered amount				
		ValidationPage.assertingRoundFigureValues("1234567890123456.00");
		ErrorMessageData objErrDtls = TestDataReader.loadErrorMsg();
		ValidationPage.continueClick_And_AssertingSingleErrorFieldMsg(objErrDtls.getAmtMaxExcd4());
	}
	
	@Test(testName = "EnteringSenderToRecipInfo_AndSelectingStructuredRemit_VD_FedWire_Pmts", priority = 64)
	public void tc64EnteringSenderToRecipInfo_AndSelectingStructuredRemit_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-064");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.enterSenderToRecipInfo_And_ValidateErrorMsg(objErrDtls.getFwSenderToRecipInfo());
	}
	
	
	@Test(testName = "EnteringSenderToRecipInfo_AndSelectingExternalRemit_VD_FedWire_Pmts", priority = 65)
	public void tc65EnteringSenderToRecipInfo_AndSelectingExternalRemit_VD_FedWire_Pmts() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("VD-Pmts-RTD-FW-000");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-065");
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		ErrorMessageData objErrDtls=TestDataReader.loadErrorMsg();
		ValidationPage.enterSenderToRecipInfo_And_ValidateErrorMsg(objErrDtls.getFwSenderToRecipInfo());
	}
	
	
//=============================================================================================================================================================================================	
	
			@Test(testName = "VD_InfoForRecipFIDropDownOptionsValidation", priority = 66)
			public void tc066VD_InfoForRecipFIDropDownOptionsValidation() throws Exception {
				AddSenderDetails objSenderDtls = TestDataReader.loadSenderDetails("DepOp-Pmts-FW");
				AddRecipientDetails objReciDtls = TestDataReader.loadRecipientDetails_02("VD-Pmts-RTD-FW-065");
				AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
				AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
				AddRecipientDetails.enterRecipientDetails(objReciDtls);
				AddRecipientDetails.enterRecipientAddressDetailsInAllIndividualFields(objReciDtls);
				AddRecipientDetails.enterAmount(objReciDtls);
				AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
				AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
//			Here We are validating Sender_To_Recipient_Info_DropDown Options (Apart from Select Option) 	
				ValidationPage.info_For_RecipFI_DropDownOptions_Validation("ThreeOptions");
				
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
	        startSuite();                 }	
		}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
	
	
}
