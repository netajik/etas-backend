package com.etas.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etas.api.config.Mappings;
import com.etas.api.model.Cab;
import com.etas.api.service.CabService;

@RestController
public class CabController {

	static Logger log = LogManager.getLogger(CabController.class);

	@Autowired
	private CabService cabService;

	@RequestMapping(value = Mappings.GET_CABS, method = RequestMethod.GET)
	public ResponseEntity<Object> getCabs() throws Exception {
		log.debug("Request received for:" + Mappings.GET_CABS);
		List<Object> cabs = cabService.getCabs();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("cabs", cabs);
		response.put("status", "success");
		log.debug("sending response having list of cabs");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_CAB, method = RequestMethod.GET)
	public ResponseEntity<Object> getCab(@PathVariable Integer cabId) {
		log.debug("Request for : " + Mappings.GET_CAB);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Map<String, Object> cab = cabService.getCab(cabId);
			response.put("cab", cab);
			response.put("status", "success");
		} catch (Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("sending respose having cab of requested cabId :" + cabId);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.CREATE_CAB, method = RequestMethod.POST)
	public ResponseEntity<Object> createCab(@RequestBody Map<String, Object> payload) {
		log.debug("Request received for:" + Mappings.CREATE_CAB);
		log.debug("new cab details:" + payload);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cab cab = cabService.createCab(payload);
			log.debug("new cabId: " + cab.getId());
			response.put("cab", cab);
			response.put("status", "success");
		} catch (Exception e) {
			log.debug("stack trace",e);
			log.error(e);
			response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		log.debug("sending response having list of cabs");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.UPDATE_CAB, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCab(@RequestBody Map<String, Object> payload) {
		log.debug("Request for : " + Mappings.UPDATE_CAB);
		log.debug("update content" + payload);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cab cab = cabService.updateCab(payload);
			response.put("status", "success");
		} catch (Exception e) {
			log.debug("stack trace",e);
			log.debug(e);
			response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("sending respose for cab update ");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = { Mappings.UPDATE_CAB_AVAILABLE,
			Mappings.UPDATE_CAB_UNAVAILABLE }, method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCabAvailability(@PathVariable Integer cabId) {
		log.debug("Request for : " + Mappings.UPDATE_CAB_AVAILABLE);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cab cab = cabService.updateCabAvailability(cabId);
			response.put("status", "success");
		} catch (Exception e) {
			// response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("sending respose for cab update ");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_CAB, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCab(@PathVariable Integer cabId) {
		log.debug("Request for : " + Mappings.DELETE_CAB);
		log.debug("request Id:" + cabId);
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Cab cab = cabService.deleteCab(cabId);
			response.put("status", "success");
		} catch (Exception e) {
			// response.put("message", e.getMessage());
			return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
		}
		log.debug("deleted cab object corresponding requested Id: " + cabId);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
