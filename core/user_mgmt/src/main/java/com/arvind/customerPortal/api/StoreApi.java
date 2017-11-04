package com.arvind.customerPortal.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arvind.customerPortal.api.controller.StoreApiController;
import com.arvind.customerPortal.model.Request;
import com.arvind.customerPortal.model.RequestUserstore;

@RestController
@RequestMapping("")
public class StoreApi {

	@Autowired
	private StoreApiController storeApiController;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = ("/store/create"))
	@ResponseBody
	public ResponseEntity<?> createStore(@Valid @RequestBody Request request, final BindingResult errors) {
		logger.info("createStore method in StoreApi ");
		return storeApiController.createStore(request);
	}

	@PostMapping(value = ("/store/search/{name}"))
	@ResponseBody
	public ResponseEntity<?> searchStore(@PathVariable String name) {
		logger.info("searchStore method in StoreApi ");
		return storeApiController.searchStore(name);

	}

	@PostMapping(value = ("/store/retrieve/{id}"))
	@ResponseBody
	public ResponseEntity<?> retrieveStore(@PathVariable String id) {
		logger.info("retrieveStore method in StoreApi ");
		return storeApiController.retrieveStore(id);

	}

	@PostMapping(value = ("/userstore/create"))
	@ResponseBody
	public ResponseEntity<?> createUserstore(@RequestBody @Valid final RequestUserstore requestUserstore, final BindingResult errors) {
		logger.info("createUserstore method in StoreApi ");
		return storeApiController.createUserstore(requestUserstore);
	}

	@PostMapping(value = ("/userstore/retrieve/{username}"))
	@ResponseBody
	public ResponseEntity<?> retriveUserStore(@PathVariable String username) {

		logger.info("retriveUserStore method in StoreApi ");
		return storeApiController.retriveUserstore(username);
	}

}
