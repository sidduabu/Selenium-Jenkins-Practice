package dataReader;


public class ErrorMessageData {


	private String EmptySenderAccName;
	private String EmptySenderAccNum;
	private String InvdSenderAccNum;
	private String EmptyChannel;
	private String EmptyRecipTyp;
	private String EmptyRecipNam;
	private String EmptyRecipNam2;
	private String EmptyRecipAddrsLine;
	private String EmptyRecipTown;
	private String EmptyRecipState;
	private String EmptyRecipCntry;
	private String EmptyRecipZipCod;
	private String EmptyRecipEmail;
	private String EmptyRecipRoutNum;
	private String EmptyRecipAccNum;
	private String EmptyAmt;
	private String EmptyEndToEnd;
	private String EmptyMsgFI;


	private String EmptyDocTyp;
	private String InvdRecipNam;
	private String InvdRecipNam2;
	private String InvdRecipTown;
	private String InvdRecipPhn;
	private String InvdRecipPhn2;
	private String InvdRecipRoutNum;
	private String InvdRecipRoutNum2;
	private String InvdRecipAccNum;
	private String InvdAmt;
	private String InvdEndToEnd;
	private String InvdZipCode;
	private String InvdRecipEmail;
	private String InvdUploadDoc;
	private String MaxDocSize;
	private String AmtMaxExcd1;
	private String AmtMaxExcd2;
	private String AmtMaxExcd3;
	private String AmtMaxExcd4;
	private String AmtMaxExcd5;
	private String AmtMaxExcd6;
	private String AmtMaxExcd7;
	private String fwEmtyRefToRecip;
	private String fwEmtyInfoRcpFI;
	private String fwInvdSendrToRecipInfo;
	private String fwInvdRefToRecip;
	private String fwSenderToRecipInfo ;
	private String fwOrgEmtyIdTyp;
	private String fwOrgEmtyIdCod;
	private String fwOrgEmtyNam;
	private String fwOrgEmtyIdNum;
	private String fwOrgEmtyAddrsTyp;
	private String fwOrgEmtyAddrsLin1;
	private String fwOrgEmtyState;
	private String fwOrgEmtyPostl;
	private String fwOrgEmtyCtry;
	private String fwOrgInvdCity;
	private String fwOrgInvdState;
	private String fwOrgInvdPostl;
	private String fwOrgInvdPhn;
	private String fwOrgInvdEml;
	private String fwDocEmtyDocTyp;
	private String fwDocEmtyDocIdNum;
	private String fwDocEmtyDocDat;
	private String fwDocInvdActlAmt;
	private String fwDocInvdOrgnlAmt;
	private String fwDocInvdDiscAmt;
	private String fwDocInvdAdjstAmt;
	private String fwDocExcdActlAmt;
	private String fwDocExcdOrgnlAmt;
	private String fwDocExcdDiscAmt;
	private String fwDocExcdAdjstAmt;
	private String fwEmptyDocAdjResnCode;
	private String fwEmptyDocAdjIndictr;
	private String fwEmtyRemitId;
	private String fwEmtyRemitLocMthd;
	private String fwEmtyRemitLoc;
	private String fwInvdRemitId;

	private String rtpEmtyDoc;
	private String rtpinvalidRemitDoc;
	private String rtpEmtyInvcAmt;
	private String rtpInvdInvcAmt;
	private String rtpInvdRemitId;

	private String rtpEmtyDocTyp;
	private String rtpEmtyDocDat;
	private String rtpEmtyDiscAmt;
	private String rtpEmtyRemitLocMetd;
	private String rtpInvdDiscAmt;



	private String EmptyDivision;
	private String EmptyTransferType;
	private String EmptyTransferMethod;
	private String EmptyRecipType;
	private String EmptyPaymentMethod;
	private String InvalidNickname;
	private String EmptyRecipientName;
	private String InvalidRecipientName;
	private String InvalidAddressLine;
	private String InvalidEmail;
	private String InvalidPhone;
	private String EmptyAccNum;
	private String InvalidAccNum;

//================= FedFile =====================================================	
	private String FedFileMaxLengthsMsg;
	private String FedFileMinLengthsMsg;
	private String EmptyTagsFedFileMsg;
	private String InvalidAmtFedFileMsg;
	private String EmptyFileMsg;
	private String greaterSizeFedFile;

//===========>> HotList, OFAC & AML-Limit Checks <<===================================================================
	private String hotlistReason;
	private String hotlistReasonCode;

	private String OFACCheckMsg;

	private String MinPayoutPerTxnReason;
	private String MaxPayoutPerTxnReason;
	private String MaxPayoutPerDayReason;
	private String MaxPayoutPerWeekReason;
	private String MaxPayoutPerMonthReason;
	private String MaxPayoutWithIn24hrsReason;

