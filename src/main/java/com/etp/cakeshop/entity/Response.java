package com.etp.cakeshop.entity;

/**
 * @author This class is used to Send the Response and it used the constants 
 *
 */
public class Response {

	public static final String RESPONSE_STATUS_SUCCESS = "S";
	public static final String RESPONSE_STATUS_FAILURE = "F";
	public static final String ERROR_TYPE_INFO = "I";
	public static final String ERROR_TYPE_ERROR = "E";
	public static final String ERROR_TYPE_WARNING = "W";
	public static final String RESPONSE_SUCCESS_0 = "0000";
	public static final String RESPONSE_FAILURE_1 = "1111";
	
   private String respMsg;
   
	private String errorType;

	private String msgId;

	private String respCode;

	private String respStatus;

	private String logId;

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespStatus() {
		return respStatus;
	}

	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	

	
}
