package pages;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dataReader.Utility;

public class AddRecipientDetails extends Utility {

	//**Common For All  
	static By RecipientListDropDown = By.xpath("//div[contains(@class, 'block_style_body p_')]//a[@data-bs-toggle='dropdown']");
	static By recipType = By.xpath("//mat-select[contains(@formcontrolname, 'recipientTypes')]");
	static By recipPhone = By.xpath("//input[contains(@id, '_recipient_phone')]");
	static By recipRoutNo = By.xpath("//input[contains(@id, '_routing_number')]");
	static By recipAccNo = By.xpath("//input[@formcontrolname='accountNumber']");
	static By recipName = By.xpath("//div[contains(@class, 'ng-star-inserted')]//input[contains(@id, '_recipient_name')]");
	static By UniqueReference = By.id("uetrReference");
	private static By addRecipType = By.xpath("//mat-select[@formcontrolname='recipientType']");
	private static By recipNickName = By.id("create_recipient_nick_name"); 
	private static By recipEmail = By.xpath("//input[contains(@id, '_recipient_email')]");
	private static By recipAccType = By.xpath("//mat-select[contains(@id, '_acc_type')]");
	private static By recipRoutingNo = By.xpath("//*[@id='create_recipient_rtn_number']//input");
	private static By intermediaryBank = By.xpath("//input[@id='fw_intermediary_rtn_number2']");
	private static By bicCode = By.id("fw_bic_code");
	private static By iBAN = By.id("fw_iban");
	private static By idType = By.id("fw_id_types");
	private static By idNum  = By.id("fw_idNumber");
	private static By AddRecip_DuplicateMSG = By.id("pay-network-not-supported-msg");
	private static By Edit_Btn = By.xpath("//*[text()='Edit']");
	private static By AuthorizationIdType = By.id("fw_id_types");
	private static By AuthorizationIdNumber = By.id("fw_idNumber");
	private static By NewRecipDropDwn = By.xpath("//a[text()=' New Recipient ']");
	private static By RecipientSearchBox = By.id("fw_recipient_search");
	private static By recipAccountNo = By.id("create_recipient_acc_number");
	private static By SelectingFirstRecipient = By.xpath("//ul[@id='recipient_type_selection']//a[contains(@class, 'select_instant_receipient')]");
	private static By NewRecipDropDwn_DepOp = By.xpath("//a[text()=' New Recipient ']");
	private static By ReleaseLinkInthreeDots = By.xpath("//ul[contains(@class, 'dropdown-menu dropdown-new dropdown-menu-right')]//span[@class='success_action btn']");
	private static By RejectLinkInthreeDots = By.xpath("//ul[contains(@class, 'dropdown-menu dropdown-new dropdown-menu-right')]//span[@class='danger_action btn']");
	private static By DeleteAndEditLinkInthreeDots = By.xpath("//ul[contains(@class, 'dropdown-menu dropdown-new dropdown-menu-right')]//span[@class='default_action btn']");

	private static By NotesMemoTextBox = By.xpath("//textarea[@formcontrolname='memo']");

	private static By ReleaseBtn_AtMemo = By.xpath("//div[@id='dual_approval_actions_rtp_new']//a[text()='Release']");
	private static By RejectBtn_AtMemo = By.xpath("//div[@id='dual_approval_actions_rtp_new']//a[text()='Reject']");
	private static By DeleteBtn_AtMemo = By.xpath("//div[@id='dual_approval_actions_rtp_new']//a[text()='Delete']");
	private static By sucessMsg = By.xpath("//div[contains(@id, '-alert-msg')]//span[text()='Successfully submitted the request']");


	static By amtField_APICallLoadingCircle = By.xpath("//*[@class='mdc-circular-progress__determinate-circle']");
	static By recipAmt = By.xpath("//*[@controlname='amount']//input");
	static By endToEnd = By.id("rtp_end_to_end_ref");
	static By Submit2 = By.xpath("//*[text()='Submit']");
	
	
	static By recipAddress = By.xpath("//*[contains(@id, 'recipient_address')]//input[contains(@id,'google_id')]");
	static By recipAddressLine = By.xpath("//*[contains(@id, 'recipient_address')]//input[@id='addr-lines']");
	static By recipTownName = By.xpath("//*[contains(@id, 'recipient_address')]//input[@id='town']");
	static By recipState = By.xpath("//*[contains(@id, 'recipient_address')]//*[contains(@id, 'state')]");
	static By recipCountry = By.xpath("//*[contains(@id, 'recipient_address')]//*[@id='countryCode']");
	static By recipZipcode = By.xpath("//*[contains(@id, 'recipient_address')]//input[@id='addr-zip1']");
	static By recipState_XB = By.xpath("//*[contains(@id, 'recipient_address')]//input[@id='addr-xb-state']");
	
//Travel Rule check box
	private static By travelruleSenderAddressCheckBox = By.id("capture_sender_override_pobox");
	private static By travelruleRecipientAddressCheckBox = By.id("capture_recipient_override_pobox");

// Related Document
	private static By relatedDocBlockAtFinalPage = By.xpath("//a[@aria-label='Related Documents']");
	private static By relatedDocUploadBtnAtFinalPage = By.xpath("//*[@header='Related Documents']//a[contains(@class,'btn btn-default m-')]");
	private static By relateDocSubmitBtn = By.xpath("//div[@id='documents']//button[text()='Submit']");
	private static By deleteRelatedDocBtn = By.xpath("//div[contains(@id,'create_payout')]//span[@class='text-red ng-star-inserted']");
	private static By yesDeleteBtn_RelatedDoc = By.xpath("//tbody[@class='mdc-data-table__content']//button[text()='Yes, Delete']");
	private static By noDontDeleteBtn_RelatedDoc = By.xpath("//tbody[@class='mdc-data-table__content']//button[@class='btn btn-secondary btn-sm']");
    private static By notesAtRelatedDocDeleteBtn = By.id("confirmNote");
	
	static By reltDocTyp = By.xpath("//mat-select[@formcontrolname='documentType']");
	private static By fwRecipPmtDate = By.id("fw_schdld_date");
	private static By reltDocFil = By.xpath("//input[@name='file']");
	static By contuBtn = By.xpath("//*[text()='Continue']");
	private static By Notes = By.xpath("//textarea[contains(@id, '_notes')]");
	private static By Submit = By.xpath("//a[@id='confirm_recipient']");
	private static By editBtn = By.xpath("//a[contains(text(),'Edit')]");
	private static By feeEdit=By.xpath("//app-amount-input[@controlname='fee']//input");


	//Advance Search    
	private static By accountNumTextBoxForRecipiSearch  = By.id("capture-do-adv-search-sender_acc_no");
	private static By statusDropDown = By.id("rcp_status");
	private static By DivisionDropDown = By.id("create_recipient_division");
	private static By senderNameDropDown = By.id("sender-name");
	private static By DepOpRecipAdvanceSearchBtn = By.xpath("//div[@id='advanced-search']//a[text()='Search ']");

	//** Remittance WebElements--->CommonForBoth_RTP&FedNow **
	static By RemitDoct = By.id("add_remittance_doc_#");
	static By RemitDate = By.id("add_remittance_doc_date");
	static By RemitInvcAmt = By.id("invoice_amt_paid");
	static By RemitDiscAmt =By.id("discount_amount");
	static By recipFeeAmt = By.xpath("//input[contains(@id, '-fee')]");
	private static By RemitID = By.id("remittance_id");
	private static By RemitLocn = By.id("remittance_location");
	private static By RemitLocnMethod = By.id("remit_location_method");
	private static By RemitMemo = By.id("remit_memo");

	//** (1)FedWire--->Remittance WebElements **	
	static By fwRemittRefToRecip = By.id("fw_ref_to_rcp");
	static By fwRemittSenderToRecipInfo = By.xpath("//div[@formarrayname='senderToRecipientInfo']//input");
	static By fwRemittInfoForRecFIDropdwn = By.id("fw_info_type");
	static By fwRemittInfoForRecFI = By.id("fw_info_for_rcp_fi");
	static By fwRemittAddRemitInfo = By.xpath("//*[@formcontrolname='additionalRemittanceType']");
	//** 1(a)FW-Structured Remmitance**	
	// (i)Remittance Orginiator
	static By fwStruOrgIdType = By.id("fw_originator_id_type");
	static By fwStruOrgIdCode = By.xpath("//mat-select[@formcontrolname='idCode']");
	static By fwStruOrgName = By.id("fw_originator_name");
	static By fwStruOrgIdNum = By.id("fw_originator_id");
	static By fwStruOrgAddrsType = By.xpath("//mat-select[@formcontrolname='addressType']");
	static By fwStruOrgAddrsLine1 = By.id("fw_originator_address_line1");
	static By fwStruOrgAddrsLine2 = By.id("fw_originator_address_line2");
	static By fwStruOrgCity = By.id("fw_originator_city");
	static By fwStruOrgState = By.id("fw_originator_state");
	static By fwStruOrgPostalCode = By.id("fw_originator_postal");
	static By fwStruOrgCountry = By.xpath("//mat-select[@formcontrolname='countryCode']");
	static By fwStruOrgPhn = By.id("fw_originator_phone");
	static By fwStruOrgEmail = By.id("fw_originator_email");


	// (i)Remittance Beneficiary
	static By fwStruBenefIdType = By.id("fw_beneficiary_id_type");
	static By fwStruBenefIdCode = By.id("fw_beneficiary_id_type");
	static By fwStruBenefName = By.id("fw_beneficiary_name");
	static By fwStruBenefIdNum = By.id("fw_beneficiary_id");
	static By fwStruBenefAddrsLine1 = By.id("fw_beneficiary_address_line1");
	static By fwStruBenefAddrsLine2 = By.id("fw_beneficiary_address_line2");
	static By fwStruBenefCity = By.id("fw_beneficiary_city");
	static By fwStruBenefState = By.id("fw_beneficiary_state");
	static By fwStruBenefPostalCode = By.id("fw_beneficiary_postal");

	// (iii)Primary Remittance Document
	static By fwStruDocumIdNum = By.id("fw_remit_doc_no");
	static By fwStruDocuDate = By.id("add_remittance_doc_date");
	static By fwStruAmtPaid = By.id("fw_act_amt_paid");
	static By fwStruOrgnlAmtPaid = By.id("fw_orig_amt");
	static By fwStruDiscAmtPaid = By.id("fw_discount_amt");
	static By fwStruAdjAmtPaid = By.id("fw_adj_amt");
	static By fwNotes = By.xpath("//textarea[@formcontrolname='memo']");
	static By fwStructAdjustReason= By.id("fw_remit_adj_rsn_code");
	static By fwAdjustIndic = By.xpath("//mat-select[@formcontrolname='creditOrDebit']");

	//** 1(b)FW-ExternalRemittance **   
	static By fwExtRemitId = By.xpath("//input[@formcontrolname='id']");
	static By fwExtRemitLoc = By.xpath("//input[@formcontrolname='location']");
	static By fwExtRemitLocMethd = By.xpath("//mat-select[@formcontrolname='locationMethod']");


	private static By activeBtn=By.xpath("//a[text()='Active ']");
	private static By phoneNoCopy=By.xpath("(//div[@class='form-group']//strong)[3]");
	private static By threeDots=By.xpath("//i[@class='fa fa-ellipsis-v pt_10']");
	private static By suspendBtn=By.xpath("//span[@class='warning_action btn']");
	private static By revokeBtn=By.xpath("//span[@class='danger_action btn']");
	private static By deleteBtn=By.xpath("//span[@class='default_action btn' and text()='Delete']");
	private static By SuspendBtn=By.linkText("Suspend");
	private static By RevokedBtn=By.linkText("Revoke");
	private static By DeleteBtn=By.linkText("Delete");


	private static By confirmEmail = By.id("confirmationEmail");

	private static By duplicateErrorMsg= By.xpath("//app-dupl-pymnt-msg/div[@class='warning_fixed_inslide']");
	private static By confirmationLink= By.xpath("//app-dupl-pymnt-msg//span[contains(@class,'link')]");

	private static By duplicateRejectErrorMsg= By.xpath("//app-dupl-pymnt-msg//div[normalize-space()='Payments with similar information cannot be submitted at this time. Please try again later.']");
	private static By duplicateWarningErrorMsg= By.xpath("//app-dupl-pymnt-msg//span[contains(normalize-space(),'I have reviewed the details and wish to proceed with the new payment.')]");
	private static By confirmDuplicateCheckbox= By.xpath("//app-dupl-pymnt-msg//input[@type='checkbox']");

	private static By duplicateAmountDetails= By.xpath("//app-dupl-pymnt-msg//div[label[normalize-space()='Amount:']]/strong");

	private static By duplicateScheduleDate= By.xpath("//div[label[normalize-space()='Scheduled Date:']]/strong");

	private static By duplicateReceiverDetails= By.xpath("//app-dupl-pymnt-msg//div[label[normalize-space()='Recipient:']]/strong");
	private static By duplicateConfirmationNo= By.xpath("//div[label[normalize-space()='Confirmation Number:']]/strong");
	private static By duplicatePaymentMethod= By.xpath("//div[label[normalize-space()='Payment Method:']]/strong");




