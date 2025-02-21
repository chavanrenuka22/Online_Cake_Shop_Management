package com.etp.cakeshop.BD;

import java.util.*;

import com.etp.cakeshop.entity.BaseResponse;
import com.etp.cakeshop.entity.Cake;



public interface CakeBD {

	/**
	 * This Method is used to save the cakes
	 * 
	 * @param cake  cake passed as parameter
	 * @param logId logid passed as parameter
	 * @return
	 */
	BaseResponse<Cake> addCake(Cake cake, BaseResponse<Cake> baseResponse, String logID);

	/**
	 * @return Returns all the cakes
	 */
	List<Cake> getAllCakes();

//	BaseResponse<Cake> addCake(BaseRequest<Cake> baseRequest, BaseResponse<Cake> baseResponse, String logId);

//	Optional<Cake> getCakeById(Long id);
//
//	void deleteCake(Long id);
//
//	Cake updateCake(Long id, Cake updatedCake, String logId);

	// boolean cakeExists(Long id);
}