	private String MinPayoutPerTxnReasonCode;
	private String MaxPayoutPerTxnReasonCode;
	private String MaxPayoutPerDayReasonCode;
	private String MaxPayoutPerWeekReasonCode;
	private String MaxPayoutPerMonthReasonCode;
	private String MaxPayoutWithIn24hrsReasonCode;

	private String hotlistAccountNum;
	private String hotlistEmail;
	private String hotlistPhoneNum;

//================== BVA ======================================================
	private String minAccNumLengthErorrMsg;
	private String minRoutNumLengthErorrMsg;
	private String minPhoneNumLengthErrorMsg;
	private String maxAmountError_RTP;
	private String maxAmountError_FedNow;
	private String maxAmountError_Payments_FedWire;
	private String maxAmountError_Transfers_RTP;

//================== TravelRule Validation error messages =======================
	private String poBoxErrMsg;
	private String whitelistedCountryErrMsg;
	private String blacklistedCountryErrMsg;

	
	
	public String getPoBoxErrMsg() {
		return poBoxErrMsg;
	}

	public void setPoBoxErrMsg(String poBoxErrMsg) {
		this.poBoxErrMsg = poBoxErrMsg;
	}

	public String getWhitelistedCountryErrMsg() {
		return whitelistedCountryErrMsg;
	}

	public void setWhitelistedCountryErrMsg(String whitelistedCountryErrMsg) {
		this.whitelistedCountryErrMsg = whitelistedCountryErrMsg;
	}

	public String getBlacklistedCountryErrMsg() {
		return blacklistedCountryErrMsg;
	}

	public void setBlacklistedCountryErrMsg(String blacklistedCountryErrMsg) {
		this.blacklistedCountryErrMsg = blacklistedCountryErrMsg;
	}

	public String getMaxAmountError_Payments_FedWire() {
		return maxAmountError_Payments_FedWire;
	}

	public void setMaxAmountError_Payments_FedWire(String maxAmountError_Payments_FedWire) {
		this.maxAmountError_Payments_FedWire = maxAmountError_Payments_FedWire;
	}

	public String getMaxAmountError_Transfers_RTP() {
		return maxAmountError_Transfers_RTP;
	}

	public void setMaxAmountError_Transfers_RTP(String maxAmountError_Transfers_RTP) {
		this.maxAmountError_Transfers_RTP = maxAmountError_Transfers_RTP;
	}

	public String getMinRoutNumLengthErorrMsg() {
		return minRoutNumLengthErorrMsg;
	}

	public void setMinRoutNumLengthErorrMsg(String minRoutNumLengthErorrMsg) {
		this.minRoutNumLengthErorrMsg = minRoutNumLengthErorrMsg;
	}

	public String getMinAccNumLengthErorrMsg() {
		return minAccNumLengthErorrMsg;
	}

	public void setMinAccNumLengthErorrMsg(String minAccNumLengthErorrMsg) {
		this.minAccNumLengthErorrMsg = minAccNumLengthErorrMsg;
	}

	public String getMinPhoneNumLengthErrorMsg() {
		return minPhoneNumLengthErrorMsg;
	}

	public void setMinPhoneNumLengthErrorMsg(String minPhoneNumLengthErrorMsg) {
		this.minPhoneNumLengthErrorMsg = minPhoneNumLengthErrorMsg;
	}

	public String getMaxAmountError_RTP() {
		return maxAmountError_RTP;
	}

	public void setMaxAmountError_RTP(String maxAmountError_RTP) {
		this.maxAmountError_RTP = maxAmountError_RTP;
	}

	public String getMaxAmountError_FedNow() {
		return maxAmountError_FedNow;
	}

	public void setMaxAmountError_FedNow(String maxAmountError_FedNow) {
		this.maxAmountError_FedNow = maxAmountError_FedNow;
	}

	public String getHotlistAccountNum() {
		return hotlistAccountNum;
	}

	public void setHotlistAccountNum(String hotlistAccountNum) {
		this.hotlistAccountNum = hotlistAccountNum;
	}

	public String getHotlistEmail() {
		return hotlistEmail;
	}

	public void setHotlistEmail(String hotlistEmail) {
		this.hotlistEmail = hotlistEmail;
	}

	public String getHotlistPhoneNum() {
		return hotlistPhoneNum;
	}

	public void setHotlistPhoneNum(String hotlistPhoneNum) {
		this.hotlistPhoneNum = hotlistPhoneNum;
	}

	public String getOFACCheckMsg() {
		return OFACCheckMsg;
	}

	public void setOFACCheckMsg(String oFACCheckMsg) {
		OFACCheckMsg = oFACCheckMsg;
	}

	public String getEmptyMsgFI() {
		return EmptyMsgFI;
	}

	public void setEmptyMsgFI(String emptyMsgFI) {
		EmptyMsgFI = emptyMsgFI;
	}

