package com.arvind.customerPortal.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arvind.customerPortal.constants.AuthsConstants;
import com.arvind.customerPortal.domain.PhoneEntity;
import com.arvind.customerPortal.domain.StoreEntity;
import com.arvind.customerPortal.domain.UserstoreEntity;
import com.arvind.customerPortal.exceptions.InsufficientAuthoritiesRuntimeException;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;
import com.arvind.customerPortal.model.ResponseMultiple;
import com.arvind.customerPortal.model.ResponseSingle;
import com.arvind.customerPortal.model.Status;
import com.arvind.customerPortal.model.Store;
import com.arvind.customerPortal.model.Userstore;
import com.arvind.customerPortal.request.validator.RequestValidatation;
import com.arvind.customerPortal.security.TokenParser;
import com.arvind.customerPortal.security.Validator;
import com.arvind.customerPortal.service.IStoreService;

@Service
public class StoreApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TokenParser tokenParser;

	@Autowired
	private Validator validator;

	@Autowired
	private IStoreService iStoreService;

	@Autowired
	private RequestValidatation requestValidatation;

	public ResponseEntity<?> createStore(Request request, String authorization) {

		logger.info("At createStore method in StoreApiController");
		requestValidatation.validatestoreRequest(request);
		verififcationUserAuthorizedToAccess(authorization, AuthsConstants.CREATE_STORE);

		StoreEntity StoreEntity = iStoreService.createStore(convertStoreModelToDomain(request.getStore()));
		try {
			if (null != StoreEntity) {
				return new ResponseEntity<>(getSuccessStatus(), HttpStatus.OK);

			} else {

				return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

		}

	}

	public ResponseEntity<?> searchStore(String storename,String authorization) {
		logger.info("At searchStore method in StoreApiController");
		verififcationUserAuthorizedToAccess(authorization, AuthsConstants.SEARCH_STORE);

		ResponseMultiple responseMultiple = new ResponseMultiple();		

		List<Store> storeList = iStoreService.searchStore(storename);

		try {
			if (null != storeList) {
				responseMultiple.setStores(storeList);
				responseMultiple.setStatus(getSuccessStatus());
				return new ResponseEntity<>(responseMultiple, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {

			return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

		}

	}

	public ResponseEntity<?> retrieveStore(String storeId, String authorization) {
		logger.info("At retrieveStore method in StoreApiController");
		verififcationUserAuthorizedToAccess(authorization, AuthsConstants.RETRIVE_STORE);

		ResponseSingle responseSingle = new ResponseSingle();

		List<Store> storeList = iStoreService.retrieveStore(storeId);

		try {
			if (null != storeList) {
				responseSingle.setStore(storeList.iterator().next());
				responseSingle.setStatus(getSuccessStatus());
				return new ResponseEntity<>(responseSingle, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

		}

	}

	public ResponseEntity<?> createUserstore(RequestUserstore requestUserstore, String authorization) {
		logger.info("At createUserstore method in StoreApiController");
		verififcationUserAuthorizedToAccess(authorization, AuthsConstants.CREATE_USERSTORE);

		requestValidatation.validateUserStoreRequest(requestUserstore);

		UserstoreEntity userstoreEntity = convertUserStoreModelToDomain(requestUserstore.getUserstore());
		boolean flag = iStoreService.createUserstore(userstoreEntity);

		if (flag) {
			return new ResponseEntity<>(getSuccessStatus(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> retriveUserstore(String username, String authorization) {
		logger.info("At createUserstore method in StoreApiController");
		verififcationUserAuthorizedToAccess(authorization, AuthsConstants.RETRIVE_USERSTORE);

		Integer userid = iStoreService.getUserByName(username);

		String s = iStoreService.getStoreId(userid);
		List<Store> storeList = iStoreService.retrieveStore(s);

		ResponseSingle responseSingle = new ResponseSingle();

		try {
			if (null != storeList) {
				responseSingle.setStore(storeList.iterator().next());
				responseSingle.setStatus(getSuccessStatus());
				return new ResponseEntity<>(responseSingle, HttpStatus.OK);

			} else {

				return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {

			return new ResponseEntity<>(getFailureStatus(), HttpStatus.BAD_REQUEST);

		}

	}

	private void verififcationUserAuthorizedToAccess(String token, String authResource) {
		logger.info("At verififcationUserAuthorizedToAccess method in StoreApiController");
		String role = tokenParser.getRole(token);
		boolean flag = validator.validate(role, authResource);

		if (!flag) {
			logger.error("Insufficient authorities to perform the action");
			throw new InsufficientAuthoritiesRuntimeException();
		}

	}

	private StoreEntity convertStoreModelToDomain(Store store) {
		logger.info("At convertStoreModelToDomain method in StoreApiController");
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setName(store.getName());
		storeEntity.setStoreid(store.getStoreid());
		storeEntity.setAddress(store.getAddress());

		PhoneEntity pe = new PhoneEntity();
		pe.setNumber(store.getPhone().getNumber());
		pe.setCc(store.getPhone().getCc());
		storeEntity.setPhone(pe);

		return storeEntity;
	}

	private UserstoreEntity convertUserStoreModelToDomain(Userstore userstore) {
		logger.info("At convertUserStoreModelToDomain method in StoreApiController");
		UserstoreEntity userstoreEntity = new UserstoreEntity();
		userstoreEntity.setUserId(userstore.getUserid());
		userstoreEntity.setStoreId(userstore.getStoreid());
		return userstoreEntity;
	}

	private Status getSuccessStatus() {
		Status status = new Status();
		status.setOk(true);
		status.setHttp(Integer.valueOf((HttpStatus.OK).toString()));
		status.setWhy("request successfull");
		return status;
	}

	private Status getFailureStatus() {

		Status status = new Status();
		status.setOk(false);
		status.setHttp(Integer.valueOf((HttpStatus.BAD_REQUEST).toString()));
		status.setWhy("request failed");
		return status;

	}

}
