package pages;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dataReader.Utility;

public class AddSenderDetails extends Utility{


	private static By Payments_Link_FI = By.xpath("//div[@id= 'PM:PaymentsManager']//span[text()='Payments']");
	private static By Recipient_Link_FI = By.xpath("//div[@id= 'PM:PaymentsManager']//span[text()='Recipients']");
	private static By Transfers_Link_DepOp = By.xpath("//div[@id= 'CS:CustomerSupport']//*[text()='Transfers']");
	private static By Recipient_Link_DepOp =By.xpath("//div[@id='CS:CustomerSupport']//*[text()='Recipients']");
	private static By backOfficeOperations = By.xpath("//a[@aria-controls='BackOfficeOperations']");
	private static By client_Routing_DropDown=By.id("client_selection_level1");
	private static By payBtn=By.id("pay-btn");
	private static By AddRecipientBtn=By.linkText("Add Recipient");

	private static By duplicateSenderDetails= By.xpath("//app-dupl-pymnt-msg//div[label[normalize-space()='Sender:']]/strong");

	private static By NewRecipientDropDwn_FI = By.id("pm-select-recip");
	private static By RecipientSearchBox_FI = By.id("search-recipient");
	private static By SelectRecipArrow_FI = By.id("select-recip-arrow");

	private static By selectDivision=By.id("pm-select-div");
	private static By searchDivision=By.xpath("//*[@id='search-div']");
	static By selectLedger=By.xpath("//mat-select[@formcontrolname='ledgerAccount']/div/div[1]/span");
	static By senderAccNo=By.xpath("//input[contains(@id, 'pay_from_sender')]");
	private static By ledger_CostCenter = By.id("pay_from_ledg_acc_cost_center");
	private static By payFromTitle = By.xpath("//div[text()='Pay From']");
	static By senderAccType=By.id("payfrom_sender_account_type");
	private static By Ledger_NameOnAcc_Field=By.id("pay_from_name_on_acc");
	private static By selectExsRecpt=By.id("pm-select-recip");
	private static By searchRecpt=By.id("search-recipient");
	private static By arrowBtn=By.id("select-recip-arrow");
	static By submitForApproveBtn = By.linkText("Submit for Approval");
	static By closeBtn=By.xpath("//a[contains(@class, 'close_button cursor-pointer ng-tn')]");
	private static By profileIcon=By.id("user_profile_icon");
	private static By logOff=By.xpath("//*[@id='user_profile_dropdown']//button[text()='Log Off']");
	private static By ApproveBtn=By.xpath("//*[@id='actions']//a[text()=' Approve ']");
	private static By DeclineBtn=By.xpath("//*[@id='actions']//a[text()=' Decline ']");
	static By DeclineSubmit=By.linkText("Decline");
	static By confirmNo=By.xpath("(//div[@class='form-group']//strong[@class='wrap-long-text'])[2]");
	private static By paymntNetwork=By.id("create_recipient_payment_method");
	static By paymentStatus=By.xpath("//span[@class='detail_item']//span[contains(@class, 'ng-star-inserted')]");
	static By searchPendingPmt=By.id("simple-search");
	static By searchBtn=By.xpath("//a[@class='input-group-addon search_btn']");
	static By memo=By.xpath("//*[@formcontrolname='memo']");
	static By ApproveSubmit=By.linkText("Approve");
	static By submitClick=By.linkText("Submit");
	static By DepOpSenderAccNo=By.xpath("//app-capture-sender-account[@controlname='senderAccount']//input");
	static By ChannelType=By.xpath("//mat-select[contains(@id, 'recipient_channel')]");
	static By DepoNewRecLink=By.xpath("//a[text()=' New Recipient ']");
	private static By recipientSearch=By.xpath("//input[contains(@id, '_recipient_search')]");
	private static By dashBoard= By.id("BO:Dashboard");
	private static By FedFileErrorMsg= By.xpath("//*[@id='fedwire_file_upload_errors']//div[@class='message_block']");
	private static By greaterFedFileErrorMsg= By.id("filesize_errorfield");
	private static By EmptyFedFileErrorMsg= By.xpath("//div[@id='filesize_errorfield']//span");
	private static By alertErrorMsg= By.xpath("//*[@id='scroll-sticky-alert-msg']//span[@class='alert-text']");

	static By senderAddressLineField = By.xpath("//*[@id='struc_sender_address_form']//input[@id='addr-lines']");
    static By senderTownNameField = By.xpath("//*[@id='struc_sender_address_form']//input[@id='town']");
    static By senderStateField = By.xpath("//[@id='struc_sender_address_form']//[contains(@id, 'state')]");
    static By senderCountryField = By.xpath("//[@id='struc_sender_address_form']//[@id='countryCode']");
    static By senderZipcodeField = By.xpath("//*[@id='struc_sender_address_form']//input[contains(@id,'addr-zip')]");
	private static By appsWidgetPanel_RightCornor = By.id("right_header_holder");
	//For few sender account number multiple names will be there(Name DropDown will visible)
	private static By senderName = By.id("sender-name");
	// For few sender account number only one Name will be there (Name DropDown will not visible)	
	//		private static By senderName = By.xpath("//div[@class='form-group col-sm-6 float-left ng-star-inserted']//strong");


	private static By PaymentManagerDropDown = By.xpath("//*[contains(@aria-controls, 'ymentsManager')]");
	private static By CustomerSupportDropDown = By.xpath("//*[contains(@aria-controls, 'stomerSupport')]");
	private static By ConsumerAndBusinessTransfersDropDown = By.xpath("//*[contains(@aria-controls, 'nsumerTransfers')]");

//	private static By tms_Pmt_Info_AfterSubmit =  By.xpath("//div[@id='basicDetails']//label[@class='btn-block']");
	private static By senderAccountType_FI = By.xpath("//mat-select[@placeholder='Select' and @aria-invalid='true']");
	private static By senderAccountType_DepOp = By.id("acc-sub-type");
//	private static By ops_TransactionTimeLine = By.id("txn_details");
//	private static By ops_Transaction_Info = By.id("basicDetails");
//	private static By ops_TransactionTimelineStages = By.xpath("//div[@id='txnTimeLine']//label[text()='Stage:']");
//	private static By ops_Transaction_Info_InstructionID = By.xpath("//div[@id='basicDetails']//label[text()='Instruction Id:']");
	private static By payFromTitle_ForScrolling = By.xpath("//div[@class='block_style_head']//div[text() = 'Pay From']");

