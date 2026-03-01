package dataReader;

import java.io.IOException;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import pages.Login;
import pages.XBWireFieldValidationPage;
import pages.AddRecipientDetails;
import pages.AddSenderDetails;
import pages.BankRails;
import pages.FedfileValidationPage;

public class TestDataReader {

	public static Login loadLogin(String TCNo) throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery="Select * from Login where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		Login login = new Login();
		login.setLoginId(recordset.getField("loginId").toString());
		login.setPassword(recordset.getField("password").toString());
		return login ;
	}

	public static AddSenderDetails loadSenderDetails(String TCNo) throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery="Select * from SenderDtls where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		AddSenderDetails senderDtls=new AddSenderDetails();
		senderDtls.setClient_RoutingNum(recordset.getField("Client_RoutingNum").toString());
		senderDtls.setDivision(recordset.getField("Division").toString());
		senderDtls.setPaymentNetwork(recordset.getField("PaymentNetwork").toString());
		senderDtls.setRecipient(recordset.getField("Recipient").toString());
		senderDtls.setLedgerOrAccount(recordset.getField("Ledger/Account").toString());
		senderDtls.setExistRecipNm(recordset.getField("RecipientName").toString());
		senderDtls.setAccountType(recordset.getField("AccountType").toString());
		senderDtls.setSenderAccNumber(recordset.getField("SenderAccNo").toString());
		senderDtls.setNameOnAccount(recordset.getField("NameOnAccount").toString());
		senderDtls.setTransferType(recordset.getField("TransferType").toString());
		senderDtls.setChannelType(recordset.getField("channelType").toString());
		senderDtls.setFedFileDoc(recordset.getField("FedFileDoc").toString());
		senderDtls.setPaymentType(recordset.getField("PaymentType").toString());
