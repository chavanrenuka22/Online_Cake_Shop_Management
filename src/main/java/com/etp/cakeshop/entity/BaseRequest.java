package com.etp.cakeshop.entity;

/**
 * @author This is baserequest data and actionId
 * @param <T>
 */
public class BaseRequest<T> {

	private T data;
	private String actionId;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getActionId() {
		return actionId;
	
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return "BaseRequest{" + "logId='" + actionId + '\'' + ", data=" + data + '}';
	}
}
