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
import com.etas.api.service.BookingService;

@RestController
public class BookingController {
	
	static Logger log = LogManager.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value = Mappings.BOOKING_REQUEST, method = RequestMethod.POST)
	public ResponseEntity<Object> createBookingRequest(@RequestBody Map<String, Object> playload) throws Exception {
		log.debug("Request received for:" + Mappings.BOOKING_REQUEST);
		Map<String, Object> response = bookingService.createBookingRequest(playload);
		log.debug("sending response");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = Mappings.BOOKING_REQUEST_STATUS, method = RequestMethod.POST)
	public ResponseEntity<Object> getRequestStatus(@PathVariable Integer requestId) throws Exception {
		log.debug("Request received for:" + Mappings.BOOKING_REQUEST_STATUS);
		Map<String, Object> response = bookingService.getRequestStatus(requestId);
		log.debug("sending response");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = Mappings.BOOKING_STATUS, method = RequestMethod.POST)
	public ResponseEntity<Object> getBookingStatus(@PathVariable Integer requestId) throws Exception {
		log.debug("Request received for:" + Mappings.BOOKING_STATUS);
		Map<String, Object> response = bookingService.getBookingStatus(requestId);
		log.debug("sending response");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
