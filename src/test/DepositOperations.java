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

public class DepositOperations extends Utility {
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



	@Test(testName = "RTP With New Recipient_Retail Transfer", priority = 1)
	public void TC001DirectSubmitWithNewRecipientRetailTransfer_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedNow Direct Submit With New Recipient_Retail Transfer", priority = 2)
	public void TC002DirectSubmitWithNewRecipientRetailTransfer_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP008");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "FedWire Direct Submit with New Recipient_Retail Transfer", priority = 3)
	public void TC003DirectSubmitFutureDateForNewRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP009");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "FedWire Direct Submit With a New Recipient_Retail Transfer", priority = 4)
	public void TC004DirectSubmitCurrentDateForNewRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}



	@Test(testName = "RTP Direct Submit With New Recipient_Business Transfer", priority = 5)
	public void TC005DirectSubmitWithNewRecipientBusinessTransfer_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP011");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "FedNow Direct Submit with New Recipient_Business Transfer", priority = 6)
	public void TC006DirectSubmitWithNewRecipientBusinessTransfer_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP012");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "FedWire Direct Submit with New Recipient_Business Transfer", priority = 7)
	public void TC007DirectSubmitFutureDateForNewRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP013");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "FedWire Direct Submit with New Recipient_Business Transfer", priority = 8)
	public void TC008DirectSubmitCurrentDateForNewRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP014");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "FedWire Direct Submit with Exist Recipient_Retail Transfer", priority = 9)
	public void TC009DirectSubmitFutureDateExistRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP025");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "FedWire Direct Submit With a Exist Recipient_Retail Transfer", priority = 10)
	public void TC010DirectSubmitCurrentDateExistRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP026");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "FedWire Direct Submit with Exist Future Date Recipient_Business Transfer", priority = 11)
	public void TC011DirectSubmitFutureDateExistRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "FedWire Direct Submit with Current Date Exist Recipient_Business Transfer", priority = 12)
	public void TC012DirectSubmitCurrentDateExistRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP028");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	/*------  Edit Click in Review Page and Continue by editing details  ----*/

	@Test(testName = "Edit Click in Review Page and FedWire Future Date Transfer New Recipient_Retail Transfer", priority = 13)
	public void TC013EditFutureDateWithNewRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP009");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}


	@Test(testName = "Edit Click in Review Page and FedWire Current Date Transfer New Recipient_Retail Transfer", priority = 14)
	public void TC014EditCurrentDateWithNewRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Edit Click in Review Page and FedWire Future Date Transfer New Recipient_Business Transfer", priority = 15)
	public void TC015EditFutureDateWithNewRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP013");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}


	@Test(testName = "Edit Click in Review Page and FedWire Current Date Transfer New Recipient_Business Transfer", priority = 16)
	public void TC016EditCurrentDateWithNewRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP014");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Edit Click in ReviewPage and FedWire FutureDate Retail Transfer With Exist Recipient", priority = 17)
	public void TC017EditFutureDateExistRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP025");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "Edit Click in ReviewPage and FedWire CurrentDate Retail Transfer With Exist Recipient", priority = 18)
	public void TC018EditCurrentDateExistRecipientRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP026");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Edit Click in ReviewPage and FedWire FutureDate Business Transfer With Exist Recipient", priority = 19)
	public void TC019EditFutureDateExistRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP027");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}


	@Test(testName = "Edit Click in ReviewPage and FedWire CurrentDate Business Transfer With Exist Recipient", priority = 20)
	public void TC020EditCurrentDateExistRecipientBusinessTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP028");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		/* Edit Transfer with another Recipient*/
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Payment Questionnaire Check For Branch Channel RetailTransfer_FedWire", priority = 21)
	public void TC021PymntQuestionnaireForBranch_RetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP029");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	
	/*--- OnUs Transfers with VCU Client ----*/

	@Test(testName = "OnUs RTP With New Recipient_Retail Transfer", priority = 22)
	public void TC022_OnUsRTPDirectSubmitWithNewRecipientRetailTransfer_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}

	@Test(testName = "FedNow Direct Submit With New Recipient_Retail Transfer", priority = 23)
	public void TC023_OnUsFedNowDirectSubmitWithNewRecipientRetailTransfer_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST02");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS02");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}

	@Test(testName = "FedWire Direct Submit With a New Recipient_Retail Transfer", priority = 24)
	public void TC024_OnUsFedWireDirectSubmitCurrentDateForNewRecipientRetailTransfer_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}
	

	@Test(testName = "FedWire Direct Submit With a Exist Recipient_Retail Transfer", priority = 25)
	public void TC025_OnUsFedWireDirectSubmitCurrentDateExistRecipientRetailTransfer_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUST04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}
	

	/*----Fee Related Cases------*/
	
	@Test(testName = "Call Center Channel_Fee_BillSeparatelyRetailTransfer_RTP", priority = 26)
	public void TC026Fee_BillSeparatelyRetailTransfer_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkBillSeparately();
	}
	
	@Test(testName = "Branch Channel_Fee_ChargedToPrincipleRetailTransfer_RTP", priority = 27)
	public void TC027Fee_ChargedToPrincipleRetailTransfer_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE02");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkChargedToPrinciple();
	}
	
	@Test(testName = "Assisted Teller Channel_NoFee_RetailTransfer_RTP", priority = 28)
	public void TC028NoFee_RetailTransfer_RTP() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkNoFee();
	}
	

	@Test(testName = "Call Center Channel_Fee_BillSeparatelyRetailTransfer_FedNow", priority = 29)
	public void TC029Fee_BillSeparatelyRetailTransfer_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkBillSeparately();
	}
	
	@Test(testName = "Branch Channel_Fee_ChargedToPrincipleRetailTransfer_FedNow", priority = 30)
	public void TC030Fee_ChargedToPrincipleRetailTransfer_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE05");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkChargedToPrinciple();
	}
	
	@Test(testName = "Assisted Teller Channel_NoFee_RetailTransfer_FedNow", priority = 31)
	public void TC031NoFee_RetailTransfer_FedNow() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE06");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod(); 
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkNoFee();
	}
	

	@Test(testName = "Call Center Channel_Fee_BillSeparatelyRetailTransfer_FedWire", priority = 32)
	public void TC032Fee_BillSeparatelyRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE07");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkBillSeparately();
	}
	
	@Test(testName = "Branch Channel_Fee_ChargedToPrincipleRetailTransfer_FedWire", priority = 33)
	public void TC033Fee_ChargedToPrincipleRetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE08");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkChargedToPrinciple();

	}
	
	@Test(testName = "Assisted Teller Channel_NoFee_RetailTransfer_FedWire", priority = 34)
	public void TC034NoFee_RetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FEE09");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.totalAmountDisplayed();
		AddSenderDetails.paymentQuestionnaireBusinessTransfer();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkNoFee();
	}
	
	

	@Test(testName = "FedWire Direct Submit With a New Recipient_Retail Transfer", priority = 35)
	public void TC035SaveTransferWithQuestionaireAndSubmit_RetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterNotes();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.saveTransfer();
		AddSenderDetails.selectQuestionaire();
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddSenderDetails.submitQuestionaire();
		AddSenderDetails.submitAndProcessed();
	}
	
	@Test(testName = "FedWire Direct Submit With a New Recipient_Retail Transfer", priority = 36)
	public void TC036SaveTransferWithoutQuestionaireAndSubmit_RetailTransfer_FedWire() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DP010");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderAndChannelDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddSenderDetails.paymentQuestionnaire_RetailTransfers(objSenderDtls);
		AddRecipientDetails.enterNotes();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.saveTransfer();
		AddSenderDetails.submitAndProcessed();
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