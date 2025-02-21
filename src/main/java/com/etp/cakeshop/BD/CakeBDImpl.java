package com.etp.cakeshop.BD;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etp.cakeshop.controller.LogginConstants;
import com.etp.cakeshop.dao.CakeDAO;
import com.etp.cakeshop.entity.BaseResponse;
import com.etp.cakeshop.entity.Cake;
import com.etp.cakeshop.service.CakeServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CakeBDImpl implements CakeBD {

	private static final Logger LOG = LogManager.getFormatterLogger();

	private final ObjectMapper objectMapper;

	public CakeBDImpl(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Autowired
	private CakeDAO cakeDao;

	@Override
	public BaseResponse<Cake> addCake(Cake cake, BaseResponse<Cake> baseResponse, String logId) {
		try {
			long startTime = System.currentTimeMillis();
			List<Cake> responseList = new ArrayList<>();

			if (cake == null) {
				baseResponse.setMessage(LogginConstants.CAKE_DATA_NULL);
				baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
				return baseResponse;
			}

			if (cake.getName() == null || cake.getName().trim().isEmpty()) {
				baseResponse.setMessage(LogginConstants.CAKE_NAME_EMPTY);
				baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
				return baseResponse;
			}

			if (cake.getFlavor() == null || cake.getFlavor().trim().isEmpty()) {
				baseResponse.setMessage(LogginConstants.CAKE_FLAVOR_EMPTY);
				baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
				return baseResponse;
			}

			if (cake.getPrice() == null || cake.getPrice() <= 0) {
				baseResponse.setMessage(LogginConstants.INVALID_CAKE_PRICE);
				baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
				return baseResponse;
			}

			if (cake.getImageUrl() == null || cake.getImageUrl().trim().isEmpty()) {
				baseResponse.setMessage(LogginConstants.CAKE_IMAGE_EMPTY);
				baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
				return baseResponse;
			}

			// Save the cake
			Cake savedCake = cakeDao.save(cake);
			responseList.add(savedCake);

			baseResponse.setMessage(LogginConstants.CAKE_SAVED_SUCCESSFULLY);
			baseResponse.setData(savedCake);
			baseResponse.setResponse(CakeServiceImpl.settingResponseForSuccess(logId));

			long endTime = System.currentTimeMillis();
			LOG.info("{} | {} {}{}", logId, LogginConstants.CAKE_SAVE_SUCCESS, LogginConstants.EXEC_TIME,
					(endTime - startTime));

			return baseResponse;
		} catch (Exception e) {
			LOG.error("{} | {} {}", logId, LogginConstants.CAKE_SAVE_ERROR, e.getMessage(), e);

			baseResponse.setMessage(LogginConstants.ERROR_PROCESSING_REQUEST + e.getMessage());
			baseResponse.setResponse(CakeServiceImpl.settingResponseForError(logId));
			return baseResponse;
		}
	}

	/**
	 * Used to save the cakes in a database
	 */
	public Cake saveCake(Cake cake, String logId) {
		try {
			validateCake(cake);

			Cake savedCake = cakeDao.save(cake);
			return savedCake;
		} catch (IllegalArgumentException e) {
			System.err.println("Validation failed: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get all the cakes
	 */

	private void validateCake(Cake cake) {
		try {
			if (cake.getName() == null || cake.getName().trim().isEmpty()) {
				System.err.println("Validation error: Cake name cannot be empty.");
				return;
			}
			if (cake.getPrice() == null || cake.getPrice() <= 0) {
				System.err.println("Validation error: Cake price must be greater than zero.");
				return;
			}
			if (cake.getFlavor() == null || cake.getFlavor().trim().isEmpty()) {
				System.err.println("Validation error: Cake flavor cannot be empty.");
				return;
			}
			if (cake.getDescription() != null && cake.getDescription().length() > 255) {
				System.err.println("Validation error: Cake description is too long. Max 255 characters.");
				return;
			}
		} catch (Exception e) {
			System.err.println("Unexpected error during cake validation: " + e.getMessage());
		}
	}

	@Override
	public List<Cake> getAllCakes() {

		return cakeDao.findAll();
	}
}