	public String getHotlistReason() {
		return hotlistReason;
	}

	public void setHotlistReason(String hotlistReason) {
		this.hotlistReason = hotlistReason;
	}

	public String getHotlistReasonCode() {
		return hotlistReasonCode;
	}

	public void setHotlistReasonCode(String hotlistReasonCode) {
		this.hotlistReasonCode = hotlistReasonCode;
	}

	public String getMinPayoutPerTxnReason() {
		return MinPayoutPerTxnReason;
	}

	public void setMinPayoutPerTxnReason(String minPayoutPerTxnReason) {
		MinPayoutPerTxnReason = minPayoutPerTxnReason;
	}

	public String getMaxPayoutPerTxnReason() {
		return MaxPayoutPerTxnReason;
	}

	public void setMaxPayoutPerTxnReason(String maxPayoutPerTxnReason) {
		MaxPayoutPerTxnReason = maxPayoutPerTxnReason;
	}

	public String getMaxPayoutPerDayReason() {
		return MaxPayoutPerDayReason;
	}

	public void setMaxPayoutPerDayReason(String maxPayoutPerDayReason) {
		MaxPayoutPerDayReason = maxPayoutPerDayReason;
	}

	public String getMaxPayoutPerWeekReason() {
		return MaxPayoutPerWeekReason;
	}

	public void setMaxPayoutPerWeekReason(String maxPayoutPerWeekReason) {
		MaxPayoutPerWeekReason = maxPayoutPerWeekReason;
	}

	public String getMaxPayoutPerMonthReason() {
		return MaxPayoutPerMonthReason;
	}

	public void setMaxPayoutPerMonthReason(String maxPayoutPerMonthReason) {
		MaxPayoutPerMonthReason = maxPayoutPerMonthReason;
	}

	public String getMaxPayoutWithIn24hrsReason() {
		return MaxPayoutWithIn24hrsReason;
	}

	public void setMaxPayoutWithIn24hrsReason(String maxPayoutWithIn24hrsReason) {
		MaxPayoutWithIn24hrsReason = maxPayoutWithIn24hrsReason;
	}

	public String getMinPayoutPerTxnReasonCode() {
		return MinPayoutPerTxnReasonCode;
	}

	public void setMinPayoutPerTxnReasonCode(String minPayoutPerTxnReasonCode) {
		MinPayoutPerTxnReasonCode = minPayoutPerTxnReasonCode;
	}

	public String getMaxPayoutPerTxnReasonCode() {
		return MaxPayoutPerTxnReasonCode;
	}

	public void setMaxPayoutPerTxnReasonCode(String maxPayoutPerTxnReasonCode) {
		MaxPayoutPerTxnReasonCode = maxPayoutPerTxnReasonCode;
	}

	public String getMaxPayoutPerDayReasonCode() {
		return MaxPayoutPerDayReasonCode;
	}

	public void setMaxPayoutPerDayReasonCode(String maxPayoutPerDayReasonCode) {
		MaxPayoutPerDayReasonCode = maxPayoutPerDayReasonCode;
	}

	public String getMaxPayoutPerWeekReasonCode() {
		return MaxPayoutPerWeekReasonCode;
	}

	public void setMaxPayoutPerWeekReasonCode(String maxPayoutPerWeekReasonCode) {
		MaxPayoutPerWeekReasonCode = maxPayoutPerWeekReasonCode;
	}

	public String getMaxPayoutPerMonthReasonCode() {
		return MaxPayoutPerMonthReasonCode;
	}

	public void setMaxPayoutPerMonthReasonCode(String maxPayoutPerMonthReasonCode) {
		MaxPayoutPerMonthReasonCode = maxPayoutPerMonthReasonCode;
	}

	public String getMaxPayoutWithIn24hrsReasonCode() {
		return MaxPayoutWithIn24hrsReasonCode;
	}

	public void setMaxPayoutWithIn24hrsReasonCode(String maxPayoutWithIn24hrsReasonCode) {
		MaxPayoutWithIn24hrsReasonCode = maxPayoutWithIn24hrsReasonCode;
	}

	public String getFedFileMaxLengthsMsg() {
		return FedFileMaxLengthsMsg;
	}

	public void setFedFileMaxLengthsMsg(String fedFileMaxLengthsMsg) {
		FedFileMaxLengthsMsg = fedFileMaxLengthsMsg;
	}

	public String getFedFileMinLengthsMsg() {
		return FedFileMinLengthsMsg;
	}

	public void setFedFileMinLengthsMsg(String fedFileMinLengthsMsg) {
		FedFileMinLengthsMsg = fedFileMinLengthsMsg;
	}

	public String getEmptyTagsFedFileMsg() {
		return EmptyTagsFedFileMsg;
	}

