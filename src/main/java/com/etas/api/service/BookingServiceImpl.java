package com.etas.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.etas.api.config.Constants;
import com.etas.api.dao.BookingDao;
import com.etas.api.dao.CabDao;
import com.etas.api.dao.EmployeeDao;
import com.etas.api.helpers.UtilityHelper;
import com.etas.api.model.Booking;
import com.etas.api.model.Cab;

@Component("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	Logger log = LogManager.getLogger(BookingService.class);
	
	@Autowired
	BookingDao bookingDao ;
	@Autowired
	private CabDao cabDao;
	@Autowired
	private CabService cabService;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeService employeeService;
	@Override
	public Map<String, Object> createBookingRequest(Map<String, Object> payload) {
		Map<String, Object> bookingResponse = new HashMap<String, Object>();
		Booking booking = new Booking();
		Cab cab = cabDao.getAvailableCab();
		booking.setBookedOn(new Date());
		booking.setEmployee(employeeDao.findById((Integer) payload.get("driverId")));
		booking.setSourceLocation(payload.get("sourceLocation").toString());
		try {
			booking.setJourneyOn(new SimpleDateFormat("yyyy-MM-dd").parse(payload.get("dateTimeOfJourney").toString()));
		} catch (ParseException e) {
			e.printStackTrace();
			log.debug("exception in parsing date");
		}
		if(cab !=null) {
			booking.setCab(cab);
			booking.setStatus((byte) 1);
			bookingDao.save(booking);
			bookingResponse.put("requestId", booking.getId());
			bookingResponse.put("status", Constants.CAB_AVAILABLE);
		} else if(cab == null) {
			bookingResponse.put("requestId", "");
			bookingResponse.put("status", Constants.CAB_NOT_AVAILABLE);
		}
		return bookingResponse;
	}
	@Override
	public Map<String, Object> getRequestStatus(Integer requestId) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		if(requestId !=null) {
			Booking booking = bookingDao.findById(requestId);
			response.put("requestId", booking.getId());
			response.put("sourceLocation", booking.getSourceLocation());
			response.put("dateTimeOfJourney", booking.getJourneyOn());
			response.put("requestCreationDate", booking.getBookedOn());
			if(booking.getStatus() == Constants.PROCESSED) {
				response.put("status", Constants.PROCESSED);
			} else if(booking.getStatus() == Constants.GENERATED) {
				response.put("status", Constants.GENERATED);
			} else if(booking.getStatus() == Constants.FAILED ) {
				response.put("status", Constants.FAILED);
			} else if(booking.getStatus() == Constants.CLOSED ) {
				response.put("status", Constants.CLOSED);
			} else if(booking.getStatus() == Constants.CANCELLED ) {
				response.put("status", Constants.CANCELLED);
			}
		} else {
			throw new Exception("request Id not found");
		}
		return response;
	}
	@Override
	public Map<String, Object> getBookingStatus(Integer requestId) throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		if(requestId !=null) {
			Booking booking = bookingDao.findById(requestId);
			response.put("requestId", booking.getId());
			response.put("sourceLocation", booking.getSourceLocation());
			response.put("dateTimeOfJourney", booking.getJourneyOn());
			response.put("passengerDetails", employeeService.getEmployee(booking.getEmployee()));
			response.put("driverDetails", employeeService.getEmployee(booking.getCab().getEmployee()));
			response.put("vehicalDetails", cabService.getCab(booking.getCab().getId()));
			if(booking.getStatus() == Constants.CANCELLED ) {
				response.put("Status", Constants.CANCELLED);
			} else if(booking.getStatus() == Constants.CLOSED ) {
				response.put("Status", Constants.CLOSED);
			}
		} else {
			throw new Exception("request Id not found");
		}
		return response;
	}
	
	
}