//====> For Travel Rule Cases <<===========
		senderDtls.setSenderAddressLine(recordset.getField("SenderAddressLine").toString());
		senderDtls.setSenderTown(recordset.getField("SenderTown").toString());
		senderDtls.setSenderState(recordset.getField("SenderState").toString());
		senderDtls.setSenderCountry(recordset.getField("SenderCountry").toString());
		senderDtls.setSenderZipCode(recordset.getField("SenderZipCode").toString());

		return senderDtls;

	}


	public static AddRecipientDetails loadRecipientDetails(String TCNo) throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery="Select * from RecipientDtls where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		AddRecipientDetails recipentDtls=new AddRecipientDetails();
		recipentDtls.setPaymentType(recordset.getField("PaymentType").toString());
		recipentDtls.setRecipientType(recordset.getField("RecipType").toString());
		recipentDtls.setRecipientName(recordset.getField("RecipName").toString());
		recipentDtls.setRecipNickNam(recordset.getField("RecipNickName").toString());
		recipentDtls.setRecipientAddrs(recordset.getField("RecipAddrs").toString());
		recipentDtls.setRecipientAddrsLin(recordset.getField("RecipAddrsLin").toString());
		recipentDtls.setRecipientTownName(recordset.getField("RecipTown").toString());
		recipentDtls.setRecipientZip(recordset.getField("RecipZip").toString());
		recipentDtls.setRecipientState(recordset.getField("RecipState").toString());
		recipentDtls.setRecipientCountry(recordset.getField("RecipCountry").toString());
		recipentDtls.setRecipientEmail(recordset.getField("RecipEmail").toString());
		recipentDtls.setRecipientPhone(recordset.getField("RecipPhone").toString());
		recipentDtls.setRecipientRoutNum(recordset.getField("RecipRouting").toString());
		recipentDtls.setRecipientAccNum(recordset.getField("RecipAccNum").toString());
		recipentDtls.setRecipientAccType(recordset.getField("RecipAccTyp").toString());
		recipentDtls.setPmtAmt(recordset.getField("Amount").toString());
		recipentDtls.setEndToend(recordset.getField("EndToEnd").toString());
		recipentDtls.setiBan(recordset.getField("IBAN").toString());
		recipentDtls.setBicCodee(recordset.getField("BIC_Code").toString());
		recipentDtls.setIdTyp(recordset.getField("IdType").toString());
		recipentDtls.setIdNumm(recordset.getField("IdNum").toString());
		recipentDtls.setPmtDate(recordset.getField("PaymentDate").toString());
		recipentDtls.setRemitDocTyp(recordset.getField("RemitDocTyp").toString());
		recipentDtls.setRemitDocmt(recordset.getField("RemitDocmt").toString());
		recipentDtls.setRemitDocDate(recordset.getField("RemitDocDate").toString());
		recipentDtls.setRemitDiscoAmt(recordset.getField("RemitDiscoAmt").toString());
		recipentDtls.setRemitId(recordset.getField("RemitId").toString());
		recipentDtls.setRemitLocMethd(recordset.getField("RemitLocMethd").toString());
		recipentDtls.setRemitLoc(recordset.getField("RemitLoc").toString());
		recipentDtls.setRemiteMemo(recordset.getField("RemiteMemo").toString());
		recipentDtls.setFwRemitRefToRecip(recordset.getField("fwRemitRefToRecip").toString());
		recipentDtls.setFwInfoForRecipFIDrpDwn(recordset.getField("fwInfoForRecipFIDropDown").toString());
		recipentDtls.setFwAdditionalRemitInfo(recordset.getField("fwAdditionalRemitInfo").toString());
		recipentDtls.setFWStructRemitOrgIdType(recordset.getField("fwStructRemitOrgIdType").toString());
		recipentDtls.setFWStructRemitOrgIdCode(recordset.getField("fwStructRemitOrgIdCode").toString());
		recipentDtls.setFWStructRemitOrgName(recordset.getField("fwStructRemitOrgName").toString());
		recipentDtls.setFWStructRemitOrgIdNum(recordset.getField("fwStructRemitOrgIdNum").toString());
		recipentDtls.setFWStructRemitOrgAddrsTyp(recordset.getField("fwStructRemitOrgAddrsTyp").toString());
		recipentDtls.setFWStructRemitOrgAddrsLin1(recordset.getField("fwStructRemitOrgAddrsLin1").toString());
		recipentDtls.setFWStructRemitOrgAddrsLin2(recordset.getField("fwStructRemitOrgAddrsLin2").toString());
		recipentDtls.setFWStructRemitOrgCity(recordset.getField("fwStructRemitOrgCity").toString());
		recipentDtls.setFWStructRemitOrgState(recordset.getField("fwStructRemitOrgState").toString());
		recipentDtls.setFWStructRemitOrgPostal(recordset.getField("fwStructRemitOrgPostal").toString());
		recipentDtls.setFWStructRemitOrgCountry(recordset.getField("fwStructRemitOrgCountry").toString());
		recipentDtls.setfwStructRemitOrgPhn(recordset.getField("fwStructRemitOrgPhn").toString());
		recipentDtls.setFWStructRemitOrgEmail(recordset.getField("fwStructRemitOrgEmail").toString());
		recipentDtls.setFWStructRemitDocType(recordset.getField("fwStructRemitDocType").toString());
		recipentDtls.setFWStructRemitDocIdNum(recordset.getField("fwStructRemitDocIdNum").toString());
		recipentDtls.setFWStructRemitDocDate(recordset.getField("fwStructRemitDocDate").toString());
		recipentDtls.setFWStructRemitDocAmtPaid(recordset.getField("fwStructRemitDocAmtPaid").toString());
		recipentDtls.setFWStructRemitDocOrgnlAmt(recordset.getField("fwStructRemitDocOrgnlAmt").toString());
		recipentDtls.setFWStructRemitDocDiscAmt(recordset.getField("fwStructRemitDocDiscAmt").toString());
		recipentDtls.setFWStructRemitDocAdjstAmt(recordset.getField("fwStructRemitDocAdjstAmt").toString());
		recipentDtls.setFwStructRemitAdjustReason(recordset.getField("FwStructRemitAdjstReasCode").toString());
		recipentDtls.setFwStructRemitAdjstIndicat(recordset.getField("FwStructRemitAdjstIndicat").toString());
		recipentDtls.setFWExtrnlRemitId(recordset.getField("fwExtrnlRemitId").toString());
		recipentDtls.setFWExtrnlRemitLocMetd(recordset.getField("fwExtrnlRemitLocMetd").toString());
		recipentDtls.setFWExtrnlRemitLoc(recordset.getField("fwExtrnlRemitLoc").toString());
		recipentDtls.setDocType(recordset.getField("DocType").toString());
		recipentDtls.setDocPath(recordset.getField("DocPath").toString());
		return recipentDtls;
	}
	
	
	public static AddRecipientDetails loadRecipientDetails_02(String TCNo) throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery="Select * from RecipientDtls_02 where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		AddRecipientDetails recipentDtls=new AddRecipientDetails();
		recipentDtls.setPaymentType(recordset.getField("PaymentType").toString());
		recipentDtls.setRecipientType(recordset.getField("RecipType").toString());
		recipentDtls.setRecipientName(recordset.getField("RecipName").toString());
		recipentDtls.setRecipNickNam(recordset.getField("RecipNickName").toString());
		recipentDtls.setRecipientAddrs(recordset.getField("RecipAddrs").toString());
		recipentDtls.setRecipientAddrsLin(recordset.getField("RecipAddrsLin").toString());
		recipentDtls.setRecipientTownName(recordset.getField("RecipTown").toString());
		recipentDtls.setRecipientZip(recordset.getField("RecipZip").toString());
		recipentDtls.setRecipientState(recordset.getField("RecipState").toString());
		recipentDtls.setRecipientCountry(recordset.getField("RecipCountry").toString());
		recipentDtls.setRecipientEmail(recordset.getField("RecipEmail").toString());
		recipentDtls.setRecipientPhone(recordset.getField("RecipPhone").toString());
		recipentDtls.setRecipientRoutNum(recordset.getField("RecipRouting").toString());
		recipentDtls.setRecipientAccNum(recordset.getField("RecipAccNum").toString());
		recipentDtls.setRecipientAccType(recordset.getField("RecipAccTyp").toString());
		recipentDtls.setPmtAmt(recordset.getField("Amount").toString());
		recipentDtls.setEndToend(recordset.getField("EndToEnd").toString());
		recipentDtls.setiBan(recordset.getField("IBAN").toString());
		recipentDtls.setBicCodee(recordset.getField("BIC_Code").toString());
		recipentDtls.setIdTyp(recordset.getField("IdType").toString());
		recipentDtls.setIdNumm(recordset.getField("IdNum").toString());
		recipentDtls.setPmtDate(recordset.getField("PaymentDate").toString());
		recipentDtls.setRemitDocTyp(recordset.getField("RemitDocTyp").toString());
		recipentDtls.setRemitDocmt(recordset.getField("RemitDocmt").toString());
		recipentDtls.setRemitDocDate(recordset.getField("RemitDocDate").toString());
		recipentDtls.setRemitDiscoAmt(recordset.getField("RemitDiscoAmt").toString());
		recipentDtls.setRemitId(recordset.getField("RemitId").toString());
		recipentDtls.setRemitLocMethd(recordset.getField("RemitLocMethd").toString());
		recipentDtls.setRemitLoc(recordset.getField("RemitLoc").toString());
		recipentDtls.setRemiteMemo(recordset.getField("RemiteMemo").toString());
		recipentDtls.setFwRemitRefToRecip(recordset.getField("fwRemitRefToRecip").toString());
		recipentDtls.setFwInfoForRecipFIDrpDwn(recordset.getField("fwInfoForRecipFIDropDown").toString());
		recipentDtls.setFwAdditionalRemitInfo(recordset.getField("fwAdditionalRemitInfo").toString());
		recipentDtls.setFWStructRemitOrgIdType(recordset.getField("fwStructRemitOrgIdType").toString());
		recipentDtls.setFWStructRemitOrgIdCode(recordset.getField("fwStructRemitOrgIdCode").toString());
		recipentDtls.setFWStructRemitOrgName(recordset.getField("fwStructRemitOrgName").toString());
		recipentDtls.setFWStructRemitOrgIdNum(recordset.getField("fwStructRemitOrgIdNum").toString());
		recipentDtls.setFWStructRemitOrgAddrsTyp(recordset.getField("fwStructRemitOrgAddrsTyp").toString());
		recipentDtls.setFWStructRemitOrgAddrsLin1(recordset.getField("fwStructRemitOrgAddrsLin1").toString());
		recipentDtls.setFWStructRemitOrgAddrsLin2(recordset.getField("fwStructRemitOrgAddrsLin2").toString());
		recipentDtls.setFWStructRemitOrgCity(recordset.getField("fwStructRemitOrgCity").toString());
		recipentDtls.setFWStructRemitOrgState(recordset.getField("fwStructRemitOrgState").toString());
		recipentDtls.setFWStructRemitOrgPostal(recordset.getField("fwStructRemitOrgPostal").toString());
		recipentDtls.setFWStructRemitOrgCountry(recordset.getField("fwStructRemitOrgCountry").toString());
		recipentDtls.setfwStructRemitOrgPhn(recordset.getField("fwStructRemitOrgPhn").toString());
		recipentDtls.setFWStructRemitOrgEmail(recordset.getField("fwStructRemitOrgEmail").toString());
		recipentDtls.setFWStructRemitDocType(recordset.getField("fwStructRemitDocType").toString());
		recipentDtls.setFWStructRemitDocIdNum(recordset.getField("fwStructRemitDocIdNum").toString());
		recipentDtls.setFWStructRemitDocDate(recordset.getField("fwStructRemitDocDate").toString());
		recipentDtls.setFWStructRemitDocAmtPaid(recordset.getField("fwStructRemitDocAmtPaid").toString());
		recipentDtls.setFWStructRemitDocOrgnlAmt(recordset.getField("fwStructRemitDocOrgnlAmt").toString());
		recipentDtls.setFWStructRemitDocDiscAmt(recordset.getField("fwStructRemitDocDiscAmt").toString());
		recipentDtls.setFWStructRemitDocAdjstAmt(recordset.getField("fwStructRemitDocAdjstAmt").toString());
		recipentDtls.setFwStructRemitAdjustReason(recordset.getField("FwStructRemitAdjstReasCode").toString());
		recipentDtls.setFwStructRemitAdjstIndicat(recordset.getField("FwStructRemitAdjstIndicat").toString());
		recipentDtls.setFWExtrnlRemitId(recordset.getField("fwExtrnlRemitId").toString());
		recipentDtls.setFWExtrnlRemitLocMetd(recordset.getField("fwExtrnlRemitLocMetd").toString());
		recipentDtls.setFWExtrnlRemitLoc(recordset.getField("fwExtrnlRemitLoc").toString());
		recipentDtls.setDocType(recordset.getField("DocType").toString());
		recipentDtls.setDocPath(recordset.getField("DocPath").toString());
		return recipentDtls;
	}


	public static ErrorMessageData loadRiskCheckAssertMsg() throws IOException, FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery = "Select * from RiskCheckAssertMsgs";
		Recordset recordset = connection.executeQuery(strQuery);
		recordset.next();
		ErrorMessageData riskCheckAssertMsg = new ErrorMessageData();
		riskCheckAssertMsg.setMinPayoutPerTxnReason(recordset.getField("MinPayoutPerTxnReason").toString());
		riskCheckAssertMsg.setMaxPayoutPerTxnReason(recordset.getField("MaxPayoutPerTxnReason").toString());
		riskCheckAssertMsg.setMaxPayoutPerDayReason(recordset.getField("MaxPayoutPerDayReason").toString());
		riskCheckAssertMsg.setMaxPayoutPerWeekReason(recordset.getField("MaxPayoutPerWeekReason").toString());
		riskCheckAssertMsg.setMaxPayoutPerMonthReason(recordset.getField("MaxPayoutPerMonthReason").toString());
		riskCheckAssertMsg.setMaxPayoutWithIn24hrsReason(recordset.getField("MaxPayoutWithIn24hrsReason").toString());
		
		
		riskCheckAssertMsg.setMinPayoutPerTxnReasonCode(recordset.getField("MinPayoutPerTxnReasonCode").toString());
		riskCheckAssertMsg.setMaxPayoutPerTxnReasonCode(recordset.getField("MaxPayoutPerTxnReasonCode").toString());
		riskCheckAssertMsg.setMaxPayoutPerDayReasonCode(recordset.getField("MaxPayoutPerDayReasonCode").toString());
		riskCheckAssertMsg.setMaxPayoutPerWeekReasonCode(recordset.getField("MaxPayoutPerWeekReasonCode").toString());
		riskCheckAssertMsg.setMaxPayoutPerMonthReasonCode(recordset.getField("MaxPayoutPerMonthReasonCode").toString());
		riskCheckAssertMsg.setMaxPayoutWithIn24hrsReasonCode(recordset.getField("MaxPayoutWithIn24hrsReasonCode").toString());
		
		
		
		riskCheckAssertMsg.setHotlistAccountNum(recordset.getField("hotlistAccountNum").toString());
		riskCheckAssertMsg.setHotlistEmail(recordset.getField("hotlistEmail").toString());
		riskCheckAssertMsg.setHotlistPhoneNum(recordset.getField("hotListPhoneNum").toString());
		
		return riskCheckAssertMsg;
	}


	public static ErrorMessageData loadErrorMsg() throws IOException, FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery = "Select * from ErrorMsgs";
		Recordset recordset = connection.executeQuery(strQuery);
		recordset.next();
		ErrorMessageData errorMessage = new ErrorMessageData();
		errorMessage.setEmptySenderAccNum(recordset.getField("EmptySenderAccNum").toString());
		errorMessage.setEmptySenderAccName(recordset.getField("EmptySenderAccName").toString());
		errorMessage.setInvdSenderAccNum(recordset.getField("InvdSenderAccNum").toString());
		errorMessage.setEmptyChannel(recordset.getField("EmptyChannel").toString());
		errorMessage.setEmptyRecipTyp(recordset.getField("EmptyRecipTyp").toString());
		errorMessage.setEmptyRecipNam(recordset.getField("EmptyRecipNam").toString());
		errorMessage.setEmptyRecipNam2(recordset.getField("EmptyRecipNam2").toString());
		errorMessage.setEmptyRecipAddrsLine(recordset.getField("EmptyRecipAddrsLine").toString());
		errorMessage.setEmptyRecipTown(recordset.getField("EmptyRecipTown").toString());
		errorMessage.setEmptyRecipState(recordset.getField("EmptyRecipState").toString());
		errorMessage.setEmptyRecipCntry(recordset.getField("EmptyRecipCntry").toString());
		errorMessage.setEmptyRecipZipCod(recordset.getField("EmptyRecipZipCod").toString());
		errorMessage.setEmptyRecipEmail(recordset.getField("EmptyRecipEmail").toString());
		errorMessage.setEmptyRecipRoutNum(recordset.getField("EmptyRecipRoutNum").toString());
		errorMessage.setEmptyRecipAccNum(recordset.getField("EmptyRecipAccNum").toString());
		errorMessage.setEmptyAmt(recordset.getField("EmptyAmt").toString());
		errorMessage.setEmptyEndToEnd(recordset.getField("EmptyEndToEnd").toString());
		errorMessage.setEmptyDocTyp(recordset.getField("EmptyDocTyp").toString());
		errorMessage.setEmptyMsgFI(recordset.getField("EmptyFileMsgFI").toString());

		errorMessage.setInvdRecipNam(recordset.getField("InvdRecipNam").toString());
		errorMessage.setInvdRecipNam2(recordset.getField("InvdRecipNam2").toString());
		errorMessage.setInvdRecipTown(recordset.getField("InvdRecipTown").toString());
		errorMessage.setInvdRecipPhn(recordset.getField("InvdRecipPhn").toString());
		errorMessage.setInvdRecipPhn2(recordset.getField("InvdRecipPhn2").toString());
		errorMessage.setInvdRecipRoutNum(recordset.getField("InvdRecipRoutNum").toString());
		errorMessage.setInvdRecipRoutNum2(recordset.getField("InvdRecipRoutNum2").toString());
		errorMessage.setInvdRecipAccNum(recordset.getField("InvdRecipAccNum").toString());
		errorMessage.setInvdAmt(recordset.getField("InvdAmt").toString());
		errorMessage.setInvdEndToEnd(recordset.getField("InvdEndToEnd").toString());
		errorMessage.setInvdZipCode(recordset.getField("InvdRecipZipCode").toString());
		errorMessage.setInvdRecipEmail(recordset.getField("InvdRecipEmail").toString());
		errorMessage.setInvdUploadDoc(recordset.getField("InvdUploadDoc").toString());
		errorMessage.setMaxDocSize(recordset.getField("MaxDocSize").toString());

		errorMessage.setAmtMaxExcd1(recordset.getField("AmtMaxExcd1").toString());
		errorMessage.setAmtMaxExcd2(recordset.getField("AmtMaxExcd2").toString());
		errorMessage.setAmtMaxExcd3(recordset.getField("AmtMaxExcd3").toString());
		errorMessage.setAmtMaxExcd4(recordset.getField("AmtMaxExcd4").toString());
		errorMessage.setAmtMaxExcd5(recordset.getField("AmtMaxExcd5").toString());
		errorMessage.setAmtMaxExcd6(recordset.getField("AmtMaxExcd6").toString());
		errorMessage.setAmtMaxExcd7(recordset.getField("AmtMaxExcd7").toString());

		errorMessage.setFwEmtyRefToRecip(recordset.getField("fwEmtyRefToRecip").toString());
		errorMessage.setFwEmtyInfoRcpFI(recordset.getField("fwEmtyInfoRcpFI").toString());
		errorMessage.setFwInvdRefToRecip(recordset.getField("fwInvdRefToRecip").toString());
		errorMessage.setFwInvdSendrToRecipInfo(recordset.getField("fwInvdSendrToRecipInfo").toString());
		errorMessage.setFwSenderToRecipInfo(recordset.getField("fw_SenderToRecipInfoErrorMsg").toString());
		errorMessage.setFwOrgEmtyIdTyp(recordset.getField("fwOrgEmtyIdTyp").toString());
		errorMessage.setFwOrgEmtyIdCod(recordset.getField("fwOrgEmtyIdCod").toString());
		errorMessage.setFwOrgEmtyNam(recordset.getField("fwOrgEmtyNam").toString());
		errorMessage.setFwOrgEmtyIdNum(recordset.getField("fwOrgEmtyIdNum").toString());
		errorMessage.setFwOrgEmtyAddrsTyp(recordset.getField("fwOrgEmtyAddrsTyp").toString());
		errorMessage.setFwOrgEmtyAddrsLin1(recordset.getField("fwOrgEmtyAddrsLin1").toString());
		errorMessage.setFwOrgEmtyState(recordset.getField("fwOrgEmtyState").toString());
		errorMessage.setFwOrgEmtyPostl(recordset.getField("fwOrgEmtyPostl").toString());
		errorMessage.setFwOrgEmtyCtry(recordset.getField("fwOrgEmtyCtry").toString());
		errorMessage.setFwOrgInvdCity(recordset.getField("fwOrgInvdCity").toString());
		errorMessage.setFwOrgInvdState(recordset.getField("fwOrgInvdState").toString());
		errorMessage.setFwOrgInvdPostl(recordset.getField("fwOrgInvdPostl").toString());
		errorMessage.setFwOrgInvdPhn(recordset.getField("fwOrgInvdPhn").toString());
		errorMessage.setFwOrgInvdEml(recordset.getField("fwOrgInvdEml").toString());
		errorMessage.setFwDocEmtyDocTyp(recordset.getField("fwDocEmtyDocTyp").toString());
		errorMessage.setFwDocEmtyDocIdNum(recordset.getField("fwDocEmtyDocIdNum").toString());
		errorMessage.setFwDocEmtyDocDat(recordset.getField("fwDocEmtyDocDat").toString());
		errorMessage.setFwDocInvdActlAmt(recordset.getField("fwDocInvdActlAmt").toString());
		errorMessage.setFwDocInvdOrgnlAmt(recordset.getField("fwDocInvdOrgnlAmt").toString());
		errorMessage.setFwDocInvdDiscAmt(recordset.getField("fwDocInvdDiscAmt").toString());
		errorMessage.setFwDocInvdAdjstAmt(recordset.getField("fwDocInvdAdjstAmt").toString());
		errorMessage.setFwDocExcdActlAmt(recordset.getField("fwDocExcdActlAmt").toString());
		errorMessage.setFwDocExcdOrgnlAmt(recordset.getField("fwDocExcdOrgnlAmt").toString());
		errorMessage.setFwDocExcdDiscAmt(recordset.getField("fwDocExcdDiscAmt").toString());
		errorMessage.setFwDocExcdAdjstAmt(recordset.getField("fwDocExcdAdjstAmt").toString());
		errorMessage.setFwEmptyDocAdjResnCode(recordset.getField("fwDocAdjResnCode").toString());
		errorMessage.setFwEmptyDocAdjIndictr(recordset.getField("fwDocAdjInditr").toString());
		errorMessage.setFwEmtyRemitId(recordset.getField("fwEmtyRemitId").toString());
		errorMessage.setFwEmtyRemitLocMthd(recordset.getField("fwEmtyRemitLocMthd").toString());
		errorMessage.setFwEmtyRemitLoc(recordset.getField("fwEmtyRemitLoc").toString());
		errorMessage.setFwInvdRemitId(recordset.getField("fwInvdRemitId").toString());
		errorMessage.setRtpEmtyDoc(recordset.getField("rtpEmtyDoc").toString());
		errorMessage.setRtpinvalidRemitDoc(recordset.getField("rtpInvalidDoc").toString());
		errorMessage.setRtpEmtyInvcAmt(recordset.getField("rtpEmtyInvcAmt").toString());
		errorMessage.setRtpInvdInvcAmt(recordset.getField("rtpInvdInvcAmt").toString());
		errorMessage.setRtpInvdRemitId(recordset.getField("rtpInvdRemitId").toString());
		errorMessage.setRtpEmtyDocTyp(recordset.getField("rtpEmtyDocTyp").toString());
		errorMessage.setRtpEmtyDiscAmt(recordset.getField("rtpEmtyDiscAmt").toString());
		errorMessage.setRtpEmtyDocDat(recordset.getField("rtpEmtyDocDat").toString());
		errorMessage.setRtpEmtyRemitLocMetd(recordset.getField("rtpEmtyRemitLocMetd").toString());
		errorMessage.setRtpInvdDiscAmt(recordset.getField("rtpInvdDiscAmt").toString());
		errorMessage.setEmptyDivision(recordset.getField("EmptyDivision").toString());
		errorMessage.setEmptyTransferType(recordset.getField("EmptyTransferType").toString());
		errorMessage.setEmptyRecipType(recordset.getField("EmptyRecipientType").toString());
		errorMessage.setEmptyTransferMethod(recordset.getField("EmptyTransferMethod").toString());
		errorMessage.setEmptyPaymentMethod(recordset.getField("EmptyPaymentMethod").toString());
		errorMessage.setInvalidNickname(recordset.getField("InvalidNickname").toString());
		errorMessage.setEmptyRecipientName(recordset.getField("EmptyRecipientName").toString());
		errorMessage.setInvalidRecipientName(recordset.getField("InvalidRecipientName").toString());
		errorMessage.setInvalidAddressLine(recordset.getField("InvalidAddressLine").toString());
		errorMessage.setInvalidEmail(recordset.getField("InvalidEmail").toString());
		errorMessage.setInvalidPhone(recordset.getField("InvalidPhone").toString());
		errorMessage.setEmptyAccNum(recordset.getField("EmptyAccountNum").toString());
		errorMessage.setInvalidAccNum(recordset.getField("InvalidAccountNum").toString());
		errorMessage.setOFACCheckMsg(recordset.getField("OFACCheckMSg").toString());