	//XBWire Locators
	private static By saveRecip=By.xpath("//input[@formcontrolname='saveRecipient']");
	static By selectCountry=By.id("xbCountry");
	private static By enterCountryName=By.id("mat-search");
	private static By clickCountry=By.xpath("//*[@id='[object Object]']/span");
	private static By clickCurrency=By.id("xbCurrency");
	private static By xbRecipName=By.id("xb_recipient_name");
	private static By xbRecipientName=By.id("create_recipient_name");
	private static By xbBankAccount=By.id("destinationAccount_bankAccount_accountNumber");
	private static By xbPhoneNumber=By.id("destination_phone");
	private static By xbContactName=By.id("destination_contactName");
	private static By xbRecipType=By.id("destination_partyType");
	private static By xbBankBICCode=By.xpath("//*[@id='xb_routing_number']//input");
	private static By xbBankRoutingorIFSC=By.id("destinationAccount_bankAccount_bankBranchCode");
	private static By xbRecipientTaxId=By.id("destination_partyIdentification_id");
	private static By xbNationality=By.id("destination_nationality");
	private static By xbPurposeOfPay=By.id("purpose_description");
	private static By xbPurposeOfPayCode=By.id("paymentCategory_code");
	private static By xbIBAN=By.id("destinationAccount_bankAccount_accountNumber_iban");
	private static By xbReciAuthType=By.id("destination_partyIdentification_idType");
	private static By xbMethods=By.id("destination_account_methods");



	private String paymentType;
	private String recipientType;
	private String recipientName;
	private String recipNickNam ; 
	private String recipientAddrs;
	private String recipientAddrsLin;
	private String recipientTownName;
	private String recipientState;
	private String recipientCountry;
	private String recipientZip;
	private String recipientEmail;
	private String recipientPhone;
	private String recipientRoutNum;
	private String recipientAccNum;
	private String recipientAccType;
	private String pmtAmt;
	private String pmtDate;
	private String idTyp;
	private String idNumm;


	private String fwRemitRefToRecip;
	private String fwInfoForRecipFIDrpDwn;
	private String fwAdditionalRemitInfo;


	private String fwStructRemitOrgIdType;
	private String fwStructRemitOrgIdCode;
	private String fwStructRemitOrgName;
	private String fwStructRemitOrgIdNum;
	private String fwStructRemitOrgAddrsTyp;
	private String fwStructRemitOrgAddrsLin1;
	private String fwStructRemitOrgAddrsLin2;
	private String fwStructRemitOrgCity;
	private String fwStructRemitOrgState;
	private String fwStructRemitOrgPostal;
	private String fwStructRemitOrgCountry;
	private String fwStructRemitOrgPhn;
	private String fwStructRemitOrgEmail;


	private String fwStructRemitDocType;
	private String fwStructRemitDocIdNum;
	private String fwStructRemitDocDate;
	private String fwStructRemitDocAmtPaid;
	private String fwStructRemitDocOrgnlAmt;
	private String fwStructRemitDocDiscAmt;
	private String fwStructRemitDocAdjstAmt;
	private String fwStructRemitAdjustReason;
	private String fwStructRemitAdjstIndicat;
	private String fwExtrnlRemitLocMetd;
	private String fwExtrnlRemitId;
	private String fwExtrnlRemitLoc;

	private String RemitDocTyp;
	private String RemitDocmt;
	private String RemitDocDate;
	private String RemitDiscoAmt;
	private String RemitId;
	private String RemitLocMethd;
	private String RemitLoc;
	private String RemiteMemo;
	private String docType;
	private String docPath;
	private String endToend;


