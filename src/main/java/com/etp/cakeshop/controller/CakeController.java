package com.etp.cakeshop.controller;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etp.cakeshop.Constants.RestMappingProvider;
import com.etp.cakeshop.Helper.IDHelper;
import com.etp.cakeshop.Util.LogUtil;
import com.etp.cakeshop.entity.BaseResponse;
import com.etp.cakeshop.entity.Cake;
import com.etp.cakeshop.service.CakeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This class handles all the REST API calls related to Cake operations.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cakes")
public class CakeController {

	private static final Logger logger = LoggerFactory.getLogger(CakeController.class);

	@Autowired
	private CakeService cakeService;

	private final ObjectMapper objectMapper;

	public CakeController(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	 * Saves a new cake in the system.
	 * 
	 * @param baseRequest The request containing a log ID and cake details.
	 * @return BaseResponse containing saved cake details or an error message.
	 */
	
	
	@RequestMapping(path = RestMappingProvider.SAVE_CAKE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@CrossOrigin
	public BaseResponse<Cake> saveCake(@Context HttpServletRequest httpRequest, @RequestBody String inputJsonString){
		long startTime = System.currentTimeMillis();
		String[] IDs = IDHelper.getIDFromJsonInput(inputJsonString);
		String logID = IDs[1];
		IDs = null;
		
	    BaseResponse<Cake> baseResponse = new BaseResponse<>();
	    
	   if(inputJsonString !=null)
	    baseResponse = cakeService.addCake(inputJsonString, baseResponse, logID);
	   
	   else
	   return null;
	   
	    inputJsonString = null;
  
	    long endTime = System.currentTimeMillis();
	    logger.info(new StringBuilder().append(logID)
	    	    .append(Objects.toString(baseResponse.getResponse(), "No Response Data")).toString());

	    	logger.info(new StringBuilder().append(logID)
	    	    .append(LogginConstants.CAKE_SAVE)
	    	    .append(LogginConstants.EXEC_TIME)
	    	    .append((endTime - startTime)).toString());

	    	logger.info(new StringBuilder().append(logID)
	    	    .append(LogginConstants.SENDING_RESPONSE).toString());

	    logID = null;

	    return baseResponse;
	}

	
	
	@RequestMapping(path = RestMappingProvider.GET_ALL_CAKES, method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@CrossOrigin
	public BaseResponse<List<Cake>> getAllCakes() {
		long startTime = System.currentTimeMillis();
		String logId = LogUtil.generateLogId();
		logger.info("Log ID: {} |{}",logId,  LogginConstants.REQUEST_RECEIVED);
		
		try {
			BaseResponse<List<Cake>> response = cakeService.getAllCakes();
			long executionTime = System.currentTimeMillis() - startTime;
			logger.info("{} | Execution Time: {} ms", logId, executionTime);
			return response;
		} catch (Exception e) {
			logger.error("{} | Error retrieving cakes", logId, e);
			return new BaseResponse<>(logId, "Error retrieving cakes: " + e.getMessage(), null);
		}
	}
}