	public void setEmptyTagsFedFileMsg(String emptyTagsFedFileMsg) {
		EmptyTagsFedFileMsg = emptyTagsFedFileMsg;
	}

	public String getInvalidAmtFedFileMsg() {
		return InvalidAmtFedFileMsg;
	}

	public void setInvalidAmtFedFileMsg(String invalidAmtFedFileMsg) {
		InvalidAmtFedFileMsg = invalidAmtFedFileMsg;
	}

	public String getEmptyFileMsg() {
		return EmptyFileMsg;
	}

	public void setEmptyFileMsg(String emptyFileMsg) {
		EmptyFileMsg = emptyFileMsg;
	}

	public String getGreaterSizeFedFile() {
		return greaterSizeFedFile;
	}

	public void setGreaterSizeFedFile(String greaterSizeFedFile) {
		this.greaterSizeFedFile = greaterSizeFedFile;
	}

	public String getEmptyChannel() {
		return EmptyChannel;
	}

	public void setEmptyChannel(String emptyChannel) {
		EmptyChannel = emptyChannel;
	}

	public String getMaxDocSize() {
		return MaxDocSize;
	}

	public void setMaxDocSize(String maxDocSize) {
		MaxDocSize = maxDocSize;
	}

	public String getEmptyAccNum() {
		return EmptyAccNum;
	}

	public void setEmptyAccNum(String emptyAccNum) {
		EmptyAccNum = emptyAccNum;
	}

	public String getInvalidAccNum() {
		return InvalidAccNum;
	}

	public void setInvalidAccNum(String invalidAccNum) {
		InvalidAccNum = invalidAccNum;
	}

	public String getEmptyRecipType() {
		return EmptyRecipType;
	}

	public void setEmptyRecipType(String emptyRecipType) {
		EmptyRecipType = emptyRecipType;
	}

	public String getEmptyDivision() {
		return EmptyDivision;
	}

	public void setEmptyDivision(String emptyDivision) {
		EmptyDivision = emptyDivision;
	}

	public String getEmptyTransferType() {
		return EmptyTransferType;
	}

	public void setEmptyTransferType(String emptyTransferType) {
		EmptyTransferType = emptyTransferType;
	}

	public String getEmptyTransferMethod() {
		return EmptyTransferMethod;
	}

	public void setEmptyTransferMethod(String emptyTransferMethod) {
		EmptyTransferMethod = emptyTransferMethod;
	}

	public String getEmptyPaymentMethod() {
		return EmptyPaymentMethod;
	}

	public void setEmptyPaymentMethod(String emptyPaymentMethod) {
		EmptyPaymentMethod = emptyPaymentMethod;
	}

	public String getInvalidNickname() {
		return InvalidNickname;
	}

	public void setInvalidNickname(String invalidNickname) {
		InvalidNickname = invalidNickname;
	}

	public String getEmptyRecipientName() {
		return EmptyRecipientName;
	}

	public void setEmptyRecipientName(String emptyRecipientName) {
		EmptyRecipientName = emptyRecipientName;
	}

	public String getInvalidRecipientName() {
		return InvalidRecipientName;
	}

	public void setInvalidRecipientName(String invalidRecipientName) {
		InvalidRecipientName = invalidRecipientName;
	}

	public String getInvalidAddressLine() {
		return InvalidAddressLine;
	}

	public void setInvalidAddressLine(String invalidAddressLine) {
		InvalidAddressLine = invalidAddressLine;
	}

	public String getInvalidEmail() {
		return InvalidEmail;
	}

	public void setInvalidEmail(String invalidEmail) {
		InvalidEmail = invalidEmail;
	}

	public String getInvalidPhone() {
		return InvalidPhone;
	}

	public void setInvalidPhone(String invalidPhone) {
		InvalidPhone = invalidPhone;
	}

	public String getFwOrgEmtyState() {
		return fwOrgEmtyState;
	}

	public void setFwOrgEmtyState(String fwOrgEmtyState) {
		this.fwOrgEmtyState = fwOrgEmtyState;
	}

	public String getFwInvdSendrToRecipInfo() {
		return fwInvdSendrToRecipInfo;
	}

	public void setFwInvdSendrToRecipInfo(String fwInvdSendrToRecipInfo) {
		this.fwInvdSendrToRecipInfo = fwInvdSendrToRecipInfo;
	}

	public String getFwInvdRemitId() {
		return fwInvdRemitId;
	}

	public void setFwInvdRemitId(String fwInvdRemitId) {
		this.fwInvdRemitId = fwInvdRemitId;
	}

	public String getFwEmptyDocAdjResnCode() {
		return fwEmptyDocAdjResnCode;
	}

	public void setFwEmptyDocAdjResnCode(String fwEmptyDocAdjResnCode) {
		this.fwEmptyDocAdjResnCode = fwEmptyDocAdjResnCode;
	}

