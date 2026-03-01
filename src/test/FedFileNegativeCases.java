package test;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import dataReader.ErrorMessageData;
import dataReader.TestDataReader;
import dataReader.Utility;
import pages.AddSenderDetails;
import pages.Login;

public class FedFileNegativeCases extends Utility {
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

	}


	/* --------------   FI Payments Cases               ------------*/


	@Test(testName = "Upload FedFile with Max Lengths check", priority = 1)
	public void TC001UploadFedFilewithMaxLengthsCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE04");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getFedFileMaxLengthsMsg());
	}


	@Test(testName = "Upload FedFile with Min Lengths check", priority = 2)
	public void TC002UploadFedFilewithMinLengthsCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE05");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getFedFileMinLengthsMsg());

	}


	@Test(testName = "Upload FedFile with Empty Tags check", priority = 3)
	public void TC003UploadFedFilewithEmptyTagsCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE06");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getEmptyTagsFedFileMsg());

	}

	@Test(testName = "Upload FedFile with Invalid Amount Tags check", priority = 4)
	public void TC004UploadFedFilewithInvalidAmountTagsCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE07");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getInvalidAmtFedFileMsg());

	}

	@Test(testName = "Upload Empty FedFile with No Tags", priority = 5)
	public void TC005UploadEmptyFedFilewithNoTagsCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE08");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getEmptyFedFileErrorMsg(), objError.getEmptyMsgFI());

	}

	@Test(testName = "Upload Maximum FedFile size > 10MB", priority = 6)
	public void TC006UploadMaxFedFileSizeGreaterThan10MBCheck_FI() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("FFILE09");
		AddSenderDetails.selectPaymentsManager();
		AddSenderDetails.Clik_Payments_Link_FI();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getGreaterFedFileErrorMsg(), objError.getGreaterSizeFedFile());

	}


	/* --------------   Deposit Operations Cases               ------------*/



	@Test(testName = "Upload FedFile with Max Lengths check", priority = 7)
	public void TC007UploadFedFilewithMaxLengthsCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE04");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getFedFileMaxLengthsMsg());
	}


	@Test(testName = "Upload FedFile with Min Lengths check", priority = 8)
	public void TC008UploadFedFilewithMinLengthsCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE05");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getFedFileMinLengthsMsg());
	}


	@Test(testName = "Upload FedFile with Empty Tags check", priority = 9)
	public void TC009UploadFedFilewithEmptyTagsCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE06");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getEmptyTagsFedFileMsg());
	}


	@Test(testName = "Upload FedFile with Invalid Amount Tags check", priority = 10)
	public void TC010UploadFedFilewithInvalidAmountTagsCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE07");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getFedFileErrorMsg(), objError.getInvalidAmtFedFileMsg());
	}


	@Test(testName = "Upload Empty FedFile with No Tags", priority = 11)
	public void TC011UploadEmptyFedFilewithNoTagsCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE08");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getEmptyFedFileErrorMsg(), objError.getEmptyFileMsg());
	}


	@Test(testName = "Upload Maximum FedFile size > 10MB", priority = 12)
	public void TC012UploadMaxFedFileSizeGreaterThan10MBCheck_Depo() throws Exception{
		AddSenderDetails objSenderDtls=TestDataReader.loadSenderDetails("DFILE09");
		AddSenderDetails.selectCustomerSupport();
		AddSenderDetails.Clik_Transfers_Link_DepOp();
		AddSenderDetails.select_ClinetsRoutingNum(objSenderDtls);
		AddSenderDetails.payBtnClick_SelectDivision_PmtNetwork(objSenderDtls);
		AddSenderDetails.uploadFedFileDoc(objSenderDtls);
		ErrorMessageData objError=TestDataReader.loadErrorMsg();
		Assert.assertEquals(AddSenderDetails.getGreaterFedFileErrorMsg(), objError.getGreaterSizeFedFile());
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