	//New Payment Questionnaire Locators
	private static By wiringInstructions=By.xpath("//label[contains(text(),'Do you have a copy of the wiring instructions?')]/following-sibling::mat-form-field//mat-select");
	private static By recieveInstructions=By.id("18");
	private static By purchaseOrBill = By.xpath("//label[contains(text(),'Is the wire being sent for purchase or Bill/Invoice?')]/following-sibling::mat-form-field//mat-select");
	private static By purposeofPayment = By.id("22");
	private static By communicateRecip = By.id("23");
	private static By knowTheRecip=By.id("24");
	private static By sourceOfFunds=By.id("25");
	private static By contactInformation = By.xpath("//label[contains(text(),'Has the contact information (email, phone number, mailing address) changed in the last 30 days?')]/following-sibling::mat-form-field//mat-select");
	private static By callBackCompleted = By.xpath("//label[contains(text(),'Call back completed')]/following-sibling::mat-form-field//mat-select");
	private static By callBackNumber=By.id("31");
	private static By signatureOfFile=By.xpath("//label[contains(text(),'Verify wet signature on file, otherwise signature')]/following-sibling::mat-form-field//mat-select");
	private static By callBackNumber1=By.id("28");
	private static By yesTextClick=By.xpath("//div[@role='listbox']/mat-option[2]/span");
	private static By noTextClick=By.xpath("//div[@role='listbox']/mat-option[3]/span");
	private static By yesText=By.xpath("//div[@role='listbox']/mat-option[2]");
	private static By noText=By.xpath("//div[@role='listbox']/mat-option[3]");
	private static By typedNameSigner=By.xpath("//label[contains(text(),'Typed name of 2nd signer(s)')]/following-sibling::input");
	private static By mustObtainSecondSign=By.xpath("//label[contains(text(),'Must obtain a 2nd SIGNATURE from Branch')]/following-sibling::mat-form-field//mat-select");
	private static By receiveWireInstructions=By.xpath("//label[contains(text(),'Receive/attach copy of incoming wire instructions')]/following-sibling::mat-form-field//mat-select");

// Transfer Questionnaire - Assisted Teller
	
	private static By wiringInstructions_ForAssitedChannel=By.xpath("//label[contains(text(),'Receive/attach copy of incoming wire instructions')]/following-sibling::mat-form-field//mat-select");
    private static By copyOfInvoiceOrDebitPaid_ForAssitedChannel = By.xpath("//label[contains(text(),'Attach copy of invoice or debt being paid (if applicable)')]/following-sibling::mat-form-field//mat-select");
	private static By purposeOfPmt_ForAssitedChannel = By.id("3");
	private static By howMemberReceiveRequest_ForAssitedChannel = By.id("4");
	private static By relationBwnSenderAndRecip_ForAssitedChannel = By.xpath("//label[contains(text(),'Relationship between sender and recipient of payment')]/following-sibling::mat-form-field//mat-select"); 
	private static By sourceOfFundBeingSent_ForAssitedChannel = By.id("8");
	private static By hasTheInfoChangedInlast30Days_ForAssitedChannel = By.xpath("//label[contains(text(),'Has the contact information')]/following-sibling::mat-form-field//mat-select");
	private static By callbackCompleted_ForAssitedChannel = By.xpath("//label[contains(text(),'Call back completed')]/following-sibling::mat-form-field//mat-select");
	private static By wetSignatureOnFile_ForAssitedChannel = By.xpath("//label[contains(text(),'Verify wet signature on file')]/following-sibling::mat-form-field//mat-select");

	
	
	
	
	
	private static By attachCopyInvoice= By.xpath("//label[contains(text(),'Attach copy of invoice or debt being paid (if applicable)')]/following-sibling::mat-form-field//mat-select");

	private static By detailedPurposePymnt = By.id("3");

	private static By requestToSendFunds = By.id("4");

	private static By relationShip=By.xpath("//label[contains(text(),'Relationship between sender and recipient of payment')]/following-sibling::mat-form-field//mat-select");

	private static By sourceOfFundsSent = By.id("8");
	private static By NewRecipBtn = By.xpath("//div[@class='dropdown input_dropdown ng-star-inserted']/a");
	private static By paymentMethodToggle=By.xpath("//div[label[normalize-space(text())='Payment Method:']]//a[contains(@class, 'dropdown-toggle')]");
	
//Tax details
	
	//private static By 
	
	
	
	

	private String client_RoutingNum;
	private String paymentNetwork;
	private String recipient;
	private String ledgerOrAccount;
	private String AccountType;
	private String Division;
	private String senderAccNumber;
	private String NameOnAccount;
	private String ExistRecipNm;
	private String transferType;
	private String channelType;
	private String fedFileDoc;
	private String paymentType;

	//=======>>For Travel Rule <<=========
    private String senderAddressLine;
    private String senderTown;
    private String senderState;
    private String senderCountry;
    private String senderZipCode;
    

    public String getSenderAddressLine() {
        return senderAddressLine;
    }

    public void setSenderAddressLine(String senderAddressLine) {
        this.senderAddressLine = senderAddressLine;
    }

    public String getSenderTown() {
        return senderTown;
    }

    public void setSenderTown(String senderTown) {
        this.senderTown = senderTown;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderZipCode() {
        return senderZipCode;
    }

    public void setSenderZipCode(String senderZipCode) {
        this.senderZipCode = senderZipCode;
    }


	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getFedFileDoc() {
		return fedFileDoc;
	}

	public void setFedFileDoc(String fedFileDoc) {
		this.fedFileDoc = fedFileDoc;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getExistRecipNm() {
		return ExistRecipNm;
	}

	public void setExistRecipNm(String existRecipNm) {
		ExistRecipNm = existRecipNm;
	}

	public String getNameOnAccount() {
		return NameOnAccount;
	}

	public void setNameOnAccount(String nameOnAccount) {
		NameOnAccount = nameOnAccount;
	}

	public String getSenderAccNumber() {
		return senderAccNumber;
	}

	public void setSenderAccNumber(String senderAccNumber) {
		this.senderAccNumber = senderAccNumber;
	}

	public String getDivision() {
		return Division;
	}

	public void setDivision(String division) {
		Division = division;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getLedgerOrAccount() {
		return ledgerOrAccount;
	}

	public void setLedgerOrAccount(String ledgerOrAccount) {
		this.ledgerOrAccount = ledgerOrAccount;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPaymentNetwork() {
		return paymentNetwork;
	}

	public void setPaymentNetwork(String paymentNetwork) {
		this.paymentNetwork = paymentNetwork;
	}

	public String getClient_RoutingNum() {
		return client_RoutingNum;
	}

	public void setClient_RoutingNum(String client_RoutingNum) {
		this.client_RoutingNum = client_RoutingNum;
	}
	public static void selectPaymentsManager() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-toggle form-control']")));
		wait.until(ExpectedConditions.elementToBeClickable(PaymentManagerDropDown));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(PaymentManagerDropDown));
		do{
			js.executeScript("arguments[0].click();",WD.findElement(PaymentManagerDropDown));
		}while(WD.findElement(PaymentManagerDropDown).getDomAttribute("aria-expanded").equals("false"));
	}


	public static void selectCustomerSupport() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-toggle form-control']")));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerSupportDropDown));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(CustomerSupportDropDown));
		do{
			js.executeScript("arguments[0].click();",WD.findElement(CustomerSupportDropDown));
		}while(WD.findElement(CustomerSupportDropDown).getDomAttribute("aria-expanded").equals("false"));
		wait.until(ExpectedConditions.elementToBeClickable(ConsumerAndBusinessTransfersDropDown));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(ConsumerAndBusinessTransfersDropDown));
		do{
			js.executeScript("arguments[0].click();",WD.findElement(ConsumerAndBusinessTransfersDropDown));
		}while(WD.findElement(ConsumerAndBusinessTransfersDropDown).getDomAttribute("aria-expanded").equals("false"));
	}