	public String getFwEmptyDocAdjIndictr() {
		return fwEmptyDocAdjIndictr;
	}

	public void setFwEmptyDocAdjIndictr(String fwEmptyDocAdjIndictr) {
		this.fwEmptyDocAdjIndictr = fwEmptyDocAdjIndictr;
	}

	public String getRtpEmtyDocTyp() {
		return rtpEmtyDocTyp;
	}

	public void setRtpEmtyDocTyp(String rtpEmtyDocTyp) {
		this.rtpEmtyDocTyp = rtpEmtyDocTyp;
	}

	public String getRtpEmtyDocDat() {
		return rtpEmtyDocDat;
	}

	public void setRtpEmtyDocDat(String rtpEmtyDocDat) {
		this.rtpEmtyDocDat = rtpEmtyDocDat;
	}

	public String getRtpEmtyDiscAmt() {
		return rtpEmtyDiscAmt;
	}

	public void setRtpEmtyDiscAmt(String rtpEmtyDiscAmt) {
		this.rtpEmtyDiscAmt = rtpEmtyDiscAmt;
	}

	public String getRtpEmtyRemitLocMetd() {
		return rtpEmtyRemitLocMetd;
	}

	public void setRtpEmtyRemitLocMetd(String rtpEmtyRemitLocMetd) {
		this.rtpEmtyRemitLocMetd = rtpEmtyRemitLocMetd;
	}

	public String getRtpInvdDiscAmt() {
		return rtpInvdDiscAmt;
	}

	public void setRtpInvdDiscAmt(String rtpInvdDiscAmt) {
		this.rtpInvdDiscAmt = rtpInvdDiscAmt;
	}

	public String getInvdEndToEnd() {
		return InvdEndToEnd;
	}

	public void setInvdEndToEnd(String invdEndToEnd) {
		InvdEndToEnd = invdEndToEnd;
	}

	public String getInvdRecipEmail() {
		return InvdRecipEmail;
	}

	public void setInvdRecipEmail(String invdRecipEmail) {
		InvdRecipEmail = invdRecipEmail;
	}

	public String getInvdZipCode() {
		return InvdZipCode;
	}

	public void setInvdZipCode(String invdZipCode) {
		InvdZipCode = invdZipCode;
	}

	public String getEmptyEndToEnd() {
		return EmptyEndToEnd;
	}

	public void setEmptyEndToEnd(String emptyEndToEnd) {
		EmptyEndToEnd = emptyEndToEnd;
	}

	public String getEmptySenderAccName() {
		return EmptySenderAccName;
	}

	public void setEmptySenderAccName(String emptySenderAccName) {
		EmptySenderAccName = emptySenderAccName;
	}

	public String getEmptySenderAccNum() {
		return EmptySenderAccNum;
	}

	public void setEmptySenderAccNum(String emptySenderAccNum) {
		EmptySenderAccNum = emptySenderAccNum;
	}

	public String getInvdSenderAccNum() {
		return InvdSenderAccNum;
	}

	public void setInvdSenderAccNum(String invdSenderAccNum) {
		InvdSenderAccNum = invdSenderAccNum;
	}

	public String getFwOrgInvdPhn() {
		return fwOrgInvdPhn;
	}

	public void setFwOrgInvdPhn(String fwOrgInvdPhn) {
		this.fwOrgInvdPhn = fwOrgInvdPhn;
	}

	public String getFwOrgInvdEml() {
		return fwOrgInvdEml;
	}

	public void setFwOrgInvdEml(String fwOrgInvdEml) {
		this.fwOrgInvdEml = fwOrgInvdEml;
	}

	public String getFwDocEmtyDocTyp() {
		return fwDocEmtyDocTyp;
	}

	public void setFwDocEmtyDocTyp(String fwDocEmtyDocTyp) {
		this.fwDocEmtyDocTyp = fwDocEmtyDocTyp;
	}

	public String getFwDocEmtyDocIdNum() {
		return fwDocEmtyDocIdNum;
	}

	public void setFwDocEmtyDocIdNum(String fwDocEmtyDocIdNum) {
		this.fwDocEmtyDocIdNum = fwDocEmtyDocIdNum;
	}

	public String getFwDocEmtyDocDat() {
		return fwDocEmtyDocDat;
	}

	public void setFwDocEmtyDocDat(String fwDocEmtyDocDat) {
		this.fwDocEmtyDocDat = fwDocEmtyDocDat;
	}

	public String getFwDocInvdActlAmt() {
		return fwDocInvdActlAmt;
	}

	public void setFwDocInvdActlAmt(String fwDocInvdActlAmt) {
		this.fwDocInvdActlAmt = fwDocInvdActlAmt;
	}

	public String getFwDocInvdOrgnlAmt() {
		return fwDocInvdOrgnlAmt;
	}

