package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
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
import pages.XBWireFieldValidationPage;

public class XBWireFieldValidation extends Utility{

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


	@Test(testName = "Country Should Not Empty Msg", priority = 1)
	public void TC001CountryShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.emptyCountryMsg();
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getCountryEmptyMsg());		

	}

	@Test(testName = "Currency Should Not Empty Msg", priority = 2)
	public void TC002CurrencyShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC002");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getCurrencyEmptyMsg());		

	}

	@Test(testName = "Contact Should Not Empty Msg", priority = 3)
	public void TC003ContactNameShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getContactNameEmptyMsg());		

	}

	@Test(testName = "Invalid Contact Name Msg", priority = 4)
	public void TC004InvalidContactNameMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidContactNameMsg());		

	}


	@Test(testName = "Account Num Should Not Empty Msg", priority = 5)
	public void TC005AccountNumShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC005");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getAccountNoNotEmptyMsg());		

	}

	@Test(testName = "Invalid Account Num Msg", priority = 6)
	public void TC006InvalidAccountNumMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC006");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidAccountNoMsg());		
	}



	@Test(testName = "Account Num Less Than Minimum Required", priority = 7)
	public void TC007AccountNumLessThanMinReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC007");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getAccountNoLessThanMinMsg());		
	}


	@Test(testName = "Bank Routing Num Should Not Empty Msg", priority = 8)
	public void TC008BankRoutingNumShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC008");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getBankRoutingNotEmptyMsg());		

	}


	@Test(testName = "Invalid Bank Routing Number Msg", priority = 9)
	public void TC009InvalidBankRoutingNumMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC009");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidRoutingMsg());		
	}


	@Test(testName = "Phone Num Should Not Empty Msg", priority = 10)
	public void TC010PhoneNumShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC010");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPhoneNoNotEmptyMsg());		
	}

	@Test(testName = "Invalid Phone Num Msg", priority = 11)
	public void TC011InvalidPhoneNumMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC011");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidPhoneNoMsg());		
	}


	@Test(testName = "Phone Num Less Than Minimum Required", priority = 12)
	public void TC012PhoneNumLessThanMinReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC012");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPhoneNoLessThanMinMsg());		
	}


	@Test(testName = "BIC Should Not Empty Msg", priority = 13)
	public void TC013BICShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC013");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getBICNotEmptyMsg());		
	}


	@Test(testName = "Invalid BIC Msg", priority = 14)
	public void TC014InvalidBICMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC014");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getBicErrorMsg(), objErrDtls.getInvalidBICMsg());		
	}


	@Test(testName = "BIC Less Than Minimum Req Msg", priority = 15)
	public void TC015BICLessThanMinReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC015");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getBicErrorMsg(), objErrDtls.getBICLessThanMinMsg());		
	}



	@Test(testName = "TaxID Should Not Empty Msg", priority = 16)
	public void TC016TaxIDShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC016");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getTaxIdNotEmptyMsg());		
	}


	@Test(testName = "Invalid TaxID Msg", priority = 17)
	public void TC017InvalidTaxIDMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC017");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidTaxIdMsg());		
	}


	@Test(testName = "TaxId Less Than Minimum Req Msg", priority = 18)
	public void TC018TaxIdLessThanMinReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC018");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterArgentinaDetails(objReciDtls);
		AddRecipientDetails.enterPhoneNoDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getTaxIdLessThanMinMsg());		
	}


	@Test(testName = "Nationality Should Not Empty Msg", priority = 19)
	public void TC019NationalityShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC019");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterBangladeshDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getNationalityEmptyMsg());		
	}


	@Test(testName = "Purpose Of Payment Should Not Empty Msg", priority = 20)
	public void TC020PurposeOfPaymentShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC020");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterBangladeshDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPurposeOfPayNotEmptyMsg());		
	}


	@Test(testName = "Passport Num Should Not Empty Msg", priority = 21)
	public void TC021PassportNumShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC021");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterBangladeshPassPort(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPassportNoNotEmptyMsg());		
	}


	@Test(testName = "Invalid Passport Num Msg", priority = 22)
	public void TC022InvalidPassPortNumMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC022");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterBangladeshPassPort(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidPassportNoMsg());		
	}


	@Test(testName = "Passport Num Less Than Min Req Msg", priority = 23)
	public void TC023PassportNumLessthanMinReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC023");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterBangladeshPassPort(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPassportNoLessThanMinMsg());		
	}



	@Test(testName = "IFSC Should Not Empty Msg", priority = 24)
	public void TC024IFSCShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC024");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterIndiaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getIFSCEmptyMsg());		
	}

	@Test(testName = "Invalid IFSC Msg", priority = 25)
	public void TC025InvalidIFSCMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC025");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterIndiaDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidIFSCMsg());		
	}

	@Test(testName = "Purpose of Payment Code Should Not Empty Msg", priority = 26)
	public void TC026PurposeofPaymentCodeShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC026");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPakistanDetails(objReciDtls);
		AddRecipientDetails.withoutPurposeofPmtCode();
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getPurposeOfPaymntCodeEmptyMsg());		
	}



	@Test(testName = "IBAN Should Not Empty Msg", priority = 27)
	public void TC027IBANShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC027");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPurposeofPmtCode();
		AddRecipientDetails.enterPakistanDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getIBANEmptyMSg());		
	}


	@Test(testName = "Invalid IBAN Msg", priority = 28)
	public void TC028InvalidIBANMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC028");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPurposeofPmtCode();
		AddRecipientDetails.enterPakistanDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getInvalidIBANMsg());		
	}

	@Test(testName = "IBAN Min Less than Req Msg", priority = 29)
	public void TC029IBANMinLessThanReqMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC029");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterPurposeofPmtCode();
		AddRecipientDetails.enterPakistanDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getIBANMinLessThanMsg());		
	}


	@Test(testName = "BankBranch Address Should Not Empty Msg", priority = 30)
	public void TC030BankBranchAddressShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC030");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterBankRoutingCode(objReciDtls);
		AddRecipientDetails.emptyAddress();
		AddRecipientDetails.enterBankAccountNo(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getBankBranchAddressEmptyMsg());		
	}


	@Test(testName = "Recipient Account Methods Should Not Empty Msg", priority = 31)
	public void TC031RecipientAccountMethodsShouldNotEmptyMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC031");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterUKDetails(objReciDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.getErrorMsg(), objErrDtls.getRecAccountMethodsEmptyMsg());		
	}

	@Test(testName = "Invalid Address Fields and End To End Msg", priority = 32)
	public void TC032InvalidAddressFieldsAndEndToEndIdMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddRecipientDetails objReciDtls=TestDataReader.loadXBWireDetails("TC032");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.selectCountryDetails(objReciDtls);
		AddRecipientDetails.enterXBRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterCommonMethodDetails1(objReciDtls);
		AddRecipientDetails.enterIndiaDetails(objReciDtls);
		XBWireFieldValidationPage.enterInvalidAddressFields();
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		List<String> actualErrorMsgs=XBWireFieldValidationPage.getAllErrorMsg();
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidRecipientNameMsg()),"Expected error message not found: " + objErrDtls.getInvalidRecipientNameMsg());
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidAddressLineMsg()),"Expected error message not found: " + objErrDtls.getInvalidAddressLineMsg());
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidTownMsg()),"Expected error message not found: " + objErrDtls.getInvalidTownMsg());
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidStateMsg()),"Expected error message not found: " + objErrDtls.getInvalidStateMsg());
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidZipMsg()),"Expected error message not found: " + objErrDtls.getInvalidZipMsg());
		Assert.assertTrue(actualErrorMsgs.contains(objErrDtls.getInvalidEndTOEndMsg()),"Expected error message not found: " + objErrDtls.getInvalidEndTOEndMsg());
	}


	@Test(testName = "Invalid Sender To Recipient Msg", priority = 33)
	public void TC033InvalidSenderToRecipMsg() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("XB001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		XBWireFieldValidationPage objErrDtls=TestDataReader.loadXBWireFieldsData();
		Assert.assertEquals(XBWireFieldValidationPage.assertInvalidSenderToRec(), objErrDtls.getInvalidSenderRecMsg());		
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