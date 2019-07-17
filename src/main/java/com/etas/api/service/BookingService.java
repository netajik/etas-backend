package com.etas.api.service;

import java.text.ParseException;
import java.util.Map;

import com.etas.api.model.Booking;

public interface BookingService {
	
	public Map<String, Object> createBookingRequest(Map<String, Object> payload) ;
	
	public Map<String, Object> getRequestStatus(Integer requestId) throws Exception;
	
	public Map<String, Object> getBookingStatus(Integer requestId) throws Exception;
}