	public void setFwDocInvdOrgnlAmt(String fwDocInvdOrgnlAmt) {
		this.fwDocInvdOrgnlAmt = fwDocInvdOrgnlAmt;
	}

	public String getFwDocInvdDiscAmt() {
		return fwDocInvdDiscAmt;
	}

	public void setFwDocInvdDiscAmt(String fwDocInvdDiscAmt) {
		this.fwDocInvdDiscAmt = fwDocInvdDiscAmt;
	}

	public String getFwDocInvdAdjstAmt() {
		return fwDocInvdAdjstAmt;
	}

	public void setFwDocInvdAdjstAmt(String fwDocInvdAdjstAmt) {
		this.fwDocInvdAdjstAmt = fwDocInvdAdjstAmt;
	}

	public String getFwDocExcdActlAmt() {
		return fwDocExcdActlAmt;
	}

	public void setFwDocExcdActlAmt(String fwDocExcdActlAmt) {
		this.fwDocExcdActlAmt = fwDocExcdActlAmt;
	}

	public String getFwDocExcdOrgnlAmt() {
		return fwDocExcdOrgnlAmt;
	}

	public void setFwDocExcdOrgnlAmt(String fwDocExcdOrgnlAmt) {
		this.fwDocExcdOrgnlAmt = fwDocExcdOrgnlAmt;
	}

	public String getFwDocExcdDiscAmt() {
		return fwDocExcdDiscAmt;
	}

	public void setFwDocExcdDiscAmt(String fwDocExcdDiscAmt) {
		this.fwDocExcdDiscAmt = fwDocExcdDiscAmt;
	}

	public String getFwDocExcdAdjstAmt() {
		return fwDocExcdAdjstAmt;
	}

	public void setFwDocExcdAdjstAmt(String fwDocExcdAdjstAmt) {
		this.fwDocExcdAdjstAmt = fwDocExcdAdjstAmt;
	}

	public String getFwEmtyRemitId() {
		return fwEmtyRemitId;
	}

	public void setFwEmtyRemitId(String fwEmtyRemitId) {
		this.fwEmtyRemitId = fwEmtyRemitId;
	}

	public String getFwEmtyRemitLocMthd() {
		return fwEmtyRemitLocMthd;
	}

	public void setFwEmtyRemitLocMthd(String fwEmtyRemitLocMthd) {
		this.fwEmtyRemitLocMthd = fwEmtyRemitLocMthd;
	}

	public String getFwEmtyRemitLoc() {
		return fwEmtyRemitLoc;
	}

	public void setFwEmtyRemitLoc(String fwEmtyRemitLoc) {
		this.fwEmtyRemitLoc = fwEmtyRemitLoc;
	}

	public String getRtpEmtyDoc() {
		return rtpEmtyDoc;
	}

	public void setRtpEmtyDoc(String rtpEmtyDoc) {
		this.rtpEmtyDoc = rtpEmtyDoc;
	}

	public String getRtpinvalidRemitDoc() {
		return rtpinvalidRemitDoc;
	}

	public void setRtpinvalidRemitDoc(String rtpinvalidRemitDoc) {
		this.rtpinvalidRemitDoc = rtpinvalidRemitDoc;
	}

	public String getRtpEmtyInvcAmt() {
		return rtpEmtyInvcAmt;
	}

	public void setRtpEmtyInvcAmt(String rtpEmtyInvcAmt) {
		this.rtpEmtyInvcAmt = rtpEmtyInvcAmt;
	}

	public String getRtpInvdInvcAmt() {
		return rtpInvdInvcAmt;
	}

	public void setRtpInvdInvcAmt(String rtpInvdInvcAmt) {
		this.rtpInvdInvcAmt = rtpInvdInvcAmt;
	}

	public String getRtpInvdRemitId() {
		return rtpInvdRemitId;
	}

	public void setRtpInvdRemitId(String rtpInvdRemitId) {
		this.rtpInvdRemitId = rtpInvdRemitId;
	}

	public String getFwOrgEmtyIdCod() {
		return fwOrgEmtyIdCod;
	}

	public void setFwOrgEmtyIdCod(String fwOrgEmtyIdCod) {
		this.fwOrgEmtyIdCod = fwOrgEmtyIdCod;
	}

	public String getFwOrgEmtyNam() {
		return fwOrgEmtyNam;
	}

	public void setFwOrgEmtyNam(String fwOrgEmtyNam) {
		this.fwOrgEmtyNam = fwOrgEmtyNam;
	}

	public String getFwOrgEmtyIdNum() {
		return fwOrgEmtyIdNum;
	}

	public void setFwOrgEmtyIdNum(String fwOrgEmtyIdNum) {
		this.fwOrgEmtyIdNum = fwOrgEmtyIdNum;
	}

	public String getFwOrgEmtyAddrsTyp() {
		return fwOrgEmtyAddrsTyp;
	}