	public static void Clik_Payments_Link_FI() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.elementToBeClickable(Payments_Link_FI));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(Payments_Link_FI));
		js.executeScript("arguments[0].click();",WD.findElement(Payments_Link_FI));
	}


	// "Transfers_Link(DepOp)"    
	public static void Clik_Transfers_Link_DepOp() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.elementToBeClickable(Transfers_Link_DepOp));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(Transfers_Link_DepOp));
		js.executeScript("arguments[0].click();",WD.findElement(Transfers_Link_DepOp));
	}


	// "Recipient_Link(FI)"    
	public static void Click_Recipient_Link_FI() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.elementToBeClickable(Recipient_Link_FI));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(Recipient_Link_FI));
		js.executeScript("arguments[0].click();",WD.findElement(Recipient_Link_FI));
	}


	//	 "Recipient_Link(DepOp)"    
	public static void Clik_Recipient_Link_DepOp() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.elementToBeClickable(Recipient_Link_DepOp));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(Recipient_Link_DepOp));
		js.executeScript("arguments[0].click();",WD.findElement(Recipient_Link_DepOp));
	}




	// Back Office Operations Portal
	public static void operationsDropDownLink() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.elementToBeClickable(backOfficeOperations));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(backOfficeOperations));
		do{
			js.executeScript("arguments[0].click();",WD.findElement(backOfficeOperations));
		}while(WD.findElement(backOfficeOperations).getDomAttribute("aria-expanded").equals("false"));
	}


	//Selecting Routing Number ----> Common for all---Payments & Add_Recipient(FI & DepOp)
	public static void select_ClinetsRoutingNum(AddSenderDetails objSenderDtls) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(client_Routing_DropDown));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(client_Routing_DropDown));
		while(WD.findElement(By.xpath("//div[@id='client_selection_level1']/a")).getDomAttribute("aria-expanded").equals("false")) {
			js.executeScript("arguments[0].click();",WD.findElement(By.xpath("//div[@id='client_selection_level1']/a")));
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' "+objSenderDtls.getClient_RoutingNum()+" ']")));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(By.xpath("//span[text()=' "+objSenderDtls.getClient_RoutingNum()+" ']")));
		js.executeScript("arguments[0].click();",WD.findElement(By.xpath("//span[text()=' "+objSenderDtls.getClient_RoutingNum()+" ']")));
	}

	public static void select_ClientsRoutingNum2(AddSenderDetails objSenderDtls) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("Payment Center"));
		//"No Records found"
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@class='mdc-data-table__content']"))); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(client_Routing_DropDown));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(appsWidgetPanel_RightCornor));
		Thread.sleep(2000);
		WD.findElement(client_Routing_DropDown).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' "+objSenderDtls.getClient_RoutingNum()+" ']")));
		WD.findElement(By.xpath("//span[text()=' "+objSenderDtls.getClient_RoutingNum()+" ']")).click();
	}



	//===============	Common For both FI_Payments & DepOp_Transfers ========================================================================================== 

	public static void payBtnClick_SelectDivision_PmtNetwork(AddSenderDetails objSenderDtls) throws IOException, InterruptedException { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(5));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(payBtn));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(payBtn));
		js.executeScript("arguments[0].click();",WD.findElement(payBtn));
		if(!objSenderDtls.getDivision().equals("VCU Mortgage Division")) 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectDivision));
			while(WD.findElement(selectDivision).getDomAttribute("aria-expanded").equals("false")){
				WD.findElement(selectDivision).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchDivision));
			WD.findElement(searchDivision).sendKeys(objSenderDtls.getDivision());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getDivision())));
			WD.findElement(By.id(objSenderDtls.getDivision())).click();
		}
		if(objSenderDtls.getRecipient().equals("Existing")&&!objSenderDtls.getDivision().contains("Transfers")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectExsRecpt));
			WD.findElement(selectExsRecpt).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchRecpt));
			WD.findElement(searchRecpt).sendKeys(objSenderDtls.getExistRecipNm());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='pm-select-recip']//span[contains(text(),'"+objSenderDtls.getExistRecipNm()+"')]"))).click();			wait.until(ExpectedConditions.elementToBeClickable(arrowBtn));
			WD.findElement(arrowBtn).click();
			try {
				wait = new WebDriverWait(WD, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='fa fa-eye ng-star-inserted']"))).click();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getPaymentNetwork())));
			WD.findElement(By.id(objSenderDtls.getPaymentNetwork())).click();
		}
	}


	//============ Dupaco_Client ========================================================================================================	

	public static void payBtnClick_SelectDivision_PmtNetwork_DupacoClient(AddSenderDetails objSenderDtls) throws IOException, InterruptedException { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(5));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(payBtn));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(payBtn));
		js.executeScript("arguments[0].click();",WD.findElement(payBtn));
