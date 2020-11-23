package com.aircode.admin.common.exception;

/**
 * <pre>
 * 1.개요 : CMS 서비스에서 발생되는 예외 처리(message.properties에 관리)
 * </pre>
 *
 * @Author  : baek
 * @Date    : 2016. 4. 19.
 * @Version : 
 */
@SuppressWarnings("serial")
public class CmsException extends RuntimeException {
	private String errCode;
	private String errMessage;
	
	public CmsException(String code, String message) {
		this.errCode = code;
		this.errMessage = message;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getErrCode()).append("]");
		sb.append("[").append(getErrMessage()).append("]");

		return sb.toString();
	}
}