	/*------   Country Wise Recipient Fields    ------*/
	private String bicCodee;
	private String iBan;
	private String purposeOfPayment;
	private String bankBranchCode;
	private String recipientTax;
	private String IFSCCode;
	private String nationality;
	private String country;
	private String currency;
	private String contactName;





	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getIFSCCode() {
		return IFSCCode;
	}
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}
	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}
	public String getBankBranchCode() {
		return bankBranchCode;
	}
	public void setBankBranchCode(String bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}
	public String getRecipientTax() {
		return recipientTax;
	}
	public void setRecipientTax(String recipientTax) {
		this.recipientTax = recipientTax;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEndToend() {
		return endToend;
	}
	public void setEndToend(String endToend) {
		this.endToend = endToend;
	}
	public String getIdTyp() {
		return idTyp;
	}
	public void setIdTyp(String idTyp) {
		this.idTyp = idTyp;
	}
	public String getIdNumm() {
		return idNumm;
	}
	public void setIdNumm(String idNumm) {
		this.idNumm = idNumm;
	}
	public String getBicCodee() {
		return bicCodee;
	}
	public void setBicCodee(String bicCodee) {
		this.bicCodee = bicCodee;
	}
	public String getiBan() {
		return iBan;
	}
	public void setiBan(String iBan) {
		this.iBan = iBan;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getRecipNickNam() {
		return recipNickNam;
	}
	public void setRecipNickNam(String recipNickNam) {
		this.recipNickNam = recipNickNam;
	}
	public String getFwStructRemitAdjustReason() {
		return fwStructRemitAdjustReason;
	}
	public void setFwStructRemitAdjustReason(String fwStructRemitAdjustReason) {
		this.fwStructRemitAdjustReason = fwStructRemitAdjustReason;
	}
	public String getFwStructRemitAdjstIndicat() {
		return fwStructRemitAdjstIndicat;
	}
	public void setFwStructRemitAdjstIndicat(String fwStructRemitAdjstIndicat) {
		this.fwStructRemitAdjstIndicat = fwStructRemitAdjstIndicat;
	}
	public String getRecipientType() {
		return recipientType;
	}
	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientAddrs() {
		return recipientAddrs;
	}
	public void setRecipientAddrs(String recipientAddrs) {
		this.recipientAddrs = recipientAddrs;
	}
	public String getRecipientAddrsLin() {
		return recipientAddrsLin;
	}
	public void setRecipientAddrsLin(String recipientAddrsLin) {
		this.recipientAddrsLin = recipientAddrsLin;
	}
	public String getRecipientTownName() {
		return recipientTownName;
	}
	public void setRecipientTownName(String recipientTownName) {
		this.recipientTownName = recipientTownName;
	}
	public String getRecipientState() {
		return recipientState;
	}
	public void setRecipientState(String recipientState) {
		this.recipientState = recipientState;
	}
	public String getRecipientCountry() {
		return recipientCountry;
	}
	public void setRecipientCountry(String recipientCountry) {
		this.recipientCountry = recipientCountry;
	}
	public String getRecipientZip() {
		return recipientZip;
	}
	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public String getRecipientRoutNum() {
		return recipientRoutNum;
	}
	public void setRecipientRoutNum(String recipientRoutNum) {
		this.recipientRoutNum = recipientRoutNum;
	}
	public String getRecipientAccNum() {
		return recipientAccNum;
	}
	public void setRecipientAccNum(String recipientAccNum) {
		this.recipientAccNum = recipientAccNum;
	}
	public String getRecipientAccType() {
		return recipientAccType;
	}
	public void setRecipientAccType(String recipientAccType) {
		this.recipientAccType = recipientAccType;
	}
	public String getPmtAmt() {
		return pmtAmt;
	}
	public void setPmtAmt(String pmtAmt) {
		this.pmtAmt = pmtAmt;
	}
	public String getPmtDate() {
		return pmtDate;
	}
	public void setPmtDate(String pmtDate) {
		this.pmtDate = pmtDate;
	}



	public String getFwRemitRefToRecip() {
		return fwRemitRefToRecip;
	}
	public void setFwRemitRefToRecip(String fwRemitRefToRecip) {
		this.fwRemitRefToRecip = fwRemitRefToRecip;
	}
	public String getFwInfoForRecipFIDrpDwn() {
		return fwInfoForRecipFIDrpDwn;
	}
	public void setFwInfoForRecipFIDrpDwn(String fwInfoForRecipFIDrpDwn) {
		this.fwInfoForRecipFIDrpDwn = fwInfoForRecipFIDrpDwn;
	}
	public String getFwAdditionalRemitInfo() {
		return fwAdditionalRemitInfo;
	}
	public void setFwAdditionalRemitInfo(String fwAdditionalRemitInfo) {
		this.fwAdditionalRemitInfo = fwAdditionalRemitInfo;
	}



	public String getFWStructRemitOrgIdType() {
		return fwStructRemitOrgIdType;
	}
	public void setFWStructRemitOrgIdType(String fwStructRemitOrgIdType) {
		this.fwStructRemitOrgIdType = fwStructRemitOrgIdType;
	}
	public String getFWStructRemitOrgIdCode() {
		return fwStructRemitOrgIdCode;
	}
	public void setFWStructRemitOrgIdCode(String fwStructRemitOrgIdCode) {
		this.fwStructRemitOrgIdCode = fwStructRemitOrgIdCode;
	}
	public String getFWStructRemitOrgName() {
		return fwStructRemitOrgName;
	}
	public void setFWStructRemitOrgName(String fwStructRemitOrgName) {
		this.fwStructRemitOrgName = fwStructRemitOrgName;
	}
	public String getFWStructRemitOrgIdNum() {
		return fwStructRemitOrgIdNum;
	}
	public void setFWStructRemitOrgIdNum(String fwStructRemitOrgIdNum) {
		this.fwStructRemitOrgIdNum = fwStructRemitOrgIdNum;
	}
	public String getFWStructRemitOrgAddrsTyp() {
		return fwStructRemitOrgAddrsTyp;
	}
	public void setFWStructRemitOrgAddrsTyp(String fwStructRemitOrgAddrsTyp) {
		this.fwStructRemitOrgAddrsTyp = fwStructRemitOrgAddrsTyp;
	}
	public String getFWStructRemitOrgAddrsLin1() {
		return fwStructRemitOrgAddrsLin1;
	}
	public void setFWStructRemitOrgAddrsLin1(String fwStructRemitOrgAddrsLin1) {
		this.fwStructRemitOrgAddrsLin1 = fwStructRemitOrgAddrsLin1;
	}
	public String getFWStructRemitOrgAddrsLin2() {
		return fwStructRemitOrgAddrsLin2;
	}
	public void setFWStructRemitOrgAddrsLin2(String fwStructRemitOrgAddrsLin2) {
		this.fwStructRemitOrgAddrsLin2 = fwStructRemitOrgAddrsLin2;
	}
	public String getfwStructRemitOrgCity() {
		return fwStructRemitOrgCity;
	}
	public void setFWStructRemitOrgCity(String fwStructRemitOrgCity) {
		this.fwStructRemitOrgCity = fwStructRemitOrgCity;
	}
	public String getFWStructRemitOrgState() {
		return fwStructRemitOrgState;
	}
	public void setFWStructRemitOrgState(String fwStructRemitOrgState) {
		this.fwStructRemitOrgState = fwStructRemitOrgState;
	}
	public String getFWStructRemitOrgPostal() {
		return fwStructRemitOrgPostal;
	}
	public void setFWStructRemitOrgPostal(String fwStructRemitOrgPostal) {
		this.fwStructRemitOrgPostal = fwStructRemitOrgPostal;
	}
	public String getFWStructRemitOrgCountry() {
		return fwStructRemitOrgCountry;
	}
	public void setFWStructRemitOrgCountry(String fwStructRemitOrgCountry) {
		this.fwStructRemitOrgCountry = fwStructRemitOrgCountry;
	}
	public String getFWStructRemitOrgPhn() {
		return fwStructRemitOrgPhn;
	}
	public void setfwStructRemitOrgPhn(String fwStructRemitOrgPhn) {
		this.fwStructRemitOrgPhn = fwStructRemitOrgPhn;
	}
	public String getFWStructRemitOrgEmail() {
		return fwStructRemitOrgEmail;
	}
	public void setFWStructRemitOrgEmail(String fwStructRemitOrgEmail) {
		this.fwStructRemitOrgEmail = fwStructRemitOrgEmail;
	}




	public String getFWStructRemitDocType() {
		return fwStructRemitDocType;
	}
	public void setFWStructRemitDocType(String fwStructRemitDocType) {
		this.fwStructRemitDocType = fwStructRemitDocType;
	}
	public String getFWStructRemitDocIdNum() {
		return fwStructRemitDocIdNum;
	}
	public void setFWStructRemitDocIdNum(String fwStructRemitDocIdNum) {
		this.fwStructRemitDocIdNum = fwStructRemitDocIdNum;
	}
	public String getFWStructRemitDocDate() {
		return fwStructRemitDocDate;
	}
	public void setFWStructRemitDocDate(String fwStructRemitDocDate) {
		this.fwStructRemitDocDate = fwStructRemitDocDate;
	}
	public String getFWStructRemitDocAmtPaid() {
		return fwStructRemitDocAmtPaid;
	}
	public void setFWStructRemitDocAmtPaid(String fwStructRemitDocAmtPaid) {
		this.fwStructRemitDocAmtPaid = fwStructRemitDocAmtPaid;
	}
	public String getFWStructRemitDocOrgnlAmt() {
		return fwStructRemitDocOrgnlAmt;
	}
	public void setFWStructRemitDocOrgnlAmt(String fwStructRemitDocOrgnlAmt) {
		this.fwStructRemitDocOrgnlAmt = fwStructRemitDocOrgnlAmt;
	}
	public String getFWStructRemitDocDiscAmt() {
		return fwStructRemitDocDiscAmt;
	}
	public void setFWStructRemitDocDiscAmt(String fwStructRemitDocDiscAmt) {
		this.fwStructRemitDocDiscAmt = fwStructRemitDocDiscAmt;
	}
	public String getFWStructRemitDocAdjstAmt() {
		return fwStructRemitDocAdjstAmt;
	}
	public void setFWStructRemitDocAdjstAmt(String fwStructRemitDocAdjstAmt) {
		this.fwStructRemitDocAdjstAmt = fwStructRemitDocAdjstAmt;
	}
	public String getFWExtrnlRemitLocMetd() {
		return fwExtrnlRemitLocMetd;
	}
	public void setFWExtrnlRemitLocMetd(String fwExtrnlRemitLocMetd) {
		this.fwExtrnlRemitLocMetd = fwExtrnlRemitLocMetd;
	}
	public String getFWExtrnlRemitId() {
		return fwExtrnlRemitId;
	}
	public void setFWExtrnlRemitId(String fwExtrnlRemitId) {
		this.fwExtrnlRemitId = fwExtrnlRemitId;
	}
	public String getFWExtrnlRemitLoc() {
		return fwExtrnlRemitLoc;
	}
	public void setFWExtrnlRemitLoc(String fwExtrnlRemitLoc) {
		this.fwExtrnlRemitLoc = fwExtrnlRemitLoc;
	}




	public String getRemitDocTyp() {
		return RemitDocTyp;
	}
	public void setRemitDocTyp(String remitDocTyp) {
		RemitDocTyp = remitDocTyp;
	}
	public String getRemitDocmt() {
		return RemitDocmt;
	}
	public void setRemitDocmt(String remitDocmt) {
		RemitDocmt = remitDocmt;
	}
	public String getRemitDocDate() {
		return RemitDocDate;
	}
	public void setRemitDocDate(String remitDocDate) {
		RemitDocDate = remitDocDate;
	}
	public String getRemitDiscoAmt() {
		return RemitDiscoAmt;
	}
	public void setRemitDiscoAmt(String remitDiscoAmt) {
		RemitDiscoAmt = remitDiscoAmt;
	}
	public String getRemitId() {
		return RemitId;
	}
	public void setRemitId(String remitId) {
		RemitId = remitId;
	}
	public String getRemitLocMethd() {
		return RemitLocMethd;
	}
	public void setRemitLocMethd(String remitLocMethd) {
		RemitLocMethd = remitLocMethd;
	}
	public String getRemitLoc() {
		return RemitLoc;
	}
	public void setRemitLoc(String remitLoc) {
		RemitLoc = remitLoc;
	}
	public String getRemiteMemo() {
		return RemiteMemo;
	}
	public void setRemiteMemo(String remiteMemo) {
		RemiteMemo = remiteMemo;
	}





	//** Common for All (RecipientDetailsMethod) **	
	public static void enterRecipientDetails(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		if(!objDetails.getPaymentType().equals("Edit")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(recipType));
			while(WD.findElement(recipType).getDomAttribute("aria-expanded").equals("false"))
			{
				WD.findElement(recipType).click();
			}
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'recipient_type')]//span[text()='"+objDetails.getRecipientType()+"']")));
			WD.findElement(By.xpath("//div[contains(@id,'recipient_type')]//span[text()='"+objDetails.getRecipientType()+"']")).click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(recipName)).clear();
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
		wait.until(ExpectedConditions.presenceOfElementLocated(recipPhone)).clear();
		WD.findElement(recipPhone).sendKeys(objDetails.getRecipientPhone());
		wait.until(ExpectedConditions.presenceOfElementLocated(recipRoutNo)).clear();
		WD.findElement(recipRoutNo).sendKeys(objDetails.getRecipientRoutNum());
		wait.until(ExpectedConditions.presenceOfElementLocated(recipAccNo)).clear();
		WD.findElement(recipAccNo).sendKeys(objDetails.getRecipientAccNum());
		wait.until(ExpectedConditions.presenceOfElementLocated(recipEmail)).clear();
		WD.findElement(recipEmail).sendKeys(objDetails.getRecipientEmail());
		wait.until(ExpectedConditions.presenceOfElementLocated(recipAccType));
		while(WD.findElement(recipAccType).getDomAttribute("aria-expanded").equals("false"))
		{
			WD.findElement(recipAccType).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientAccType()+"']")));
		WD.findElement(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientAccType()+"']")).click();

	}

	public static void enterRecipientDetails_FI(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		if(!objDetails.getRecipientType().equalsIgnoreCase("")&&!objDetails.getPaymentType().equals("Edit")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(recipType));
			WD.findElement(recipType).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_recipient_types-panel']//span[text()='"+objDetails.getRecipientType()+"']")));
			WD.findElement(By.xpath("//div[@id='fw_recipient_types-panel']//span[text()='"+objDetails.getRecipientType()+"']")).click(); 
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(recipName)).clear();
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
		WD.findElement(recipEmail).clear();
		WD.findElement(recipEmail).sendKeys(objDetails.getRecipientEmail());
		WD.findElement(recipRoutNo).clear();
		WD.findElement(recipRoutNo).sendKeys(objDetails.getRecipientRoutNum());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'info_small routing_no_bankname placeholder_ellipsis routing_no')]")));

	}

	public static void RecipientType_Validation_FI() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipType));
		WD.findElement(recipType).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='FI']")));
		WebElement listbox = WD.findElement(By.id("fw_recipient_types-panel"));
		// Locate "Individual" and "Business" options based on their text
		WebElement individualOption = listbox.findElement(By.xpath(".//mat-option[.//span[text()='Individual']]"));
		WebElement businessOption = listbox.findElement(By.xpath(".//mat-option[.//span[text()='Business']]"));
		WebElement FI = listbox.findElement(By.xpath(".//mat-option[.//span[text()='FI']]"));
		// Check if the options are disabled
		Assert.assertTrue(individualOption.getDomAttribute("aria-disabled").equals("true"));
		Assert.assertTrue(businessOption.getDomAttribute("aria-disabled").equals("true"));
		Assert.assertTrue(FI.getDomAttribute("aria-disabled").equals("false"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_recipient_types-panel']//span[text()='FI']"))).click();
	}


	public static void enterRecipientDetails_ForeignIndividual(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipType));
		WD.findElement(recipType).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")));
		WD.findElement(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")).click();
		//		Routing Number As IntermediateBank
		WD.findElement(intermediaryBank).sendKeys(objDetails.getRecipientRoutNum());
		WD.findElement(intermediaryBank).sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' AVIDIA BANK ']")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(recipName));
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
		WD.findElement(AuthorizationIdType).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_id_types-panel']//span[text()='"+objDetails.getIdTyp()+"']")));
		WD.findElement(By.xpath("//div[@id='fw_id_types-panel']//span[text()='"+objDetails.getIdTyp()+"']")).click();
		WD.findElement(AuthorizationIdNumber).sendKeys(objDetails.getIdNumm());
		WD.findElement(bicCode).sendKeys(objDetails.getBicCodee());
		WD.findElement(iBAN).sendKeys(objDetails.getiBan());
	}

	public static void selectingExistingRecipientIndividual_Dupaco_DepOp(String recipiName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(NewRecipDropDwn));
		WD.findElement(NewRecipDropDwn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(RecipientSearchBox));
		WD.findElement(RecipientSearchBox).sendKeys(recipiName);
		WD.findElement(RecipientSearchBox).sendKeys(Keys.SPACE);
		WD.findElement(RecipientSearchBox).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		WD.findElement(SelectingFirstRecipient).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'selected_instant_recipient')]")));
	}


	public static void selectingExistingRecipientForeignIndividual_Dupaco_DepOp(String recipiName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(NewRecipDropDwn_DepOp));
		WD.findElement(NewRecipDropDwn_DepOp).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(RecipientSearchBox));
		WD.findElement(RecipientSearchBox).sendKeys(recipiName);
		WD.findElement(RecipientSearchBox).sendKeys(Keys.SPACE);
		WD.findElement(RecipientSearchBox).sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		WD.findElement(SelectingFirstRecipient).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'selected_instant_recipient')]")));
		WD.findElement(intermediaryBank).sendKeys("211370396");
		WD.findElement(intermediaryBank).sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' AVIDIA BANK ']")));
	}

	public static void saveRecipient() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(saveRecip));
		WD.findElement(saveRecip).click();
	}
	
	public static void selectPaymentNetworks() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='enableFedNow']")));
		WD.findElement(By.xpath("//input[@formcontrolname='enableFedNow']")).click();
		WD.findElement(By.xpath("//input[@formcontrolname='enableRTP']")).click();
	}

	public static void enterAddressDetails(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipAddress)).clear();
		WD.findElement(recipAddress).sendKeys(objDetails.getRecipientAddrs());
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
			WD.findElement(By.className("pac-item")).click();
		}catch(Exception e) {
			WD.findElement(recipAddress).sendKeys(Keys.SPACE, Keys.ARROW_DOWN);
		}
	}

	public static void enterRecipientAddressDetailsInAllIndividualFields(AddRecipientDetails objDetails) throws Exception  {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(recipAddress)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipAddress));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(recipAddress));
		js.executeScript("arguments[0].value='';", WD.findElement(recipAddress));
		Thread.sleep(2000);
		WD.findElement(recipAddress).sendKeys(objDetails.getRecipientAddrs(), Keys.TAB);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(recipAddressLine)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipAddressLine));
		wait.until(ExpectedConditions.presenceOfElementLocated(recipZipcode));
		WD.findElement(recipAddressLine).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		WD.findElement(recipAddressLine).sendKeys(objDetails.getRecipientAddrsLin());
		WD.findElement(recipTownName).sendKeys(objDetails.getRecipientTownName());
		if(!objDetails.getRecipientCountry().equalsIgnoreCase("")) {
			while(WD.findElement(recipCountry).getDomAttribute("aria-expanded").equals("false"))
			{
				WD.findElement(recipCountry).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getRecipientCountry()+"')]")));
			WD.findElement(By.xpath("//div[@id='countryCode-panel']//span[contains(text() ,'"+objDetails.getRecipientCountry()+"')]")).click(); 
			if(WD.findElement(recipState).getTagName().equalsIgnoreCase("input")) {
				WD.findElement(recipState).click();
				WD.findElement(recipState).sendKeys(objDetails.getRecipientState()); 
			}else {
				while(WD.findElement(recipState).getDomAttribute("aria-expanded").equals("false")) {
					WD.findElement(recipState).click();  }
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='state-panel']//span[contains(text(), '"+objDetails.getRecipientState()+"')]")));
				WD.findElement(By.xpath("//div[@id='state-panel']//span[contains(text(),'"+objDetails.getRecipientState()+"')]")).click(); 
			}		
		}
		else {
			if(WD.findElement(recipState).getTagName().equalsIgnoreCase("input")) {
				WD.findElement(recipState).click();
				WD.findElement(recipState).sendKeys(objDetails.getRecipientState()); 
			}else {
				while(WD.findElement(recipState).getDomAttribute("aria-expanded").equals("false")) {
					WD.findElement(recipState).click();  }
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='state-panel']//span[contains(text(), '"+objDetails.getRecipientState()+"')]")));
				WD.findElement(By.xpath("//div[@id='state-panel']//span[text()='"+objDetails.getRecipientState()+"']")).click(); 
			}
		
		}
		WD.findElement(recipZipcode).sendKeys(objDetails.getRecipientZip(), Keys.TAB);
	}

	public static String enterRandomPhoneNum() {
        Random rand = new Random();
        int firstDigit = rand.nextInt(9) + 1;
        long remainingDigits = (long) (rand.nextDouble() * 1_000_000_000L);
        String phone = firstDigit + String.format("%09d", remainingDigits); 
        WD.findElement(recipPhone).clear();
        WD.findElement(recipPhone).sendKeys(phone);
        return phone;
    }
	
	public static String enterNewRecipientDetails(AddRecipientDetails objDetails) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipType));
        int count = 0;
        String phoneNo="";
        while(WD.findElement(addRecipType).getDomAttribute("aria-expanded").equals("false"))
        {
            WD.findElement(addRecipType).click();
            count++;
            if(count > 5) {
                break;
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objDetails.getRecipientType())));
        WD.findElement(By.id(objDetails.getRecipientType())).click();
        WD.findElement(recipEmail).sendKeys(objDetails.getRecipientEmail());
        WD.findElement(recipNickName).sendKeys(objDetails.getRecipNickNam());
        WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
        WD.findElement(recipRoutingNo).sendKeys(objDetails.getRecipientRoutNum());
        if(!objDetails.getRecipientType().equals("FI")) {
        WD.findElement(recipAccountNo).sendKeys(objDetails.getRecipientAccNum());
        phoneNo=enterRandomPhoneNum();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recipAccType));
        WD.findElement(recipAccType).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getRecipientAccType()+"']")));
        WD.findElement(By.xpath("//span[text()='"+objDetails.getRecipientAccType()+"']")).click();
        }
        return phoneNo;
    }


	public static void enterNewRecipientDetails_FIRecipientType(AddRecipientDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipType));
		while(WD.findElement(addRecipType).getDomAttribute("aria-expanded").equals("false"))
		{
			WD.findElement(addRecipType).click();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objDetails.getRecipientType())));
		WD.findElement(By.id(objDetails.getRecipientType())).click();
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());	
		WD.findElement(recipRoutingNo).sendKeys(objDetails.getRecipientRoutNum());
	}

	public static void enterFeeAmount() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipFeeAmt));
		WD.findElement(recipFeeAmt).sendKeys(Keys.CLEAR);
		WD.findElement(recipFeeAmt).sendKeys(Keys.CLEAR);
		WD.findElement(recipFeeAmt).sendKeys("2.33", Keys.TAB);
	}

	public static void pmtDateCheck_FItoFI(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwRecipPmtDate));
		Assert.assertFalse(WD.findElement(fwRecipPmtDate).isEnabled(), "Msg:- FI to FI payment date is enable ");
	}

	public static void enterFWFuturePaymentDate(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwRecipPmtDate));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(fwRecipPmtDate));
		 js.executeScript("arguments[0].click();",WD.findElement(fwRecipPmtDate));  
