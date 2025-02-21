package com.etp.cakeshop.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etp.cakeshop.BD.CakeBD;
import com.etp.cakeshop.Util.LogUtil;
import com.etp.cakeshop.controller.LogginConstants;
import com.etp.cakeshop.entity.ActionInfo;
import com.etp.cakeshop.entity.BaseRequest;
import com.etp.cakeshop.entity.BaseResponse;
import com.etp.cakeshop.entity.Cake;
import com.etp.cakeshop.entity.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CakeServiceImpl implements CakeService {

	@Autowired
	private CakeBD cakeBd;

	private final ObjectMapper objectMapper;

	public CakeServiceImpl(CakeBD cakeBd, ObjectMapper objectMapper) {
		super();
		this.cakeBd = cakeBd;
		this.objectMapper = objectMapper;
	}

	private static final Logger logger = LoggerFactory.getLogger(CakeServiceImpl.class);

	@Override
	public BaseResponse<Cake> addCake(String inputJsonString, BaseResponse<Cake> baseResponse, String logID) {
		logger.info("{} | {}", logID, LogginConstants.CAKE_SAVE_START);
		long startTime = System.currentTimeMillis();
		String errorMessage = "";

		BaseRequest<Cake> baseRequest;
		try {
			baseRequest = objectMapper.readValue(inputJsonString, new TypeReference<BaseRequest<Cake>>() {
			});
		} catch (JsonProcessingException e) {
			logger.error("{} | {} {}", logID, LogginConstants.JSON_PARSE_ERROR, e.getMessage(), e);
			return new BaseResponse<>(logID, LogginConstants.JSON_PARSE_ERROR + e.getMessage(), null);
		}

		Cake cake = baseRequest.getData();

		if (baseRequest.getActionId() == null || baseRequest.getActionId().isEmpty()
				|| !"savecake".equals(baseRequest.getActionId())) {
			errorMessage = LogginConstants.INVALID_ACTION_ID;
			logger.error("{} | {}", logID, errorMessage);
			return new BaseResponse<>(logID, errorMessage, null);
		}

//		BaseResponse<Cake> savedCakeResponse = new BaseResponse<Cake>();
		try {

			baseResponse = cakeBd.addCake(cake, baseResponse, logID);
			Cake savedCake = baseResponse.getData();

		} catch (Exception e) {
			logger.error("{} | {} {}", logID, LogginConstants.CAKE_SAVE_ERROR, e.getMessage(), e);

		}

		long endTime = System.currentTimeMillis();
		logger.info("{} | {} {}{}", logID, LogginConstants.CAKE_SAVE_SUCCESS, LogginConstants.EXEC_TIME,
				(endTime - startTime));

		return baseResponse;
	}

	@Override
	public BaseResponse<List<Cake>> getAllCakes() {
		String logId = LogUtil.generateLogId();
		logger.info("{} | {}", logId, LogginConstants.CAKE_SAVE_START);

		long startTime = System.currentTimeMillis();
		BaseResponse<List<Cake>> response = new BaseResponse<List<Cake>>();

		try {
			List<Cake> bdResponse = cakeBd.getAllCakes();

			response.setData(bdResponse);
			response.setLogId(logId);
			response.setMessage(LogginConstants.CAKE_SAVE_SUCCESS);
			response.setResponse(settingResponseForSuccess(logId));
		} catch (Exception e) {
			logger.error("{} | {} {}", logId, LogginConstants.CAKE_SAVE_ERROR, e.getMessage(), e);

			response = new BaseResponse<>(logId, LogginConstants.ERROR_PROCESSING_REQUEST + e.getMessage(), null);
			response.setResponse(settingResponseForError(logId));
		}

		long executionTime = System.currentTimeMillis() - startTime;
		logger.info("{} | {} {}{}", logId, LogginConstants.CAKE_SAVE_SUCCESS, LogginConstants.EXEC_TIME, executionTime);

		return response;
	}

	public static Response settingResponseForSuccess(String logId) {
		Response response = new Response();
		response.setErrorType(Response.ERROR_TYPE_INFO);
		response.setLogId(logId);
		response.setMsgId(logId);
		response.setRespCode(Response.RESPONSE_SUCCESS_0);
		response.setRespStatus(Response.RESPONSE_STATUS_SUCCESS);
		response.setRespMsg("Successfully retrieved the Data");
		return response;
	}

	public static Response settingResponseForError(String logId) {
		Response response = new Response();
		response.setErrorType(Response.ERROR_TYPE_WARNING);
		response.setLogId(logId);
		response.setMsgId(logId);
		response.setRespCode(Response.RESPONSE_FAILURE_1);
		response.setRespStatus(Response.RESPONSE_STATUS_FAILURE);
		response.setRespMsg("Error retrieving the Data");
		return response;
	}

}