//For Dupaco_FI_Payments only one division is configured so there is no Division_DropDown.....So we are using the below code only for Dupaco_Transfers 		
		if(objSenderDtls.getDivision().equalsIgnoreCase("Dupaco Community CU Retail Transfers")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(selectDivision));
			while(WD.findElement(selectDivision).getDomAttribute("aria-expanded").equals("false")){
				WD.findElement(selectDivision).click();    }
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchDivision));
			WD.findElement(searchDivision).sendKeys(objSenderDtls.getDivision());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getDivision())));
			WD.findElement(By.id(objSenderDtls.getDivision())).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getPaymentNetwork())));
		WD.findElement(By.id(objSenderDtls.getPaymentNetwork())).click();
	}

	public static void payBtnClick_SelectingExistingRecipient_FIpayments(String RecipientName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(10));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(payBtn));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(payBtn));
		js.executeScript("arguments[0].click();",WD.findElement(payBtn));
		wait.until(ExpectedConditions.visibilityOfElementLocated(NewRecipientDropDwn_FI));
		WD.findElement(NewRecipientDropDwn_FI).click();
		WD.findElement(RecipientSearchBox_FI).sendKeys(RecipientName, Keys.SPACE, Keys.BACK_SPACE);
		Thread.sleep(3000);
		WD.findElement(RecipientSearchBox_FI).sendKeys( Keys.SPACE);
		WD.findElement(RecipientSearchBox_FI).sendKeys(Keys.BACK_SPACE);
		WD.findElement(By.xpath("//*[contains(@id, 'pay_scenario w-')]//*[@class='ach btn_sidepanel ng-star-inserted'][1]")).click();
		WD.findElement(SelectRecipArrow_FI).click();
	}

	public static void selectAccType_enterAccNum_AccName(AddSenderDetails objSenderDtls) throws InterruptedException{
		// Note :- We are using this method ...for Dupaco_Client Existing Individual Recipient 
		// As there are no GL accounts for Dupaco_client so Ledger/Account dropDown will not be display so we can't use the enterSenderDetails()
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(objSenderDtls.getAccountType().equals("Ledger")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccType));
			WD.findElement(senderAccType).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Ledger')]")));
			Thread.sleep(2000);
			WD.findElement(By.xpath("//span[contains(text(),'Ledger')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(Ledger_NameOnAcc_Field));
			WD.findElement(Ledger_NameOnAcc_Field).sendKeys(objSenderDtls.getNameOnAccount());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccNo));
		WD.findElement(senderAccNo).sendKeys(objSenderDtls.getSenderAccNumber(), Keys.TAB);
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(payFromTitle));
	}
	public static void enterSenderAddressDetailsInAllIndividualFields(AddSenderDetails objDetails) throws Exception  {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("struc_sender_address_form")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(senderAddressLineField));
        wait.until(ExpectedConditions.presenceOfElementLocated(senderZipcodeField));
        WD.findElement(senderAddressLineField).clear();
        WD.findElement(senderAddressLineField).sendKeys(Keys.CLEAR, objDetails.getSenderAddressLine());
        WD.findElement(senderTownNameField).clear();
        WD.findElement(senderTownNameField).sendKeys(Keys.CLEAR,objDetails.getSenderTown());
        
        if(objDetails.getSenderCountry().contains("America")) {
            while(WD.findElement(senderCountryField).getDomAttribute("aria-expanded").equals("false"))  {
                WD.findElement(senderCountryField).click();   }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getSenderCountry()+"')]")));
            WD.findElement(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getSenderCountry()+"')]")).click(); 
            while(WD.findElement(senderStateField).getDomAttribute("aria-expanded").equals("false")) {
                WD.findElement(senderStateField).click();  }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='state-panel']//span[contains(text(), '"+objDetails.getSenderState()+"')]")));
            WD.findElement(By.xpath("//div[@id='state-panel']//span[contains(text(), '"+objDetails.getSenderState()+"')]")).click();  }
        else {
            while(WD.findElement(senderCountryField).getDomAttribute("aria-expanded").equals("false"))    {
                  WD.findElement(senderCountryField).click();     }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getSenderCountry()+"')]")));
            WD.findElement(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getSenderCountry()+"')]")).click(); 
            WD.findElement(senderStateField).clear();
            WD.findElement(senderStateField).sendKeys(Keys.CLEAR, objDetails.getSenderState()); 
        }
        WD.findElement(senderZipcodeField).clear(); 
        WD.findElement(senderZipcodeField).sendKeys(Keys.CLEAR, objDetails.getSenderZipCode(), Keys.TAB);
    }

	public static void dupaco_SelectingSenderAccType_DepOp_Pmts() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='warning_msg_highlighter account_not_retrieved ng-star-inserted']")));
		Thread.sleep(2000);
		WD.findElement(senderAccountType_DepOp).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='acc-sub-type-panel']//span[1]")));
		WD.findElements(By.xpath("//div[@id='acc-sub-type-panel']//span[1]")).get(0).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));
	}


	//=========================	Common for FI & DepOp (Add Recipient) ===========================================================	

	public static void addRecipBtnClick_SelectDivision_TransferType_PmtNetwk(AddSenderDetails objSenderDtls) throws IOException, InterruptedException { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));	
		JavascriptExecutor js = (JavascriptExecutor) WD;
		//		this wait path is not there for AddRecipient_DepOp	
		if(!objSenderDtls.getPaymentType().equals("Edit")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientBtn));
			js.executeScript("arguments[0].scrollIntoView();",WD.findElement(AddRecipientBtn));
			js.executeScript("arguments[0].click();",WD.findElement(AddRecipientBtn));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block_style recipient_account_info ng-star-inserted']//i[@class='fa fa-search fa_search_icon text-white']")));
		}
		if(!objSenderDtls.getDivision().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-select[@id='create_recipient_division']")));
			while(WD.findElement(By.xpath("//mat-select[@id='create_recipient_division']")).getDomAttribute("aria-expanded").equals("false"))
			{
				js.executeScript("arguments[0].click();",WD.findElement(By.xpath("//mat-select[@id='create_recipient_division']/div/div[1]/span")));
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objSenderDtls.getDivision()+"']")));
			WD.findElement(By.xpath("//span[text()='"+objSenderDtls.getDivision()+"']")).click();}
		if(!objSenderDtls.getTransferType().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@id='create_recipient_trans_type']/div/div[1]/span")));
			WD.findElement(By.xpath("//mat-select[@id='create_recipient_trans_type']/div/div[1]/span")).click();
			while(WD.findElement(By.xpath("//mat-select[@id='create_recipient_trans_type']")).getDomAttribute("aria-expanded").equals("false"))
			{
				WD.findElement(By.xpath("//mat-select[@id='create_recipient_trans_type']/div/div[1]/span")).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//span[text()='"+objSenderDtls.getTransferType()+"']")));
			WD.findElement(By.xpath("//mat-option//span[text()='"+objSenderDtls.getTransferType()+"']")).click();   }
		if(!objSenderDtls.getPaymentNetwork().equalsIgnoreCase("")) {
			WD.findElement(paymntNetwork).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getPaymentNetwork())));
			WD.findElement(By.id(objSenderDtls.getPaymentNetwork())).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//span[text()='"+objSenderDtls.getPaymentNetwork()+"']")));
		}
	}		


	public static void enterSenderAndChannelDetails(AddSenderDetails objSenderDtls) throws IOException, InterruptedException { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(!objSenderDtls.getSenderAccNumber().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(DepOpSenderAccNo));
			WebElement senderField=WD.findElement(DepOpSenderAccNo);
			((JavascriptExecutor) WD).executeScript("arguments[0].value='';", senderField);
			WD.findElement(DepOpSenderAccNo).sendKeys(objSenderDtls.getSenderAccNumber());
			WD.findElement(DepOpSenderAccNo).sendKeys(Keys.TAB);
		}

		if(objSenderDtls.getSenderAccNumber().length()>=4 && objSenderDtls.getSenderAccNumber().matches("\\d+")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='btn-block' and text()='Current Available Balance:']")));
		}

		if(!objSenderDtls.getChannelType().equalsIgnoreCase("")&&!objSenderDtls.getPaymentType().equalsIgnoreCase("Edit")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ChannelType));
			while(WD.findElement(ChannelType).getDomAttribute("aria-expanded").equals("false")) {
				WD.findElement(ChannelType).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getChannelType())));
			WD.findElement(By.id(objSenderDtls.getChannelType())).click();  }

		if(objSenderDtls.getRecipient().equals("Existing"))   {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='btn-block' and text()='Current Available Balance:']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(DepoNewRecLink));
			while(WD.findElement(DepoNewRecLink).getDomAttribute("aria-expanded").equals("false")) {
				WD.findElement(DepoNewRecLink).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(recipientSearch));
			JavascriptExecutor js = (JavascriptExecutor) WD;
			js.executeScript("arguments[0].scrollIntoView();", WD.findElement(recipientSearch));
			WD.findElement(recipientSearch).click();
			WD.findElement(recipientSearch).sendKeys(objSenderDtls.getExistRecipNm());
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='recipient_type_selection']//span[contains(text(),'"+objSenderDtls.getExistRecipNm()+"')]"))).click();
			if(objSenderDtls.getPaymentType().equals("Edit")) {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")));
				WD.findElements(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")).get(1).click();
			}
			else {
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")));
				WD.findElements(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")).get(0).click();
			}
		}
	}

	public static void enterDepoSenderDetails(AddSenderDetails objSenderDtls) throws Exception { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(!objSenderDtls.getSenderAccNumber().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.elementToBeClickable(DepOpSenderAccNo));
			WD.findElement(DepOpSenderAccNo).sendKeys(objSenderDtls.getSenderAccNumber());
			WD.findElement(DepOpSenderAccNo).sendKeys(Keys.TAB);
		}
	}

	public static void enterChannelDetails_RecipientType(AddSenderDetails objSenderDtls) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(!objSenderDtls.getChannelType().equalsIgnoreCase("")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='btn-block' and text()='Current Available Balance:']")));
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ChannelType));
			WD.findElement(ChannelType).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getChannelType())));
			WD.findElement(By.id(objSenderDtls.getChannelType())).click();}
		if(objSenderDtls.getRecipient().equals("Existing"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(DepoNewRecLink));
			WD.findElement(DepoNewRecLink).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(recipientSearch));
			Thread.sleep(2000);
			WD.findElement(recipientSearch).sendKeys(objSenderDtls.getExistRecipNm());
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='select_instant_receipient2 cursor-pointer']")));
			WD.findElement(By.xpath("//a[@class='select_instant_receipient2 cursor-pointer']")).click();
		}
	}	

	public static String enterSenderAccountNumber(AddSenderDetails objSenderDtls) throws Exception { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DepOpSenderAccNo));
		WebElement senderField=WD.findElement(DepOpSenderAccNo);
		((JavascriptExecutor) WD).executeScript("arguments[0].value='';", senderField);
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(objSenderDtls.getSenderAccNumber());
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(Keys.TAB);
		// Below path is not there for AddRecipient_DepOp----> VCU Client
		if(objSenderDtls.getClient_RoutingNum().contains("Sylvan Financials")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));  }
		if(objSenderDtls.getClient_RoutingNum().contains("Veridian Credit Union")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ng-star-inserted']//*[text()='Name:']")));  }
		String senderNamee = WD.findElement(senderName).getText();
		System.out.println(senderNamee);
		return senderNamee;

	}

	public static String enterSenderAccountNumber_VCU(AddSenderDetails objSenderDtls) throws Exception { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DepOpSenderAccNo));
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(objSenderDtls.getSenderAccNumber());
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(Keys.TAB);
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(senderName));
		String senderNamee = WD.findElement(senderName).getText();
		System.out.println(senderNamee);
		return senderNamee;

	}

	public static void enterSenderAccountNumber_DepOp004(AddSenderDetails objSenderDtls) throws Exception { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DepOpSenderAccNo));
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(objSenderDtls.getSenderAccNumber());
		WD.findElements(DepOpSenderAccNo).get(1).sendKeys(Keys.TAB);
	}

	public static void enterSenderDetails(AddSenderDetails objSenderDtls) throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(!objSenderDtls.getTransferType().equals("ONUS")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@formcontrolname='ledgerAccount']")));
			try {
				while(WD.findElement(By.xpath("//mat-select[@formcontrolname='ledgerAccount']")).getDomAttribute("aria-expanded").equals("false"))
				{
					Thread.sleep(3000);
					WD.findElement(selectLedger).click();
				}
			}catch(Exception e) {
				while(WD.findElement(By.xpath("//mat-select[@formcontrolname='ledgerAccount']")).getDomAttribute("aria-expanded").equals("false"))
				{
					WD.findElement(selectLedger).click();
				}
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option/span[contains(.,'" + objSenderDtls.getLedgerOrAccount() + "')]")));
			WD.findElement(By.xpath("//mat-option/span[contains(.,'" + objSenderDtls.getLedgerOrAccount() + "')]")).click();
		}

		if(objSenderDtls.getLedgerOrAccount().equals("Other Account")){  
			if(objSenderDtls.getAccountType().equals("Ledger")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccType));
				WD.findElement(senderAccType).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Ledger")));
				Thread.sleep(2000);
				WD.findElement(By.id("Ledger")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(Ledger_NameOnAcc_Field));
				WebElement senderField=WD.findElement(Ledger_NameOnAcc_Field);
				((JavascriptExecutor) WD).executeScript("arguments[0].value='';", senderField);
				WD.findElement(Ledger_NameOnAcc_Field).sendKeys(objSenderDtls.getNameOnAccount());
			}
			if(objSenderDtls.getAccountType().equals("DDA")){
				wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccType));
				WD.findElement(senderAccType).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//span[contains(text(),'DDA')]")));
				WD.findElement(By.xpath("//mat-option//span[contains(text(),'DDA')]")).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccNo));
			WebElement senderField=WD.findElement(senderAccNo);
			((JavascriptExecutor) WD).executeScript("arguments[0].value='';", senderField);
			WD.findElement(senderAccNo).sendKeys(objSenderDtls.getSenderAccNumber(), Keys.TAB);
			//			JavascriptExecutor js = (JavascriptExecutor) WD;
			//			js.executeScript("arguments[0].scrollIntoView();", WD.findElement(payFromTitle));
		}

	}
	public static void selectNetworkInExistingList(AddSenderDetails objSenderDtls) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodToggle));
		while (WD.findElement(paymentMethodToggle).getDomAttribute("aria-expanded").equals("false")) {
			WD.findElement(paymentMethodToggle).click();
		}
		if(objSenderDtls.getPaymentNetwork().equals("FedWire")) {
			objSenderDtls.setPaymentNetwork("Fedwire");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='value' and contains(text(),'"+objSenderDtls.getPaymentNetwork()+"')]")));
		WD.findElement(By.xpath("//span[@class='value' and contains(text(),'"+objSenderDtls.getPaymentNetwork()+"')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Create "+objSenderDtls.getPaymentNetwork()+" Payment']")));

	}

	public static void selectNewOrExistingRecipFIPayments(AddSenderDetails objSenderDtls) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		boolean isExisting = objSenderDtls.getRecipient().equals("Existing") && !objSenderDtls.getDivision().contains("Transfers");
		JavascriptExecutor js = (JavascriptExecutor) WD;
		if (!isExisting) {
			// Only needed for new recipients
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='row ng-star-inserted']/div[@class='row ng-star-inserted']")));
		}
		// Wait for and expand the recipient dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(NewRecipBtn));
		while (WD.findElement(NewRecipBtn).getDomAttribute("aria-expanded").equals("false")) {
			js.executeScript("arguments[0].scrollIntoView();",WD.findElement(NewRecipBtn));
            js.executeScript("arguments[0].click();",WD.findElement(NewRecipBtn));
         }
		// Common part: search for recipient name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fw_recipient_search")));
		WD.findElement(By.id("fw_recipient_search")).sendKeys(objSenderDtls.getExistRecipNm());
		if (isExisting) {
			// Select existing recipient
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + objSenderDtls.getExistRecipNm() + "')]")));
            js.executeScript("arguments[0].scrollIntoView();",WD.findElement(By.xpath("//a[contains(text(),'" + objSenderDtls.getExistRecipNm() + "')]")));
            js.executeScript("arguments[0].click();",WD.findElement(By.xpath("//a[contains(text(),'" + objSenderDtls.getExistRecipNm() + "')]")));
            try {
				wait = new WebDriverWait(WD, Duration.ofSeconds(15));
				if(objSenderDtls.getPaymentType().equals("Edit")) {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")));
					WD.findElements(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")).get(1).click();
				}
				else if(!objSenderDtls.getPaymentType().equals("Edit")&&!objSenderDtls.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")));
					WD.findElements(By.xpath("//span[@class='fa fa-eye ng-star-inserted']")).get(0).click();
				}			
				}
			catch (TimeoutException e) {
				// TODO: handle exception
			}
		} else {
			// Select new recipient
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'New Recipient')]"))).click();
		}
	}

	public static void enterCostCenterData() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ledger_CostCenter));
		WD.findElement(ledger_CostCenter).sendKeys("123");


	}

	public static void enterFIVCUSenderDetails(AddSenderDetails objSenderDtls) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(senderAccNo));
		WD.findElement(senderAccNo).sendKeys(objSenderDtls.getSenderAccNumber());
		WD.findElement(senderAccNo).sendKeys(Keys.TAB);
	}

	public static void dupaco_SelectingSenderAccType_FI() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='warning_msg_highlighter account_not_retrieved ng-star-inserted']")));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(payFromTitle_ForScrolling));
		Thread.sleep(2000);
		while(WD.findElement(senderAccountType_FI).getDomAttribute("aria-expanded").equals("false")) 
		{
			WD.findElement(senderAccountType_FI).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-optgroup[@label='DDA']//span[contains(text(),'Savings')]")));
		//		WD.findElement(By.xpath("//mat-optgroup[@label='DDA']//span[contains(text(),'Savings')]")).click();
		WD.findElement(By.xpath("//mat-optgroup[@label='DDA']//span[contains(text(),' Checking ')]")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='row ng-star-inserted']//div[@class='row sender_details_info_wires']")));

	}


	public static void dupaco_SelectingSenderAccType_DepOp_AddRecipient() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='warning_msg_highlighter account_not_retrieved ng-star-inserted']")));
		Thread.sleep(2000);
		WD.findElements(senderAccountType_DepOp).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='acc-sub-type-panel']//span[1]")));
		WD.findElement(By.xpath("//div[@id='acc-sub-type-panel']//span[1]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));
	}


	public static void submitForApproval() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitForApproveBtn));
		WD.findElement(submitForApproveBtn).click();
	}

	public static String pendingApprovalMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your payment has been submitted and is pending for approval.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		System.out.println("Conf "+WD.findElement(confirmNo).getText());
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Pending Approval");
		return WD.findElement(confirmNo).getText();
	}

	public static void searchConfirmationNo(String ConfirmationNo) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		try {
			wait = new WebDriverWait(WD, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchPendingPmt));
		WD.findElement(searchPendingPmt).sendKeys(ConfirmationNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(searchBtn));
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(searchBtn));
		js.executeScript("arguments[0].click();",WD.findElement(searchBtn));

	}

	public static void approvingPayment() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveBtn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
        js.executeScript("arguments[0].click();",WD.findElement(ApproveBtn));		wait.until(ExpectedConditions.visibilityOfElementLocated(memo));
		WD.findElement(memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveSubmit));
		WD.findElement(ApproveSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Payment has been approved and is processed successfully.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Processed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
	}

	public static void partialApprovePayment() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveBtn));
		WD.findElement(ApproveBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(memo));
		WD.findElement(memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveSubmit));
		WD.findElement(ApproveSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Payment has been approved and is in the Partially Approved state.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Partially Approved");
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
	}

	public static void approvingFuturePymt() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveBtn));
		WD.findElement(ApproveBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(memo));
		WD.findElement(memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ApproveSubmit));
		WD.findElement(ApproveSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Payment has been approved and is scheduled for processing.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Scheduled");
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
	}

	public static void declinePayment() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(DeclineBtn));
		WD.findElement(DeclineBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(memo));
		WD.findElement(memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(DeclineSubmit));
		WD.findElement(DeclineSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Payment has been declined.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Approval Declined");
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
	}

	public static void closeBtn() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();
		}
		catch(Exception e) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'cdk-overlay-connected-position-bounding-box')]//div[contains(@class,'cdk-overlay-pane')]")));
				WD.findElement(By.xpath("//div[contains(@class,'cdk-overlay-connected-position-bounding-box')]//div[contains(@class,'cdk-overlay-pane')]//mat-option[1]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
				WD.findElement(closeBtn).click();
			} catch (Exception e2) {

			}
		}
	}


	public static void logOff() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(15));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();
		}
		catch(Exception e) {
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(profileIcon));
		js.executeScript("arguments[0].click();", WD.findElement(profileIcon));
		wait.until(ExpectedConditions.visibilityOfElementLocated(logOff));
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(logOff));
		js.executeScript("arguments[0].click();", WD.findElement(logOff));
		wait.until(ExpectedConditions.titleContains("Login"));
	}

	public static String closeBtn2() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(5));
		String testCaseStatus ;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();
			testCaseStatus = "InFirstTry";
		}
		catch(Exception e) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'cdk-overlay-connected-position-bounding-box')]//div[contains(@class,'cdk-overlay-pane')]")));
				WD.findElement(By.xpath("//div[contains(@class,'cdk-overlay-connected-position-bounding-box')]//div[contains(@class,'cdk-overlay-pane')]//mat-option[1]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
				WD.findElement(closeBtn).click();
				testCaseStatus = "InSecondTry";
			} catch (Exception e2) {
				testCaseStatus = "InFinalCatch";
			}
		}

		return testCaseStatus;
	}

	public static String logOff2() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(15));
		String testCaseStatus ;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();
			testCaseStatus = "InFirstTry";
		}
		catch(Exception e) {   
			// TODO: handle exception  
		}
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
			JavascriptExecutor js = (JavascriptExecutor) WD;
			js.executeScript("arguments[0].scrollIntoView();", WD.findElement(profileIcon));
			js.executeScript("arguments[0].click();", WD.findElement(profileIcon));
			wait.until(ExpectedConditions.visibilityOfElementLocated(logOff));
			WD.findElement(logOff).click();
			wait.until(ExpectedConditions.titleContains("Login"));
			testCaseStatus = "InSecondTry";

		} catch (Exception e2) {
			// TODO: handle exception
			testCaseStatus = "InFinalCatch";  }

		return testCaseStatus ;
	}

	public static void directSubmit() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
	}

	public static void directSubmitMsg() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your payment has been submitted and is processed successfully.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Processed");
	}


	public static void xbDirectSubmitMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your payment has been submitted and is currently being processed.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"In Process");
	}

	public static void futureDatePymtSubmitMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your payment has been submitted and is scheduled for processing.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Scheduled");
	}

	public static void checkDisabledRecipient(AddSenderDetails objSenderDtls) throws IOException, InterruptedException { 
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(payBtn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();",WD.findElement(payBtn));
		js.executeScript("arguments[0].click();",WD.findElement(payBtn));		
		wait.until(ExpectedConditions.elementToBeClickable(selectExsRecpt));
		WD.findElement(selectDivision).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchDivision));
		WD.findElement(searchDivision).sendKeys(objSenderDtls.getDivision());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objSenderDtls.getDivision())));
		WD.findElement(By.id(objSenderDtls.getDivision())).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectExsRecpt));
		WD.findElement(selectExsRecpt).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchRecpt));
		WD.findElement(searchRecpt).sendKeys(objSenderDtls.getExistRecipNm());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(),'"+objSenderDtls.getExistRecipNm()+"')]")));
	}

	public static void dashBoardClick() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.visibilityOfElementLocated(dashBoard));
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(dashBoard));
		js.executeScript("arguments[0].click();", WD.findElement(dashBoard));
	}

	public static void submitAndRejectedMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Your payment has been submitted but was rejected during processing.')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Rejected");
	}

	public static void submitAndInProcessMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basicDetails")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"In Process");
	}

	public static void checkPrefundedModel(AddSenderDetails objSenderDtls) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		if(objSenderDtls.getDivision().equals("VCU Retail Transfers")) 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']/label[text()='Deal ID:']")));
		}
	}

	public static void AssertEditedFee(String fee) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		String totalAmountInTMS=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Total Amount:']]//strong"))).getText();
		String feeInTMS=WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]//strong")).getText();
		Assert.assertEquals(feeInTMS,fee+" USD");
		wait.until(ExpectedConditions.visibilityOfElementLocated(BankRails.instructionID));
		WD.findElement(BankRails.instructionID).click();
		String totalAmountInOPS=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Total Amount:']]//strong"))).getText();
		String feeInOPS=WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]//strong")).getText();
		Assert.assertEquals(totalAmountInTMS,totalAmountInOPS);
		Assert.assertEquals(feeInTMS,feeInOPS);
	}


	public static void uploadFedFileDoc(AddSenderDetails objSenderDtls) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='payment_with_file_upload_btn']")));
		WebElement fileInput = WD.findElement(By.xpath("//*[@id='payment_with_file_upload_btn']"));
		Thread.sleep(2000);
		String fileLocation = System.getProperty("user.dir").toString() + ""+objSenderDtls.getFedFileDoc()+"";
		fileInput.sendKeys(fileLocation);
	}

	public static String getFedFileErrorMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FedFileErrorMsg));
		return WD.findElement(FedFileErrorMsg).getText();
	}
	public static String getErrorMsg() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(alertErrorMsg));
		Thread.sleep(500);
		return WD.findElement(alertErrorMsg).getText();
	}
	public static String getBankRailsErrorMsg() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(alertErrorMsg));
		Thread.sleep(500);
		return WD.findElement(alertErrorMsg).getText();
	}

	public static String getEmptyFedFileErrorMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(EmptyFedFileErrorMsg));
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-create-fedwire-payment[@class='ng-star-inserted']//div[@class='multiple_message_display_block error_msg']")));
		return WD.findElement(EmptyFedFileErrorMsg).getText();
	}

	public static String getGreaterFedFileErrorMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(greaterFedFileErrorMsg));
		return WD.findElement(greaterFedFileErrorMsg).getText();
	}

	public static void closeAllPages() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(closeBtn));
			WD.findElements(closeBtn).get(1).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBtn));
			WD.findElement(closeBtn).click();
		}
		catch(Exception e) {
		}
	}


	public static void paymentQuestionnaire_RetailTransfers(AddSenderDetails objDetails) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(wiringInstructions));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(wiringInstructions));
		js.executeScript("arguments[0].click();", WD.findElement(wiringInstructions));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		WD.findElement(recieveInstructions).sendKeys("Through Text");
		WD.findElement(purchaseOrBill).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		WD.findElement(purposeofPayment).sendKeys("Vacation");	
		WD.findElement(communicateRecip).sendKeys("Through Text");	
		WD.findElement(knowTheRecip).sendKeys("Through Email");	
		WD.findElement(sourceOfFunds).sendKeys("Cash");	
		if(objDetails.getChannelType().equals("Call Center"))
		{
			WD.findElement(callBackNumber).sendKeys("8772837492");	
			WD.findElement(contactInformation).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(yesTextClick).click();
			}
			Thread.sleep(500);
			WD.findElement(callBackCompleted).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(yesTextClick).click();
			}
			Thread.sleep(500);
			WD.findElement(signatureOfFile).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(yesTextClick).click();
			}
		}
		if(objDetails.getChannelType().equals("Branch")) {
			WD.findElement(callBackNumber1).sendKeys("8772837492");	
		}
	}

	
	public static void enterTransfersQuestionnaireDetails_For_RetailsTransfers_AssitedTeller() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(wiringInstructions_ForAssitedChannel));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(wiringInstructions_ForAssitedChannel));
		js.executeScript("arguments[0].click();", WD.findElement(wiringInstructions_ForAssitedChannel));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		js.executeScript("arguments[0].click();", WD.findElement(copyOfInvoiceOrDebitPaid_ForAssitedChannel));
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		WD.findElement(purposeOfPmt_ForAssitedChannel).sendKeys("Vacation");
		WD.findElement(howMemberReceiveRequest_ForAssitedChannel).sendKeys("Through Email");
		js.executeScript("arguments[0].click();", WD.findElement(relationBwnSenderAndRecip_ForAssitedChannel));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		WD.findElement(yesTextClick).click();
		Thread.sleep(500);