//================= FedFile Cases Data  ==============================================================================================================================================================================================		
		errorMessage.setFedFileMaxLengthsMsg(recordset.getField("FedFileMaxLengthsMsg").toString());
		errorMessage.setFedFileMinLengthsMsg(recordset.getField("FedFileMinLengthsMsg").toString());
		errorMessage.setEmptyTagsFedFileMsg(recordset.getField("EmptyFedFileMsg").toString());
		errorMessage.setInvalidAmtFedFileMsg(recordset.getField("AmountInvalidTagsMsg").toString());
		errorMessage.setEmptyFileMsg(recordset.getField("EmptyFileMsg").toString());
		errorMessage.setGreaterSizeFedFile(recordset.getField("GreaterFileSizeMsg").toString());
//================= BVA Cases Data  ==============================================================================================================================================================================================		
		errorMessage.setMinAccNumLengthErorrMsg(recordset.getField("MinAccNumLengthErorrMsg").toString());
		errorMessage.setMinRoutNumLengthErorrMsg(recordset.getField("MinRoutNumLengthErorrMsg").toString());
		errorMessage.setMinPhoneNumLengthErrorMsg(recordset.getField("MinPhoneNumLengthErorrMsg").toString());
		errorMessage.setMaxAmountError_RTP(recordset.getField("MaxAmount_RTP").toString());
		errorMessage.setMaxAmountError_FedNow(recordset.getField("MaxAmount_FedNow").toString());
		errorMessage.setMaxAmountError_Payments_FedWire(recordset.getField("MaxAmount_Payment_FedWire").toString());
		errorMessage.setMaxAmountError_Transfers_RTP(recordset.getField("MaxAmount_Transfers_RTP").toString());
