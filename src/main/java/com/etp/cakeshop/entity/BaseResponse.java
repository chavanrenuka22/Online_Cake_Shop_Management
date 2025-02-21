package com.etp.cakeshop.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Swapnil This is a baseResponse class
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private String logId;
    private String message;
    private T data;
    private Response response;

    public BaseResponse() {
    }

    public BaseResponse(String logId, String message, T data) {
        this.logId = logId;
        this.message = message;
        this.data = data;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "logId='" + logId + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