//		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
//		{
//			WD.findElement(yesTextClick).click();
//			Thread.sleep(500);
//		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@value='no']")));
		WD.findElement(By.xpath("//input[@value='no']")).click();
		WD.findElement(sourceOfFundBeingSent_ForAssitedChannel).sendKeys("Cash");
		js.executeScript("arguments[0].click();", WD.findElement(hasTheInfoChangedInlast30Days_ForAssitedChannel));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		js.executeScript("arguments[0].click();", WD.findElement(callbackCompleted_ForAssitedChannel));
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		js.executeScript("arguments[0].click();", WD.findElement(wetSignatureOnFile_ForAssitedChannel));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		Thread.sleep(500);
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
	}
	
	
	
	
	public static void paymentQuestionnairExtraFields() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(typedNameSigner));
		WD.findElement(typedNameSigner).sendKeys("signature2");	
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(mustObtainSecondSign));
		js.executeScript("arguments[0].click();", WD.findElement(mustObtainSecondSign));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
	}


	public static void paymentQuestionnaireBusinessTransfer() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(receiveWireInstructions));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(receiveWireInstructions));
		js.executeScript("arguments[0].click();", WD.findElement(receiveWireInstructions));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		Thread.sleep(500);
		WD.findElement(detailedPurposePymnt).sendKeys("Vacation");	
		WD.findElement(attachCopyInvoice).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}

		WD.findElement(requestToSendFunds).sendKeys("Email");	

		WD.findElement(relationShip).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(noTextClick).click();
		}	

		Thread.sleep(500);
		WD.findElement(callBackCompleted).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		WD.findElement(sourceOfFundsSent).sendKeys("wire Transfer");	

		WD.findElement(signatureOfFile).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}
		Thread.sleep(500);
		WD.findElement(contactInformation).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(yesText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(yesTextClick).click();
		}	
	}

	public static void AssertSenderDetails(AddSenderDetails objDetails) {
		String senderDetails = WD.findElement(duplicateSenderDetails).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(senderName,objDetails.getNameOnAccount());
		Assert.assertEquals(senderAccNo,objDetails.getSenderAccNumber());
	}

	public static void assertMortgageSenderDDADetails(AddSenderDetails objDetails, String senderNameInUI) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(!objDetails.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
			WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
			try {
				WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
			}catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay From:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(senderName,senderNameInUI);
		Assert.assertEquals(senderAccNo,"Sylvan Financials Savings "+objDetails.getSenderAccNumber()+"");
	}

	public static void assertMortgageSenderDetails(AddSenderDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(!objDetails.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
			WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
			try {
				WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
			}catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay From:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(senderName,"Mortage GL_CC Account");
		Assert.assertEquals(senderAccNo,"Sylvan Financials General Ledger 119192001");
	}

	public static void assertMortgageSenderDetailsFI(AddSenderDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(!objDetails.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
			WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
			try {
				WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
			}catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay From:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(senderName,"Mortgage Division");
		Assert.assertEquals(senderAccNo,"Sylvan Financials");
	}
	
	public static void assertMortgageSenderDetailsEditedFI(AddSenderDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(!objDetails.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
			WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
			try {
				WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
			}catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay From:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		Assert.assertEquals(senderName,"Sylvan Financials");
	}

	public static void assertMortgageSenderDetailsLedger(AddSenderDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(!objDetails.getLedgerOrAccount().contains("Fund via Fed Master A/C")) {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
			WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
			try {
				WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
			}catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay From:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String senderName = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(senderName,objDetails.getNameOnAccount());
		Assert.assertEquals(senderAccNo,"Sylvan Financials General Ledger "+objDetails.getSenderAccNumber()+"");
	}



	public static void checkONUS() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'title_glance_details')]/span[1]")));
		String paymentRail=WD.findElement(By.xpath("//div[contains(@class,'title_glance_details')]/span[1]")).getText();	
		Assert.assertEquals(paymentRail,"OnUs");
	}


	public static void checkNoFee() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]")));
	}


	public static void checkBillSeparately() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(BankRails.instructionID));
		WD.findElement(BankRails.instructionID).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]/div")));
		String feeType=WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]/div")).getText().trim();
		Assert.assertEquals(feeType,"(Billed Separately)");
	}

	public static void checkChargedToPrinciple() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(BankRails.instructionID));
		WD.findElement(BankRails.instructionID).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]/div")));
		String feeType=WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Fee:']]/div")).getText().trim();
		Assert.assertEquals(feeType,"(Charged to principle)");
	}

	public static void EditClick(String ConfirmationNo) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Edit')]")));
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(By.xpath("//a[contains(text(),'Edit')]")));
		WD.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
	}


	public static void editPaymentQuestionnaire(AddSenderDetails objDetails) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(wiringInstructions));
		Thread.sleep(2000);
		WD.findElements(wiringInstructions).get(1).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(noTextClick).click();
		}
		Thread.sleep(500);
		WD.findElements(purchaseOrBill).get(1).click();	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
		while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
		{
			WD.findElement(noTextClick).click();
		}
		WD.findElements(purposeofPayment).get(1).clear();	
		WD.findElements(purposeofPayment).get(1).sendKeys("Trip");	
		WD.findElements(communicateRecip).get(1).clear();	
		WD.findElements(communicateRecip).get(1).sendKeys("Through");	
		WD.findElements(knowTheRecip).get(1).clear();	
		WD.findElements(knowTheRecip).get(1).sendKeys("Email");	
		WD.findElements(sourceOfFunds).get(1).clear();	
		WD.findElements(sourceOfFunds).get(1).sendKeys("Wallet");	
		if(objDetails.getChannelType().equals("Call Center"))
		{
			WD.findElements(callBackNumber).get(1).clear();	
			WD.findElements(callBackNumber).get(1).sendKeys("8772837492");	
			WD.findElements(contactInformation).get(1).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(noTextClick).click();
			}
			Thread.sleep(500);
			WD.findElements(callBackCompleted).get(1).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(noTextClick).click();
			}
			Thread.sleep(500);
			WD.findElements(signatureOfFile).get(1).click();	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='listbox']/mat-option")));
			while(WD.findElement(noText).getDomAttribute("aria-selected").equals("false"))
			{
				WD.findElement(noTextClick).click();
			}
		}
		WD.findElement(By.id("notes")).sendKeys("hdks");
		if(objDetails.getChannelType().equals("Branch")) {
			WD.findElements(callBackNumber1).get(1).clear();	
			WD.findElements(callBackNumber1).get(1).sendKeys("8772837492");	
		}
	}


	public static String getSenderName(AddSenderDetails objDetails) throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		String senderName="";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-select[@id='sender-name']//span")));
		senderName=WD.findElement(By.xpath("//mat-select[@id='sender-name']//span")).getText();
		return senderName;
	}
	
	public static String getDDASenderName() throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		String senderName="";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires']//label[text()='Name:']/following-sibling::strong")));
		senderName=WD.findElement(By.xpath("//div[@class='row sender_details_info_wires']//label[text()='Name:']/following-sibling::strong")).getText();
		return senderName;
	}


	public static void assertTransferSenderDetails(AddSenderDetails objDetails,String senderName) throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")));
		WD.findElements(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).get(0).click();
		WD.findElement(By.xpath("//span[@class='icon_holder fa fa-eye ng-star-inserted']")).click();
		String senderDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Sender:']]//strong")).getText();
		String[] senderSplit = senderDetails.split(",");	
		String nameOfSender = senderSplit[0].trim();
		String senderAccNo = senderSplit[1].trim();
		Assert.assertEquals(nameOfSender,senderName);
		Assert.assertTrue(senderAccNo.contains(objDetails.getSenderAccNumber().trim()));
	}

	public static void senderDetailsLoadingCheck() throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='btn-block' and text()='Current Available Balance:']")));
	}


	public static void saveTransfer() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Save")));
		WD.findElement(By.linkText("Save")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Your payment has been submitted and is in the Awaiting Confirmation state.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Awaiting Confirmation");
	}

	public static void selectQuestionaire() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Transfer Questionnaire')]"))).click();
	}

	public static void submitQuestionaire() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		WD.findElement(By.xpath("//textarea[@type='textarea']")).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Submit Transfer Questionnaire')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully updated questionnaire information for the transfer.']")));
	}

	public static void submitAndProcessed() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notes")));
		WD.findElement(By.id("notes")).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Payment has been submitted  and is processed successfully.')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"Processed");
	}


	public static void submitAndInProcess() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(submitClick));
		WD.findElement(submitClick).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notes")));
		WD.findElement(By.id("notes")).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitClick)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Payment has been submitted  and is currently being processed.')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmNo));
		Assert.assertEquals(WD.findElement(paymentStatus).getText(),"In Process");
	}
	public static void checkPaymentMethod(AddSenderDetails objDtls) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Payment Method:']]//strong")));
		String paymentMethod=WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Payment Method:']]//strong")).getText();
		if(objDtls.getPaymentNetwork().equals("FedWire")) {
			objDtls.setPaymentNetwork("Fedwire");
		}
		Assert.assertEquals(paymentMethod,objDtls.getPaymentNetwork());
	}

}