//		WD.findElement(fwRecipPmtDate).click();
		selectingDate(objDetails.getPmtDate());
	}

	public static void selectingDate(String date) throws InterruptedException {
		String[] split = date.split(","); 
		String month = split[0];       
		String day = split[1];          
		String year = split[2]; 
		// Click the year dropdown and select the correct year
		WebElement yearDropdown = WD.findElement(By.xpath("//select[@aria-label='Select year']"));
		yearDropdown.click();
		WebElement yearOption = WD.findElement(By.xpath("//select[@aria-label='Select year']/option[contains(text(),'" + year + "')]"));
		yearOption.click();
		// Click the month dropdown and select the correct month
		WebElement monthDropdown = WD.findElement(By.xpath("//select[@aria-label='Select month']"));
		monthDropdown.click();
		WebElement monthOption = WD.findElement(By.xpath("//select[@aria-label='Select month']/option[contains(text(),'" + month.substring(0, 3) + "')]"));
		monthOption.click();
		// For selecting the day
		List<WebElement> days = WD.findElements(By.xpath("//div[@class='ngb-dp-day ng-star-inserted']//div[@ngbdatepickerdayview and text()='" + day + "']"));
		// Click on the correct day
		for (WebElement d : days) {
			if (d.isEnabled()) {
				d.click();
				break;
			}
		}
	}
	public static void enterNotes() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwNotes));
		WD.findElement(fwNotes).sendKeys("hdks");
	}
	
	public static void saveRecipientCheckbox() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@formcontrolname='saveRecipient']"))).click();
	}
	public static void enterAmount(AddRecipientDetails objDetails) throws IOException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(recipAmt));
        WebElement amountField=WD.findElement(recipAmt);
        ((JavascriptExecutor) WD).executeScript("arguments[0].value='';", amountField);
        WD.findElement(recipAmt).sendKeys(Keys.ENTER);
        WD.findElement(recipAmt).sendKeys(" ");
        Double amt = Double.valueOf(objDetails.getPmtAmt());
        WD.findElement(recipAmt).sendKeys(amt.toString());
        WD.findElement(recipAmt).sendKeys(Keys.SPACE,Keys.ENTER,Keys.TAB);
        amountField=WD.findElement(recipAmt);
        ((JavascriptExecutor) WD).executeScript("arguments[0].value='';", amountField);
        WD.findElement(recipAmt).sendKeys(Keys.ENTER);
        WD.findElement(recipAmt).sendKeys(" ");
        amt = Double.valueOf(objDetails.getPmtAmt());
        WD.findElement(recipAmt).sendKeys(amt.toString());
        WD.findElement(recipAmt).sendKeys(Keys.SPACE,Keys.ENTER,Keys.TAB);
    }
	
	public static void enterAmount002(AddRecipientDetails objDetails) throws IOException {
		 WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(recipAmt));
	         WD.findElement(recipAmt).clear();
	        WD.findElement(recipAmt).sendKeys(Keys.CLEAR, objDetails.getPmtAmt(), Keys.TAB);
	}

	public static void enterCurrentDate() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fwRecipPmtDate));
        WD.findElement(fwRecipPmtDate).click();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM,d,yyyy");
        String date=currentDate.format(formatter);
        String[] split = date.split(","); 
        String month = split[0];       
        String year = split[2]; 
        // Click the year dropdown and select the correct year
        WebElement yearDropdown = WD.findElement(By.xpath("//select[@aria-label='Select year']"));
        yearDropdown.click();
        WebElement yearOption = WD.findElement(By.xpath("//select[@aria-label='Select year']/option[contains(text(),'" + year + "')]"));
        yearOption.click();
        // Click the month dropdown and select the correct month
        WebElement monthDropdown = WD.findElement(By.xpath("//select[@aria-label='Select month']"));
        monthDropdown.click();
        WebElement monthOption = WD.findElement(By.xpath("//select[@aria-label='Select month']/option[contains(text(),'" + month.substring(0, 3) + "')]"));
        monthOption.click();
        WD.findElement(By.xpath("//div[@class='ngb-dp-day ng-star-inserted ngb-dp-today']//div")).click();
    }

	

	public static void enterAmount_CheckAPIcall(AddRecipientDetails objDetails) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipAmt));
		// Clearing the Amount field with JavaScript Code (clear() is not working for this field)
		((JavascriptExecutor) WD).executeScript("arguments[0].value='';", WD.findElement(recipAmt));
		WD.findElement(recipAmt).sendKeys(objDetails.getPmtAmt(),Keys.TAB);

		if(objDetails.getRecipientType().equalsIgnoreCase("FI")) {
			int count = 0 ;
			while(count< 10) {
				boolean result =WD.findElement(amtField_APICallLoadingCircle).isDisplayed(); 
				//			    System.out.println(result); 
				Assert.assertFalse(result, "Loading Circle displayed--> API call is happening for FI to FI Pmt"); 
				//			    System.out.println("Count Num"+count);
				count++ ;   
			}
		}
		else {
			wait.until(ExpectedConditions.presenceOfElementLocated(amtField_APICallLoadingCircle));
		}
	}

	public static void enter100000Amount() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipAmt));
		WD.findElement(recipAmt).sendKeys("99999");
		WD.findElement(recipAmt).sendKeys(Keys.TAB);
	}

	public static void uploadRelatedDocAndSelectDocType(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(reltDocTyp));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElements(reltDocTyp).get(0));
		js.executeScript("arguments[0].click();",WD.findElements(reltDocTyp).get(0));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+objDetails.getDocType()+"']")));
		WD.findElement(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+objDetails.getDocType()+"']")).click();
		String fileLocation = System.getProperty("user.dir").toString() + ""+objDetails.getDocPath()+"";
		WD.findElement(reltDocFil).sendKeys(fileLocation);
	}
	
	
	
	
	public static void uploadRelatedDocFile_AndSelectDocType_AtFirstPage(String docType, String docPath) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(reltDocTyp));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElements(reltDocTyp).get(0));
		js.executeScript("arguments[0].click();",WD.findElements(reltDocTyp).get(0));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+docType+"']")));
		WD.findElement(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+docType+"']")).click();
		String fileLocation = System.getProperty("user.dir").toString() + ""+docPath+"";
		WD.findElement(reltDocFil).sendKeys(fileLocation);
		WD.findElement(recipAmt).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB );
