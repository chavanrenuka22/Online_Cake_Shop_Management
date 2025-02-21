package com.etp.cakeshop.service;

import java.util.List;

import com.etp.cakeshop.entity.BaseResponse;
import com.etp.cakeshop.entity.Cake;

public interface CakeService {

	/**
	 * contains the logic for Adding the cake
	 * 
	 * @param inputJsonString
	 * @param baseResponse unique logid for tracking the request
	 * @return contains the saved cake or an error message
	 */
	 BaseResponse<Cake>  addCake( String inputJsonString, BaseResponse <Cake> baseResponse, String logID);

	/**
	 * @return the list of cakes
	 */
	BaseResponse<List<Cake>> getAllCakes();

	/**
	 * @param cakeId
	 * @return returns the cake by its id
	 */
//	BaseResponse<Cake> getCakeById(String cakeId);

	/**
	 * @param cakeId
	 * @param updatedCake updates the cake by its id
	 * @param logId
	 * @return
	 */
//	BaseResponse<Cake> updateCakeById(String cakeId, Cake updatedCake, String logId);

	/**
	 * @param cakeId
	 * @param logId  deletes cake by its id
	 * @return
	 */
//	BaseResponse<String> deleteCakeById(String cakeId, String logId);
}
