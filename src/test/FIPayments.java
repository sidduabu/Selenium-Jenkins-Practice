package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

import dataReader.TestDataReader;
import dataReader.Utility;
import pages.Login;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;

public class FIPayments extends Utility {
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
		AddSenderDetails.selectPaymentsManager();
	}

	
	@Test(testName = "Direct RTP Payment for a Exist Recipient_Positive", priority = 1)
	public void TC001DirectSubmitRTPwithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC005");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Direct FedNow Payment for a Exist Recipient_Positive", priority = 2)
	public void TC002DirectSubmitFedNowwithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC006");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Direct FedWire Current Date Payment Exist Recipient_Positive", priority = 3)
	public void TC003DirectSubmitFedWireCurrentDatePmtWithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Direct FedWire Future Date Payment Exist Recipient_Positive", priority = 4)
	public void TC004DirectSubmitFedWireFutureDatePmtWithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC008");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}


	@Test(testName = "Direct Submit RTP Payment for a New Recipient_Positive", priority = 5)
	public void TC005DirectSubmitRTPwithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC001");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Direct Submit FedNow Payment for a New Recipient_Positive", priority = 6)
	public void TC006DirectSubmitFedNowWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC002");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Direct Submit FedWire Future Date Payment", priority = 7)
	public void TC007DirectSubmitFedWireFutureDateWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}

	@Test(testName = "Direct Submit FedWire Current Date Payment", priority = 8)
	public void TC008DirectSubmitFedWireCurrentDatePmtWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	

	@Test(testName = "Check Invisibility of Suspended Recipient while doing Payment", priority = 9)
	public void TC009CheckInvisibilityofSuspendedRecipientWhilePymt() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC012");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.checkDisabledRecipient(objSenderDtls);
	}

	@Test(testName = "Check Invisibility of Revoked Recipient while doing Payment", priority = 10)
	public void TC010CheckInvisibilityofRevokedRecipientWhilePymt() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC013");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.checkDisabledRecipient(objSenderDtls);
	}

	@Test(testName = "Check Invisibility of Deleted Recipient while doing Payment", priority = 11)
	public void TC011CheckInvisibilityofDeletedRecipientWhilePymt() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC014");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.checkDisabledRecipient(objSenderDtls);
	}


	@Test(testName = "Direct Submit Pymnt with Awaited Suspend Recipient", priority = 12)
	public void TC012DirectSubmitPymntwithAwaitedSuspendRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC015");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Direct Submit Pymnt with Awaited Revoked Recipient", priority = 13)
	public void TC013DirectSubmitPymntwithAwaitedRevokedRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC016");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Direct Submit Pymnt with Awaited Deleted Recipient", priority = 14)
	public void TC014DirectSubmitPymntwithAwaitedDeletedRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC017");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	
	@Test(testName = "Direct Submit DDA Account RTP Payment for a New Recipient_Positive", priority = 15)
	public void TC015DirectSubmitDDAAccountTypeRTPwithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC018");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	
	@Test(testName = "Direct Submit Ledger Account RTP Payment for a New Recipient_Positive", priority = 16)
	public void TC016DirectSubmitLedgerAccountTypeRTPwithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC019");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	
	
	@Test(testName = "Direct Submit FedWire DDA Account Payment", priority = 17)
	public void TC017DirectSubmitFedWireDDAAccountWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC020");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	
	
	@Test(testName = "Direct Submit FedWire Ledger Account Payment", priority = 18)
	public void TC018DirectSubmitFedWireLedgerAccountWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC021");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	
	/*------  Edit Click in Review Page and Continue by editing details  ----*/

	@Test(testName = "Edit Click FedWire Future Date Payment in Review Page", priority = 19)
	public void TC019EditFedWireFutureDatePmntWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC003");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddRecipientDetails.editClick();
		objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}
	

	@Test(testName = "Edit Click FedWire Current Date Payment in Review Page", priority = 20)
	public void TC020EditFedWireCurrentDatePmtWithNewRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC004");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	    AddRecipientDetails.editClick();
		objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	
	
	@Test(testName = "Edit Click FedWire Current Date Payment Exist Recipient_Positive", priority = 21)
	public void TC021EditFedWireCurrentDatePmtWithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	    AddRecipientDetails.editClick();
	    objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Edit Click FedWire Future Date Payment Exist Recipient_Positive", priority = 22)
	public void TC022EditFedWireFutureDatePmtWithExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC008");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC004");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWFuturePaymentDate(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
	    AddRecipientDetails.editClick();
	    objReciDtls=TestDataReader.loadRecipientDetails("TC013");
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.futureDatePymtSubmitMsg();
	}
	
	
	@Test(testName = "Sender to Recipient Fields Payment Exist Recipient", priority = 23)
	public void TC023SenderToRecipientAllFieldsPayment() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC007");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.senderToRecipientAllFields();
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}
	

	
	/*--  ONUS Cases with VCU as Debitor and Creditor---*/
	
	@Test(testName = "OnUs Direct RTP Payment for a Exist Recipient_Positive", priority = 24)
	public void TC024_OnUsDirectSubmitRTPwithExistRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS01");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}

	@Test(testName = "OnUs Direct FedNow Payment for a Exist Recipient_Positive", priority = 25)
	public void TC025_OnUsDirectSubmitFedNowwithExistRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS02");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs Direct FedWire Current Date Payment Exist Recipient_Positive", priority = 26)
	public void TC026_OnUsDirectSubmitFedWireCurrentDatePmtWithExistRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS03");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}



	@Test(testName = "OnUs Direct Submit RTP Payment for a New Recipient_Positive", priority = 27)
	public void TC027_OnUsDirectSubmitRTPwithNewRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS04");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS01");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs Direct Submit FedNow Payment for a New Recipient_Positive", priority = 28)
	public void TC028_OnUsDirectSubmitFedNowWithNewRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS05");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS02");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}


	@Test(testName = "OnUs Direct Submit FedWire Current Date Payment", priority = 29)
	public void TC029_OnUsDirectSubmitFedWireCurrentDatePmtWithNewRecipient_VCU() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("ONUS06");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("ONUS03");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterRecipientDetails(objReciDtls);
		AddRecipientDetails.enterAddressDetails(objReciDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
		AddSenderDetails.checkONUS();
	}
	
	

	@Test(testName = "RTP Payment with all Networks Exist Recipient_Positive", priority = 30)
	public void TC030DirectSubmitRTPwithAllNetworksExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC040");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC001");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}

	@Test(testName = "Fednow Payment with all Networks Exist Recipient_Positive", priority = 31)
	public void TC031DirectSubmitFednowwithAllNetworksExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC041");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC002");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.uploadRelatedDocAndSelectDocType(objReciDtls);
		AddRecipientDetails.enterRTPRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
	}


	@Test(testName = "Fedwire Payment with all Networks Exist Recipient_Positive", priority = 32)
	public void TC032DirectSubmitFedwirewithAllNetworksExistRecipient() throws Exception {
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("TC042");
		AddRecipientDetails objReciDtls=TestDataReader.loadRecipientDetails("TC003");
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.selectNetworkInExistingList(objSenderDtls);
		AddSenderDetails.enterSenderDetails(objSenderDtls);
		AddRecipientDetails.enterAmount(objReciDtls);
		AddRecipientDetails.enterFWireRemittanceDetails(objReciDtls);
		AddRecipientDetails.continueBtnClick_With_TravelRuleMethod();
		AddSenderDetails.directSubmitMsg();
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