////  ArrayList for storing the Related Document Details	
//		List<String> related_Doc_DetailsList = new ArrayList<String>();	
////	Removing the Unnecessary characters in that ex:- From this "/RelatedDoc_MP3_Type_.mp3"  to this "RelatedDoc_MP3_Type_"
//    	related_Doc_DetailsList.add(objDetails.getDocPath().replace("/RelatedDocuments/", ""));
//    	Thread.sleep(3000);
//    	return related_Doc_DetailsList ;
	}
	
	
	
	public static void uploadRelatedDocFile_AndSelectDocType_AtFinalPage(String docType, String docPath) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocBlockAtFinalPage));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(relatedDocBlockAtFinalPage));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocUploadBtnAtFinalPage));
		js.executeScript("arguments[0].click();",WD.findElement(relatedDocUploadBtnAtFinalPage));
		wait.until(ExpectedConditions.visibilityOfElementLocated(reltDocTyp));
		js.executeScript("arguments[0].click();",WD.findElement(reltDocTyp));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+docType+"']")));
		WD.findElement(By.xpath("//div[contains(@id, '-panel')]//span[text()='"+docType+"']")).click();
		String fileLocation = System.getProperty("user.dir").toString() + ""+docPath+"";
		WD.findElement(reltDocFil).sendKeys(fileLocation);
        WD.findElement(relateDocSubmitBtn).click();
        if(docPath.contains("NormalSize")) {
//  We are using this method for uploading the Normal size file and also File above 10mb ...So the success message will display only for Normal size files     	
     		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='scroll-sticky-alert-msg']//span[text()='Successfully added the document.']")));
        }
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(AddSenderDetails.paymentStatus));
////  ArrayList for storing the Related Document Details	
//		List<String> related_Doc_DetailsList = new ArrayList<String>();	
//    	related_Doc_DetailsList.add(docPath.replace("/RelatedDocuments/", ""));
//    	Thread.sleep(3000);
//    	return related_Doc_DetailsList ;
	}
	
	

	public static void validate_RelatedDocDetails_AtFinalPage_ForSingleFile(AddRecipientDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocBlockAtFinalPage));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(relatedDocBlockAtFinalPage));
		do {
			js.executeScript("arguments[0].click();",WD.findElement(relatedDocBlockAtFinalPage));

		   }while(WD.findElement(relatedDocBlockAtFinalPage).getDomAttribute("aria-expanded").equals("false"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='documents']//table//tbody")));
		
	   String docType = WD.findElements(By.xpath("//*[contains(@class,'mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-documentTypeName')]//span[contains(@class, 'ng-star-inserted')]")).get(0).getText();
	   String docFileName = WD.findElements(By.xpath("//*[contains(@class,'mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-uploadedFileName')]//span[contains(@class, 'ng-star-inserted')]")).get(0).getText();
//	Using trim() to removing the unnecessary space at starting and ending of docType_word & docFileName_word
	   Assert.assertEquals(docType.trim(), objDetails.getDocType());
//	Removing the Unnecessary characters in that ex:- From this "/RelatedDocuments/RelatedDoc_MP3_Type.mp3"  to this "RelatedDoc_MP3_Type"
       Assert.assertEquals(docFileName.trim(), objDetails.getDocPath().substring(18));

	}
	
	public static List<String> uploadingMultipleRelatedDocuments_AndSelectingDocType(String pageName , String numOfFiles ) throws IOException, InterruptedException {
		List<String> allDocTypeDetails = new ArrayList<String>();	
		allDocTypeDetails.add("Miscellaneous");
		allDocTypeDetails.add("Miscellaneous");
		allDocTypeDetails.add("Miscellaneous");
		allDocTypeDetails.add("Miscellaneous");
		allDocTypeDetails.add("Miscellaneous");
		allDocTypeDetails.add("Miscellaneous");
		List<String> allDocPaths = new ArrayList<String>();	
		allDocPaths.add("/RelatedDocuments/RelatedDoc_PNG_Type_NormalSize.png");
		allDocPaths.add("/RelatedDocuments/RelatedDoc_JPG_Type_NormalSize.jpg");
		allDocPaths.add("/RelatedDocuments/RelatedDoc_JPEG_Type_NormalSize.jpeg");
		allDocPaths.add("/RelatedDocuments/RelatedDoc_PDF_Type_NormalSize.pdf");
		allDocPaths.add("/RelatedDocuments/RelatedDoc_BMP_Type_NormalSize.bmp");
		allDocPaths.add("/RelatedDocuments/RelatedDoc_WAV_Type_NormalSize.wav");
		if(numOfFiles.equalsIgnoreCase("SevenFiles")) {
			allDocTypeDetails.add("Miscellaneous");
			allDocPaths.add("/RelatedDocuments/RelatedDoc_MP3_Type_NormalSize.mp3");
		}
//  ArrayList for storing the Related Document Details	
		List<String> related_Doc_DetailsList = new ArrayList<String>();	
		int docTypeIndex = 0;
		if(pageName.equalsIgnoreCase("FirstPage")) {
			for (String singleDocPath : allDocPaths) {
				uploadRelatedDocFile_AndSelectDocType_AtFirstPage(allDocTypeDetails.get(docTypeIndex), singleDocPath);
		    	related_Doc_DetailsList.add(singleDocPath.replace("/RelatedDocuments/", ""));
				docTypeIndex++;
				Thread.sleep(5000);
			}
		}
		else if(pageName.equalsIgnoreCase("FinalPage")) {
			for (String singleDocPath : allDocPaths) {
				uploadRelatedDocFile_AndSelectDocType_AtFinalPage(allDocTypeDetails.get(docTypeIndex), singleDocPath);
		    	related_Doc_DetailsList.add(singleDocPath.replace("/RelatedDocuments/", ""));
				docTypeIndex++;
				Thread.sleep(5000);
			}
		}else {
			Assert.fail("Uploading Related Document code didn't got executed...Please check Document path  w.r.t  if-block string");
		}

	    	Thread.sleep(3000);
	    	return related_Doc_DetailsList ;	
	}
	
	public static void validate_RelatedDocDetails_AtFinalPage_ForMultipleFiles(List<String> relatedDocPath) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocBlockAtFinalPage));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(relatedDocBlockAtFinalPage));
		do {
			js.executeScript("arguments[0].click();",WD.findElement(relatedDocBlockAtFinalPage));

		   }while(WD.findElement(relatedDocBlockAtFinalPage).getDomAttribute("aria-expanded").equals("false"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='documents']//table//tbody")));
		for (String s1 : relatedDocPath) {
			System.out.println("Expected String stored in the list :- "+s1);
		}
        List<String> actualDocNames = new ArrayList<>();
        List<String> actualDocTypes = new ArrayList<>();
		int indexnum = 1 ;
		 List<WebElement> multipleDocPaths = WD.findElements(By.xpath("//div[@id='documents']//table//tbody//tr//td[contains(@class, 'mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-uploadedFileName')]//span[@class='cursor-pointer ng-star-inserted']"));
		for (WebElement singleDocPaths : multipleDocPaths) {			
			   String docFileName = singleDocPaths.getText();
			   String docType = singleDocPaths.findElements(By.xpath("//div[@id='documents']//table//tbody//tr//td[contains(@class, 'mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-documentTypeName')]//span[contains(@class, 'ng-star-inserted')]")).get((indexnum*3)-1).getText();
		        
		        actualDocNames.add(docFileName.trim());
		        actualDocTypes.add(docType.trim());
			   System.out.println(docType);
				System.out.println("Actual String getting from the UI :- "+docFileName);
			   Assert.assertTrue(docType.contains("Miscellaneous"), "AssertFailed at Document type Validation");
//			   Assert.assertTrue(relatedDocPath.contains(docFileName.trim()), "AssertFailed at Document File Name Validation");
			   indexnum++ ;
		   }
		validateMessages(relatedDocPath,actualDocNames);
//		validateMessages(relatedDocPath,actualDocTypes);

		
		}
		
	public static void validateMessages(List<String> expectedDocDetails, List<String> actualDocDetails) {
		// Convert to sets of trimmed strings
		        Set<String> expectedSet = new HashSet<>(expectedDocDetails);
		        Set<String> actualSet = new HashSet<>(actualDocDetails);
		        if (!expectedSet.equals(actualSet)) {
		// Checking if there are any missing Doc Details or not (ExceptedMsgs - ActualMsgs)
		        	Set<String> missing = new HashSet<>(expectedSet);
		            missing.removeAll(actualSet);
		// Checking if there are any Unexpected Doc Details or not (ActualMsgs - ExceptedMsgs)
		            Set<String> unexpected = new HashSet<>(actualSet);
		            unexpected.removeAll(expectedSet);
		// Building the String to print all the Missing/Unexpected Doc Details
		            StringBuilder errorMessage = new StringBuilder();
		            errorMessage.append("Related Doc details validation failed!\n");
		            if (!missing.isEmpty()) {
		                errorMessage.append("Missing Details:\n");
		                for (String msg : missing) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		            if (!unexpected.isEmpty()) {
		                errorMessage.append("Unexpected Details:\n");
		                for (String msg : unexpected) {
		                    errorMessage.append(":- ").append(msg).append("\n");
		                }
		            }
		//  Fail the test
		            Assert.fail(errorMessage.toString());
		        } else {
		            System.out.println("Test Case :- Pass ");
		            System.out.println("Expected Doc details are matching with Actual Doc details..!");

		        }
		    }

		
		
	
	
	public static void Validate_DeletedRelatedDocumentAtFinalPage() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocBlockAtFinalPage));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(relatedDocBlockAtFinalPage));
		do {
			js.executeScript("arguments[0].click();",WD.findElement(relatedDocBlockAtFinalPage));
		   }while(WD.findElement(relatedDocBlockAtFinalPage).getDomAttribute("aria-expanded").equals("false"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@header='Related Documents']//div[@class='block_style collapsable_block']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' No documents available for the payment.']")));

	}
	


	
	
	
	public static void cancelingAndThenDeletingTheUploadedRelatedDoc() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteRelatedDocBtn));
		WD.findElement(deleteRelatedDocBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(noDontDeleteBtn_RelatedDoc));
		WD.findElement(noDontDeleteBtn_RelatedDoc).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteRelatedDocBtn));
		WD.findElement(deleteRelatedDocBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(yesDeleteBtn_RelatedDoc));
		WD.findElement(yesDeleteBtn_RelatedDoc).click();
	}
	
	public static void cancelingAndThenDeletingTheUploadedRelatedDocAtFinalPage() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(relatedDocBlockAtFinalPage));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(relatedDocBlockAtFinalPage));
		do {
			js.executeScript("arguments[0].click();",WD.findElement(relatedDocBlockAtFinalPage));
		   }while(WD.findElement(relatedDocBlockAtFinalPage).getDomAttribute("aria-expanded").equals("false"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@header='Related Documents']//div[@class='block_style collapsable_block']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteRelatedDocBtn));
		WD.findElement(deleteRelatedDocBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(notesAtRelatedDocDeleteBtn));
		WD.findElement(notesAtRelatedDocDeleteBtn).sendKeys("Notesss");
		wait.until(ExpectedConditions.visibilityOfElementLocated(noDontDeleteBtn_RelatedDoc));
		WD.findElement(noDontDeleteBtn_RelatedDoc).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteRelatedDocBtn));
		WD.findElement(deleteRelatedDocBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(notesAtRelatedDocDeleteBtn));
		WD.findElement(notesAtRelatedDocDeleteBtn).sendKeys("Notesss");
		wait.until(ExpectedConditions.visibilityOfElementLocated(yesDeleteBtn_RelatedDoc));
		WD.findElement(yesDeleteBtn_RelatedDoc).click();
 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='scroll-sticky-alert-msg']//span[text()='Successfully deleted the document.']")));
	}

	//** RemittanceDetailsMethod--->CommonForRTP&FedNow **
	public static void enterRTPRemittanceDetails(AddRecipientDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(endToEnd));
		Random random=new Random();
		long randomNumber = 100000000000L + (long)(random.nextDouble() * 900000000000L);
		String endToEndNo="abc"+randomNumber; 
		WD.findElement(endToEnd).sendKeys(endToEndNo);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(reltDocTyp));
		WD.findElements(reltDocTyp).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='add_remittance_info_doc_type-panel']//span[text()='"+objDetails.getRemitDocTyp()+"']")));
		WD.findElement(By.xpath("//div[@id='add_remittance_info_doc_type-panel']//span[text()='"+objDetails.getRemitDocTyp()+"']")).click(); 
		WD.findElement(RemitDoct).sendKeys(objDetails.getRemitDocmt());
		WD.findElement(RemitDate).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.btn-light.ng-star-inserted")));
		WD.findElement(By.cssSelector("div.btn-light.ng-star-inserted")).click();
		WD.findElement(RemitDiscAmt).sendKeys(objDetails.getRemitDiscoAmt());
		int remitDiscoAmt = Integer.parseInt(objDetails.getRemitDiscoAmt());
		int pmtAmt = Integer.parseInt(objDetails.getPmtAmt());
		int totalAmt = remitDiscoAmt + pmtAmt;
		WD.findElement(RemitInvcAmt).sendKeys(String.valueOf(totalAmt));
		WD.findElement(RemitID).sendKeys(objDetails.getRemitId());
		WD.findElement(RemitLocn).sendKeys(objDetails.getRemitLoc());
		WD.findElement(RemitLocnMethod).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='remit_location_method-panel']//span[text()='"+objDetails.getRemitLocMethd()+"']")));
		WD.findElement(By.xpath("//div[@id='remit_location_method-panel']//span[text()='"+objDetails.getRemitLocMethd()+"']")).click(); 
		WD.findElement(RemitMemo).sendKeys(objDetails.getRemiteMemo());
		WD.findElement(Notes).sendKeys(objDetails.getRemiteMemo());
	}

	public static void enterFWireRemittanceDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		if(objDetails.getFwRemitRefToRecip().equalsIgnoreCase("Randomlong")) {
			Random random=new Random();
			long randomNumber = 100000000000L + (long)(random.nextDouble() * 900000000000L);
			String fwRefToRecipent="jkloa"+randomNumber; 
			WD.findElement(fwRemittRefToRecip).sendKeys(fwRefToRecipent);    }
		else {
			WD.findElement(fwRemittRefToRecip).sendKeys(objDetails.getFwRemitRefToRecip()); 	
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwRemittSenderToRecipInfo));
// 	As per the new update--->"Sender to Recipient Information cannot be used when structured or external remittance is present."
		if(!objDetails.getFwAdditionalRemitInfo().equals("Structured Remittance") && !objDetails.getFwAdditionalRemitInfo().equals("External Remittance")) {
			WD.findElement(fwRemittSenderToRecipInfo).sendKeys("Information1");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwRemittInfoForRecFIDropdwn));
		while(WD.findElement(fwRemittInfoForRecFIDropdwn).getDomAttribute("aria-expanded").equals("false"))
		{
			WD.findElement(fwRemittInfoForRecFIDropdwn).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_info_type-panel']//span[text()='"+objDetails.getFwInfoForRecipFIDrpDwn()+"']"))).click();
		WD.findElement(fwRemittInfoForRecFI).sendKeys(objDetails.getFwInfoForRecipFIDrpDwn());
		if(objDetails.getFwAdditionalRemitInfo().equals("Structured Remittance")) {
			WD.findElement(fwRemittAddRemitInfo).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getFwAdditionalRemitInfo()+"']"))).click();
			enterStrucOrginiatorRemittance(objDetails);
			enterStrucBeneficiaryRemittance(objDetails);
			enterStrucDocmRemittance(objDetails);
		}
		else if(objDetails.getFwAdditionalRemitInfo().equals("External Remittance")) {
			WD.findElement(fwRemittAddRemitInfo).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getFwAdditionalRemitInfo()+"']"))).click();
			enterExternalRemittance(objDetails);
		}

		try {
			wait = new WebDriverWait(WD, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmail));
			WebElement clearEmail=WD.findElement(confirmEmail);
			((JavascriptExecutor) WD).executeScript("arguments[0].value='';", clearEmail);
			WD.findElement(confirmEmail).sendKeys("abcedfgh12@gmail.com");	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void senderToRecipientAllFields() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		while (true) {
			try {
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@formarrayname='senderToRecipientInfo']//i[@class='fa fa-plus']")));
				element.click();
			} catch (TimeoutException e) {
				break; // Exit loop if the element is no longer visible
			}
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fwRemittSenderToRecipInfo));
		WD.findElements(fwRemittSenderToRecipInfo).get(0).sendKeys("Information1");
		WD.findElements(fwRemittSenderToRecipInfo).get(1).sendKeys("Information2");
		WD.findElements(fwRemittSenderToRecipInfo).get(2).sendKeys("Information3");
		WD.findElements(fwRemittSenderToRecipInfo).get(3).sendKeys("Information4");
	}


	// Remittance Originator:
	public static void enterStrucOrginiatorRemittance(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(	ExpectedConditions.visibilityOfElementLocated(fwStruOrgIdType));
		if(!objDetails.getFWStructRemitOrgIdType().equalsIgnoreCase("")) {
			WD.findElement(fwStruOrgIdType).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_originator_id_type-panel']//span[text()='"+objDetails.getFWStructRemitOrgIdType()+"']")));
			WD.findElement(By.xpath("//div[@id='fw_originator_id_type-panel']//span[text()='"+objDetails.getFWStructRemitOrgIdType()+"']")).click();}
		if(!objDetails.getFWStructRemitOrgIdCode().equalsIgnoreCase("")) {
			WD.findElements(fwStruOrgIdCode).get(0).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getFWStructRemitOrgIdCode()+"']")));
			WD.findElements(By.xpath("//span[text()='"+objDetails.getFWStructRemitOrgIdCode()+"']")).get(0).click(); }
		wait.until(	ExpectedConditions.visibilityOfElementLocated(fwStruOrgName));
		WD.findElement(fwStruOrgName).sendKeys(objDetails.getFWStructRemitOrgName());
		WD.findElement(fwStruOrgIdNum).sendKeys(objDetails.getFWStructRemitOrgIdNum());
//Remove this code....as per the recent update Address_Type Field is no longer available		
//		wait.until(	ExpectedConditions.visibilityOfElementLocated(fwStruOrgAddrsType));
//		while(WD.findElement(fwStruOrgAddrsType).getDomAttribute("aria-expanded").equals("false"))
//		{
//			WD.findElement(fwStruOrgAddrsType).click();
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgAddrsTyp()+"']"))).click();
		WD.findElement(fwStruOrgAddrsLine1).sendKeys(objDetails.getFWStructRemitOrgAddrsLin1());
		WD.findElement(fwStruOrgAddrsLine2).sendKeys(objDetails.getFWStructRemitOrgAddrsLin2());
		WD.findElement(fwStruOrgCity).sendKeys(objDetails.getfwStructRemitOrgCity());
		WD.findElement(fwStruOrgState).sendKeys(objDetails.getFWStructRemitOrgState());
		WD.findElement(fwStruOrgPostalCode).sendKeys(objDetails.getFWStructRemitOrgPostal());
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwStruOrgCountry));
		while(WD.findElement(fwStruOrgCountry).getDomAttribute("aria-expanded").equals("false"))
		{
			WD.findElement(fwStruOrgCountry).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgCountry()+"']")));
		WD.findElement(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgCountry()+"']")).click(); 
		WD.findElement(fwStruOrgPhn).sendKeys(objDetails.getFWStructRemitOrgPhn());
		WD.findElement(fwStruOrgEmail).sendKeys(objDetails.getFWStructRemitOrgEmail());
	} 

	//  Remittance Beneficiary:
	public static void enterStrucBeneficiaryRemittance(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwStruBenefIdType));
		if(!objDetails.getFWStructRemitOrgIdType().equalsIgnoreCase("")) {
			while(WD.findElement(fwStruBenefIdType).getDomAttribute("aria-expanded").equals("false"))
			{
				WD.findElement(fwStruBenefIdType).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fw_beneficiary_id_type-panel']//span[text()='"+objDetails.getFWStructRemitOrgIdType()+"']")));
			WD.findElement(By.xpath("//div[@id='fw_beneficiary_id_type-panel']//span[text()='"+objDetails.getFWStructRemitOrgIdType()+"']")).click();}
		if(!objDetails.getFWStructRemitOrgIdCode().equalsIgnoreCase("")) {
			WD.findElements(fwStruOrgIdCode).get(1).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getFWStructRemitOrgIdCode()+"']")));
			WD.findElements(By.xpath("//span[text()='"+objDetails.getFWStructRemitOrgIdCode()+"']")).get(1).click();
			WD.findElement(fwStruBenefIdNum).sendKeys(objDetails.getFWStructRemitOrgIdNum());}
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwStruBenefName));
		WD.findElement(fwStruBenefName).sendKeys(objDetails.getFWStructRemitOrgName());