	public void setFwOrgEmtyAddrsTyp(String fwOrgEmtyAddrsTyp) {
		this.fwOrgEmtyAddrsTyp = fwOrgEmtyAddrsTyp;
	}

	public String getFwOrgEmtyAddrsLin1() {
		return fwOrgEmtyAddrsLin1;
	}

	public void setFwOrgEmtyAddrsLin1(String fwOrgEmtyAddrsLin1) {
		this.fwOrgEmtyAddrsLin1 = fwOrgEmtyAddrsLin1;
	}

	public String getFwOrgEmtyPostl() {
		return fwOrgEmtyPostl;
	}

	public void setFwOrgEmtyPostl(String fwOrgEmtyPostl) {
		this.fwOrgEmtyPostl = fwOrgEmtyPostl;
	}

	public String getFwOrgEmtyCtry() {
		return fwOrgEmtyCtry;
	}

	public void setFwOrgEmtyCtry(String fwOrgEmtyCtry) {
		this.fwOrgEmtyCtry = fwOrgEmtyCtry;
	}

	public String getFwOrgInvdCity() {
		return fwOrgInvdCity;
	}

	public void setFwOrgInvdCity(String fwOrgInvdCity) {
		this.fwOrgInvdCity = fwOrgInvdCity;
	}

	public String getFwOrgInvdState() {
		return fwOrgInvdState;
	}

	public void setFwOrgInvdState(String fwOrgInvdState) {
		this.fwOrgInvdState = fwOrgInvdState;
	}

	public String getFwOrgInvdPostl() {
		return fwOrgInvdPostl;
	}

	public void setFwOrgInvdPostl(String fwOrgInvdPostl) {
		this.fwOrgInvdPostl = fwOrgInvdPostl;
	}

	public String getFwOrgEmtyIdTyp() {
		return fwOrgEmtyIdTyp;
	}

	public void setFwOrgEmtyIdTyp(String fwOrgEmtyIdTyp) {
		this.fwOrgEmtyIdTyp = fwOrgEmtyIdTyp;
	}

	public String getInvdRecipRoutNum() {
		return InvdRecipRoutNum;
	}

	public void setInvdRecipRoutNum(String invdRecipRoutNum) {
		InvdRecipRoutNum = invdRecipRoutNum;
	}

	public String getInvdRecipRoutNum2() {
		return InvdRecipRoutNum2;
	}

	public void setInvdRecipRoutNum2(String invdRecipRoutNum2) {
		InvdRecipRoutNum2 = invdRecipRoutNum2;
	}

	public String getInvdRecipAccNum() {
		return InvdRecipAccNum;
	}

	public void setInvdRecipAccNum(String invdRecipAccNum) {
		InvdRecipAccNum = invdRecipAccNum;
	}

	public String getInvdAmt() {
		return InvdAmt;
	}

	public void setInvdAmt(String invdAmt) {
		InvdAmt = invdAmt;
	}

	public String getInvdUploadDoc() {
		return InvdUploadDoc;
	}

	public void setInvdUploadDoc(String invdUploadDoc) {
		InvdUploadDoc = invdUploadDoc;
	}

	public String getAmtMaxExcd1() {
		return AmtMaxExcd1;
	}

	public void setAmtMaxExcd1(String amtMaxExcd1) {
		AmtMaxExcd1 = amtMaxExcd1;
	}

	public String getAmtMaxExcd2() {
		return AmtMaxExcd2;
	}

	public void setAmtMaxExcd2(String amtMaxExcd2) {
		AmtMaxExcd2 = amtMaxExcd2;
	}

	public String getAmtMaxExcd3() {
		return AmtMaxExcd3;
	}

	public void setAmtMaxExcd3(String amtMaxExcd3) {
		AmtMaxExcd3 = amtMaxExcd3;
	}

	public String getAmtMaxExcd4() {
		return AmtMaxExcd4;
	}

	public void setAmtMaxExcd4(String amtMaxExcd4) {
		AmtMaxExcd4 = amtMaxExcd4;
	}

	public String getAmtMaxExcd5() {
		return AmtMaxExcd5;
	}

	public void setAmtMaxExcd5(String amtMaxExcd5) {
		AmtMaxExcd5 = amtMaxExcd5;
	}

	public String getAmtMaxExcd6() {
		return AmtMaxExcd6;
	}

	public void setAmtMaxExcd6(String amtMaxExcd6) {
		AmtMaxExcd6 = amtMaxExcd6;
	}

	public String getAmtMaxExcd7() {
		return AmtMaxExcd7;
	}

	public void setAmtMaxExcd7(String amtMaxExcd7) {
		AmtMaxExcd7 = amtMaxExcd7;
	}

	public String getFwEmtyRefToRecip() {
		return fwEmtyRefToRecip;
	}