//==================>> Travel Rule Cases <<====================================================================================================================================================================================
		errorMessage.setPoBoxErrMsg(recordset.getField("P.O.BoxMsg").toString());
		errorMessage.setWhitelistedCountryErrMsg(recordset.getField("WhiteListedCountryMsg").toString());
		errorMessage.setBlacklistedCountryErrMsg(recordset.getField("BlackListedCountryMsg").toString());


		
		return errorMessage;
	}


	public static BankRails loadBankRailsDetails() throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery = "Select * from BankRails";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();	
		BankRails bankRails=new BankRails();
		bankRails.setAccNumNotExistMsg(recordset.getField("AccNumNotExistMsg").toString());
		bankRails.setAccountNotopenMsg2(recordset.getField("AccountNotOpenMsg").toString());
		bankRails.setInvalidAccount(recordset.getField("InvalidAccount").toString());
		bankRails.setBankcoreServiceTimeoutMsg(recordset.getField("BankcoreServiceTimeoutMsg").toString());
		bankRails.setInsufficientBalance1(recordset.getField("InsuficientBalance1").toString());
		bankRails.setInsufficientBalance2(recordset.getField("InsuficientBalance2").toString());
		bankRails.setInvalidAmountInDebitorMsg(recordset.getField("InvalidAmountInDebitorMsg").toString());
		bankRails.setInvalidAmountInCreditorMsg(recordset.getField("InvalidAmountInCreditorMsg").toString());
		bankRails.setDepoAccRejectWarningMsg(recordset.getField("DepoAccRejectWarningMsg").toString());
		bankRails.setDepoAccRejectWarningMsg2(recordset.getField("DepoAccRejectWarningMsg2").toString());
		bankRails.setSimulatorWaitTimeoutMsg(recordset.getField("SimulatorWaitTimeoutMsg").toString());
		bankRails.setReqAmountLessThanMinMsg1(recordset.getField("ReqAmountLessThanMinMsg1").toString());
		bankRails.setReqAmountLessThanMinMsg2(recordset.getField("ReqAmountLessThanMinMsg2").toString());
		bankRails.setBankCoreSetUpErrMsg(recordset.getField("BankCoreSetUpErrMsg").toString());
		bankRails.setUnAuthorisedReqSentMsg(recordset.getField("UnAuthorisedReqSentMsg").toString());
		bankRails.setBankCoreServiceInternalMsg(recordset.getField("BankCoreServiceInternalMsg").toString());
		bankRails.setDebitorAccCloseMsg(recordset.getField("DebitorAccCloseMsg").toString());
		bankRails.setInsuficientBalance3(recordset.getField("InsuficientBalance3").toString());
		bankRails.setReqAmountLessThanMinMsg3(recordset.getField("ReqAmountLessThanMinMsg3").toString());
		bankRails.setAmountInvalidRejectMsg(recordset.getField("AmountInvalidRejectMsg").toString());
		bankRails.setAccWarningCodesMsg(recordset.getField("AccWarningCodesMsg").toString());
		bankRails.setInvalidInformRecMsg(recordset.getField("InvalidInformRecMsg").toString());
		bankRails.setAccHoldWarnMsg(recordset.getField("AccHoldWarnMsg").toString());

		return bankRails;

	}


	public static AddRecipientDetails loadXBWireDetails(String TCNo) throws IOException, FilloException {
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery="Select * from XBWireDetails where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		AddRecipientDetails recipentDtls=new AddRecipientDetails();
		recipentDtls.setRecipientType(recordset.getField("RecipType").toString());
		recipentDtls.setRecipientName(recordset.getField("RecipName").toString());
		recipentDtls.setRecipientAddrs(recordset.getField("RecipAddrs").toString());
		recipentDtls.setRecipientEmail(recordset.getField("RecipEmail").toString());
		recipentDtls.setRecipientPhone(recordset.getField("RecipPhone").toString());
		recipentDtls.setRecipientRoutNum(recordset.getField("RecipRouting").toString());
		recipentDtls.setRecipientAccNum(recordset.getField("RecipAccNum").toString());
		recipentDtls.setPmtAmt(recordset.getField("Amount").toString());
		recipentDtls.setiBan(recordset.getField("IBAN").toString());
		recipentDtls.setBicCodee(recordset.getField("BIC_Code").toString());
		recipentDtls.setPurposeOfPayment(recordset.getField("PurposeOfPayment").toString());
		recipentDtls.setBankBranchCode(recordset.getField("BankBranchCode").toString());
		recipentDtls.setNationality(recordset.getField("Nationality").toString());
		recipentDtls.setRecipientTax(recordset.getField("RecipientTax").toString());
		recipentDtls.setIFSCCode(recordset.getField("IFSCCode").toString());
		recipentDtls.setCountry(recordset.getField("Country").toString());
		recipentDtls.setCurrency(recordset.getField("Currency").toString());
		recipentDtls.setContactName(recordset.getField("ContactName").toString());

		return recipentDtls;
	}



	public static FedfileValidationPage loadfedFileData(String TCNo) throws IOException, FilloException{
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(Utility.getTMSTestDataPath());	
		String strQuery="Select * from FedFile where TestCaseNumber='"+TCNo+"'";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		FedfileValidationPage fedfile = new FedfileValidationPage();
		fedfile.setAccount(recordset.getField("Account").toString());
		fedfile.setAccountType(recordset.getField("AccountType").toString());
		fedfile.setNameOnAccount(recordset.getField("NameOnAccount").toString());
		fedfile.setSenderAccountNumber(recordset.getField("SenderAccNo").toString());
		fedfile.setDepOpSenderAccNumber(recordset.getField("DepOpSenderAccNumber").toString());
		fedfile.setRecipientType(recordset.getField("RecipType").toString());
		fedfile.setRecipientName(recordset.getField("RecipName").toString());
		fedfile.setRecipientAddress(recordset.getField("RecipAddrs").toString());
		fedfile.setRecipientRoutingNum(recordset.getField("RecipRouting").toString());
		fedfile.setRecipientAccountNum(recordset.getField("RecipAccNum").toString());
		fedfile.setAmount(recordset.getField("Amount").toString());
		fedfile.setRemitRefToRecip(recordset.getField("RemitRefToRecip").toString());
		fedfile.setSenderToRecipientInfo(recordset.getField("SenderToRecipientInfo").toString());
		fedfile.setInfoForRecipientFI_DropDown(recordset.getField("InfoForRecipientFI_DropDown").toString());
		fedfile.setInfoforRecipientFI_TextBox(recordset.getField("InfoforRecipientFI_TextBox").toString());
		fedfile.setAdditionalRemitInfo(recordset.getField("AdditionalRemitInfo").toString());
		fedfile.setRemitID(recordset.getField("RemitID").toString());
		fedfile.setRemitLocnMethod(recordset.getField("RemitLocnMethod").toString());
		fedfile.setRemitLocn(recordset.getField("RemitLocn").toString());

		fedfile.setFwStruOrgIdType(recordset.getField("fwStruOrgIdType").toString());
		fedfile.setFwStruOrgIdCode(recordset.getField("fwStruOrgIdCode").toString());
		fedfile.setFwStruOrgName(recordset.getField("fwStruOrgName").toString());
		fedfile.setFwStruOrgIdNum(recordset.getField("fwStruOrgIdNum").toString());
		fedfile.setFwStruOrgAddrsType(recordset.getField("fwStruOrgAddrsType").toString());
		fedfile.setFwStruOrgAddrsLine1(recordset.getField("fwStruOrgAddrsLine1").toString());
		fedfile.setFwStruOrgAddrsLine2(recordset.getField("fwStruOrgAddrsLine2").toString());
		fedfile.setFwStruOrgCity(recordset.getField("fwStruOrgCity").toString());
		fedfile.setFwStruOrgState(recordset.getField("fwStruOrgState").toString());
		fedfile.setFwStruOrgPostalCode(recordset.getField("fwStruOrgPostalCode").toString());
		fedfile.setFwStruOrgCountry(recordset.getField("fwStruOrgCountry").toString());
		fedfile.setFwStruOrgPhn(recordset.getField("fwStruOrgPhn").toString());
		fedfile.setFwStruOrgEmail(recordset.getField("fwStruOrgEmail").toString());

		fedfile.setFwStruBenefIdType(recordset.getField("fwStruBenefIdType").toString());
		fedfile.setFwStruBenefIdCode(recordset.getField("fwStruBenefIdCode").toString());
		fedfile.setFwStruBenefName(recordset.getField("fwStruBenefName").toString());
		fedfile.setFwStruBenefIdNum(recordset.getField("fwStruBenefIdNum").toString());
		fedfile.setFwStruBenefAddrsType(recordset.getField("fwStruBenefAddrsType").toString());
		fedfile.setFwStruBenefAddrsLine1(recordset.getField("fwStruBenefAddrsLine1").toString());
		fedfile.setFwStruBenefAddrsLine2(recordset.getField("fwStruBenefAddrsLine2").toString());
		fedfile.setFwStruBenefCity(recordset.getField("fwStruBenefCity").toString());
		fedfile.setFwStruBenefState(recordset.getField("fwStruBenefState").toString());
		fedfile.setFwStruBenefPostalCode(recordset.getField("fwStruBenefPostalCode").toString());
		fedfile.setFwStruBenefCountry(recordset.getField("fwStruBenefCountry").toString());

		fedfile.setFwStruDocumType(recordset.getField("fwStruDocumType").toString());
		fedfile.setFwStruDocumIdNum(recordset.getField("fwStruDocumIdNum").toString());
		fedfile.setFwStruDocuDate(recordset.getField("fwStruDocuDate").toString());
		fedfile.setFwStruAmtPaid(recordset.getField("fwStruAmtPaid").toString());
		fedfile.setFwStruOrgnlAmtPaid(recordset.getField("fwStruOrgnlAmtPaid").toString());
		fedfile.setFwStruDiscAmtPaid(recordset.getField("fwStruDiscAmtPaid").toString());
		fedfile.setFwStruAdjAmtPaid(recordset.getField("fwStruAdjAmtPaid").toString());
		fedfile.setFwStruAdjReasonCode(recordset.getField("fwStruAdjReasonCode").toString());
		fedfile.setFwAdjustIndic(recordset.getField("fwAdjustIndic").toString());

		fedfile.setChannel(recordset.getField("Channel").toString());
		fedfile.setDepOpNewRecipLink(recordset.getField("DepOpNewRecipLink").toString());
		fedfile.setFeeAmt(recordset.getField("FeeAmount").toString());


		return fedfile;
	}


	public static XBWireFieldValidationPage loadXBWireFieldsData() throws IOException, FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery = "Select * from XBWireFields";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();
		XBWireFieldValidationPage errorMessage = new XBWireFieldValidationPage();
		errorMessage.setCountryEmptyMsg(recordset.getField("CountryEmptyMsg").toString());
		errorMessage.setCurrencyEmptyMsg(recordset.getField("CurrencyEmptyMsg").toString());
		errorMessage.setContactNameEmptyMsg(recordset.getField("ContactNameEmptyMsg").toString());
		errorMessage.setInvalidContactNameMsg(recordset.getField("InvalidContactNameMsg").toString());
		errorMessage.setAccountNoNotEmptyMsg(recordset.getField("AccountNoNotEmptyMsg").toString());
		errorMessage.setInvalidAccountNoMsg(recordset.getField("InvalidAccountNoMsg").toString());
		errorMessage.setAccountNoLessThanMinMsg(recordset.getField("AccountNoLessThanMinMsg").toString());
		errorMessage.setBankRoutingNotEmptyMsg(recordset.getField("BankRoutingNotEmptyMsg").toString());
		errorMessage.setInvalidRoutingMsg(recordset.getField("InvalidRoutingMsg").toString());
		errorMessage.setPhoneNoNotEmptyMsg(recordset.getField("PhoneNoNotEmptyMsg").toString());
		errorMessage.setInvalidPhoneNoMsg(recordset.getField("InvalidPhoneNoMsg").toString());
		errorMessage.setPhoneNoLessThanMinMsg(recordset.getField("PhoneNoLessThanMinMsg").toString());
		errorMessage.setBICNotEmptyMsg(recordset.getField("BICNotEmptyMsg").toString());
		errorMessage.setInvalidBICMsg(recordset.getField("InvalidBICMsg").toString());
		errorMessage.setBICLessThanMinMsg(recordset.getField("BICLessThanMinMsg").toString());
		errorMessage.setTaxIdNotEmptyMsg(recordset.getField("TaxIdNotEmptyMsg").toString());
		errorMessage.setInvalidTaxIdMsg(recordset.getField("InvalidTaxIdMsg").toString());
		errorMessage.setTaxIdLessThanMinMsg(recordset.getField("TaxIdLessThanMinMsg").toString());
		errorMessage.setNationalityEmptyMsg(recordset.getField("NationalityEmptyMsg").toString());
		errorMessage.setPurposeOfPayNotEmptyMsg(recordset.getField("PurposeOfPayNotEmptyMsg").toString());
		errorMessage.setPassportNoNotEmptyMsg(recordset.getField("PassportNoNotEmptyMsg").toString());
		errorMessage.setInvalidPassportNoMsg(recordset.getField("InvalidPassportNoMsg").toString());
		errorMessage.setPassportNoLessThanMinMsg(recordset.getField("PassportNoLessThanMinMsg").toString());
		errorMessage.setIFSCEmptyMsg(recordset.getField("IFSCEmptyMsg").toString());
		errorMessage.setInvalidIFSCMsg(recordset.getField("InvalidIFSCMsg").toString());
		errorMessage.setPurposeOfPaymntCodeEmptyMsg(recordset.getField("PurposeOfPaymntCodeEmptyMsg").toString());
		errorMessage.setIBANEmptyMSg(recordset.getField("IBANEmptyMSg").toString());
		errorMessage.setInvalidIBANMsg(recordset.getField("InvalidIBANMsg").toString());
		errorMessage.setIBANMinLessThanMsg(recordset.getField("IBANMinLessThanMsg").toString());
		errorMessage.setBankBranchAddressEmptyMsg(recordset.getField("BankBranchAddressEmptyMsg").toString());
		errorMessage.setRecAccountMethodsEmptyMsg(recordset.getField("RecAccountMethodsEmptyMsg").toString());
		errorMessage.setInvalidRecipientNameMsg(recordset.getField("InvalidRecipientNameMsg").toString());
		errorMessage.setInvalidAddressLineMsg(recordset.getField("InvalidAddressLineMsg").toString());
		errorMessage.setInvalidTownMsg(recordset.getField("InvalidTownMsg").toString());
		errorMessage.setInvalidStateMsg(recordset.getField("InvalidStateMsg").toString());
		errorMessage.setInvalidZipMsg(recordset.getField("InvalidZipMsg").toString());
		errorMessage.setInvalidEndTOEndMsg(recordset.getField("InvalidEndTOEndMsg").toString());
		errorMessage.setInvalidSenderRecMsg(recordset.getField("InvalidSenderRecMsg").toString());
		return errorMessage;


	}
	
	public static AddSenderDetails loadTaxWireFieldsData() throws FilloException
	{
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(Utility.getTMSTestDataPath());
		String strQuery = "Select * from Taxwires";
		Recordset recordset=connection.executeQuery(strQuery);
		recordset.next();		
		AddSenderDetails taxwires = new AddSenderDetails();
		
		
		
		
		return taxwires;
	}

}