//Remove this code....as per the recent update Address_Type Field is no longer available		
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fwStruOrgAddrsType));
//		while(WD.findElements(fwStruOrgAddrsType).get(1).getDomAttribute("aria-expanded").equals("false"))
//		{
//			WD.findElements(fwStruOrgAddrsType).get(1).click();
//		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgAddrsTyp()+"']"))).click();
		WD.findElement(fwStruBenefAddrsLine1).sendKeys(objDetails.getFWStructRemitOrgAddrsLin1());
		WD.findElement(fwStruBenefAddrsLine2).sendKeys(objDetails.getFWStructRemitOrgAddrsLin2());
		WD.findElement(fwStruBenefCity).sendKeys(objDetails.getfwStructRemitOrgCity());
		WD.findElement(fwStruBenefState).sendKeys(objDetails.getFWStructRemitOrgState());
		WD.findElement(fwStruBenefPostalCode).sendKeys(objDetails.getFWStructRemitOrgPostal());
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fwStruOrgCountry));
		while(WD.findElements(fwStruOrgCountry).get(1).getDomAttribute("aria-expanded").equals("false"))
		{
			WD.findElements(fwStruOrgCountry).get(1).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgCountry()+"']")));
		WD.findElement(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitOrgCountry()+"']")).click(); 
	}

	//  Primary Remittance Document :
	public static void enterStrucDocmRemittance(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(reltDocTyp));
		WD.findElements(reltDocTyp).get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitDocType()+"']")));
		WD.findElement(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWStructRemitDocType()+"']")).click(); 
		WD.findElement(fwStruDocumIdNum).sendKeys(objDetails.getFWStructRemitDocIdNum());
		WD.findElement(fwStruDocuDate).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.btn-light.ng-star-inserted")));
		WD.findElement(By.cssSelector("div.btn-light.ng-star-inserted")).click();
		WD.findElement(fwStruAmtPaid).sendKeys(objDetails.getFWStructRemitDocAmtPaid());
		WD.findElement(fwStruOrgnlAmtPaid).sendKeys(objDetails.getFWStructRemitDocOrgnlAmt());
		WD.findElement(fwStruDiscAmtPaid).sendKeys(objDetails.getFWStructRemitDocDiscAmt());
		WD.findElement(fwStruAdjAmtPaid).sendKeys(objDetails.getFWStructRemitDocAdjstAmt());
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwStructAdjustReason));
		WD.findElement(fwStructAdjustReason).sendKeys(objDetails.getFwStructRemitAdjustReason());
		WD.findElement(fwAdjustIndic).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFwStructRemitAdjstIndicat()+"']")));
		WD.findElement(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFwStructRemitAdjstIndicat()+"']")).click(); 
	}

	// External_Remittance
	public static void enterExternalRemittance(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(fwExtRemitId));
		WD.findElement(fwExtRemitId).sendKeys(objDetails.getFWExtrnlRemitId());
		WD.findElement(fwExtRemitLocMethd).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWExtrnlRemitLocMetd()+"']")));
		WD.findElement(By.xpath("//div[contains(@id,'-panel')]//span[text()='"+objDetails.getFWExtrnlRemitLocMetd()+"']")).click();
		WD.findElement(fwExtRemitLoc).sendKeys(objDetails.getFWExtrnlRemitLoc());
	}


	public static void submitApprovingRecipient(String PhoneNo) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.searchPendingPmt));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(10));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.closeBtn));
			WD.findElement(AddSenderDetails.closeBtn).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		WD.findElement(AddSenderDetails.searchPendingPmt).sendKeys(PhoneNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(AddSenderDetails.searchBtn));
		WD.findElement(AddSenderDetails.searchBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.ApproveSubmit));
		WD.findElement(AddSenderDetails.ApproveSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.ApproveSubmit));
		WD.findElement(AddSenderDetails.ApproveSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
	}

	public static void submitDecliningRecipient(String PhoneNo) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.searchPendingPmt));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tfoot/tr")));
		WD.findElement(AddSenderDetails.searchPendingPmt).sendKeys(PhoneNo);
		WD.findElement(AddSenderDetails.searchBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.DeclineSubmit));
		WD.findElement(AddSenderDetails.DeclineSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.DeclineSubmit));
		WD.findElement(AddSenderDetails.DeclineSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
	}

	public static void submitDecliningRecipient2(String PhoneNo, String Expectedstatus) {
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(10));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.closeBtn));
			WD.findElement(AddSenderDetails.closeBtn).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.searchPendingPmt));
		WD.findElement(AddSenderDetails.searchPendingPmt).sendKeys(PhoneNo);
		WD.findElement(AddSenderDetails.searchBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.DeclineSubmit));
		WD.findElement(AddSenderDetails.DeclineSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.DeclineSubmit));
		WD.findElement(AddSenderDetails.DeclineSubmit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),Expectedstatus);
	}

	public static void releasingTheRiskRecipient(String expectedStatus) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Awaiting Risk Review");
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeDots));
		WD.findElement(threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(ReleaseLinkInthreeDots));
		WD.findElement(ReleaseLinkInthreeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NotesMemoTextBox));
		WD.findElement(NotesMemoTextBox).sendKeys("Memo:- Checking Releasing process");
		WD.findElement(ReleaseBtn_AtMemo).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//strong[text()='"+expectedStatus+"']")));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),expectedStatus);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void rejectingTheRiskRecipient(String expectedStatus) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Awaiting Risk Review");
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeDots));
		WD.findElement(threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(RejectLinkInthreeDots));
		WD.findElement(RejectLinkInthreeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NotesMemoTextBox));
		WD.findElement(NotesMemoTextBox).sendKeys("Memo:-Checking Rejecting process");
		WD.findElement(RejectBtn_AtMemo).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//strong[text()='"+expectedStatus+"']")));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),expectedStatus);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='actions']//span[@class='highlight']")));
	}

	public static void successApprovalMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Active");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='actions']//span[@class='highlight']")));
	}

	public static void totalAmountDisplayed() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong/span[@class='ng-star-inserted']/span")));
	}
	public static void currentBalanceDisplay_DepOp() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'float-left sender_details_info_wires ng-star-inserted')]//span[@class='ng-star-inserted']")));
	}

	public static void awaitingRiskReviewStatus() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Awaiting Risk Review");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}


	public static void deletingTheRecipient001(String expectedStatus) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Active");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecipientDetails.threeDots));
		WD.findElement(AddRecipientDetails.threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteAndEditLinkInthreeDots));
		WD.findElements(DeleteAndEditLinkInthreeDots).get(0).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NotesMemoTextBox));
		WD.findElement(NotesMemoTextBox).sendKeys("Memo:-Checking Deleting process");
		WD.findElement(DeleteBtn_AtMemo).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//strong[text()='"+expectedStatus+"']")));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),expectedStatus);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));

	}

	
	public static void travelRuleCheckBoxClick(String senderAddressCheckboxClick , String recipAddressCheckboxClick )   {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(3));
		if(senderAddressCheckboxClick.contentEquals("Yes")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(travelruleSenderAddressCheckBox));
				JavascriptExecutor js = (JavascriptExecutor) WD;
				js.executeScript("arguments[0].scrollIntoView();", WD.findElement(travelruleSenderAddressCheckBox));
				if(!WD.findElement(travelruleSenderAddressCheckBox).isSelected()){
				js.executeScript("arguments[0].click();",WD.findElement(travelruleSenderAddressCheckBox));}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if(recipAddressCheckboxClick.contentEquals("Yes")) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(travelruleRecipientAddressCheckBox));
				JavascriptExecutor js = (JavascriptExecutor) WD;
				js.executeScript("arguments[0].scrollIntoView();", WD.findElement(travelruleRecipientAddressCheckBox));
				if(!WD.findElement(travelruleRecipientAddressCheckBox).isSelected()){
	            js.executeScript("arguments[0].click();",WD.findElement(travelruleRecipientAddressCheckBox)); }
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}


	public static void continueBtnClick_With_TravelRuleMethod() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
		travelRuleCheckBoxClick("Yes", "Yes");
		int count = 0;
		try {
			while (count < 3 && WD.findElement(contuBtn).isDisplayed()) {
				WD.findElement(contuBtn).click();
				count++;
			}
		} catch (Exception e) {	
			
		}
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(recipAmt));
		}	
		catch (Exception e2) {
            js.executeScript("arguments[0].click();",WD.findElement(contuBtn)); 
		}
	}
	
	public static void continueBtnClick_With_No_travelRuleMethod() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
		int count = 0;
		try {
//			while (count < 3 && WD.findElement(contuBtn).isDisplayed()) {
			while (count < 3) {
				WD.findElement(contuBtn).click();
				count++;
			}
		} catch (Exception e) {	
			
		}
	}
	
	public static void clickContinue_For_XBwireCases() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
		travelRuleCheckBoxClick("Yes", "Yes");
		int count = 0;
		try {
			while (count < 3 && WD.findElement(contuBtn).isDisplayed()) {
				WD.findElement(contuBtn).click();
				count++;
			}
		} catch (Exception e) {	
			
		}
	}
	
	

	public static void continueClick() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
		WD.findElement(contuBtn).click();
	}

	public static void editClick_PendingApproval() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(editBtn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(editBtn));
		WD.findElement(editBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Change Sender Account']"))).click();
	}

	public static void editClick() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(editBtn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(editBtn));
		WD.findElement(editBtn).click();
	}
	
	public static String editFee() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(feeEdit));
		for (int i = 0; i < 6; i++) {
			WD.findElement(feeEdit).sendKeys(Keys.BACK_SPACE);
		}
		String fee="6.00";
		WD.findElement(feeEdit).sendKeys(fee);
		WD.findElement(feeEdit).sendKeys(Keys.TAB);
		return fee;
	}


	public static void submitBtn() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(Submit));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(Submit));
		js.executeScript("arguments[0].click();",WD.findElement(Submit));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Recipient has been created and is in Awaiting Approval state.']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Awaiting Approval");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='actions']//span[@class='highlight']")));
	}

	public static void submitBtn2()throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(Submit));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(Submit));
		Thread.sleep(2000);
		WD.findElement(Submit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void submitBtn3(String expectedStatus)throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(Submit));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(Submit));
		Thread.sleep(2000);
		WD.findElement(Submit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),expectedStatus);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void selectExistRecipient(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(activeBtn));
		WD.findElement(activeBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tbody")));
		WebElement table = WD.findElement(By.xpath("//div[@class='block_style_body p_0 tbl']//tbody"));
		List<WebElement> trElement = table.findElements(By.tagName("tr"));
		for (WebElement tr : trElement) {
			List<WebElement> tdElements = tr.findElements(By.tagName("td"));
			int flag=0;
			int count = 0;
			String name = "";
			WebElement recipientName=null;
			for (WebElement td : tdElements) {
				count++;
				if (count == 2) {
					name = td.getText();
					recipientName=td;
				}
				if (count == 7) {
					System.out.println(objDetails.getRecipientName()+" Name");
					if (name.equals(objDetails.getRecipientName())) {
						try {
							wait.until(ExpectedConditions.visibilityOf(recipientName.findElement(By.xpath("span/span/span"))));
							recipientName.findElement(By.xpath("span/span/span")).click();
							flag=1;
							break;
						}
						catch(TimeoutException e) {
							System.out.println("app-recipients-status element or span not found for " + name);
						}
					}
				}
			}
			if(flag==1)break;
		}
	}

	public static String getPhoneNo() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNoCopy));
		String phoneNum=WD.findElement(phoneNoCopy).getText();
		String updatedPhone = phoneNum.replaceAll("[^0-9]", "");
		updatedPhone = updatedPhone.substring(1);
		return updatedPhone;
	}


	public static void simpleSearch_FI(String PhoneNo) {
		try {
			WebDriverWait wait2 = new WebDriverWait(WD, Duration.ofSeconds(10));
			wait2.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.closeBtn));
			WD.findElement(AddSenderDetails.closeBtn).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.searchPendingPmt));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@class='mdc-data-table__content']/tr/td")));
		WD.findElement(AddSenderDetails.searchPendingPmt).sendKeys(PhoneNo);
		WD.findElement(AddSenderDetails.searchBtn).click();
	}

	public static void RecipientAdvanceSearch_DepOp(AddSenderDetails objSenderDtls, String senderNam) throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumTextBoxForRecipiSearch));
		WD.findElement(accountNumTextBoxForRecipiSearch).clear();
		WD.findElement(accountNumTextBoxForRecipiSearch).sendKeys(Keys.CLEAR,objSenderDtls.getSenderAccNumber(), Keys.TAB);
		WD.findElement(statusDropDown).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='rcp_status-panel']//span[text()='Active']")));
		WD.findElement(By.xpath("//div[@id='rcp_status-panel']//span[text()='ALL']")).click();
		WD.findElement(By.xpath("//div[@id='rcp_status-panel']//span[text()='ALL']")).click();
		Thread.sleep(2000);
		WD.findElement(By.xpath("//div[@id='rcp_status-panel']//span[text()='Active']")).click();
		WD.findElement(By.xpath("//div[@id='rcp_status-panel']")).sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[text()=' Address:']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='advanced-search']//label[text()=' Address:']")));
		WD.findElement(DivisionDropDown).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_recipient_division-panel']//span[text()='"+objSenderDtls.getDivision()+"']")));
		WD.findElement(By.xpath("//div[@id='create_recipient_division-panel']//span[text()='"+objSenderDtls.getDivision()+"']")).click();
		WD.findElement(senderNameDropDown).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sender-name-panel']//span[text()='"+senderNam+"']")));
		WD.findElement(By.xpath("//div[@id='sender-name-panel']//span[text()='"+senderNam+"']")).click();
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row sender_details_info_wires ng-star-inserted']//label[@class='btn-block']")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[text()=' Address:']")));
		WD.findElement(DepOpRecipAdvanceSearchBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'mat-mdc-table')]")));
	}


	public static void selectExistRecipient_DepOp(AddRecipientDetails objRecipDetails) throws IOException, Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'mat-mdc-table')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[contains(@class,'mat-mdc-table')]//*[text()='Active']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block_style_body p_0 tbl']//tbody")));
		////		Expanding the Recipient table list from 10 to 100
		//		while(WD.findElement(RecipientListDropDown).getDomProperty("aria-expanded").equals("false")) {
		//			WD.findElement(RecipientListDropDown).click();
		//			if(WD.findElement(RecipientListDropDown).getDomProperty("aria-expanded").equals("true"))break;
		//		}
		//		WD.findElement(By.xpath("//*[@id='dropdownMenu1']//a[text()=' 100 ']"));


		WebElement table = WD.findElement(By.xpath("//div[@class='block_style_body p_0 tbl']//tbody"));
		//		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'block_style_body p_')]//tbody")));
		//		        WebElement table = WD.findElement(By.xpath("//div[contains(@class, 'block_style_body p_')]//tbody"));

		List<WebElement> trElement = table.findElements(By.tagName("tr"));
		for (WebElement tr : trElement) {
			List<WebElement> tdElements = tr.findElements(By.tagName("td"));
			int flag=0;
			int count = 0;
			String name = "";
			WebElement recipientName=null;
			for (WebElement td : tdElements) {
				count++;
				if (count == 1) {
					name = td.getText();
					recipientName=td;
				}
				if (count == 7) {
					System.out.println("Expected_Name:- "+objRecipDetails.getRecipientName()+ "  &  Actual_Name:- "+name);
					if (name.equals(objRecipDetails.getRecipientName())) {
						try {
							wait.until(ExpectedConditions.visibilityOf(recipientName.findElement(By.xpath("span/span/span"))));
							JavascriptExecutor js = (JavascriptExecutor) WD;
							js.executeScript("arguments[0].scrollIntoView();", recipientName.findElement(By.xpath("span/span/span")));
							Thread.sleep(2000);
							recipientName.findElement(By.xpath("span/span/span")).click();
							flag=1;
							break;
						}
						catch(TimeoutException e) {
							System.out.println("app-recipients-status element or span not found for " + name);
						}
					}
				}
			}
			if(flag==1)break;
		}
	}



	public static void suspendRecipient() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeDots));
		WD.findElement(threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(suspendBtn));
		WD.findElement(suspendBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(SuspendBtn));
		WD.findElement(SuspendBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//img")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
	}

	public static void revokeRecipient() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeDots));
		WD.findElement(threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(revokeBtn));
		WD.findElement(revokeBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(RevokedBtn));
		WD.findElement(RevokedBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//img")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
	}

	public static void deleteRecipient() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(threeDots));
		WD.findElement(threeDots).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteBtn));
		WD.findElement(deleteBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.memo));
		WD.findElement(AddSenderDetails.memo).sendKeys("memo");
		wait.until(ExpectedConditions.visibilityOfElementLocated(DeleteBtn));
		WD.findElement(DeleteBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='detail_item']//img")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully submitted the request']")));
	}

	public static void SuspendSuccessMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Suspended");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void RevokeSuccessMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Revoked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void approvingDelete() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Deleted");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void declineSuspend() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Active");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void declineRevoke() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Active");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}

	public static void declineDelete() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Active");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//label[text()='Transfer Type:']")));
	}
	public static void recipDetails(AddRecipientDetails objDetails) throws Exception{
		WebDriverWait wait =new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipType));
		if(!objDetails.getRecipientType().equalsIgnoreCase("")) {
			WD.findElement(addRecipType).click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")));
			WD.findElement(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")).click();	 }
		WD.findElement(recipNickName).clear();
		WD.findElement(recipNickName).sendKeys(objDetails.getRecipNickNam());
		WD.findElement(recipName).clear();
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
		WD.findElement(recipPhone).clear();
		WD.findElement(recipPhone).sendKeys(objDetails.getRecipientPhone());
		WD.findElement(recipEmail).clear();
		WD.findElement(recipEmail).sendKeys(objDetails.getRecipientEmail());
	}

	public static void recipDetails2(AddRecipientDetails objDetails) throws Exception{
		WebDriverWait wait =new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipType));
		while(WD.findElement(addRecipType).getDomAttribute("aria-expanded").equals("false")) {
			WD.findElement(addRecipType).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']"))).click();
		WD.findElement(addRecipType).sendKeys(Keys.TAB);
		WD.findElement(addRecipType).sendKeys(Keys.TAB);
		WD.findElement(recipNickName).sendKeys(objDetails.getRecipNickNam());
		WD.findElement(recipName).sendKeys(objDetails.getRecipientName());
		WD.findElement(recipPhone).sendKeys(objDetails.getRecipientPhone());
		WD.findElement(recipEmail).sendKeys(objDetails.getRecipientEmail());
	}

	public static void recipBankDetails(AddRecipientDetails objDetails) throws Exception{
		WebDriverWait wait =new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipRoutingNo));
		WD.findElement(recipRoutingNo).sendKeys(objDetails.getRecipientRoutNum());
		WD.findElement(recipAccountNo).sendKeys(objDetails.getRecipientAccNum());
	}

	public static void fwBIScodeIBAN(AddRecipientDetails objDetails){
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(intermediaryBank));
		WD.findElement(idType).click();
		WD.findElement(By.xpath("//div[@id='fw_id_types-panel']//span[text()='"+objDetails.getIdTyp()+"']")).click();
		WD.findElement(idNum).sendKeys(objDetails.getIdNumm());
		WD.findElement(intermediaryBank).sendKeys(objDetails.getRecipientRoutNum());
		WD.findElement(bicCode).sendKeys(objDetails.getBicCodee());
		WD.findElement(iBAN).sendKeys(objDetails.getiBan());
	}

	public static void endToEnd(AddRecipientDetails objDetails) throws Exception{
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipEmail));
		WD.findElement(endToEnd).sendKeys(objDetails.getEndToend());
		WD.findElement(endToEnd).sendKeys(Keys.SPACE);
	}

	public static void selectCountryDetails(AddRecipientDetails objDetails) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectCountry));
		WD.findElement(selectCountry).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterCountryName));
		WD.findElement(enterCountryName).clear();
		WD.findElement(enterCountryName).sendKeys(objDetails.getCountry());
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'"+objDetails.getCountry()+"')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(clickCountry));
		if(objDetails.getCountry().equals("India")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@id='[object Object]']/span)[2]"))).click();
        }else {
            WD.findElement(clickCountry).click();
        }		wait.until(ExpectedConditions.visibilityOfElementLocated(clickCurrency));
		WD.findElement(clickCurrency).click();
		Thread.sleep(1000);
		if(!objDetails.getCurrency().equals("")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(objDetails.getCurrency())));
			WD.findElement(By.id(objDetails.getCurrency())).click();
		}else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='xbCurrency-panel']//span[text()='Select']"))).click();
		}
	}

	public static void enteBankAddressDetails(AddRecipientDetails objDetails) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recipAddress));
		WD.findElements(recipAddress).get(1).sendKeys(objDetails.getRecipientAddrs());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
		WD.findElement(By.className("pac-item")).click();
	}

	//** Common for All (XBRecipientDetailsMethod) **	
	public static void enterXBRecipientDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("app-capture-xb-payment-fields")));
		wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipName));
		WD.findElement(xbRecipName).sendKeys(objDetails.getRecipientName());
		wait.until(ExpectedConditions.presenceOfElementLocated(xbBankBICCode));
		WD.findElement(xbBankBICCode).sendKeys(objDetails.getBicCodee());
		wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipType));
		WD.findElement(xbRecipType).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientType()+"']")));
		WD.findElement(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientType()+"']")).click();
	}
	
	//** Common for All (XBRecipientDetailsMethod) **	
		public static void addXBRecipientDetails(AddRecipientDetails objDetails) throws IOException {
			WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("app-capture-xb-payment-fields")));
			wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipientName));
			WD.findElement(xbRecipientName).sendKeys(objDetails.getRecipientName());
			wait.until(ExpectedConditions.presenceOfElementLocated(xbBankBICCode));
			WD.findElement(xbBankBICCode).sendKeys(objDetails.getBicCodee());
			wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipType));
			WD.findElement(xbRecipType).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientType()+"']")));
			WD.findElement(By.xpath("//mat-option//span[text()='"+objDetails.getRecipientType()+"']")).click();
		}

	public static void enterCommonMethodDetails1(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankAccount));
		if(!objDetails.getCurrency().equals("USD")) {
			WD.findElement(xbPurposeOfPay).sendKeys(objDetails.getPurposeOfPayment());
		}
		WD.findElement(xbBankAccount).sendKeys(objDetails.getRecipientAccNum());
	}

	public static void enterArgentinaDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankAccount));
		WD.findElement(xbBankAccount).sendKeys(objDetails.getRecipientAccNum());
		if(!objDetails.getCurrency().equals("USD")){
			WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getRecipientRoutNum());
			WD.findElement(xbContactName).sendKeys(objDetails.getContactName());
			WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());
		}
	}


	public static void enterPhoneNoDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbPhoneNumber));
		WD.findElement(xbPhoneNumber).sendKeys(objDetails.getRecipientPhone());
	}

	public static void enterBangladeshDetails(AddRecipientDetails objDetails) throws IOException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xbNationality));
        WD.findElement(xbReciAuthType).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TAX ID"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xbRecipientTaxId));
        WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());
        while(WD.findElement(By.id("destination_nationality")).getDomAttribute("aria-expanded").equals("false")) {
            WD.findElement(By.id("destination_nationality")).click();
        }
        WD.findElement(By.xpath("//div[@id='destination_nationality-panel']//input")).sendKeys(objDetails.getNationality());
        if(objDetails.getNationality().equals("Select")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='destination_nationality-panel']//mat-option[1]"))).click();
        }
        else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='destination_nationality-panel']//mat-option[2]"))).click();
        }
        enterPhoneNoDetails(objDetails);
    }


	public static void enterIndiaDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		if(!objDetails.getCurrency().equals("USD")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(xbPurposeOfPayCode));
			WD.findElement(xbPurposeOfPayCode).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='[object Object]']/span)[2]")));
			WD.findElement(By.xpath("(//*[@id='[object Object]']/span)[2]")).click();
			WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getIFSCCode());
			WD.findElement(xbNationality).sendKeys(objDetails.getNationality());
		}
	}


	public static void enterColombiaDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbRecipientTaxId));
		WD.findElement(xbContactName).sendKeys(objDetails.getRecipientName());
		WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());
	}

	public static void enterPurposeofPmtCode() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbPurposeOfPayCode));
		WD.findElement(xbPurposeOfPayCode).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='[object Object]']/span)[2]")));
		WD.findElement(By.xpath("(//*[@id='[object Object]']/span)[2]")).click();
	}

	public static void enterCommonMethodDetails3(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		enterIBANDetails(objDetails);
		if(!objDetails.getCurrency().equals("USD")){
			wait.until(ExpectedConditions.visibilityOfElementLocated(xbPurposeOfPay));
			WD.findElement(xbPurposeOfPay).sendKeys(objDetails.getPurposeOfPayment());}
	}


	public static void enterIBANDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbIBAN));
		WD.findElement(xbIBAN).sendKeys(objDetails.getiBan());
	}

	public static void enterBankRoutingCode(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankRoutingorIFSC));
		WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getRecipientRoutNum());
	}

	public static void enterBankBranchCode(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankRoutingorIFSC));
		WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getBankBranchCode());
	}

	public static void enterIFSCCode(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankRoutingorIFSC));
		WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getIFSCCode());
	}

	public static void enterBankAccountNo(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankAccount));
		WD.findElement(xbBankAccount).sendKeys(objDetails.getRecipientAccNum());
	}

	public static void enterUKDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipName));
		WD.findElement(xbRecipName).sendKeys(objDetails.getRecipientName());
		wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipType));
		WD.findElement(xbRecipType).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")));
		WD.findElement(By.xpath("//span[text()='"+objDetails.getRecipientType()+"']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbMethods));
		WD.findElement(xbMethods).click();
		if(objDetails.getCurrency().equals("USD"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BBAN")));
			WD.findElement(By.id("BBAN")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankBICCode));
			WD.findElement(xbBankBICCode).sendKeys(objDetails.getBicCodee());
			WD.findElement(xbIBAN).sendKeys(objDetails.getiBan());
		}
		if(!objDetails.getBicCodee().equals("")&&!objDetails.getCurrency().equals("USD")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Swift/BIC and IBAN")));
			WD.findElement(By.id("Swift/BIC and IBAN")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(xbBankBICCode));
			WD.findElement(xbBankBICCode).sendKeys(objDetails.getBicCodee());
			WD.findElement(xbIBAN).sendKeys(objDetails.getiBan());
		}
		else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='destination_account_methods-panel']//span[text()='Select']"))).click();
		}
		WD.findElement(xbBankRoutingorIFSC).sendKeys(objDetails.getBankBranchCode());
	}

	public static void enterChileDetails(AddRecipientDetails objDetails) throws IOException {
		if(!objDetails.getCurrency().equals("USD")) {
			enterPhoneNoDetails(objDetails);
			enterBankAccountNo(objDetails);
			WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());}
		else {
			enterBankAccountNo(objDetails);
		}
	}

	public static void enterRecTaxIdDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(xbRecipientTaxId));
		WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());
	}

	public static void enterPakistanDetails(AddRecipientDetails objDetails) throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(xbReciAuthType));
		enterCommonMethodDetails3(objDetails);
		enterPhoneNoDetails(objDetails);
		WD.findElement(xbReciAuthType).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CNIC"))).click();
		enterRecTaxIdDetails(objDetails);
	}


	public static void emptyCountryMsg() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectCountry));
		WD.findElement(selectCountry).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mdc-list-item__primary-text' and text()='Select']"))).click();
	}

	public static void enterBangladeshPassPort(AddRecipientDetails objDetails) throws IOException {
        WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xbNationality));
        WD.findElement(xbReciAuthType).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passport Number"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(xbRecipientTaxId));
        WD.findElement(xbRecipientTaxId).sendKeys(objDetails.getRecipientTax());
        while(WD.findElement(By.id("destination_nationality")).getDomAttribute("aria-expanded").equals("false")) {
            WD.findElement(By.id("destination_nationality")).click();
        }
        WD.findElement(By.xpath("//div[@id='destination_nationality-panel']//input")).sendKeys(objDetails.getNationality());
        if(objDetails.getNationality().equals("Select")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='destination_nationality-panel']//mat-option[1]"))).click();
        }
        else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='destination_nationality-panel']//mat-option[2]"))).click();
        }    enterPhoneNoDetails(objDetails);
    }
	public static void withoutPurposeofPmtCode() throws IOException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xbPurposeOfPayCode));
		WD.findElement(xbPurposeOfPayCode).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='paymentCategory_code-panel']//span[text()='Select']"))).click();
	}

	public static void emptyAddress() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recipAddress));
		WD.findElements(recipAddress).get(1).sendKeys(Keys.TAB);
	}

	public static void enterUniqueReference() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(3));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(UniqueReference));
			WD.findElement(UniqueReference).sendKeys("d8bb32d9-1acc-4555-a2ee-f721125c3647");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Unique Reference field didn't displayed");
		}
	}

	public static void addRecipientDuplicateMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddRecip_DuplicateMSG));
		Assert.assertEquals(true, WD.findElement(AddRecip_DuplicateMSG).getText().contains("The Account number you entered matches with the existing recipient"));
	}

	public static void editBtnClick()throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Edit_Btn));
		JavascriptExecutor js = (JavascriptExecutor) WD;
		js.executeScript("arguments[0].scrollIntoView();", WD.findElement(Edit_Btn));
		Thread.sleep(2000);
		WD.findElement(Edit_Btn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipName));
	}


	public static void AssertRejectMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		WD.findElement(recipAmt).sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateErrorMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateRejectErrorMsg));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(contuBtn));
	}

	public static void AssertStatus(List<String> list) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationLink)).click();;
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddSenderDetails.paymentStatus));
		Assert.assertEquals(WD.findElement(AddSenderDetails.paymentStatus).getText(),"Processed");
		Assert.assertEquals(WD.findElement(duplicateConfirmationNo).getText(),list.get(0));
		Assert.assertEquals(WD.findElement(duplicatePaymentMethod).getText(),list.get(1));
		Assert.assertEquals(WD.findElement(duplicateScheduleDate).getText(),list.get(2));
	}


	public static List<String> storeFields() {
		List<String> list=new ArrayList<String>();
		String confirmationNo=WD.findElement(duplicateConfirmationNo).getText();
		String paymentMethod=WD.findElement(duplicatePaymentMethod).getText();
		String scheduledDate=WD.findElement(duplicateScheduleDate).getText();
		list.add(confirmationNo);
		list.add(paymentMethod);
		list.add(scheduledDate);
		return list;

	}

	public static void checkSubmitBtnVisible() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Submit));
	}

	public static void AssertAmount(AddRecipientDetails objDetails) {
		String amountText = WD.findElement(duplicateAmountDetails).getText();
		Assert.assertTrue(amountText.contains(objDetails.getPmtAmt()));
	}

	public static void AssertReceiverDetails(AddRecipientDetails objDetails) {
		String receiverDetails = WD.findElement(duplicateReceiverDetails).getText();
		String[] receiverSplit = receiverDetails.split(",");	
		String receiverName = receiverSplit[0].trim();
		String receiverAccNo = receiverSplit[1].trim();
		Assert.assertEquals(receiverName,objDetails.getRecipientName());
		String expectedLastFourDigits = objDetails.getRecipientAccNum().substring(objDetails.getRecipientAccNum().length() - 4);
		Assert.assertEquals(receiverAccNo,expectedLastFourDigits);
	}


	public static void AssertWarningMsg() {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		WD.findElement(recipAmt).sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateErrorMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateWarningErrorMsg));
		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDuplicateCheckbox));
		WD.findElement(confirmDuplicateCheckbox).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(contuBtn));
	}
	public static String normalizeAndSortAddress(String address) {
		return Arrays.stream(address.toLowerCase()
				.replaceAll("\\bst\\b", "street")
				.replaceAll("\\brd\\b", "road")
				.replaceAll("\\bave\\b", "avenue")
				.replaceAll("usa", "us")
				.replaceAll("[,]", "")     // remove commas
				.split("\\s+"))            // split by space
				.sorted()
				.collect(Collectors.joining(" "));
	}

	public static void assertNewReceiverDetails(AddRecipientDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		String receiverDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay To:']]//strong[@style='word-break: break-word;']")).getText();
		String[] receiverSplit = receiverDetails.split(",");	
		String receiverName = receiverSplit[0].trim();
		String receiverAccNo = receiverSplit[1].trim();
		Assert.assertEquals(receiverName,objDetails.getRecipientName());


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")));
		String recipientAddress = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")).getText();

		String recipientEmail = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Email:']]//strong")).getText();

		String recipientPhone = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Phone:']]//strong")).getText();


		String address1=normalizeAndSortAddress(recipientAddress);
		String address2=normalizeAndSortAddress(objDetails.getRecipientAddrs());

		Assert.assertEquals(address1,address2);
		Assert.assertEquals(recipientEmail,objDetails.getRecipientEmail());

		if(!objDetails.getRecipientType().equals("FI")) {
			Assert.assertTrue(receiverAccNo.contains(objDetails.getRecipientAccNum()));
			String digitsOnly = recipientPhone.replaceAll("\\D", ""); // removes all non-digits
			String formatted = digitsOnly.substring(1); // skip the leading '1'
			Assert.assertEquals(formatted,objDetails.getRecipientPhone());
		}
	}


	public static void assertExistReceiverDetails(AddRecipientDetails objDetails,List<Object> list) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		String receiverDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Pay To:']]//strong[@style='word-break: break-word;']")).getText().trim();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")));
		String recipientAddress = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")).getText();

		String recipientEmail = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Email:']]//strong")).getText();

		String recipientPhone = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Phone:']]//strong")).getText();

		String recipientType = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Type:']]//strong")).getText();

		String address1=normalizeAndSortAddress(recipientAddress);
		String address2=normalizeAndSortAddress(list.get(1).toString());

		Assert.assertEquals(address1,address2);
		Assert.assertEquals(recipientType,list.get(0));
		Assert.assertEquals(recipientEmail,list.get(2));
		if(!objDetails.getRecipientType().equals("FI")) {
			String digitsOnly = recipientPhone.replaceAll("\\D", ""); // removes all non-digits
			String formatted = digitsOnly.substring(1); // skip the leading '1'
			String onlyDigits = list.get(3).toString().replaceAll("\\D", ""); // removes all non-digits
			String updatedPhone = onlyDigits.substring(1); // skip the leading '1'
			Assert.assertEquals(formatted,updatedPhone);
		}
		Assert.assertEquals(receiverDetails,list.get(4).toString().trim());
	}


	public static List<Object> getSelectedExistRecipDetails() {
		List<Object> list=new ArrayList<Object>();
		list.add(WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Recipient Type:']]//strong")).getText());
		list.add(WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")).getText());
		list.add(WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Recipient Email:']]//strong")).getText());
		list.add(WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Recipient Phone :']]//strong")).getText());
		list.add(WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Recipient:']]//strong")).getText());
		return list;
	}

	public static List<Object> getDateAndAmount() {
		WD.findElement(recipAmt).sendKeys(Keys.TAB);
		List<Object> list=new ArrayList<Object>();
		list.add(WD.findElement(recipAmt).getDomProperty("value"));
		list.add(WD.findElement(fwRecipPmtDate).getDomProperty("value"));
		return list;
	}

	public static String normalizeDate(String date) {
		return date.replaceAll("(?<=\\s)(\\d{1})(?=,)", "0$1"); // Pads single-digit day
	}

	public static void assertDateAndAmount(List<Object> list) {
		String amount = WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Amount:']]//strong")).getText();
		String paymentDate = WD.findElement(By.xpath("//div[contains(@class,'form-group')][.//label[text()='Scheduled Date:']]//strong")).getText();
		Assert.assertEquals(normalizeDate(paymentDate), normalizeDate(list.get(1).toString()));
		Assert.assertTrue(amount.contains(list.get(0).toString()));
	}

	public static void clickDownArrows() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'glance_arrows') and contains(@class, 'ng-star-inserted')]/a/i")));
		List<WebElement> elements=WD.findElements(By.xpath("//div[contains(@class, 'glance_arrows') and contains(@class, 'ng-star-inserted')]/a/i"));
		if(elements.get(0).getDomAttribute("class").equals("fa fa-chevron-down")){
			elements.get(0).click();}
		try {
			if(elements.get(1).getDomAttribute("class").equals("fa fa-chevron-down")) {
				elements.get(1).click();}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void assertTransferNewReceiverDetails(AddRecipientDetails objDetails) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(40));
		String receiverDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Recipient:']]//strong[@style='word-break: break-word;']")).getText();
		String[] receiverSplit = receiverDetails.split(",");	
		String receiverName = receiverSplit[0].trim();
		String receiverAccNo = receiverSplit[1].trim();
		Assert.assertEquals(receiverName,objDetails.getRecipientName());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")));
		String recipientAddress = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")).getText();
		String recipientEmail = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Email:']]//strong")).getText();
		String recipientPhone = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Phone:']]//strong")).getText();
		String address1=normalizeAndSortAddress(recipientAddress);
		String address2=normalizeAndSortAddress(objDetails.getRecipientAddrs());
		Assert.assertEquals(address1,address2);
		Assert.assertEquals(recipientEmail,objDetails.getRecipientEmail());
		Assert.assertTrue(receiverAccNo.contains(objDetails.getRecipientAccNum()));
		String digitsOnly = recipientPhone.replaceAll("\\D", ""); // removes all non-digits
		String formatted = digitsOnly.substring(1); // skip the leading '1'
		Assert.assertEquals(formatted,objDetails.getRecipientPhone());
	}


	public static void assertTransferExistReceiverDetails(AddRecipientDetails objDetails,List<Object> list) {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		String receiverDetails = WD.findElement(By.xpath("//div[label[normalize-space()='Recipient:']]//strong[@style='word-break: break-word;']")).getText().trim();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")));
		String recipientAddress = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Address:']]//strong")).getText();
		String recipientEmail = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Email:']]//strong")).getText();
		String recipientPhone = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Phone:']]//strong")).getText();
		String recipientType = WD.findElement(By.xpath("//app-payment-party-details[@elementid='paymentPartyRecipientDetails'] //div[contains(@class,'form-group')][.//label[text()='Recipient Type:']]//strong")).getText();
		String address1=normalizeAndSortAddress(recipientAddress);
		String address2=normalizeAndSortAddress(list.get(1).toString());

		Assert.assertEquals(address1,address2);
		Assert.assertEquals(recipientType,list.get(0));
		Assert.assertEquals(recipientEmail,list.get(2));
		String digitsOnly = recipientPhone.replaceAll("\\D", ""); // removes all non-digits
		String formatted = digitsOnly.substring(1); // skip the leading '1'
		String onlyDigits = list.get(3).toString().replaceAll("\\D", ""); // removes all non-digits
		String updatedPhone = onlyDigits.substring(1); // skip the leading '1'
		Assert.assertEquals(formatted,updatedPhone);
		Assert.assertEquals(receiverDetails,list.get(4).toString().trim());
	}
	
	public static void saveThisRecipientClick() throws Exception {
		WebDriverWait wait = new WebDriverWait(WD, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Save this Recipient")));
		WD.findElement(By.linkText("Save this Recipient")).click();
	}

}