	public void setFwEmtyRefToRecip(String fwEmtyRefToRecip) {
		this.fwEmtyRefToRecip = fwEmtyRefToRecip;
	}

	public String getFwEmtyInfoRcpFI() {
		return fwEmtyInfoRcpFI;
	}

	public void setFwEmtyInfoRcpFI(String fwEmtyInfoRcpFI) {
		this.fwEmtyInfoRcpFI = fwEmtyInfoRcpFI;
	}

	public String getFwInvdRefToRecip() {
		return fwInvdRefToRecip;
	}

	public void setFwInvdRefToRecip(String fwInvdRefToRecip) {
		this.fwInvdRefToRecip = fwInvdRefToRecip;
	}
	
	public String getFwSenderToRecipInfo() {
		return fwSenderToRecipInfo;
	}

	public void setFwSenderToRecipInfo(String fwSenderToRecipInfo) {
		this.fwSenderToRecipInfo = fwSenderToRecipInfo;
	}

	public String getEmptyRecipRoutNum() {
		return EmptyRecipRoutNum;
	}

	public void setEmptyRecipRoutNum(String emptyRecipRoutNum) {
		EmptyRecipRoutNum = emptyRecipRoutNum;
	}

	public String getEmptyRecipAccNum() {
		return EmptyRecipAccNum;
	}

	public void setEmptyRecipAccNum(String emptyRecipAccNum) {
		EmptyRecipAccNum = emptyRecipAccNum;
	}

	public String getEmptyAmt() {
		return EmptyAmt;
	}

	public void setEmptyAmt(String emptyAmt) {
		EmptyAmt = emptyAmt;
	}

	public String getEmptyDocTyp() {
		return EmptyDocTyp;
	}

	public void setEmptyDocTyp(String emptyDocTyp) {
		EmptyDocTyp = emptyDocTyp;
	}

	public String getInvdRecipNam() {
		return InvdRecipNam;
	}

	public void setInvdRecipNam(String invdRecipNam) {
		InvdRecipNam = invdRecipNam;
	}

	public String getInvdRecipNam2() {
		return InvdRecipNam2;
	}

	public void setInvdRecipNam2(String invdRecipNam2) {
		InvdRecipNam2 = invdRecipNam2;
	}

	public String getInvdRecipTown() {
		return InvdRecipTown;
	}

	public void setInvdRecipTown(String invdRecipTown) {
		InvdRecipTown = invdRecipTown;
	}

	public String getInvdRecipPhn() {
		return InvdRecipPhn;
	}

	public void setInvdRecipPhn(String invdRecipPhn) {
		InvdRecipPhn = invdRecipPhn;
	}

	public String getInvdRecipPhn2() {
		return InvdRecipPhn2;
	}

	public void setInvdRecipPhn2(String invdRecipPhn2) {
		InvdRecipPhn2 = invdRecipPhn2;
	}

	public String getEmptyRecipAddrsLine() {
		return EmptyRecipAddrsLine;
	}

	public void setEmptyRecipAddrsLine(String emptyRecipAddrsLine) {
		EmptyRecipAddrsLine = emptyRecipAddrsLine;
	}

	public String getEmptyRecipTown() {
		return EmptyRecipTown;
	}

	public void setEmptyRecipTown(String emptyRecipTown) {
		EmptyRecipTown = emptyRecipTown;
	}

	public String getEmptyRecipState() {
		return EmptyRecipState;
	}

	public void setEmptyRecipState(String emptyRecipState) {
		EmptyRecipState = emptyRecipState;
	}

	public String getEmptyRecipCntry() {
		return EmptyRecipCntry;
	}

	public void setEmptyRecipCntry(String emptyRecipCntry) {
		EmptyRecipCntry = emptyRecipCntry;
	}

	public String getEmptyRecipZipCod() {
		return EmptyRecipZipCod;
	}

	public void setEmptyRecipZipCod(String emptyRecipZipCod) {
		EmptyRecipZipCod = emptyRecipZipCod;
	}

	public String getEmptyRecipEmail() {
		return EmptyRecipEmail;
	}

	public void setEmptyRecipEmail(String emptyRecipEmail) {
		EmptyRecipEmail = emptyRecipEmail;
	}

	public String getEmptyRecipTyp() {
		return EmptyRecipTyp;
	}

	public void setEmptyRecipTyp(String emptyRecipTyp) {
		EmptyRecipTyp = emptyRecipTyp;
	}

	public String getEmptyRecipNam() {
		return EmptyRecipNam;
	}

	public void setEmptyRecipNam(String emptyRecipNam) {
		EmptyRecipNam = emptyRecipNam;
	}

	public String getEmptyRecipNam2() {
		return EmptyRecipNam2;
	}

	public void setEmptyRecipNam2(String emptyRecipNam2) {
		EmptyRecipNam2 = emptyRecipNam2;
	}

}