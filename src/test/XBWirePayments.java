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

public class XBWirePayments extends Utility{

	public static  ExtentSparkReporter extentSparkReporter;
	public static  ExtentReports extentReports;
	public static ExtentTest extentTest;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	Date date = new Date();
	static Login objLogin;

	@BeforeClass
	public void startSuite() throws Exception {
		openBrowser();
		Utility.open_TMSSite();
		objLogin = TestDataReader.loadLogin("OPAY_001");
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
	}

	@Test(testName = "Transfer Money for Argentina Recipient", priority = 1)
	public void TC001RetailTransferXBWire_Argentina() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for India Recipient", priority = 2)
	public void TC002RetailTransferXBWire_India() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB002");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterIndiaDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for China Recipient", priority = 3)
	public void TC003RetailTransferXBWire_China() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPurposeofPmtCode();}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Colombia Recipient", priority = 4)
	public void TC004RetailTransferXBWire_Colombia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
			AddRecipientDetails.enterColombiaDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Bangladesh Recipient", priority = 5)
	public void TC005RetailTransferXBWire_Bangladesh() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB005");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBangladeshDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Taiwan Recipient", priority = 6)
	public void TC006RetailTransferXBWire_Taiwan() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB006");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Korea Recipient", priority = 7)
	public void TC007RetailTransferXBWire_Korea() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB007");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Bosnia and Herzegovina Recipient", priority = 8)
	public void TC008RetailTransferXBWire_BosniaAndHerzegovina() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB008");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Indonesia Recipient", priority = 9)
	public void TC009RetailTransferXBWire_Indonesia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB009");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankRoutingCode(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Peru Recipient", priority = 10)
	public void TC010RetailTransferXBWire_Peru() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB010");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterRecTaxIdDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Thailand Recipient", priority = 11)
	public void TC011RetailTransferXBWire_Thailand() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB011");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Japan Recipient", priority = 12)
	public void TC012RetailTransferXBWire_Japan() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB012");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Malaysia Recipient", priority = 13)
	public void TC013RetailTransferXBWire_Malaysia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB013");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Mexico Recipient", priority = 14)
	public void TC014RetailTransferXBWire_Mexico() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB014");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterRecTaxIdDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Oman Recipient", priority = 15)
	public void TC015RetailTransferXBWire_Oman() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB015");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Morocco Recipient", priority = 16)
	public void TC016RetailTransferXBWire_Morocco() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB016");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for South Africa Recipient", priority = 17)
	public void TC017RetailTransferXBWire_SouthAfrica() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB017");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankRoutingCode(objReciDtls);}
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Singapore Recipient", priority = 18)
	public void TC018RetailTransferXBWire_Singapore() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB018");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankRoutingCode(objReciDtls);
			AddRecipientDetails.enteBankAddressDetails(objReciDtls);}
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Canada Recipient", priority = 19)
	public void TC019RetailTransferXBWire_Canada() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB019");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		AddRecipientDetails.enterBankBranchCode(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}	

	@Test(testName = "Transfer Money for Philippines Recipient", priority = 20)
	public void TC020RetailTransferXBWire_Philippines() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB020");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPurposeofPmtCode();}
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for New Zealand Recipient", priority = 21)
	public void TC021RetailTransferXBWire_NewZealand() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB021");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankBranchCode(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for VietNam  Recipient", priority = 22)
	public void TC022RetailTransferXBWire_VietNam() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB022");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankRoutingCode(objReciDtls);}
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for United Arab Emirates  Recipient", priority = 23)
	public void TC023RetailTransferXBWire_UnitedArabEmirates() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB023");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPurposeofPmtCode();}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Australia Recipient", priority = 24)
	public void TC024RetailTransferXBWire_Australia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB024");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankBranchCode(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Nigeria Recipient", priority = 25)
	public void TC025RetailTransferXBWire_Nigeria() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB025");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Hong Kong Recipient", priority = 26)
	public void TC026RetailTransferXBWire_HongKong() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB026");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterBankRoutingCode(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Czechia Recipient", priority = 27)
	public void TC027RetailTransferXBWire_Czechia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB027");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Denmark Recipient", priority = 28)
	public void TC028RetailTransferXBWire_Denmark() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB028");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for United Kingdom of Great Britain and Northern Ireland EURO Recipient", priority = 29)
	public void TC029RetailTransferXBWire_UKEURO() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB029");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
			AddRecipientDetails.enterIBANDetails(objReciDtls);}
		else {
			AddRecipientDetails.enterUKDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();		
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Norway Recipient", priority = 30)
	public void TC030RetailTransferXBWire_Norway() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB030");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();		
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Poland Recipient", priority = 31)
	public void TC031RetailTransferXBWire_Poland() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB031");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}



	@Test(testName = "Transfer Money for Sweden Recipient", priority = 32)
	public void TC032RetailTransferXBWire_Sweden() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB032");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Switzerland Recipient", priority = 33)
	public void TC033RetailTransferXBWire_Switzerland() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB033");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Turkey Recipient", priority = 34)
	public void TC034RetailTransferXBWire_Turkey() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB034");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Israel Recipient", priority = 35)
	public void TC035RetailTransferXBWire_Israel() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB035");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Qatar Recipient", priority = 36)
	public void TC036RetailTransferXBWire_Qatar() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB036");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Egypt Recipient", priority = 37)
	public void TC037RetailTransferXBWire_Egypt() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB037");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Hungary Recipient", priority = 38)
	public void TC038RetailTransferXBWire_Hungary() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB038");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Saudi Arabia Recipient", priority = 39)
	public void TC039RetailTransferXBWire_SaudiArabia() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB039");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Kuwait Recipient", priority = 40)
	public void TC040RetailTransferXBWire_Kuwait() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB040");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Romania Recipient", priority = 41)
	public void TC041RetailTransferXBWire_Romania() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB041");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Bahrain Recipient", priority = 42)
	public void TC042RetailTransferXBWire_Bahrain() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB042");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails3(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPurposeofPmtCode();}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for United Kingdom of Great Britain and Northern Ireland Recipient", priority = 43)
	public void TC043RetailTransferXBWire_UKGBP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB043");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterUKDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Transfer Money for Chile Recipient", priority = 44)
	public void TC044RetailTransferXBWire_Chile() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB044");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterChileDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}

	@Test(testName = "Transfer Money for Pakistan Recipient", priority = 45)
	public void TC045RetailTransferXBWire_Pakistan() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB045");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPakistanDetails(objReciDtls);
			AddRecipientDetails.enterPurposeofPmtCode();}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Edit click in Review Page and do Transfer Money for Pakistan Recipient", priority = 46)
	public void TC046EditRetailTransferPakistanToChileDetails_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB045");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterPakistanDetails(objReciDtls);
		AddRecipientDetails.enterPurposeofPmtCode();
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddRecipientDetails.editClick();
		//Editing All Details from Pakistan to Chile Country
		objReciDtls=TestDataReader.loadXBWireDetails("XB044");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterChileDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
	}


	@Test(testName = "Assert Edited Fee is Showing Same in OPS UI, Transfer for Pakistan Recipient", priority = 47)
	public void TC047AssertEditedFeeShowingSameinOPS_XBWire_Pakistan() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB004");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB046");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		String fee=AddRecipientDetails.editFee();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.checkPrefundedModel(objSenderDtls);
		AddSenderDetails.AssertEditedFee(fee);
	}


	@Test(testName = "Save Transfer Without entering Questionnaire and then Submit", priority = 48)
	public void TC048SaveTransferWithoutQuestionaireAndSubmit_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.saveTransfer();
		AddSenderDetails.selectQuestionaire();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.submitQuestionaire();
		AddSenderDetails.submitAndInProcess();
	}
	
	@Test(testName = "Save Transfer With entering Questionnaire and then Submit", priority = 49)
	public void TC049SaveTransferWithQuestionaireAndSubmit_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		if(!objReciDtls.getCurrency().equals("USD")){
			AddRecipientDetails.enterPhoneNoDetails(objReciDtls);}
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.enterNotes();
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.saveTransfer();
		AddSenderDetails.selectQuestionaire();
		AddSenderDetails.submitQuestionaire();
		AddSenderDetails.submitAndInProcess();
	}
	
	@Test(testName = "Save Transfer With entering Questionnaire and then Submit", priority = 50)
	public void TC050SaveAndCheckRecipientThenDelete_VCURetailTransfer_XBWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB003");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("XB046");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		String senderName=AddSenderDetails.getSenderName(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterIBANDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.saveRecipientCheckbox();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.clickContinue_For_XBwireCases();
		AddSenderDetails.xbDirectSubmitMsg();	
		AddSenderDetails.closeBtn();
		AddSenderDetails.Clik_Recipient_Link_DepOp();
		AddRecipientDetails.RecipientAdvanceSearch_DepOp(objSenderDtls, senderName);
		AddRecipientDetails.selectExistRecipient_DepOp(objReciDtls);
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
		AddSenderDetails.closeBtn();
	}

	@AfterClass
	public void endSuite() throws Exception {
		Utility.closeBrowser(WD);
	}
}